 /* A real-world multi-objective problem suite (the RE benchmark set) */ 
 /* Reference: */
 /* Ryoji Tanabe, Hisao Ishibuchi, "An Easy-to-use Real-world Multi-objective Problem Suite" (submitted) */
 /*  Copyright (c) 2018 Ryoji Tanabe */

 /* This program is free software: you can redistribute it and/or modify */
 /* it under the terms of the GNU General Public License as published by */
 /* the Free Software Foundation, either version 3 of the License, or */
 /* (at your option) any later version. */

 /* This program is distributed in the hope that it will be useful, */
 /* but WITHOUT ANY WARRANTY; without even the implied warranty of */
 /* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the */
 /* GNU General Public License for more details. */

 /* You should have received a copy of the GNU General Public License */
 /* along with this program.  If not, see <http://www.gnu.org/licenses/>. */

#include <stdio.h>
#include <stdlib.h>
#include <math.h>

double FEASIBLE_VALUES_RE22[] = {0.20, 0.31, 0.40, 0.44, 0.60, 0.62, 0.79, 0.80, 0.88, 0.93, 1.0, 1.20, 1.24, 1.32, 1.40, 1.55, 1.58, 1.60, 1.76, 1.80, 1.86, 2.0, 2.17, 2.20, 2.37, 2.40, 2.48, 2.60, 2.64, 2.79, 2.80, 3.0, 3.08, 3,10, 3.16, 3.41, 3.52, 3.60, 3.72, 3.95, 3.96, 4.0, 4.03, 4.20, 4.34, 4.40, 4.65, 4.74, 4.80, 4.84, 5.0, 5.28, 5.40, 5.53, 5.72, 6.0, 6.16, 6.32, 6.60, 7.11, 7.20, 7.80, 7.90, 8.0, 8.40, 8.69, 9.0, 9.48, 10.27, 11.0, 11.06, 11.85, 12.0, 13.0, 14.0, 15.0};

double FEASIBLE_VALUES_RE25[] = {0.009, 0.0095, 0.0104, 0.0118, 0.0128, 0.0132, 0.014, 0.015, 0.0162, 0.0173, 0.018, 0.02, 0.023, 0.025, 0.028, 0.032, 0.035, 0.041, 0.047, 0.054, 0.063, 0.072, 0.08, 0.092, 0.105, 0.12, 0.135, 0.148, 0.162, 0.177, 0.192, 0.207, 0.225, 0.244, 0.263, 0.283, 0.307, 0.331, 0.362, 0.394, 0.4375, 0.5};

/*    
  This function was described in the following website:
  http://www.sat.t.u-tokyo.ac.jp/~omi/random_variables_generation.html
  I understand that a better pseudo random generator should be used!
 */ 
double randUniform() {
    return ((double)rand()+1.0)/((double)RAND_MAX+2.0);
}

/* 
  This function was described in the following website:
  http://www.sat.t.u-tokyo.ac.jp/~omi/random_variables_generation.html
 */ 
double randNormal(double mu, double sigma){
  double z=sqrt( -2.0*log(randUniform()) ) * sin( 2.0*M_PI*randUniform());
  return mu + sigma*z;
}

double getClosestValue(double *targetArray, int arraySize, double compValue) {
  int i;
  double closestValue = targetArray[0];
  double minDiffValue = fabs(targetArray[0] - compValue);
  double tmpDiffValue = 0;

  printf("%d\n", arraySize);
  
  for (i = 1; i < arraySize; i++) {
    tmpDiffValue = fabs(targetArray[i] - compValue);
    if (tmpDiffValue < minDiffValue) {
      minDiffValue = tmpDiffValue;
      closestValue = targetArray[i];
    }
  }

  return closestValue;		  
}

void setNumObjsVars(char *testProblem, int *numObjs, int *numVars, int *numConsts, double *lowerBounds, double *upperBounds) {
  *numConsts = 0;
  
  if (testProblem == "RE21") {
    *numObjs = 2;
    *numVars = 4;    
  }
  else if (testProblem == "RE22") {
    *numObjs = 2;
    *numVars = 3;    
  }
  else if (testProblem == "RE23") {
    *numObjs = 2;
    *numVars = 4;    
  }
  else if (testProblem == "RE24") {
    *numObjs = 2;
    *numVars = 2;    
  }
  else if (testProblem == "RE25") {
    *numObjs = 2;
    *numVars = 3;    
  }
  else if (testProblem == "RE31") {
    *numObjs = 3;
    *numVars = 3;    
  }
  else if (testProblem == "RE32") {
    *numObjs = 3;
    *numVars = 4;    
  }
  else if (testProblem == "RE33") {
    *numObjs = 3;
    *numVars = 4;    
  }
  else if (testProblem == "RE34") {
    *numObjs = 3;
    *numVars = 5;    
  }
  else if (testProblem == "RE35") {
    *numObjs = 3;
    *numVars = 7;    
  }
  else if (testProblem == "RE36") {
    *numObjs = 3;
    *numVars = 4;    
  }
  else if (testProblem == "RE37") {
    *numObjs = 3;
    *numVars = 4;    
  }
  else if (testProblem == "RE41") {
    *numObjs = 4;
    *numVars = 7;    
  }
  else if (testProblem == "RE42") {
    *numObjs = 4;
    *numVars = 6;    
  }
  else if (testProblem == "RE61") {
    *numObjs = 6;
    *numVars = 3;    
  }
  else if (testProblem == "RE91") {
    *numObjs = 9;
    *numVars = 7;    
  }    
  else if (testProblem == "CRE21") {
    *numObjs = 2;
    *numVars = 3;    
    *numConsts = 3;  
  }    
  else if (testProblem == "CRE22") {
    *numObjs = 2;
    *numVars = 4;    
    *numConsts = 4;
  }    
  else if (testProblem == "CRE23") {
    *numObjs = 2;
    *numVars = 4;
    *numConsts = 4;
  }
  else if (testProblem == "CRE24") {
    *numObjs = 2;
    *numVars = 7;
    *numConsts = 11;
  }    
  else if (testProblem == "CRE25") {
    *numObjs = 2;
    *numVars = 4;
    *numConsts = 1;
  }
  else if (testProblem == "CRE31") {
    *numObjs = 3;
    *numVars = 7;
    *numConsts = 10;
  }    
  else if (testProblem == "CRE32") {
    *numObjs = 3;
    *numVars = 6;
    *numConsts = 9;
  }
  else if (testProblem == "CRE51") {
    *numObjs = 5;
    *numVars = 3;
    *numConsts = 7;
  }
  else {
    printf("Error! %s is not defined\n", testProblem);
    exit(-1);
  }  
}

