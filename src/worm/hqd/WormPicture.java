package worm.hqd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 使用java爬虫爬取图片
 * 2019-03-11
 * @author hqd
 *
 */
public class WormPicture {

	//地址
	private static final String URL = "http://http://image.baidu.com/";
	//获取img标签正则
	private static final String IMGURL_REG = "<img.*src=(.*?)[^>]*?>";
	//获取src路径的正则
	private static final String IMGSRC_REG = "[a-zA-Z]+://[^\\s]*";
	
	//获取html内容
	public static String getHTML(String srcUrl) {
		URL url = null;
		URLConnection urlConn = null;
		InputStream is = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		String line = null;
		StringBuffer sb = null;
		String sbStr = null;
		try {
			url = new URL(srcUrl);
			urlConn = url.openConnection();
			is = urlConn.getInputStream();
			isr = new InputStreamReader(is);
			br = new BufferedReader(isr);
			sb = new StringBuffer();
			while((line = br.readLine()) != null) {
				sb.append(line);
				sb.append("\n");
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
				isr.close();
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sbStr;
	}
	
	//获取image url地址
	public static List<String> getImageURL(String html){
		Matcher matcher = Pattern.compile(IMGURL_REG).matcher(html);
		List<String> list = new ArrayList<String>();
		while(matcher.find()) {
			list.add(matcher.group());
		}
		return list;
	}
	
	//获取image src地址
	public static List<String> getImageSrc(List<String> listUrl){
		List<String> listSrc = new ArrayList<String>();
		for(String img : listUrl) {
			Matcher matcher = Pattern.compile(IMGSRC_REG).matcher(img);
			while(matcher.find()) {
				listSrc.add(matcher.group().substring(0, matcher.group().length()-1));
			}
		}
		return listSrc;
	}
	
	//下载图片
	
	
}
