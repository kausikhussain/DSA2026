# Qvantel Technical Assessment Coding Questions

This directory contains coding questions and pattern problems from Qvantel technical assessments.

---

## 📌 Questions Overview

| # | Problem | Core Technique | Solution File |
|---|---|---|---|
| 1 | **Palindromic Number Diamond Pattern** (Assignment Question) | Symmetric sequence generation (`1..k..1`) with multi-token standard input parsing | [`AssignmentCodingQuestion.py`](AssignmentCodingQuestion.py) |
| 2 | **Centered Palindromic Number Diamond** (Related Question) | Space-padded symmetric diamond centering (`(N-k)` leading spaces + `1..k..1`) | [`CenteredPalindromicDiamond.py`](CenteredPalindromicDiamond.py) |

---

## 📝 Problem 1: Palindromic Number Diamond Pattern

### Problem Statement
Given an integer $N$, print a diamond pattern of numbers where:
- The rows increase in peak value from $1$ up to $N$, and then decrease from $N-1$ down to $1$.
- Each row $k$ consists of numbers from $1$ up to $k$, followed by numbers from $k-1$ down to $1$ (left-aligned).

### Sample Input & Output
**Input:**
```
3
```
**Output:**
```
1
121
12321
121
1
```

---

## 📝 Problem 2: Centered Palindromic Number Diamond Pattern

### Problem Statement
Given an integer $N$, print a centered symmetric diamond pattern of numbers where:
- Each row $k$ (with peak from $1 \dots N \dots 1$) is indented with $(N - k)$ spaces.
- Row $k$ prints numbers ascending from $1$ up to $k$, then descending from $k-1$ down to $1$.

### Sample Input & Output
**Input:**
```
3
```
**Output:**
```
  1
 121
12321
 121
  1
```

---

## 🚀 How to Run

```bash
# Run Assignment Question with input
echo 3 | python qvantel/AssignmentCodingQuestion.py

# Run Centered Diamond Question with input
echo 3 | python qvantel/CenteredPalindromicDiamond.py
```
