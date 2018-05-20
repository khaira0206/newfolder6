package binaryTree;

import java.util.LinkedList;
import java.util.Queue;



public class levelOrderTraversal {

	public static void main(String arg[]){
		levelOrderTraversal lot = new levelOrderTraversal();
		lot.root = new Node(1);
		lot.root.left = new Node(2);
		lot.root.right = new Node(3);
		lot.root.left.left = new Node(4);
		lot.root.left.right = new Node(5);
		lot.root.right.left = new Node(6);
		lot.root.right.right = new Node(7);
		lot.printLevelOrder();
		
	}
	
	Node root;
	
	public levelOrderTraversal(){
		root = null;
	}
	void printLevelOrder(){
		
	Queue<Node> queue = new LinkedList<Node>();
	queue.add(root);
	
	while(!queue.isEmpty()){
		
		Node node = queue.poll();
		System.out.print(node.data + " ");
		
		if(node.left != null){
			queue.add(node.left);
		}
		if(node.right != null){
			queue.add(node.right);
		}
		
	}
	
	}
}
