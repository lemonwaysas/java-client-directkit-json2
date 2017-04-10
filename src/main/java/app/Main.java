package app;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import lw.api.client.LemonWayService;

public class Main {

	public static void main(String[] args) {
		LemonWayService service = new LemonWayService("society", "123456");
		//GetWalletDetailsTest(service);
		try {
			UploadFileTest(service);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void GetWalletDetailsTest(LemonWayService service) {
		Map<String, String> param = new HashMap<String, String>();
		param.put("wallet", "9999");
		JSONObject response = service.callService("GetWalletDetails", param);
		System.out.println("Response without d " + response);

	}

	public static void UploadFileTest(LemonWayService service) throws IOException  {
		final String file_name="src/main/resources/test.jpg";
		Path path = Paths.get(file_name);
		byte[] data = Files.readAllBytes(path);
		byte[] encoded = Base64.getEncoder().encode(data);
		
		Map<String, String> param = new HashMap<String, String>();
		param.put("wallet", "9999");
		param.put("fileName", path.getFileName().toString());
		param.put("type", "3");
		param.put("buffer", new String(encoded));
		JSONObject response = service.callService("UploadFile", param);
		System.out.println("Response without d " + response);



	}

}