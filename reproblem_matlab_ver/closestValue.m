% This function returns the closest value of a given real value among candidates.
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

function xClosest = closestValue(feasibleValues, x)

n = length(feasibleValues);
minDiffValue = abs(feasibleValues(1) - x);
xClosest = feasibleValues(1);

for i = 2 : n
    tmpDiffValue = abs(feasibleValues(i) - x);
    if tmpDiffValue < minDiffValue
       minDiffValue = tmpDiffValue;
       xClosest = feasibleValues(i);    
    end
end

