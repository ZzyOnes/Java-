package high_Class_Problem;
/*
 * ����һ������arr[0...i]����arr[i]��ʾ��i��Բ�����ڵ�λ��
 * ��������arr�ں�ŵ���������Ž�ĵڼ�����������������Ž��״̬����-1
 */
public class HanoiProblem {
	
	public static int step1(int[] arr) {
		if(arr==null||arr.length==0) {
			return -1;
		}
		return process(arr,arr.length-1,1,2,3);
	}
	//��0...i��Բ��ȫ���Ƶ�to����ȥ
	//����arr 0��i�ں�ŵ������ĵڼ���
	public static int process(int[] arr, int i, int from, int other, int to) {
		if(i==-1) {
			return 0;
		}
		if(arr[i]!=from&&arr[i]!=to) {
			return -1;
		}
		if(arr[i]==from) {
			return process(arr,i-1,from,to,other);
		}else {
			int rest = process(arr,i-1,other,from,to);
			if(rest==-1)return -1;
			else return 1<<i + rest;
		}
	}

	//�ݹ�ĵ���
	public static int step2(int[] arr) {
		if(arr==null||arr.length==0) {
			return -1;
		}
		int from = 1;
		int other = 2;
		int to = 3;
		int i = arr.length-1;
		int rest = 0;
		int temp = 0;
		while(i>=0) {
			if(arr[i]!=from&&arr[i]!=to) {
				return -1;
			}
			if(arr[i]==to) {
				rest += i==0? 1: 1<<(i-1);
				temp = from;
				from = other;
			}else {
				temp = to;
				to = other;
			}
			other = temp;
			i--;
		}
		return rest;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] test = {2,2,2,2,1};
		System.out.print(step1(test)+" "+step2(test));
	}

}
