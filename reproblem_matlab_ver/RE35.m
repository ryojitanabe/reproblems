% This is a three-objective version of the speed reducer design problem.
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
    
function f=RE35(x)
	x1 = x(1);
	x2 = x(2);
	x3 = round(x(3));
	x4 = x(4);
	x5 = x(5);
	x6 = x(6);
	x7 = x(7);

	% First original objective function (weight)
	f(1) = 0.7854 * x1 * (x2 * x2) * (((10.0 * x3 * x3) / 3.0) + (14.933 * x3) - 43.0934) - 1.508 * x1 * (x6 * x6 + x7 * x7) + 7.477 * (x6 * x6 * x6 + x7 * x7 * x7) + 0.7854 * (x4 * x6 * x6 + x5 * x7 * x7);
	
	% Second original objective function (stress)
	tmpVar = power((745.0 * x4) / (x2 * x3), 2.0)  + 1.69 * 1e7;
	f(2) =  sqrt(tmpVar) / (0.1 * x6 * x6 * x6);

	% Constraint functions 	
	g(1) = -(1.0 / (x1 * x2 * x2 * x3)) + 1.0 / 27.0;
	g(2) = -(1.0 / (x1 * x2 * x2 * x3 * x3)) + 1.0 / 397.5;
	g(3) = -(x4 * x4 * x4) / (x2 * x3 * x6 * x6 * x6 * x6) + 1.0 / 1.93;
	g(4) = -(x5 * x5 * x5) / (x2 * x3 * x7 * x7 * x7 * x7) + 1.0 / 1.93;
	g(5) = -(x2 * x3) + 40.0;
	g(6) = -(x1 / x2) + 12.0;
	g(7) = -5.0 + (x1 / x2);
	g(8) = -1.9 + x4 - 1.5 * x6;
	g(9) = -1.9 + x5 - 1.1 * x7;
	g(10) =  -f(2) + 1300.0;
	tmpVar = power((745.0 * x5) / (x2 * x3), 2.0) + 1.575 * 1e8;
	g(11) = -sqrt(tmpVar) / (0.1 * x7 * x7 * x7) + 1100.0;


	% Calculate the constratint violation values
	g(g>=0)=0;
	g(g<0)=-g(g<0); 

	f(3) = g(1) + g(2) + g(3) + g(4) + g(5) + g(6) + g(7) + g(8) + g(9) + g(10) + g(11);

