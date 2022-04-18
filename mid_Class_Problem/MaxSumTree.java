package mid_Class_Problem;
/*
 * 求从根节点到叶节点的每条路径和之中最大的路径和
 */
public class MaxSumTree {
	
	public static class Node{
		public int value;
		public Node left;
		public Node right;
		
		public Node(int val) {
			this.value=val;
		}
	}

	//定义全局变量记录所以路径的最大权值和
	public static int maxSum = Integer.MIN_VALUE;
	
	public static int Process1(Node head) {
		p(head,0);
		return maxSum;
	}
	//pre是从x节点到当前结点的父节点的路径和
	public static void p(Node x,int pre) {
		if(x.left==null&&x.right==null) {
			maxSum = Math.max(maxSum, pre+x.value);
			return ;
		}
		if(x.left!=null) {
			p(x.left,pre+x.value);
		}
		if(x.right!=null) {
			p(x.right,pre+x.value);
		}
	}
	//next是以x为根节点到叶结点的路径和
	public static int Process2(Node x) {
		if(x.left==null&&x.right==null) {
			return x.value;
		}
		int next = Integer.MIN_VALUE;
		if(x.left!=null) {
			next = Process2(x.left);
		}
		if(x.right!=null) {
			next = Math.max(next, Process2(x.right));
		}
		return x.value+next;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
