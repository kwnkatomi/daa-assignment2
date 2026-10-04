# DAA Assignment 2 — Data Structures

Assignment 2 for the **Design and Analysis of Algorithms** course.

The purpose of this project is to implement fundamental data structures from scratch and analyze their behavior and performance without relying on Java's built-in collection implementations.

## Implemented Data Structures

### DynamicArray

An array-based dynamic data structure storing primitive `int` values.

Implemented operations:

- `add(int value)`
- `add(int index, int value)`
- `remove(int index)`
- `get(int index)`
- `contains(int value)`
- `size()`

The internal array begins with a small capacity and doubles in size whenever it becomes full.

### MyLinkedList

A custom singly linked list implementation using nodes containing primitive `int` values.

Implemented operations:

- `add(int value)`
- `add(int index, int value)`
- `remove(int index)`
- `get(int index)`
- `contains(int value)`
- `size()`

The implementation keeps references to both the head and tail nodes.

### MinHeap

An array-based binary min-heap.

Implemented operations:

- `insert(int value)`
- `peekMin()`
- `extractMin()`

Insertion restores the heap property using **bubble-up**, while extraction restores it using **bubble-down**.

The heap satisfies:

```text
parent <= child

for every node in the structure.
Project Structure
daa-assignment2/
│
├── pom.xml
├── results/
│   ├── plots/
│   └── results.csv
├── scripts/plot_results.py
│
├── src/
│   ├── main/
│   │   └── java/
│   │       └── structures/
│   │           ├── DynamicArray.java
│   │           ├── MyLinkedList.java
│   │           └── MinHeap.java
│   │
│   └── test/
│       └── java/
│           └── structures/
│               ├── DynamicArrayTest.java
│               ├── MyLinkedListTest.java
│               └── MinHeapTest.java
│
├── README.md
└── REPORT.md
```

## Technologies
- Java 17
- Maven
- JUnit 5


### Requirements
To build and test the project, install:
Java 17+
Maven 3+

You can check the installed versions with:
java -version
mvn -version

### Build
Clone the repository:
git clone https://github.com/kwnkatomi/daa-assignment2.git
cd daa-assignment2

Build the project with Maven:
mvn clean package

Run Tests
Run all JUnit tests with:
mvn test

The tests check the main operations of all three custom data structures, including indexed insertion and removal, searching, heap ordering, duplicate heap values, sorted extraction, and invalid operations.
Error Handling
Invalid indices in DynamicArray and MyLinkedList throw:
IndexOutOfBoundsException

Calling heap operations such as peekMin() or extractMin() when the heap is empty throws:
IllegalStateException

## Assignment Goals
This project is designed to compare custom data structures not only through asymptotic complexity, but also through their actual behavior during different workloads.
The full assignment includes:
- implementation of DynamicArray, MyLinkedList, and MinHeap from scratch;
- loop-invariant correctness proofs;
- best-, average-, and worst-case complexity analysis;
- operation counters for steps, moves, and comparisons;
- benchmark workloads for random access, search, insertion/removal, and priority processing;
- experimental results exported to CSV;
- performance plots;
- analysis of cache locality and pointer chasing;
- JUnit 5 correctness and edge-case tests.

## Author
Sara Yermaganbetova / kwnkatomi

## Design and Analysis of Algorithms — Assignment 2