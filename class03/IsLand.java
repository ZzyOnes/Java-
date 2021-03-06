package class03;

public class IsLand {
	
	public static int countIsLand(int[][] m) {
		if(m==null||m[0]==null) {
			return 0;
		}
		int res = 0;
		int N = m.length;
		int M = m[0].length;
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(m[i][j]==1) {
					res++;
					infect(m,i,j,N,M);
				}
			}
		}
		return res;
	}
	
	public static void infect(int[][] m,int i,int j,int N,int M) {
		if(i<0||i>=N||j<0||j>=M||m[i][j]!=1) {
			return ;
		}
		m[i][j]=2;
		infect(m,i+1,j,N,M);
		infect(m,i,j+1,N,M);
		infect(m,i-1,j,N,M);
		infect(m,i,j-1,N,M);
		return ;
	}
	
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
