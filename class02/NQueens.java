package class02;

public class NQueens {
	
	public static int num1(int n) {
		if(n<1) {
			return 0;
		}
		int[] record = new int [n];
		return process(0,n,record);
	}
	
	public static int process(int i,int n,int[] record) {
		if(i==n) {
			return 1;
		}
		int res=0;
		for(int j=0;j<n;j++) {//一列一列的试
			if(isvalid(i,j,record)) {
				record[i]=j;
				res+=process(i+1,n,record);
			}
		}
		return res;
	}

	public static boolean isvalid(int i,int j,int[] record) {
		for(int k=0;k<i;k++) {
			if(j==record[k]||Math.abs(k-i)==Math.abs(record[k]-j)) {
				return false;
			}
		}
		return true;
	}
	//采用位运算加速，不能超过32皇后问题,在比较的时候做了优化
	public static int num2(int n) {
		if(n<1||n>32) {
			return 0;
		}
		int limit=n == 32 ? -1:(1<<n)-1;//生成一个后n位是1的数2^n-1
		return process2(limit,0,0,0);
	}
	//colLim,leftDiaLim,rightDiaLim分别为列限制、左斜线限制、右斜线限制。位上标1不能填皇后，标0可以填皇后
	public static int process2(int limit,int colLim,int leftDiaLim,int rightDiaLim) {
		if(colLim==limit) {
			return 1;
		}
		//所有候选皇后的位置在pos上
		int pos = limit&(~(colLim|leftDiaLim|rightDiaLim));
		int mostRightOne = 0;
		int res=0;
		while(pos!=0) {//还有皇后可以试
			mostRightOne = pos&(~pos+1);//提取最右侧的1,从最右边开始一个一个试
			pos = pos-mostRightOne;
			res+=process2(limit,colLim|mostRightOne,(leftDiaLim|mostRightOne)<<1,(rightDiaLim|mostRightOne)>>1);
		}
		return res;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int M=16;
		for(int N=1;N<=M;N++) {
			System.out.println("\n"+N+"皇后问题-----");
			long start1=System.currentTimeMillis();
			System.out.print("结果1为："+num2(N)+" ");
			long end1=System.currentTimeMillis();
			System.out.println("用时为："+(end1-start1)+"ms");
			long start=System.currentTimeMillis();
			System.out.print("结果2为："+num1(N)+" ");
			long end=System.currentTimeMillis();
			System.out.println("用时为："+(end-start)+"ms");
			System.out.println("优化了"+((end-start)-(end1-start1))+"ms");
		}
	}
}
