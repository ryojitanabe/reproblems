% Note: this is a revised version of the file downloaded from Dr. Miqing Li's web site (http://www.cs.bham.ac.uk/~limx/)
%
% This is the nine objective version of the car cab design problem
% 
% Reference:
% K. Deb and H. Jain, "An evolutionary many-objective optimization algorithm using reference-point-based nondominated sorting approach, part I: solving problems with box constraints," IEEE TEVC, vol. 18, no. 4, pp. 577-601, 2014

    
function f=RE91(x)
	 x0 = x(1);
	 x1 = x(2);
	 x2 = x(3);
	 x3 = x(4);
	 x4 = x(5);
	 x5 = x(6);
	 x6 = x(7);

	 x7 = 0.006 * randn + 0.345;
	 x8 = 0.006 * randn + 0.192;
	 x9 = 10 * randn + 0.0;
	 x10 = 10 * randn + 0.0;

	 f(1) = 1.98 + 4.9 * x0 + 6.67 * x1 + 6.98 * x2 +  4.01 * x3 +  1.75 * x4 +  0.00001 * x5  +  2.73 * x6;

	 f(2) = max(0.0, (1.16 - 0.3717* x1 * x3 - 0.00931 * x1 * x9 - 0.484 * x2 * x8 + 0.01343 * x5 * x9 )/1.0) ;

	 f(3) = max(0.0, (0.261 - 0.0159 * x0 * x1 - 0.188 * x0 * x7 - 0.019 * x1 * x6 + 0.0144 * x2 * x4 + 0.87570001 * x4 * x9 + 0.08045 * x5 * x8 + 0.00139 * x7 * x10 + 0.00001575 * x9 * x10)/0.32);

	 f(4) = max(0.0, (0.214 + 0.00817 * x4  - 0.131 * x0 * x7 - 0.0704 * x0 * x8 + 0.03099 * x1 * x5 - 0.018 * x1 * x6 + 0.0208 * x2 * x7 + 0.121 * x2 * x8 - 0.00364 * x4 * x5 + 0.0007715 * x4 * x9 - 0.0005354 * x5 * x9 + 0.00121 * x7 * x10 + 0.00184 * x8 * x9 - 0.018 * x1 * x1)/0.32);

	 f(5) =  max(0.0, (0.74 - 0.61* x1 - 0.163 * x2 * x7 + 0.001232 * x2 * x9 - 0.166 * x6 * x8 + 0.227 * x1 * x1)/0.32);

	 temp = (( 28.98 + 3.818 * x2 - 4.2 * x0 * x1 + 0.0207 * x4 * x9 + 6.63 * x5 * x8 - 7.77 * x6 * x7 + 0.32 * x8 * x9) + (33.86 + 2.95 * x2 + 0.1792 * x9 - 5.057 * x0 * x1 - 11 * x1 * x7 - 0.0215 * x4 * x9 - 9.98 * x6 * x7 + 22 * x7 * x8) + (46.36 - 9.9 * x1 - 12.9 * x0 * x7 + 0.1107 * x2 * x9) )/3;

	 f(6) =  max(0.0, temp/32);           

	 f(7) =  max(0.0, (4.72 - 0.5 * x3 - 0.19 * x1 * x2 - 0.0122 * x3 * x9 + 0.009325 * x5 * x9 + 0.000191 * x10 * x10)/4.0);

	 f(8) =  max(0.0, (10.58 - 0.674 * x0 * x1 - 1.95  * x1 * x7  + 0.02054  * x2 * x9 - 0.0198  * x3 * x9  + 0.028  * x5 * x9)/9.9) ;

	 f(9) =  max(0.0, (16.45 - 0.489 * x2 * x6 - 0.843 * x4 * x5 + 0.0432 * x8 * x9 - 0.0556 * x8 * x10 - 0.000786 * x10 * x10)/15.7);    	
