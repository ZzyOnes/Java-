package high_Class_Problem;
/*
 * Լɪ�����⣺
 * ����������������˭��Ҫ��ʱ�临�Ӷ�ΪO(n)
 */
public class JosephusProblem {
	
	public static class Node{
		Node next;
		String pepole;
		
		public Node(String p) {
			this.pepole = p;
		}
	}
	
	public static Node josephusKill2(Node head,int m) {
		if(head==null||head.next==head||m<1) {
			return head;
		}
		Node cur = head.next;
		int temp = 1;
		while(cur!=head) {
			temp++;
			cur = cur.next;
		}
		temp = getLiveNum(temp,m);//ͨ������ֱ������ĸ������
		while(--temp!=0) {
			head = head.next;
		}
		return head;
	}

	//�õ�һ��i���ڵ㣬����m�ͻ��������ջ��������˵ı���Ƕ���
	//�ϱ�� = (�±��+m-1)%i+1
	public static int getLiveNum(int i,int m) {
		if(i==1) {
			return 1;
		}
		return (getLiveNum(i-1,m)+m-1)%i+1;
	}
	
	//n����Χ��һȦ������ѭ��ȡ��arr�е�����
	//ɱn-1�ַ��ػ���˵ı��
	public static int Live(int n,int[] arr) {
		return num(n,arr,0);
	}
	
	//��ʣi���ˣ���ǰȡ�����ִ�indexλ�ÿ�ʼ
	//���ػ��ŵ��˵ı��
	public static int num(int i,int[] arr,int index) {
		if(i==1) {
			return 1;
		}
		//�� = (��+m-1)%i+1
		return (num(i-1,arr,nextIndex(index,arr.length))+arr[index]+1)%i+1;
	}
	//����index����һ��λ��
	public static int nextIndex(int index,int size) {
		return index==size-1?0:index+1;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
