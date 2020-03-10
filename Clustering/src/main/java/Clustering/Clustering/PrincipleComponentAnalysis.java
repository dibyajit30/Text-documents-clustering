package Clustering.Clustering;

import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;
import org.nd4j.linalg.dimensionalityreduction.PCA;


public class PrincipleComponentAnalysis {
	double[][] getPrincipleComponents(double[][] allComponents, int nComponents){
		INDArray nd = Nd4j.create(allComponents);
		PCA pca = new PCA();
		nd = pca.pca(nd, nComponents, true);
		//allComponents = ((Object) nd).convertToDoubles();
		return allComponents;
	}
	
}
