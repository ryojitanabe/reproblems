% This is the conceptual marine design
% 
% Reference:
%  M. G. Parsons and R. L. Scott, "Formulation of Multicriterion Design Optimization Problems for Solution With Scalar Numerical Optimization Methods," J. Ship Research, vol. 48, no. 1, pp. 61-76, 2004.
%
%  Copyright (c) 2018 Ryoji Tanabe
%
% This program is free software: you can redistribute it and/or modify
% it under the terms of the GNU General Public License as published by
% the Free Software Foundation, either version 3 of the License, or
% (at your option) any later version.

% This program is distributed in the hope that it will be useful,
% but WITHOUT ANY WARRANTY; without even the implied warranty of
% MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
% GNU General Public License for more details.

% You should have received a copy of the GNU General Public License
% along with this program.  If not, see <http://www.gnu.org/licenses/>.
    
function [f,g]=CRE32(x)
	 x_L = x(1);
	 x_B = x(2);
	 x_D = x(3);
	 x_T = x(4);
	 x_Vk = x(5);
	 x_CB = x(6);

	 displacement = 1.025 * x_L * x_B * x_T * x_CB;
	 V = 0.5144 * x_Vk;
	 pg = 9.8065;
	 Fn = V / power(pg * x_L, 0.5);
	 a = (4977.06 * x_CB * x_CB) - (8105.61 * x_CB) + 4456.51;
	 b = (-10847.2 * x_CB * x_CB) + (12817.0 * x_CB) - 6960.32;

	 power_pb = (power(displacement, 2.0/3.0) * power(x_Vk, 3.0)) / (a + (b * Fn));
	 outfit_weight = 1.0 * power(x_L, 0.8) * power(x_B, 0.6) * power(x_D, 0.3) * power(x_CB, 0.1);
	 steel_weight = 0.034 * power(x_L ,1.7) * power(x_B ,0.7) * power(x_D ,0.4) * power(x_CB ,0.5);
	 machinery_weight = 0.17 * power(power_pb, 0.9);
	 light_ship_weight = steel_weight + outfit_weight + machinery_weight;

	 ship_cost = 1.3 * ((2000.0 * power(steel_weight, 0.85))  + (3500.0 * outfit_weight) + (2400.0 * power(power_pb, 0.8)));
	 capital_costs = 0.2 * ship_cost;

	 DWT = displacement - light_ship_weight;

	 running_costs = 40000.0 * power(DWT, 0.3);

	 round_trip_miles = 5000.0;
	 sea_days = (round_trip_miles / 24.0) * x_Vk;
	 handling_rate = 8000.0;

	 daily_consumption = ((0.19 * power_pb * 24.0) / 1000.0) + 0.2;
	 fuel_price = 100.0;
	 fuel_cost = 1.05 * daily_consumption * sea_days * fuel_price;
	 port_cost = 6.3 * power(DWT, 0.8);

	 fuel_carried = daily_consumption * (sea_days + 5.0);
	 miscellaneous_DWT = 2.0 * power(DWT, 0.5);

	 cargo_DWT = DWT - fuel_carried - miscellaneous_DWT;
	 port_days = 2.0 * ((cargo_DWT / handling_rate) + 0.5);
	 RTPA = 350.0 / (sea_days + port_days);
	 voyage_costs = (fuel_cost + port_cost) * RTPA;
	 annual_costs = capital_costs + running_costs + voyage_costs;
	 annual_cargo = cargo_DWT * RTPA;

	f(1) = annual_costs / annual_cargo;
	f(2) = light_ship_weight;
	f(3) = -annual_cargo;  % f_2 is dealt as a minimization problem

	% Reformulated objective functions
	g(1) = (x_L / x_B) - 6.0;
	g(2) = -(x_L / x_D) + 15.0;
	g(3) = -(x_L / x_T) + 19.0;
	g(4) = 0.45 * power(DWT, 0.31) - x_T;
	g(5) = 0.7 * x_D + 0.7 - x_T;
	g(6) = 50000.0 - DWT;
	g(7) = DWT - 3000.0;
	g(8) = 0.32 - Fn;

	 KB = 0.53 * x_T;
	 BMT = ((0.085 * x_CB - 0.002) * x_B * x_B) / (x_T * x_CB);
	 KG = 1.0 + 0.52 * x_D;
	 g(9) = (KB + BMT - KG) - (0.07 * x_B);

	 % Calculate the constratint violation values
	 g(g>=0)=0;
	 g(g<0)=-g(g<0); 

