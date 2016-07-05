package kitri.hadoop.mapred03;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class AirMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
	private final static IntWritable one = new IntWritable(1); 
	private Text outputKey = new Text();
	
	@Override
	protected void map(LongWritable key, Text value,
			Mapper<LongWritable, Text, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {

		String[] values = value.toString().split(",");
		outputKey.set(values[1]);
		if(key.get()>0 & values!=null && values.length>0){
			if(isStringInt(values[15])){
				int delay = Integer.parseInt(values[15]);
				
				if(delay >0){
					context.write(outputKey, one);
				}
				
			}
			
		}
		
	}
	
	public static boolean isStringInt(String s) {
	    try {
	        Integer.parseInt(s);
	        return true;
	    } catch (NumberFormatException e) {
	        return false;
	    }
	  }
	
}
