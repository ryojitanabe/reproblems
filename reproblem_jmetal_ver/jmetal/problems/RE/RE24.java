//  RE24.java
//
//  Author:
//       Ryoji Tanabe <rt.ryoji.tanabe@gmail.com>
//
// This is a two-objective version of the hatch cover design problem.
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

public class RE24 extends Problem {
  
    // defining the lower and upper limits
    public static final double [] LOWERLIMIT = {0.5, 0.5};
    public static final double [] UPPERLIMIT = {4, 50};
    private int numberOfOriginalObjectives_;
    private int numberOfOriginalConstraints_;
    
    /**
     * Constructor.
     * Creates a default instance of the RE4 problem.
     */
    public RE24() {
	numberOfVariables_   = 2;
	numberOfOriginalObjectives_ = 1;
	numberOfOriginalConstraints_ = 4;
	numberOfObjectives_  = 2;
	numberOfConstraints_ = 0;
	problemName_         = "RE24";
	        
	upperLimit_ = new double[numberOfVariables_];
	lowerLimit_ = new double[numberOfVariables_];

	for (int i = 0; i < numberOfVariables_; i++){
	    lowerLimit_[i] = LOWERLIMIT[i];
	    upperLimit_[i] = UPPERLIMIT[i];
	} // for
	        
	solutionType_ = new RealSolutionType(this) ;
    } // RE24    
        
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
	
	// First original objective function
	f[0] = x1 + (120 * x2);

	double E = 700000;
	double sigmaBMax = 700;
	double tauMax = 450;
	double deltaMax = 1.5;
	double sigmaK = (E * x1 * x1) / 100;
	double sigmaB = 4500 / (x1 * x2);
	double tau = 1800 / x2;
	double delta = (56.2 * 10000) / (E * x1 * x2 * x2);
	
	g[0] = 1 - (sigmaB / sigmaBMax);
	g[1] = 1 - (tau / tauMax);
	g[2] = 1 - (delta / deltaMax);
	g[3] = 1 - (sigmaB / sigmaK);

	for (int i = 0; i < numberOfOriginalConstraints_; i++ ) {
	    if (g[i] < 0.0) g[i] = -g[i];
	    else g[i] = 0;
	}

	f[1] = g[0] + g[1] + g[2] + g[3];          

	for (int i = 0; i < numberOfObjectives_; i++) solution.setObjective(i, f[i]);        
    } // evaluate

    /** 
     * Evaluates the constraint overhead of a solution 
     * @param solution The solution
     * @throws JMException 
     */  
    public void evaluateConstraints(Solution solution) throws JMException {
    } // evaluateConstraints   
} // RE24
