package kitri.hadoop.mapred02;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class StockMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
	private final static IntWritable one = new IntWritable(1); 
	private Text outputKey = new Text();
	
	@Override
	protected void map(LongWritable key, Text value,
			Mapper<LongWritable, Text, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {

		String[] values = value.toString().split(",");
		if(values[0].equals("NASDAQ")){
			double chk = Double.parseDouble(values[6])- Double.parseDouble(values[3]);
			if(chk>0){
				String mapkey = values[2].substring(0, 4);
				outputKey.set(mapkey);
				context.write(outputKey, one);
			}
		}
		
		
		
		
		
		
	}
	
}
