package hadoop.sort.mapred.job.multi.dto;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableUtils;

public class CustomKey implements WritableComparable<CustomKey> {
	private String jobid;
	private Integer grade;

	
	public String getJobid() {
		return jobid;
	}

	public void setJobid(String jobid) {
		this.jobid = jobid;
	}

	public Integer getGrade() {
		return grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	@Override
	public void write(DataOutput out) throws IOException {
		WritableUtils.writeString(out, jobid);
		out.writeInt(grade);
	}

	@Override
	public void readFields(DataInput in) throws IOException {
		jobid = WritableUtils.readString(in);
		grade = in.readInt();
	}

	@Override
	public int compareTo(CustomKey key) {

		int result = jobid.compareTo(key.jobid);
		if(result==0){//this의 year와 매개변수로 전달되는 year와 같을 경우
			result = grade.compareTo(key.grade);
		}
		return result;
	}
	public String toString(){
		return 
		(new StringBuffer())
		.append(jobid).append(",").append(grade).toString(); 
	}
}
