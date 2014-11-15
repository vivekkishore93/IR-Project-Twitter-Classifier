package irproj;

public class tweetCls {
	String tweetContent;
	String tweetProcessed;
	
	int retweet;
	int fav;
	long tweetID;
	int tweetClass;
	
	
	public tweetCls(String tweetContent,String tweetProcessed, int retweet, int fav, long tweetID,
			int tweetClass) {
		super();
		this.tweetContent = tweetContent;
		this.tweetProcessed = tweetProcessed;
		this.retweet = retweet;
		this.fav = fav;
		this.tweetID = tweetID;
		this.tweetClass = tweetClass;
		
	}
	
	
	public static void main(String args[])
	{
		
		
		
		
	}


	public String getTweetContent() {
		return tweetContent;
	}


	public void setTweetContent(String tweetContent) {
		this.tweetContent = tweetContent;
	}
	public String getTweetProcessed() {
		return tweetProcessed;
	}


	public void setTweetProcessed(String tweetProcessed) {
		this.tweetProcessed = tweetProcessed;
	}


	public int getRetweet() {
		return retweet;
	}


	public void setRetweet(int retweet) {
		this.retweet = retweet;
	}


	public int getFav() {
		return fav;
	}


	public void setFav(int fav) {
		this.fav = fav;
	}


	public long getTweetID() {
		return tweetID;
	}


	public void setTweetID(long tweetID) {
		this.tweetID = tweetID;
	}


	public int getTweetClass() {
		return tweetClass;
	}


	public void setTweetClass(int tweetClass) {
		this.tweetClass = tweetClass;
	}

}
