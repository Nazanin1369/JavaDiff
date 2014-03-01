package org.Nazanin.Diff;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class Diff 
{

	int[][] p;
	ArrayList<String> a;
	ArrayList<String> b ;
	boolean[] aK;
	boolean[] bK;
	
	public Diff()
	{
		this.a = new ArrayList<String>();
		this.b = new ArrayList<String>();
		this.p = new int [a.size()][b.size()];
	}
	
	/*
	 * This method reads two files and put them in arrayLists a and b
	 */
	public void readFile()
	{
		String path1 = "C:/Users/Nazanin/Downloads/file1.txt";
		String path2 = "C:/Users/Nazanin/Downloads/file2.txt";
		String line1;
		String line2;
		
		try
		{
			FileReader fr1 = new FileReader(path1);
			FileReader fr2 = new FileReader(path2);
			BufferedReader reader1 = new BufferedReader(fr1);
			BufferedReader reader2 = new BufferedReader(fr2);
			while ((line1 = reader1.readLine()) != null)
			 {
				line1.trim();
				a.add(line1);
			}
			while((line2 = reader2.readLine()) != null)
			{
				line2.trim();
				b.add(line2);
			}
			reader1.close();
			reader2.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
			
		}
	}
	
	
	/*
	 * This method finds Pij which is the length of the longest common subsequence between two lines.
	 */
	public int findP(int i, int j)
	{
		if( i == 0 || j == 0)
		{
			return 0;
		}
		
		
		if( a.get(i-1).equals(b.get(j-1)))
		{
			if(p[i - 1][j - 1] == 0)
			{
				p[i - 1][j - 1] = findP(i-1, j-1);
				p[i - 1][j - 1]++;
			}
			return 1 + p[i - 1][j - 1] - 1;
		}
		else
		{
			if(p[i - 1][j] == 0)
			{
				p[i - 1][j] = findP(i-1, j);
				p[i - 1][j]++;
			}
			if(p[i][j - 1] == 0)
			{
				p[i][j - 1] = findP(i-1, j-1);
				p[i][j - 1]++;
			}
			return Math.max(p[i - 1][j] - 1 , p[i][j - 1] - 1);
		}
	
	}
	
	/*
	 * This method uses  Hunt–McIlroy algorithm to fill k-candidates.
	 */
	public boolean[][] findK()
	{
		boolean[][] k = new boolean[a.size()][b.size()];
		this.aK = new boolean[a.size()];
		this.bK  = new boolean[b.size()];
		p = new int[a.size() + 1][b.size() + 1];
	
		for(int i = a.size(); i >= 1; i--)
		{
			for(int j = b.size(); j >= 1; j--)
			{
				if(a.get(i-1).equals(b.get(j-1)) && findP(i,j) > Math.max(findP(i-1, j), findP(i, j-1)))
				{
					k[i-1][j-1] = true;
				}
			}
		}
		return k;
		
	}
	/*
	 * This method prints k-candidates.
	 */
	public void printK(boolean[][] k)
	{
		for(int i = 0;i < k.length; i++)
		{
			for(int j = 0; j < k[i].length; j++ )
			{
				if(k[i][j] == true)
				{
					aK[i] = true;
					bK[j] = true;
				}
			}
	    }
	}
	
	/*
	 * This method prints non-matching lines to standard output.
	 */
	public void printFalses()
	{
		int i;
		int j=0;
		System.out.println("\n-----------------------------\nResults:\n");
		for(i = 0; i < aK.length ; i++)
		{
			if(aK[i] == false )
			{
				System.out.println("< " +" "+ a.get(i));
			}
			if( bK[j] == false)
			{
				System.out.println("> "+ " "+ b.get(j));
			}
			j++;
		}
		
		/*
		 * print one remainder of one string if the other is exhausted
		 */
		while(i < aK.length || j < bK.length) {
            if(i == aK.length) 
            	{
            	System.out.println("> " + b.get(j++));
            	}
            else if (j == bK.length) 
            	{
            	System.out.println("< " + a.get(i++));
            	}
         }
		
	}
	/*
	 * This method computes the Levenshtein Distance between lines of the files
	 */
	public void computeLevenshteinDistance()
	{
		LevenshteinDistance ld = new LevenshteinDistance();
		int i;
		int j=0;
		System.out.println("\n---------------------------\nLevenshtein Distance:\n");
		for(i = 0; i < aK.length ; i++)
		{
			if(aK[i] == false )
			{
				 System.out.println("line"+ i + ": "+ ld.computeLD(a.get(i), b.get(j)));
			}
			if( bK[j] == false)
			{
				 System.out.println("line"+ j +": "+ ld.computeLD(a.get(i), b.get(j)));
			}
			j++;
		}
		
		while(i < aK.length || j < bK.length) {
            if(i == aK.length) 
            	{
            	System.out.println("line"+ i + ": "+ ld.computeLD("", b.get(j++)));
            	}
            else if (j == bK.length) 
            	{
            	System.out.println("line"+ i + ": "+ ld.computeLD(a.get(i++),""));
            	}
         }

	
	}
	
}
