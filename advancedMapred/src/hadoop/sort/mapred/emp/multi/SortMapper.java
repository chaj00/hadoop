package hadoop.sort.mapred.emp.multi;


import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class SortMapper 
				extends Mapper<LongWritable, Text, CustomKey, EmpDTO>{
	@Override
	protected void map(LongWritable key, Text value,
			Mapper<LongWritable, Text, CustomKey, EmpDTO>.Context context)
			throws IOException, InterruptedException {
		if(key.get()>0){ 
			String[] line = value.toString().split(",");
			//mapper실행 결과로 내보낼 데이터를 dto로 표현
			EmpDTO dto = new EmpDTO(new Integer(line[0]), line[1], new Integer(line[2]), line[3]);
			CustomKey customKey = new CustomKey();
			
			if(dto.getDeptno()==10){
				customKey.setDeptnm("insa");
				customKey.setDeptno(dto.getDeptno());
				customKey.setSal(dto.getSal());
				context.write(customKey, dto);
				
			}else if(dto.getDeptno()==20){
				customKey.setDeptnm("operation");
				customKey.setDeptno(dto.getDeptno());
				customKey.setSal(dto.getSal());
				context.write(customKey, dto);
			}else if(dto.getDeptno()==30){
				customKey.setDeptnm("system");
				customKey.setDeptno(dto.getDeptno());
				customKey.setSal(dto.getSal());
				context.write(customKey, dto);
			}
			
		}
	}
	

}




