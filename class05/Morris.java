package class05;

public class Morris {

	public static class Node{
		public Node left;
		public Node right;
		public int value;
		public Node(int v) {
			this.value = v;
		}
	}
	
	public static void morris(Node head) {
		if(head==null) {
			return ;
		}
		Node cur = head;
		Node mostRight = null;
		while(cur!=null) {
			mostRight = cur.left;//mostRight是cur的左孩子
			if(mostRight!=null) {//有左子树
				while(mostRight.right!=null&&mostRight.right!=cur) {
					mostRight = mostRight.right;//找到左子树的最右结点
				}
				if(mostRight.right==null) {//第一次来到cur
					mostRight.right = cur;//指向cur
					cur = cur.left;//cur往左走
					continue;
				}else {//第二次来的cur
					mostRight.right = null;
				}
			}
			cur = cur.right;//cur往右走第二次经过该结点
		}
	}
	//先序遍历
	public static void morrisPre(Node head) {
		if(head==null) {
			return ;
		}
		Node cur = head;
		Node mostRight = null;
		while(cur!=null) {
			mostRight = cur.left;//mostRight是cur的左孩子
			if(mostRight!=null) {//有左子树
				while(mostRight.right!=null&&mostRight.right!=cur) {
					mostRight = mostRight.right;//找到左子树的最右结点
				}
				if(mostRight.right==null) {//第一次来到cur
					System.out.print(cur);//处理行为
					mostRight.right = cur;//指向cur
					cur = cur.left;//cur往左走
					continue;
				}else {//第二次来的cur
					mostRight.right = null;
				}
			}else {//如果没有左子树
				System.out.print(cur);
			}
			cur = cur.right;//cur往右走第二次经过该结点
		}
	}
	//中序遍历
	public static void morrisIn(Node head) {
		if(head==null) {
			return ;
		}
		Node cur = head;
		Node mostRight = null;
		while(cur!=null) {
			mostRight = cur.left;//mostRight是cur的左孩子
			if(mostRight!=null) {//有左子树
				while(mostRight.right!=null&&mostRight.right!=cur) {
					mostRight = mostRight.right;//找到左子树的最右结点
				}
				if(mostRight.right==null) {//第一次来到cur
					mostRight.right = cur;//指向cur
					cur = cur.left;//cur往左走
					continue;
				}else {//回原
					mostRight.right = null;
				}
			}
			System.out.print(cur);
			cur = cur.right;//cur往右走第二次经过该结点
		}
	}
	//后序遍历
	public static void morrisPos(Node head) {
		if(head==null) {
			return ;
		}
		Node cur = head;
		Node mostRight = null;
		while(cur!=null) {
			mostRight = cur.left;//mostRight是cur的左孩子
			if(mostRight!=null) {//有左子树
				while(mostRight.right!=null&&mostRight.right!=cur) {
					mostRight = mostRight.right;//找到左子树的最右结点
				}
				if(mostRight.right==null) {//第一次来到cur
					mostRight.right = cur;//指向cur
					cur = cur.left;//cur往左走
					continue;
				}else {//还原
					mostRight.right = null;
					//第二次来的自己先还原再打印
					printEdge(cur);
				}
			}
			cur = cur.right;//cur往右走第二次经过该结点
		}
	}
	//以x为头的树逆序打印这棵树的右边界
	public static void printEdge(Node x) {
		Node tail = reverseEdge(x);
		Node cur = tail;
		while(cur!=null) {
			System.out.print(cur);
			cur = cur.right;
		}
		reverseEdge(tail);//再次翻转
	}
	//将以x为头的边界指针逆序
	public static Node reverseEdge(Node x) {
		Node pre = null;
		Node next = null;
		while(x!=null) {
			next = x.right;
			x.right = pre;
			pre = x;
			x = next;
		}
		return pre;
	}
	//用morris中序遍历判断是否线索二叉树 (最优解)
		public static boolean IsBST(Node head) {
			if(head==null) {
				return true;
			}
			int preValue = Integer.MIN_VALUE;
			Node cur = head;
			Node mostRight = null;
			while(cur!=null) {
				mostRight = cur.left;//mostRight是cur的左孩子
				if(mostRight!=null) {//有左子树
					while(mostRight.right!=null&&mostRight.right!=cur) {
						mostRight = mostRight.right;//找到左子树的最右结点
					}
					if(mostRight.right==null) {//第一次来到cur
						mostRight.right = cur;//指向cur
						cur = cur.left;//cur往左走
						continue;
					}else {//回原
						mostRight.right = null;
					}
				}
				if(cur.value<=preValue) {
					return false;
				}
				preValue = cur.value;
				//System.out.print(cur);
				cur = cur.right;//cur往右走第二次经过该结点
			}
			return true;
		}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
