package Clustering.Clustering;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.lang.Math;

public class Main{
	
	public static void main(String[] args)
	{
     
	// This puts all the stop words into hashset.	
	 HashSet<String> uniqueWords = new HashSet<>();
     HashMap<String, Integer> freq;
     //String directory = "C:\\Users\\Admin\\Desktop\\NYU Courant(2nd sem)\\BDS\\HW2\\";
     String directory = "C:\\Users\\Dibyajit\\Documents\\Courses\\BDS\\HW\\";
     File folder = new File(directory + "Text-documents-clustering\\\\Clustering\\\\resources\\\\dataset_3\\\\data\\\\C1");
     
     PreProcessing pp = new PreProcessing();
     documentMatrix dm = new documentMatrix();
     ArrayList<String> list = new ArrayList<>();
     
     HashSet<String> stopwords = pp.getStopWords();
    
     ArrayList<HashMap<String, Integer>> listmap = new ArrayList<>();
	 for(File file: folder.listFiles())
	 { 
	   freq = new HashMap<>();
	   ArrayList<String> ans = new ArrayList<>();
	   list = pp.readFromFile(stopwords, file);
	   
	   HashMap<String, Integer> map = dm.getFreq(list, uniqueWords, freq);
	   listmap.add(map);
     }
	 
	  int[][] documentMatrix = dm.makeMatrix(listmap, uniqueWords);
	  
	  double[][] transformedMatrix = dm.makeTM(documentMatrix); 
	  double[][] sortedtransMatrix = dm.sortedTheMatrix(transformedMatrix, documentMatrix);
	  
	  //double[][] pca_sortedtransMatrix = pca.transform(sortedtransMatrix, PCA.TransformationType.WHITENING);
	  
	  //Applying pca on sortedtransMatrix
	  
//	  for(int i=0;i<documentMatrix.length;i++)
//	  {
//		  for(int j=0;j<documentMatrix[0].length;j++)
//		  {
//			  System.out.print(sortedtransMatrix[i][j] + " ");
//		  }
//		  System.out.println();
//	  }
	  
	  Clustering c = new Clustering();
	  
	  double[][] euclideanMatrix = c.getEuclideanMatrix(sortedtransMatrix);  
	  double[][] cosineMatrix = c.getCosineMatrix(sortedtransMatrix);
	  ArrayList<List<Integer>> clusters = c.makeClusters(sortedtransMatrix, 3, 0);
	  
	  for(int i=0;i<clusters.size();i++)
	  {
		  System.out.println(clusters.get(i));
	  }
	  
	  Performance per = new Performance();
	  per.buildCM(clusters);
	}
}
