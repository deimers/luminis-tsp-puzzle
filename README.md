## TSPSolver

A solver for the traveling salesman problem (TSP) written in Groovy.

### About the Problem

TSP is classic algorithmic problem in computer science.

TSP has several applications, but typically formulated as a problem of logisitics:

* Given a list of cities and their pairwise distances, find a shortest possible tour that visits each city exactly once.

### About the Solution

TSP is most easily expressed as a weighted graph of segments (edges) that represent the length (weight) between cities (nodes).

A brute-force approach would be to try all permutations (combinations), check for their validity (does such a route exist?) and sort by the sum of their lengths. However, since the number of existing combinations equals the *factorial of the number of cities* the running time of such a solver on moderate hardware becomes problematic quickly. 

Note: 5! = 120, 10! = 3628800, 15! = 1.3076744e+12

This solver applies a backtracking (branch&bound) approach to explore the space (segments) to find valid routes first, then sorts by their length.

## Run

    groovy TSPSolver.groovy
