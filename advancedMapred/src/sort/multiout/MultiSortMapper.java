package sort.multiout;


import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class MultiSortMapper 
				extends Mapper<LongWritable, Text, CustomKey, IntWritable>{
	private final static IntWritable one = new IntWritable(1);
	private CustomKey word = new CustomKey();
	
	@Override
	protected void map(LongWritable key, Text value,
			Mapper<LongWritable, Text, CustomKey, IntWritable>.Context context)
			throws IOException, InterruptedException {
		if(key.get()>0){ 
			String[] line = value.toString().split(",");
			if(line!=null && line.length>0){
				String mod = line[1].substring(0,3);
				if(mod.equals("BAD")){
					word.setMod(mod);
					word.setJobId(line[1]);
					word.setGrade(new Integer(line[3]));
					context.write(word, one);
				}else if(mod.equals("RE4")){
					word.setMod(mod);
					word.setJobId(line[1]);
					word.setGrade(new Integer(line[3]));
					context.write(word, one);
				}
				
			}
		}
	}
	

}