void setBounds(char *testProblem, int numVars, double *lowerBounds, double *upperBounds) {
  int i;
  
  if (testProblem == "RE21") {            
    double F = 10;
    double sigma = 10;
    double tmpVar = (F / sigma);
  
    for (i = 0; i < numVars; i++) upperBounds[i] = 3 * tmpVar;
    lowerBounds[0] = tmpVar;
    lowerBounds[1] = sqrt(2.0) * tmpVar;
    lowerBounds[2] = sqrt(2.0) * tmpVar;
    lowerBounds[3] = tmpVar;
  }
  else if (testProblem == "RE22") {            
    lowerBounds[0] = 0.2;
    lowerBounds[1] = 0;
    lowerBounds[2] = 0;
    upperBounds[0] = 15;
    upperBounds[1] = 20;
    upperBounds[2] = 40;
  }
  else if (testProblem == "RE23") {            
    lowerBounds[0] = 1;
    lowerBounds[1] = 1;
    lowerBounds[2] = 10;
    lowerBounds[3] = 10;
    upperBounds[0] = 100;
    upperBounds[1] = 100;
    upperBounds[2] = 200;
    upperBounds[3] = 240;
  }
  else if (testProblem == "RE24") {            
    lowerBounds[0] = 0.5;
    lowerBounds[1] = 0.5;   
    upperBounds[0] = 4;
    upperBounds[1] = 50;
  }
  else if (testProblem == "RE25") {            
    lowerBounds[0] = 1;   
    lowerBounds[1] = 0.6;   
    lowerBounds[2] = 0.09;   
    upperBounds[0] = 70;
    upperBounds[1] = 3;
    upperBounds[2] = 0.5;   
  }
  else if (testProblem == "RE31" || testProblem == "CRE21") {            
    lowerBounds[0] = 0.00001;
    lowerBounds[1] = 0.00001;
    lowerBounds[2] = 1.0;
    upperBounds[0] = 100.0;
    upperBounds[1] = 100.0;
    upperBounds[2] = 3.0;
  }
  else if (testProblem == "RE32" || testProblem == "CRE22") {            
    lowerBounds[0] = 0.125;
    lowerBounds[1] = 0.1;
    lowerBounds[2] = 0.1;
    lowerBounds[3] = 0.125;
    upperBounds[0] = 5.0;
    upperBounds[1] = 10.0;
    upperBounds[2] = 10.0;
    upperBounds[3] = 5.0;
  }
  else if (testProblem == "RE33" || testProblem == "CRE23") {            
    lowerBounds[0] = 55;
    lowerBounds[1] = 75;
    lowerBounds[2] = 1000;
    lowerBounds[3] = 11;
    upperBounds[0] = 80;
    upperBounds[1] = 110;
    upperBounds[2] = 3000;
    upperBounds[3] = 20;
  }
  else if (testProblem == "RE34") {            
    for (i = 0; i < numVars; i++) {
      lowerBounds[i] = 1.0;
      upperBounds[i] = 3.0;
    } 	    
  }
  else if (testProblem == "RE35" || testProblem == "CRE24") {            
    lowerBounds[0] = 2.6;
    lowerBounds[1] = 0.7;
    lowerBounds[2] = 17;
    lowerBounds[3] = 7.3;
    lowerBounds[4] = 7.3;
    lowerBounds[5] = 2.9;
    lowerBounds[6] = 5.0;
  
    upperBounds[0] = 3.6;
    upperBounds[1] = 0.8;
    upperBounds[2] = 28;
    upperBounds[3] = 8.3;
    upperBounds[4] = 8.3;
    upperBounds[5] = 3.9;
    upperBounds[6] = 5.5;
  }
  else if (testProblem == "RE36" || testProblem == "CRE25") {            
    lowerBounds[0] = 12;
    lowerBounds[1] = 12;
    lowerBounds[2] = 12;
    lowerBounds[3] = 12; 
    upperBounds[0] = 60;
    upperBounds[1] = 60;
    upperBounds[2] = 60;
    upperBounds[3] = 60;
  }
  else if (testProblem == "RE37") {            
    for (i = 0; i < numVars; i++) {
      lowerBounds[i] = 0.0;
      upperBounds[i] = 1.0;
    } 	    
  }  
  else if (testProblem == "RE41" || testProblem == "CRE31") {            
    lowerBounds[0] = 0.5;
    lowerBounds[1] = 0.45; 
    lowerBounds[2] = 0.5; 
    lowerBounds[3] = 0.5; 
    lowerBounds[4] = 0.875; 
    lowerBounds[5] = 0.4; 
    lowerBounds[6] = 0.4; 
    upperBounds[0] = 1.5;
    upperBounds[1] = 1.35;
    upperBounds[2] = 1.5;
    upperBounds[3] = 1.5;
    upperBounds[4] = 2.625;
    upperBounds[5] = 1.2;
    upperBounds[6] = 1.2;
  }
  else if (testProblem == "RE42" || testProblem == "CRE32") {            
    lowerBounds[0] = 150.0; 
    lowerBounds[1] = 20.0; 
    lowerBounds[2] = 13.0; 
    lowerBounds[3] = 10.0; 
    lowerBounds[4] = 14.0; 
    lowerBounds[5] = 0.63; 
    upperBounds[0] = 274.32;
    upperBounds[1] = 32.31;
    upperBounds[2] = 25.0;
    upperBounds[3] = 11.71;
    upperBounds[4] = 18.0;
    upperBounds[5] = 0.75;
    
  }
  else if (testProblem == "RE61" || testProblem == "CRE51") {            
    lowerBounds[0] = 0.01; 
    lowerBounds[1] = 0.01; 
    lowerBounds[2] = 0.01; 
    upperBounds[0] = 0.45;
    upperBounds[1] = 0.10;
    upperBounds[2] = 0.10;
  }
  else if (testProblem == "RE91") {            
    lowerBounds[0] = 0.5; 
    lowerBounds[1] = 0.45; 
    lowerBounds[2] = 0.5; 
    lowerBounds[3] = 0.5; 
    lowerBounds[4] = 0.875; 
    lowerBounds[5] = 0.4; 
    lowerBounds[6] = 0.4; 

    upperBounds[0] = 1.5;
    upperBounds[1] = 1.35;
    upperBounds[2] = 1.5;
    upperBounds[3] = 1.5;
    upperBounds[4] = 2.625;
    upperBounds[5] = 1.2;
    upperBounds[6] = 1.2;    
  }  
}


void RE21(int numObjs, int numVars, double *f, double *x) {
  int i;
  double x1 = x[0];
  double x2 = x[1];
  double x3 = x[2];
  double x4 = x[3];

  double F = 10;
  double sigma = 10;
  double E = 2 * 1e5;
  double L = 200;

  f[0] = L * ((2 * x1) + sqrt(2.0) * x2 + sqrt(x3) + x4);
  f[1] = ((F * L) / E) * ((2.0 / x1) + (2.0 * sqrt(2.0) / x2) - (2.0 * sqrt(2.0) / x3) + (2.0 / x4));  
}

void RE22(int numObjs, int numVars, double *f, double *x) {
  int i;
  int numOriginalConsts = 2;
  double g[numOriginalConsts];
  int arraySize = sizeof(FEASIBLE_VALUES_RE22)/sizeof(FEASIBLE_VALUES_RE22[0]);
  
  double x1 = getClosestValue(FEASIBLE_VALUES_RE22, arraySize, x[0]);
  double x2 = x[1];       	
  double x3 = x[2];

  // First original objective function
  f[0] = (29.4 * x1) + (0.6 * x2 * x3);

  // Original constraint functions 	
  g[0] = (x1 * x3) - 7.735 * ((x1 * x1) / x2) - 180.0;
  g[1] = 4.0 - (x3 / x2);

  for (i = 0; i < numOriginalConsts; i++ ) {
    if (g[i] < 0.0) g[i] = -g[i];
    else g[i] = 0;
  }

  f[1] = g[0] + g[1];	  
}

void RE23(int numObjs, int numVars, double *f, double *x) {
  int i;
  int numOriginalConsts = 3;
  double g[numOriginalConsts];
  
  double x1 = 0.0625 * round(x[0]);
  double x2 = 0.0625 * round(x[1]);
  double x3 = x[2];
  double x4 = x[3];		 
	
  // First original objective function
  f[0] = (0.6224 * x1 * x3* x4) + (1.7781 * x2 * x3 * x3) + (3.1661 * x1 * x1 * x4) + (19.84 * x1 * x1 * x3);

  g[0] = x1 - (0.0193 * x3);
  g[1] = x2 - (0.00954 * x3);
  g[2] = (M_PI * x3 * x3 * x4) + ((4.0/3.0) * (M_PI * x3 * x3 * x3)) - 1296000;

  for (i = 0; i < numOriginalConsts; i++ ) {
    if (g[i] < 0.0) g[i] = -g[i];
    else g[i] = 0;
  }

  f[1] = g[0] + g[1] + g[2];  
}

