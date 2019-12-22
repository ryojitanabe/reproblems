//  RE22.java
//
//  Author:
//       Ryoji Tanabe <rt.ryoji.tanabe@gmail.com>
//
// This is a two-objective version of the reinforced concrete beam design problem.
// 
// Reference:
// H. M. Amir and T. Hasegawa, "Nonlinear Mixed-Discrete Structural Optimization," J. Struct. Eng., vol. 115, no. 3, pp. 626-646, 1989.
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

public class RE22 extends Problem {
  
    // defining the lower and upper limits
    public static final double [] LOWERLIMIT = {0.2, 0, 0};
    public static final double [] UPPERLIMIT = {15, 20, 40};   
    
    // public static final double [] LOWERLIMIT = {0.2, 28, 5};
    // public static final double [] UPPERLIMIT = {15, 40, 10};                          

    private int numberOfOriginalObjectives_;
    private int numberOfOriginalConstraints_;

    public static final double[] AsFeasibleIntergers = {0.20, 0.31, 0.40, 0.44, 0.60, 0.62, 0.79, 0.80, 0.88, 0.93, 1.0, 1.20, 1.24, 1.32, 1.40, 1.55, 1.58, 1.60, 1.76, 1.80, 1.86, 2.0, 2.17, 2.20, 2.37, 2.40, 2.48, 2.60, 2.64, 2.79, 2.80, 3.0, 3.08, 3,10, 3.16, 3.41, 3.52, 3.60, 3.72, 3.95, 3.96, 4.0, 4.03, 4.20, 4.34, 4.40, 4.65, 4.74, 4.80, 4.84, 5.0, 5.28, 5.40, 5.53, 5.72, 6.0, 6.16, 6.32, 6.60, 7.11, 7.20, 7.80, 7.90, 8.0, 8.40, 8.69, 9.0, 9.48, 10.27, 11.0, 11.06, 11.85, 12.0, 13.0, 14.0, 15.0};
           
    /**
     * Constructor.
     * Creates a default instance of the RE22 problem.
     */
    public RE22() {
	numberOfVariables_   = 3;
	numberOfOriginalObjectives_ = 1;
	numberOfOriginalConstraints_ = 2;
	numberOfObjectives_  = 2;
	numberOfConstraints_ = 0;
	problemName_         = "RE22";
	        
	upperLimit_ = new double[numberOfVariables_];
	lowerLimit_ = new double[numberOfVariables_];

	for (int i = 0; i < numberOfVariables_; i++){
	    lowerLimit_[i] = LOWERLIMIT[i];
	    upperLimit_[i] = UPPERLIMIT[i];
	} // for
	        
	solutionType_ = new RealSolutionType(this) ;
    } // RE22    

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
      
	double x1 = getClosestValue(AsFeasibleIntergers, x[0]);
	double x2 = x[1];       	
	double x3 = x[2];
	
	// First original objective function
	f[0] = (29.4 * x1) + (0.6 * x2 * x3);

	// Original constraint functions 	
	g[0] = (x1 * x3) - 7.735 * ((x1 * x1) / x2) - 180.0;
	g[1] = 4.0 - (x3 / x2);

	for (int i = 0; i < numberOfOriginalConstraints_; i++ ) {
	    if (g[i] < 0.0) g[i] = -g[i];
	    else g[i] = 0;
	}

	f[1] = g[0] + g[1];
	
	for (int i = 0; i < numberOfObjectives_; i++) solution.setObjective(i, f[i]);        
    } // evaluate

    /** 
     * Evaluates the constraint overhead of a solution 
     * @param solution The solution
     * @throws JMException 
     */  
    public void evaluateConstraints(Solution solution) throws JMException {
    } // evaluateConstraints   
} // RE22
