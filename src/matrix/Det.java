package matrix;

public class Det {
	private double determinant;
	private double[][] matrix;
	private boolean debugFlag;
	private static Counter c;
	public Det(Matrix mtr) throws IllegalArgumentException{
		setMatrix(mtr);
		debugFlag = false;
	}
	public Det(Matrix mtr,boolean flag) throws IllegalArgumentException{
		setMatrix(mtr);
		debugFlag = flag;
		c = new Counter();
	}
	public void setMatrix(Matrix mtr) throws IllegalArgumentException{
		if(mtr.getMatrix().length != mtr.getMatrix()[0].length)
			throw new IllegalArgumentException(mtr+": 正方行列でないため処理できません.");
		else
			matrix = mtr.getMatrix();
	}
	public double[][] getMatrix(){
		return matrix;
	}
	public double getDet(){
		determinant = CalcDet(matrix, matrix.length, debugFlag);
		if(debugFlag == true){
			System.out.println("再帰(呼出)回数"+c.getCount());
			c.setCount(0);
		}
		return determinant;
	}
	private static double CalcDet(double[][] mtr, int row, boolean debugFlag){
		int i, j, k, flag;
		double det=0, mtrBuf[][] = new double[100][100];
		if(debugFlag==true)
			c.inc();
		if(row == 2){
			det=mtr[0][0]*mtr[1][1] - mtr[0][1] * mtr[1][0];
		}else if(row >= 3){
			for(i = 0; i<row; i++){
				for(j=1;j<row; j++){
					flag=0;
					for(k=0; k<row-1; k++){
						if(k == i)
							flag=1;
						if(flag==1)
							mtrBuf[j-1][k] = mtr[j][k+1];
						else
							mtrBuf[j-1][k] = mtr[j][k];
					}
				}
				det += Math.pow(-1, i) * mtr[0][i] * CalcDet(mtrBuf,row-1, debugFlag);			
			}
		}else{
			System.err.println("Unexpected Error");
			System.exit(1);
		}
		return det;
	}
	
}

