package yxq;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

//java模拟登陆类
public class Login {
	private String name;
	private String password;
	private String cookie;
	public Login(String name2,String password2,String cookie) {
		this.name=name2;
		this.password=password2;
		this.cookie=cookie;
	}
		//将连接成功后的con返回
	public HttpURLConnection success() throws IOException {
		//将表单数据传入map
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("usrID", name);
		map.put("PassWd", password);
		map.put("toAddress", "cblogin.jsp");
		//向urlcon连接类传入要访问的servelt地址，cookie，以及变量referer(访问的url的前一个地址)
		UrlCon con=new UrlCon("http://mag.test.consh.com/cbLogin.cdcb", cookie,"http://mag.test.consh.com/");
		//获取装配好的con连接类
		HttpURLConnection conn=con.getHttpURLConnection();
		//遍历map对象，将属性名及参数以指定方式拼接
		String param="";
		Set<String> mapKey=map.keySet();
		for(String s:mapKey){
			param+=s+"="+map.get(s)+"&";
		}
		param+="1=1";
		//获取con连接类的out对象，将拼接好的参数写入，然后连接
		PrintWriter out = new PrintWriter(conn.getOutputStream());
		out.write(param);  
		out.flush();
		out.close();
		conn.connect();
		
		return conn;  
	}
}
