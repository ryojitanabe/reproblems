//  CRE23.java
//
//  Author:
//       Ryoji Tanabe <rt.ryoji.tanabe@gmail.com>
//
// This is a three-objective version of the disc brake design problem
// 
// Reference:
//  T. Ray and K. M. Liew, "A swarm metaphor for multiobjective design optimization," Eng. opt., vol. 34, no. 2, pp. 141–153, 2002.
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

public class CRE23 extends Problem {
  
    // defining the lower and upper limits
    //      public static final double [] LOWERLIMIT = {55, 75, 1000, 2};
    public static final double [] LOWERLIMIT = {55, 75, 1000, 11};
    public static final double [] UPPERLIMIT = {80, 110, 3000, 20};                          
    private int numberOfOriginalObjectives_;
    private int numberOfOriginalConstraints_;
    
    /**
     * Constructor.
     * Creates a default instance of the CRE23 problem.
     */
    public CRE23() {
	numberOfVariables_   = 4;
	numberOfOriginalObjectives_ = 2;
	numberOfOriginalConstraints_ = 4;
	numberOfObjectives_  = numberOfOriginalObjectives_;
	numberOfConstraints_ = numberOfOriginalConstraints_;
	problemName_         = "CRE23";
	        
	upperLimit_ = new double[numberOfVariables_];
	lowerLimit_ = new double[numberOfVariables_];

	for (int i = 0; i < numberOfVariables_; i++){
	    lowerLimit_[i] = LOWERLIMIT[i];
	    upperLimit_[i] = UPPERLIMIT[i];
	} // for
	        
	solutionType_ = new RealSolutionType(this) ;
    } // CRE23
	
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
	double x3 = x[2];
	double x4 = x[3];
	
	// First original objective function
	f[0] = 4.9 * 1e-5 * (x2 * x2 - x1 * x1) * (x4 - 1.0);
	// Second original objective function
	f[1] = ((9.82 * 1e6) * (x2 * x2 - x1 * x1)) / (x3 * x4 * (x2 * x2 * x2 - x1 * x1 * x1));

	for (int i = 0; i < numberOfObjectives_; i++) solution.setObjective(i, f[i]);        
	
	// Reformulated objective functions
	g[0] = (x2 - x1) - 20.0;
	g[1] = 0.4 - (x3 / (3.14 * (x2 * x2 - x1 * x1)));
	g[2] = 1.0 - (2.22 * 1e-3 * x3 * (x2 * x2 * x2 - x1 * x1 * x1)) / Math.pow((x2 * x2 - x1 * x1), 2);
	g[3] = (2.66 * 1e-2 * x3 * x4 * (x2 * x2 * x2 - x1 * x1 * x1)) / (x2 * x2 - x1 * x1) - 900.0;

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
} // CRE23
