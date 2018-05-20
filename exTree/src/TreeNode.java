
class TreeNode{
	
	int data;
	TreeNode left, right, parent;
	int size;
	
	class Result{
		
		public TreeNode node;
		public boolean isAncestor;
		
		public Result(TreeNode n, boolean isAnc){
			node = n;
			isAncestor = isAnc;
		}
	}
	
	public TreeNode(int d)
	{
		data = d;
		size = 1;
	}
	
	public void insertInOrder(int d)
	{
		if(d <= data){
			if(left == null)
			{
				setLeftNode(new TreeNode(d));
			}
			else{
				left.insertInOrder(d);
			}
		}
		else{
			if(right == null)
			{
				setRightNode(new TreeNode(d));
			}
			else{
				right.insertInOrder(d);
			}
		}
		
		size++;
	}
	
	public void setLeftNode( TreeNode d)
	{
		this.left = d;
		if(left != null){
			left.parent = this;
		}
		
		
	}
	public void setRightNode( TreeNode d)
	{
		this.right = d;
		if(right != null){
			right.parent = this;
		}
		
	}
	 public TreeNode find(int d){
		 if(d == data){
			 return this;
		 }
		 else if(d <= data){
			return left != null ? left.find(d) : null ;	  
		 }
		 else{
			 return right != null ? right.find(d) : null;
		 }
		 
		 
	 }
	 boolean isBalanced(TreeNode root){
		 return checkHeight(root) != Integer.MIN_VALUE;
		 
	 }
	 int checkHeight(TreeNode root){
		 if(root == null){
			 return -1;
		 }
		 int leftHeight = checkHeight(root.left);
		 if(leftHeight == Integer.MIN_VALUE) return Integer.MIN_VALUE;
		 
		 int rightHeight = checkHeight(root.right);
		 if(rightHeight == Integer.MIN_VALUE) return Integer.MIN_VALUE;
			 
		 int heightdiff = leftHeight - rightHeight;
		 
		 if(Math.abs(heightdiff) > 1)
		 {
			 return Integer.MIN_VALUE;
		 }
		 else{
			 
			 return Math.max(checkHeight(root.left), checkHeight(root.right)) + 1;
		 }
	 }
	 
	TreeNode commonAncestor(TreeNode root, TreeNode p, TreeNode q)
	{
		Result r = commonAncHelper(root, p, q);
	    if(r.isAncestor){
	    	return r.node;
	    	
	    }
	    return null;
	}
	Result commonAncHelper(TreeNode root, TreeNode p, TreeNode q){
		
		if(root == null){
			return new Result(null, false);
		}
		
		if(root == p && root == q){
			System.out.println("WTF");
			return new Result(root, true);
		}
		Result rx = commonAncHelper(root.left, p, q);
		if(rx.isAncestor){
			return rx;
		}
		Result ry = commonAncHelper(root.right, p ,q);
		if(ry.isAncestor){
			return ry;
		}
		
		if(rx.node != null && ry.node != null){
			return new Result(root, true);
		}
		else if(root == p || root == q){
			boolean isAncestor = rx.node != null || ry.node != null;
			return new Result(root, isAncestor);
		}
		else{
			return new Result(rx.node != null ? rx.node : ry.node, false);
		}
	}
	
	public static void main(String arg[])
	{
		TreeNode tn =  new TreeNode(35);
		
		tn.insertInOrder(3);
		tn.insertInOrder(15);
		tn.insertInOrder(25);
		tn.insertInOrder(45);
		tn.isBalanced(tn);
		System.out.println("Is present in the tree ");
		System.out.println( tn.find(3)!= null? "Yes" : "No");
		System.out.println("Is tree Balanaced ");
		System.out.println(tn.isBalanced(tn)== true ? "Yes" : "No");
		TreeNode tn1 = tn.commonAncestor(tn ,tn.left.right,tn.right );
		System.out.println("Common Ancestor"+tn1.data);
		
	}
}