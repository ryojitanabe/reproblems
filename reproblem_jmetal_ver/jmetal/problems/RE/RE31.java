//  RE31.java
//
//  Author:
//       Ryoji Tanabe <rt.ryoji.tanabe@gmail.com>
//
// This is a three-objective version of the two bar truss design problem
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

package jmetal.problems.RE;

import jmetal.core.Problem;
import jmetal.core.Solution;
import jmetal.encodings.solutionType.BinaryRealSolutionType;
import jmetal.encodings.solutionType.RealSolutionType;
import jmetal.util.JMException;

public class RE31 extends Problem {
  
    // defining the lower and upper limits
    public static final double [] LOWERLIMIT = {0.00001, 0.00001, 1.0};
    public static final double [] UPPERLIMIT = {100.0, 100.0, 3.0};                          
    private int numberOfOriginalObjectives_;
    private int numberOfOriginalConstraints_;
    
 /**
  * Constructor.
  * Creates a default instance of the RE31 problem.
  */
  public RE31() {
    numberOfVariables_   = 3;
    numberOfOriginalObjectives_ = 2;
    numberOfOriginalConstraints_ = 3;
    numberOfObjectives_  = 3;
    numberOfConstraints_ = 0;
    problemName_         = "RE31";
	        
    upperLimit_ = new double[numberOfVariables_];
    lowerLimit_ = new double[numberOfVariables_];

    for (int i = 0; i < numberOfVariables_; i++){
      lowerLimit_[i] = LOWERLIMIT[i];
      upperLimit_[i] = UPPERLIMIT[i];
    } // for
	        
    solutionType_ = new RealSolutionType(this) ;
 } // RE31
	
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
    
    // First original objective function
    f[0] = x1 * Math.sqrt(16.0 + (x3 * x3)) + x2 * Math.sqrt(1.0 + x3 * x3);
    // Second original objective function
    f[1] = (20.0 * Math.sqrt(16.0 + (x3 * x3))) / (x1 * x3);

    // Constraint functions 
    g[0] = 0.1 - f[0];
    g[1] = 100000.0 - f[1];
    g[2] = 100000 - ((80.0 * Math.sqrt(1.0 + x3 * x3)) / (x3 * x2));

    for (int i = 0; i < numberOfOriginalConstraints_; i++ ) {
	if (g[i] < 0.0) g[i] = -g[i];
	else g[i] = 0;
    }
	
    f[2] = g[0] + g[1] + g[2];
	
    for (int i = 0; i < numberOfObjectives_; i++) solution.setObjective(i, f[i]);        
  } // evaluate

  /** 
   * Evaluates the constraint overhead of a solution 
   * @param solution The solution
   * @throws JMException 
   */  
  public void evaluateConstraints(Solution solution) throws JMException {

  } // evaluateConstraints   
} // RE31
