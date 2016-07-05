package hadoop.multioutput.mapred;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class MultiStockMapper extends
		Mapper<LongWritable, Text, Text, IntWritable> {
	private final static IntWritable one = new IntWritable(1);
	private Text outputKey = new Text();

	@Override
	protected void map(LongWritable key, Text value,
			Mapper<LongWritable, Text, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		if (key.get() > 0) {
			String[] values = value.toString().split(",");
			if (values != null && values.length > 0) {
				double chk = Double.parseDouble(values[6])
						- Double.parseDouble(values[3]);
				String year = values[2].substring(0, 4);

				if (chk > 0) {
					outputKey.set("up," + year);
					context.write(outputKey, one);
				} else if (chk < 0) {
					outputKey.set("down," + year);
					context.write(outputKey, one);
				} else if (chk == 0) {
					outputKey.set("same," + year);
					context.write(outputKey, one);
				}
			}
		}

	}

}
