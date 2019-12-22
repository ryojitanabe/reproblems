% This is the disc brake design problem
% 
% Reference:
%  T. Ray and K. M. Liew, "A swarm metaphor for multiobjective design optimization," Eng. opt., vol. 34, no. 2, pp. 141–153, 2002.
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
    
function [f,g]=CRE23(x)
	 x1 = x(1);
	 x2 = x(2);
	 x3 = x(3);
	 x4 = x(4);

	 % First original objective function
	 f(1) = 4.9 * 1e-5 * (x2 * x2 - x1 * x1) * (x4 - 1.0);

	 % Second original objective function
	 f(2) = ((9.82 * 1e6) * (x2 * x2 - x1 * x1)) / (x3 * x4 * (x2 * x2 * x2 - x1 * x1 * x1));
	 
	 % Reformulated objective functions
	 g(1) = (x2 - x1) - 20.0;
	 g(2) = 0.4 - (x3 / (3.14 * (x2 * x2 - x1 * x1)));
	 g(3) = 1.0 - (2.22 * 1e-3 * x3 * (x2 * x2 * x2 - x1 * x1 * x1)) / power((x2 * x2 - x1 * x1), 2);
	 g(4) = (2.66 * 1e-2 * x3 * x4 * (x2 * x2 * x2 - x1 * x1 * x1)) / (x2 * x2 - x1 * x1) - 900.0;

	 % Calculate the constratint violation values
	 g(g>=0)=0;
	 g(g<0)=-g(g<0); 
