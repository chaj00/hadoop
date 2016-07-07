package hadoop.sort.mapred.emp.multi;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Writable;

public class EmpDTO implements Writable{
	private Integer deptno;
	private String ename;
	private Integer sal;
	private String job;
	
	public EmpDTO(){
		
	}
	
	
	public EmpDTO(Integer deptno, String ename, Integer sal, String job) {
		super();
		this.deptno = deptno;
		this.ename = ename;
		this.sal = sal;
		this.job = job;
	}



	@Override
	public void readFields(DataInput in) throws IOException {
		deptno = in.readInt();
		ename = in.readUTF();
		sal = in.readInt();
		job = in.readUTF();
		
	}

	@Override
	public void write(DataOutput out) throws IOException {
		out.writeInt(deptno);
		out.writeUTF(ename);
		out.writeInt(sal);
		out.writeUTF(job);
	}

	

	public int getDeptno() {
		return deptno;
	}



	public void setDeptno(Integer deptno) {
		this.deptno = deptno;
	}



	public String getEname() {
		return ename;
	}



	public void setEname(String ename) {
		this.ename = ename;
	}



	public int getSal() {
		return sal;
	}



	public void setSal(Integer sal) {
		this.sal = sal;
	}



	public String getJob() {
		return job;
	}



	public void setJob(String job) {
		this.job = job;
	}



	@Override
	public String toString() {
		return deptno+","+ename+","+sal+","+job;
	}
	
	

}
