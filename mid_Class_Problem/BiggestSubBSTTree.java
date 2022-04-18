package mid_Class_Problem;

//��������������������Ľ�����
public class BiggestSubBSTTree {
	//�����
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

	//����Ҫ���ص���Ϣ
	public static class Info{
		public boolean isBST;//����ʡ��ֻ���жϣ�leftInfo.maxBSThead == x.left , rightInfo.maxBSThead == x.right
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
	
	//��ʼ�ݹ���·
	//������XΪͷ������������� ��������Ϣ
	public static Info process(Node X) {
		if(X==null) {
			return null;
		}
		Info leftInfo = process(X.left);//��������Ҫ��Ϣ
		Info rightInfo = process(X.right);//��������Ҫ��Ϣ
		//��ʼ���±�������Ϣ
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
		//������1
		if(leftInfo!=null) {
			maxBSThead = leftInfo.maxBSThead;
			maxBSTSize = leftInfo.maxBSTSize;
		}
		//������2
		if(rightInfo!=null&&rightInfo.maxBSTSize>maxBSTSize) {
			maxBSThead = rightInfo.maxBSThead;
			maxBSTSize = rightInfo.maxBSTSize;
		}
		//������3
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
