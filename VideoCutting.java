import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 视频分割
 * 
 * @author zhangle
 *
 */
public class VideoCutting {
	/**
	 * 运行命令
	 * 
	 * @param command
	 */
	public void runCmd(String command) {
		try {
			Runtime rt = Runtime.getRuntime();
			Process proc = rt.exec(command);
			InputStream stderr = proc.getErrorStream();
			InputStreamReader isr = new InputStreamReader(stderr);
			BufferedReader br = new BufferedReader(isr);
			String line = null;
			System.out.println("<ERROR>");
			while ((line = br.readLine()) != null)
				System.out.println(line);
			System.out.println("</ERROR>");
			int exitVal = proc.waitFor();
			System.out.println("Process exitValue: " + exitVal);
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

	/**
	 * 分割视频
	 * @param infile 输入文件名
	 * @param outfile 输出文件名
	 * @param size 视频大小
	 * @return
	 */
	public boolean cut(String infile, String outfile, int size) {
		// Todo
		String cutintoPieces = "avidemux2_cli --nogui --load " + infile
				+ " --autosplit " + size + " --save " + outfile;
		;
		try {
			Runtime rt = Runtime.getRuntime();
			Process proc = rt.exec(cutintoPieces);
			InputStream stderr = proc.getErrorStream();
			InputStreamReader isr = new InputStreamReader(stderr);
			BufferedReader br = new BufferedReader(isr);
			String line = null;
			System.out.println("<ERROR>");
			while ((line = br.readLine()) != null)
				System.out.println(line);
			System.out.println("</ERROR>");
			int exitVal = proc.waitFor();
			System.out.println("Process exitValue: " + exitVal);
		} catch (Throwable t) {
			t.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 *  main
	 * @param args
	 */
	public static void main(String args[]) {
		VideoCutting vc = new VideoCutting();
		String infile = "/home/zhangle/test.avi";
		String outfile = "/home/zhangle/1.avi";
		//分割
		int size = 10;//按照大小进行分割,即每段视频大小为10M
		if (vc.cut(infile, outfile, size)) {
			System.out.println("The cut is finished!");
		} else {
			System.out.println("The cut is unfinished!");
		}
	}
}
