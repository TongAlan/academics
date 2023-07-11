package windowBuilder.bst;

public class BST {
	public BNode root;
	public static int heightOfTree;
	
	//Instance Variables to Print For GUI instead of the console
	public String inOrderString = "";
	public String PreOrderString = "";
	public String PostOrderString = "";
	public String BST_LBL = "";
	public String searchResult = "";

	public BST() {
		root = null;
	}

	public boolean isEmpty() {
		return root == null;
	}

	//Insert Method 
	public void insert(int d) {
		BNode newNode = new BNode(d);
		if(isEmpty()) {
			root = newNode;
			return;
		} 
		if(!isEmpty()) {
			BNode current = root;
			BNode pNode = null;
			while(current != null) {
				pNode = current;

				if(pNode.key < newNode.key) {
					current = current.getRight();
					newNode.height++;

				} else if(pNode.key > newNode.key) {
					current = current.getLeft();
					newNode.height++;
				}
				if(newNode.height > heightOfTree) {
					heightOfTree = newNode.height;
				}
			}
			if(pNode.key > newNode.key) {
				pNode.left = newNode;
				newNode.parent = pNode;
			} else if(pNode.key < newNode.key) {
				pNode.right = newNode;
				newNode.parent = pNode;
			}
		}
	}

	// Recursive Print BST By Level
	public void printLevelOrder(BNode root)
	{
		int h = heightOfTree;
		for (int i=1; i<=h; i++)
		{
			printGivenLevel(root, i);
			BST_LBL = BST_LBL + "\n";
		}
	}

	void printGivenLevel(BNode root, int l)
	{
		if (root == null) {
			return;
		}
		if (l == 1) {
			BST_LBL = BST_LBL + root.key + " ";
		}	
		else if (l > 1){
			printGivenLevel(root.left, l-1);
			printGivenLevel(root.right, l-1);
		}
	}

	// In-Order Traversal 
	public void inOrder(BNode root) {
		if(root == null) {
			return;
		}
		inOrder(root.left);
		inOrderString = inOrderString + root.key + ", ";
		inOrder(root.right);
	}

	//Post-Order Traversal
	public void postOrder(BNode root) {
		if(root == null) {
			return;
		}
		postOrder(root.left);
		postOrder(root.right);
		PostOrderString = PostOrderString + root.key + ", ";
	}

	//Pre-Order Traversal
	public void preOrder(BNode root) {
		if(root == null) {
			return;
		}
		PreOrderString = PreOrderString + root.key + ", ";
		preOrder(root.left);
		preOrder(root.right);
	}

	// Method to Count the Number of Child/Children
	public int countChildren(BNode n) {
		if(n.left == null && n.right == null) {
			return 0;
		} else if(n.left != null && n.right != null) {
			return 2;
		} else {
			return 1;
		}
	}

	// Finds the Last Successor of the Given Node
	public BNode findLastSuccessor(BNode n) {
		BNode cur = n.right;
		while(cur.left != null) {
			cur = cur.left;
		}
		return cur;
	}

	//Clear the BST
	public void clearBST() {
		root = null;
	}

	// Delete Node based on Key
	public BNode delete(int key) {
		BNode n = findNodeFromKey(key, root);

		if(n == root) {
			if(countChildren(root) == 0) {
				root = null;
			} else if(countChildren(root) == 1) {
				if(root.left == null) {
					root.right.parent = null;
					root = root.right;
				} else if(root.right == null) {
					root.left.parent = null;
					root = root.left;
				}
			} else if(countChildren(root)== 2) {
				BNode lastSuccessor = findLastSuccessor(n);
				if(lastSuccessor == n.right) {
					lastSuccessor.left = n.left;
					n.left.parent = lastSuccessor;
					lastSuccessor.parent = n.parent;
					root = lastSuccessor;
				} else if(lastSuccessor != n.right) {
					lastSuccessor.parent.left = lastSuccessor.right;
					if(lastSuccessor.right != null) {
						lastSuccessor.right.parent = lastSuccessor.parent;
					}
					lastSuccessor.right = n.right;
					n.right.parent = lastSuccessor;

					lastSuccessor.left = n.left;
					n.left.parent = lastSuccessor;
					lastSuccessor.parent = n.parent;
					root = lastSuccessor;
				}
			}
		} else {
			if(countChildren(n) == 0) {
				if(n.parent.left == n) {
					n.parent.left = null;
				} else {
					n.parent.right = null;
				}
			} else if(countChildren(n) == 1) {
				if(n.left == null) {
					n.right.parent = n.parent;
					if(n.parent.left == n) {
						n.parent.left = n.right;
					} else if(n.parent.right == n){
						n.parent.right = n.right;
					}
				} else if(n.right == null) {
					n.left.parent = n.parent;
					if(n.parent.left == n) {
						n.parent.left = n.left;
					} else if(n.parent.right == n){
						n.parent.right = n.left;
					}
				}
			} else if(countChildren(n) == 2){ 
				BNode lastSuccessor = findLastSuccessor(n);
				if(lastSuccessor == n.right) {
					lastSuccessor.left = n.left;
					n.left.parent = lastSuccessor;
					lastSuccessor.parent = n.parent;
					if(n.parent.right == n) {
						n.parent.right = lastSuccessor;
					} else if(n.parent.left == n) {
						n.parent.left = lastSuccessor;
					}
				} else if(lastSuccessor != n.right) {
					lastSuccessor.parent.left = lastSuccessor.right;
					if(lastSuccessor.right != null) {
						lastSuccessor.right.parent = lastSuccessor.parent;
					}
					lastSuccessor.right = n.right;
					n.right.parent = lastSuccessor;

					lastSuccessor.left = n.left;
					n.left.parent = lastSuccessor;
					lastSuccessor.parent = n.parent;
					if(n.parent.right == n) {
						n.parent.right = lastSuccessor;
					} else if(n.parent.left == n) {
						n.parent.left = lastSuccessor;
					}
				}
			}
		}
		return n;
	}

	// Boolean Method to Find a Given Node...uses findNodeFromKey method
	public boolean search(int n, BNode current) {
		if(findNodeFromKey(n, current) == null) {
			searchResult = "False";
			return false;
		} else {
			searchResult = "True";
			return true;
		}
	}

	// Recursive Method to find Node based on Key...similar to search method
	public BNode findNodeFromKey(int key, BNode current) {
		if(isEmpty()) {
			return current;
		}
		if(current.key == key) {
			return current;
		}
		if(current.key < key) {
			if(current.right == null) {
				return null;
			}
			return findNodeFromKey(key, current.right);
		}
		if(current.key > key) {
			if(current.left == null) {
				return null;
			}
			return findNodeFromKey(key, current.left);
		}
		return null;
	}
}










