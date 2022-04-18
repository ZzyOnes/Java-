package mid_Class_Problem;
/*
 * ��Ӹ��ڵ㵽Ҷ�ڵ��ÿ��·����֮������·����
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

	//����ȫ�ֱ�����¼����·�������Ȩֵ��
	public static int maxSum = Integer.MIN_VALUE;
	
	public static int Process1(Node head) {
		p(head,0);
		return maxSum;
	}
	//pre�Ǵ�x�ڵ㵽��ǰ���ĸ��ڵ��·����
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
	//next����xΪ���ڵ㵽Ҷ����·����
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
