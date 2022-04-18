package mid_Class_Problem;
//��һ��ʱ�临�Ӷ�O(n)�ռ临�Ӷ�O(1)�ķ���
//�ҳ�1~n����Щ��û�������ϳ���
public class PrintNoInArray {
	
	//�뱣֤arr[0...n-1]�ϵ�����1...n֮��
	public static void printNumberNoInArray(int[] arry) {
		if(arry.length=='0'||arry==null) {
			return;
		}
		for(int value:arry) { //��ȡ����iλ���Ϸŵ���i+1
			modify(value,arry);
		}
		for(int i=0;i<arry.length;i++) {
			if(arry[i]!=i+1) {
				System.out.print(" "+(i+1));
			}
		}
	}

	public static void modify(int value, int[] arry) {
		while(arry[value-1]!=value) {
			int temp = arry[value-1];
			arry[value-1] = value;
			value = temp;
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] test = {3,2,3,5,6,1,6};
		printNumberNoInArray(test);

	}

}
