package hadoop.sort.mapred.emp.multi;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableUtils;

public class CustomKey implements WritableComparable<CustomKey> {
	private String deptnm;
	private Integer deptno;
	private Integer sal;



	public String getDeptnm() {
		return deptnm;
	}

	public void setDeptnm(String deptnm) {
		this.deptnm = deptnm;
	}

	public Integer getDeptno() {
		return deptno;
	}

	public void setDeptno(Integer deptno) {
		this.deptno = deptno;
	}

	public Integer getSal() {
		return sal;
	}

	public void setSal(Integer sal) {
		this.sal = sal;
	}

	@Override
	public void write(DataOutput out) throws IOException {
		out.writeUTF(deptnm);
		out.writeInt(deptno);
		out.writeInt(sal);
	}

	@Override
	public void readFields(DataInput in) throws IOException {
		deptnm = in.readUTF();
		deptno = in.readInt();
		sal = in.readInt();
	}

	@Override
	public int compareTo(CustomKey key) {

		int result = deptno.compareTo(key.deptno);
		if(result==0){//this의 year와 매개변수로 전달되는 year와 같을 경우
			result = sal.compareTo(key.sal);
		}
		return result;
	}
	public String toString(){
		return 
		(new StringBuffer())
		.append(deptnm).append(",").append(deptno).append(",").append(sal).toString(); 
	}
}
