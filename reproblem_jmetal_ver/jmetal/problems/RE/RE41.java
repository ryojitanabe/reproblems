//  RE41.java
//
//  Author:
//       Ryoji Tanabe <rt.ryoji.tanabe@gmail.com>
//
// This is the four objective version of the car side impact design problem
// 
// Reference:
// Himanshu Jain, Kalyanmoy Deb: An Evolutionary Many-Objective Optimization Algorithm Using Reference-Point Based Nondominated Sorting Approach, Part II: Handling Constraints and Extending to an Adaptive Approach. IEEE Trans. Evolutionary Computation 18(4): 602-622 (2014)
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

public class RE41 extends Problem {
  
  // defining the lower and upper limits
    public static final double [] LOWERLIMIT = {0.5, 0.45, 0.5, 0.5, 0.875, 0.4, 0.4};
    public static final double [] UPPERLIMIT = {1.5, 1.35, 1.5, 1.5, 2.625, 1.2, 1.2};
    private int numberOfOriginalObjectives_;
    private int numberOfOriginalConstraints_;
    
 /**
  * Constructor.
  * Creates a default instance of the RE41 problem
  */
  public RE41() {
    numberOfVariables_   = 7;
    numberOfOriginalObjectives_ = 3;
    numberOfOriginalConstraints_ = 10;
    numberOfObjectives_  = 4;
    numberOfConstraints_ = 0;
    problemName_         = "RE41";
	        
    upperLimit_ = new double[numberOfVariables_];
    lowerLimit_ = new double[numberOfVariables_];

    for (int i = 0; i < numberOfVariables_; i++){
      lowerLimit_[i] = LOWERLIMIT[i];
      upperLimit_[i] = UPPERLIMIT[i];
    } // for
	        
    solutionType_ = new RealSolutionType(this) ;
 } // RE41
	
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
    double x5 = x[4];
    double x6 = x[5];
    double x7 = x[6];
    
    // First original objective function
    f[0] = 1.98 + 4.9 * x1 + 6.67 * x2 + 6.98 * x3 + 4.01 * x4 + 1.78 * x5 + 0.00001 * x6 + 2.73 * x7;
    // Second original objective function
    f[1] = 4.72 - 0.5 * x4 - 0.19 * x2 * x3;
    // Third original objective function
    double Vmbp = 10.58 - 0.674 * x1 * x2 - 0.67275 * x2;
    double Vfd = 16.45 - 0.489 * x3 * x7 - 0.843 * x5 * x6;
    f[2] = 0.5 * (Vmbp + Vfd);

    // Constraint functions
    g[0] = 1 -(1.16 - 0.3717 * x2 * x4 - 0.0092928 * x3);
    g[1] = 0.32 -(0.261 - 0.0159 * x1 * x2 - 0.06486 * x1 -  0.019 * x2 * x7 + 0.0144 * x3 * x5 + 0.0154464 * x6);
    g[2] = 0.32 -(0.214 + 0.00817 * x5 - 0.045195 * x1 - 0.0135168 * x1 + 0.03099 * x2 * x6 - 0.018 * x2 * x7  + 0.007176 * x3 + 0.023232 * x3 - 0.00364 * x5 * x6 - 0.018 * x2 * x2);
    g[3] = 0.32 -(0.74 - 0.61 * x2 - 0.031296 * x3 - 0.031872 * x7 + 0.227 * x2 * x2);
    g[4] = 32 -(28.98 + 3.818 * x3 - 4.2 * x1 * x2 + 1.27296 * x6 - 2.68065 * x7);
    g[5] = 32 -(33.86 + 2.95 * x3 - 5.057 * x1 * x2 - 3.795 * x2 - 3.4431 * x7 + 1.45728);
    g[6] =  32 -(46.36 - 9.9 * x2 - 4.4505 * x1);
    g[7] =  4 - f[1];
    g[8] =  9.9 - Vmbp;
    g[9] =  15.7 - Vfd;

    for (int i = 0; i < numberOfOriginalConstraints_; i++ ) {
	if (g[i] < 0.0) g[i] = -g[i];
	else g[i] = 0;
    }

    f[3] = 0;
    for (int i = 0; i < numberOfOriginalConstraints_; i++ ) {
	f[3] += g[i];
    }   

    for (int i = 0; i < numberOfObjectives_; i++) solution.setObjective(i, f[i]);        
  } // evaluate

  /** 
   * Evaluates the constraint overhead of a solution 
   * @param solution The solution
   * @throws JMException 
   */  
  public void evaluateConstraints(Solution solution) throws JMException {
  } // evaluateConstraints   
} // RE41
