import com.sun.source.tree.EmptyStatementTree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Concrete implementation of a binary tree using a node-based, linked
 * structure.
 */
public class LinkedBinaryTree<E> extends AbstractBinaryTree<E> {

    static java.util.Random rnd = new java.util.Random();


    /**
     * The root of the binary tree
     */
    protected Node<E> root = null; // root of the tree

    // LinkedBinaryTree instance variables
    /**
     * The number of nodes in the binary tree
     */
    private int size = 0; // number of nodes in the tree


    /**
     * Constructs an empty binary tree.
     */
    public LinkedBinaryTree() {
    } // constructs an empty binary tree

    // constructor


    public static void main(String[] args) {
//		LinkedBinaryTree<Integer> bt = new LinkedBinaryTree<Integer>();
//
//
//		  // Direct construction of Tree Position<Integer> root = bt.addRoot(12);
//		  Position<Integer> p1 = bt.addLeft(root, 25); Position<Integer> p2 =
//		  bt.addRight(root, 31);
//
//		  Position<Integer> p3 = bt.addLeft(p1, 58); bt.addRight(p1, 36);
//
//		  Position<Integer> p5 = bt.addLeft(p2, 42); bt.addRight(p2, 90);
//
//		  Position<Integer> p4 = bt.addLeft(p3, 62); bt.addRight(p3, 75);
//
//
//		  // Can you write a level order constructor?
//		// Level order construction
//		// Integer[] arr = {12, 25, 31, 58, 36, 42, 90, 62, 75};
//		// bt.createLevelOrder(arr);
//
//		System.out.println("bt inorder: " + bt.size() + " " + bt.inorder());
//		System.out.println("bt preorder: " + bt.size() + " " + bt.preorder());
//		System.out.println("bt preorder: " + bt.size() + " " + bt.postorder());
//
//		System.out.println("bt height: " + bt.height(bt.root()));
//		System.out.println("bt depth: " + bt.depth(bt.root()));
//
//		System.out.println(bt.toString());
    }


    /**
     * Factory function to create a new node storing element e.
     */
    protected Node<E> createNode(E e, Node<E> parent, Node<E> left, Node<E> right) {
        return new Node<E>(e, parent, left, right);
    }

    /**
     * Verifies that a Position belongs to the appropriate class, and is not one
     * that has been previously removed. Note that our current implementation does
     * not actually verify that the position belongs to this particular list
     * instance.
     *
     * @param p a Position (that should belong to this tree)
     * @return the underlying Node instance for the position
     * @throws IllegalArgumentException if an invalid position is detected
     */
    protected Node<E> validate(Position<E> p) throws IllegalArgumentException {
        if (!(p instanceof Node))
            throw new IllegalArgumentException("Not valid position type");
        Node<E> node = (Node<E>) p; // safe cast
        if (node.getParent() == node) // our convention for defunct node
            throw new IllegalArgumentException("p is no longer in the tree");
        return node;
    }

    /**
     * Returns the number of nodes in the tree.
     *
     * @return number of nodes in the tree
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Returns the root Position of the tree (or null if tree is empty).
     *
     * @return root Position of the tree (or null if tree is empty)
     */
    @Override
    public Position<E> root() {
        return root;
    }

    /**
     * Returns the Position of p's parent (or null if p is root).
     *
     * @param p A valid Position within the tree
     * @return Position of p's parent (or null if p is root)
     * @throws IllegalArgumentException if p is not a valid Position for this tree.
     */
    @Override
    public Position<E> parent(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return node.getParent();
    }

    /**
     * Returns the Position of p's left child (or null if no child exists).
     *
     * @param p A valid Position within the tree
     * @return the Position of the left child (or null if no child exists)
     * @throws IllegalArgumentException if p is not a valid Position for this tree
     */
    @Override
    public Position<E> left(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return node.getLeft();
    }

