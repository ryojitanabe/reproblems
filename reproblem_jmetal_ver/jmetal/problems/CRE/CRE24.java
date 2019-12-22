//  CRE24.java
//
//  Author:
//       Ryoji Tanabe <rt.ryoji.tanabe@gmail.com>
//
// This is a three-objective version of the speed reducer design problem.
// 
// Reference:
//  C. A. C. Coello and G. T. Pulido, "Multiobjective structural optimization using a microgenetic algorithm," Stru. and Multi. Opt., vol. 30, no. 5, pp. 388-403, 2005.
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

package jmetal.problems.CRE;

import jmetal.core.Problem;
import jmetal.core.Solution;
import jmetal.encodings.solutionType.BinaryRealSolutionType;
import jmetal.encodings.solutionType.RealSolutionType;
import jmetal.util.JMException;

public class CRE24 extends Problem {
  
    // defining the lower and upper limits
    public static final double [] LOWERLIMIT = {2.6, 0.7, 17, 7.3, 7.3, 2.9, 5.0};
    public static final double [] UPPERLIMIT = {3.6, 0.8, 28, 8.3, 8.3, 3.9, 5.5};                          
    private int numberOfOriginalObjectives_;
    private int numberOfOriginalConstraints_;

    /**
     * Constructor.
     * Creates a default instance of the CRE24 problem.
     * @param solutionType The solution type must "Real" or "BinaryReal".
     */
    public CRE24() {
	numberOfVariables_   = 7;
	numberOfOriginalObjectives_ = 2;
	numberOfOriginalConstraints_ = 11;
	numberOfObjectives_  = numberOfOriginalObjectives_;
	numberOfConstraints_ = numberOfOriginalConstraints_;
	problemName_         = "CRE24";
	        
	upperLimit_ = new double[numberOfVariables_];
	lowerLimit_ = new double[numberOfVariables_];

	for (int i = 0; i < numberOfVariables_; i++){
	    lowerLimit_[i] = LOWERLIMIT[i];
	    upperLimit_[i] = UPPERLIMIT[i];
	} // for
	        
	solutionType_ = new RealSolutionType(this) ;
    } // CRE24
	
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

	double x1 = x[0];
	double x2 = x[1];
	double x3 = Math.rint(x[2]);
	double x4 = x[3];
	double x5 = x[4];
	double x6 = x[5];
	double x7 = x[6];
	
	// First original objective function (weight)
	f[0] = 0.7854 * x1 * (x2 * x2) * (((10.0 * x3 * x3) / 3.0) + (14.933 * x3) - 43.0934)
	    - 1.508 * x1 * (x6 * x6 + x7 * x7)
	    + 7.477 * (x6 * x6 * x6 + x7 * x7 * x7)
	    + 0.7854 * (x4 * x6 * x6 + x5 * x7 * x7);
    
	// Second original objective function (stress)
	double tmpVar = Math.pow((745.0 * x4) / (x2 * x3), 2.0)  + 1.69 * 1e7;
	f[1] =  Math.sqrt(tmpVar) / (0.1 * x6 * x6 * x6);

	// Constraint functions 	
	g[0] = -(1.0 / (x1 * x2 * x2 * x3)) + 1.0 / 27.0;
	g[1] = -(1.0 / (x1 * x2 * x2 * x3 * x3)) + 1.0 / 397.5;
	g[2] = -(x4 * x4 * x4) / (x2 * x3 * x6 * x6 * x6 * x6) + 1.0 / 1.93;
	g[3] = -(x5 * x5 * x5) / (x2 * x3 * x7 * x7 * x7 * x7) + 1.0 / 1.93;
	g[4] = -(x2 * x3) + 40.0;
	g[5] = -(x1 / x2) + 12.0;
	g[6] = -5.0 + (x1 / x2);
	g[7] = -1.9 + x4 - 1.5 * x6;
	g[8] = -1.9 + x5 - 1.1 * x7;
	g[9] =  -f[1] + 1300.0;
	tmpVar = Math.pow((745.0 * x5) / (x2 * x3), 2.0) + 1.575 * 1e8;
	g[10] = -Math.sqrt(tmpVar) / (0.1 * x7 * x7 * x7) + 1100.0;

	for (int i = 0; i < numberOfObjectives_; i++) solution.setObjective(i, f[i]);        

	for (int i = 0; i < numberOfOriginalConstraints_; i++ ) {
	    if (g[i] < 0.0) g[i] = -g[i];
	    else g[i] = 0;
	}

	double total = 0.0;
	for (int i = 0; i < numberOfConstraints_; i++) {
	    if (g[i] < 0.0){
		total += g[i];
	    } // int
	} // for
        
	solution.setOverallConstraintViolation(total);      	       
    } // evaluate

    /** 
     * Evaluates the constraint overhead of a solution 
     * @param solution The solution
     * @throws JMException 
     */  
    public void evaluateConstraints(Solution solution) throws JMException {
    } // evaluateConstraints   
} // CRE24
