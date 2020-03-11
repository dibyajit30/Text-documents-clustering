package Clustering.Clustering;
import Jama.Matrix;
import Jama.SingularValueDecomposition;

public class SVDJama {
	double[][] getSVD(double[][] allComponents, int nComponents){
		Matrix matrix = new Matrix(allComponents);
		SingularValueDecomposition mySVD = matrix.svd();
		//Matrix svd = mySVD.getU().times(mySVD.getS()).times(mySVD.getV().transpose());
		return allComponents;
	}
}
