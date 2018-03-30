package dwq;


import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
//公共HttpURLConnection连接类  设置request参数
public class UrlCon{  
	private String referer;
    private String urlStr;
    private String cookie;
    public UrlCon(String urlStr,String cookie,String referer) {  
        super();  
        this.urlStr = urlStr; 
        this.cookie=cookie;
        this.referer=referer;
    }  
  //将设置好的con返回
    public HttpURLConnection getHttpURLConnection() {  
        HttpURLConnection conn = null;  
        try {  
            URL url = new URL(urlStr);  
            conn = (HttpURLConnection) url.openConnection();  
            conn.setConnectTimeout(5 * 1000);  
            conn.setDoOutput(true); 
            conn.setDoInput(true);
            conn.setRequestMethod("POST");  
            conn.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");  
            conn.setRequestProperty("Accept-Encoding", "gzip,deflate");  
            conn.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.9"); 
            conn.setRequestProperty("Cache-Control", "max-age=0");  
            conn.setRequestProperty("Connection", "keep-alive");  
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded"); 
            conn.setRequestProperty("Cookie", cookie);
            conn.setRequestProperty("Host", "192.168.0.110:8080"); 
            conn.setRequestProperty("Origin", "http://192.168.0.110:8080");
            conn.setRequestProperty("Referer", referer);
            conn.setRequestProperty("Upgrade-Insecure-Requests", "1");
            conn.setRequestProperty("User-Agent",   
            		"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36");
              
        } catch (MalformedURLException e) {  
            e.printStackTrace();  
        } catch (ProtocolException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        return conn;  
    }
    
}  