void RE24(int numObjs, int numVars, double *f, double *x) {
  int i;
  int numOriginalConsts = 4;
  double g[numOriginalConsts];

  double x1 = x[0];       	
  double x2 = x[1];

  // First original objective function
  f[0] = x1 + (120 * x2);

  double E = 700000;
  double sigmaBMax = 700;
  double tauMax = 450;
  double deltaMax = 1.5;
  double sigmaK = (E * x1 * x1) / 100;
  double sigmaB = 4500 / (x1 * x2);
  double tau = 1800 / x2;
  double delta = (56.2 * 10000) / (E * x1 * x2 * x2);
	
  g[0] = 1 - (sigmaB / sigmaBMax);
  g[1] = 1 - (tau / tauMax);
  g[2] = 1 - (delta / deltaMax);
  g[3] = 1 - (sigmaB / sigmaK);

  for (i = 0; i < numOriginalConsts; i++ ) {
    if (g[i] < 0.0) g[i] = -g[i];
    else g[i] = 0;
  }

  f[1] = g[0] + g[1] + g[2] + g[3];  
}

void RE25(int numObjs, int numVars, double *f, double *x) {
  int i;
  int numOriginalConsts = 6;
  double g[numOriginalConsts];
  int arraySize = sizeof(FEASIBLE_VALUES_RE25)/sizeof(FEASIBLE_VALUES_RE25[0]);
  
  double x1 = round(x[0]);
  double x2 = x[1];       	
  double x3 = getClosestValue(FEASIBLE_VALUES_RE25, arraySize, x[2]);

  // first original objective function
  f[0] = (M_PI * M_PI * x2 * x3 * x3 * (x1 + 2)) / 4.0;
	    
  // constraint functions
  double Cf = ((4.0 * (x2 / x3) - 1) / (4.0 * (x2 / x3) - 4)) + (0.615 * x3 / x2);
  double Fmax = 1000.0;
  double S =189000.0;	    
  double G = 11.5 * 1e+6;
  double K  = (G * x3 * x3 * x3 * x3) / (8 * x1 * x2 * x2 * x2);
  double lmax = 14.0;
  double lf = (Fmax / K) + 1.05 *  (x1 + 2) * x3;
  double dmin = 0.2;
  double Dmax = 3;
  double Fp = 300.0;
  double sigmaP = Fp / K;
  double sigmaPM = 6;
  double sigmaW = 1.25;

  g[0] = -((8 * Cf * Fmax * x2) / (M_PI * x3 * x3 * x3)) + S;
  g[1] = -lf + lmax;
  g[2] = -3 + (x2 / x3);
  g[3] = -sigmaP + sigmaPM;
  g[4] = -sigmaP - ((Fmax - Fp) / K) - 1.05 * (x1 + 2) * x3 + lf;
  g[5] = sigmaW- ((Fmax - Fp) / K);

  for (i = 0; i < numOriginalConsts; i++ ) {
    if (g[i] < 0.0) g[i] = -g[i];
    else g[i] = 0;
  }

  f[1] = g[0] + g[1] + g[2] + g[3] + g[4] + g[5];      
}

void RE31(int numObjs, int numVars, double *f, double *x) {
  int i;
  int numOriginalConsts = 3;
  double g[numOriginalConsts];
  
  double x1 = x[0];
  double x2 = x[1];
  double x3 = x[2];
    
  // First original objective function
  f[0] = x1 * sqrt(16.0 + (x3 * x3)) + x2 * sqrt(1.0 + x3 * x3);
  // Second original objective function
  f[1] = (20.0 * sqrt(16.0 + (x3 * x3))) / (x1 * x3);

  // Constraint functions 
  g[0] = 0.1 - f[0];
  g[1] = 100000.0 - f[1];
  g[2] = 100000 - ((80.0 * sqrt(1.0 + x3 * x3)) / (x3 * x2));

  for (i = 0; i < numOriginalConsts; i++ ) {
    if (g[i] < 0.0) g[i] = -g[i];
    else g[i] = 0;
  }
	
  f[2] = g[0] + g[1] + g[2];
}

void RE32(int numObjs, int numVars, double *f, double *x) {
  int i;
  int numOriginalConsts = 4;
  double g[numOriginalConsts];

  double x1 = x[0];
  double x2 = x[1];
  double x3 = x[2];
  double x4 = x[3];

  double P = 6000;
  double L = 14;
  double E = 30 * 1e6;

  //    double deltaMax = 0.25;
  double G = 12 * 1e6;
  double tauMax = 13600;
  double sigmaMax = 30000;
    
  // First original objective function
  f[0] = (1.10471 * x1 * x1 * x2) + (0.04811 * x3 * x4) * (14.0 + x2);
  // Second original objective function
  f[1] = (4 * P * L * L * L) / (E * x4 * x3 * x3 * x3);

  // constraint functions
  double M = P * (L + (x2 / 2));
  double tmpVar = ((x2 * x2) / 4.0) + pow((x1 + x3) / 2.0, 2);
  double R = sqrt(tmpVar);
  tmpVar = ((x2 * x2) / 12.0) + pow((x1 + x3) / 2.0, 2);
  double J = 2 * sqrt(2) * x1 * x2 * tmpVar;

  double tauDashDash = (M * R) / J;    
  double tauDash = P / (sqrt(2) * x1 * x2);
  tmpVar = tauDash * tauDash + ((2 * tauDash * tauDashDash * x2) / (2 * R)) + (tauDashDash * tauDashDash);
  double tau = sqrt(tmpVar);
  double sigma = (6 * P * L) / (x4 * x3 * x3);
  tmpVar = 4.013 * E * sqrt((x3 * x3 * x4 * x4 * x4 * x4 * x4 * x4) / 36.0) / (L * L);
  double tmpVar2 = (x3 / (2 * L)) * sqrt(E / (4 * G));
  double PC = tmpVar * (1 - tmpVar2);

  g[0] = tauMax - tau;
  g[1] = sigmaMax - sigma;
  g[2] = x4 - x1;
  g[3] = PC - P;

  for (i = 0; i < numOriginalConsts; i++ ) {
    if (g[i] < 0.0) g[i] = -g[i];
    else g[i] = 0;
  }
	
  f[2] = g[0] + g[1] + g[2] + g[3];   
}

void RE33(int numObjs, int numVars, double *f, double *x) {
  int i;
  int numOriginalConsts = 4;
  double g[numOriginalConsts];
  
  double x1 = x[0];
  double x2 = x[1];
  double x3 = x[2];
  double x4 = x[3];
	
  // First original objective function
  f[0] = 4.9 * 1e-5 * (x2 * x2 - x1 * x1) * (x4 - 1.0);
  // Second original objective function
  f[1] = ((9.82 * 1e6) * (x2 * x2 - x1 * x1)) / (x3 * x4 * (x2 * x2 * x2 - x1 * x1 * x1));
    
  // Reformulated objective functions
  g[0] = (x2 - x1) - 20.0;
  g[1] = 0.4 - (x3 / (3.14 * (x2 * x2 - x1 * x1)));
  g[2] = 1.0 - (2.22 * 1e-3 * x3 * (x2 * x2 * x2 - x1 * x1 * x1)) / pow((x2 * x2 - x1 * x1), 2);
  g[3] = (2.66 * 1e-2 * x3 * x4 * (x2 * x2 * x2 - x1 * x1 * x1)) / (x2 * x2 - x1 * x1) - 900.0;

  for (i = 0; i < numOriginalConsts; i++ ) {
    if (g[i] < 0.0) g[i] = -g[i];
    else g[i] = 0;
  }

  f[2] = g[0] + g[1] + g[2] + g[3];
}

