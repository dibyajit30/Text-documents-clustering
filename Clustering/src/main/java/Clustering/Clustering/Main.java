package Clustering.Clustering;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
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
	
	public static void main(String[] args) throws IOException
	{
     
	// This puts all the stop words into hashset.	
	 HashSet<String> uniqueWords = new HashSet<>();
     HashMap<String, Integer> freq;
     File folder = new File("..\\\\Clustering\\\\resources\\\\dataset_3\\\\data\\\\C");
     
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
	  double[][] docMatrix = new double[documentMatrix.length][documentMatrix[0].length];
	  for(int i=0;i<documentMatrix.length;i++) {
		  for(int j=0;j<documentMatrix[0].length;j++) {
			  docMatrix[i][j] = (double) documentMatrix[i][j] * 1000;
		  }
	  }
	// Generating keyWords for clusters
	  ArrayList<String> keyWords = dm.generateKeyWords(transformedMatrix);
	  FileWriter fstream = new FileWriter("..\\\\Clustering\\\\resources\\\\keywords.txt");
      
      for(int i=0; i<keyWords.size(); i++)
	  {
		  System.out.println("Keyword for cluster " + i + ": " + keyWords.get(i));
		  fstream.write("Keyword for cluster " + i + ": " + keyWords.get(i) + "\n");
	  }
      fstream.close();
	  PrincipleComponentAnalysis pca = new PrincipleComponentAnalysis();
	  SVDJama svdJama = new SVDJama();
	  //double[][] principleComponents = pca.getPrincipleComponents(docMatrix, 2);
	  double[][] svd = svdJama.getSVD(transformedMatrix, 2);
	  Clustering c = new Clustering();
	  
	  double[][] euclideanMatrix = c.getEuclideanMatrix(sortedtransMatrix);  
	  double[][] cosineMatrix = c.getCosineMatrix(sortedtransMatrix);
	  int applyEuclideanDistance = 1;
	  int applyCosineSimilarity = 0;
	  int noOfClusters = 3;
	  ArrayList<List<Integer>> clusters = c.makeClusters(docMatrix, noOfClusters, applyCosineSimilarity);
	  
	  for(int i=0;i<clusters.size();i++)
	  {
		  System.out.println("Predicted cluster " + i + ": " + clusters.get(i));
	  }
	  
	  Performance per = new Performance();
	  per.buildCM(clusters);
	  ArrayList<Double> precisions = per.getPrecisions(per.C1_truePositives, per.C1_falsePositives, per.C4_truePositives, per.C4_falsePositives, per.C7_truePositives, per.C7_falsePositives);
	  System.out.print("Precisions : ");
	  for(double d:precisions)
	  {
		  System.out.print( d + " "); 
	  }
	  
	  System.out.println();
	  ArrayList<Double> recalls = per.getRecalls(per.C1_truePositives, per.C4_truePositives, per.C7_truePositives);
	  System.out.print("Recalls : ");
	  for(double d:recalls)
	  {
		  System.out.print(d + " "); 
	  }
	  System.out.println();
	  
	  ArrayList<Double> F1Scores = per.getF1Scores(precisions, recalls);
	  System.out.print("F1 scores : ");
	  for(double d:F1Scores)
	  {
		  System.out.print(d + " "); 
	  }
			  
	  Visualization vc = new Visualization(transformedMatrix);
	  vc.setVisible(true);
	  
	  
	  
	  
	}
}
