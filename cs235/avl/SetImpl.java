package cs235.avl;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class SetImpl implements java.util.Set, Tree {

    private static Node root = null;
    private int size = 0;
    private Queue<Node> q = new LinkedList<Node>();

    public boolean remove(Object o) {

        if (!contains(o)) {
            return false;
        }

        Comparable c = (Comparable) o;
        size--;
        root = recRemove(root, c);
        return true;
    }

    public Node recRemove(Node root, Comparable c) {

        if (c.compareTo(root.data) > 0) {
            root.rightChild = recRemove(root.rightChild, c);
        } else if (c.compareTo(root.data) < 0) {
            root.leftChild = recRemove(root.leftChild, c);
        } else {
            if (root.leftChild == null && root.rightChild == null) {
                return null;
            } else if (root.rightChild == null) {
                root.data = root.leftChild.data;
                root.leftChild = null;
            } else {
                root.data = findMin(root.rightChild).data;
                c = findMin(root.rightChild).data;
                root.rightChild = recRemove(root.rightChild, c);
            }

        }
        fixHeight(root);
        root = balance(calcDiff(root), root);
        fixHeight(root);
        return root;
    }

    public Node findMin(Node n) {
        while (n.leftChild != null) {
            n = n.leftChild;
        }
        return n;
    }

    public Node findMax(Node n) {
        while (n.rightChild != null) {
            n = n.rightChild;
        }
        return n;
    }

    public int size() {
        return size;
    }

    public void clear() {
        root = null;
        size = 0;
    }

    public myIterator iterator() {
        return new myIterator();
    }

    public static class myIterator extends SetImpl implements Iterator<Object> {

        private Queue<Node> q = new LinkedList<Node>();

        public Queue<Node> getQ() {
            return q;
        }

        public void setQ(Queue<Node> q) {
            this.q = q;
        }

        public myIterator() {
            if (root != null) {
                q.add(root);
            }
        }

        public boolean hasNext() {
            if (q.isEmpty()) {
                return false;
            }
            return true;
        }

        public Object next() {
            Node cur = q.poll();
            if (cur.leftChild != null) {
                q.offer(cur.leftChild);
            }
            if (cur.rightChild != null) {
                q.offer(cur.rightChild);
            }
            return cur.data;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    class Node extends SetImpl implements TreeNode {

        private Node leftChild = null;
        private Node rightChild = null;
        private String data = "";
        private int height = -1;

        public Node(Object o, int height) {
            this.data = (String) o;
            this.height = height;
        }

        public Object getData() {
            return this.data;
        }

        public TreeNode getLeftChild() {
            return this.leftChild;
        }

        public TreeNode getRightChild() {
            return this.rightChild;
        }

        public int getHeight() {
            if (this == null) {
                return -1;
            }
            return this.height;
        }
    }

    public boolean add(Object o) {
        Comparable c = (Comparable) o;
        if (contains(c)) {
            return false;
        }
        size++;

        if (root == null) {
            root = new Node(c, 0);
            return true;
        }

        root = addRecurse(c, root);
        return true;
    }

    public Node addRecurse(Comparable c, Node root) {

        Node newNode = null;
        if (root.getRightChild() != null && c.compareTo(root.getData()) > 0) {
            root.rightChild = addRecurse(c, (Node) root.getRightChild());

        } else if (root.getLeftChild() != null
                && c.compareTo(root.getData()) < 0) {
            root.leftChild = addRecurse(c, (Node) root.getLeftChild());

        } else {
            if (c.compareTo(root.getData()) > 0) {
                newNode = new Node(c, 0);
                root.rightChild = newNode;
            } else {
                newNode = new Node(c, 0);
                root.leftChild = newNode;
            }
        }
        fixHeight(root);
        root = balance(calcDiff(root), root);
        fixHeight(root);
        return root;
    }

    public int calcDiff(Node n) {
        int lChild = 0;
        int rChild = 0;
        if (n.leftChild == null && n.rightChild == null) {
            lChild = 0;
            rChild = 0;
        } else if (n.leftChild == null) {
            lChild = -1;
            rChild = n.rightChild.height;
        } else if (n.rightChild == null) {
            rChild = -1;
            lChild = n.leftChild.height;
        } else {
            rChild = n.rightChild.height;
            lChild = n.leftChild.height;
        }
        int result = lChild - rChild;
        return result;
    }

    public Node balance(int i, Node n) {
        if (i < -1) {
            if (calcDiff(n.rightChild) > 0) {
                n.rightChild.height -= 1;
                n.rightChild = rotateRight(n.rightChild);
            }
            n.height -= 2;
            n = rotateLeft(n);
        } else if (i > 1) {
            if (calcDiff(n.leftChild) < 0) {
                n.leftChild.height -= 1;
                n.leftChild = rotateLeft(n.leftChild);
            }
            n.height -= 2;
            n = rotateRight(n);
        } else {
        }
        return n;
    }

    public Node rotateRight(Node n) {
        Node temp = n.leftChild;
        n.leftChild = temp.rightChild;
        temp.rightChild = n;
        fixHeight(n);
        fixHeight(temp);
        return temp;
    }

    public Node rotateLeft(Node n) {
        Node temp = n.rightChild;
        n.rightChild = temp.leftChild;
        temp.leftChild = n;
        fixHeight(n);
        fixHeight(temp);
        return temp;
    }

    public void fixHeight(Node n) {
        if (n == null) {
            n.height = -1;
        } else if (n.leftChild == null && n.rightChild == null) {
            n.height = 0;
        } else if (n.leftChild == null && n.rightChild != null) {
            n.height = n.rightChild.height + 1;
        } else if (n.leftChild != null && n.rightChild == null) {
            n.height = n.leftChild.height + 1;
        } else {
            n.height = Math.max(n.leftChild.height, n.rightChild.height) + 1;
        }
    }

    public boolean contains(Object o) {

        TreeNode cur = root;
        Comparable c = (Comparable) o;
        if (cur == null) {
            return false;
        }
        if (root.getData().equals(o)) {
            return true;
        } else {
            return recContains(c, cur);
        }
    }

    public boolean recContains(Comparable c, TreeNode cur) {

        if (cur == null) {
            return false;
        }
        if (c.compareTo(cur.getData()) == 0) {
            return true;
        } else if (c.compareTo(cur.getData()) > 0) {
            cur = cur.getRightChild();
            return recContains(c, cur);
        } else if (c.compareTo(cur.getData()) < 0) {
            cur = cur.getLeftChild();
            return recContains(c, cur);
        }
        return true;
    }

    public TreeNode getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public boolean addAll(Collection c) {
        throw new UnsupportedOperationException();
    }

    public boolean containsAll(Collection c) {
        throw new UnsupportedOperationException();
    }

    public boolean isEmpty() {
        throw new UnsupportedOperationException();
    }

    public boolean removeAll(Collection c) {
        throw new UnsupportedOperationException();
    }

    public boolean retainAll(Collection c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object[] toArray() {
        throw new UnsupportedOperationException();
    }

    public Object[] toArray(Object[] array) {
        throw new UnsupportedOperationException();
    }
}
