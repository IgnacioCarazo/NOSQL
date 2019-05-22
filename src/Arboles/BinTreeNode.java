package Arboles;

public class BinTreeNode<K extends Comparable<K>,V> {
    public BinTreeNode<K,V> left;
    public BinTreeNode<K,V> right;
    public K key;
    public V value;

    public BinTreeNode(K key, V value) {
        this.key = key;
        this.value = value;
    }
}
