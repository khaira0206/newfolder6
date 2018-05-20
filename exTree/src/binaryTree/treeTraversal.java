package binaryTree;

class Node{
	int data;
	Node left, right;
	
	public Node(int data){
		this.data = data;
		this.left = this.right =null;
	}
}

public class treeTraversal {

	public static void main(String arg[]){
		
		treeTraversal tree = new treeTraversal();
		tree.root = new Node(1);
		tree.root.left =new Node(2);
		tree.root.right = new Node(3);
		tree.root.left.left = new Node(4);
		tree.root.left.right = new Node(5);
		
		System.out.println("PreOrder traversal is ");
		tree.preOrder(tree.root);
		System.out.println("InOrder traversal is ");
		tree.inOrder(tree.root);
		System.out.println("PostOrder traversal is ");
		tree.postOrder(tree.root);
	}
	
	Node root;
	
	 treeTraversal(){
		root = null;
	}
	 
	 public void preOrder(Node node){
		 
		 if (node == null)
	            return;
		 
		 System.out.println(node.data + " ");
		 preOrder(node.left);
		 preOrder(node.right);
		 
	 }
	 public void inOrder(Node node){
		
		 if (node == null)
	            return;
		 
		 inOrder(node.left);
		 System.out.println(node.data + " ");
		 inOrder(node.right); 
	 }
	 public void postOrder(Node node){
		 
		 if (node == null)
	            return;
		 
		 postOrder(node.left);
		 postOrder(node.right);
		 System.out.println(node.data + " ");
	 }
	
	
}
