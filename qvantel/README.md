# Qvantel Technical Assessment Coding Questions

This directory contains coding questions and pattern problems from Qvantel technical assessments.

---

## 📌 Questions Overview

| # | Problem | Core Technique | Solution File |
|---|---|---|---|
| 1 | **Palindromic Number Diamond Pattern** (Assignment Question) | Symmetric sequence generation (`1..k..1`) with multi-token standard input parsing | [`AssignmentCodingQuestion.py`](AssignmentCodingQuestion.py) |
| 2 | **Centered Palindromic Number Diamond** (Related Question) | Space-padded symmetric diamond centering (`(N-k)` leading spaces + `1..k..1`) | [`CenteredPalindromicDiamond.py`](CenteredPalindromicDiamond.py) |
| 3 | **Palindromic Alphabet Diamond Pattern** (Practice Question) | Alphabet transformation (`chr/ord`), centering indentation, and dual numeric/char token parsing | [`PalindromicAlphabetDiamond.py`](PalindromicAlphabetDiamond.py) |

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

## 📝 Problem 3: Palindromic Alphabet Diamond Pattern

### Problem Statement
Given an integer $N$ or a character $C$ (such as `'C'` representing 3), print a centered symmetric alphabet diamond where:
- The peak characters ascend from `'A'` to the $N$-th letter, then descend back to `'A'`.
- Each row is centered using $(N - k)$ spaces.

### Sample Input & Output
**Input:**
```
3
```
*or*
```
C
```
**Output:**
```
  A
 ABA
ABCBA
 ABA
  A
```

---

## 🚀 How to Run

```bash
# Run Assignment Question with input
echo 3 | python qvantel/AssignmentCodingQuestion.py

# Run Centered Number Diamond Question with input
echo 3 | python qvantel/CenteredPalindromicDiamond.py

# Run Palindromic Alphabet Diamond Question with input
echo 3 | python qvantel/PalindromicAlphabetDiamond.py
# Or with character input
echo C | python qvantel/PalindromicAlphabetDiamond.py
```
