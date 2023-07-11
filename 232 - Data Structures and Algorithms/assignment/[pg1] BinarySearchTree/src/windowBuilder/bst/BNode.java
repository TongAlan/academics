package windowBuilder.bst;

public class BNode {
	
	public BNode left;
	public BNode right;
	public BNode parent;
	public int height = 1;
	public int key; 
	
	//Constructor 
	public BNode(int d) {
		left = null;
		right = null;
		key = d;
	}
	
	public BNode getLeft() {
		return left;
	}
	
	public BNode getRight() {
		return right;
	}
	
	public int getKey() {
		return key;
	}
	
	
}
