# Assignment 2 — DNA Sequence Comparison using LCS

> **CME 2204 Algorithm Analysis** | 2025–2026 Spring

## Overview

This project implements the **Longest Common Subsequence (LCS)** algorithm using **Dynamic Programming** in Java, applied to real biological DNA sequences from human and chimpanzee genomes. The sequences correspond to an endocrine hormone related gene region.

## Background

DNA sequences are composed of four nucleotide bases: **A**, **T**, **C**, **G**. The LCS algorithm finds the longest sequence that appears in both strings in the same relative order (not necessarily contiguous). This technique is widely used in **bioinformatics** and **evolutionary analysis** to measure genetic similarity between organisms.

## Tasks

1. **Parse** `.txt` files and extract the DNA sequences.
2. **Merge** each sequence into one continuous string by removing line breaks (done for both human and chimpanzee sequences).
3. **Implement** the LCS algorithm using Dynamic Programming.
4. **Reconstruct and print** one complete LCS.
5. **Find and print** at least 5 different common subsequences (each with length ≤ LCS length).
6. **Answer** all analysis questions with clear explanations.

## Input Files

| File | Description |
|------|-------------|
| `human_dna.txt` | Human DNA sequence (endocrine hormone gene region) |
| `chimp_dna.txt` | Chimpanzee DNA sequence (same gene region) |

## How to Run

```bash
javac Main.java
java Main human_dna.txt chimp_dna.txt
```

## Algorithm — Dynamic Programming LCS

```
Given sequences X (length m) and Y (length n):

Build an (m+1) x (n+1) table dp where:
  dp[i][j] = length of LCS of X[0..i-1] and Y[0..j-1]

Fill rule:
  if X[i-1] == Y[j-1]:  dp[i][j] = dp[i-1][j-1] + 1
  else:                  dp[i][j] = max(dp[i-1][j], dp[i][j-1])

Backtrack from dp[m][n] to reconstruct the LCS string.
```

**Time Complexity:** O(m × n)  
**Space Complexity:** O(m × n) — can be optimized to O(min(m, n))

## Analysis Questions

1. What is the time complexity of your LCS implementation? Justify your answer.
2. What is the space complexity? Discuss any possible optimizations.
3. Why is DNA sequence comparison important in biology and medicine?
4. What observations can you make about the similarity between the human and chimpanzee sequences?

## Grading

| Component | Points |
|-----------|--------|
| Dynamic Programming Implementation | 35 |
| LCS Reconstruction | 20 |
| `.txt` File Parsing | 10 |
| Multiple Common Subsequences | 15 |
| Code Quality | 10 |
| Analysis Questions | 10 |
| **Total** | **100** |
