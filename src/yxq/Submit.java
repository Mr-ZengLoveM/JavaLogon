package yxq;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
//保存提交类
public class Submit {
	private String cookie;
	/**
	 * @param args
	 */
	public Submit(String cookie) {
		this.cookie=cookie;
	}
	private void sava(){
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("unitTypechk", "政府部门");
		map.put("unitType", "政府部门");
		map.put("unit", "区发改局");
		map.put("zfbmsel1", "区发改局");
		map.put("psnName", "zyh11");
		map.put("sex", "男");
		map.put("psnSample", "ZYH11");
		map.put("telephone", "110");
		map.put("mobie", "120");
		map.put("psnPowerword", "76d80224611fc919a5d54f0ff9fba446");
		map.put("realName", "MrZeng11");
		map.put("cbdo", "1");
		map.put("ID", "0");
		map.put("jumpaddress", "base/psnMore.cdcb");
		UrlCon con=new UrlCon("http://mag.test.consh.com/base/psnEdit.cdcb", cookie,"http://mag.test.consh.com/base/psnAdd.cdcb");
		HttpURLConnection conn=con.getHttpURLConnection();
		
		String param="";
		Set<String> mapKey=map.keySet();
		for(String s:mapKey){
			param+=s+"="+map.get(s)+"&";
		}
        param+="1=1";
        
		try {
			OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");  
			out.write(param);
			out.flush();
			out.close();
		    conn.connect();
		    
		    InputStream urlStream = conn.getInputStream();    
//			//将输入流以bufferedReader的方式输出  
			BufferedReader bufferedReader = new BufferedReader(    
			      new InputStreamReader(urlStream));    
			String ss = null;    
			String total = "";    
			//输出结果。校验你是否操作成功  
			while ((ss = bufferedReader.readLine()) != null) {    
			  total += ss;    
			}
			System.out.println(total);  
		    
		}catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	public static void main(String[] args) throws IOException {
		//java第一次模拟登陆，能登陆，但并不能保存操作
			Login login=new Login("admin","c4ca4238a0b923820dcc509a6f75849b","JSESSIONID=null");
			HttpURLConnection con=login.success();
			String sessionId=new GetCookie().getCookie(con);
			System.out.println(sessionId);
			//java第二次模拟登陆，能做保存操作
			Login login2=new Login("admin","c4ca4238a0b923820dcc509a6f75849b",sessionId);
			HttpURLConnection con2=login2.success();
			//判断是否登录成功
		    String key = null; 
		    boolean flag=false;
		    for(int i = 1; (key = con2.getHeaderFieldKey(i)) != null; i++){  
		    	if(key.equalsIgnoreCase("Content-Length")){ 
		    		flag=true;
		    		break;
		    	}
		    }
		    if(flag==true){
		    	System.out.println("登录成功");
	        	new Submit(sessionId).sava();//第二次登录成功后，保存录入数据
		    }else{
		    	System.out.println("登录失败");
		    }
	}
}
