package com.datastructures.tree;

public class BinarySearchTree {

    public class Node {
        private Node left;
        private Node right;
        private int  value;
        public Node( Node left, Node right, int value ){
            this.left = left;
            this.right = right;
            this.value = value;
        }
        public Node getLeft() { return left; }
        public Node getRight() { return right; }
        public int getValue() { return value; }
        public void printValue(){
            System.out.print( "Node data: " + value);
        }

    }

     Node findNode( Node root, int value ){
        while ( root != null ){
            int curVal = root.getValue();
            if ( curVal == value ) break;
            if ( curVal < value ){
                root = root.getRight();
            } else { // curVal > value
                root = root.getLeft();
            }
        }
        return root;
    }

    public static int treeHeight( Node n ){
        if ( n == null ) return 0;return 1 + Math.max( treeHeight( n.getLeft() ),
                treeHeight( n.getRight() ) );
    }

    void preorderTraversal( Node root ){
        if ( root == null ) return;
        root.printValue();
        preorderTraversal( root.getLeft() );
        preorderTraversal( root.getRight() );
    }

    void inorderTraversal( Node root ){
        if ( root == null ) return;
        inorderTraversal( root.getLeft() );
        root.printValue();
        inorderTraversal( root.getRight() );
    }
    void postorderTraversal( Node root ){
        if ( root == null ) return;
        postorderTraversal( root.getLeft() );
        postorderTraversal( root.getRight() );
        root.printValue();
    }

    /* Function to count number of nodes recursively */
    private int countNodes(Node r)
    {
        if (r == null)
            return 0;
        else
        {
            int l = 1;
            l += countNodes(r.getLeft());
            l += countNodes(r.getRight());
            return l;
        }
    }





}
