package class04;

public class MaxDistanceInTree {
	
	
	public static class Node{
		public int value;
		public Node left;
		public Node right;
		
		public Node(int data) {
			this.value = data;
		}
	}

	public static class Info{
		public int maxDistance;
		public int height;
		
		public Info(int distance,int height) {
			this.height = height;
			this.maxDistance = distance;
		}
	}
	
	public static int maxDistance(Node head) {
		return Process(head).maxDistance;
	}
	
	//返回以x为头整棵树的两个信息
	public static Info Process(Node x) {
		if(x==null) {
			return new Info(0,0);
		}
		Info leftTree = Process(x.left);
		Info rightTree = Process(x.right);
		int p1 = leftTree.maxDistance;
		int p2 = rightTree.maxDistance;
		int p3 = leftTree.height+rightTree.height+1;
		int maxDistance = Math.max(p1,Math.max(p2,p3));
		int height = Math.max(leftTree.height,rightTree.height) + 1;
		return new Info(maxDistance,height);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
