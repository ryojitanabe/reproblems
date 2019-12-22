package jmetal.experiments.studies;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.logging.Logger;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Level;
import jmetal.core.Algorithm;
import jmetal.core.Problem;
import jmetal.experiments.Experiment;
import jmetal.experiments.Settings;
import jmetal.experiments.settings.GWASFGA_Settings;
import jmetal.experiments.settings.MOEAD_Settings;
import jmetal.experiments.settings.NSGAII_Settings;
import jmetal.qualityIndicator.util.MetricsUtil;
import jmetal.util.JMException;
import jmetal.util.ReferencePoint;
import jmetal.problems.ProblemFactory;
import jmetal.util.AchievementScalarizingFunction;

/**
 * Class implementing an example of experiment using GWASFGA as base algorithm.
 * The experiment consisting in studying GWASFGA in several problems
 */
public class GWASFGAStudy3D extends Experiment {

	/**
	 * Configures the algorithms in each independent run
	 *
	 * @param problem
	 *            The problem to solve
	 * @param problemIndex
	 * @param algorithm
	 *            Array containing the algorithms to run
	 * @throws ClassNotFoundException
	 */
	public synchronized void algorithmSettings(String problemName, int problemIndex, Algorithm[] algorithm) throws ClassNotFoundException {
		try {
			MetricsUtil paretoFrontInformation = new MetricsUtil();
			String paretoFrontDirectory = new String("data/paretoFronts/");
			String paretoFrontFilePath = new String(paretoFrontDirectory + paretoFrontFile_[problemIndex]);
			int numberOfAlgorithms = algorithmNameList_.length;
			Object[] problemParams = { "Real" };
			Problem problem = (new ProblemFactory()).getProblem(problemName, problemParams);

			// Common configuration
			HashMap[] parameters = new HashMap[numberOfAlgorithms];
			for (int i = 0; i < numberOfAlgorithms; i++) {
				parameters[i] = new HashMap();
				parameters[i].put("populationSize_", 300);
				parameters[i].put("maxEvaluations_", 240000); 
			} // for

			// GWASFGA configuration
			parameters[0].put("weightsDirectory_", "data");
			parameters[0].put("normalization_", true);
			parameters[0].put("utopianValueInPercent_", 1.0);
			parameters[0].put("estimatePoints_", true);
			// If the nadir and ideal points are not known, they have to be estimated
			// In other case, they are calculated using the Pareto front
			AchievementScalarizingFunction asfInUtopian, asfInNadir;
			if ((Boolean) parameters[0].get("estimatePoints_")) {
				asfInUtopian = new AchievementScalarizingFunction(problem.getNumberOfObjectives());
				asfInNadir = new AchievementScalarizingFunction(problem.getNumberOfObjectives());
			} else {
				double[][] paretoFront = paretoFrontInformation.readFront(paretoFrontFilePath);
				double[] idealPoint = paretoFrontInformation.getMinimumValues(paretoFront, problem.getNumberOfObjectives());
				double[] nadirPoint = paretoFrontInformation.getMaximumValues(paretoFront, problem.getNumberOfObjectives());
				double[] utopianPoint = new double[idealPoint.length];
				for (int indexObjective = 0; indexObjective < idealPoint.length; indexObjective++) {
					double quantity = Math.abs((nadirPoint[indexObjective] - idealPoint[indexObjective]) * (Double) parameters[0].get("utopianValueInPercent_")) / 100;
					utopianPoint[indexObjective] = idealPoint[indexObjective] - quantity;
					nadirPoint[indexObjective] = nadirPoint[indexObjective] + quantity;
				}
				asfInUtopian = new AchievementScalarizingFunction(new ReferencePoint(utopianPoint), nadirPoint, idealPoint);
				asfInNadir = new AchievementScalarizingFunction(new ReferencePoint(nadirPoint), nadirPoint, idealPoint);
			}
			parameters[0].put("asfInUtopian_", asfInUtopian);
			parameters[0].put("asfInNadir_", asfInNadir);

			if ((!paretoFrontFile_[problemIndex].equals("")) || (paretoFrontFile_[problemIndex] == null)) {
				for (int i = 0; i < numberOfAlgorithms; i++) {
					parameters[i].put("paretoFrontFile_", paretoFrontFile_[problemIndex]);
				}

				algorithm[0] = new GWASFGA_Settings(problemName).configure(parameters[0]);				
			}
		} catch (IllegalArgumentException ex) {
			Logger.getLogger(GWASFGAStudy3D.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			Logger.getLogger(GWASFGAStudy3D.class.getName()).log(Level.SEVERE, null, ex);
		} catch (JMException ex) {
			Logger.getLogger(GWASFGAStudy3D.class.getName()).log(Level.SEVERE, null, ex);
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} // algorithmSettings

	public static void main(String[] args) throws JMException, IOException {
		GWASFGAStudy3D exp = new GWASFGAStudy3D();

		exp.experimentName_ = "GWASFGAStudy3D";
		exp.algorithmNameList_ = new String[] { "GWASFGA" };

		exp.problemList_ = new String[] { "DTLZ1", "DTLZ2"};

		exp.paretoFrontFile_ = new String[] { "DTLZ1.3D.pf", "DTLZ2.3D.pf" };

		exp.indicatorList_ = new String[] { "HV" };

		int numberOfAlgorithms = exp.algorithmNameList_.length;

		exp.experimentBaseDirectory_ = "." + exp.experimentName_;
		exp.paretoFrontDirectory_ = "data/paretoFronts";

		exp.algorithmSettings_ = new Settings[numberOfAlgorithms];

		exp.independentRuns_ = 1;

		exp.deleteWithChildren(exp.experimentBaseDirectory_);

		// Run the experiments
		exp.initExperiment();
		exp.runExperiment(1);

		generateCombinedObjectivesFiles(exp);
	} // main

	/**
	 * Deletes the given path and, if it is a directory, deletes all its
	 * children.
	 */
	public boolean deleteWithChildren(String path) {
		File file = new File(path);
		if (!file.exists()) {
			return true;
		}
		if (!file.isDirectory()) {
			return file.delete();
		}
		return this.deleteChildren(file) && file.delete();
	}

	private boolean deleteChildren(File dir) {
		File[] children = dir.listFiles();
		boolean childrenDeleted = true;
		for (int i = 0; children != null && i < children.length; i++) {
			File child = children[i];
			if (child.isDirectory()) {
				childrenDeleted = this.deleteChildren(child) && childrenDeleted;
			}
			if (child.exists()) {
				childrenDeleted = child.delete() && childrenDeleted;
			}
		}
		return childrenDeleted;
	}

	private static void generateCombinedObjectivesFiles(GWASFGAStudy3D exp) throws IOException {
		int numberOfObjectives = 0;

		for (int algorithm = 0; algorithm < exp.algorithmNameList_.length; algorithm++) {
			for (int problem = 0; problem < exp.problemList_.length; problem++) {
				// Read values from data files
				String directory = exp.experimentBaseDirectory_;
				directory += "/data";
				directory += "/" + exp.algorithmNameList_[algorithm];
				directory += "/" + exp.problemList_[problem];

				String fileName = directory + "/FUN.acum";
				FileWriter outputFile = new FileWriter(fileName, false);

				for (int run = 0; run < exp.independentRuns_; run++) {
					double[][] solutions = readSolutionsFromFile(directory + "/FUN." + run);

					for (int solution = 0; solution < solutions.length; solution++) {
						numberOfObjectives = solutions[0].length;
						for (int objective = 0; objective < numberOfObjectives; objective++) {
							outputFile.write(solutions[solution][objective] + " ");
							;
						}
						outputFile.write("\n");
					} // for
				} // for
				outputFile.close();
				System.out.println("File " + fileName + " created successfully.");

				String plotFileName = new String(exp.experimentBaseDirectory_ + "/" + exp.algorithmNameList_[algorithm] + "-" + exp.problemList_[problem] + ".bat");
				FileWriter plotFile = new FileWriter(plotFileName, true);

				String command = new String();
				if (numberOfObjectives == 2)
					command = "gnuplot -e \"plot '../" + exp.paretoFrontDirectory_ + "/" + exp.paretoFrontFile_[problem] + "' with lines linewidth 3 linecolor 9 tit 'Pareto optimal front (" + exp.problemList_[problem] + ")', '" + fileName.substring(fileName.indexOf("/") + 1)
							+ "' with points pointtype 6 pointsize 1 linecolor 1;pause mouse close\";";
				else if (numberOfObjectives == 3)
					command = "gnuplot -e \"splot '../" + exp.paretoFrontDirectory_ + "/" + exp.paretoFrontFile_[problem] + "' with points linewidth 1 linecolor 9 tit 'Pareto optimal front (" + exp.problemList_[problem] + ")', '" + fileName.substring(fileName.indexOf("/") + 1)
							+ "' with points pointtype 6 pointsize 1 linecolor 1;pause mouse close\";";

				plotFile.write(command);
				plotFile.close();

				System.out.println("File " + plotFileName + " created successfully.");
			} // for
		} // for
	}

	/**
	 * This method reads solutions from a file.
	 *
	 * @param path
	 *            The path to the file that contains the solutions
	 * @return double [][] whit the solutions readed
	 *
	 */
	private static double[][] readSolutionsFromFile(String path) {
		try {
			// Open the file
			FileInputStream fis = new FileInputStream(path);
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader br = new BufferedReader(isr);

			List<double[]> list = new ArrayList<double[]>();
			int numberOfObjectives = 0;
			String aux = br.readLine();
			while (aux != null) {
				StringTokenizer st = new StringTokenizer(aux);
				int i = 0;
				numberOfObjectives = st.countTokens();
				double[] vector = new double[st.countTokens()];
				while (st.hasMoreTokens()) {
					double value = (new Double(st.nextToken())).doubleValue();
					vector[i] = value;
					i++;
				}
				list.add(vector);
				aux = br.readLine();
			}

			br.close();

			double[][] front = new double[list.size()][numberOfObjectives];
			for (int i = 0; i < list.size(); i++) {
				front[i] = list.get(i);
			}
			return front;

		} catch (Exception e) {
			System.out.println("InputFacilities crashed reading for file: " + path);
			e.printStackTrace();
		}
		return null;
	} // readFront
} // GWASFGAStudy

