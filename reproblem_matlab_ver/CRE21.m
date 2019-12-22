% This is the two bar truss design problem
% 
% Reference:
%  C. A. C. Coello and G. T. Pulido, "Multiobjective structural optimization using a microgenetic algorithm," Stru. and Multi. Opt., vol. 30, no. 5, pp. 388-403, 2005.
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
    
function [f,g]=CRE21(x)
	 x1 = x(1);
	 x2 = x(2);
	 x3 = x(3);

	 % First original objective function
	 f(1) = x1 * sqrt(16.0 + (x3 * x3)) + x2 * sqrt(1.0 + x3 * x3);

	 % Second original objective function
	 f(2) = (20.0 * sqrt(16.0 + (x3 * x3))) / (x1 * x3);

	 % Constraint functions 
	 g(1) = 0.1 - f(1);
	 g(2) = 100000.0 - f(2);
	 g(3) = 100000 - ((80.0 * sqrt(1.0 + x3 * x3)) / (x3 * x2));
	   
	 % Calculate the constratint violation values
	 g(g>=0)=0;
	 g(g<0)=-g(g<0); 

