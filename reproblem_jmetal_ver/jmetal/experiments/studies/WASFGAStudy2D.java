package jmetal.experiments.studies;

import java.io.File;
import java.util.logging.Logger;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import jmetal.core.Algorithm;
import jmetal.core.Problem;
import jmetal.experiments.Experiment;
import jmetal.experiments.Settings;
import jmetal.experiments.settings.WASFGA_Settings;
import jmetal.problems.ProblemFactory;
import jmetal.qualityIndicator.util.MetricsUtil;
import jmetal.util.AchievementScalarizingFunction;
import jmetal.util.JMException;
import jmetal.util.ReferencePoint;

/**
 * @author Rubén Saborido Infantes
 * Class implementing an example of experiment using WASFGA as base algorithm.
 * The experiment consisting in studying WASFGA in several problems
 */
public class WASFGAStudy2D extends Experiment 
{
    private static ReferencePoint[] referencePoints;    

    /**
     * Configures the algorithms in each independent run
     *
     * @param problem The problem to solve
     * @param problemIndex
     * @param algorithm Array containing the algorithms to run
     * @throws ClassNotFoundException
     */
    public synchronized void algorithmSettings(String problemName,
            int problemIndex,
            Algorithm[] algorithm)
            throws ClassNotFoundException {
        try {
            MetricsUtil paretoFrontInformation = new MetricsUtil();
            int numberOfAlgorithms = algorithmNameList_.length;
            Object[] problemParams = {"Real"};
            Problem problem = (new ProblemFactory()).getProblem(problemName, problemParams);
            
            String paretoFrontDirectory = new String("data/paretoFronts/");
            String paretoFrontFilePath = new String(paretoFrontDirectory + paretoFrontFile_[problemIndex]);

            double[][] paretoFront = paretoFrontInformation.readFront(paretoFrontFilePath);
            double[] idealPoint = paretoFrontInformation.getMinimumValues(paretoFront, problem.getNumberOfObjectives());
            double[] nadirPoint = paretoFrontInformation.getMaximumValues(paretoFront, problem.getNumberOfObjectives());

            AchievementScalarizingFunction asf = new AchievementScalarizingFunction(problem.getNumberOfObjectives());
            try {
                asf = new AchievementScalarizingFunction(null, nadirPoint, idealPoint);
            } catch (CloneNotSupportedException ex) {
                Logger.getLogger(WASFGAStudy2D.class.getName()).log(Level.SEVERE, null, ex);
            }            

            //Common configuration
            HashMap[] parameters = new HashMap[numberOfAlgorithms];
            for (int i = 0; i < numberOfAlgorithms; i++) {
                parameters[i] = new HashMap();
                parameters[i].put("populationSize_", 200);
                parameters[i].put("maxEvaluations_", 120000);
                parameters[i].put("folderForOutputFiles_", experimentBaseDirectory_ + "/data/" + algorithmNameList_[i]);
            } // for               

            //WASFGA configuration
            parameters[0].put("weightsDirectory_", "data");            
            parameters[0].put("normalization_", true);
            parameters[0].put("estimatePoints_", false);
            ReferencePoint rp = new ReferencePoint(referencePoints[problemIndex].toDouble());
            parameters[0].put("referencePoint_", rp);
            parameters[0].put("asf_", asf);
   
            if ((!paretoFrontFile_[problemIndex].equals(""))
                    || (paretoFrontFile_[problemIndex] == null)) {
                for (int i = 0; i < numberOfAlgorithms; i++) {
                    parameters[i].put("paretoFrontFile_", paretoFrontFile_[problemIndex]);
                }

                algorithm[0] = new WASFGA_Settings(problemName).configure(parameters[0]);                 
            } // if                                       
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(WASFGAStudy2D.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(WASFGAStudy2D.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JMException ex) {
            Logger.getLogger(WASFGAStudy2D.class.getName()).log(Level.SEVERE, null, ex);
        }
    } // algorithmSettings

    public static void main(String[] args) throws JMException, IOException {
        WASFGAStudy2D exp = new WASFGAStudy2D();

        exp.experimentName_ = "WASFGAStudy2D";
        exp.algorithmNameList_ = new String[]{"WASFGA"};            

        exp.problemList_ = new String[]{
         "ZDT1",
         "DTLZ1",
         "WFG1"};         

        exp.paretoFrontFile_ = new String[]{
         "ZDT1.pf",
         "DTLZ1.2D.pf",
         "WFG1.2D.pf"};         

        exp.paretoFrontDirectory_ = new String("data/paretoFronts/");
        referencePoints = new ReferencePoint[exp.problemList_.length];
               
        for (int indexOfProblem = 0; indexOfProblem < exp.problemList_.length; indexOfProblem++) {
            referencePoints[indexOfProblem] = new ReferencePoint(ReferencePoint.ReferencePointType.ACHIEVABLE, exp.paretoFrontDirectory_ + exp.paretoFrontFile_[indexOfProblem]);
            //referencePoints[indexOfProblem] = new ReferencePoint("."+exp.experimentName_ + "/data/WASFGA/" + exp.problemList_[indexOfProblem] + "/REFERENCE_POINTS.rl");        	        	
        }        

        exp.indicatorList_ = new String[]{"HVROI"};

        int numberOfAlgorithms = exp.algorithmNameList_.length;

        exp.experimentBaseDirectory_ = "."
                + exp.experimentName_;
        exp.paretoFrontDirectory_ = "data/paretoFronts";

        exp.algorithmSettings_ = new Settings[numberOfAlgorithms];

        exp.independentRuns_ = 1;                    
      
        exp.deleteWithChildren(exp.experimentBaseDirectory_);

        // Run the experiments                
        exp.initExperiment();
        exp.runExperiment(1);                
    } // main

    /**
     * Deletes the given path and, if it is a directory, deletes all its
     * children.
     */
    private boolean deleteWithChildren(String path) {
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
} // WASFGAStudy

