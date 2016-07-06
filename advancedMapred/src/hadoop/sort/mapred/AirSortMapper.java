package hadoop.sort.mapred;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class AirSortMapper extends Mapper<LongWritable, Text, CustomKey, IntWritable> {
	private final static IntWritable one = new IntWritable(1); 
	private CustomKey outputKey = new CustomKey();
	
	@Override
	protected void map(LongWritable key, Text value,
			Mapper<LongWritable, Text, CustomKey, IntWritable>.Context context)
			throws IOException, InterruptedException {
		if(key.get()>0){
			String[] values = value.toString().split(",");
			if(values!=null && values.length>0){
				if(isStringInt(values[15])){
					int delay = Integer.parseInt(values[15]);
					outputKey.setYear(values[0]);
					outputKey.setMonth(new Integer(values[1]));
					if(delay >0){
						context.write(outputKey, one);
					}
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
