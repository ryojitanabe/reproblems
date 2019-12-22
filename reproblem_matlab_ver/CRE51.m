% Note: this is a translated version of the jMetal implementation (http://jmetal.sourceforge.net/)
% This is the water resource planning problem problem
% 
% Reference:
% T. Ray, K. Tai, and K. C. Seow, "Multiobjective design optimization by an evolutionary algorithm," Eng. Opt., vol. 33, no. 4, pp. 399-424, 2001.
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
    
function [f,g]=CRE51(x)
	 x1 = x(1);
	 x2 = x(2);
	 x3 = x(3);

	 % First original objective function
	 f(1) = 106780.37 * (x2 + x3) + 61704.67 ;
	 % Second original objective function
	 f(2) = 3000 * x1 ;
	 % Third original objective function
	 f(3) = 305700 * 2289 * x2 / power(0.06*2289, 0.65) ;
	 % Fourth original objective function
	 f(4) = 250 * 2289 * exp(-39.75*x2+9.9*x3+2.74) ;
	 % Fifth original objective function
	 f(5) = 25 * (1.39 /(x1*x2) + 4940*x3 -80) ;

	 % Constraint functions          
	g(1) = 1 - (0.00139/(x1*x2)+4.94*x3-0.08);
	g(2) = 1 - (0.000306/(x1*x2)+1.082*x3-0.0986);
	g(3) = 50000 - (12.307/(x1*x2) + 49408.24*x3+4051.02);
	g(4) = 16000 - (2.098/(x1*x2)+8046.33*x3-696.71);
	g(5) = 10000 - (2.138/(x1*x2)+7883.39*x3-705.04);
	g(6) = 2000 - (0.417*x1*x2 + 1721.26*x3-136.54);
	g(7) = 550 - (0.164/(x1*x2)+631.13*x3-54.48);
      			
	 % Calculate the constratint violation values
	 g(g>=0)=0;
	 g(g<0)=-g(g<0); 
