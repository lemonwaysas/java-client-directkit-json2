package lw.api.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.DatagramSocket;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.SocketException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;


public class Program {

	private Map <String,String> params ;
	final String DIRECTKIT_JSON2="https://sandbox-api.lemonway.fr/mb/demo/dev/directkitjson2/Service.asmx";
	final private String version ="1.8";
	final private String ua="java"+System.getProperty("java.version");


	public Program(String login,String password){
		this.params=new HashMap<String,String>();
		this.params.put("wlLogin",login) ;
		this.params.put("version", version);
		this.params.put("walletIp", this.getIp());
		this.params.put("wlPass", password);
		this.params.put("walletUa", ua);
	}

	public  JSONObject  callService(String serviceName, Map <String,String> parameters) {
		this.params.putAll(parameters);
		String url_api=DIRECTKIT_JSON2.concat("/").concat(serviceName);
		Map<String,Map<String,String>> request = new HashMap<String,Map<String,String>>();
		this.params.putAll(parameters);
		request.put("p",this.params);
		JSONObject response = null;
		HttpURLConnection connection = null;
		try {
			// Create connection
			URL url;
			//try {
			url = new URL(url_api);

			connection = (HttpURLConnection) url.openConnection();

			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/json; charset=utf-8");

			connection.setUseCaches(false);
			connection.setDoOutput(true);

			// Send request
			JSONObject param_request = new JSONObject(request);
			OutputStreamWriter wr= new OutputStreamWriter(connection.getOutputStream());
			wr.write(param_request.toString());
			wr.flush();
			wr.close();

			// Get Response
			InputStreamReader isr = new InputStreamReader(connection.getInputStream());
			BufferedReader buf = new BufferedReader(isr);
			try {
				String s, s2 = new String();
				while ((s = buf.readLine()) != null) {
					s2 += s + "\n";
				}
				response = new JSONObject(s2);
				response=response.getJSONObject("d");
			}
			finally {
				buf.close();
				isr.close();
			}

		}catch (JSONException e ){
			e.printStackTrace();
			
		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e ){
			e.printStackTrace();
		}

		finally {
			if (connection != null) {
				connection.disconnect();
			}

		}

		return response ;	

	}

	private  String getIp(){
		try {
			DatagramSocket socket = new DatagramSocket();
			socket.connect(InetAddress.getByName("8.8.8.8"), 10002);
			String ip = socket.getLocalAddress().getHostAddress();
			socket.close();
			return ip;
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		} catch (SocketException e1) {
			// TODO Auto-generated catch block
			//e1.printStackTrace();
		}
		return null;
	}
}





