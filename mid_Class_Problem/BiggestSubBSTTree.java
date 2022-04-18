package mid_Class_Problem;

//返回最大搜索二叉子树的结点个数
public class BiggestSubBSTTree {
	//树结点
	public static class Node{
		public int value;
		public Node left; 
		public Node right;
		
		public Node(int v,Node l,Node r) {
			this.value = v;
			this.left = l;
			this.right = r;
		}
	}

	//设置要返回的信息
	public static class Info{
		public boolean isBST;//可以省略只需判断：leftInfo.maxBSThead == x.left , rightInfo.maxBSThead == x.right
		public Node maxBSThead;
		public int min;
		public int max;
		public int maxBSTSize;
		
		public Info(boolean isBST,Node maxBSThead,int min,int max,int maxBSTSize) {
			this.isBST = isBST;
			this.maxBSThead = maxBSThead;
			this.min = min;
			this.max = max;
			this.maxBSTSize = maxBSTSize;
		}
	}
	
	//开始递归套路
	//返回以X为头的最大搜索二叉 子树的信息
	public static Info process(Node X) {
		if(X==null) {
			return null;
		}
		Info leftInfo = process(X.left);//向左子树要信息
		Info rightInfo = process(X.right);//向右子树要信息
		//开始更新本结点的信息
		int min=X.value;
		int max=X.value;
		boolean isBST=false;
		Node maxBSThead=null;
		int maxBSTSize=0;
		if(leftInfo!=null) {
			min = Math.min(leftInfo.min, min);
			max = Math.max(leftInfo.max, max);
		}
		if(rightInfo!=null) {
			min = Math.min(rightInfo.min, min);
			max = Math.max(rightInfo.max, max);
		}
		//可能性1
		if(leftInfo!=null) {
			maxBSThead = leftInfo.maxBSThead;
			maxBSTSize = leftInfo.maxBSTSize;
		}
		//可能性2
		if(rightInfo!=null&&rightInfo.maxBSTSize>maxBSTSize) {
			maxBSThead = rightInfo.maxBSThead;
			maxBSTSize = rightInfo.maxBSTSize;
		}
		//可能性3
		if((leftInfo==null||leftInfo.isBST)&&(rightInfo==null||rightInfo.isBST)) {
			if((leftInfo==null||leftInfo.max<X.value)&&(rightInfo==null||rightInfo.min>X.value)) {
				maxBSThead = X;
				isBST = true;
				int maxleftsize = leftInfo==null?0:leftInfo.maxBSTSize;
				int maxrightsize = rightInfo==null?0:rightInfo.maxBSTSize;
				maxBSTSize = maxleftsize+maxrightsize+1;
			}
		}
		return new Info(isBST, maxBSThead, min, max, maxBSTSize);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
