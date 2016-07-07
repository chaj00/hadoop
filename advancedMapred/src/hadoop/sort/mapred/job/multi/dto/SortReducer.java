package hadoop.sort.mapred.job.multi.dto;



import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;

public class SortReducer
		extends Reducer<CustomKey, JobDataDTO, NullWritable, JobDataDTO> {
	
	NullWritable nullKey = NullWritable.get();
	
	/*private MultipleOutputs<CustomKey, JobDataDTO> multiOut;
	@Override
	protected void setup(
			Reducer<CustomKey, JobDataDTO, CustomKey, JobDataDTO>.Context context)
			throws IOException, InterruptedException {
		multiOut = new MultipleOutputs<CustomKey, JobDataDTO>(context);
	}
	@Override
	protected void cleanup(
			Reducer<CustomKey, JobDataDTO, CustomKey, JobDataDTO>.Context context)
			throws IOException, InterruptedException {
		//반드시 종료
		multiOut.close();
	}*/
	@Override
	protected void reduce(CustomKey key, Iterable<JobDataDTO> values,
			Reducer<CustomKey, JobDataDTO, NullWritable, JobDataDTO>.Context context)
			throws IOException, InterruptedException {
		
		for(JobDataDTO dto : values){
			context.write(nullKey, dto);
		}
		
		
		/*//jobid로 평가하기
		String jobId = key.getJobid();
		if(jobId.startsWith("BAD")){
			for (JobDataDTO val:values){
				resultKey.setGrade(key.getGrade());
				resultKey.setJobid(jobId);
				multiOut.write("BAD",resultKey, result);
			}
		}else if(jobId.startsWith("RE4")){
			for (JobDataDTO val:values){
				resultKey.setGrade(key.getGrade());
				resultKey.setJobid(jobId);
				multiOut.write("RE4",resultKey, result);
			}
		}*/
	}
}







