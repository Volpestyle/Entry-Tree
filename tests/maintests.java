package edu.iastate.cs228.proj4.tests;

import java.util.Arrays;

import edu.iastate.cs228.proj4.Dictionary;
import edu.iastate.cs228.proj4.EntryTree;

/**
 * Driver class for general testing on Project 4.
 * 
 * @author James Volpe
 *
 */
public class maintests
{
	public static void main(String[] args)
	{
		
		Character[] toFind =
			{ 'E', 'N', 'T', 'R', 'Y', 'W' };
			Character[] toFind2 =
			{ 'E', 'N', 'T', 'I', 'P' };
			Character[] toFind3 =
				{ 'E', 'N', 'T', 'I', 'C' };
			String toAdd = "where u enter";
			String toAdd2 = "italian";
			String toAdd3 = "booty";
			EntryTree<Character, String> myEntryTree = new EntryTree<Character, String>();
			myEntryTree.add(toFind, toAdd);
			myEntryTree.add(toFind2, toAdd2);
			myEntryTree.add(toFind3, toAdd3);
			System.out.println(Arrays.deepToString(myEntryTree.getAll()));
	}
}