void RE34(int numObjs, int numVars, double *f, double *x) {
  double x1 = x[0];
  double x2 = x[1];
  double x3 = x[2];
  double x4 = x[3];
  double x5 = x[4];

  f[0] = 1640.2823 + (2.3573285 * x1) + (2.3220035 * x2) + (4.5688768 * x3) + (7.7213633 * x4) + (4.4559504 * x5);

  f[1] = 6.5856 + (1.15 * x1) - (1.0427 * x2) + (0.9738 * x3) + (0.8364 * x4) - (0.3695 * x1 * x4) + (0.0861 * x1 * x5) + (0.3628 * x2 * x4)  - (0.1106 * x1 * x1)  - (0.3437 * x3 * x3) + (0.1764 * x4 * x4);
  
  f[2] = -0.0551 + (0.0181 * x1) + (0.1024 * x2) + (0.0421 * x3) - (0.0073 * x1 * x2) + (0.024 * x2 * x3) - (0.0118 * x2 * x4) - (0.0204 * x3 * x4) - (0.008 * x3 * x5) - (0.0241 * x2 * x2) + (0.0109 * x4 * x4);  
}

void RE35(int numObjs, int numVars, double *f, double *x) {
  int i;
  int numOriginalConsts = 11;
  double g[numOriginalConsts];

  double x1 = x[0];
  double x2 = x[1];
  double x3 = round(x[2]);
  double x4 = x[3];
  double x5 = x[4];
  double x6 = x[5];
  double x7 = x[6];

  // First original objective function (weight)
  f[0] = 0.7854 * x1 * (x2 * x2) * (((10.0 * x3 * x3) / 3.0) + (14.933 * x3) - 43.0934)
    - 1.508 * x1 * (x6 * x6 + x7 * x7)
    + 7.477 * (x6 * x6 * x6 + x7 * x7 * x7)
    + 0.7854 * (x4 * x6 * x6 + x5 * x7 * x7);
    
  // Second original objective function (stress)
  double tmpVar = pow((745.0 * x4) / (x2 * x3), 2.0)  + 1.69 * 1e7;
  f[1] =  sqrt(tmpVar) / (0.1 * x6 * x6 * x6);

  // Constraint functions 	
  g[0] = -(1.0 / (x1 * x2 * x2 * x3)) + 1.0 / 27.0;
  g[1] = -(1.0 / (x1 * x2 * x2 * x3 * x3)) + 1.0 / 397.5;
  g[2] = -(x4 * x4 * x4) / (x2 * x3 * x6 * x6 * x6 * x6) + 1.0 / 1.93;
  g[3] = -(x5 * x5 * x5) / (x2 * x3 * x7 * x7 * x7 * x7) + 1.0 / 1.93;
  g[4] = -(x2 * x3) + 40.0;
  g[5] = -(x1 / x2) + 12.0;
  g[6] = -5.0 + (x1 / x2);
  g[7] = -1.9 + x4 - 1.5 * x6;
  g[8] = -1.9 + x5 - 1.1 * x7;
  g[9] =  -f[1] + 1300.0;
  tmpVar = pow((745.0 * x5) / (x2 * x3), 2.0) + 1.575 * 1e8;
  g[10] = -sqrt(tmpVar) / (0.1 * x7 * x7 * x7) + 1100.0;
	
  for (i = 0; i < numOriginalConsts; i++ ) {
    if (g[i] < 0.0) g[i] = -g[i];
    else g[i] = 0;
  }

  f[2] = g[0] + g[1] + g[2] + g[3] + g[4] + g[5] + g[6] + g[7] + g[8] + g[9] + g[10];
}

void RE36(int numObjs, int numVars, double *f, double *x) {
  int i;
  int numOriginalConsts = 1;
  double g[numOriginalConsts];

  // all the four variables must be inverger values
  double x1 = round(x[0]);
  double x2 = round(x[1]);
  double x3 = round(x[2]);
  double x4 = round(x[3]);
   
  // First original objective function
  f[0] = fabs(6.931 - ((x3 / x1) * (x4 / x2)));
  // Second original objective function (the maximum value among the four variables)
  double maxValue = x1;
  if (x2 > maxValue) maxValue = x2;
  if (x3 > maxValue) maxValue = x3;
  if (x4 > maxValue) maxValue = x4;
  f[1] = maxValue;

  g[0] = 0.5 - (f[0] / 6.931);

  for (i = 0; i < numOriginalConsts; i++ ) {
    if (g[i] < 0.0) g[i] = -g[i];
    else g[i] = 0;
  }
    
  f[2] = g[0];
}

void RE37(int numObjs, int numVars, double *f, double *x) {
  double xAlpha = x[0];
  double xHA = x[1];
  double xOA = x[2];
  double xOPTT = x[3];
   
  // f1 (TF_max)
  f[0] = 0.692 + (0.477 * xAlpha) - (0.687 * xHA) - (0.080 * xOA) - (0.0650 * xOPTT) - (0.167 * xAlpha * xAlpha) - (0.0129 * xHA * xAlpha) + (0.0796 * xHA * xHA) - (0.0634 * xOA * xAlpha) - (0.0257 * xOA * xHA) + (0.0877 * xOA * xOA) - (0.0521 * xOPTT * xAlpha) + (0.00156 * xOPTT * xHA) + (0.00198 * xOPTT * xOA) + (0.0184 * xOPTT * xOPTT);

  // f2 (X_cc)
  f[1] = 0.153 - (0.322 * xAlpha) + (0.396 * xHA) + (0.424 * xOA) + (0.0226 * xOPTT) + (0.175 * xAlpha * xAlpha) + (0.0185 * xHA * xAlpha) - (0.0701 * xHA * xHA) - (0.251 * xOA * xAlpha) + (0.179 * xOA * xHA) + (0.0150 * xOA * xOA) + (0.0134 * xOPTT * xAlpha) + (0.0296 * xOPTT * xHA) + (0.0752 * xOPTT * xOA) + (0.0192 * xOPTT * xOPTT);
   
  // f3 (TT_max)
  f[2] = 0.370 - (0.205 * xAlpha) + (0.0307 * xHA) + (0.108 * xOA) + (1.019 * xOPTT) - (0.135 * xAlpha * xAlpha) + (0.0141 * xHA * xAlpha) + (0.0998 * xHA * xHA) + (0.208 * xOA * xAlpha) - (0.0301 * xOA * xHA) - (0.226 * xOA * xOA) + (0.353 * xOPTT * xAlpha) - (0.0497 * xOPTT * xOA) - (0.423 * xOPTT * xOPTT) + (0.202 * xHA * xAlpha * xAlpha) - (0.281 * xOA * xAlpha * xAlpha) - (0.342 * xHA * xHA * xAlpha) - (0.245 * xHA * xHA * xOA) + (0.281 * xOA * xOA * xHA) - (0.184 * xOPTT * xOPTT * xAlpha) - (0.281 * xHA * xAlpha * xOA);	
}

