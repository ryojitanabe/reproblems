% This is the gear train design problem.
% 
% Reference:
% Kalyanmoy Deb and Aravind Srinivasan, "Innovization: Innovative Design Principles Through Optimization," KanGAL Report Number 2005007, Indian Institute of Technology Kanpur (2005).
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
    
function [f,g]=CRE25(x)
	 x1 = round(x(1));
	 x2 = round(x(2));
	 x3 = round(x(3));
	 x4 = round(x(4));

	 % First original objective function
	 f(1) = abs(6.931 - ((x3 / x1) * (x4 / x2)));

	 % Second original objective function (the maximum value among the four variables)
	 A = [x1 x2 x3 x4]
	 f(2) = max(A);

	 g(1) = 0.5 - (f(1) / 6.931);

	 % Calculate the constratint violation values
	 g(g>=0)=0;
	 g(g<0)=-g(g<0); 
