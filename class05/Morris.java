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
			mostRight = cur.left;//mostRight��cur������
			if(mostRight!=null) {//��������
				while(mostRight.right!=null&&mostRight.right!=cur) {
					mostRight = mostRight.right;//�ҵ������������ҽ��
				}
				if(mostRight.right==null) {//��һ������cur
					mostRight.right = cur;//ָ��cur
					cur = cur.left;//cur������
					continue;
				}else {//�ڶ�������cur
					mostRight.right = null;
				}
			}
			cur = cur.right;//cur�����ߵڶ��ξ����ý��
		}
	}
	//�������
	public static void morrisPre(Node head) {
		if(head==null) {
			return ;
		}
		Node cur = head;
		Node mostRight = null;
		while(cur!=null) {
			mostRight = cur.left;//mostRight��cur������
			if(mostRight!=null) {//��������
				while(mostRight.right!=null&&mostRight.right!=cur) {
					mostRight = mostRight.right;//�ҵ������������ҽ��
				}
				if(mostRight.right==null) {//��һ������cur
					System.out.print(cur);//������Ϊ
					mostRight.right = cur;//ָ��cur
					cur = cur.left;//cur������
					continue;
				}else {//�ڶ�������cur
					mostRight.right = null;
				}
			}else {//���û��������
				System.out.print(cur);
			}
			cur = cur.right;//cur�����ߵڶ��ξ����ý��
		}
	}
	//�������
	public static void morrisIn(Node head) {
		if(head==null) {
			return ;
		}
		Node cur = head;
		Node mostRight = null;
		while(cur!=null) {
			mostRight = cur.left;//mostRight��cur������
			if(mostRight!=null) {//��������
				while(mostRight.right!=null&&mostRight.right!=cur) {
					mostRight = mostRight.right;//�ҵ������������ҽ��
				}
				if(mostRight.right==null) {//��һ������cur
					mostRight.right = cur;//ָ��cur
					cur = cur.left;//cur������
					continue;
				}else {//��ԭ
					mostRight.right = null;
				}
			}
			System.out.print(cur);
			cur = cur.right;//cur�����ߵڶ��ξ����ý��
		}
	}
	//�������
	public static void morrisPos(Node head) {
		if(head==null) {
			return ;
		}
		Node cur = head;
		Node mostRight = null;
		while(cur!=null) {
			mostRight = cur.left;//mostRight��cur������
			if(mostRight!=null) {//��������
				while(mostRight.right!=null&&mostRight.right!=cur) {
					mostRight = mostRight.right;//�ҵ������������ҽ��
				}
				if(mostRight.right==null) {//��һ������cur
					mostRight.right = cur;//ָ��cur
					cur = cur.left;//cur������
					continue;
				}else {//��ԭ
					mostRight.right = null;
					//�ڶ��������Լ��Ȼ�ԭ�ٴ�ӡ
					printEdge(cur);
				}
			}
			cur = cur.right;//cur�����ߵڶ��ξ����ý��
		}
	}
	//��xΪͷ���������ӡ��������ұ߽�
	public static void printEdge(Node x) {
		Node tail = reverseEdge(x);
		Node cur = tail;
		while(cur!=null) {
			System.out.print(cur);
			cur = cur.right;
		}
		reverseEdge(tail);//�ٴη�ת
	}
	//����xΪͷ�ı߽�ָ������
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
	//��morris��������ж��Ƿ����������� (���Ž�)
		public static boolean IsBST(Node head) {
			if(head==null) {
				return true;
			}
			int preValue = Integer.MIN_VALUE;
			Node cur = head;
			Node mostRight = null;
			while(cur!=null) {
				mostRight = cur.left;//mostRight��cur������
				if(mostRight!=null) {//��������
					while(mostRight.right!=null&&mostRight.right!=cur) {
						mostRight = mostRight.right;//�ҵ������������ҽ��
					}
					if(mostRight.right==null) {//��һ������cur
						mostRight.right = cur;//ָ��cur
						cur = cur.left;//cur������
						continue;
					}else {//��ԭ
						mostRight.right = null;
					}
				}
				if(cur.value<=preValue) {
					return false;
				}
				preValue = cur.value;
				//System.out.print(cur);
				cur = cur.right;//cur�����ߵڶ��ξ����ý��
			}
			return true;
		}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
