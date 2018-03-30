package dwq;

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
		map.put("submitData", "��������");
		map.put("name", "name");
		UrlCon con=new UrlCon("http://192.168.0.110:8080/dcdep/admin/base/editRecord.cdcb", cookie,"http://192.168.0.110:8080/dcdep/admin/base/unitIntroduceEdit.jsp?key=name&_t=729752");
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
			Login login=new Login("admin","123456","");
			HttpURLConnection con=login.success();
			String sessionId=new GetCookie().getCookie(con);
			System.out.println(sessionId);
			//java�ڶ���ģ���½�������������
			Login login2=new Login("admin","123456",sessionId);
			HttpURLConnection con2=login2.success();
			String sessionId2=new GetCookie().getCookie(con2);
			System.out.println(sessionId2);
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
	        	//new Submit(sessionId).sava();//�ڶ��ε�¼�ɹ��󣬱���¼������
		    }else{
		    	System.out.println("��¼ʧ��");
		    }
	}
}
