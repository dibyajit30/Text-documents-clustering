package Clustering.Clustering;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Performance {
    
	
//	HashMap<Integer, Integer> map = new HashMap<>();
//	{
//	map.put(0, 1);
//	map.put(1, 2);
//	map.put(2, 3);
//	
//	map.put(3, 1);
//	map.put(4, 2);
//	map.put(5, 3);
//	
//	map.put(6, 1);
//	map.put(7, 2);
//	map.put(8, 3);
//	
//	map.put(9, 1);
//	map.put(10, 2);
//	map.put(11, 3);
//	
//	map.put(12, 1);
//	map.put(13, 2);
//	map.put(14, 3);
//	
//	map.put(15, 1);
//	map.put(16, 2);
//	map.put(17, 3);
//	
//	map.put(18, 1);
//	map.put(19, 2);
//	map.put(20, 3);
//	
//	map.put(21, 1);
//	map.put(22, 2);
//	map.put(23, 3);
//	};
	

	int C1_truePositives=0;
	int C1_falsePositives=0;
	
	int C4_truePositives=0;
	int C4_falsePositives=0;
	
	int C7_truePositives=0;
	int C7_falsePositives=0;
	
	public void buildCM(ArrayList<List<Integer>> clusters)
	{
		ArrayList<List<Integer>> preDefClusters = new ArrayList<>();
		List<Integer> preDef1 = new ArrayList<>();
		List<Integer> preDef2 = new ArrayList<>();
		List<Integer> preDef3 = new ArrayList<>();
		for(int i=0;i<24;i=i+3) {
			
			preDef1.add(i);
			preDef2.add(i+1);
			preDef3.add(i+2);
		}
		
		preDefClusters.add(preDef1);
		preDefClusters.add(preDef2);
		preDefClusters.add(preDef3);
		
		int[][] cM = new int[2][2];
//		int truePositives=0;
//		int falsePositives=0;
//		
//		int C1_truePositives=0;
//		int C1_falsePositives=0;
//		
//		int C4_truePositives=0;
//		int C4_falsePositives=0;
//		
//		int C7_truePositives=0;
//		int C7_falsePositives=0;
		
			
		List<Integer> cluster1 = new ArrayList<>();
		List<Integer> cluster4 = new ArrayList<>();
		List<Integer> cluster7 = new ArrayList<>();
	
		
		for(List<Integer> cluster:clusters) {
			int allocateCluster[] = new  int[3];
			
			
			for(int i=0;i<cluster.size();i++) {
				if(preDefClusters.get(0).contains(cluster.get(i))) {
					allocateCluster[0] = allocateCluster[0] + 1;
				}
				else if(preDefClusters.get(1).contains(cluster.get(i))) {
					allocateCluster[1] = allocateCluster[1] + 1;
				}
				else {
					allocateCluster[2] = allocateCluster[2] + 1;
				}
			}
			
			int m = Integer.MIN_VALUE;
			int maxIndex = 0;
			for(int i=0;i<allocateCluster.length;i++) {
				if(allocateCluster[i]>m) {
					m = allocateCluster[i];
					maxIndex = i;
				}
			}
			
			if(maxIndex==0)
			{
				cluster1 = cluster;
			}
			else if(maxIndex==1)
			{
				cluster4 = cluster;
			}
			else
			{
				cluster7 = cluster;
			}
		}
		
		// Now we have used majority voting and assigned clusters with most votes to their respective predicted clusters
		// Matching predef1 and cluster 1
		for(int i:cluster1)
		{
			if(preDef1.contains(i))
			{
				C1_truePositives++;
			}
			else
			{
				C1_falsePositives++;
			}
		}
		
		//Matching predef2 and cluster 2
		for(int i:cluster4)
		{
			if(preDef2.contains(i))
			{
				C4_truePositives++;
			}
			else
			{
				C4_falsePositives++;
			}
		}
		
		//Matching predef3 and cluster 3
		for(int i:cluster7)
		{
			if(preDef3.contains(i))
			{
				C7_truePositives++;
			}
			else
			{
				C7_falsePositives++;
			}
		}
	}
	
	public ArrayList<Double> getPrecisions(int C1_truePositives, int C1_falsePositives, int C4_truePositives, int C4_falsePositives, int C7_truePositives, int C7_falsePositives)
	{
		ArrayList<Double> precisions = new ArrayList<>();
		
		double C1_Precision = 0.0;
		double C4_Precision = 0.0;
		double C7_Precision = 0.0;
		
		C1_Precision = (C1_truePositives)/(double)(C1_truePositives+C1_falsePositives);
		C4_Precision = (C4_truePositives)/(double)(C4_truePositives+C4_falsePositives);
		C7_Precision = (C7_truePositives)/(double)(C7_truePositives+C7_falsePositives);
		precisions.add(C1_Precision);
		precisions.add(C4_Precision);
		precisions.add(C7_Precision);
		
		return precisions;
	}
	
	public ArrayList<Double> getRecalls(int C1_truePositives, int C4_truePositives, int C7_truePositives)
	{
        ArrayList<Double> recalls = new ArrayList<>();
		
		double C1_Recall = 0.0;
		double C4_Recall = 0.0;
		double C7_Recall = 0.0;
		
		C1_Recall = (double)(C1_truePositives)/8;
		C4_Recall = (double)(C4_truePositives)/8;
		C7_Recall = (double)(C7_truePositives)/8;
		recalls.add(C1_Recall);
		recalls.add(C4_Recall);
		recalls.add(C7_Recall);
		
		return recalls;
	}
	
	public ArrayList<Double> getF1Scores(ArrayList<Double> precisions, ArrayList<Double> recalls)
	{
		 ArrayList<Double> F1Scores = new ArrayList<>();
			
			double C1_F1Scores = 0.0;
			double C4_F1Scores = 0.0;
			double C7_F1Scores = 0.0;
			
			C1_F1Scores = precisions.get(0) * recalls.get(1) / (precisions.get(0) + recalls.get(0));
			C4_F1Scores = precisions.get(1) * recalls.get(1) / (precisions.get(1) + recalls.get(1));
			C7_F1Scores = precisions.get(2) * recalls.get(2) / (precisions.get(2) + recalls.get(2));
			F1Scores.add(C1_F1Scores);
			F1Scores.add(C4_F1Scores);
			F1Scores.add(C7_F1Scores);
			
			return F1Scores;
	}
}
