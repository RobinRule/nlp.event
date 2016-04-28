package nlp.model.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

/**
 * @author Bruce Yang
 * @author Robin (Some modification)
 */
public class UnixScript {
	private static ProcessBuilder pB;
	private StringBuilder stdout;
	private StringBuilder stderr;
	public UnixScript(File f) throws IOException{
		pB.directory(f);
		stdout = new StringBuilder();
		stderr = new StringBuilder();
	}
	 /**
	 * 执行 mac(unix) 脚本命令~
	 * @param command
	 * @return
	 * @throws IOException 
	 */
	public void execute(String command) throws IOException {
		pB.command(command);
		Process proc = pB.start();
		stdout = new StringBuilder();
		stderr = new StringBuilder();
		BufferedReader stdInput = new BufferedReader(new 
		InputStreamReader(proc.getInputStream()));

		BufferedReader stdError = new BufferedReader(new 
		InputStreamReader(proc.getErrorStream()));
		// read the output from the command
		System.out.println("Here is the standard output of the command:\n");
		String s = null;
		while ((s = stdInput.readLine()) != null) {
			stdout.append(s+'\n');
		}
		// read any errors from the attempted command
		System.out.println("Here is the standard error of the command (if any):\n");
		while ((s = stdError.readLine()) != null) {
			stderr.append(s+'\n');
		}
	}

  /**
  * 读取控制命令的输出结果
  * 原文链接：http://lavasoft.blog.51cto.com/62575/15599
  * @param cmd 命令
  * @return 控制命令的输出结果
  * @throws IOException
  */
	public String getStdout() {
		return stdout.toString();
	}
	public String getStderr() {
		return stderr.toString();
	}
  
  public static void main(String[] args) throws Exception{

	  
  }
}
