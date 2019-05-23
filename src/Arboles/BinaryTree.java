package Arboles;

import java.util.HashMap;

public class BinaryTree {
    public BinTreeNode root = null;

    public BinaryTree(){

    }

    public void insert(int ID, HashMap hashMap) {
        this.root = this.insert(root, ID, hashMap);
    }

    public BinTreeNode insert(BinTreeNode current, int ID, HashMap hashMap) {
        if (current == null) {
            return new BinTreeNode(ID, hashMap);
        } else if (current.ID > ID) {
            current.left = this.insert(current.left, ID, hashMap);
        } else if (current.ID < ID) {
            current.right = this.insert(current.right, ID, hashMap);
        }
        return current;
    }

    public void delete(Integer ID) {

        deleteNode(this.root, ID);
    }

    private BinTreeNode deleteNode(BinTreeNode root, Integer ID) {

        if(root == null) return root;

        if(ID < root.getID()) {
            root.setLeft(deleteNode(root.getLeft(), ID));
        } else if(ID > root.getID()) {
            root.setRight(deleteNode(root.getRight(), ID));
        } else {
            // node with no leaf nodes
            if(root.getLeft() == null && root.getRight() == null) {
                System.out.println("deleting "+ID);
                return null;
            } else if(root.getLeft() == null) {
                // node with one node (no left node)
                System.out.println("deleting "+ID);
                return root.getRight();
            } else if(root.getRight() == null) {
                // node with one node (no right node)
                System.out.println("deleting "+ID);
                return root.getLeft();
            } else {
                // nodes with two nodes
                // search for min number in right sub tree
                Integer minValue = minValue(root.getRight());
                root.setID(minValue);
                root.setRight(deleteNode(root.getRight(), minValue));
                System.out.println("deleting "+ID);
            }
        }

        return root;
    }
    private Integer minValue(BinTreeNode node) {

        if(node.getLeft() != null) {
            return minValue(node.getLeft());
        }
        return node.getID();
    }

    public HashMap search(int ID) {
        return this.search(this.root, ID);
    }

    public HashMap search(BinTreeNode current, int ID) {
        if (current == null) {
            System.out.println(321);
            return null;
        } else if (current.ID > ID) {
            return this.search(current.left, ID);
        } else if (current.ID < ID) {
            return this.search(current.right, ID);
        } else {
            System.out.println(123);
            return current.hashMap;
        }
    }
}