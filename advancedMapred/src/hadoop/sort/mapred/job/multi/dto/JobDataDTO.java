package hadoop.sort.mapred.job.multi.dto;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Writable;

public class JobDataDTO implements Writable{
	private String id;
	private String jobId;
	private String comment;
	private int grade;
	
	public JobDataDTO(){
		
	}
	
	public JobDataDTO(String id, String jobId, String comment, int grade) {
		super();
		this.id = id;
		this.jobId = jobId;
		this.comment = comment;
		this.grade = grade;
	}

	@Override
	public void readFields(DataInput in) throws IOException {
		id = in.readUTF();
		jobId = in.readUTF();
		comment = in.readUTF();
		grade = in.readInt();
	}

	@Override
	public void write(DataOutput out) throws IOException {
		out.writeUTF(id);
		out.writeUTF(jobId);
		out.writeUTF(comment);
		out.writeInt(grade);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	@Override
	public String toString() {
		return id+","+jobId+","+comment+","+grade;
	}
	
	

}
