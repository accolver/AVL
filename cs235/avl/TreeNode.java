

/**
 * NOTE: This is an optional interface.
 *
 * If you want to use the AVL GUI,
 * you need to implement this interface.
 * The GUI allows you to see what your AVL
 * tree looks like as you add and remove nodes.
 */


package cs235.avl;


public interface TreeNode {

    /**
     * Returns the data stored in the node
     * 
     * @return the data stored in the node.
     */
    Object getData();

    /**
     * Returns the left child of the node or null if there is no left child
     * 
     * @return the left child of the node or null if there is no left child.
     */
    TreeNode getLeftChild();

    /**
     * Returns the right child of the node or null if there is no right child
     * 
     * @return the right child of the node or null if there is no right child.
     */
    TreeNode getRightChild();

    /**
     * Returns the height of the node.
     * 
     * @return the height of the node.
     */
    int getHeight();

}


