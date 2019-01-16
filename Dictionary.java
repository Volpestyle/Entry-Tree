package edu.iastate.cs228.proj4;

import java.util.*;

import java.io.*;

/**
 * @author James Volpe
 * 
 * 
 * An application class that uses EntryTree class to process a file of
 * commands (one per line). Each command line consists of the name of
 * a public method of the EntryTree class followed by its arguments in
 * string form if the method has arguments. The name of the file is 
 * available to the main method from its String[] parameter at index 0.
 * You can assume that the command file is always in correct format. The 
 * main method creates an object of the EntryTree class with K being 
 * Character and V being String, reads each line from the command file, 
 * decodes the line by splitting into String parts, forms the corresponding 
 * arguments, and calls the public method from the EntryTree object with 
 * the arguments, and prints out the result on the console. Note that the 
 * name of a public method in the EntryTree class on each command line 
 * specifies that the public method should be called from the EntryTree 
 * object. A sample input file of commands and a sample output file are 
 * provided. 
 * 
 * The sample output file was produced by redirecting the console output
 * to a file, i.e.,
 * 
 * java Dictionary infile.txt > outfile.txt
 *  
 * 
 * NOTE that all methods of EntryTree class can be present as commands
 * except for getAll method inside the input file.
 * 
 * 
 */
public class Dictionary 
{
 public static void main(String[] args) throws FileNotFoundException
 {
	 EntryTree<Character, String> newEntryTree = new EntryTree<Character, String>();
	 File inputFile = null;
	 Scanner fileScanner = null;
	 try {
		 if (args == null || args.length < 1) {
			System.out.println("Insufficient arguments. You must provide an input file");
			return;
		} else {
			inputFile = new File(args[0]);
			fileScanner = new Scanner(inputFile);
		}
	 }
	 catch (FileNotFoundException e){
		 System.out.println("Bad path, try again");
		 e.printStackTrace();
	 }
	 
	 while (fileScanner.hasNextLine()) {
		 String line = fileScanner.nextLine(); 
		 String[] lineAsArray = line.split("\\s+"); // split
			
		 if (lineAsArray[0].equalsIgnoreCase("showTree")) {
			System.out.println("Command: " + lineAsArray[0]);
			System.out.println();
			System.out.println("Result from a showTree:");
			newEntryTree.showTree();
		 }
			
		 else if (lineAsArray[0].equalsIgnoreCase("add")) {
			System.out.println("Command: " + lineAsArray[0] + " " + lineAsArray[1] + " " + lineAsArray[2]);
			System.out.println("Result from an add: " + newEntryTree.add(toCharacterArray(lineAsArray[1]), lineAsArray[2]));
		 }
			
		 else if (lineAsArray[0].equalsIgnoreCase("search")) {
			System.out.println("Command: " + lineAsArray[0] + " " + lineAsArray[1]);
			System.out.println("Result from a search: " + newEntryTree.search(toCharacterArray(lineAsArray[1])));
		 } 
			
		else if (lineAsArray[0].equalsIgnoreCase("prefix")) {	
			System.out.println("Command: " + lineAsArray[0] + " " + lineAsArray[1]);
			System.out.print("Result from a prefix: ");
			Character[] characterArray = newEntryTree.prefix(toCharacterArray(lineAsArray[1]));
			for (int i = 0; i < characterArray.length; i++) {
				System.out.print(characterArray[i]);
			}
			System.out.println();	
		} 
		
		else if (lineAsArray[0].equalsIgnoreCase("remove")) {
			System.out.println("Command: " + lineAsArray[0] + " " + lineAsArray[1]);
			System.out.println("Result from a remove: " + newEntryTree.remove(toCharacterArray(lineAsArray[1])));
		} 
		 
		System.out.println();

	 }
	fileScanner.close();
 }
 
 /**
  * helper method to convert string to character objects 
  * PS: I got it from stack overflow
  * @param s String to be converted
  * @return Character Object array
  */
 private static Character[] toCharacterArray(String s) {
	   if ( s == null ) {
	     return null;
	   }
	   int len = s.length();
	   Character[] array = new Character[len];
	   for (int i = 0; i < len ; i++) {
	      array[i] = new Character(s.charAt(i));
	   }
	   return array;
	}
}
