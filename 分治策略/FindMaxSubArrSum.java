package 分治策略;

/*
 * 求最大子数组和
 */
public class FindMaxSubArrSum {
	
	//跨越中点时的最大子数组和，返回[左界，右界，最大和]
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

	//利用分治策略求子数组最大和，返回[左界，右界，最大和]
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
	
	//非递归，线性时间的算法
	public static int[] findMaxSubArray2(int[] arr) {
		if(arr==null||arr.length==0) {
			return null;
		}
		int left = 0;
		int right = left;
		int[] res = new int[3];
		int[] sumArr = new int[arr.length]; //存放arr[0..i]的和
		sumArr[0] = arr[0];
		boolean flag = true;
		int maxNum = Integer.MIN_VALUE;
		int maxIndex = -1;
		for(int i=0;i<arr.length;i++) {
			if(flag) {
				if(arr[i]>0) {
					left = i;right = left;//找到第一个大于0的数记为左边界和右边界
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
			return res;//如果数组中的数全是小于等于0则返回arr中最大的数
		}
		//找到了left,和right开始迭代
		int sum = arr[left];
		int max = sum;
		int resLeft = left;int resRight = right;
		for(int j=right+1;j<arr.length;j++) {
			if(sum>max) {
				max = sum;resLeft = left;resRight = right;//记录最大和的左界与右界
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
				if(sumArr[j]-sumArr[right]+sum<0) {//如果sum+arr[right+1..j]<0了 left和right就等于下一个正数的下标
				    while(arr[j]<=0&&j<arr.length) {
				    	j++;
				    }//另起炉灶
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
		System.out.println("最大子数组为arr["+res1[0]+"]到"+"arr["+res1[1]+"] 和为："+res1[2]);
		System.out.println("最大子数组为arr["+res2[0]+"]到"+"arr["+res2[1]+"] 和为："+res2[2]);
	}

}
