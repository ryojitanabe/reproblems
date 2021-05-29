#  An Easy-to-use Real-world Multi-objective Optimization Problem Suite

This repository provides the Java ([jMetal 4.5](http://jmetal.sourceforge.net/)), C, Matlab/Octave, and Python implementations of the (at least not synthetic) real-world (RE) problems presented in the following paper:

> Ryoji Tanabe and Hisao Ishibuchi, **An easy-to-use real-world multi-objective optimization problem suite**, Applied Soft Computing, Vol. 89, pp. 106078 (2020), [link](https://www.sciencedirect.com/science/article/pii/S1568494620300181), [pdf](https://arxiv.org/abs/2009.12867), [supplementary-pdf](https://github.com/ryojitanabe/reproblems/blob/master/doc/re-supplementary_file.pdf)

In any implementation, the objective values of a randomly generated solution on the RE21 problem are shown in the default setting. For the sake of simplicity, the name of each RE problem does not include the number of decision variables. For example, RE2-4-1 is denoted as RE21 in the source code. The supplementary file describes details of the RE problems. This repository also provides data of approximated Pareto fronts of the problems.

The RE problem set is also available in the last stable version of jMetal ([jMetal 5.10](https://github.com/jMetal/jMetal)).

## Change log (YY.MM.DD)

* [2021.5.29] [Issue \#8](https://github.com/ryojitanabe/reproblems/issues/8):  A bug in the code of RE42 and CRE32 has been fixed. The sixth constraint function in RE42 and CRE32 was fixed by replacing ``50000.0 - DWT`` with ``500000.0 - DWT``. A typo in the supplementary file has also been fixed.

* [2021.5.28] [Issue \#7](https://github.com/ryojitanabe/reproblems/issues/7):  Typos in the supplementary file have been fixed.

* [2020.12.29] [Issue \#6](https://github.com/ryojitanabe/reproblems/issues/6):  Typos in the supplementary file have been fixed. 

* [2020.11.24] [Issue \#5](https://github.com/ryojitanabe/reproblems/issues/5):  Typos in the supplementary file have been fixed. 

* [2020.11.23] [Issue \#4](https://github.com/ryojitanabe/reproblems/issues/4):  A bug in the Matlab code of RE21 has been fixed. The lower bounds for the second and third variables in RE21 were fixed by replacing ``sqrt(tmpVar)`` with ``sqrt(2)*tmpVar``.

* [2020.11.1] A Python implementation of the RE problem has been released.

* [2020.11.1] A bug in the C code of RE24 has been fixed. The upper bound for the second variable in RE24 was fixed by replacing ``upperBounds[0] = 50;`` with ``upperBounds[1] = 50;``.

* [2020.10.2] [Issue \#2](https://github.com/ryojitanabe/reproblems/issues/2) and [\#3](https://github.com/ryojitanabe/reproblems/pull/3): Bugs in the source code of RE21 have been fixed. In the second objective function in RE21, ``(2.0 / x2)`` was replaced with ``(2.0 / x1)``. The data of approximated Pareto fronts of RE21 and RE25 have been updated.

* [2020.8.12] [Issue \#1](https://github.com/ryojitanabe/reproblems/issues/1): Bugs in the source code of RE21 and RE25 have been fixed. In RE21, ``E = 2 * 10e5`` was replaced with ``E = 2 * 1e5``. Also, in RE25, ``G = 11.5 * 10e+6`` was replaced with ``G = 11.5 * 1e+6``. It should be noted that the bug-fixed versions of RE21 and RE25 are consistent with their original definitions but do not reproduce the results shown in the Applied Soft Computing paper. [2020.10.2] ~~The data of approximated Pareto fronts of RE21 and RE25 are also incorrect.~~ The data of approximated Pareto fronts of RE21 and RE25 have been updated.
