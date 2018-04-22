package matrix;

public class InvMatrix extends Det {
	private double[][] invmatrix;
	private boolean debugFlag, debugFlagPvt;
	public static final double[][] NO_INVMTR = null;
	public InvMatrix(Matrix mtr) throws IllegalArgumentException{
		super(mtr);
		debugFlag = false;
		debugFlagPvt = false;
	}
	public InvMatrix(Matrix mtr, boolean flg, boolean flgPvt) throws IllegalArgumentException{
		super(mtr, flg);
		debugFlag = flg;
		debugFlagPvt = flgPvt;
	}
	public double[][] getInvmatrix(){
		if(super.getDet() == 0)
			invmatrix = NO_INVMTR;
		else
			invmatrix = CalcInvmtr(super.getMatrix(), debugFlag, debugFlagPvt);
		return invmatrix;
	}
	private static double[][] CalcInvmtr(double[][] mtr, boolean debugFlag, boolean debugFlagPvt){
		int pvt, pvtRow, i, j;
		double buffer;
		double[] rowBuf;
		double[][] mtrBuf = new double[mtr.length][mtr[0].length];
		double[][] idmtr = new double[mtr.length][mtr[0].length];
		Counter pvtCnt = new Counter();
		Counter divCnt = new Counter();
		Counter subCnt = new Counter();
		
		for(i=0; i<mtr.length; i++){
			for(j=0; j<mtr[i].length; j++){
				mtrBuf[i][j] = mtr[i][j];
			}
		}
		for(i=0; i<mtrBuf.length; i++){
			for(j=0; j<mtrBuf[i].length; j++){
				if(i == j)
					idmtr[i][j] = 1;
				else
					idmtr[i][j] = 0;
			}
		}
		for(pvt=0; pvt<mtrBuf.length; pvt++){
			if(debugFlagPvt!=true){
				pvtRow=pvt;
				for(i=pvt; i<mtrBuf.length; i++){
					if(Math.abs(mtrBuf[i][pvt]) > Math.abs(mtrBuf[pvtRow][pvt])){
						pvtRow = i;
					}
				}
				if(pvt != pvtRow){
					if(debugFlag==true)
						pvtCnt.inc();
					rowBuf = mtrBuf[pvtRow];
					mtrBuf[pvtRow] = mtrBuf[pvt];
					mtrBuf[pvt] = rowBuf;
					rowBuf = idmtr[pvtRow];
					idmtr[pvtRow] = idmtr[pvt];
					idmtr[pvt] = rowBuf;
				}
			}
			if(mtrBuf[pvt][pvt] != 1){
				buffer = mtrBuf[pvt][pvt];
				for(i=0; i<mtrBuf.length; i++){
					mtrBuf[pvt][i] /= buffer;
					idmtr[pvt][i] /= buffer;
					if(debugFlag == true){
						divCnt.inc();
						divCnt.inc();
					}
				}
			}
			for(i=0; i<mtrBuf.length; i++){
				if(i != pvt){
					buffer = mtrBuf[i][pvt];
					for (j=0; j<mtrBuf.length; j++){
						mtrBuf[i][j] -= buffer*mtrBuf[pvt][j];
						idmtr[i][j] -= buffer*idmtr[pvt][j];
						if(debugFlag == true){
							subCnt.inc();
							subCnt.inc();
						}
					}
				}
			}
		}
		if(debugFlag==true){
			System.out.println("ピポット回数: "+pvtCnt.getCount());
			System.out.println("除算回数: "+divCnt.getCount());
			System.out.println("減算回数: "+subCnt.getCount());
		}
		return idmtr;
	}
}
