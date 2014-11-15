package irproj;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;




public class scoreWords{

static //	HashMap<String,Double> scoremap=new HashMap<String,Double>();
	HashMap<String,Integer> idf = new HashMap<String,Integer>();
static HashMap<String,Integer> indexMap = new HashMap<String,Integer>();

static HashMap<Integer,Integer> label = new HashMap<Integer,Integer>();


	static HashMap<String,Integer> lastEnc = new HashMap<String,Integer>();

	
static 	int tempIndex = 0;
static int tweetno=0;	
static double[][] score;
public static void makeIDF(ArrayList<tweetCls> tweetsArray)
	{
	idf.clear();
	lastEnc.clear();
	indexMap.clear();
	label.clear();
	Iterator<tweetCls> it =tweetsArray.iterator();
		
//		Iterator<String> it = testing.iterator();
		
		
		tempIndex = 0;
		tweetno=0;
		
		while(it.hasNext())
		{
			
			tweetCls tweet = it.next();
			String s = tweet.getTweetProcessed();
			
//			String s = it.next();
			
			StringTokenizer st = new StringTokenizer(s);
			
			label.put(tweetno,tweet.getTweetClass());
			
			tweetno++;
			
			while(st.hasMoreTokens())
			{
				
				String a = st.nextToken();
				if(idf.containsKey(a)==false)
				{
					idf.put(a,1);
					lastEnc.put(a,tweetno);
					indexMap.put(a,tempIndex);
					tempIndex++;
					
				}
				else
				{
					int temp = lastEnc.get(a);
					if(temp == tweetno)
					{
						
					}
					else
					{
						int count = 1+ idf.get(a);
						idf.put(a, count);
						lastEnc.put(a,tweetno);
					}
				}
				
		
				
				
			}
			
			
			
			
		}
		
		
		
	}
	
	

	
//	public static void displayidf()
//	{
//		
////		Iterator<tweetCls> it =tweetsArray.iterator();
//		
//		Iterator<String> it = testing.iterator();
//		
//		while(it.hasNext())
//		{
//			
////			tweetCls tweet = it.next();
////			String s = tweet.getTweetProcessed();
//			
//			String s = it.next();
//			
//			StringTokenizer st = new StringTokenizer(s);
//			while(st.hasMoreTokens())
//			{
//				String a = st.nextToken();
//				
//				System.out.println(a+" = "+idf.get(a)+" index is "+indexMap.get(a));
//				
//				
//		
//				
//				
//			}
//			
//			
//			
//			
//		}
		
		
		
//	}
	
	
	
	
	
	
	public static void makeTable(ArrayList<tweetCls> tweetsArray)
	{
		 score= new double[tweetno][tempIndex];
		Iterator<tweetCls> it = tweetsArray.iterator();
		HashMap<String,Integer> presentCount = new HashMap<String,Integer>();
		
		int row=0;
		
		while(it.hasNext())
		{
			presentCount.clear();
			
			tweetCls tweet= it.next();
			
			String s =tweet.getTweetProcessed();
			StringTokenizer st = new StringTokenizer(s);
			
			while(st.hasMoreTokens())
			{
				String a = st.nextToken();
				
				if(presentCount.containsKey(a)==false)
				{
					presentCount.put(a, 1);
					
				}
				else
				{
					int count  = 1+ presentCount.get(a);
					presentCount.put(a, count);
				}
				
				
				
				
			}
			
			Set set = presentCount.entrySet();
		      // Get an iterator
		      Iterator i = set.iterator();
		      // Display elements
		      while(i.hasNext()) {
		         Map.Entry me = (Map.Entry)i.next();
		         String key = (String)me.getKey();
		         int value = (Integer)me.getValue();
		         
		         
		         int index =  indexMap.get(key);
		         
		         if((row<0)||(row>tweetno)||(index<0)||(index > tempIndex))
		         {
		        	 System.out.println(tweetno+"  XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX   "+tempIndex);
		        	 System.out.println("row is "+row+" index is"+index);
		         }
		         score[row][index] = Math.log10(1 + value)*idf.get(key);
		         
		    
		         
		         
		         
		      }
		      
		      
			
			
			
		row++;	
		}
		
		
		
	}
	
	
	public static void printScore(String filename) throws IOException
	{
		PrintWriter out = new PrintWriter(new FileWriter(filename+".txt"));
		
	
		
		for (int i = 0; i < tweetno; i++) {
			for (int j = 0; j < tempIndex; j++) {
				out.printf("%.1f\t",score[i][j]);
			}
			out.println(label.get(i));
		}
		out.close();
		
		
		
	}
	
	
	public static void doEverything(ArrayList<tweetCls> tweetsArray,String filename) throws IOException
	{
		makeIDF(tweetsArray);
		makeTable(tweetsArray);
		printScore(filename);
	}
	
	
	public static void main(String args[])throws IOException
	{
				
	}
	
	
	
	
	
}