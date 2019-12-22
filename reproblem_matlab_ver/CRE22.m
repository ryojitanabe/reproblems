% This is the disc brake design prolem
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
    
function [f,g]=CRE22(x)
	 x1 = x(1);
	 x2 = x(2);
	 x3 = x(3);
	 x4 = x(4);

	 P = 6000;
	 L = 14;
	 E = 30 * 1e6;
	 G = 12 * 1e6;
	 tauMax = 13600;
	 sigmaMax = 30000;
    
	% First original objective function
	f(1) = (1.10471 * x1 * x1 * x2) + (0.04811 * x3 * x4) * (14.0 + x2);
	% Second original objective function
	f(2) = (4 * P * L * L * L) / (E * x4 * x3 * x3 * x3);

	% constraint functions
	M = P * (L + (x2 / 2));
	tmpVar = ((x2 * x2) / 4.0) + power((x1 + x3) / 2.0, 2);
	R = sqrt(tmpVar);
	tmpVar = ((x2 * x2) / 12.0) + power((x1 + x3) / 2.0, 2);
	J = 2 * sqrt(2) * x1 * x2 * tmpVar;

    	tauDashDash = (M * R) / J;    
	tauDash = P / (sqrt(2) * x1 * x2);
	tmpVar = tauDash * tauDash + ((2 * tauDash * tauDashDash * x2) / (2 * R)) + (tauDashDash * tauDashDash);
	tau = sqrt(tmpVar);
	sigma = (6 * P * L) / (x4 * x3 * x3);
	tmpVar = 4.013 * E * sqrt((x3 * x3 * x4 * x4 * x4 * x4 * x4 * x4) / 36.0) / (L * L);
	tmpVar2 = (x3 / (2 * L)) * sqrt(E / (4 * G));
	PC = tmpVar * (1 - tmpVar2);

	g(1) = tauMax - tau;
    	g(2) = sigmaMax - sigma;
    	g(3) = x4 - x1;
    	g(4) = PC - P;

	% Calculate the constratint violation values
	g(g>=0)=0;
	g(g<0)=-g(g<0); 
