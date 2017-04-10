package lw.api.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

public class LemonWayService {

	// private Map<String, String> params;
	private String login;
	private String password;
	final String DIRECTKIT_JSON2 = "https://sandbox-api.lemonway.fr/mb/hiep/dev/directkitjson2/Service.asmx";
	final private String version = "1.8";
	final private String ua = getUa();

	public LemonWayService(String login, String password) {
		this.login = login;
		this.password = password;
	}

	public JSONObject callService(String serviceName, Map<String, String> parameters) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("wlLogin", this.login);
		params.put("wlPass", this.password);
		params.put("version", version);
		params.put("walletIp", getIp());
		params.put("walletUa", ua);
		params.putAll(parameters);
		String url_api = DIRECTKIT_JSON2.concat("/").concat(serviceName);
		Map<String, Map<String, String>> request = new HashMap<String, Map<String, String>>();
		request.put("p", params);
		JSONObject response = null;
		HttpURLConnection connection = null;
		try {
			// Create connection
			URL url;
			url = new URL(url_api);

			connection = (HttpURLConnection) url.openConnection();

			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/json; charset=utf-8");

			connection.setUseCaches(false);
			connection.setDoOutput(true);

			// Send request
			JSONObject param_request = new JSONObject(request);
			OutputStreamWriter wr = new OutputStreamWriter(connection.getOutputStream());
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
				response = response.getJSONObject("d");
			} finally {
				buf.close();
				isr.close();
			}

		} catch (JSONException e) {
			e.printStackTrace();

		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		finally {
			if (connection != null) {
				connection.disconnect();
			}

		}

		return response;

	}

	/**
	 * This tutorial is a Console Application running on your server. if you are
	 * in a Web Application context, you will have to return the IP of your
	 * end-user instead.
	 * 
	 * @return IP of end-user
	 */
	private String getIp() {
		return "127.0.0.1";
	}

	/**
	 * This tutorial is a Console Application running on your server. if you are
	 * in a Web Application context, you will have to return the User-Agent of
	 * your end-user instead.
	 * 
	 * @return user-agent of end-user
	 */
	private String getUa() {

		return "java".concat(System.getProperty("java.version"));
	}
}
