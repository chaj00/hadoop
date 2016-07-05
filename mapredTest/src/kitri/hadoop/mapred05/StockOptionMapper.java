package kitri.hadoop.mapred05;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class StockOptionMapper extends
		Mapper<LongWritable, Text, Text, IntWritable> {
	private final static IntWritable one = new IntWritable(1);
	private Text outputKey = new Text();
	private String jobType;

	
	
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
		if (key.get() > 0) {
			
			String[] values = value.toString().split(",");
			if(values!=null & values.length>0){
				double chk = Double.parseDouble(values[6]) - Double.parseDouble(values[3]);
				if(jobType.equals("up")){
					if (chk > 0) {
						String mapkey = values[2].substring(0, 4);
						outputKey.set(mapkey);
						context.write(outputKey, one);
					}
				}else if(jobType.equals("down")){
					if (chk < 0) {
						String mapkey = values[2].substring(0, 4);
						outputKey.set(mapkey);
						context.write(outputKey, one);
					}
				}else if(jobType.equals("equal")){
					if (chk == 0) {
						String mapkey = values[2].substring(0, 4);
						outputKey.set(mapkey);
						context.write(outputKey, one);
					}
				}
			}
			

		}

	}

}
