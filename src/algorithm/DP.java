
/**
 * This file contains a recursive implementation of the TSP problem using dynamic programming. The
 * main idea is that since we need to do all n! permutations of nodes to find the optimal solution
 * that caching the results of sub paths can improve performance.
 *
 * <p>For example, if one permutation is: '... D A B C' then later when we need to compute the value
 * of the permutation '... E B A C' we should already have cached the answer for the subgraph
 * containing the nodes {A, B, C}.
 *
 * <p>Time Complexity: O(n^2 * 2^n) Space Complexity: O(n * 2^n)
 *
 * @author Steven & Felix Halim, William Fiset, Micah Stairs
 */
//package com.williamfiset.algorithms.graphtheory;

import java.util.*;

public class DP {

  private final int N;
  private final int START_NODE;
  private final int FINISHED_STATE;

  private int[][] distance;
  private int minTourCost = Integer.MAX_VALUE;

  private List<Integer> tour = new ArrayList<>();
  private boolean ranSolver = false;

  public DP(int[][] distance) {
    this(0, distance);
  }

  public DP(int startNode, int[][] distance) {

    this.distance = distance;
    N = distance.length;
    START_NODE = startNode;

    // Validate inputs.
    if (N <= 2) throw new IllegalStateException("TSP on 0, 1 or 2 nodes doesn't make sense.");
    if (N != distance[0].length)
      throw new IllegalArgumentException("Matrix must be square (N x N)");
    if (START_NODE < 0 || START_NODE >= N)
      throw new IllegalArgumentException("Starting node must be: 0 <= startNode < N");
    if (N > 32)
      throw new IllegalArgumentException(
          "Matrix too large! A matrix that size for the DP TSP problem with a time complexity of"
              + "O(n^2*2^n) requires way too much computation for any modern home computer to handle");

    // The finished state is when the finished state mask has all bits are set to
    // one (meaning all the nodes have been visited).
    FINISHED_STATE = (1 << N) - 1;
  }

  // Returns the optimal tour for the traveling salesman problem.
  public List<Integer> getTour() {
    if (!ranSolver) solve();
    return tour;
  }

  // Returns the minimal tour cost.
  public int getTourCost() {
    if (!ranSolver) solve();
    return minTourCost;
  }

  public void solve() {

    // Run the solver
    int state = 1 << START_NODE;
    Integer[][] memo = new Integer[N][1 << N];
    Integer[][] prev = new Integer[N][1 << N];
    minTourCost = tsp(START_NODE, state, memo, prev);

    // Regenerate path
    int index = START_NODE;
    while (true) {
      tour.add(index);
      Integer nextIndex = prev[index][state];
      if (nextIndex == null) break;
      int nextState = state | (1 << nextIndex);
      state = nextState;
      index = nextIndex;
    }
    tour.add(START_NODE);
    ranSolver = true;
  }

  private int tsp(int i, int state, Integer[][] memo, Integer[][] prev) {

    // Done this tour. Return cost of going back to start node.
    if (state == FINISHED_STATE) return distance[i][START_NODE];

    // Return cached answer if already computed.
    if (memo[i][state] != null) return memo[i][state];

    int minCost = Integer.MAX_VALUE;
    int index = -1;
    for (int next = 0; next < N; next++) {

      // Skip if the next node has already been visited.
      if ((state & (1 << next)) != 0) continue;

      int nextState = state | (1 << next);
      int newCost = distance[i][next] + tsp(next, nextState, memo, prev);
      if (newCost < minCost) {
        minCost = newCost;
        index = next;
      }
    }

    prev[i][state] = index;
    return memo[i][state] = minCost;
  }

  // Example usage:
  public static void main(String[] args) {

    // Create adjacency matrix
	Scanner scanner = new Scanner(System.in);
    int n = 15;
    int[][] distanceMatrix = new int[n][n];
    for (int[] row : distanceMatrix) java.util.Arrays.fill(row, 10000);
    
	for(int i = 0; i < n; i++) {
		for(int j = 0; j < n; j++) {
			distanceMatrix[i][j] = scanner.nextInt();
			System.out.println(distanceMatrix[i][j]);
		}
	}

    // Run the solver
    DP solver = new DP(distanceMatrix);

    // Prints: [0, 3, 2, 4, 1, 5, 0]
    System.out.println("Tour: " + solver.getTour());

    // Print: 42.0
    System.out.println("Tour cost: " + solver.getTourCost());
  }
}