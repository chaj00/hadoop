package kitri.hadoop.hdfs.exam01;

import java.io.BufferedReader;
import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class HDFSExam02 {

	public static void main(String[] args) {
		Configuration conf = new Configuration();
		
		FileSystem hdfs = null;
		
		try {
			hdfs = FileSystem.get(conf);
			
			Path path = new Path(args[0]);

			FSDataInputStream hdfsIn = hdfs.open(path);
			String inputdata = hdfsIn.readUTF();
			System.out.println("입력문자열:"+inputdata);
			
			/*byte[] writeBuf = new byte[1024];
			int len;

            while ((len = hdfsIn.read(writeBuf)) != -1){

            	System.out.println(new String(writeBuf, 0, len, "UTF-8"));

            }*/
          
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