void RE41(int numObjs, int numVars, double *f, double *x) {
  int i;
  int numOriginalConsts = 10;
  double g[numOriginalConsts];

  double x1 = x[0];
  double x2 = x[1];
  double x3 = x[2];
  double x4 = x[3];
  double x5 = x[4];
  double x6 = x[5];
  double x7 = x[6];
    
  // First original objective function
  f[0] = 1.98 + 4.9 * x1 + 6.67 * x2 + 6.98 * x3 + 4.01 * x4 + 1.78 * x5 + 0.00001 * x6 + 2.73 * x7;
  // Second original objective function
  f[1] = 4.72 - 0.5 * x4 - 0.19 * x2 * x3;
  // Third original objective function
  double Vmbp = 10.58 - 0.674 * x1 * x2 - 0.67275 * x2;
  double Vfd = 16.45 - 0.489 * x3 * x7 - 0.843 * x5 * x6;
  f[2] = 0.5 * (Vmbp + Vfd);

  // Constraint functions
  g[0] = 1 -(1.16 - 0.3717 * x2 * x4 - 0.0092928 * x3);
  g[1] = 0.32 -(0.261 - 0.0159 * x1 * x2 - 0.06486 * x1 -  0.019 * x2 * x7 + 0.0144 * x3 * x5 + 0.0154464 * x6);
  g[2] = 0.32 -(0.214 + 0.00817 * x5 - 0.045195 * x1 - 0.0135168 * x1 + 0.03099 * x2 * x6 - 0.018 * x2 * x7  + 0.007176 * x3 + 0.023232 * x3 - 0.00364 * x5 * x6 - 0.018 * x2 * x2);
  g[3] = 0.32 -(0.74 - 0.61 * x2 - 0.031296 * x3 - 0.031872 * x7 + 0.227 * x2 * x2);
  g[4] = 32 -(28.98 + 3.818 * x3 - 4.2 * x1 * x2 + 1.27296 * x6 - 2.68065 * x7);
  g[5] = 32 -(33.86 + 2.95 * x3 - 5.057 * x1 * x2 - 3.795 * x2 - 3.4431 * x7 + 1.45728);
  g[6] =  32 -(46.36 - 9.9 * x2 - 4.4505 * x1);
  g[7] =  4 - f[1];
  g[8] =  9.9 - Vmbp;
  g[9] =  15.7 - Vfd;

  for (i = 0; i < numOriginalConsts; i++ ) {
    if (g[i] < 0.0) g[i] = -g[i];
    else g[i] = 0;
  }
  
  f[3] = g[0] + g[1] + g[2] + g[3] + g[4] + g[5] + g[6] + g[7] + g[8] + g[9];  
}

void RE42(int numObjs, int numVars, double *f, double *x) {
  int i;
  int numOriginalConsts = 9;
  double constraintFuncs[numOriginalConsts];

  double x_L = x[0];
  double x_B = x[1];
  double x_D = x[2];
  double x_T = x[3];
  double x_Vk = x[4];
  double x_CB = x[5];
   
  double displacement = 1.025 * x_L * x_B * x_T * x_CB;
  double V = 0.5144 * x_Vk;
  double g = 9.8065;
  double Fn = V / pow(g * x_L, 0.5);
  double a = (4977.06 * x_CB * x_CB) - (8105.61 * x_CB) + 4456.51;
  double b = (-10847.2 * x_CB * x_CB) + (12817.0 * x_CB) - 6960.32;

  double power = (pow(displacement, 2.0/3.0) * pow(x_Vk, 3.0)) / (a + (b * Fn));
  double outfit_weight = 1.0 * pow(x_L , 0.8) * pow(x_B , 0.6) * pow(x_D, 0.3) * pow(x_CB, 0.1);
  double steel_weight = 0.034 * pow(x_L ,1.7) * pow(x_B ,0.7) * pow(x_D ,0.4) * pow(x_CB ,0.5);
  double machinery_weight = 0.17 * pow(power, 0.9);
  double light_ship_weight = steel_weight + outfit_weight + machinery_weight;

  double ship_cost = 1.3 * ((2000.0 * pow(steel_weight, 0.85))  + (3500.0 * outfit_weight) + (2400.0 * pow(power, 0.8)));
  double capital_costs = 0.2 * ship_cost;

  double DWT = displacement - light_ship_weight;

  double running_costs = 40000.0 * pow(DWT, 0.3);

  double round_trip_miles = 5000.0;
  double sea_days = (round_trip_miles / 24.0) * x_Vk;
  double handling_rate = 8000.0;

  double daily_consumption = ((0.19 * power * 24.0) / 1000.0) + 0.2;
  double fuel_price = 100.0;
  double fuel_cost = 1.05 * daily_consumption * sea_days * fuel_price;
  double port_cost = 6.3 * pow(DWT, 0.8);

  double fuel_carried = daily_consumption * (sea_days + 5.0);
  double miscellaneous_DWT = 2.0 * pow(DWT, 0.5);

  double cargo_DWT = DWT - fuel_carried - miscellaneous_DWT;
  double port_days = 2.0 * ((cargo_DWT / handling_rate) + 0.5);
  double RTPA = 350.0 / (sea_days + port_days);

  double voyage_costs = (fuel_cost + port_cost) * RTPA;
  double annual_costs = capital_costs + running_costs + voyage_costs;
  double annual_cargo = cargo_DWT * RTPA;

  f[0] = annual_costs / annual_cargo;
  f[1] = light_ship_weight;
  f[2] = -annual_cargo;  // f_2 is dealt as a minimization problem

  // Reformulated objective functions
  constraintFuncs[0] = (x_L / x_B) - 6.0;
  constraintFuncs[1] = -(x_L / x_D) + 15.0;
  constraintFuncs[2] = -(x_L / x_T) + 19.0;
  constraintFuncs[3] = 0.45 * pow(DWT, 0.31) - x_T;
  constraintFuncs[4] = 0.7 * x_D + 0.7 - x_T;
  constraintFuncs[5] = 500000.0 - DWT;
  constraintFuncs[6] = DWT - 3000.0;
  constraintFuncs[7] = 0.32 - Fn;

  double KB = 0.53 * x_T;
  double BMT = ((0.085 * x_CB - 0.002) * x_B * x_B) / (x_T * x_CB);
  double KG = 1.0 + 0.52 * x_D;
  constraintFuncs[8] = (KB + BMT - KG) - (0.07 * x_B);

  for (i = 0; i < numOriginalConsts; i++ ) {
    if (constraintFuncs[i] < 0.0) constraintFuncs[i] = -constraintFuncs[i];
    else constraintFuncs[i] = 0;
  }

  f[3] = constraintFuncs[0] + constraintFuncs[1] + constraintFuncs[2] + constraintFuncs[3] + constraintFuncs[4] + constraintFuncs[5] + constraintFuncs[6] + constraintFuncs[7] + constraintFuncs[8];  	  
}

void RE61(int numObjs, int numVars, double *f, double *x) {
  int i;
  int numOriginalConsts = 7;
  double g[numOriginalConsts];

  // First original objective function
  f[0] = 106780.37 * (x[1] + x[2]) + 61704.67 ;
  // Second original objective function
  f[1] = 3000 * x[0] ;
  // Third original objective function
  f[2] = 305700 * 2289 * x[1] / pow(0.06*2289, 0.65) ;
  // Fourth original objective function
  f[3] = 250 * 2289 * exp(-39.75*x[1]+9.9*x[2]+2.74) ;
  // Fifth original objective function
  f[4] = 25 * (1.39 /(x[0]*x[1]) + 4940*x[2] -80) ;

  // Constraint functions          
  g[0] = 1 - (0.00139/(x[0]*x[1])+4.94*x[2]-0.08)             ;
  g[1] = 1 - (0.000306/(x[0]*x[1])+1.082*x[2]-0.0986)         ;
  g[2] = 50000 - (12.307/(x[0]*x[1]) + 49408.24*x[2]+4051.02) ;
  g[3] = 16000 - (2.098/(x[0]*x[1])+8046.33*x[2]-696.71)      ;
  g[4] = 10000 - (2.138/(x[0]*x[1])+7883.39*x[2]-705.04)      ;
  g[5] = 2000 - (0.417*x[0]*x[1] + 1721.26*x[2]-136.54)       ;
  g[6] = 550 - (0.164/(x[0]*x[1])+631.13*x[2]-54.48) ;
       
  for (i = 0; i < numOriginalConsts; i++ ) {
    if (g[i] < 0.0) g[i] = -g[i];
    else g[i] = 0;
  }

  f[5] = g[0] + g[1] + g[2] + g[3] + g[4] + g[5] + g[6];
}

