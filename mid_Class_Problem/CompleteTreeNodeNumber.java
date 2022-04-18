package mid_Class_Problem;
//递归快速求解完全二叉数结点个数
public class CompleteTreeNodeNumber {

	public static class Node{
		public int value;
		public Node left;
		public Node right;
		
		public Node(int v) {
			this.value = v;
		}
	}

	//求以x为头结点的完全二叉数的结点个数
	public static int process(Node x,int level,int h) {
		if(level==h) {
			return 1;
		}
			if(findTreeLeft(x.right,level+1)==h) {//如果右子树的最左结点到了最后一层表示左子树满了
				return (1<<(h-level))+process(x.right,level+1,h);
			}else {//如果没到最后一层，则表示右子树满了
				return (1<<(h-level-1))+process(x.left,level+1,h);//1<<(h-level-1)记得加括号不然会出错
			}
	}
	//找到以Y为头的最左结点的深度
	public static int findTreeLeft(Node y,int level) {
		while(y!=null) {
			level++;
			y = y.left;
		}
		return level-1;
	}
	
	public static int GetCompleteTreeNumber(Node head) {
		if(head==null)return 0;
		int h = findTreeLeft(head,1);
		System.out.println(h);
		int ans = process(head,1,h);
		return ans;
	}
	
	//第二种递归
	static int pans=0;
	public static int p(Node x,int level) {
		if(level==1) {
			return 1;
		}
		if(x.right!=null) {
			if(f(x.right)==level-1) {
				pans += (1<<(level-1))+p(x.right,level-1);
			}else {
				pans += (1<<(level-2))+p(x.left,level-1);
			}
		}else {
			return 2;
		}
		return pans;
	}
	public static int f(Node y) {
		if(y==null)return 0;
		int ans=1;
		while(y.left!=null) {
			ans++;
			y=y.left;
		}
		return ans;
	}
	public static int Get(Node head) {
		if(head==null)return 0;
		int level = f(head);
		int ans = p(head,level);
		return ans;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Node head = new Node(1);
		head.left = new Node(2);
		head.right = new Node(3);
		head.left.left = new Node(4);
		head.left.right = new Node(5);
		head.right.left = new Node(6);
		head.right.right = new Node(7);
		head.left.left.left = new Node(8);
		head.left.left.right = new Node(9);
		int ans1 = GetCompleteTreeNumber(head);
		int ans2 = Get(head);
		System.out.print(ans1 + " " + ans2);
	}

}
