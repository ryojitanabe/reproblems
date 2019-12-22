% This is a four bar truss design problem.
% 
% Reference:
% F. Y. Cheng and X. S. Li: Generalized center method for multiobjective engineering optimization. Engineering Optimization, 31(5), pp. 641-661 (1999)
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
    
function f=RE22(x)
	 FEASIBLE_VALUES_RE22 = [0.20 0.31 0.40 0.44 0.60 0.62 0.79 0.80 0.88 0.93 1.0 1.20 1.24 1.32 1.40 1.55 1.58 1.60 1.76 1.80 1.86 2.0 2.17 2.20 2.37 2.40 2.48 2.60 2.64 2.79 2.80 3.0 3.08 3,10 3.16 3.41 3.52 3.60 3.72 3.95 3.96 4.0 4.03 4.20 4.34 4.40 4.65 4.74 4.80 4.84 5.0 5.28 5.40 5.53 5.72 6.0 6.16 6.32 6.60 7.11 7.20 7.80 7.90 8.0 8.40 8.69 9.0 9.48 10.27 11.0 11.06 11.85 12.0 13.0 14.0 15.0];

	 x1 = closestValue(FEASIBLE_VALUES_RE22, x(1));
	 x2 = x(2);
	 x3 = x(3);

   	 % First original objective function
   	 f(1) = (29.4 * x1) + (0.6 * x2 * x3);

   	  % Original constraint functions 	
   	  g(1) = (x1 * x3) - 7.735 * ((x1 * x1) / x2) - 180.0;
   	  g(2) = 4.0 - (x3 / x2);
	   
	  % Calculate the constratint violation values
	  g(g>=0)=0;
	  g(g<0)=-g(g<0); 

	  f(2) = g(1) + g(2);	  
