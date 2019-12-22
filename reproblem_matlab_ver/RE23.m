% This is a two-objective version of the pressure vessel design problem.
% 
% Reference:
% B. K. Kannan and S. N. Kramer, "An Augmented Lagrange Multiplier Based Method for Mixed Integer Discrete Continuous Optimization and Its Applications to Mechanical Design, Journal of Mechanical Design, vol. 116, no. 2, pp. 405-411, 1994.
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
    
function f=RE23(x)
	 x1 = 0.0625 * round(x(1));
	 x2 = 0.0625 * round(x(2));
	 x3 = x(3);
	 x4 = x(4);		 

	 % First original objective function
	 f(1) = (0.6224 * x1 * x3* x4) + (1.7781 * x2 * x3 * x3) + (3.1661 * x1 * x1 * x4) + (19.84 * x1 * x1 * x3);

	 g(1) = x1 - (0.0193 * x3);
	 g(2) = x2 - (0.00954 * x3);
	 g(3) = (pi * x3 * x3 * x4) + ((4.0/3.0) * (pi * x3 * x3 * x3)) - 1296000;
	   
	  % Calculate the constratint violation values
	  g(g>=0)=0;
	  g(g<0)=-g(g<0); 

	  f(2) = g(1) + g(2) + g(3);	  
