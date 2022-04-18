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
		for(int j=0;j<n;j++) {//һ��һ�е���
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
	//����λ������٣����ܳ���32�ʺ�����,�ڱȽϵ�ʱ�������Ż�
	public static int num2(int n) {
		if(n<1||n>32) {
			return 0;
		}
		int limit=n == 32 ? -1:(1<<n)-1;//����һ����nλ��1����2^n-1
		return process2(limit,0,0,0);
	}
	//colLim,leftDiaLim,rightDiaLim�ֱ�Ϊ�����ơ���б�����ơ���б�����ơ�λ�ϱ�1������ʺ󣬱�0������ʺ�
	public static int process2(int limit,int colLim,int leftDiaLim,int rightDiaLim) {
		if(colLim==limit) {
			return 1;
		}
		//���к�ѡ�ʺ��λ����pos��
		int pos = limit&(~(colLim|leftDiaLim|rightDiaLim));
		int mostRightOne = 0;
		int res=0;
		while(pos!=0) {//���лʺ������
			mostRightOne = pos&(~pos+1);//��ȡ���Ҳ��1,�����ұ߿�ʼһ��һ����
			pos = pos-mostRightOne;
			res+=process2(limit,colLim|mostRightOne,(leftDiaLim|mostRightOne)<<1,(rightDiaLim|mostRightOne)>>1);
		}
		return res;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int M=16;
		for(int N=1;N<=M;N++) {
			System.out.println("\n"+N+"�ʺ�����-----");
			long start1=System.currentTimeMillis();
			System.out.print("���1Ϊ��"+num2(N)+" ");
			long end1=System.currentTimeMillis();
			System.out.println("��ʱΪ��"+(end1-start1)+"ms");
			long start=System.currentTimeMillis();
			System.out.print("���2Ϊ��"+num1(N)+" ");
			long end=System.currentTimeMillis();
			System.out.println("��ʱΪ��"+(end-start)+"ms");
			System.out.println("�Ż���"+((end-start)-(end1-start1))+"ms");
		}
	}
}
