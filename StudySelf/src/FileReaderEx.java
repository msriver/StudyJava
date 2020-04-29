import java.io.*;

public class FileReaderEx {
	public static void main(String[] args) {
		FileReader fin = null;
		
		try {
			fin = new FileReader("c:\\temp\\test.txt");
			int c;
			while((c=fin.read()) != -1) {
				System.out.println((char)c);
			}
			fin.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
