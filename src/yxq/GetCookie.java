package yxq;


import java.io.IOException;
import java.net.HttpURLConnection;
//��ȡcookie�࣬ͨ����ȡ��������Ӧ(responseHeader)���cookieֵ
public class GetCookie {

	
	public String getCookie(HttpURLConnection conn) throws IOException {
		
			 String sessionId = "";  
		     String cookieVal = "";  
		     String key = null;  
		     //ȡcookie  
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
