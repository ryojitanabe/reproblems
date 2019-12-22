//  RE34.java
//
//  Author:
//       Ryoji Tanabe <rt.ryoji.tanabe@gmail.com>
//
// This is the vehicle crashworthiness design problem.
// 
// Reference:
// X. Liao, Q. Li, X. Yang, W. Zhang, and W. Li, "Multiobjective optimization for crash safety design of vehicles using stepwise regression model," Struct. Multidiscipl. Optim., vol. 35, no. 6, pp. 561-569, 2008.
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
import jmetal.core.Variable;
import jmetal.encodings.solutionType.BinaryRealSolutionType;
import jmetal.encodings.solutionType.RealSolutionType;
import jmetal.util.JMException;

/**
 * Class representing problem RE34
 */
public class RE34 extends Problem {  
     
  /** 
   * Constructor.
   * Creates a new instance of the RE34 problem.
   */
    public RE34() {
	numberOfVariables_   = 5;
	numberOfObjectives_  = 3;
	numberOfConstraints_ = 0  ;
	problemName_         = "RE34"                    ;
        
	upperLimit_ = new double[numberOfVariables_] ;
	lowerLimit_ = new double[numberOfVariables_] ;
       
	for (int i = 0; i < numberOfVariables_; i++) {
	    lowerLimit_[i] = 1.0;
	    upperLimit_[i] = 3.0;
	} // for
        
	solutionType_ = new RealSolutionType(this) ;
    } // RE34
    
    /** 
     * Evaluates a solution 
     * @param solution The solution to evaluate
     * @throws JMException 
     */
    public void evaluate(Solution solution) throws JMException {
	Variable[] decisionVariables  = solution.getDecisionVariables();
	double x1, x2, x3, x4, x5;
	double tmp_obj_value;

	x1 = decisionVariables[0].getValue();
	x2 = decisionVariables[1].getValue();
	x3 = decisionVariables[2].getValue();
	x4 = decisionVariables[3].getValue();
	x5 = decisionVariables[4].getValue();

	// f1
	tmp_obj_value = 1640.2823 + (2.3573285 * x1) + (2.3220035 * x2) + (4.5688768 * x3) + (7.7213633 * x4) + (4.4559504 * x5);
	solution.setObjective(0, tmp_obj_value);	

	// f2
	tmp_obj_value = 6.5856 + (1.15 * x1) - (1.0427 * x2) + (0.9738 * x3) + (0.8364 * x4) - (0.3695 * x1 * x4) + (0.0861 * x1 * x5) + (0.3628 * x2 * x4)  - (0.1106 * x1 * x1)  - (0.3437 * x3 * x3) + (0.1764 * x4 * x4);
	solution.setObjective(1, tmp_obj_value);	

	// f3
	tmp_obj_value = -0.0551 + (0.0181 * x1) + (0.1024 * x2) + (0.0421 * x3) - (0.0073 * x1 * x2) + (0.024 * x2 * x3) - (0.0118 * x2 * x4) - (0.0204 * x3 * x4) - (0.008 * x3 * x5) - (0.0241 * x2 * x2) + (0.0109 * x4 * x4);

	solution.setObjective(2, tmp_obj_value);	
    } // evaluate
} // RE34
