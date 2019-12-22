//  CRE51.java
//
//  Author:
//       Antonio J. Nebro <antonio@lcc.uma.es>
//       Juan J. Durillo <durillo@lcc.uma.es>
//
//  Copyright (c) 2011 Antonio J. Nebro, Juan J. Durillo
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

/**
 * Class representing problem CRE51
 */
public class CRE51 extends Problem {
  
    // defining the lower and upper limits
    public static final double [] LOWERLIMIT = {0.01, 0.01, 0.01};
    public static final double [] UPPERLIMIT = {0.45, 0.10, 0.10};                          
    private int numberOfOriginalObjectives_;
    private int numberOfOriginalConstraints_;
    
    /**
     * Constructor.
     * Creates a default instance of the CRE51 problem.
     * @param solutionType The solution type must "Real" or "BinaryReal".
     */
    public CRE51() {
	numberOfVariables_   = 3 ;
	numberOfOriginalObjectives_ = 5;
	numberOfOriginalConstraints_ = 7;
	numberOfObjectives_  = numberOfOriginalObjectives_;
	numberOfConstraints_ = numberOfOriginalConstraints_;
	problemName_         = "CRE51";
	        
	upperLimit_ = new double[numberOfVariables_];
	lowerLimit_ = new double[numberOfVariables_];
	
	for (int i = 0; i < numberOfVariables_; i++){
	    lowerLimit_[i] = LOWERLIMIT[i];
	    upperLimit_[i] = UPPERLIMIT[i];
	} // for
	
	solutionType_ = new RealSolutionType(this) ;
    } // CRE51
	
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
    
	// First original objective function
	f[0] = 106780.37 * (x[1] + x[2]) + 61704.67 ;
	// Second original objective function
	f[1] = 3000 * x[0] ;
	// Third original objective function
	f[2] = 305700 * 2289 * x[1] / Math.pow(0.06*2289, 0.65) ;
	// Fourth original objective function
	f[3] = 250 * 2289 * Math.exp(-39.75*x[1]+9.9*x[2]+2.74) ;
	// Fifth original objective function
	f[4] = 25 * (1.39 /(x[0]*x[1]) + 4940*x[2] -80) ;
    	
	for (int i = 0; i < numberOfObjectives_; i++) solution.setObjective(i, f[i]);        

	// Constraint functions          
	g[0] = 1 - (0.00139/(x[0]*x[1])+4.94*x[2]-0.08)             ;
	g[1] = 1 - (0.000306/(x[0]*x[1])+1.082*x[2]-0.0986)         ;
	g[2] = 50000 - (12.307/(x[0]*x[1]) + 49408.24*x[2]+4051.02) ;
	g[3] = 16000 - (2.098/(x[0]*x[1])+8046.33*x[2]-696.71)      ;
	g[4] = 10000 - (2.138/(x[0]*x[1])+7883.39*x[2]-705.04)      ;
	g[5] = 2000 - (0.417*x[0]*x[1] + 1721.26*x[2]-136.54)       ;
	g[6] = 550 - (0.164/(x[0]*x[1])+631.13*x[2]-54.48) ;
       
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
} // CRE51
