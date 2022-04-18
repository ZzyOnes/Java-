package mid_Class_Problem;
/*
 * ����һ���ַ����确1&0|1^0&1&0������һ�����ڴ���desired'True or False'
 * ���� �ж����������ŵķ���ʹ���߼��������Ľ��Ϊdesired
 */
public class ExpressionNumber {
	
	//�жϸ����ַ����Ƿ���Ч
	public static boolean isValid(char[] exp) {
		if((exp.length%2)==0) {//���������ż����Ч
			return false;
		}
		for(int i=0;i<exp.length;i=i+2) {
			if(exp[i]!='0'||exp[i]!='1')return false;
		}
		for(int i=1;i<exp.length;i=i+2) {
			if(exp[i]!='&'||exp[i]!='|'||exp[i]!='^')return false;
		}
		return true;
	}
	
	public static int num1(String express,boolean desired) {
		if(express.length()==0||express.equals("")) {
			return 0;
		}
		char[] exp = express.toCharArray();
		if(!isValid(exp)) {
			return 0;
		}
		return process(exp,desired,0,exp.length-1);
	}
	//����exp[left...right]�еķ�����
	public static int process(char[] exp, boolean desired, int left, int right) {
		if(left==right) {//�ݹ����
			if(exp[left]=='1') {
				return desired==true?1:0; 
			}else {
				return desired?0:1;
			}
		}
		int res=0;//��¼
		if(desired) {//������ڴ�����true
			for(int i=left+1;i<right;i=i+2) {
				switch(exp[i]) {
				case '&': 
					res+=process(exp,true,left,i-1)*process(exp,true,i+1,right);
					break;
				case '|':
					res+=process(exp,true,left,i-1)*process(exp,false,i+1,right);
					res+=process(exp,false,left,i-1)*process(exp,true,i+1,right);
					res+=process(exp,true,left,i-1)*process(exp,true,i+1,right);
					break;
				case '^':
					res+=process(exp,true,left,i-1)*process(exp,false,i+1,right);
					res+=process(exp,false,left,i-1)*process(exp,true,i+1,right);
					break;
				}
			}
		}else {
			for(int i=left+1;i<right;i=i+2) {
				switch(exp[i]) {
				case '&': 
					res+=process(exp,true,left,i-1)*process(exp,false,i+1,right);
					res+=process(exp,false,left,i-1)*process(exp,true,i+1,right);
					res+=process(exp,false,left,i-1)*process(exp,false,i+1,right);
					break;
				case '|':
					res+=process(exp,false,left,i-1)*process(exp,false,i+1,right);
					break;
				case '^':
					res+=process(exp,true,left,i-1)*process(exp,true,i+1,right);
					res+=process(exp,false,left,i-1)*process(exp,false,i+1,right);
					break;
				}
			}
		}
		return res;
	}
	
	//�ݹ�Ķ�̬�滮 leftΪ�У�rightΪ��
	public static int dpLive(String express, boolean desired) {
		char[] exp = express.toCharArray();
		int N = exp.length;
		int[][] tMap = new int[N][N];//true��
		int[][] fMap = new int[N][N];//false��
		//��ʼ��
		for(int i=0;i<N;i+=2) {
			tMap[i][i] = exp[i]=='1'?1:0;
			fMap[i][i] = exp[i]=='1'?0:1;
		}
		//��ʼ���
		for(int row=N-3;row>=0;row-=2) {
			for(int col=row+2;col<N;col+=2) {
				for(int i=row+1;i<col;i+=2) {
					switch(exp[i]) {
					case '&': 
						tMap[row][col]+=tMap[row][i-1]*tMap[i+1][col];
						break;
					case '|':
						tMap[row][col]+=tMap[row][i-1]*fMap[i+1][col];
						tMap[row][col]+=fMap[row][i-1]*tMap[i+1][col];
						tMap[row][col]+=tMap[row][i-1]*tMap[i+1][col];
						break;
					case '^':
						tMap[row][col]+=tMap[row][i-1]*fMap[i+1][col];
						tMap[row][col]+=fMap[row][i-1]*tMap[i+1][col];
						break;
					}
				}
				for(int i=row+1;i<col;i+=2) {
					switch(exp[i]) {
					case '&': 
						fMap[row][col]+=fMap[row][i-1]*tMap[i+1][col];
						fMap[row][col]+=tMap[row][i-1]*fMap[i+1][col];
						fMap[row][col]+=fMap[row][i-1]*fMap[i+1][col];
						break;
					case '|':
						fMap[row][col]+=fMap[row][i-1]*fMap[i+1][col];
						break;
					case '^':
						fMap[row][col]+=tMap[row][i-1]*tMap[i+1][col];
						fMap[row][col]+=fMap[row][i-1]*fMap[i+1][col];
						break;
					}
				}
			}
		}
		return desired?tMap[0][N-1]:fMap[0][N-1];
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str = "1&0^1";
		char[] s = str.toCharArray();
		int res1 = process(s,true,0,s.length-1);
		int res2 = dpLive(str,true);
		System.out.print(res1+"   "+res2);

	}

}
