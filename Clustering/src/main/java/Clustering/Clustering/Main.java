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
     String directory = "C:\\Users\\Admin\\Desktop\\NYU Courant(2nd sem)\\BDS\\";
     //String directory = "C:\\Users\\Dibyajit\\Documents\\Courses\\BDS\\HW\\";
     File folder = new File(directory + "Text-documents-clustering\\\\Clustering\\\\resources\\\\dataset_3\\\\data\\\\C");
     
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
	  //double[][] sortedtransMatrix = dm.sortedTheMatrix(transformedMatrix, documentMatrix);
	  
	  //double[][] pca_sortedtransMatrix = pca.transform(sortedtransMatrix, PCA.TransformationType.WHITENING);
	  
	  //Applying pca on sortedtransMatrix
	  
	  // Generating keyWords for clusters
	  ArrayList<String> keyWords = dm.generateKeyWords(transformedMatrix);
	  for(String s:keyWords)
	  {
		  System.out.println(s);
	  }
	  
	  PrincipleComponentAnalysis pca = new PrincipleComponentAnalysis();
	  //double[][] principleComponents = pca.getPrincipleComponents(sortedtransMatrix, 2);
	  
	  
	  Clustering c = new Clustering();
	  
	  double[][] euclideanMatrix = c.getEuclideanMatrix(transformedMatrix);  
	  double[][] cosineMatrix = c.getCosineMatrix(transformedMatrix);
	  ArrayList<List<Integer>> clusters = c.makeClusters(transformedMatrix, 3, 1);
	  
	  for(int i=0;i<clusters.size();i++)
	  {
		  System.out.println(clusters.get(i));
	  }
	  
	  Performance per = new Performance();
	  per.buildCM(clusters);
	  
	  Visualization vc = new Visualization(transformedMatrix);
	  vc.setVisible(true);
	  
	  
	  
	  
	}
}
