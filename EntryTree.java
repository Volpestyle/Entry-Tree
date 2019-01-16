package edu.iastate.cs228.proj4;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * 
 * @author James Volpe
 *
 *
 * An entry tree class.
 *
 *
 */
public class EntryTree<K, V> {
	// Dummy root node.
	// Made public for grading.
	public Node root;

	/**
	 * 
	 * You are allowed to add at most TWO more data fields to EntryTree class of int
	 * type ONLY if you need to.
	 * 
	 */
	
	int currentIndex; //This is for getAll() to keep track of index in two dimensional array when in a recursive method.
	int height; //height of tree

	// All made public for grading.
	public class Node implements EntryNode<K, V> {
		public Node child; // reference to the first child node
		public Node parent; // reference to the parent node
		public Node prev; // reference to the previous sibling
		public Node next; // reference to the next sibling
		public K key; // the key for this node
		public V value; // the value at this node

		public Node(K aKey, V aValue) {
			key = aKey;
			value = aValue;
			child = null;
			parent = null;
			prev = null;
			next = null;
		}

		@Override
		public EntryNode<K, V> parent() {
			return parent;
		}

		@Override
		public EntryNode<K, V> child() {
			return child;
		}

		@Override
		public EntryNode<K, V> next() {
			return next;
		}

		@Override
		public EntryNode<K, V> prev() {
			return prev;
		}

		@Override
		public K key() {
			return key;
		}

		@Override
		public V value() {
			return value;
		}
	}

	public EntryTree() {
		root = new Node(null, null);
	}

	/**
	 * Returns the value of the entry with a specified key sequence, or {@code null}
	 * if this tree contains no entry with this key sequence.
	 * 
	 * This method returns {@code null} if {@code keyarr} is null or if its length
	 * is {@code 0}. If any element of {@code keyarr} is {@code null}, then the
	 * method throws a {@code NullPointerException}. The method returns the value of
	 * the entry with the key sequence in {@code keyarr} or {@code null} if this
	 * tree contains no entry with this key sequence. An example is given in
	 * provided sample input and output files to illustrate this method.
	 *
	 * @param keyarr
	 *            Read description.
	 * @return Read description.
	 * @throws NullPointerException
	 *             Read description.
	 */
	public V search(K[] keyarr) {
		if (keyarr == null || keyarr.length == 0) {
			return null;
		}
		for (int i = 0; i < keyarr.length; i++) {
			if (keyarr[i] == null)
				throw new NullPointerException();
		}
		Node parentNode = root;
		Node currentNode = null;
		for (int i = 0; i < keyarr.length; i++) {
			currentNode = searchForKey(parentNode, keyarr[i]);
			if (currentNode == null) {
				 return null;
			}
			parentNode = currentNode; 
		}
		return currentNode.value;	
	}

	/**
	 * 
	 * This method returns an array of type {@code K[]} with the longest prefix of
	 * the key sequence specified in {@code keyarr} such that the keys in the prefix
	 * label the nodes on the path from the root to a node. The length of the
	 * returned array is the length of the longest prefix.
	 * 
	 * This method returns {@code null} if {@code keyarr} is {@code null}, or if its
	 * length is {@code 0}. If any element of {@code keyarr} is {@code null}, then
	 * the method throws a {@code NullPointerException}. A prefix of the array
	 * {@code keyarr} is a key sequence in the subarray of {@code keyarr} from index
	 * {@code 0} to any index {@code m>=0}, i.e., greater than or equal to; the
	 * corresponding suffix is a key sequence in the subarray of {@code keyarr} from
	 * index {@code m+1} to index {@code keyarr.length-1}. The method returns an
	 * array of type {@code K[]} with the longest prefix of the key sequence
	 * specified in {@code keyarr} such that the keys in the prefix are,
	 * respectively, with the nodes on the path from the root to a node. The lngth
	 * of the returned array is the length of the longest prefix. Note that if the
	 * length of the longest prefix is {@code 0}, then the method returns
	 * {@code null}. This method can be used to select a shorted key sequence for an
	 * {@code add} command to create a shorter path of nodes in the tree. An example
	 * is given in the attachment to illustrate how this method is used with the
	 * {@code #add(K[] keyarr, V aValue)} method.
	 * 
	 * NOTE: In this method you are allowed to use {@link java.util.Arrays}'s
	 * {@code copyOf} method only.
	 * 
	 * @param keyarr
	 *            Read description.
	 * @return Read description.
	 * @throws NullPointerException
	 *             Read description.
	 */
	public K[] prefix(K[] keyarr) {
		if (keyarr == null || keyarr.length == 0) {
			return null;
		}
		for (int i = 0; i < keyarr.length; i++) {
			if (keyarr[i] == null)
				throw new NullPointerException();
		}
		Node parentNode = root;
		Node currentNode = null; 
		int lengthOfArray = 0;
		for (int i = 0; i < keyarr.length; i++) {
			currentNode = searchForKey(parentNode, keyarr[i]);
			if (currentNode != null) {
				lengthOfArray++;
			}
			parentNode = currentNode;
		}
		return Arrays.copyOf(keyarr, lengthOfArray);
	}

