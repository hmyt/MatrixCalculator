package matrix;

import java.io.*;
import java.util.*;

public class Matrix {
	private ArrayList<ArrayList<Double>> matrix = new ArrayList<ArrayList<Double>>();
	public Matrix(String arg) throws FileNotFoundException, IOException, IllegalArgumentException{
		int jBuf = 0;
		try{
			BufferedReader br = new BufferedReader(new FileReader(arg));		
			String strBuf;
			while((strBuf=br.readLine()) != null){
				if(strBuf.length() != 0){
					String[] strArrBuf = strBuf.split(" ");
					ArrayList<Double> mtrBuf = new ArrayList<Double>();
					for(int i=0; i < strArrBuf.length; i++){
						mtrBuf.add(Double.parseDouble(strArrBuf[i]));
					}
					if(matrix.size() != 0 && mtrBuf.size() != jBuf){
						br.close();
						throw new IllegalArgumentException(arg+": 行列の形が不正です.");
					}
					else{
						matrix.add(mtrBuf);
						jBuf=mtrBuf.size();
					}
				}
			}
			br.close();
		}catch(FileNotFoundException e){
			throw new FileNotFoundException(arg+": 指定されたファイルが見つかりません.");
		}catch(IOException e){
			throw new IOException(arg+": IO Error.");
		}catch(IllegalArgumentException e){
			throw new IllegalArgumentException(e);
		}
		System.out.printf("%s:読み込み成功\n", arg);
	}
	public double[][] getMatrix(){
		double[][] mtr = new double[matrix.size()][matrix.get(0).size()]; 
		for (int i=0; i<matrix.size(); i++){
			for (int j=0; j < matrix.get(i).size(); j++){
				mtr[i][j] = matrix.get(i).get(j);
			}
		}
		return mtr;
	}
}
