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
		int truePositives=0;
		int trueNegatives=0;
		int falsePositives=0;
		int falseNegatives=0;
		
		
		
		List<Integer> cluster1 = new ArrayList<>();
		List<Integer> cluster2 = new ArrayList<>();
		List<Integer> cluster3 = new ArrayList<>();
	
		
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
				cluster2 = cluster;
			}
			else
			{
				cluster3 = cluster;
			}
		}
		
		// Now we have used majority voting and assigned clusters with most votes to their respective predicted clusters
		// Matching predef1 and cluster 1
		for(int i:cluster1)
		{
			if(preDef1.contains(i))
			{
				truePositives++;
			}
			else
			{
				falsePositives++;
			}
		}
		
		//Matching predef2 and cluster 2
		for(int i:cluster2)
		{
			if(preDef2.contains(i))
			{
				truePositives++;
			}
			else
			{
				falsePositives++;
			}
		}
		
		//Matching predef3 and cluster 3
		for(int i:cluster3)
		{
			if(preDef3.contains(i))
			{
				truePositives++;
			}
			else
			{
				falsePositives++;
			}
		}
		
		int total  = truePositives + falsePositives;
		//System.out.print(truePositives + " " + falsePositives);

		double accuracy = truePositives/(double)total;
		
		System.out.println(accuracy*100);
		
		
	}
}
