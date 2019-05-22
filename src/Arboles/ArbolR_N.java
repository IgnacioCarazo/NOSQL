package Arboles;

/*
 *  Java Program to Implement Red Black Tree
 */

import main.Atributo;

import java.util.HashMap;
import java.util.Scanner;

/* Class Node */
/* Class ArbolR_N */
public class ArbolR_N
{

    private RedBlackNode current;
    private RedBlackNode parent;
    private RedBlackNode grand;
    private RedBlackNode great;
    private RedBlackNode header;
    private static RedBlackNode nullNode;
    /* static initializer for nullNode */
    static
    {
        nullNode = new RedBlackNode(0, new HashMap());
        nullNode.left = nullNode;
        nullNode.right = nullNode;
    }
    /* Black - 1  RED - 0 */
    static final int BLACK = 1;
    static final int RED   = 0;
    /* Constructor */

    public ArbolR_N()
    {
        header = new RedBlackNode(0, new HashMap());
        header.left = nullNode;
        header.right = nullNode;
    }
    /* Function to check if tree is empty */
    public boolean isEmpty()
    {
        return header.right == nullNode;
    }
    /* Make the tree logically empty */
    public void makeEmpty()
    {
        header.right = nullNode;
    }
    /* Function to insert item */
    public void insert(int id, HashMap hashMap)
    {
        System.out.println(1);
        current = parent = grand = header;
        nullNode.Id = id;
        while (current.Id != id)
        {
            great = grand;
            grand = parent;
            parent = current;
            current = id < current.Id ? current.left : current.right;
            // Check if two red children and fix if so
            if (current.left.color == RED && current.right.color == RED)
                handleReorient( id );
        }
        // Insertion fails if already present
        if (current != nullNode)
            return;
        current = new RedBlackNode(id,hashMap, nullNode, nullNode);
        // Attach to parent
        if (id < parent.Id)
            parent.left = current;
        else
            parent.right = current;
        handleReorient( id );
    }
    private void handleReorient(int item)
    {
        // Do the color flip
        current.color = RED;
        current.left.color = BLACK;
        current.right.color = BLACK;

        if (parent.color == RED)
        {
            // Have to rotate
            grand.color = RED;
            if (item < grand.Id != item < parent.Id)
                parent = rotate( item, grand );  // Start dbl rotate
            current = rotate(item, great );
            current.color = BLACK;
        }
        // Make root black
        header.right.color = BLACK;
    }
    private RedBlackNode rotate(int item, RedBlackNode parent)
    {
        if(item < parent.Id)
            return parent.left = item < parent.left.Id ? rotateWithLeftChild(parent.left) : rotateWithRightChild(parent.left) ;
        else
            return parent.right = item < parent.right.Id ? rotateWithLeftChild(parent.right) : rotateWithRightChild(parent.right);
    }
    /* Rotate binary tree node with left child */
    private RedBlackNode rotateWithLeftChild(RedBlackNode k2)
    {
        RedBlackNode k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;
        return k1;
    }
    /* Rotate binary tree node with right child */
    private RedBlackNode rotateWithRightChild(RedBlackNode k1)
    {
        RedBlackNode k2 = k1.right;
        k1.right = k2.left;
        k2.left = k1;
        return k2;
    }
    /* Functions to count number of nodes */
    public int countNodes()
    {
        return countNodes(header.right);
    }
    private int countNodes(RedBlackNode r)
    {
        if (r == nullNode)
            return 0;
        else
        {
            int l = 1;
            l += countNodes(r.left);
            l += countNodes(r.right);
            return l;
        }
    }
    /* Functions to search for an Id */
    public HashMap search(int val)
    {   RedBlackNode nodo = search(header.right, val);
        return nodo.hashMap;
    }
    private RedBlackNode search(RedBlackNode r, int val)
    {
        boolean found = false;
        while ((r != nullNode) && !found)
        {
            int rval = r.Id;
            if (val < rval)
                r = r.left;
            else if (val > rval)
                r = r.right;
            else
            {
                found = true;
                break;
            }
            r = search(r, val);
        }
        return r;
    }
    /* Function for inorder traversal */
    public void inorder()
    {
        inorder(header.right);
    }
    private void inorder(RedBlackNode r)
    {
        if (r != nullNode)
        {
            inorder(r.left);
            char c = 'B';
            if (r.color == 0)
                c = 'R';
            System.out.print(r.Id +""+c+" ");
            inorder(r.right);
        }
    }
    /* Function for preorder traversal */
    public void preorder()
    {
        preorder(header.right);
    }
    private void preorder(RedBlackNode r)
    {
        if (r != nullNode)
        {
            char c = 'B';
            if (r.color == 0)
                c = 'R';
            System.out.print(r.Id +""+c+" ");
            preorder(r.left);
            preorder(r.right);
        }
    }
    /* Function for postorder traversal */
    public void postorder()
    {
        postorder(header.right);
    }
    private void postorder(RedBlackNode r)
    {
        if (r != nullNode)
        {
            postorder(r.left);
            postorder(r.right);
            char c = 'B';
            if (r.color == 0)
                c = 'R';
            System.out.print(r.Id +""+c+" ");
        }
    }


    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        /* Creating object of RedBlack Tree */
        ArbolR_N rbt = new ArbolR_N();
        System.out.println("Red Black Tree Test\n");
        char ch;
        /*  Perform tree operations  */
        do
        {
            System.out.println("\nRed Black Tree Operations\n");
            System.out.println("1. insert ");
            System.out.println("2. search");
            System.out.println("3. count nodes");
            System.out.println("4. check empty");
            System.out.println("5. clear tree");

            int choice = scan.nextInt();
            switch (choice)
            {
                case 1 :
                    System.out.println("Enter integer Id to insert");
                    HashMap<String, Atributo> hashMap = new HashMap();
                    Atributo atributo = new Atributo();
                    atributo.valueInt = 2017090425;
                    hashMap.put("Carnet",atributo);
                    rbt.insert( scan.nextInt(), hashMap);
                    break;
                case 2 :
                    System.out.println("Enter integer Id to search");
                    System.out.println("Search result : "+ rbt.search( scan.nextInt() ).get("Carnet"));
                    break;
                case 3 :
                    System.out.println("Nodes = "+ rbt.countNodes());
                    break;
                case 4 :
                    System.out.println("Empty status = "+ rbt.isEmpty());
                    break;
                case 5 :
                    System.out.println("\nTree Cleared");
                    rbt.makeEmpty();
                    break;
                default :
                    System.out.println("Wrong Entry \n ");
                    break;
            }
            /*  Display tree  */
            System.out.print("\nPost order : ");
            rbt.postorder();
            System.out.print("\nPre order : ");
            rbt.preorder();
            System.out.print("\nIn order : ");
            rbt.inorder();

            System.out.println("\nDo you want to continue (Type y or n) \n");
            ch = scan.next().charAt(0);
        } while (ch == 'Y'|| ch == 'y');
    }
}

class RedBlackNode
{
    RedBlackNode left, right;
    int Id;
    int color;
    HashMap<String, Atributo> hashMap;

    /* Constructor */
    public RedBlackNode(int theElement, HashMap hashMap)
    {
        this( theElement,hashMap, null, null );
    }

    /* Constructor */
    RedBlackNode(int theElement, HashMap hashMap, RedBlackNode lt, RedBlackNode rt)
    {
        left = lt;
        right = rt;
        Id = theElement;
        this.hashMap = hashMap;
        color = 1;
    }

    @Override
    public String toString() {
        return "Numero entero del nodo: " + Id;
    }
}
