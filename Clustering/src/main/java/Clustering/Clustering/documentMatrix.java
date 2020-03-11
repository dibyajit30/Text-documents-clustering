package Clustering.Clustering;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;

public class documentMatrix {
    
	ArrayList<String> ordered_uniqueWords = new ArrayList<>();
	
	public HashMap<String, Integer> getFreq(ArrayList<String> list, HashSet<String> uniqueWords, HashMap<String, Integer> freq)
	{
		for(String s:list)
		   {
			   if(!uniqueWords.contains(s))
			   {
				   uniqueWords.add(s);
			   }
		   }
		
		HashMap<String, Integer> freqCount = new HashMap<>();
		for(String s:list)
		{
			if(freqCount.containsKey(s))
			{
				freqCount.put(s, freqCount.get(s)+1);
			}
			else
			{
				freqCount.put(s,1);
			}
		}
		
		return freqCount;
	}
	
	
	public int[][] makeMatrix(ArrayList<HashMap<String, Integer>> listmap, HashSet<String> set)
	{
	   int[][] documentMatrix = new int[listmap.size()][set.size()];
	   
		for(int i=0;i<listmap.size();i++)
	   {
		   int j=0;
			HashMap<String, Integer> map = listmap.get(i);
			for(String check:set)
		   {
				ordered_uniqueWords.add(check);
				if(!map.containsKey(check))
			   {
				   documentMatrix[i][j]=0; 
			   }
			   else
			   {
				   documentMatrix[i][j]=map.get(check); 
			   }
				j++;
		   }
	   }
		return documentMatrix;
	}
	
	public double[][] sortedTheMatrix(double[][] transformedMatrix, int[][] documentMatrix)
	{
//		ArrayList<double[]> tM = (ArrayList<double[]>) Arrays.asList(transformedMatrix);
//		ArrayList<int[]> dM = (ArrayList<int[]>) Arrays.asList(documentMatrix);
//		for(int i=0;i<tM.size();i++)
//		{
//			double[] tMarray= tM.get(i);
//			int[] dMarray = dM.get(i);
		 double[][] documentMatrix1 = new double[documentMatrix.length][documentMatrix[0].length];
		 
		 // converting document matrix into double which is documentmatrix 1 
		 for(int i11=0;i11<documentMatrix.length;i11++)
		 {
			 for(int j=0;j<documentMatrix[0].length;j++)
			 {
				 documentMatrix1[i11][j] = (double)documentMatrix[i11][j];
			 }
		 }
		 
		 ///sort documentmatrix1 on the basis of transformed matrix 
			Arrays.sort(documentMatrix1, new Comparator<double[]>(){
				@Override
		        public int compare(double[] left, double[] right) {
		          return (int) (left[0] - right[0]);
//		          if (entry1[col] > entry2[col]) 
//		                return 1; 
//		            else
//		                return -1;
		        }
	
		});
			return documentMatrix1;
	}
	
	
	public double[][] makeTM(int[][] documentMatrix)
	{
		double tf=0.0;
		double idf=0.0;
		int sum;
		int i=0;
		int j=0;
		int count;
		int no_of_docs = documentMatrix.length;
		double[] idfs = new double[documentMatrix[0].length];
	    double[][] transformedMatrix = new double[documentMatrix.length][documentMatrix[0].length];
		
		for(j=0;j<documentMatrix[0].length;j++)
		{
			count=0;
			for(i=0;i<documentMatrix.length;i++)
			{
				if(documentMatrix[i][j]!=0)
				{
					count++;
				}
			}
			idfs[j]=count;
		}
		
		for(i=0;i<documentMatrix.length;i++)
		{
			sum=0;
			for(j=0;j<documentMatrix[0].length;j++)
			{
			   sum = sum + documentMatrix[i][j]; 
			}
			for(j=0;j<documentMatrix[0].length;j++)
			{
				 tf = (double)documentMatrix[i][j]/sum;
				 idf = Math.log(no_of_docs/idfs[j]);
				 transformedMatrix[i][j] = tf*idf;
			}
		}
		
		return transformedMatrix;
	}
	
	
	public ArrayList<String> generateKeyWords(double[][] transformedMatrix)
	{
		double[][] intermediate_tm = new double[3][transformedMatrix[0].length];
		for(int j=0;j<transformedMatrix[0].length;j++)
		{	
			double sum=0.0;
			for(int i=0;i<8;i++)
			{
				sum = sum + transformedMatrix[i][j];
			}
			
			intermediate_tm[0][j] = sum;
			sum = 0.0;
			
			for(int i=8;i<16;i++)
			{
				sum = sum + transformedMatrix[i][j];
			}
			
			intermediate_tm[1][j] = sum;
			sum = 0.0;
			
			for(int i=16;i<24;i++)
			{
				sum = sum + transformedMatrix[i][j];
			}
			
			intermediate_tm[2][j] = sum;
		}
		
		ArrayList<String> keyWords = new ArrayList<>();
		String keyWord = "";
		for(double[] array:intermediate_tm)
		{
			double maximum = Double.MIN_VALUE;
			for(int i=0;i<array.length;i++)
			{
				if(array[i]>maximum)
				{
					maximum = array[i];
					keyWord = ordered_uniqueWords.get(i);
				}
			}
			keyWords.add(keyWord);
		}
		
		return keyWords;
	}
	
	
	
}
