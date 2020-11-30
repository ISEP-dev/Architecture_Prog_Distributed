#Part 1 :
### - Question 1 : 
  - Step 1 : (1, 2, 3)
  - Step 2 : (1, 2, 3)
  - Step 3 : (1, 2, 3)

The nodes reach an agreement because there is no faulty process

### - Question 2 :
  - No, the nodes didn't reach an agreement because there is not a majority of 1, 2 or 3  
  - In that case, we need at least another non-faulty node
  - 2k + 1 non-faulty nodes are require, for a total of 3k + 1 
  
### - Question 3 : 
(`n` is the number of processes)
 - Best case scenario : `n-1` messages 
 - Worst case scenario : `2*(n-1) + Sum(n-(i+1)) + (n-2)` messages

