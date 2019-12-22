% This is a two-objective version of the hatch cover design problem.
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
    
function f=RE24(x)
	 x1 = x(1);
	 x2 = x(2);

	% First original objective function
	f(1) = x1 + (120 * x2);

	E = 700000;
	sigmaBMax = 700;
	tauMax = 450;
	deltaMax = 1.5;
	sigmaK = (E * x1 * x1) / 100;
	sigmaB = 4500 / (x1 * x2);
	tau = 1800 / x2;
	delta = (56.2 * 10000) / (E * x1 * x2 * x2);
	
	g(1) = 1 - (sigmaB / sigmaBMax);
	g(2) = 1 - (tau / tauMax);
	g(3) = 1 - (delta / deltaMax);
	g(4) = 1 - (sigmaB / sigmaK);

	% Calculate the constratint violation values
	g(g>=0)=0;
	g(g<0)=-g(g<0); 

	f(2) = g(1) + g(2) + g(3) + g(4);	  
