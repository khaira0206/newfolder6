class Node{
	int data;
	Node left, right;
	int size;
	
	public Node(int d)
	{
		data = d;
		size = 1;
	}
	
	
}
public class treeFromArray {
	
	Node createMinimalBST(int array[]){
		
		return createMinimalBST(array, 0, array.length - 1);
	}
	
	Node createMinimalBST(int array[], int start, int end){
		
		if(end < start){
			return null;
		}
		
		int mid = (start + end)/2;	
		Node n = new Node(array[mid]);
		n.left = createMinimalBST(array, start, mid-1);
		n.right = createMinimalBST(array, mid + 1, end);
		
		return n;
	}
	
	void show(Node n){
		
		while(n != null)
		{
			System.out.println("left node"+ n.left+"right nodes"+n.right);
			
		}
	}
	
public static void main(String arg[])
{
	int[] array = {1,2,3,4,5,6,7,8,9};
	
	treeFromArray tfa = new treeFromArray();
	tfa.createMinimalBST(array);
	//tfa.show(5);
}

}
