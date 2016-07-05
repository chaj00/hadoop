package kitri.hadoop.hdfs.exam01;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

//hdfs를 제어
/*
 * HDFS를 자바 API로 제어
 * API를 이용해서 HDFS에 파일을 생성하는 예제
 * 사용자가 지정한 경로에 텍스트 파일을 생성하는 작업
 */

public class HDFSExam01 {
	public static void main(String[] args){
		//1. 파일 시스템을 제어할 수 있는 객체를 생성
		//-> core-site.xml파일과 같은 하둡의 설정파일을 
		//접근해서 내용을 조회하거나 설정할 수 있도록 모델링된 객체를 사용
		Configuration conf = new Configuration();

		//2. HDFS를 접근할 수 있는 객체를 생성 -HDFS
		FileSystem hdfs = null;
		try {
			hdfs = FileSystem.get(conf);
			
			//3. 경로에 대한 작업
			Path path = new Path(args[0]);
			
			//4. HDFS에 파일을 저장
			FSDataOutputStream hdfsOut = hdfs.create(path);
			
			//5. HDFS에 저장할 파일을 읽어서 저장하기
			hdfsOut.writeUTF(args[1]);
			/*BufferedReader br = new BufferedReader(new FileReader("src/data/test.txt"));
			while(true){
				String line = br.readLine();
				if(line==null){
					break;
				}
				hdfsOut.writeUTF(line);
			}
			
			br.close();*/
			hdfs.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
