//  RE36.java
//
//  Author:
//       Ryoji Tanabe <rt.ryoji.tanabe@gmail.com>
//
// This is the vehicle crashworthiness design problem.
// 
// Reference:
// Kalyanmoy Deb and Aravind Srinivasan, "Innovization: Innovative Design Principles Through Optimization," KanGAL Report Number 2005007, Indian Institute of Technology Kanpur (2005).
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

public class RE36 extends Problem {
  
    // defining the lower and upper limits
    public static final double [] LOWERLIMIT = {12, 12, 12, 12};
    public static final double [] UPPERLIMIT = {60, 60, 60, 60};
    private int numberOfOriginalObjectives_;
    private int numberOfOriginalConstraints_;
    
 /**
  * Constructor.
  * Creates a default instance of the RE36 problem.
  */
  public RE36() {
    numberOfVariables_   = 4;
    numberOfOriginalObjectives_ = 2;
    numberOfOriginalConstraints_ = 1;
    numberOfObjectives_  = 3;
    numberOfConstraints_ = 0;
    problemName_         = "RE36";
	        
    upperLimit_ = new double[numberOfVariables_];
    lowerLimit_ = new double[numberOfVariables_];

    for (int i = 0; i < numberOfVariables_; i++){
      lowerLimit_[i] = LOWERLIMIT[i];
      upperLimit_[i] = UPPERLIMIT[i];
    } // for
	        
    solutionType_ = new RealSolutionType(this) ;
 } // RE36
	
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

    // all the four variables must be inverger values
    double x1 = Math.rint(x[0]);
    double x2 = Math.rint(x[1]);
    double x3 = Math.rint(x[2]);
    double x4 = Math.rint(x[3]);
   
    // First original objective function
    f[0] = Math.abs(6.931 - ((x3 / x1) * (x4 / x2)));
    // Second original objective function (the maximum value among the four variables)
    double maxValue = x1;
    if (x2 > maxValue) maxValue = x2;
    if (x3 > maxValue) maxValue = x3;
    if (x4 > maxValue) maxValue = x4;
    f[1] = maxValue;

    g[0] = 0.5 - (f[0] / 6.931);

    for (int i = 0; i < numberOfOriginalConstraints_; i++ ) {
	if (g[i] < 0.0) g[i] = -g[i];
	else g[i] = 0;
    }
    
    f[2] = g[0];

    for (int i = 0; i < numberOfObjectives_; i++) solution.setObjective(i, f[i]);        
  } // evaluate

  /** 
   * Evaluates the constraint overhead of a solution 
   * @param solution The solution
   * @throws JMException 
   */  
  public void evaluateConstraints(Solution solution) throws JMException {

  } // evaluateConstraints   
} // RE36
