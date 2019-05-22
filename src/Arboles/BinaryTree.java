package Arboles;

public class BinaryTree<K extends Comparable<K>,V> {
    private BinTreeNode<K, V> root = null;

    public void insert(K key, V value) {
        this.root = this.insert(root, key, value);
    }

    private BinTreeNode<K, V> insert(BinTreeNode<K, V> current, K key, V value) {
        if (current == null) {
            return new BinTreeNode<>(key, value);
        } else if (current.key.compareTo(key) < 0) {
            current.left = this.insert(current.left, key, value);
        } else if (current.key.compareTo(key) > 0) {
            current.right = this.insert(current.right, key, value);
        }
        return current;
    }

    public V search(K key) {
        return this.search(this.root, key);
    }

    private V search(BinTreeNode<K, V> current, K key) {
        if (current == null) {
            return null;
        } else if (current.key.compareTo(key) < 0) {
            return this.search(current.left, key);
        } else if (current.key.compareTo(key) > 0) {
            return this.search(current.right, key);
        } else {
            return current.value;
        }
    }
}