    /**
     * Returns the Position of p's right child (or null if no child exists).
     *
     * @param p A valid Position within the tree
     * @return the Position of the right child (or null if no child exists)
     * @throws IllegalArgumentException if p is not a valid Position for this tree
     */
    @Override
    public Position<E> right(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return node.getRight();
    }

    /**
     * Places element e at the root of an empty tree and returns its new Position.
     *
     * @param e the new element
     * @return the Position of the new element
     * @throws IllegalStateException if the tree is not empty
     */
    public Position<E> addRoot(E e) throws IllegalStateException {
        if (!isEmpty()) throw new IllegalStateException("Tree is not empty");
        root = createNode(e, null, null, null);
        size = 1;
        return root;
    }


    /**
     * Creates a new left child of Position p storing element e and returns its
     * Position.
     *
     * @param p the Position to the left of which the new element is inserted
     * @param e the new element
     * @return the Position of the new element
     * @throws IllegalArgumentException if p is not a valid Position for this tree
     * @throws IllegalArgumentException if p already has a left child
     */
    public Position<E> addLeft(Position<E> p, E e) throws IllegalArgumentException {
    	Node<E> parent = validate(p);
    	if (parent.getLeft() != null)
    	    throw new IllegalArgumentException("p already has a left child");
    	Node<E> child = createNode(e, parent, null, null);
    	parent.setLeft(child);
    	size++;
    	return child;
    }

    /**
     * Creates a new right child of Position p storing element e and returns its
     * Position.
     *
     * @param p the Position to the right of which the new element is inserted
     * @param e the new element
     * @return the Position of the new element
     * @throws IllegalArgumentException if p is not a valid Position for this tree.
     * @throws IllegalArgumentException if p already has a right child
     */
    public Position<E> addRight(Position<E> p, E e) throws IllegalArgumentException {
    	Node<E> parent = validate(p);
    	if(parent.getRight() != null)
    	    throw new IllegalArgumentException("p already has a right child");
    	Node<E> child = createNode(e, parent, null, null);
    	parent.setRight(child);
    	size++;
		return child;
    }

    /**
     * Replaces the element at Position p with element e and returns the replaced
     * element.
     *
     * @param p the relevant Position
     * @param e the new element
     * @return the replaced element
     * @throws IllegalArgumentException if p is not a valid Position for this tree.
     */
    public E set(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> node = validate(p);
        E temp = node.getElement();
        node.setElement(e);
        return temp;
    }

    /**
     * Attaches trees t1 and t2, respectively, as the left and right subtree of the
     * leaf Position p. As a side effect, t1 and t2 are set to empty trees.
     *
     * @param p  a leaf of the tree
     * @param t1 an independent tree whose structure becomes the left child of p
     * @param t2 an independent tree whose structure becomes the right child of p
     * @throws IllegalArgumentException if p is not a valid Position for this tree
     * @throws IllegalArgumentException if p is not a leaf
     */
    public void attach(Position<E> p, LinkedBinaryTree<E> t1, LinkedBinaryTree<E> t2) throws IllegalArgumentException {
       Node<E> node = validate(p);
       if (isInternal(p)) throw new IllegalArgumentException("p must be a leaf");

       size += t1.size() + t2.size();

        if(!t1.isEmpty()){
            t1.root.setParent(node);
            node.setLeft(t1.root);
            t1.root=null;
            t1.size=0;
        }

        if(!t2.isEmpty()){
            t2.root.setParent(node);
            node.setRight(t2.root);
            t2.root = null;
            t2.size=0;
        }
    }