	/**
	 * 
	 * This method returns {@code false} if {@code keyarr} is {@code null}, its
	 * length is {@code 0}, or {@code aValue} is {@code null}. If any element of
	 * {@code keyarr} is {@code null}, then the method throws a
	 * {@code NullPointerException}.
	 * 
	 * This method locates the node {@code P} corresponding to the longest prefix of
	 * the key sequence specified in {@code keyarr} such that the keys in the prefix
	 * label the nodes on the path from the root to the node. If the length of the
	 * prefix is equal to the length of {@code keyarr}, then the method places
	 * {@code aValue} at the node {@code P} (in place of its old value) and returns
	 * {@code true}. Otherwise, the method creates a new path of nodes (starting at
	 * a node {@code S}) labelled by the corresponding suffix for the prefix,
	 * connects the prefix path and suffix path together by making the node
	 * {@code S} a child of the node {@code P}, and returns {@code true}. An example
	 * input and output files illustrate this method's operation.
	 *
	 * NOTE: In this method you are allowed to use {@link java.util.Arrays}'s
	 * {@code copyOf} method only.
	 * 
	 * @param keyarr
	 *            Read description.
	 * @param Read
	 *            description.
	 * @return Read description.
	 * @throws NullPointerException
	 *             Read description.
	 */
	public boolean add(K[] keyarr, V aValue) {
		if (keyarr == null || keyarr.length == 0)
			return false;
		if (aValue == null) {
			return false;
		}
		for (int i = 0; i < keyarr.length; i++) {
			if (keyarr[i] == null)
				throw new NullPointerException();
		}
		Node parent = root;
		Node currentNode = null;
		for (int i = 0; i < keyarr.length; i++) {
			currentNode = searchForKey(parent, keyarr[i]); //searches one level down for key
			if (currentNode == null) { //could not find node with the specified key 
				currentNode = new Node(keyarr[i], null);
				currentNode.parent = parent;
				if (parent.child != null) {
					Node currentNode2 = parent.child;
					while (currentNode2.next != null) {
						currentNode2 = currentNode2.next;
					}
					currentNode2.next = currentNode;
					currentNode.prev = currentNode2;
				} else
					parent.child = currentNode;
			}
			parent = currentNode;
		}
		currentNode.value = aValue;
		return true;
	}

	/**
	 * Helper method to find the first node with the specified key. Only searches one level down.
	 * 
	 * @param parent:
	 *            parent node to start from
	 * @param key:
	 *            key to look for
	 * @return the node if found, null if not found
	 */
	private Node searchForKey(Node parent, K key) {
		if (parent == null) {
			return null;
		}
			parent = parent.child;
			Node currentNode = parent;
			while (currentNode != null) {
				if (currentNode.key().equals(key)) {
					return currentNode;
				}
				currentNode = currentNode.next;
			}
		return null;
	}

	/**
	 * Removes the entry for a key sequence from this tree and returns its value if
	 * it is present. Otherwise, it makes no change to the tree and returns
	 * {@code null}.
	 * 
	 * This method returns {@code null} if {@code keyarr} is {@code null} or its
	 * length is {@code 0}. If any element of {@code keyarr} is {@code null}, then
	 * the method throws a {@code NullPointerException}. The method returns
	 * {@code null} if the tree contains no entry with the key sequence specified in
	 * {@code keyarr}. Otherwise, the method finds the path with the key sequence,
	 * saves the value field of the node at the end of the path, sets the value
	 * field to {@code null}.
	 * 
	 * The following rules are used to decide whether the current node and higher
	 * nodes on the path need to be removed. The root cannot be removed. Any node
	 * whose value is not {@code null} cannot be removed. Consider a non-root node
	 * whose value is {@code null}. If the node is a leaf node (has no children),
	 * then the node is removed. Otherwise, if the node is the parent of a single
	 * child and the child node is removed, then the node is removed. Finally, the
	 * method returns the saved old value.
	 * 
	 * 
	 * @param keyarr
	 *            Read description.
	 * @return Read description.
	 * @throws NullPointerException
	 *             Read description.
	 * 
	 */
	public V remove(K[] keyarr) {
		if (keyarr == null || keyarr.length == 0)
			return null;
		for (int i = 0; i < keyarr.length; i++) {
			if (keyarr[i] == null)
				throw new NullPointerException();
		}
		Node parentNode = root;
		Node currentNode = null; 
		V result;
		for (int i = 0; i < keyarr.length; i++) {
			currentNode = searchForKey(parentNode, keyarr[i]);
			if (currentNode == null) { //path must exsist
				return null;
			}
			parentNode = currentNode;
		}
		result = currentNode.value();
		if (result != null) {
			currentNode.value = null;
			return result;
		}
		else { //null
			if (currentNode.child == null) { //is leaf
				recRemoveNode(currentNode);
			}
		}
		return result;
	}
	
