import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import lw.api.client.Program;

public class Main {



	public static void main (String[] args ){
		Program test = new Program("society", "123456");
		String serviceName="GetWalletDetails";
		Map<String, String > param = new HashMap<String,String>();
		param.put("wallet","sc");
		JSONObject response=test.callService(serviceName, param);
		System.out.println("Response without d "+response);
	}
}