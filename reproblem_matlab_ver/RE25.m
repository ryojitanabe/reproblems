% This is a two-objective version of the coil compression spring design problem.
% 
% Reference:
% J. Lampinen and I. Zelinka, "Mixed integer-discrete-continuous optimization by differential evolution, part 2: a practical example," in International Conference on Soft Computing, 1999, pp. 77-81.
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
    
function f=RE25(x)
	 FEASIBLE_VALUES_RE25 = [0.009 0.0095 0.0104 0.0118 0.0128 0.0132 0.014 0.015 0.0162 0.0173 0.018 0.02 0.023 0.025 0.028 0.032 0.035 0.041 0.047 0.054 0.063 0.072 0.08 0.092 0.105 0.12 0.135 0.148 0.162 0.177 0.192 0.207 0.225 0.244 0.263 0.283 0.307 0.331 0.362 0.394 0.4375 0.5];

	 x1 = round(x(1));
	 x2 = x(2);
	 x3 = closestValue(FEASIBLE_VALUES_RE25, x(3));

	% first original objective function
	f(1) = (pi * pi * x2 * x3 * x3 * (x1 + 2)) / 4.0;
	    
	% constraint functions
	Cf = ((4.0 * (x2 / x3) - 1) / (4.0 * (x2 / x3) - 4)) + (0.615 * x3 / x2);
	Fmax = 1000.0;
	S =189000.0;	    
	G = 11.5 * 1e+6;
	K  = (G * x3 * x3 * x3 * x3) / (8 * x1 * x2 * x2 * x2);
	lmax = 14.0;
	lf = (Fmax / K) + 1.05 *  (x1 + 2) * x3;
	dmin = 0.2;
	Dmax = 3;
	Fp = 300.0;
	sigmaP = Fp / K;
	sigmaPM = 6;
	sigmaW = 1.25;

	g(1) = -((8 * Cf * Fmax * x2) / (pi * x3 * x3 * x3)) + S;
	g(2) = -lf + lmax;
	g(3) = -3 + (x2 / x3);
	g(4) = -sigmaP + sigmaPM;
	g(5) = -sigmaP - ((Fmax - Fp) / K) - 1.05 * (x1 + 2) * x3 + lf;
	g(6) = sigmaW- ((Fmax - Fp) / K);

	% Calculate the constratint violation values
	g(g>=0)=0;
	g(g<0)=-g(g<0); 

	f(2) = g(1) + g(2) + g(3) + g(4) + g(5) + g(6);
