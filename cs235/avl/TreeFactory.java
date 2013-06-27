

package cs235.avl;


public class TreeFactory {

    /**
     * NOTE: This is an optional method.
     *
     * You only need to implement this method
     * if you want to use the AVL GUI.
     * The GUI allows you to see what your AVL
     * tree looks like as you add and remove nodes.
     *
     * Creates and returns an object that implements the Tree interface.
     *
     * @return A new object that implements the Tree interface
     */
    public static Tree createTree()
    {
	return new SetImpl();
    }

    /**
     * NOTE: This is an optional method.
     *
     * You only need to implement this method
     * if you want undo and redo support in the GUI.
     *
     * Copies a tree by copying each node in the tree.
     * The new tree doesn't share any nodes with the old tree.
     *
     * @return a new tree that is a copy of t
     */
    public static Tree copyTree(Tree t)
    {
	return null;
    }

}


