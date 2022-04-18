package mid_Class_Problem;
//用一种时间复杂度O(n)空间复杂度O(1)的方法
//找出1~n中哪些数没在数组上出现
public class PrintNoInArray {
	
	//请保证arr[0...n-1]上的数在1...n之间
	public static void printNumberNoInArray(int[] arry) {
		if(arry.length=='0'||arry==null) {
			return;
		}
		for(int value:arry) { //争取做到i位置上放的是i+1
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
