package hadoop.sort.mapred.job.multi.dto;


import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class SortMapper 
				extends Mapper<LongWritable, Text, CustomKey, JobDataDTO>{
	@Override
	protected void map(LongWritable key, Text value,
			Mapper<LongWritable, Text, CustomKey, JobDataDTO>.Context context)
			throws IOException, InterruptedException {
		if(key.get()>0){ 
			String[] line = value.toString().split(",");
			//mapper실행 결과로 내보낼 데이터를 dto로 표현
			JobDataDTO dto = new JobDataDTO(line[0], line[1], line[2], Integer.parseInt(line[3]));
			CustomKey customKey = new CustomKey();
			customKey.setJobid(dto.getJobId());
			customKey.setGrade(dto.getGrade());
			context.write(customKey, dto);
		}
	}
	

}




