package edu.iastate.cs228.proj4;

/**
 * An interface for describing basic getter functionality of a node.
 *  
 */
public interface EntryNode<K, V> 
{
 /**
  * 
  * Returns the parent node of this node. If there is no parent node,
  * {@code null} is returned.
  */
 public EntryNode<K, V> parent();

 /**
  * 
  * <p>
  * Returns the child node of this node. If a node has multiple children,
  * then this returns the left-most child. E.g.,
  * </p>
  * 
  * <pre>
  *          Parent
  *            |
  * null <-> Child <-> Sibling <-> Sibling ...
  * </pre>
  * <p>
  * If there is no child node, {@code null} is returned.
  * </p>
  * 
  */
 public EntryNode<K, V> child();

 /**
  * Returns the next sibling of this node. If there is no next sibling,
  * {@code null} is returned.
  * 
  */
 public EntryNode<K, V> next();

 /**
  * Returns the previous sibling of this node. If there is no previous
  * sibling, {@code null} is returned.
  * 
  */
 public EntryNode<K, V> prev();

 /**
  * Returns the (non-{@code null}) key for this node.
  * 
  */
 public K key();

 /**
  * Returns the value for this node.
  * 
  */
 public V value();
}
