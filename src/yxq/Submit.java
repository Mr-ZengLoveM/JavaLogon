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
//�����ύ��
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
		map.put("unitTypechk", "��������");
		map.put("unitType", "��������");
		map.put("unit", "�����ľ�");
		map.put("zfbmsel1", "�����ľ�");
		map.put("psnName", "zyh11");
		map.put("sex", "��");
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
//			//����������bufferedReader�ķ�ʽ���  
			BufferedReader bufferedReader = new BufferedReader(    
			      new InputStreamReader(urlStream));    
			String ss = null;    
			String total = "";    
			//��������У�����Ƿ�����ɹ�  
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
		//java��һ��ģ���½���ܵ�½���������ܱ������
			Login login=new Login("admin","c4ca4238a0b923820dcc509a6f75849b","JSESSIONID=null");
			HttpURLConnection con=login.success();
			String sessionId=new GetCookie().getCookie(con);
			System.out.println(sessionId);
			//java�ڶ���ģ���½�������������
			Login login2=new Login("admin","c4ca4238a0b923820dcc509a6f75849b",sessionId);
			HttpURLConnection con2=login2.success();
			//�ж��Ƿ��¼�ɹ�
		    String key = null; 
		    boolean flag=false;
		    for(int i = 1; (key = con2.getHeaderFieldKey(i)) != null; i++){  
		    	if(key.equalsIgnoreCase("Content-Length")){ 
		    		flag=true;
		    		break;
		    	}
		    }
		    if(flag==true){
		    	System.out.println("��¼�ɹ�");
	        	new Submit(sessionId).sava();//�ڶ��ε�¼�ɹ��󣬱���¼������
		    }else{
		    	System.out.println("��¼ʧ��");
		    }
	}
}
