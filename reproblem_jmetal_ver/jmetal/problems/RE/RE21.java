//  RE21.java
//
//  Author:
//       Ryoji Tanabe <rt.ryoji.tanabe@gmail.com>
//
// This is a four bar truss design problem.
// 
// Reference:
// F. Y. Cheng and X. S. Li: Generalized center method for multiobjective engineering optimization. Engineering Optimization, 31(5), pp. 641-661 (1999)
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

/**
 * Class representing problem Speed reducer design
 */
public class RE21 extends Problem {
  
    // defining the lower and upper limits
    // public static final double [] LOWERLIMIT = {1, 0.6, 0.09};
    // public static final double [] UPPERLIMIT = {70, 3, 0.5};                          
    private int numberOfOriginalObjectives_;
    private int numberOfOriginalConstraints_;
           
    /**
     * Constructor.
     * Creates a default instance of the RE21 problem.
     */
    public RE21() {
	numberOfVariables_   = 4;
	numberOfOriginalObjectives_ = 2;
	numberOfOriginalConstraints_ = 0;
	numberOfObjectives_  = 2;
	numberOfConstraints_ = 0;
	problemName_         = "RE21";
	        
	upperLimit_ = new double[numberOfVariables_];
	lowerLimit_ = new double[numberOfVariables_];

	double F = 10;
	double sigma = 10;
	double tmpVar = (F / sigma);
	
	for (int i = 0; i < numberOfVariables_; i++) upperLimit_[i] = 3 * tmpVar;

	lowerLimit_[0] = tmpVar;
	lowerLimit_[1] = Math.sqrt(2.0) * tmpVar;
	lowerLimit_[2] = Math.sqrt(2.0) * tmpVar;
	lowerLimit_[3] = tmpVar;
	        
	solutionType_ = new RealSolutionType(this) ;
    } // RE21    
        
    /**
     * Evaluates a solution
     * @param solution The solution to evaluate
     * @throws JMException 
     */
    public void evaluate(Solution solution) throws JMException {         
	double [] x = new double[numberOfVariables_]; 
	double [] f = new double[numberOfObjectives_];
	
	for (int i = 0; i < numberOfVariables_; i++) x[i] = solution.getDecisionVariables()[i].getValue();    
      
	double x1 = x[0];
	double x2 = x[1];
	double x3 = x[2];
	double x4 = x[3];

	double F = 10;
	double sigma = 10;
	double E = 200000;
	double L = 200;

	f[0] = L * ((2 * x1) + Math.sqrt(2.0) * x2 + Math.sqrt(x3) + x4);
	f[1] = ((F * L) / E) * ((2.0 / x1) + (2.0 * Math.sqrt(2.0) / x2) - (2.0 * Math.sqrt(2.0) / x3) + (2.0 / x4));
    
	for (int i = 0; i < numberOfObjectives_; i++) solution.setObjective(i, f[i]);        
    } // evaluate

    /** 
     * Evaluates the constraint overhead of a solution 
     * @param solution The solution
     * @throws JMException 
     */  
    public void evaluateConstraints(Solution solution) throws JMException {
    } // evaluateConstraints
    
} // RE21
