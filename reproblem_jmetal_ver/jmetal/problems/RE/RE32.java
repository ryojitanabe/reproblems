//  RE32.java
//
//  Author:
//       Ryoji Tanabe <rt.ryoji.tanabe@gmail.com>
//
// This is a three-objective version of the disc brake design prolem
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

package jmetal.problems.RE;

import jmetal.core.Problem;
import jmetal.core.Solution;
import jmetal.encodings.solutionType.BinaryRealSolutionType;
import jmetal.encodings.solutionType.RealSolutionType;
import jmetal.util.JMException;

public class RE32 extends Problem {
    // Lower and upper limits
    // public static final double [] LOWERLIMIT = {0.125, 0.00001, 0.1, 0.00001};
    // public static final double [] UPPERLIMIT = {30.0, 5.0, 30.0, 10.0}; 
    public static final double [] LOWERLIMIT = {0.125, 0.1, 0.1, 0.125};
    public static final double [] UPPERLIMIT = {5.0, 10.0, 10.0, 5.0};                          
    private int numberOfOriginalObjectives_;
    private int numberOfOriginalConstraints_;

 /**
  * Constructor.
  * Creates a default instance of the RE2 problem.
  */
  public RE32() {
    numberOfVariables_   = 4;
    numberOfOriginalObjectives_ = 2;
    numberOfOriginalConstraints_ = 4;
    numberOfObjectives_  = 3;
    numberOfConstraints_ = 0;
    problemName_         = "RE32";
	        
    upperLimit_ = new double[numberOfVariables_];
    lowerLimit_ = new double[numberOfVariables_];
    
    for (int i = 0; i < numberOfVariables_; i++){
	lowerLimit_[i] = LOWERLIMIT[i];
	upperLimit_[i] = UPPERLIMIT[i];
    } // for
    
    solutionType_ = new RealSolutionType(this) ;
 } // RE32
	
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

    double P = 6000;
    double L = 14;
    double E = 30 * 1e6;

    //    double deltaMax = 0.25;
    double G = 12 * 1e6;
    double tauMax = 13600;
    double sigmaMax = 30000;
    
    // First original objective function
    f[0] = (1.10471 * x1 * x1 * x2) + (0.04811 * x3 * x4) * (14.0 + x2);
    // Second original objective function
    f[1] = (4 * P * L * L * L) / (E * x4 * x3 * x3 * x3);

    // constraint functions
    double M = P * (L + (x2 / 2));
    double tmpVar = ((x2 * x2) / 4.0) + Math.pow((x1 + x3) / 2.0, 2);
    double R = Math.sqrt(tmpVar);
    tmpVar = ((x2 * x2) / 12.0) + Math.pow((x1 + x3) / 2.0, 2);
    double J = 2 * Math.sqrt(2) * x1 * x2 * tmpVar;

    double tauDashDash = (M * R) / J;    
    double tauDash = P / (Math.sqrt(2) * x1 * x2);
    tmpVar = tauDash * tauDash + ((2 * tauDash * tauDashDash * x2) / (2 * R)) + (tauDashDash * tauDashDash);
    double tau = Math.sqrt(tmpVar);
    double sigma = (6 * P * L) / (x4 * x3 * x3);
    tmpVar = 4.013 * E * Math.sqrt((x3 * x3 * x4 * x4 * x4 * x4 * x4 * x4) / 36.0) / (L * L);
    double tmpVar2 = (x3 / (2 * L)) * Math.sqrt(E / (4 * G));
    double PC = tmpVar * (1 - tmpVar2);

    g[0] = tauMax - tau;
    g[1] = sigmaMax - sigma;
    g[2] = x4 - x1;
    g[3] = PC - P;

    for (int i = 0; i < numberOfOriginalConstraints_; i++ ) {
	if (g[i] < 0.0) g[i] = -g[i];
	else g[i] = 0;
    }
	
    f[2] = g[0] + g[1] + g[2] + g[3];
	    
    for (int i = 0; i < numberOfObjectives_; i++) solution.setObjective(i, f[i]);        
  } // evaluate

    /** 
     * Evaluates the constraint overhead of a solution 
     * @param solution The solution
     * @throws JMException 
     */  
    public void evaluateConstraints(Solution solution) throws JMException {
    } // evaluateConstraints   
} // RE32
