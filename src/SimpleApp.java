
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;

public class SimpleApp {
	public static void main(String[] args) {
		String logFile;
		
		if (args.length != 0) 
			logFile = args[0];
		else
			logFile = "/media/gf/Java/spark-1.4.0-bin-hadoop2.6/README.md";
	
		
		final SparkConf conf = new SparkConf().setAppName("Simple Application");

		final JavaSparkContext sc = new JavaSparkContext(conf);
		final JavaRDD<String> logData = sc.textFile(logFile).cache();

		final String[] check = getFilterSet();
		
		System.out.println("Start: " + new Date());
		for (int i =0; i < check.length; i++) {
			final int post = i;
			long count =  logData.filter(new Function<String, Boolean>() {
				public Boolean call(String s) {
					return s.contains(check[post]);
				}
			}).count();
			
			System.out.println("Lines with " + check[i] + ": " + count);
		}
		System.out.println("End: " + new Date());
		
		sc.close();
	}
	
	static String[] getFilterSet(){
		final Collection<String> list = new ArrayList<String>();
		for (int i = 0; i < 128; i++)
			if (Character.isDigit(i) || Character.isLetter(i)||Character.isSpaceChar(i))
				list.add(String.valueOf((char)i));
		
		return list.toArray(new String[list.size()]);
	}
	
}
