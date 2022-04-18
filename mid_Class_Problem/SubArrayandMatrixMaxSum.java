package mid_Class_Problem;
// 1.求数组中子数组的最大累加和    2.求矩阵中子矩阵的最大累加和
public class SubArrayandMatrixMaxSum {
	//假设max为数组中累加和最大且长度最长
	public static int arrySumMax(int[] arr) {
		int max = Integer.MIN_VALUE;
		int cur = 0;//数组累加和
		for(int i=0;i<arr.length;i++) {
			cur += arr[i];
			max = Math.max(max, cur);
			cur = cur<0?0:cur;//如果当前累加和小于0，说明对所求没有贡献，应当从0开始
		}
		return max;
	}
	
	//求子矩阵最大累加和
	public static int matrixSumMax(int[][] m) {
		if(m==null||m.length==0||m[0].length==0) {
			return 0;
		}
		int max = Integer.MIN_VALUE;
		int[] s = null;
		//遍历每个从第i行到第j行的矩阵将其压缩到数组s中再求数组中子数组的最大累加和，最后返回所有累加和最大的那个
		for(int i=0;i<m.length;i++) {
			 s = new int[m[0].length];
			for(int j=i;j<m.length;j++) {
				int cur = 0;
				for(int k=0;k<s.length;k++) {
					s[k] += m[j][k];//压缩矩阵的技巧
					cur+=s[k];//在压缩的同时可以同时计算
					max = Math.max(cur, max);
					cur = cur<0?0:cur;
				}
			}
		}
		return max;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
