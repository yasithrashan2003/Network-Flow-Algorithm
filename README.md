# Network Flow Algorithm Implementation

This project implements a solution for finding maximum flow in a network using the Ford-Fulkerson algorithm with the Edmonds-Karp improvement.

## Overview

The network flow problem involves finding the maximum amount of flow that can be sent from a source node to a target node in a directed graph with capacity constraints on each edge. This implementation handles integer capacities and follows the conservation of flow principle at each node.

## Project Structure

```
NetworkFlowAlgorithm/
├── src/
│   └── com/
│       └── network/
│           ├── model/              # Data structures
│           │   ├── Edge.java       # Represents edges with capacity and flow
│           │   ├── FlowNetwork.java # The network graph representation
│           │   └── Node.java       # Represents nodes in the network
│           ├── algorithm/          # Algorithms
│           │   └── MaxFlowAlgorithm.java # Implementation of max flow algorithm
│           ├── io/                 # Input/Output functionality
│           │   └── NetworkParser.java # Parses network from input files
│           └── Main.java           # Entry point of the application
└── data/                           # Input data
    └── sample_network.txt          # Sample network file
```

## How to Run

1. In IntelliJ IDEA:
   - Open the project
   - Right-click on Main.java and select "Run Main.main()"
   - Configure program arguments in Run Configuration to include the path to your network file

2. From command line (after building):
   ```
   javac -d bin src/com/network/*.java src/com/network/*/*.java
   java -cp bin com.network.Main data/sample_network.txt
   ```

## Input File Format

The input file should follow this format:
- First line: Number of nodes n (nodes are numbered 0 to n-1)
- Following lines: Edge definitions as "from to capacity"
- Node 0 is the source, and node n-1 is the target

Example:
```
4
0 1 6
0 2 4
1 2 2
1 3 3
2 3 5
```

## Implementation Details

This implementation uses:
- A graph representation with nodes and directed edges
- The Ford-Fulkerson algorithm with Edmonds-Karp improvement (BFS to find augmenting paths)
- Integer capacities and flows
- Detailed logging of the algorithm execution

## Algorithm Explanation

The Ford-Fulkerson algorithm works by repeatedly finding augmenting paths from source to target and pushing flow along these paths. The Edmonds-Karp improvement uses BFS to find these paths, which guarantees an O(VE²) time complexity.

The implementation:
1. Starts with zero flow
2. Repeatedly finds augmenting paths using BFS
3. Determines the bottleneck capacity on each path
4. Augments the flow by the bottleneck value
5. Continues until no more augmenting paths can be found

## Performance Analysis

- Time Complexity: O(VE²) where V is the number of vertices and E is the number of edges
- Space Complexity: O(V + E) for storing the graph and algorithm data structures
