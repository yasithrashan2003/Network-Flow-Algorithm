# Network Flow Algorithm

This repository contains an implementation of a maximum flow algorithm for directed networks. The project is developed as part of a coursework assignment for algorithms and data structures.

## Overview

The implementation solves the maximum flow problem in a network - finding the largest possible flow from a source node to a sink node, subject to edge capacity constraints.

### Features

- Graph data structure representation of flow networks
- Parser for network description files
- Implementation of the [algorithm name] algorithm for finding maximum flow
- Detailed output of algorithm steps and final solution

## Input Format

The program accepts input files with the following format:

```
n               // Number of nodes (n). Nodes are numbered 0 to n-1
a b c           // Edge from node a to node b with capacity c
...
```

Node 0 is always the source, and node n-1 is always the target/sink.

## Usage

```
java -jar NetworkFlow.jar input.txt
```

## Implementation Details

- **Data Structure**: [Brief description of your chosen data structure]
- **Algorithm**: [Name and brief description of the algorithm implemented]
- **Time Complexity**: O([Your complexity])

## Project Structure

```
src/
├── main/
│   ├── java/
│   │   ├── model/
│   │   │   ├── Graph.java
│   │   │   ├── Edge.java
│   │   │   └── Node.java
│   │   ├── algorithm/
│   │   │   └── MaxFlow.java
│   │   ├── parser/
│   │   │   └── NetworkParser.java
│   │   └── Main.java
└── test/
    └── java/
        └── [Test files]
```

## Requirements

- Java 11 or higher
- Maven (for dependency management and building)

## Building

```
mvn clean package
```

## License

This project is an academic coursework submission and is not licensed for public use or distribution.

## Author

Yasith Rashan
