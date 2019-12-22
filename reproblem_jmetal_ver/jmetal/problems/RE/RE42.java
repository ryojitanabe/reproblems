//  RE42.java
//
//  Author:
//       Ryoji Tanabe <rt.ryoji.tanabe@gmail.com>
//
// This is the four objective version of the conceptual marine design
// 
// Reference:
//  M. G. Parsons and R. L. Scott, "Formulation of Multicriterion Design Optimization Problems for Solution With Scalar Numerical Optimization Methods," J. Ship Research, vol. 48, no. 1, pp. 61-76, 2004.
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

public class RE42 extends Problem {
  
  // defining the lower and upper limits
    public static final double [] LOWERLIMIT = {150.0, 20.0, 13.0, 10.0, 14.0, 0.63};
    public static final double [] UPPERLIMIT = {274.32, 32.31, 25.0, 11.71, 18.0, 0.75};
    private int numberOfOriginalObjectives_;
    private int numberOfOriginalConstraints_;
    
 /**
  * Constructor.
  * Creates a default instance of the ship parametric design problem.
  */
  public RE42() {
    numberOfVariables_   = 6;
    numberOfOriginalObjectives_ = 3;
    numberOfOriginalConstraints_ = 9;
    numberOfObjectives_  = 4;
    numberOfConstraints_ = 0;
    problemName_         = "RE42";
	        
    upperLimit_ = new double[numberOfVariables_];
    lowerLimit_ = new double[numberOfVariables_];

    for (int i = 0; i < numberOfVariables_; i++){
      lowerLimit_[i] = LOWERLIMIT[i];
      upperLimit_[i] = UPPERLIMIT[i];
    } // for
	        
    solutionType_ = new RealSolutionType(this) ;
 } // RE42
	
  /**
   * Evaluates a solution
   * @param solution The solution to evaluate
   * @throws JMException 
   */
    public void evaluate(Solution solution) throws JMException {         
	double [] x = new double[numberOfVariables_];
	double [] f = new double[numberOfObjectives_];
	double [] constraintFuncs = new double[numberOfOriginalConstraints_];
    
	for (int i = 0; i < numberOfVariables_; i++) x[i] = solution.getDecisionVariables()[i].getValue();

	double x_L = x[0];
	double x_B = x[1];
	double x_D = x[2];
	double x_T = x[3];
	double x_Vk = x[4];
	double x_CB = x[5];
   
	double displacement = 1.025 * x_L * x_B * x_T * x_CB;
	double V = 0.5144 * x_Vk;
	double g = 9.8065;
	double Fn = V / Math.pow(g * x_L, 0.5);
	double a = (4977.06 * x_CB * x_CB) - (8105.61 * x_CB) + 4456.51;
	double b = (-10847.2 * x_CB * x_CB) + (12817.0 * x_CB) - 6960.32;

	double power = (Math.pow(displacement, 2.0/3.0) * Math.pow(x_Vk, 3.0)) / (a + (b * Fn));
	double outfit_weight = 1.0 * Math.pow(x_L , 0.8) * Math.pow(x_B , 0.6) * Math.pow(x_D, 0.3) * Math.pow(x_CB, 0.1);
	double steel_weight = 0.034 * Math.pow(x_L ,1.7) * Math.pow(x_B ,0.7) * Math.pow(x_D ,0.4) * Math.pow(x_CB ,0.5);
	double machinery_weight = 0.17 * Math.pow(power, 0.9);
	double light_ship_weight = steel_weight + outfit_weight + machinery_weight;

	double ship_cost = 1.3 * ((2000.0 * Math.pow(steel_weight, 0.85))  + (3500.0 * outfit_weight) + (2400.0 * Math.pow(power, 0.8)));
	double capital_costs = 0.2 * ship_cost;

	double DWT = displacement - light_ship_weight;

	double running_costs = 40000.0 * Math.pow(DWT, 0.3);

	double round_trip_miles = 5000.0;
	double sea_days = (round_trip_miles / 24.0) * x_Vk;
	double handling_rate = 8000.0;

	double daily_consumption = ((0.19 * power * 24.0) / 1000.0) + 0.2;
	double fuel_price = 100.0;
	double fuel_cost = 1.05 * daily_consumption * sea_days * fuel_price;
	double port_cost = 6.3 * Math.pow(DWT, 0.8);

	double fuel_carried = daily_consumption * (sea_days + 5.0);
	double miscellaneous_DWT = 2.0 * Math.pow(DWT, 0.5);

	double cargo_DWT = DWT - fuel_carried - miscellaneous_DWT;
	double port_days = 2.0 * ((cargo_DWT / handling_rate) + 0.5);
	double RTPA = 350.0 / (sea_days + port_days);

	double voyage_costs = (fuel_cost + port_cost) * RTPA;
	double annual_costs = capital_costs + running_costs + voyage_costs;
	double annual_cargo = cargo_DWT * RTPA;

	f[0] = annual_costs / annual_cargo;
	f[1] = light_ship_weight;
	f[2] = -annual_cargo;  // f_2 is dealt as a minimization problem

	// Reformulated objective functions
	constraintFuncs[0] = (x_L / x_B) - 6.0;
	constraintFuncs[1] = -(x_L / x_D) + 15.0;
	constraintFuncs[2] = -(x_L / x_T) + 19.0;
	constraintFuncs[3] = 0.45 * Math.pow(DWT, 0.31) - x_T;
	constraintFuncs[4] = 0.7 * x_D + 0.7 - x_T;
	constraintFuncs[5] = 50000.0 - DWT;
	constraintFuncs[6] = DWT - 3000.0;
	constraintFuncs[7] = 0.32 - Fn;

	double KB = 0.53 * x_T;
	double BMT = ((0.085 * x_CB - 0.002) * x_B * x_B) / (x_T * x_CB);
	double KG = 1.0 + 0.52 * x_D;
	constraintFuncs[8] = (KB + BMT - KG) - (0.07 * x_B);

	for (int i = 0; i < numberOfOriginalConstraints_; i++ ) {
	    if (constraintFuncs[i] < 0.0) constraintFuncs[i] = -constraintFuncs[i];
	    else constraintFuncs[i] = 0;
	}

	f[3] = 0;
	
	for (int i = 0; i < numberOfOriginalConstraints_; i++ ) {
	    f[3] += constraintFuncs[i];
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
} // RE42
