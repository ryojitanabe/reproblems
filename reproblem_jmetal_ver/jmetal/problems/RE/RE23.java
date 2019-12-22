//  RE23.java
//
//  Author:
//       Ryoji Tanabe <rt.ryoji.tanabe@gmail.com>
//
// This is a two-objective version of the pressure vessel design problem.
// 
// Reference:
// B. K. Kannan and S. N. Kramer, "An Augmented Lagrange Multiplier Based Method for Mixed Integer Discrete Continuous Optimization and Its Applications to Mechanical Design, Journal of Mechanical Design, vol. 116, no. 2, pp. 405-411, 1994.
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

public class RE23 extends Problem {
  
    // defining the lower and upper limits
    public static final double [] LOWERLIMIT = {1, 1, 10, 10};
    public static final double [] UPPERLIMIT = {100, 100, 200, 240};                          
    private int numberOfOriginalObjectives_;
    private int numberOfOriginalConstraints_;
    
    /**
     * Constructor.
     * Creates a default instance of the RE23 problem.
     */
    public RE23() {
	numberOfVariables_   = 4;
	numberOfOriginalObjectives_ = 1;
	numberOfOriginalConstraints_ = 3;
	numberOfObjectives_  = 2;
	numberOfConstraints_ = 0;
	problemName_         = "RE23";
	        
	upperLimit_ = new double[numberOfVariables_];
	lowerLimit_ = new double[numberOfVariables_];

	for (int i = 0; i < numberOfVariables_; i++){
	    lowerLimit_[i] = LOWERLIMIT[i];
	    upperLimit_[i] = UPPERLIMIT[i];
	} // for
	        
	solutionType_ = new RealSolutionType(this) ;
    } // RE23    
    
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

	double x1 = 0.0625 * Math.rint(x[0]);
	double x2 = 0.0625 * Math.rint(x[1]);
	double x3 = x[2];
	double x4 = x[3];		 
	
	// First original objective function
	f[0] = (0.6224 * x1 * x3* x4) + (1.7781 * x2 * x3 * x3) + (3.1661 * x1 * x1 * x4) + (19.84 * x1 * x1 * x3);

	g[0] = x1 - (0.0193 * x3);
	g[1] = x2 - (0.00954 * x3);
	g[2] = (Math.PI * x3 * x3 * x4) + ((4.0/3.0) * (Math.PI * x3 * x3 * x3)) - 1296000;

	for (int i = 0; i < numberOfOriginalConstraints_; i++ ) {
	    if (g[i] < 0.0) g[i] = -g[i];
	    else g[i] = 0;
	}

	f[1] = g[0] + g[1] + g[2];

	for (int i = 0; i < numberOfObjectives_; i++) solution.setObjective(i, f[i]);        
    } // evaluate

    /** 
     * Evaluates the constraint overhead of a solution 
     * @param solution The solution
     * @throws JMException 
     */  
    public void evaluateConstraints(Solution solution) throws JMException {
    } // evaluateConstraints   
} // RE23
