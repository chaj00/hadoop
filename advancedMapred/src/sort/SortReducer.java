package sort;


import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Reducer;

public class SortReducer
		extends Reducer<CustomKey, IntWritable, CustomKey, IntWritable> {
	
	private IntWritable resultValue = new IntWritable();
	private CustomKey resultKey = new CustomKey();
	
	@Override
	protected void reduce(CustomKey key, Iterable<IntWritable> values,
			Reducer<CustomKey, IntWritable, CustomKey, IntWritable>.Context context)
			throws IOException, InterruptedException {
		System.out.println(key.toString());
		int sum = 0;
		Integer beforeGrade = key.getGrade();
		for (IntWritable val:values){
			if(beforeGrade!=key.getGrade()){
				resultValue.set(sum);
				resultKey.setJobId(key.getJobId());
				resultKey.setGrade(beforeGrade);
				context.write(resultKey, resultValue);
				sum=0;
			}
			
			sum = sum+val.get();
			beforeGrade = key.getGrade();
			
		}
		
		if(key.getGrade()==beforeGrade){
			resultValue.set(sum);
			resultKey.setJobId(key.getJobId());
			resultKey.setGrade(key.getGrade());
		}
		
		resultValue.set(sum);
		context.write(key, resultValue);
		
	}
	

}