    /**
     * Removes the node at Position p and replaces it with its child, if any.
     *
     * @param p the relevant Position
     * @return element that was removed
     * @throws IllegalArgumentException if p is not a valid Position for this tree.
     * @throws IllegalArgumentException if p has two children.
     */
    public E remove(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
    	if(numChildren(p) == 2)
    	    throw new IllegalArgumentException("p has two children");
    	Node<E> child = (node.getLeft() != null ? node.getLeft() : node.getRight());
    	if (child!=null)
    	    child.setParent(node.getParent());
    	if (node == root)
    	    root = child;
    	else{
    	    Node<E> parent = node.getParent();
    	    if (node == parent.getLeft())
    	        parent.setLeft(child);
    	    else
    	        parent.setRight(child);
        }
    	size--;
    	E removed = node.getElement();
    	node.setElement(null);
    	node.setLeft(null);
    	node.setRight(null);
    	node.setParent(node);
    	return removed;
    }

//    public String toString() {
//    	// you can use either the StringBuilder or ArrayList...
//        StringBuilder sb = new StringBuilder();
////        ArrayList<E> buf = new ArrayList<>();
//        sb.append("[");
//        for (Position<E> p : positions()) {
//            sb.append(p.getElement());
//            sb.append(", ");
////            buf.add(p.getElement());
//        }
//        sb.append("]");
//        return sb.toString();
////        return buf.toString();
//    }

    @Override
    public String toString() {
        Iterator<E> it = iterator();
        if (!it.hasNext())
            return "[]";

        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (; ; ) {
            E e = it.next();
            sb.append(e);
            if (!it.hasNext())
                return sb.append(']').toString();
            sb.append(", ");
        }
    }

    public void createLevelOrder(ArrayList<E> l) {
    	root = createLevelOrderHelper(l, null, 0);
    }

    private Node<E> createLevelOrderHelper(ArrayList<E> l, Node<E> p, int i) {
    	if(i < l.size()){
    	    Node<E> newest = createNode(l.get(i), p, null, null);
    	    newest.setLeft(createLevelOrderHelper(l, newest.getLeft(), 2 * i + 1));
    	    newest.setRight(createLevelOrderHelper(l, newest.getRight(), 2 * i + 2));
    	    size++;
    	    return newest;
        }
    	return p;
    }

    public void createLevelOrder(E[] arr) {
        root = createLevelOrderHelper(arr, root, 0);
    }

    private Node<E> createLevelOrderHelper(E[] arr, Node<E> p, int i) {
		if (i < arr.length){
		    Node<E> newNode = createNode(arr[i], p, null, null);
		    newNode.setLeft(createLevelOrderHelper(arr, newNode.getLeft(), 2*i+1));
		    newNode.setRight(createLevelOrderHelper(arr, newNode.getRight(), 2*i+2));
		    size++;
		    return newNode;
        }
		return p;
    }

    private void inorderSubtree(Position<E> p, List<Position<E>> snapshot) {
        if (left(p) != null)
            inorderSubtree(left(p), snapshot);
        snapshot.add(p);
        if (right(p) != null)
            inorderSubtree(right(p), snapshot);
    }
// Returns an iterable collection of positions of the tree, reported in inorder. ∗/

    public Iterable<Position<E>> inorder( ) {
        List<Position<E>> snapshot = new ArrayList<>( );
        if (!isEmpty( ))
            inorderSubtree(root( ), snapshot); // fill the snapshot recursively
        return snapshot;
    }

    public Iterable<Position<E>> positions(){
        return inorder();
    }

    /**
     * Nested static class for a binary tree node.
     */
    protected static class Node<E> implements Position<E> {
        private E element;
        private Node<E> left, right, parent;


        public Node(E e, Node<E> p, Node<E> l, Node<E> r) {
        	element = e;
        	parent = p;
        	left = l;
        	right = r;
        }

        // accessor
        @Override
        public E getElement() throws IllegalStateException {
            return element;
        }

        public void setElement(E element) {
            this.element = element;
        }

        public Node<E> getLeft() {
            return left;
        }

        public void setLeft(Node<E> left) {
            this.left = left;
        }

        public Node<E> getRight() {
            return right;
        }

        public void setRight(Node<E> right) {
            this.right = right;
        }

        public Node<E> getParent() {
            return parent;
        }

        public void setParent(Node<E> parent) {
            this.parent = parent;
        }
//        ************** End of nested Node class *************************
    }
}