void RE91(int numObjs, int numVars, double *f, double *x) {
  double x1 = x[0];
  double x2 = x[1];
  double x3 = x[2];
  double x4 = x[3];
  double x5 = x[4];
  double x6 = x[5];
  double x7 = x[6];

  double x8 = 0.006 * (randNormal(0, 1)) + 0.345;
  double x9 = 0.006 * (randNormal(0, 1)) + 0.192;
  double x10 = 10 * (randNormal(0, 1)) + 0.0;
  double x11 = 10 * (randNormal(0, 1)) + 0.0;
    
  // First function
  f[0] = 1.98 + 4.9 * x1 + 6.67 * x2 + 6.98 * x3
    +  4.01 * x4 +  1.75 * x5 +  0.00001 * x6  +  2.73 * x7;
  // Second function
  f[1] = fmax(0.0, (1.16 - 0.3717* x2 * x4 - 0.00931 * x2 * x10 - 0.484 * x3 * x9
			+ 0.01343 * x6 * x10 )/1.0) ;
  // Third function
  f[2] = fmax(0.0, (0.261 - 0.0159 * x1 * x2
			- 0.188 * x1 * x8 - 0.019 * x2 * x7
			+ 0.0144 * x3 * x5 + 0.87570001 * x5 * x10
			+ 0.08045 * x6 * x9 + 0.00139 * x8 * x11
			+ 0.00001575 * x10 * x11)/0.32);
  
  f[3] = fmax(0.0, (0.214 + 0.00817 * x5 
			- 0.131 * x1 * x8 - 0.0704 * x1 * x9
			+ 0.03099 * x2 * x6 - 0.018 * x2 * x7
			+ 0.0208 * x3 * x8 + 0.121 * x3 * x9
			- 0.00364 * x5 * x6 + 0.0007715 * x5 * x10
			- 0.0005354 * x6 * x10 + 0.00121 * x8 * x11
			+ 0.00184 * x9 * x10 - 0.018 * x2 * x2)/0.32);
    
  f[4] =  fmax(0.0, (0.74 - 0.61* x2 - 0.163 * x3 * x8
			 + 0.001232 * x3 * x10 - 0.166 * x7 * x9
			 + 0.227 * x2 * x2)/0.32);
  double temp = 0.0;
     
  temp = (( 28.98 + 3.818 * x3 
	    - 4.2 * x1 * x2 + 0.0207 * x5 * x10
	    + 6.63 * x6 * x9 - 7.77 * x7 * x8
	    + 0.32 * x9 * x10)
	  + (33.86 + 2.95 * x3
	     + 0.1792 * x10 - 5.057 * x1 * x2
	     - 11 * x2 * x8 - 0.0215 * x5 * x10
	     - 9.98 * x7 * x8 + 22 * x8 * x9)
	  + (46.36 - 9.9 * x2 - 12.9 * x1 * x8
	     + 0.1107 * x3 * x10) )/3;
     
  f[5] =  fmax(0.0, temp/32);    
   
    
  f[6] =  fmax(0.0, (4.72 - 0.5 * x4 - 0.19 * x2 * x3
			 - 0.0122 * x4 * x10 + 0.009325 * x6 * x10
			 + 0.000191 * x11 * x11)/4.0);
    
  f[7] =  fmax(0.0, (10.58 - 0.674 * x1 * x2 
			 - 1.95  * x2 * x8  + 0.02054  * x3 * x10 
			 - 0.0198  * x4 * x10  + 0.028  * x6 * x10)/9.9) ;
    
  f[8] =  fmax(0.0, (16.45 - 0.489 * x3 * x7
			 - 0.843 * x5 * x6 + 0.0432 * x9 * x10
			 - 0.0556 * x9 * x11 - 0.000786 * x11 * x11)/15.7);    		  
}

void CRE21(int numObjs, int numVars, int numConsts, double *f, double *x, double *g) {
  int i;  
  double x1 = x[0];
  double x2 = x[1];
  double x3 = x[2];
    
  // First original objective function
  f[0] = x1 * sqrt(16.0 + (x3 * x3)) + x2 * sqrt(1.0 + x3 * x3);
  // Second original objective function
  f[1] = (20.0 * sqrt(16.0 + (x3 * x3))) / (x1 * x3);

  // Constraint functions 
  g[0] = 0.1 - f[0];
  g[1] = 100000.0 - f[1];
  g[2] = 100000 - ((80.0 * sqrt(1.0 + x3 * x3)) / (x3 * x2));

  for (i = 0; i < numConsts; i++ ) {
    if (g[i] < 0.0) g[i] = -g[i];
    else g[i] = 0;
  }       
}

void CRE22(int numObjs, int numVars, int numConsts, double *f, double *x, double *g) {
  int i;
  double x1 = x[0];
  double x2 = x[1];
  double x3 = x[2];
  double x4 = x[3];

  double P = 6000;
  double L = 14;
  double E = 30 * 1e6;

  //    double deltaMax = 0.25;
  double G = 12 * 1e6;
  double tauMax = 13600;
  double sigmaMax = 30000;
    
  // First original objective function
  f[0] = (1.10471 * x1 * x1 * x2) + (0.04811 * x3 * x4) * (14.0 + x2);
  // Second original objective function
  f[1] = (4 * P * L * L * L) / (E * x4 * x3 * x3 * x3);

  // constraint functions
  double M = P * (L + (x2 / 2));
  double tmpVar = ((x2 * x2) / 4.0) + pow((x1 + x3) / 2.0, 2);
  double R = sqrt(tmpVar);
  tmpVar = ((x2 * x2) / 12.0) + pow((x1 + x3) / 2.0, 2);
  double J = 2 * sqrt(2) * x1 * x2 * tmpVar;

  double tauDashDash = (M * R) / J;    
  double tauDash = P / (sqrt(2) * x1 * x2);
  tmpVar = tauDash * tauDash + ((2 * tauDash * tauDashDash * x2) / (2 * R)) + (tauDashDash * tauDashDash);
  double tau = sqrt(tmpVar);
  double sigma = (6 * P * L) / (x4 * x3 * x3);
  tmpVar = 4.013 * E * sqrt((x3 * x3 * x4 * x4 * x4 * x4 * x4 * x4) / 36.0) / (L * L);
  double tmpVar2 = (x3 / (2 * L)) * sqrt(E / (4 * G));
  double PC = tmpVar * (1 - tmpVar2);

  g[0] = tauMax - tau;
  g[1] = sigmaMax - sigma;
  g[2] = x4 - x1;
  g[3] = PC - P;

  for (i = 0; i < numConsts; i++ ) {
    if (g[i] < 0.0) g[i] = -g[i];
    else g[i] = 0;
  }       
}

