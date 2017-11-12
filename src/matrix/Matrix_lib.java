package matrix;

import java.util.Arrays;

public class Matrix_lib {

	public double [][] getProduct(double a[][], double b[][]){
		//行列計算した結果の行列は、行列Aの行数 × 行列Bの列数　の行列となる。
		//Javaでは列だけを取り出すことができないので、転置行列を作成して転置行列の行を取り出す。
		double answer[][] = new double [a.length][b[0].length];
		double t[][] = this.getTranspose(b);
		for(int i = 0; i < a.length; i++) {
			for(int j = 0; j < b[0].length; j++) {
				answer[i][j] = this.getInnerProduct(a[i], t[j]);
			}
		}
		return answer;
	}
	
	public double getInnerProduct(double a[], double b[]) { //内積計算
		double answer = 0;
		for (int i = 0; i < a.length; i++) { 
			answer += a[i] * b[i];
		}
		return answer;
	}
	
	public double[][] getTranspose(double a[][]){  //転置行列
		double t[][] = new double[a[0].length][a.length];
		for(int i = 0; i < a[0].length; i++) {
			for(int j = 0; j < a.length; j++) {
				t[i][j] = a[j][i];
			}
		}
		return t;
	}
	
	public double getCofactor(double [][]a) { //行列式計算
		double cofactor = 0;
		if(a.length == 2) { //2次元ならば計算できる
			cofactor = a[0][0] * a[1][1] - a[0][1] * a[1][0];
		}
		else { //3次元以上のとき
			double tmp[][] = new double[a.length - 1][a[0].length - 1];
			//1列について余因子展開 
			int index = 0;
			//余因子を作成
			for(int i = 0; i < a.length; i++) {
				//余因子行列作成
				int p = 0, q = 0;
				for(int j = 0; j < a.length; j++) {
					if(i == j) continue;
					for(int k = 0; k < a[0].length; k++) {
						if(index == k) continue;
						tmp[p][q++] = a[j][k];
					}
					p++;
					q = 0;
				}
				System.out.println("tmp["+i+"] = " +Arrays.deepToString(tmp));
				cofactor += a[i][index]*Math.pow(-1,(i + 1) + (index + 1))*this.getCofactor(tmp);
			}
		}
		return cofactor;
	}
	
	public double [][] getInverse(double[][]a){ //逆行列
		double answer[][] = new double[a.length][a[0].length];
		double tmp[][] = new double[a.length - 1][a[0].length - 1];
		double determinant = this.getCofactor(a);
		//余因子行列作成
		for(int i = 0; i < a.length; i++) {
			for(int j = 0; j < a[0].length; j++) {
				int p = 0, q = 0;
				for(int k = 0; k < a.length; k++) {
					if(i == k) continue;
					for(int s = 0; s < a[0].length; s++) {
						if(j == s) continue;
						tmp[p][q] = a[k][s];
						q++;
					}
					p++;
					q = 0;
				}
				answer[i][j] = Math.pow(-1, i + 1 + j + 1)*this.getCofactor(tmp)/ determinant;
			}	
		}
		answer = this.getTranspose(answer); //転置
		return answer;
	}
	
	public double[] getSolution(double a[][], double b[]){ //連立方程式を解く
		double solution[] = new double[b.length];
		double c[][] = new double[a.length][a[0].length];
		c = getInverse(a);
		for(int i = 0; i < a.length; i++){
			for(int j = 0; j < a[0].length; j++) {
				solution[i] += c[i][j] * b[j]; 
			}
			}
			
			return solution;
		}

}