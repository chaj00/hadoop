package kitri.hadoop.mapred04;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class AirOptionMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
	private final static IntWritable one = new IntWritable(1); 
	private Text outputKey = new Text();
	private String jobType;//외부에서  사용자가 입력하는 값을 저장할 변수
	
	@Override
	protected void setup(
			Mapper<LongWritable, Text, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		jobType = context.getConfiguration().get("jobType");
	}

	@Override
	protected void map(LongWritable key, Text value,
			Mapper<LongWritable, Text, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {

		String[] values = value.toString().split(",");
		
		if(key.get()>0 & values!=null & values.length>0){
			if(jobType.equals("departure")){
				if(isStringInt(values[15])){
					int delay = Integer.parseInt(values[15]);
					
					if(delay >0){
						outputKey.set(values[1]);
						context.write(outputKey, one);
					}
				}
			}else if (jobType.equals("arrival")){
				if(isStringInt(values[14])){
					int delay = Integer.parseInt(values[14]);
					
					if(delay >0){
						outputKey.set(values[1]);
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
