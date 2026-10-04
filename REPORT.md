# Assignment 2: Data Structures

## 1. Complexity Analysis


| Structure | Operation | Best | Average | Worst | Auxiliary space | Justification |
|---|---|---:|---:|---:|---:|---|
| DynamicArray | Indexed access | Θ(1) | Θ(1) | Θ(1) | O(1) | Direct array access |
| MyLinkedList | Indexed access | Θ(1) | Θ(n) | Θ(n) | O(1) | Must traverse nodes to reach the index |
| MinHeap | Insertion | Θ(1) | Θ(log n)* | Θ(log n) | O(1) | May bubble up the heap |
| MinHeap | `extractMin` | Θ(1) | Θ(log n) | Θ(log n) | O(1) | May bubble down the heap |


## 2. Loop Invariant Proofs

### 2.1 DynamicArray indexed insertion

Invariant: At the start of the iteration with index i, the original elements from positions i through size - 1 have already been copied one position to the right. The elements before i remain in their original positions.
- Initialization: At the first iteration, i == size, so there are no elements in that range yet. The invariant holds.
- Maintenance: The loop copies the element at i - 1 to i, then decreases i. That extends the shifted range by one element.
- Termination: The loop stops when i == index. All original elements from index onward have been shifted right, leaving index open.
- Conclusion: Writing value at index inserts it in the correct place and preserves the order of the other elements.

### 2.2 MinHeap bubble-down

Invariant: At the start of each iteration, the only possible heap-order violation is at the current index. Its child subtrees are valid min-heaps, and the rest of the heap satisfies the min-heap property.
- Initialization: After moving the last element to the root, the root may violate the heap property, but its child subtrees remain valid heaps.
- Maintenance: The loop compares the current node with its smaller child. If it swaps them, the smaller value moves up, and any remaining violation follows the larger value down to the new current index.
- Termination: The loop stops when the current node has no children or is no larger than its children. The heap property then holds at that position and throughout the heap.
- Conclusion: The returned value is the former minimum, and the remaining elements satisfy the min-heap property.
- 
## 3. Benchmark Method

Test sizes: 100, 1,000, 10,000, and 100,000; generated data uses seed 42.
W1: 10,000 indexed lookups at random valid positions.
W2: 1,000 searches: 500 values from the data and 500 absent values; shuffled before searching.
W3: 1,000 insertions, then 1,000 removals, at the head or at index n / 2.
W4: Insert all n values into the min-heap, then extract them all; check that extraction is sorted.
Each benchmark does one warm-up and five timed runs; the median time is reported. The CSV includes time and operation counters.

## 4. Results

### W1: Random Access

![W1 time](results/plots/W1_time.png)

![W1 operation counts](results/plots/W1_operations.png)

### W2: Search

![W2 time](results/plots/W2_time.png)

![W2 operation counts](results/plots/W2_operations.png)

### W3: Insert and Remove

![W3 time](results/plots/W3_time.png)

![W3 operation counts](results/plots/W3_operations.png)

### W4: Priority Processing

![W4 time](results/plots/W4_time.png)

![W4 operation counts](results/plots/W4_operations.png)



