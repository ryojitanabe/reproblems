% This is a two-objective version of the reinforced concrete beam design problem.
% 
% Reference:
% H. M. Amir and T. Hasegawa, "Nonlinear Mixed-Discrete Structural Optimization," J. Struct. Eng., vol. 115, no. 3, pp. 626-646, 1989.
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

function f=RE21(x)
	 x1 = x(1);
	 x2 = x(2);
	 x3 = x(3);
	 x4 = x(4);

	 F = 10;
	 sigma = 10;
	 E = 2 * 1e5;
	 L = 200;

	 f(1) = L * ((2 * x1) + sqrt(2.0) * x2 + sqrt(x3) + x4);
	 f(2) = ((F * L) / E) * ((2.0 / x1) + (2.0 * sqrt(2.0) / x2) - (2.0 * sqrt(2.0) / x3) + (2.0 / x4));  
end
