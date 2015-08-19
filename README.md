## JavaDiff

This program represents "diff" functionality. 
"Diff" is a file comparison utility that outputs the differences between two files. 

@version 1.0

#### How it works:
  1- reads two files and put them in arrayLists a and b
  2- For each line in a file it finds Pij which is the length of the longest common subsequence between two lines.
  3- uses  Hunt–McIlroy algorithm to fill k-candidates.
  4- prints k-candidates.
  5- prints non-matching lines to standard output.
  6- computes the Levenshtein Distance between lines of the files.
  
#### How to run: 
  * change the path of the files to your files path in Diff.java
    javac *.java
    java  MainClass.java