	/**
	 * Recursive helper method in case there is a chain reaction of removing nodes
	 * @param currentNode Node to remove
	 */
	private void recRemoveNode(Node currentNode) {
		if (currentNode == root) {
			return;
		}
		if (currentNode.next != null || currentNode.prev != null) {
			if (currentNode.parent.child.equals(currentNode)) {
				currentNode.parent.child = currentNode.next;
			}
			if (currentNode.next != null)
				currentNode.next.prev = currentNode.prev;
			if (currentNode.prev != null)
				currentNode.prev.next = currentNode.next;
			return;
		}
		else {
			currentNode.parent.child = null;
			recRemoveNode(currentNode.parent);
		}
	}

	/**
	 * 
	 * This method prints the tree on the console in the output format shown in
	 * provided sample output file. If the tree has no entry, then the method just
	 * prints out the line for the dummy root node.
	 * 
	 */
	public void showTree() {
		recShowTree(root, 0);
	}
	
	/**
	 * Recursive helper method to print tree.
	 * @param currentNode Node to start from
	 * @param currentLevel Recursive level tracker to print correct number of '.'
	 */
	private void recShowTree(Node currentNode, int currentLevel) {
		while (currentNode != null) {
			if (currentNode != root) 
				System.out.print("....");
			for (int i = 0; i < currentLevel; i++)
				System.out.print("..");
			
			System.out.print(currentNode.key + "::" + currentNode.value + "\n");
			recShowTree(currentNode.child, currentLevel + 1);
			currentNode = currentNode.next;
		}
	}

	/**
	 * 
	 * Returns all values in this entry tree together with their keys. The order of
	 * outputs would be similar to level order traversal, i.e., first you would get
	 * all values together with their keys in first level from left to right, then
	 * second level, and so on. If tree has no values then it would return
	 * {@code null}.
	 *
	 * For the example image given in description, the returned String[][] would
	 * look as follows:
	 * 
	 * {{"IA","Grow"}, {"ISU","CS228"}}
	 * 
	 * NOTE: In this method you are allowed to use {@link java.util.LinkedList}.
	 * 
	 * 
	 */
	public String[][] getAll() { //I apologize... this is way more complex than it should be lol
		currentIndex = 0;
		height = 0;
		recGetHeight(root, 0); //Get the height/depth of the root
		int h = height;
		height = 0;
		recGetRealHeight(root); //Get the number of nodes with values, in order to initialize String array.
		String[][] result = new String[height][2];
		for (int i=0; i<=h; i++) //include root
            recGetAll(root, i, result);
		return result;
	}
	
	/**
	 * Recursive helper method to get fill specified string by level order traversal
	 * @param currentNode Node to start traversal from
	 * @param level recursive level tracker
	 * @param str specified string to fill
	 */
	
	private void recGetAll(Node currentNode, int level, String[][] str) {
		if (currentNode == null) {
			return;
		}
			if (level == 0) { 
				while (currentNode != null) {
					if (currentNode.value != null) {
						Node temp = currentNode;
						for (int i = 0; i < 2; i++) {
							if (i == 0) {
								str[currentIndex][i] = "";
								while (temp != root) {
									str[currentIndex][i] += temp.key + "";
									temp = temp.parent;
								}
								str[currentIndex][i] = reverseString(str[currentIndex][i]);
							}
							else 
								str[currentIndex][i] = currentNode.value + "";
						}
						currentIndex++;
					}
					currentNode = currentNode.next;
				}
			}
			
			else if (level > 0) { 
	            recGetAll(currentNode.child, level-1, str); 
	            while (currentNode != null) {
	            		currentNode = currentNode.next;
	            		if (currentNode != null) {
	            			recGetAll(currentNode.child, level-1, str); 
	            		}
	            }  
			}
        		
	}
	
	/**
	 * Recrusive helper method to get height/depth of specified node
	 * @param currentNode Node to measure fro
	 * @param level cursive level tracker. "Height" is set to the highest value this reaches.
	 * @return 
	 */
	private void recGetHeight(Node currentNode, int level) {
		while (currentNode != null) {
			recGetHeight(currentNode.child, level + 1);
			if (level > height) {
				height = level;
			}
			currentNode = currentNode.next;
		}
	}
	
	/**
	 * Recursive helper method to get the number of nodes that have a value 
	 * @param currentNode Node to measure from
	 */
	private void recGetRealHeight(Node currentNode) { 
		if (currentNode == null) {
			return; 
		}
		while (currentNode != null) {
			recGetRealHeight(currentNode.child);
			if (currentNode.value != null) {
				height++;
			}
			currentNode = currentNode.next;
		}
	}
	
	/**
	 * Helper method to reverse a string. Used in "recGetAll".
	 * @param str String to reverse
	 * @return Reversed string
	 */
	private String reverseString(String str) {
		String result = "";
		char[] chars = str.toCharArray();   
        for (int i = chars.length-1; i>=0; i--) 
            result += chars[i];
        return result;
	}
}
