package high_Class_Problem;

import java.util.Arrays;
import java.util.Comparator;

/*
 * 给你一个数组，代表每个人的重量，再给你一个limit代表船的最大承重量且每条船最多只能坐2个人
 * 问：最少需要多少条船可以让每个人过河
 */
public class MinBoat {
	
	//构造比较器
	public static class intComparator implements Comparator<Integer>{
		@Override
		public int compare(Integer o1, Integer o2) {
			return o1-o2;
		}
	}

	public static int minBoat(int[] arr, int limit) {
		//将数组从小到大排序
		Arrays.sort(arr);
		//过滤掉极端情况
		if(arr[arr.length-1]<=limit/2) {
			return (arr.length+1)/2;
		}
		if(arr[0]>limit/2) {
			return arr.length;
		}
		int index = -1;//找到小于等于limit/2的最右位置
		for(int i = arr.length-1;i>=0;i--) {
			if(arr[i]<=(limit/2)) {
				index = i;
				break;
			}
		}
		int L = index; //左侧区域的最右标号
		int R = index+1; //右侧区域的最左标号
		int unused = 0; //统计画差的个数
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
				L = Math.max(-1,L-sloved);//搞定了sloved个，L向左移但不能越界
			}
		}
		int leftNum = index+1;
		int rightNum = arr.length-leftNum;
		int used = leftNum - unused; // 搞定的个数
		int moreUnsolve = rightNum - used; //右侧还未搞定的
		return used + (unused+1)/2 + moreUnsolve;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] testArr = {5,2,2,2,2,3,1,3,3,3,3,4,4,4,4};
		int testLimit = 6;
		System.out.print(minBoat(testArr,testLimit));
	}

}
