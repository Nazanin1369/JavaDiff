package org.Nazanin.Diff;

/**
 * This program represents "diff" functionality. 
 * "Diff" is a file comparison utility that outputs the differences between two files.
 * @version 1.0
 * @author Nazanin
 * @Date January,2014
 *
 */
public class MainClass 
{
	public static void main(String[] args)
	{
		Diff diff = new Diff();
		//read files and put lines in arrayLists a and b
		diff.readFile();
		//Find P and K
		boolean[][] ks = diff.findK();
		diff.printK(ks);
		//Printing false items
		diff.printFalses();	
		//printing Levenshtein distance for each line
		diff.computeLevenshteinDistance();
	}
}
