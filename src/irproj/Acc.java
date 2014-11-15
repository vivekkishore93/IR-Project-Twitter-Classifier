package irproj;
import  twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.regex.Pattern;


public class Acc {
	
	static ArrayList<tweetCls> tweetsArray = new ArrayList<tweetCls>();
	static ArrayList<tweetCls> newTweets = new ArrayList<tweetCls>();
	
	public static void preprocess(ArrayList<tweetCls> tweetsArray)
	{
		Porter p = new Porter();
		RemoveStopwords r= new RemoveStopwords();
		
		Iterator<tweetCls> it = tweetsArray.iterator();
		while(it.hasNext())
		{
		    tweetCls obj = it.next();
		    obj.setTweetProcessed(r.removeStopWords(p.mainPorter(obj.getTweetContent())));
		    //Do something with obj
//		    System.out.println(obj.getTweetProcessed());
		}
		
		
		
		
	}
	
	public static void main(String args[]) throws TwitterException, IOException
	{	
		
		
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true)
		  .setOAuthConsumerKey("pXVYIbh2aQoL533aDpjBIevUq")
		  .setOAuthConsumerSecret("UoKceTjkl4UGUFpSZZ7nXRmmc2CS6uo0YdYQf5kPqrUWoCJiyA")
		  .setOAuthAccessToken("232916455-FJfG2u10j2gwARRsenN6hFoHCjaTOKtTk8X0aTjn")
		  .setOAuthAccessTokenSecret("LUATXkxz8X2KwUJGgJFum9Bf3JBMZoB6gyDq3KMvw694s");
		TwitterFactory tf = new TwitterFactory(cb.build());
		Twitter twitter = tf.getInstance();
		
		
		
//		Twitter twitter = new TwitterFactory(cb.build()).getInstance();
//		Twitter twitter = new TwitterFactory(cb.build()).getInstance();
		File file = new File("filename.txt");
		PrintWriter out1 = new PrintWriter(new FileWriter("D:\\code\\java\\irproj\\training\\tweets.txt"));

		PrintWriter out2 = new PrintWriter(new FileWriter("D:\\code\\java\\irproj\\training\\tweetID.txt"));
		PrintWriter out3 = new PrintWriter(new FileWriter("D:\\code\\java\\irproj\\training\\Processedtweets.txt"));
		
		PrintWriter out4 = new PrintWriter(new FileWriter("D:\\code\\java\\irproj\\testing\\tweets.txt"));
		PrintWriter out5 = new PrintWriter(new FileWriter("D:\\code\\java\\irproj\\testing\\tweetID.txt"));
		PrintWriter out6 = new PrintWriter(new FileWriter("D:\\code\\java\\irproj\\testing\\Processedtweets.txt"));
		 
		// if file doesnt exists, then create it
		if (!file.exists()) {
			file.createNewFile();
		}
		
		
		String categories[]={"sports","politics","entertainment"};
		
		
		for(int i=0;i<3;i++)
		{	
		
		

		
		try{
		
		        
		            Query query = new Query(categories[i]);
		            QueryResult result;
		            query.setCount(100);
		            result = twitter.search(query);
		            List<Status> tweets = result.getTweets();
		            for (Status tweet : tweets) {
		            	String str = tweet.getText();
		            	int retweet = tweet.getRetweetCount();
		            	int fav = tweet.getFavoriteCount();
		            	long tweetID = tweet.getId();
		            	int tweetClass = i;
		            	
		            	str = str.replaceAll("@\\w+|#\\w+|\\bRT\\b", "")
		                        .replaceAll("\n", " ")
		                        .replaceAll("[^\\p{L}\\p{N} ]+", " ")
		                        .replaceAll(" +", " ")
		                        .trim();
		            	str=str.replaceAll("\\bhttp\\b","");
		        		str=str.replaceAll("\\bt\\b","");
		        		str=str.replaceAll("\\bco\\b","");
		        		
		            	tweetCls tweetOb = new tweetCls(str,"",retweet,fav,tweetID,tweetClass);
		            	
		            	tweetsArray.add(tweetOb);
		            	out1.println(tweetOb.getTweetContent());
		            	out2.println(tweetID);
		            	
		                
		            }
		}
		catch(Exception e)
		{
		}
		
		}

		Paging paging = new Paging();
		paging.setCount(200);

		ResponseList<Status> Status = twitter.getHomeTimeline(paging);
		  
		    System.out.println("Showing home timeline.");
		
		    for (Status tweet : Status) {
     
		     
		        String str = tweet.getText();
            	int retweet = tweet.getRetweetCount();
            	int fav = tweet.getFavoriteCount();
            	long tweetID = tweet.getId();
            	
            	
            	str = str.replaceAll("@\\w+|#\\w+|\\bRT\\b", "")
                        .replaceAll("\n", " ")
                        .replaceAll("[^\\p{L}\\p{N} ]+", " ")
                        .replaceAll(" +", " ")
                        .trim();
            	str=str.replaceAll("\\bhttp\\b","");
        		str=str.replaceAll("\\bt\\b","");
        		str=str.replaceAll("\\bco\\b","");
            	tweetCls tweetOb = new tweetCls(str,"",retweet,fav,tweetID,-1);
            	newTweets.add(tweetOb);

            	out4.println(tweetOb.getTweetContent());
            	out5.println(tweetID);
            	
		        
		    }
		    
		        
		

		
		
		
		preprocess(tweetsArray);
		scoreWords.doEverything(tweetsArray,"score");
		
		preprocess(newTweets);
		scoreWords.doEverything(newTweets, "newtweetsScore");
		
		
		
			
	}
	
}
	


