package matrix;

class MainMatrix {

	public static void main(String[] args) {
		boolean debugOption = false,debugOptionPvt = false;
		int flagPosition = -1, flagPositionPvt = -1;
		if(args.length==0){
			System.err.println("Usage: MainMatrix [file] ...");
			System.exit(1);
		}
		int argsLength = args.length;
		for (int i=0; i<args.length; i++) {
            if ("--debug".equals(args[i])) {
                debugOption = true;
                flagPosition = i;
                argsLength--;
            } else if ("--debug-no-pivot".equals(args[i])) {
                debugOptionPvt = true;
                flagPositionPvt = i;
                argsLength--;
            }
		}			
		Matrix[] mtr = new Matrix[argsLength];
		Det[] det = new Det[argsLength];
		InvMatrix[] inv = new InvMatrix[argsLength];
		for(int i=0; i<argsLength;i++){
			if(i != flagPosition && i != flagPositionPvt){
				try{
					mtr[i] = new Matrix(args[i]);
					System.out.println("もとの行列-------------");
					printMatrix(mtr[i]);
					System.out.println("-------------");
					if(debugOption == true)
						det[i] = new Det(mtr[i], debugOption);
					else
						det[i] = new Det(mtr[i]);
					System.out.println("行列式-------------");
					System.out.println(det[i].getDet());
					System.out.println("-------------");
					if(debugOption == true || debugOptionPvt == true)
						inv[i] = new InvMatrix(mtr[i], debugOption, debugOptionPvt);
					else
						inv[i] = new InvMatrix(mtr[i]);
					System.out.println("逆行列-------------");
					if(inv[i].getInvmatrix()==InvMatrix.NO_INVMTR)
						System.out.println("逆行列は存在しません.");
					else
						printMatrix(inv[i].getInvmatrix());
				}catch(Exception e){
					System.out.println(e);
				}
			}
		}
	}

	private static void printMatrix(Matrix mtr){
		double[][] matrix;
		matrix = mtr.getMatrix();
		printMatrix(matrix);
	}
	private static void printMatrix(double[][] mtr){
		for (int i=0; i<mtr.length; i++){
			for (int j=0; j < mtr[i].length; j++){
				System.out.printf("%.5f ", mtr[i][j]);
			}
			System.out.printf("\n");
		}
	}
	
}
