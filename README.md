# DNA Sequence Comparison using LCS

> **CME 2204 Algorithm Analysis** | Dokuz Eylül University | 2025–2026 Spring

## Overview

Implementation of the **Longest Common Subsequence (LCS)** algorithm using **Dynamic Programming** in Java, applied to real biological DNA sequences. The sequences correspond to an endocrine hormone-related gene region from **Homo sapiens** and **Pan troglodytes** (chimpanzee). A percentage similarity score is computed alongside the full LCS and five distinct shorter common subsequences.

---

## Algorithm

### LCS — Dynamic Programming

Two phases:

**1. Length Computation** — Fill an `(m+1) × (n+1)` table bottom-up:

```
if s1[i-1] == s2[j-1]:  length[i][j] = length[i-1][j-1] + 1
else:                    length[i][j] = max(length[i-1][j], length[i][j-1])
```

LCS length is read from `length[m][n]`.

**2. Direction Matrix** — A parallel `(m+1) × (n+1)` character table records the path taken at each cell:

| Symbol | Meaning |
|--------|---------|
| `D` | Diagonal — character match |
| `U` | Up — came from row above |
| `L` | Left — came from column left |

Back-tracking from `(m, n)` reconstructs the full LCS.

| Complexity | Value |
|------------|-------|
| Time | O(m · n) |
| Space | O(m · n) |
| Reconstruction | O(m + n) |

---

## Project Structure

```
├── src/
│   ├── Main.java                   # Entry point; orchestrates all tasks
│   ├── LongestCommonString.java    # LCS_len(), LCS_str(), printLCS(), print5Subsequence()
│   └── FileOperations.java         # File reading and DNA string merging
├── homosapiens.txt                 # Human DNA sequence (endocrine hormone gene region)
└── chimpanzee.txt                  # Chimpanzee DNA sequence (same gene region)
```

---

## Input Files

Raw sequence data stored as plain-text files (FASTA-derived). Each file contains the nucleotide sequence split across multiple lines. The `loadTxtAndMerge()` method reads each file line by line and concatenates all non-empty lines into a single continuous DNA string.

```java
public static String loadTxtAndMerge(String path) {
    StringBuilder merged = new StringBuilder();
    try (BufferedReader br = new BufferedReader(new FileReader(path))) {
        String line;
        while ((line = br.readLine()) != null) {
            if (!line.isEmpty()) merged.append(line);
        }
    } catch (IOException e) { e.printStackTrace(); }
    return merged.toString();
}
```

---

## How to Run

Place `homosapiens.txt` and `chimpanzee.txt` in the project root, then:

```bash
javac src/*.java -d out/
java -Xss100m -cp out/ Main
```

> ⚠️ The `-Xss100m` flag is required. The recursive `printLCS()` traverses up to `m+n` stack frames on full-length sequences, which overflows the default JVM stack (512 KB).  
> In IntelliJ IDEA: **Run → Edit Configurations → VM options → `-Xss100m`**

---

## Key Methods

### `LCS_len(String s1, String s2)`
Builds and returns the full `(m+1) × (n+1)` DP length table.

### `LCS_str(String s1, String s2, int[][] len)`
Builds the direction matrix (`D` / `U` / `L`) from the length table.

### `printLCS(char[][] LCS, String X, int i, int j)`
Recursively back-tracks through the direction matrix and prints the LCS in correct left-to-right order.

```java
public static void printLCS(char[][] LCS, String X, int i, int j) {
    if (i == 0 || j == 0) return;
    if (LCS[i][j] == 'D') {
        printLCS(LCS, X, i-1, j-1);
        System.out.print(X.charAt(i-1));
    } else if (LCS[i][j] == 'U') printLCS(LCS, X, i-1, j);
    else printLCS(LCS, X, i, j-1);
}
```

### `print5Subsequence(char[][] LCS, String X)`
Calls `printLCS()` five times with randomly offset start coordinates (reduced by a random value in `[0, 4000)`), producing five distinct valid common subsequences each with length ≤ LCS length.

### `similarity()`
```
similarity (%) = (LCS length / max(|s1|, |s2|)) × 100
```

---

## Results & Discussion

### Similarity
The computed similarity between the human and chimpanzee endocrine hormone gene region exceeds **90%**, consistent with the well-established ~98–99% nucleotide identity between the two species. High conservation is expected here — endocrine hormone gene regions encode proteins critical for hormonal signalling, growth, and metabolism, placing them under strong purifying selection.

### Space Optimisations

The current implementation stores two full `O(m·n)` matrices. Possible optimisations for larger inputs:

| Approach | Space | Notes |
|----------|-------|-------|
| Two-row rolling array | O(n) | LCS length only — no reconstruction |
| Hirschberg's algorithm | O(m + n) | Full reconstruction in linear space |
| Bit-parallel (small alphabet) | O(m·n / 64) | ~64× speedup via bitwise ops |
| Sparse DP | O(n · log n) avg | Effective on highly similar sequences |

### Recursion Stack
A `StackOverflowError` occurs with default JVM settings on full-length sequences. A permanent fix is to rewrite `printLCS()` iteratively using an explicit stack, eliminating the need for `-Xss`.

---

## Analysis Questions (Summary)

1. **Time complexity:** O(m·n) — dominated by the two nested loops in `LCS_len()`.
2. **Space complexity:** O(m·n) — two full matrices. Reducible to O(n) for length-only, or O(m+n) with Hirschberg's algorithm.
3. **Why DNA comparison matters:** Evolutionary analysis, gene function prediction, disease mutation detection, drug target identification, forensic profiling.
4. **Human vs. chimpanzee:** High LCS score confirms strong conservation of the endocrine hormone gene region. Multiple valid back-tracking paths yield different common subsequences, reflecting the degeneracy of the LCS solution space.

---

## References

1. The Chimpanzee Sequencing and Analysis Consortium, "Initial sequence of the chimpanzee genome and comparison with the human genome," *Nature*, vol. 437, pp. 69–87, Sep. 2005. [Online](https://www.genome.gov/Pages/Research/DIR/Chimp_Analysis.pdf)
