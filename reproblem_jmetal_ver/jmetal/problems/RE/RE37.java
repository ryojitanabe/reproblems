//  RE37.java
//
//  Author:
//       Ryoji Tanabe <rt.ryoji.tanabe@gmail.com>
//
// This is the rocket injector design problem.
// 
// Reference:
// R. Vaidyanathan, K. Tucker, N. Papila, W. Shyy, CFD-Based Design Optimization For Single Element Rocket Injector, in: AIAA Aerospace Sciences Meeting, 2003, pp. 1-21
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

public class RE37 extends Problem {    
 /**
  * Constructor.
  * Creates a default instance of the RE37 problem.
  */
  public RE37() {
    numberOfVariables_   = 4;
    numberOfObjectives_  = 3;
    numberOfConstraints_ = 0;
    problemName_         = "RE37";
	        
    upperLimit_ = new double[numberOfVariables_];
    lowerLimit_ = new double[numberOfVariables_];

    for (int i = 0; i < numberOfVariables_; i++){
	lowerLimit_[i] = 0;
	upperLimit_[i] = 1;
    } // for
	        
    solutionType_ = new RealSolutionType(this) ;
 } // RE37
	
  /**
   * Evaluates a solution
   * @param solution The solution to evaluate
   * @throws JMException 
   */
  public void evaluate(Solution solution) throws JMException {         
    double [] f = new double[numberOfObjectives_];
    
    double xAlpha = solution.getDecisionVariables()[0].getValue();
    double xHA = solution.getDecisionVariables()[1].getValue();
    double xOA = solution.getDecisionVariables()[2].getValue();
    double xOPTT = solution.getDecisionVariables()[3].getValue();
   
    // f1 (TF_max)
    f[0] = 0.692 + (0.477 * xAlpha) - (0.687 * xHA) - (0.080 * xOA) - (0.0650 * xOPTT) - (0.167 * xAlpha * xAlpha) - (0.0129 * xHA * xAlpha) + (0.0796 * xHA * xHA) - (0.0634 * xOA * xAlpha) - (0.0257 * xOA * xHA) + (0.0877 * xOA * xOA) - (0.0521 * xOPTT * xAlpha) + (0.00156 * xOPTT * xHA) + (0.00198 * xOPTT * xOA) + (0.0184 * xOPTT * xOPTT);

    // f2 (X_cc)
    f[1] = 0.153 - (0.322 * xAlpha) + (0.396 * xHA) + (0.424 * xOA) + (0.0226 * xOPTT) + (0.175 * xAlpha * xAlpha) + (0.0185 * xHA * xAlpha) - (0.0701 * xHA * xHA) - (0.251 * xOA * xAlpha) + (0.179 * xOA * xHA) + (0.0150 * xOA * xOA) + (0.0134 * xOPTT * xAlpha) + (0.0296 * xOPTT * xHA) + (0.0752 * xOPTT * xOA) + (0.0192 * xOPTT * xOPTT);
   
    // f3 (TT_max)
    f[2] = 0.370 - (0.205 * xAlpha) + (0.0307 * xHA) + (0.108 * xOA) + (1.019 * xOPTT) - (0.135 * xAlpha * xAlpha) + (0.0141 * xHA * xAlpha) + (0.0998 * xHA * xHA) + (0.208 * xOA * xAlpha) - (0.0301 * xOA * xHA) - (0.226 * xOA * xOA) + (0.353 * xOPTT * xAlpha) - (0.0497 * xOPTT * xOA) - (0.423 * xOPTT * xOPTT) + (0.202 * xHA * xAlpha * xAlpha) - (0.281 * xOA * xAlpha * xAlpha) - (0.342 * xHA * xHA * xAlpha) - (0.245 * xHA * xHA * xOA) + (0.281 * xOA * xOA * xHA) - (0.184 * xOPTT * xOPTT * xAlpha) - (0.281 * xHA * xAlpha * xOA);	
	
    for (int i = 0; i < numberOfObjectives_; i++) solution.setObjective(i, f[i]);        
  } // evaluate
} // RE37
