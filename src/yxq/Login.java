package yxq;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

//javaģ���½��
public class Login {
	private String name;
	private String password;
	private String cookie;
	public Login(String name2,String password2,String cookie) {
		this.name=name2;
		this.password=password2;
		this.cookie=cookie;
	}
		//�����ӳɹ����con����
	public HttpURLConnection success() throws IOException {
		//�������ݴ���map
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("usrID", name);
		map.put("PassWd", password);
		map.put("toAddress", "cblogin.jsp");
		//��urlcon�����ഫ��Ҫ���ʵ�servelt��ַ��cookie���Լ�����referer(���ʵ�url��ǰһ����ַ)
		UrlCon con=new UrlCon("http://mag.test.consh.com/cbLogin.cdcb", cookie,"http://mag.test.consh.com/");
		//��ȡװ��õ�con������
		HttpURLConnection conn=con.getHttpURLConnection();
		//����map���󣬽���������������ָ����ʽƴ��
		String param="";
		Set<String> mapKey=map.keySet();
		for(String s:mapKey){
			param+=s+"="+map.get(s)+"&";
		}
		param+="1=1";
		//��ȡcon�������out���󣬽�ƴ�ӺõĲ���д�룬Ȼ������
		PrintWriter out = new PrintWriter(conn.getOutputStream());
		out.write(param);  
		out.flush();
		out.close();
		conn.connect();
		
		return conn;  
	}
}
