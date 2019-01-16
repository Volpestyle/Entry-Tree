package edu.iastate.cs228.proj4.tests;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.FileNotFoundException;
import java.util.Arrays;

import org.junit.Test;

import edu.iastate.cs228.proj4.Dictionary;
import edu.iastate.cs228.proj4.EntryTree;

/**
 * JUnits for this project.
 * 
 * @author James Volpe
 *
 */
public class TestEntryTree
{
	@Test
	public void main() { //InFile tests. This tests for showTree();
	try {
		Dictionary.main(new String[] {"src/edu/iastate/cs228/proj4/tests/infile.txt"});
		Dictionary.main(new String[] {"src/edu/iastate/cs228/proj4/tests/infile2.txt"});
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	
	@Test
	public void searchTest1() { //return correct value
		Character[] chars1 = { 'E', 'N', 'T', 'R', 'Y' };
		Character[] chars2 = { 'E', 'N', 'T', 'R', 'O', 'P', 'Y' };
		Character[] chars3 = { 'E', 'N', 'T', 'R', 'I'};
		String str1 = "where u enter";
		String str2 = "physics";
		String str3 = "italian";
		EntryTree<Character, String> myEntryTree = new EntryTree<Character, String>();
		myEntryTree.add(chars1, str1);
		myEntryTree.add(chars2, str2);
		myEntryTree.add(chars3, str3);
		assertEquals(str1, myEntryTree.search(chars1));
		assertEquals(str2, myEntryTree.search(chars2));
		assertEquals(str3, myEntryTree.search(chars3));
	}
	
	@Test
	public void searchTest2() { //return null
		Character[] chars1 = { 'E', 'N', 'T', 'R', 'Y' };
		Character[] chars2 = { 'E', 'N', 'T', 'R', 'O', 'P', 'Y' };
		Character[] chars3 = { 'E', 'N', 'T', 'R', 'I'};
		String str1 = "where u enter";
		String str2 = "idk";
		String str3 = "italian";
		EntryTree<Character, String> myEntryTree = new EntryTree<Character, String>();
		myEntryTree.add(chars1, str1);
		myEntryTree.add(chars2, str2);
		assertEquals(null, myEntryTree.search(chars3));
	}
	
	@Test
	public void searchTest3_Exceptions() { //exceptions
		Character[] chars1 = { 'E', null, 'T', 'R', 'Y' };
		Character[] chars2 = { 'E', 'N', 'T', 'R', 'Y' };
		String str1 = "where u enter";
		EntryTree<Character, String> myEntryTree = new EntryTree<Character, String>();
		myEntryTree.add(chars2, str1);
		boolean thrown = false;
		try {
			myEntryTree.search(chars1);
		} catch (NullPointerException e) {
			thrown = true;
		}
		assertTrue(thrown);
	}
	
	@Test
	public void prefixTest1() { 
		Character[] chars1 = { 'E', 'N', 'T', 'R', 'Y' };
		Character[] chars2 = { 'E', 'N', 'T', 'R', 'O', 'P', 'Y' };
		Character[] chars3 = { 'E', 'N', 'T', 'R', 'I'};
		Character[] prefix = { 'E', 'N', 'T', 'R', 'A', 'N', 'C', 'E'};
		Character[] expected = { 'E', 'N', 'T', 'R' };
		String str1 = "where u enter";
		String str2 = "idk";
		String str3 = "italian";
		EntryTree<Character, String> myEntryTree = new EntryTree<Character, String>();
		myEntryTree.add(chars1, str1);
		myEntryTree.add(chars2, str2);
		myEntryTree.add(chars3, str3);
		assertArrayEquals(expected, myEntryTree.prefix(prefix));
	}
	
	@Test
	public void prefixTest2_Exceptions() {
		Character[] chars1 = { 'E', 'N', 'T', 'R', 'Y' };
		Character[] chars2 = { 'E', 'N', 'T', 'R', 'O', 'P', 'Y' };
		Character[] chars3 = { 'E', 'N', 'T', 'R', 'I'};
		Character[] prefix = { 'E', null, 'T', 'R', 'Y' };
		String str1 = "where u enter";
		String str2 = "idk";
		String str3 = "italian";
		EntryTree<Character, String> myEntryTree = new EntryTree<Character, String>();
		myEntryTree.add(chars1, str1);
		myEntryTree.add(chars2, str2);
		myEntryTree.add(chars3, str3);
		boolean thrown = false;
		try {
			myEntryTree.prefix(prefix);
		} catch (NullPointerException e) {
			thrown = true;
		}
		assertTrue(thrown);
	}
	
	@Test
	public void addTest1() //Make sure all nodes have correct relation to one another
	{
		Character[] chars1 = { 'E', 'N', 'T', 'R', 'Y' };
		Character[] chars2 = { 'E', 'N', 'T', 'R', 'O', 'P', 'Y' };
		Character[] chars3 = { 'E', 'N', 'T', 'R', 'I'};
		String str1 = "where u enter";
		String str2 = "idk";
		String str3 = "italian";
		EntryTree<Character, String> myEntryTree = new EntryTree<Character, String>();
		myEntryTree.add(chars1, str1);
		myEntryTree.add(chars2, str2);
		myEntryTree.add(chars3, str3);
		assertEquals(null, myEntryTree.root.key);
		assertEquals(chars1[0], myEntryTree.root.child.key);
		assertEquals(chars1[1], myEntryTree.root.child.child.key);
		assertEquals(chars1[2], myEntryTree.root.child.child.child.key);
		assertEquals(chars1[3], myEntryTree.root.child.child.child.child.key);
		assertEquals(chars1[4], myEntryTree.root.child.child.child.child.child.key);
		assertEquals(str1, myEntryTree.root.child.child.child.child.child.value); //test for value
		assertEquals(chars2[4], myEntryTree.root.child.child.child.child.child.next.key);
		assertEquals(chars3[4], myEntryTree.root.child.child.child.child.child.next.next.key);
		assertEquals(chars2[5], myEntryTree.root.child.child.child.child.child.next.child.key);
		assertEquals(chars2[6], myEntryTree.root.child.child.child.child.child.next.child.child.key);
	}
	
	@Test
	public void addTest2() { //accepts new value for same key
		Character[] chars1 = { 'E', 'N', 'T', 'R', 'Y' };
		String str1 = "where u enter";
		String str2 = "idk";
		EntryTree<Character, String> myEntryTree = new EntryTree<Character, String>();
		myEntryTree.add(chars1, str1);
		assertEquals(str1, myEntryTree.root.child.child.child.child.child.value);
		myEntryTree.add(chars1, str2);
		assertEquals(str2, myEntryTree.root.child.child.child.child.child.value); 
	}
	
	@Test
	public void addTest3_Exceptions() {
		Character[] chars1 = { 'E', 'N', 'T', 'R', 'Y' };
		Character[] chars2 = { 'E', 'N', 'T', 'R', 'O', 'P', 'Y' };
		Character[] chars3 = { 'E', null, 'T', 'R', 'Y' };
		String str1 = "where u enter";
		String str2 = "idk";
		String str3 = "italian";
		EntryTree<Character, String> myEntryTree = new EntryTree<Character, String>();
		myEntryTree.add(chars1, str1);
		myEntryTree.add(chars2, str2);
		boolean thrown = false;
		try {
			myEntryTree.add(chars3, str3);
		} catch (NullPointerException e) {
			thrown = true;
		}
		assertTrue(thrown);
	}
	
	@Test
	public void removeTest1() { //sibling becomes new child
		Character[] chars3 = { 'E', 'N', 'T', 'R', 'I'};
		Character[] chars2 = { 'E', 'N', 'T', 'R', 'Y'};
		String str2 = "where u enter";
		String str3 = "italian";
		EntryTree<Character, String> myEntryTree = new EntryTree<Character, String>();
		myEntryTree.add(chars3, str3);
		myEntryTree.add(chars2, str2);
		assertEquals(chars3[4], myEntryTree.root.child.child.child.child.child.key);
		assertEquals(str3, myEntryTree.root.child.child.child.child.child.value);
		myEntryTree.remove(chars3);
		//after first removal, value is null
		assertEquals(null, myEntryTree.root.child.child.child.child.child.value);
		myEntryTree.remove(chars3);
		//after remove of I, Y's sibling, Y should become the new child of R.
		assertEquals(chars2[4], myEntryTree.root.child.child.child.child.child.key); 
	}
	
	@Test
	public void removeTest2() { //only-child leaf is removed, causing parent to also be removed
		Character[] chars3 = { 'E', 'N', 'T', 'R', 'I'};
		Character[] chars2 = { 'E', 'N', 'T', 'E', 'R'};
		String str2 = "keyboard";
		String str3 = "italian";
		EntryTree<Character, String> myEntryTree = new EntryTree<Character, String>();
		myEntryTree.add(chars3, str3);
		myEntryTree.add(chars2, str2);
		assertEquals(chars3[4], myEntryTree.root.child.child.child.child.child.key);
		assertEquals(str3, myEntryTree.root.child.child.child.child.child.value);
		myEntryTree.remove(chars3);
		//after first removal, value is null
		assertEquals(null, myEntryTree.root.child.child.child.child.child.value);
		myEntryTree.remove(chars3);
		//after remove of I, R will dissapear too, making E the child of T
		assertEquals(chars2[3], myEntryTree.root.child.child.child.child.key); 
	}
	
	@Test
	public void removeTest3() { //chain-reaction
		Character[] chars2 = { 'E', 'N', 'T', 'E', 'R'};
		String str2 = "keyboard";
		EntryTree<Character, String> myEntryTree = new EntryTree<Character, String>();
		myEntryTree.add(chars2, str2);
		myEntryTree.remove(chars2);
		//after first removal, value is null
		assertEquals(null, myEntryTree.root.child.child.child.child.child.value);
		myEntryTree.remove(chars2);
		//when R is removed, all nodes will be removed because of chain reaction
		assertEquals(null, myEntryTree.root.child);
	}
	
	@Test
	public void removeTest4_Exceptions() {
		Character[] chars1 = { 'E', 'N', 'T', 'R', 'Y' };
		Character[] chars2 = { 'E', 'N', 'T', 'R', 'O', 'P', 'Y' };
		Character[] chars3 = { 'E', null, 'T', 'R', 'Y' };
		String str1 = "where u enter";
		String str2 = "idk";
		EntryTree<Character, String> myEntryTree = new EntryTree<Character, String>();
		myEntryTree.add(chars1, str1);
		myEntryTree.add(chars2, str2);
		boolean thrown = false;
		try {
			myEntryTree.remove(chars3);
		} catch (NullPointerException e) {
			thrown = true;
		}
		assertTrue(thrown);
	}
	
	@Test
	public void getAllTest1() {
		Character[] chars1 = { 'E', 'N', 'T', 'R', 'Y' };
		Character[] chars2 = { 'E', 'N', 'T', 'R', 'O', 'P', 'Y' };
		Character[] chars3 = { 'E', 'N', 'T', 'R', 'I'};
		String str1 = "where u enter";
		String str2 = "idk";
		String str3 = "italian";
		EntryTree<Character, String> myEntryTree = new EntryTree<Character, String>();
		myEntryTree.add(chars1, str1);
		myEntryTree.add(chars2, str2);
		myEntryTree.add(chars3, str3);
		assertEquals("[[ENTRY, where u enter], [ENTRI, italian], [ENTROPY, idk]]", Arrays.deepToString(myEntryTree.getAll()));
	}
}
	

