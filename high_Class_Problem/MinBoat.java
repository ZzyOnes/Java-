package high_Class_Problem;

import java.util.Arrays;
import java.util.Comparator;

/*
 * ����һ�����飬����ÿ���˵��������ٸ���һ��limit����������������ÿ�������ֻ����2����
 * �ʣ�������Ҫ��������������ÿ���˹���
 */
public class MinBoat {
	
	//����Ƚ���
	public static class intComparator implements Comparator<Integer>{
		@Override
		public int compare(Integer o1, Integer o2) {
			return o1-o2;
		}
	}

	public static int minBoat(int[] arr, int limit) {
		//�������С��������
		Arrays.sort(arr);
		//���˵��������
		if(arr[arr.length-1]<=limit/2) {
			return (arr.length+1)/2;
		}
		if(arr[0]>limit/2) {
			return arr.length;
		}
		int index = -1;//�ҵ�С�ڵ���limit/2������λ��
		for(int i = arr.length-1;i>=0;i--) {
			if(arr[i]<=(limit/2)) {
				index = i;
				break;
			}
		}
		int L = index; //�����������ұ��
		int R = index+1; //�Ҳ������������
		int unused = 0; //ͳ�ƻ���ĸ���
		while(L>=0) {
			int sloved = 0;
			while(R<arr.length&&(arr[L]+arr[R])<=limit) {
				R++;
				sloved++;
			}
			if(sloved==0) {
				L--;
				unused++;
			}else {
				L = Math.max(-1,L-sloved);//�㶨��sloved����L�����Ƶ�����Խ��
			}
		}
		int leftNum = index+1;
		int rightNum = arr.length-leftNum;
		int used = leftNum - unused; // �㶨�ĸ���
		int moreUnsolve = rightNum - used; //�Ҳ໹δ�㶨��
		return used + (unused+1)/2 + moreUnsolve;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] testArr = {5,2,2,2,2,3,1,3,3,3,3,4,4,4,4};
		int testLimit = 6;
		System.out.print(minBoat(testArr,testLimit));
	}

}
