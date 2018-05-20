package binaryTree;

public class levelOrderTraversal1 {

	public static void main(String arg[]){
		levelOrderTraversal1 lot = new levelOrderTraversal1();
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
	public levelOrderTraversal1(){
		root = null;
	}
	
	void printLevelOrder(){
		
		int h = height(root);
		System.out.println("height of tree "+ h);
		for(int i = 1; i <= h; i++){
			printGivenLevel(root, i);
			System.out.println();
		}
	}
	
	public int height(Node node){
		
		if(node == null){
			return 0;
		}
		else{
			int lheight = height(node.left);
			int rheight = height(node.right);
			
			if(lheight > rheight){
				return lheight + 1;
			}
			else{
	      		return rheight + 1;
				
			}
			
		}
	}
	public void printGivenLevel(Node node , int level){
		
		if(node == null){
			return;
		}
		if(level == 1){
			System.out.print(node.data +" ");
		}
		else if(level > 1){
			printGivenLevel(node.left, level - 1);
			printGivenLevel(node.right, level - 1);
		}
	}
	
}
