package ���β���;

/*
 * ������������
 */
public class FindMaxSubArrSum {
	
	//��Խ�е�ʱ�����������ͣ�����[��磬�ҽ磬����]
	public static int[] findMaxCrossingSubArray(int[] arr,int low, int mid, int hight) {
		int[] res = new int[3];
		int leftSum = Integer.MIN_VALUE;
		int sum = 0;
		int maxLeft = mid;
		for(int i=mid;i>=low;i--) {
			sum += arr[i];
			if(sum>leftSum) {
				leftSum = sum;
				maxLeft = i;
			}
		}
		int rightSum = Integer.MIN_VALUE;
		int maxRight = mid + 1;
		sum = 0;
		for(int i=mid+1;i<=hight;i++) {
			sum += arr[i];
			if(sum>rightSum) {
				rightSum = sum;
				maxRight = i;
			}
		}
		res[0] = maxLeft;
		res[1] = maxRight;
		res[2] = rightSum + leftSum;
		return res;
	}

	//���÷��β��������������ͣ�����[��磬�ҽ磬����]
	public static int[] findMaxSubArray1(int[] arr,int low,int hight) {
		int[] res = new int[3];
		if(low==hight) {
			res[0] = low;res[1] = hight;res[2] = arr[low];
			return res;
		}
		int mid = (low+hight)/2;
		int[] leftRes = findMaxSubArray1(arr,low,mid);
		int[] rightRes = findMaxSubArray1(arr,mid+1,hight);
		int[] crossRes = findMaxCrossingSubArray(arr,low,mid,hight);
		if(leftRes[2]>rightRes[2]&&leftRes[2]>crossRes[2]) {
			return leftRes;
		}
		if(rightRes[2]>leftRes[2]&&rightRes[2]>crossRes[2]) {
			return rightRes;
		}
		return crossRes;
	}
	
	//�ǵݹ飬����ʱ����㷨
	public static int[] findMaxSubArray2(int[] arr) {
		if(arr==null||arr.length==0) {
			return null;
		}
		int left = 0;
		int right = left;
		int[] res = new int[3];
		int[] sumArr = new int[arr.length]; //���arr[0..i]�ĺ�
		sumArr[0] = arr[0];
		boolean flag = true;
		int maxNum = Integer.MIN_VALUE;
		int maxIndex = -1;
		for(int i=0;i<arr.length;i++) {
			if(flag) {
				if(arr[i]>0) {
					left = i;right = left;//�ҵ���һ������0������Ϊ��߽���ұ߽�
					flag = false;
				}
			}
			if(i>0) {
				sumArr[i] = sumArr[i-1] + arr[i];
			}
			if(arr[i]>maxNum) {
				maxNum = arr[i];maxIndex=i;
			}
		}
		if(flag) {
			res[0]=maxIndex;res[1]=maxIndex;res[2]=maxNum;
			return res;//��������е���ȫ��С�ڵ���0�򷵻�arr��������
		}
		//�ҵ���left,��right��ʼ����
		int sum = arr[left];
		int max = sum;
		int resLeft = left;int resRight = right;
		for(int j=right+1;j<arr.length;j++) {
			if(sum>max) {
				max = sum;resLeft = left;resRight = right;//��¼���͵�������ҽ�
			}
			if(arr[j]>0) {
				int leftToj = sumArr[j]-sumArr[left]+arr[left];
				if(leftToj>arr[j]&&leftToj>sum) {
					sum = leftToj;
					right = j;
				}else if(arr[j]>leftToj&&arr[j]>sum){
					sum = arr[j];
					left = j;right = j;
				}
			}else {
				if(sumArr[j]-sumArr[right]+sum<0) {//���sum+arr[right+1..j]<0�� left��right�͵�����һ���������±�
				    while(arr[j]<=0&&j<arr.length) {
				    	j++;
				    }//����¯��
				    left = j;right = j;
				    sum = arr[j];
				}
			}
		}
		res[0] = resLeft;res[1] = resRight;res[2] = max;
		return res;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] test = {-10000,13,-3,-25,20,-3,-16,-23,1,2,-7,12,-5,-22,15,-4,7};
		int[] res1 = findMaxSubArray1(test,0,test.length-1);
		int[] res2 = findMaxSubArray2(test);
		System.out.println("���������Ϊarr["+res1[0]+"]��"+"arr["+res1[1]+"] ��Ϊ��"+res1[2]);
		System.out.println("���������Ϊarr["+res2[0]+"]��"+"arr["+res2[1]+"] ��Ϊ��"+res2[2]);
	}

}
