% A real-world multi-objective problem suite (the RE benchmark set)
% 
% Reference:
% Ryoji Tanabe, Hisao Ishibuchi, "An Easy-to-use Real-world Multi-objective Problem Suite" (submitted)
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

clc;
clear all;
 
testProblem = "CRE51";
numConsts = 0;

switch testProblem
       case "RE21"
	    numObjs = 2;
	    numVars = 4;

  	    F = 10;
	    sigma = 10;
	    tmpVar = (F / sigma);
	    tmpVar2 = 3 * tmpVar;
	    
	    lowerBounds = [tmpVar sqrt(tmpVar) sqrt(tmpVar) tmpVar];
	    upperBounds = [tmpVar2 tmpVar2 tmpVar2 tmpVar2];
       case "RE22"
	    numObjs = 2;
	    numVars = 3;
	    lowerBounds = [0.2 0 0];
	    upperBounds = [15 20 40];
       case "RE23"
	    numObjs = 2;
	    numVars = 4;
	    lowerBounds = [1 1 10 10];
	    upperBounds = [100 100 200 240];
       case "RE24"
	    numObjs = 2;
	    numVars = 2;
	    lowerBounds = [0.5 0.5];
	    upperBounds = [4 50];
       case "RE25"
	    numObjs = 2;
	    numVars = 3;
	    lowerBounds = [1 0.6 0.09];
	    upperBounds = [70 3 0.5];
       case "RE31"
	    numObjs = 3;
	    numVars = 3;
	    lowerBounds = [0.00001 0.00001 1.0];
	    upperBounds = [100.0 100.0 3.0];
       case "RE32"
	    numObjs = 3;
	    numVars = 4;
	    lowerBounds = [0.125 0.1 0.1 0.125];
	    upperBounds = [5.0 10.0 10.0 5.0];
       case "RE33"
	    numObjs = 3;
	    numVars = 4;
	    lowerBounds = [55 75 1000 11];
	    upperBounds = [80 110 3000 20];
       case "RE34"
	    numObjs = 3;
	    numVars = 5;
	    lowerBounds = [1.0 1.0 1.0 1.0 1.0];
	    upperBounds = [3.0 3.0 3.0 3.0 3.0];
       case "RE35"
	    numObjs = 3;
	    numVars = 7;
	    lowerBounds = [2.6 0.7 17 7.3 7.3 2.9 5.0];
	    upperBounds = [3.6 0.8 28 8.3 8.3 3.9 5.5];
       case "RE36"
	    numObjs = 3;
	    numVars = 4;
	    lowerBounds = [12 12 12 12];
	    upperBounds = [60 60 60 60];
       case "RE37"
	    numObjs = 3;
	    numVars = 4;
	    lowerBounds = [0 0 0 0];
	    upperBounds = [1 1 1 1];
       case "RE41"
	    numObjs = 4;
	    numVars = 7;
	    lowerBounds = [0.5 0.45 0.5 0.5 0.875 0.4 0.4];
	    upperBounds = [1.5 1.35 1.5 1.5 2.625 1.2 1.2];
       case "RE42"
	    numObjs = 4;
	    numVars = 6;
	    lowerBounds = [150.0 20.0 13.0 10.0 14.0 0.63];
	    upperBounds = [274.32 32.31 25.0 11.71 18.0 0.75];
       case "RE61"
	    numObjs = 6;
	    numVars = 3;
	    lowerBounds = [0.01 0.01 0.01];
	    upperBounds = [0.45 0.10 0.10];
       case "RE91"
	    numObjs = 9;
	    numVars = 7;
	    lowerBounds = [0.5 0.45 0.5 0.5 0.875 0.4 0.4];
	    upperBounds = [1.5 1.35 1.5 1.5 2.625 1.2 1.2];
       case "CRE21"
	    numObjs = 2;
	    numVars = 3;
	    numConsts = 3;	
	    lowerBounds = [0.00001 0.00001 1.0];
	    upperBounds = [100.0 100.0 3.0];
       case "CRE22"
	    numObjs = 2;
	    numVars = 4;
	    numConsts = 4;	
	    lowerBounds = [0.125 0.1 0.1 0.125];
	    upperBounds = [5.0 10.0 10.0 5.0];
       case "CRE23"
	    numObjs = 2;
	    numVars = 4;
	    numConsts = 4;	
	    lowerBounds = [55 75 1000 11];
	    upperBounds = [80 110 3000 20];
       case "CRE24"
	    numObjs = 2;
	    numVars = 7;
	    numConsts = 11;		    
	    lowerBounds = [2.6 0.7 17 7.3 7.3 2.9 5.0];
	    upperBounds = [3.6 0.8 28 8.3 8.3 3.9 5.5];
       case "CRE25"
	    numObjs = 2;
	    numVars = 4;
	    numConsts = 1;		    
	    lowerBounds = [12 12 12 12];
	    upperBounds = [60 60 60 60];
       case "CRE31"
	    numObjs = 3;
	    numVars = 7;
	    numConsts = 10;		    
	    lowerBounds = [0.5 0.45 0.5 0.5 0.875 0.4 0.4];
	    upperBounds = [1.5 1.35 1.5 1.5 2.625 1.2 1.2];
       case "CRE32"
	    numObjs = 3;
	    numVars = 6;
	    numConsts = 9;
	    lowerBounds = [150.0 20.0 13.0 10.0 14.0 0.63];
	    upperBounds = [274.32 32.31 25.0 11.71 18.0 0.75];
       case "CRE51"
	    numObjs = 5;
	    numVars = 3;
	    numConsts = 7;	    
	    lowerBounds = [0.01 0.01 0.01];
	    upperBounds = [0.45 0.10 0.10];
end

printf("The selected probelm is %s\n", testProblem);
printf("Number of objectives = %d, number of design variables = %d, number of constraints = %d \n", numObjs, numVars, numConsts);
printf("Lower bounds for x: ");
disp(lowerBounds)
printf("Upper bounds for x: ");
disp(upperBounds)
printf("Randomly generated solution x: ");
x = (upperBounds - lowerBounds) .* rand(1, numVars) + lowerBounds;
disp(x)

if numConsts <= 0
   res=feval(testProblem, x);
else
   [res, g]=feval(testProblem, x);
end

printf("Objective vector of  x: ");
disp(res)

if numConsts > 0
   printf("Constraint violation values of  x: ");
   disp(g)
end

