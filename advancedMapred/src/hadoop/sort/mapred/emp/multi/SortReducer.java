package hadoop.sort.mapred.emp.multi;



import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;

public class SortReducer
		extends Reducer<CustomKey, EmpDTO, NullWritable, EmpDTO> {
	
	NullWritable nullKey = NullWritable.get();
	
	private MultipleOutputs<NullWritable, EmpDTO> multiOut;
	
	@Override
	protected void setup(
			Reducer<CustomKey, EmpDTO, NullWritable, EmpDTO>.Context context)
			throws IOException, InterruptedException {
		multiOut = new MultipleOutputs<NullWritable, EmpDTO>(context);
	}
	@Override
	protected void cleanup(
			Reducer<CustomKey, EmpDTO, NullWritable, EmpDTO>.Context context)
			throws IOException, InterruptedException {
		//반드시 종료
		multiOut.close();
	}
	@Override
	protected void reduce(CustomKey key, Iterable<EmpDTO> values,
			Reducer<CustomKey, EmpDTO, NullWritable, EmpDTO>.Context context)
			throws IOException, InterruptedException {
		
		for(EmpDTO dto : values){
			multiOut.write(key.getDeptnm(),nullKey, dto);
		}
	}
}







