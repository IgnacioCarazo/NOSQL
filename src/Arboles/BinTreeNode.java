package Arboles;

import java.util.HashMap;

public class BinTreeNode {
    public BinTreeNode left;
    public BinTreeNode right;
    public int ID;
    public HashMap hashMap;

    public BinTreeNode getLeft() {
        return left;
    }

    public BinTreeNode getRight() {
        return right;
    }

    public int getID() {
        return ID;
    }

    public HashMap getHashMap() {
        return hashMap;
    }

    public void setLeft(BinTreeNode left) {
        this.left = left;
    }

    public void setRight(BinTreeNode right) {
        this.right = right;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setHashMap(HashMap hashMap) {
        this.hashMap = hashMap;
    }

    public BinTreeNode(int ID, HashMap hashMap) {
        this.ID = ID;
        this.hashMap = hashMap;
    }
    public BinTreeNode(){

    }
}
