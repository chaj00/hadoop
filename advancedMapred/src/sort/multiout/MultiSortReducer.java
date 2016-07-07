package sort.multiout;


import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;

public class MultiSortReducer
		extends Reducer<CustomKey, IntWritable, CustomKey, IntWritable> {
	
	private IntWritable resultValue = new IntWritable();
	private CustomKey resultKey = new CustomKey();
	private MultipleOutputs<CustomKey, IntWritable> multiOut;
	
	@Override
	protected void setup(
			Reducer<CustomKey, IntWritable, CustomKey, IntWritable>.Context context)
			throws IOException, InterruptedException {
		multiOut = new MultipleOutputs<CustomKey, IntWritable>(context);
	}
	
	
	@Override
	protected void cleanup(
			Reducer<CustomKey, IntWritable, CustomKey, IntWritable>.Context context)
			throws IOException, InterruptedException {
		//반드시 종료
		multiOut.close();
		
	}

	@Override
	protected void reduce(CustomKey key, Iterable<IntWritable> values,
			Reducer<CustomKey, IntWritable, CustomKey, IntWritable>.Context context)
			throws IOException, InterruptedException {
		System.out.println(key.toString());
			
		if(key.getMod().equals("BAD")){
			for (IntWritable val:values){
				resultKey.setMod(key.getMod());
				resultKey.setJobId(key.getJobId());
				resultKey.setGrade(key.getGrade());
				multiOut.write(key.getMod(), resultKey, resultValue);
			}
		}else if(key.getMod().equals("RE4")){
			for (IntWritable val:values){
				resultKey.setMod(key.getMod());
				resultKey.setJobId(key.getJobId());
				resultKey.setGrade(key.getGrade());
				multiOut.write(key.getMod(), resultKey, resultValue);
			}
		}
		
	}

}