void CRE23(int numObjs, int numVars, int numConsts, double *f, double *x, double *g) {
  int i;  
  double x1 = x[0];
  double x2 = x[1];
  double x3 = x[2];
  double x4 = x[3];
	
  // First original objective function
  f[0] = 4.9 * 1e-5 * (x2 * x2 - x1 * x1) * (x4 - 1.0);
  // Second original objective function
  f[1] = ((9.82 * 1e6) * (x2 * x2 - x1 * x1)) / (x3 * x4 * (x2 * x2 * x2 - x1 * x1 * x1));
    
  // Reformulated objective functions
  g[0] = (x2 - x1) - 20.0;
  g[1] = 0.4 - (x3 / (3.14 * (x2 * x2 - x1 * x1)));
  g[2] = 1.0 - (2.22 * 1e-3 * x3 * (x2 * x2 * x2 - x1 * x1 * x1)) / pow((x2 * x2 - x1 * x1), 2);
  g[3] = (2.66 * 1e-2 * x3 * x4 * (x2 * x2 * x2 - x1 * x1 * x1)) / (x2 * x2 - x1 * x1) - 900.0;

  for (i = 0; i < numConsts; i++ ) {
    if (g[i] < 0.0) g[i] = -g[i];
    else g[i] = 0;
  }
}

void CRE24(int numObjs, int numVars, int numConsts, double *f, double *x, double *g) {
  int i;
  double x1 = x[0];
  double x2 = x[1];
  double x3 = round(x[2]);
  double x4 = x[3];
  double x5 = x[4];
  double x6 = x[5];
  double x7 = x[6];

  // First original objective function (weight)
  f[0] = 0.7854 * x1 * (x2 * x2) * (((10.0 * x3 * x3) / 3.0) + (14.933 * x3) - 43.0934)
    - 1.508 * x1 * (x6 * x6 + x7 * x7)
    + 7.477 * (x6 * x6 * x6 + x7 * x7 * x7)
    + 0.7854 * (x4 * x6 * x6 + x5 * x7 * x7);
    
  // Second original objective function (stress)
  double tmpVar = pow((745.0 * x4) / (x2 * x3), 2.0)  + 1.69 * 1e7;
  f[1] =  sqrt(tmpVar) / (0.1 * x6 * x6 * x6);

  // Constraint functions 	
  g[0] = -(1.0 / (x1 * x2 * x2 * x3)) + 1.0 / 27.0;
  g[1] = -(1.0 / (x1 * x2 * x2 * x3 * x3)) + 1.0 / 397.5;
  g[2] = -(x4 * x4 * x4) / (x2 * x3 * x6 * x6 * x6 * x6) + 1.0 / 1.93;
  g[3] = -(x5 * x5 * x5) / (x2 * x3 * x7 * x7 * x7 * x7) + 1.0 / 1.93;
  g[4] = -(x2 * x3) + 40.0;
  g[5] = -(x1 / x2) + 12.0;
  g[6] = -5.0 + (x1 / x2);
  g[7] = -1.9 + x4 - 1.5 * x6;
  g[8] = -1.9 + x5 - 1.1 * x7;
  g[9] =  -f[1] + 1300.0;
  tmpVar = pow((745.0 * x5) / (x2 * x3), 2.0) + 1.575 * 1e8;
  g[10] = -sqrt(tmpVar) / (0.1 * x7 * x7 * x7) + 1100.0;
	
  for (i = 0; i < numConsts; i++ ) {
    if (g[i] < 0.0) g[i] = -g[i];
    else g[i] = 0;
  }
}

void CRE25(int numObjs, int numVars, int numConsts, double *f, double *x, double *g) {
  int i;
  // all the four variables must be inverger values
  double x1 = round(x[0]);
  double x2 = round(x[1]);
  double x3 = round(x[2]);
  double x4 = round(x[3]);
   
  // First original objective function
  f[0] = fabs(6.931 - ((x3 / x1) * (x4 / x2)));
  // Second original objective function (the maximum value among the four variables)
  double maxValue = x1;
  if (x2 > maxValue) maxValue = x2;
  if (x3 > maxValue) maxValue = x3;
  if (x4 > maxValue) maxValue = x4;
  f[1] = maxValue;

  g[0] = 0.5 - (f[0] / 6.931);

  for (i = 0; i < numConsts; i++ ) {
    if (g[i] < 0.0) g[i] = -g[i];
    else g[i] = 0;
  }  
}

void CRE31(int numObjs, int numVars, int numConsts, double *f, double *x, double *g) {
  int i;
  double x1 = x[0];
  double x2 = x[1];
  double x3 = x[2];
  double x4 = x[3];
  double x5 = x[4];
  double x6 = x[5];
  double x7 = x[6];
    
  // First original objective function
  f[0] = 1.98 + 4.9 * x1 + 6.67 * x2 + 6.98 * x3 + 4.01 * x4 + 1.78 * x5 + 0.00001 * x6 + 2.73 * x7;
  // Second original objective function
  f[1] = 4.72 - 0.5 * x4 - 0.19 * x2 * x3;
  // Third original objective function
  double Vmbp = 10.58 - 0.674 * x1 * x2 - 0.67275 * x2;
  double Vfd = 16.45 - 0.489 * x3 * x7 - 0.843 * x5 * x6;
  f[2] = 0.5 * (Vmbp + Vfd);

  // Constraint functions
  g[0] = 1 -(1.16 - 0.3717 * x2 * x4 - 0.0092928 * x3);
  g[1] = 0.32 -(0.261 - 0.0159 * x1 * x2 - 0.06486 * x1 -  0.019 * x2 * x7 + 0.0144 * x3 * x5 + 0.0154464 * x6);
  g[2] = 0.32 -(0.214 + 0.00817 * x5 - 0.045195 * x1 - 0.0135168 * x1 + 0.03099 * x2 * x6 - 0.018 * x2 * x7  + 0.007176 * x3 + 0.023232 * x3 - 0.00364 * x5 * x6 - 0.018 * x2 * x2);
  g[3] = 0.32 -(0.74 - 0.61 * x2 - 0.031296 * x3 - 0.031872 * x7 + 0.227 * x2 * x2);
  g[4] = 32 -(28.98 + 3.818 * x3 - 4.2 * x1 * x2 + 1.27296 * x6 - 2.68065 * x7);
  g[5] = 32 -(33.86 + 2.95 * x3 - 5.057 * x1 * x2 - 3.795 * x2 - 3.4431 * x7 + 1.45728);
  g[6] =  32 -(46.36 - 9.9 * x2 - 4.4505 * x1);
  g[7] =  4 - f[1];
  g[8] =  9.9 - Vmbp;
  g[9] =  15.7 - Vfd;

  for (i = 0; i < numConsts; i++ ) {
    if (g[i] < 0.0) g[i] = -g[i];
    else g[i] = 0;
  }
 
}

