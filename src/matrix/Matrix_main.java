package matrix;

import java.util.Arrays;

public class Matrix_main {

	public static void main(String[] args) {
		double a[][] = {{0, 1},{2, 0},{0, 3}};
		double b[][] = {{1, -1, 2},{3, 0, 4}};
		Matrix_lib mlib = new Matrix_lib(a, b);
		System.out.println("Product = "+Arrays.deepToString(mlib.getProduct()));
		System.out.println("Transpose = "+Arrays.deepToString(mlib.getTranspose(b)));
	}
	
}
