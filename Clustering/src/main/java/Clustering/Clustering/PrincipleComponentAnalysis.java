package Clustering.Clustering;

import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;
import org.nd4j.linalg.dimensionalityreduction.PCA;


public class PrincipleComponentAnalysis {
	double[][] getPrincipleComponents(double[][] allComponents, int nComponents){
		INDArray nd = Nd4j.create(allComponents);
		PCA pca = new PCA();
		INDArray nd1 = nd.mmul(nd.transpose());
		int i = nd.size(0);
		INDArray nd2 = nd1.muli(1 / nd.size(0));
		nd = pca.pca(nd, nComponents, false);
		
		//allComponents = ((Object) nd).convertToDoubles();
		return allComponents;
	}
	
}
