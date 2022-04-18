package mid_Class_Problem;

//将搜索二叉树转换成双向链表
public class BSTtoDoubleLink {

	//搜索二叉树结点
	public static class Node{
		public int value;
		public Node left; //左指针变为last
		public Node right;//右指针变为next
		
		public Node(int v,Node l,Node r) {
			this.value = v;
			this.left = l;
			this.right = r;
		}
	}
	//搜索二叉树转换成双向链表后返回的头和尾
	public static class Info{
		Node start;
		Node end;
		
		public Info(Node s,Node e) {
			this.start = s;
			this.end = e;
		}
	}
	
	//以X为头结点的搜索二叉树，全部以有序的双向链表的方式连接好
	//并且放回整个链表的头和尾
	public static Info process(Node X) {
		if(X==null) {
			return 	new Info(null,null);
		}
		Info leftInfo = process(X.left);
		Info rightInfo = process(X.right);
		if(leftInfo.end!=null) {
			leftInfo.end.right = X;
		}
		X.left = leftInfo.end;
		X.right = rightInfo.start;
		if(rightInfo.start!=null) {
			rightInfo.start.left = X;
		}
		return new Info(leftInfo.start!=null?leftInfo.start:X,rightInfo.end!=null?rightInfo.end:X);
	}
	
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
