// Note this is a revised version of the file downloaded from Dr. Miqing Li's web site (http://www.cs.bham.ac.uk/~limx/)
// RE91.java
//
//  Author:
//       Yi Xiang <antonio@lcc.uma.es>

/**
 * This is a real world problem presented in the following paper:
 * Multiobjective optimization for crash safety design of vehicles using stepwise regression model
   Xingtao Liao & Qing Li & Xujing Yang &
    Weigang Zhang & Wei Li
 */

package jmetal.problems.RE;

import java.util.Random;

import jmetal.core.Problem;
import jmetal.core.Solution;
import jmetal.encodings.solutionType.BinaryRealSolutionType;
import jmetal.encodings.solutionType.RealSolutionType;
import jmetal.util.JMException;

public class RE91 extends Problem {                     
    public Random rdm = new Random();

    /**
     * Constructor.
     * Creates a default instance of the CrashWorthinessDesign problem.
     */
    public RE91() {
	numberOfVariables_   = 7 ;
	numberOfObjectives_  = 9 ;
	numberOfConstraints_ = 0 ;
	problemName_         = "RE91";
	        
	upperLimit_ = new double[numberOfVariables_];
	lowerLimit_ = new double[numberOfVariables_];

	lowerLimit_[0] = 0.5;
	upperLimit_[0] = 1.5;
    
	lowerLimit_[1] = 0.45;
	upperLimit_[1] = 1.35;
	        
	lowerLimit_[2] = 0.5;
	upperLimit_[2] = 1.5;
    
	lowerLimit_[3] = 0.5;
	upperLimit_[3] = 1.5;
    
	lowerLimit_[4] = 0.875;
	upperLimit_[4] = 2.625;
    
	lowerLimit_[5] = 0.4;
	upperLimit_[5] = 1.2;
    
	lowerLimit_[6] = 0.4;
	upperLimit_[6] = 1.2;
    
	solutionType_ = new RealSolutionType(this) ;
    } // CrashWorthinessDesign
	
    /**
     * Evaluates a solution
     * @param solution The solution to evaluate
     * @throws JMException 
     */
    public void evaluate(Solution solution) throws JMException {         
	double [] x = new double[11] ; // 7 decision variables
	double [] f = new double[9] ; // 9 functions
   
	x[0] = solution.getDecisionVariables()[0].getValue();
	x[1] = solution.getDecisionVariables()[1].getValue();
	x[2] = solution.getDecisionVariables()[2].getValue();
	x[3] = solution.getDecisionVariables()[3].getValue();
	x[4] = solution.getDecisionVariables()[4].getValue();
	x[5] = solution.getDecisionVariables()[5].getValue();
	x[6] = solution.getDecisionVariables()[6].getValue();
    
	x[7] = 0.006 * (rdm.nextGaussian()) + 0.345;
	x[8] = 0.006 * (rdm.nextGaussian()) + 0.192;
	x[9] = 10 * (rdm.nextGaussian()) + 0.0;
	x[10] = 10 * (rdm.nextGaussian()) + 0.0;
   
	// First function
	f[0] = 1.98 + 4.9 * x[0] + 6.67 * x[1] + 6.98 * x[2]
	    +  4.01 * x[3] +  1.75 * x[4] +  0.00001 * x[5]  +  2.73 * x[6];
	// Second function
	f[1] = Math.max(0.0, (1.16 - 0.3717* x[1] * x[3] - 0.00931 * x[1] * x[9] - 0.484 * x[2] * x[8]
			      + 0.01343 * x[5] * x[9] )/1.0) ;
	// Third function
	f[2] = Math.max(0.0, (0.261 - 0.0159 * x[0] * x[1]
			      - 0.188 * x[0] * x[7] - 0.019 * x[1] * x[6]
			      + 0.0144 * x[2] * x[4] + 0.87570001 * x[4] * x[9]
			      + 0.08045 * x[5] * x[8] + 0.00139 * x[7] * x[10]
			      + 0.00001575 * x[9] * x[10])/0.32);
  
	f[3] = Math.max(0.0, (0.214 + 0.00817 * x[4] 
			      - 0.131 * x[0] * x[7] - 0.0704 * x[0] * x[8]
			      + 0.03099 * x[1] * x[5] - 0.018 * x[1] * x[6]
			      + 0.0208 * x[2] * x[7] + 0.121 * x[2] * x[8]
			      - 0.00364 * x[4] * x[5] + 0.0007715 * x[4] * x[9]
			      - 0.0005354 * x[5] * x[9] + 0.00121 * x[7] * x[10]
			      + 0.00184 * x[8] * x[9] - 0.018 * x[1] * x[1])/0.32);
    
	f[4] =  Math.max(0.0, (0.74 - 0.61* x[1] - 0.163 * x[2] * x[7]
			       + 0.001232 * x[2] * x[9] - 0.166 * x[6] * x[8]
			       + 0.227 * x[1] * x[1])/0.32);
	double temp = 0.0;
     
	temp = (( 28.98 + 3.818 * x[2] 
		  - 4.2 * x[0] * x[1] + 0.0207 * x[4] * x[9]
		  + 6.63 * x[5] * x[8] - 7.77 * x[6] * x[7]
		  + 0.32 * x[8] * x[9])
		+ (33.86 + 2.95 * x[2]
		   + 0.1792 * x[9] - 5.057 * x[0] * x[1]
		   - 11 * x[1] * x[7] - 0.0215 * x[4] * x[9]
		   - 9.98 * x[6] * x[7] + 22 * x[7] * x[8])
		+ (46.36 - 9.9 * x[1] - 12.9 * x[0] * x[7]
		   + 0.1107 * x[2] * x[9]) )/3;
     
	f[5] =  Math.max(0.0, temp/32);    
   
    
	f[6] =  Math.max(0.0, (4.72 - 0.5 * x[3] - 0.19 * x[1] * x[2]
			       - 0.0122 * x[3] * x[9] + 0.009325 * x[5] * x[9]
			       + 0.000191 * x[10] * x[10])/4.0);
    
	f[7] =  Math.max(0.0, (10.58 - 0.674 * x[0] * x[1] 
			       - 1.95  * x[1] * x[7]  + 0.02054  * x[2] * x[9] 
			       - 0.0198  * x[3] * x[9]  + 0.028  * x[5] * x[9])/9.9) ;
    
	f[8] =  Math.max(0.0, (16.45 - 0.489 * x[2] * x[6]
			       - 0.843 * x[4] * x[5] + 0.0432 * x[8] * x[9]
			       - 0.0556 * x[8] * x[10] - 0.000786 * x[10] * x[10])/15.7);    		
	for (int i=0;i<this.numberOfObjectives_;i++) {
	    solution.setObjective(i,f[i]);   
	}

    } // evaluate

    /** 
     * Evaluates the constraint overhead of a solution 
     * @param solution The solution
     * @throws JMException 
     */  
    public void evaluateConstraints(Solution solution) throws JMException {
    }
} // 