void CRE32(int numObjs, int numVars, int numConsts, double *f, double *x, double *g) {
  int i;
  double x_L = x[0];
  double x_B = x[1];
  double x_D = x[2];
  double x_T = x[3];
  double x_Vk = x[4];
  double x_CB = x[5];
   
  double displacement = 1.025 * x_L * x_B * x_T * x_CB;
  double V = 0.5144 * x_Vk;
  double pg = 9.8065;
  double Fn = V / pow(pg * x_L, 0.5);
  double a = (4977.06 * x_CB * x_CB) - (8105.61 * x_CB) + 4456.51;
  double b = (-10847.2 * x_CB * x_CB) + (12817.0 * x_CB) - 6960.32;

  double power = (pow(displacement, 2.0/3.0) * pow(x_Vk, 3.0)) / (a + (b * Fn));
  double outfit_weight = 1.0 * pow(x_L , 0.8) * pow(x_B , 0.6) * pow(x_D, 0.3) * pow(x_CB, 0.1);
  double steel_weight = 0.034 * pow(x_L ,1.7) * pow(x_B ,0.7) * pow(x_D ,0.4) * pow(x_CB ,0.5);
  double machinery_weight = 0.17 * pow(power, 0.9);
  double light_ship_weight = steel_weight + outfit_weight + machinery_weight;

  double ship_cost = 1.3 * ((2000.0 * pow(steel_weight, 0.85))  + (3500.0 * outfit_weight) + (2400.0 * pow(power, 0.8)));
  double capital_costs = 0.2 * ship_cost;

  double DWT = displacement - light_ship_weight;

  double running_costs = 40000.0 * pow(DWT, 0.3);

  double round_trip_miles = 5000.0;
  double sea_days = (round_trip_miles / 24.0) * x_Vk;
  double handling_rate = 8000.0;

  double daily_consumption = ((0.19 * power * 24.0) / 1000.0) + 0.2;
  double fuel_price = 100.0;
  double fuel_cost = 1.05 * daily_consumption * sea_days * fuel_price;
  double port_cost = 6.3 * pow(DWT, 0.8);

  double fuel_carried = daily_consumption * (sea_days + 5.0);
  double miscellaneous_DWT = 2.0 * pow(DWT, 0.5);

  double cargo_DWT = DWT - fuel_carried - miscellaneous_DWT;
  double port_days = 2.0 * ((cargo_DWT / handling_rate) + 0.5);
  double RTPA = 350.0 / (sea_days + port_days);

  double voyage_costs = (fuel_cost + port_cost) * RTPA;
  double annual_costs = capital_costs + running_costs + voyage_costs;
  double annual_cargo = cargo_DWT * RTPA;

  f[0] = annual_costs / annual_cargo;
  f[1] = light_ship_weight;
  f[2] = -annual_cargo;  // f_2 is dealt as a minimization problem

  // Reformulated objective functions
  g[0] = (x_L / x_B) - 6.0;
  g[1] = -(x_L / x_D) + 15.0;
  g[2] = -(x_L / x_T) + 19.0;
  g[3] = 0.45 * pow(DWT, 0.31) - x_T;
  g[4] = 0.7 * x_D + 0.7 - x_T;
  g[5] = 500000.0 - DWT;
  g[6] = DWT - 3000.0;
  g[7] = 0.32 - Fn;

  double KB = 0.53 * x_T;
  double BMT = ((0.085 * x_CB - 0.002) * x_B * x_B) / (x_T * x_CB);
  double KG = 1.0 + 0.52 * x_D;
  g[8] = (KB + BMT - KG) - (0.07 * x_B);

  for (i = 0; i < numConsts; i++ ) {
    if (g[i] < 0.0) g[i] = -g[i];
    else g[i] = 0;
  }
}

void CRE51(int numObjs, int numVars, int numConsts, double *f, double *x, double *g) {
  int i;
  // First original objective function
  f[0] = 106780.37 * (x[1] + x[2]) + 61704.67 ;
  // Second original objective function
  f[1] = 3000 * x[0] ;
  // Third original objective function
  f[2] = 305700 * 2289 * x[1] / pow(0.06*2289, 0.65) ;
  // Fourth original objective function
  f[3] = 250 * 2289 * exp(-39.75*x[1]+9.9*x[2]+2.74) ;
  // Fifth original objective function
  f[4] = 25 * (1.39 /(x[0]*x[1]) + 4940*x[2] -80) ;

  // Constraint functions          
  g[0] = 1 - (0.00139/(x[0]*x[1])+4.94*x[2]-0.08)             ;
  g[1] = 1 - (0.000306/(x[0]*x[1])+1.082*x[2]-0.0986)         ;
  g[2] = 50000 - (12.307/(x[0]*x[1]) + 49408.24*x[2]+4051.02) ;
  g[3] = 16000 - (2.098/(x[0]*x[1])+8046.33*x[2]-696.71)      ;
  g[4] = 10000 - (2.138/(x[0]*x[1])+7883.39*x[2]-705.04)      ;
  g[5] = 2000 - (0.417*x[0]*x[1] + 1721.26*x[2]-136.54)       ;
  g[6] = 550 - (0.164/(x[0]*x[1])+631.13*x[2]-54.48) ;
       
  for (i = 0; i < numConsts; i++ ) {
    if (g[i] < 0.0) g[i] = -g[i];
    else g[i] = 0;
  }
}

void getObjectives(char *testProblem, int numObjs, int numVars, int numConsts, double *f, double *x, double *g) {
  if (testProblem == "RE21") RE21(numObjs, numVars, f, x);
  else if (testProblem == "RE22") RE22(numObjs, numVars, f, x);
  else if (testProblem == "RE23") RE23(numObjs, numVars, f, x);
  else if (testProblem == "RE24") RE24(numObjs, numVars, f, x);
  else if (testProblem == "RE25") RE25(numObjs, numVars, f, x);
  else if (testProblem == "RE31") RE31(numObjs, numVars, f, x);
  else if (testProblem == "RE32") RE32(numObjs, numVars, f, x);
  else if (testProblem == "RE33") RE33(numObjs, numVars, f, x);
  else if (testProblem == "RE34") RE34(numObjs, numVars, f, x);
  else if (testProblem == "RE35") RE35(numObjs, numVars, f, x);
  else if (testProblem == "RE36") RE36(numObjs, numVars, f, x);
  else if (testProblem == "RE37") RE37(numObjs, numVars, f, x);
  else if (testProblem == "RE41") RE41(numObjs, numVars, f, x);
  else if (testProblem == "RE42") RE42(numObjs, numVars, f, x);
  else if (testProblem == "RE61") RE61(numObjs, numVars, f, x);
  else if (testProblem == "RE91") RE91(numObjs, numVars, f, x);  
  else if (testProblem == "CRE21") CRE21(numObjs, numVars, numConsts, f, x, g);
  else if (testProblem == "CRE22") CRE22(numObjs, numVars, numConsts, f, x, g);
  else if (testProblem == "CRE23") CRE23(numObjs, numVars, numConsts, f, x, g);
  else if (testProblem == "CRE24") CRE24(numObjs, numVars, numConsts, f, x, g);
  else if (testProblem == "CRE25") CRE25(numObjs, numVars, numConsts, f, x, g);
  else if (testProblem == "CRE31") CRE31(numObjs, numVars, numConsts, f, x, g);
  else if (testProblem == "CRE32") CRE32(numObjs, numVars, numConsts, f, x, g);
  else if (testProblem == "CRE51") CRE51(numObjs, numVars, numConsts, f, x, g);
}

int main(int argc, char **argv) {
  char* testProblem = "RE21";
  printf("%s\n", testProblem);

  int i;    
  int numObjs;
  int numVars;
  int numConsts;
  double *lowerBounds;
  double *upperBounds;

  setNumObjsVars(testProblem, &numObjs, &numVars, &numConsts, lowerBounds, upperBounds);
  
  double f[numObjs];
  double x[numVars];
  double g[numConsts];

  
  lowerBounds = (double *)malloc(numVars * sizeof(double)); 
  upperBounds = (double *)malloc(numVars * sizeof(double)); 
  setBounds(testProblem, numVars, lowerBounds, upperBounds);  
  for (i = 0; i < numVars; i++) x[i] = (upperBounds[i] - lowerBounds[i]) * randUniform() + lowerBounds[i];
   
  getObjectives(testProblem, numObjs, numVars, numConsts, f, x, g);
  
  printf("x = ");
  for (i = 0; i < numVars; i++) printf("%f, ", x[i]);
  printf("\n");
  
  printf("f(x) = ");
  for (i = 0; i < numObjs; i++) printf("%f, ", f[i]);
  printf("\n");

  if (numConsts > 0) {
    printf("g(x) = ");
    for (i = 0; i < numConsts; i++) printf("%f, ", g[i]);
    printf("\n");
  }
  
  return 0;
}
