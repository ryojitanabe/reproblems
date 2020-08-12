//  RE25.java
//
//  Author:
//       Ryoji Tanabe <rt.ryoji.tanabe@gmail.com>
//
// This is a two-objective version of the coil compression spring design problem.
// 
// Reference:
// J. Lampinen and I. Zelinka, "Mixed integer-discrete-continuous optimization by differential evolution, part 2: a practical example," in International Conference on Soft Computing, 1999, pp. 77-81.
//
//  Copyright (c) 2018 Ryoji Tanabe
//
//  This program is free software: you can redistribute it and/or modify
//  it under the terms of the GNU Lesser General Public License as published by
//  the Free Software Foundation, either version 3 of the License, or
//  (at your option) any later version.
//
//  This program is distributed in the hope that it will be useful,
//  but WITHOUT ANY WARRANTY; without even the implied warranty of
//  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//  GNU Lesser General Public License for more details.
// 
//  You should have received a copy of the GNU Lesser General Public License
//  along with this program.  If not, see <http://www.gnu.org/licenses/>.

package jmetal.problems.RE;

import jmetal.core.Problem;
import jmetal.core.Solution;
import jmetal.encodings.solutionType.BinaryRealSolutionType;
import jmetal.encodings.solutionType.RealSolutionType;
import jmetal.util.JMException;

public class RE25 extends Problem {
  
    // defining the lower and upper limits
    public static final double [] LOWERLIMIT = {1, 0.6, 0.09};
    public static final double [] UPPERLIMIT = {70, 3, 0.5};                          
    private int numberOfOriginalObjectives_;
    private int numberOfOriginalConstraints_;

    public static final double[] diameterFeasibleIntergers = {0.009, 0.0095, 0.0104, 0.0118, 0.0128, 0.0132, 0.014, 0.015, 0.0162, 0.0173, 0.018, 0.02, 0.023, 0.025, 0.028, 0.032, 0.035, 0.041, 0.047, 0.054, 0.063, 0.072, 0.08, 0.092, 0.105, 0.12, 0.135, 0.148, 0.162, 0.177, 0.192, 0.207, 0.225, 0.244, 0.263, 0.283, 0.307, 0.331, 0.362, 0.394, 0.4375, 0.5};

    /**
     * Constructor.
     * Creates a default instance of the RE25 problem.
     * @param solutionType The solution type must "Real" or "BinaryReal".
     */
    public RE25() {
	numberOfVariables_   = 3;
	numberOfOriginalObjectives_ = 1;
	numberOfOriginalConstraints_ = 6; // Since two out of the original 8 constraint functions can be expressed by the bound constraints, they are removed
	numberOfObjectives_  = 2;
	numberOfConstraints_ = 0;
	problemName_         = "RE25";
	        
	upperLimit_ = new double[numberOfVariables_];
	lowerLimit_ = new double[numberOfVariables_];

	for (int i = 0; i < numberOfVariables_; i++){
	    lowerLimit_[i] = LOWERLIMIT[i];
	    upperLimit_[i] = UPPERLIMIT[i];
	} // for
	        
	solutionType_ = new RealSolutionType(this) ;
    } // RE25    

    public double getClosestValue(double[] targetArray, double compValue) {
	double closestValue = targetArray[0];
	double minDiffValue = Math.abs(targetArray[0] - compValue);
	double tmpDiffValue = 0;
	
	for (int i = 1; i < targetArray.length; i++) {
	    tmpDiffValue = Math.abs(targetArray[i] - compValue);
	    if (tmpDiffValue < minDiffValue) {
		minDiffValue = tmpDiffValue;
		closestValue = targetArray[i];
	    }
	}

	return closestValue;		
    }
        
    /**
     * Evaluates a solution
     * @param solution The solution to evaluate
     * @throws JMException 
     */
    public void evaluate(Solution solution) throws JMException {         
	double [] x = new double[numberOfVariables_]; 
	double [] f = new double[numberOfObjectives_];
	double [] g = new double[numberOfOriginalConstraints_];
	
	for (int i = 0; i < numberOfVariables_; i++) x[i] = solution.getDecisionVariables()[i].getValue();    
     
	double x1 = Math.rint(x[0]);
	double x2 = x[1];       	
	double x3 = getClosestValue(diameterFeasibleIntergers, x[2]);
	
	// first original objective function
	f[0] = (Math.PI * Math.PI * x2 * x3 * x3 * (x1 + 2)) / 4.0;
	    
	// constraint functions
	double Cf = ((4.0 * (x2 / x3) - 1) / (4.0 * (x2 / x3) - 4)) + (0.615 * x3 / x2);
	double Fmax = 1000.0;
	double S =189000.0;	    
	double G = 11.5 * 1e+6;
	double K  = (G * x3 * x3 * x3 * x3) / (8 * x1 * x2 * x2 * x2);
	double lmax = 14.0;
	double lf = (Fmax / K) + 1.05 *  (x1 + 2) * x3;
	double dmin = 0.2;
	double Dmax = 3;
	double Fp = 300.0;
	double sigmaP = Fp / K;
	double sigmaPM = 6;
	double sigmaW = 1.25;

	g[0] = -((8 * Cf * Fmax * x2) / (Math.PI * x3 * x3 * x3)) + S;
	g[1] = -lf + lmax;
	g[2] = -3 + (x2 / x3);
	g[3] = -sigmaP + sigmaPM;
	g[4] = -sigmaP - ((Fmax - Fp) / K) - 1.05 * (x1 + 2) * x3 + lf;
	g[5] = sigmaW- ((Fmax - Fp) / K);

	for (int i = 0; i < numberOfOriginalConstraints_; i++ ) {
	    if (g[i] < 0.0) g[i] = -g[i];
	    else g[i] = 0;
	}

	f[1] = g[0] + g[1] + g[2] + g[3] + g[4] + g[5];
    
	for (int i = 0; i < numberOfObjectives_; i++) solution.setObjective(i, f[i]);        
    } // evaluate

    /** 
     * Evaluates the constraint overhead of a solution 
     * @param solution The solution
     * @throws JMException 
     */  
    public void evaluateConstraints(Solution solution) throws JMException {
    } // evaluateConstraints   
} // RE25
