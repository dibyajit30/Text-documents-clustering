package Clustering.Clustering;
import java.util.*;

public class Clustering {
      
	public ArrayList<List<Integer>> makeClusters(double[][] matrix, int k, int select)
	{
		ArrayList<List<Integer>> clusters = new ArrayList<>();
		int iteration=0;
		double[][] means = new double[k][matrix[0].length];
		for(int i=0;i<k;i++)
		{
			ArrayList<Integer> clusterIndex = new ArrayList<>();
			means[i] = matrix[i];
			clusters.add(clusterIndex);
		}
		
		double[][] meansCopy = new double[k][matrix[0].length];
		for(int i=0;i<k;i++)
		{
			meansCopy[i] = matrix[i];
		}
		
		while(true)
		{
			//inserting documents into clusters
			iteration++;
			for(int j=0;j<matrix.length;j++)
			{
				double min = Integer.MAX_VALUE;
				int minIndex = 0;
				for(int i=0;i<means.length;i++)
				{
					if(select==1)
					{
						if(getCosineDis(means[i],matrix[j]) < min) {
							min = getCosineDis(means[i],matrix[j]);
							minIndex = i;
						}
					}
					else
					{
						if(getEuclideanDis(means[i],matrix[j]) < min) {
				    		min = getEuclideanDis(means[i],matrix[j]);
							minIndex = i;
						}
					}
				}
				clusters.get(minIndex).add(j);	
			}
			
			//mean gets recalculated
			
			for(int i=0;i<clusters.size();i++) {
				means[i] = getMeans(clusters.get(i),matrix);
			}
			//
			
			if(matrixSimilar(means, meansCopy) || iteration==1000)
			{
				break;
			}				
			meansCopy = means;	
			
			//clear out the clusters for next iteration insertions using new calculated means
			clusters = new ArrayList<>();
			for(int i=0;i<k;i++)
			{
				ArrayList<Integer> clusterIndex = new ArrayList<>();
				clusters.add(clusterIndex);
			}
			
		}
		return clusters;		
	}
	
	  @SuppressWarnings("null")
	public double[] getMeans(List<Integer> clusterIndexes, double[][] matrix)
	  {
		  double[] sum = new double[matrix[0].length]; 
		  //double[][] arrays = new double[clusters.length][matrix];
		  for(int i=0;i<clusterIndexes.size();i++)
		  {
			  int fetch = clusterIndexes.get(i);
			  for(int j=0;j<sum.length;j++)
			  {
				  sum[j] = sum[j] + matrix[fetch][j];
			  }
		  }
		  
		  for(int i=0;i<sum.length;i++)
		  {
			  sum[i] = sum[i]/clusterIndexes.size();
		  }
		  return sum;
	  }
	
	public boolean matrixSimilar(double[][] means, double[][] meansCopy)
	{
		for(int i=0;i<means.length;i++)
		{
			for(int j=0;j<means[0].length;j++) {
				if(means[i][j]!=meansCopy[i][j])
				{
					return false;
				}
			}
			
		}
		return true;
	}
	
	public double mean(double[] matrix)
	{
		double sum=0;
		for(double i:matrix)
		{
			sum = sum + i;
		}
		
		return sum/matrix.length;
	}
	
	
	
	public double[][] getEuclideanMatrix(double[][] sortedtransMatrix)
      {
    	  int row = sortedtransMatrix.length;
    	  int col = sortedtransMatrix.length;
    	  
    	  double[][] euclideanMatrix = new double[row][col];
    	  
    	  for(int i=0;i<row;i++)
    	  {
    		  for(int j=0;j<col;j++)
    		  {
    			  euclideanMatrix[i][j] = getEuclideanDis(sortedtransMatrix[i], sortedtransMatrix[j]);
    		  }
    	  }
    	return euclideanMatrix;		  
      }
      
      public double getEuclideanDis(double[] a1, double[] a2)
      {
    	 double ans=0.0;
    	 for(int i=0;i<a1.length;i++)
    	 {
    		 double diff = a1[i]-a2[i];
    		 ans = ans + diff * diff;
    	 }
    	 
    	 return Math.sqrt(ans);
      }

	public double[][] getCosineMatrix(double[][] sortedtransMatrix) {
		int row = sortedtransMatrix.length;
  	    int col = sortedtransMatrix.length;
  	  
  	    double[][] cosineMatrix = new double[row][col];
  	    
  	    for(int i=0;i<row;i++)
	    {
		  for(int j=0;j<col;j++)
		  {
			  cosineMatrix[i][j] = getCosineDis(sortedtransMatrix[i], sortedtransMatrix[j]);
			  
		  }
	    }
	return cosineMatrix;	
	}
	
	public double getCosineDis(double[] a1, double[] a2)
	{
		double dotProduct = 0.0;
		double a1Denom = 0.0;
		double a2Denom = 0.0;
		for(int i=0;i<a1.length;i++)
		{
			dotProduct = dotProduct + a1[i]*a2[i];
			a1Denom = a1Denom + a1[i]*a1[i];
			a2Denom = a2Denom + a2[i]*a2[i];
		}
		double sqrta1 = Math.sqrt(a1Denom);
		double sqrta2 = Math.sqrt(a2Denom);
		
		return dotProduct / (sqrta1*sqrta2);
	}
}
