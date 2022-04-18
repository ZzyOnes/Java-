package mid_Class_Problem;

//������������ת����˫������
public class BSTtoDoubleLink {

	//�������������
	public static class Node{
		public int value;
		public Node left; //��ָ���Ϊlast
		public Node right;//��ָ���Ϊnext
		
		public Node(int v,Node l,Node r) {
			this.value = v;
			this.left = l;
			this.right = r;
		}
	}
	//����������ת����˫������󷵻ص�ͷ��β
	public static class Info{
		Node start;
		Node end;
		
		public Info(Node s,Node e) {
			this.start = s;
			this.end = e;
		}
	}
	
	//��XΪͷ����������������ȫ���������˫������ķ�ʽ���Ӻ�
	//���ҷŻ����������ͷ��β
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
