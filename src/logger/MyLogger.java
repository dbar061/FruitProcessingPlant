package logger;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;


public class MyLogger {
	public static void Log(String log){
		FileOutputStream fos;
		try {
			fos = new FileOutputStream("pcabro_log", true);
			PrintWriter pw = new PrintWriter(fos, true);
			pw.println(log);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
