

/**
 * NOTE: This is an optional interface.
 *
 * You only need to implement this interface
 * if you want to use the AVL GUI.
 * The GUI allows you to see what your AVL
 * tree looks like as you add and remove nodes.
 */


package cs235.avl;


public interface Tree extends java.util.Set {

    /**
     * Returns the root node of the tree
     * 
     * @return the root node of the tree.
     */
    TreeNode getRoot();

}


