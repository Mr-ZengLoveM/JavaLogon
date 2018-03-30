package yxq;


import java.io.IOException;
import java.net.HttpURLConnection;
//获取cookie类，通过获取服务器响应(responseHeader)里的cookie值
public class GetCookie {

	
	public String getCookie(HttpURLConnection conn) throws IOException {
		
			 String sessionId = "";  
		     String cookieVal = "";  
		     String key = null;  
		     //取cookie  
		     for(int i = 1; (key = conn.getHeaderFieldKey(i)) != null; i++){  
		          if(key.equalsIgnoreCase("set-cookie")){  
		                cookieVal = conn.getHeaderField(i);  
		                cookieVal = cookieVal.substring(0, cookieVal.indexOf(";"));  
		                sessionId = sessionId + cookieVal + ";"; 
		          }
		          System.out.println(conn.getHeaderFieldKey(i));
		      }  
		return sessionId; 
	}

}
