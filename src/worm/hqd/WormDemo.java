package worm.hqd;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 2019-03-11
 * java实现网络爬虫练习
 * @author hqd
 *
 */
public class WormDemo {

	public static void main(String[] args) {
		URL url = null;
		URLConnection urlConn = null;
		BufferedReader br = null;
		PrintWriter pw = null;
		String regex = "https://[\\w+\\.?/?]+\\.[A-Za-z]+"; 	//url匹配规则
		Pattern pattern = Pattern.compile(regex);
		try {
			url = new URL("https://www.hupu.com"); 	//爬取的网址(虎扑)
			urlConn = url.openConnection();
			//将爬取的链接放到D盘的WormDemo.txt文件中
			pw = new PrintWriter(new FileWriter("D:/WormDemo.txt"),true); 	
			br = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
			String buf = null;
			while((buf = br.readLine()) != null) {
				Matcher buf_m = pattern.matcher(buf);
				while(buf_m.find()) {
					pw.println(buf_m.group());
				}
			}
			System.out.println("爬取成功^_^");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			pw.close();
		} 	
	}
	
}
