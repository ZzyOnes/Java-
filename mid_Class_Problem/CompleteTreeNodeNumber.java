package mid_Class_Problem;
//�ݹ���������ȫ������������
public class CompleteTreeNodeNumber {

	public static class Node{
		public int value;
		public Node left;
		public Node right;
		
		public Node(int v) {
			this.value = v;
		}
	}

	//����xΪͷ������ȫ�������Ľ�����
	public static int process(Node x,int level,int h) {
		if(level==h) {
			return 1;
		}
			if(findTreeLeft(x.right,level+1)==h) {//����������������㵽�����һ���ʾ����������
				return (1<<(h-level))+process(x.right,level+1,h);
			}else {//���û�����һ�㣬���ʾ����������
				return (1<<(h-level-1))+process(x.left,level+1,h);//1<<(h-level-1)�ǵü����Ų�Ȼ�����
			}
	}
	//�ҵ���YΪͷ������������
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
	
	//�ڶ��ֵݹ�
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
