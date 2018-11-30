package com.iflytek.aiui.demo;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * AIUI WebAPI V2接口调用示例
 * 
 * 运行方法：直接运�? main()
 * 
 * 结果�? 控制台输出接口返回�?�信�?
 * 
 * @author iflytek_aiui
 * 
 */
public class WebaiuiDemo {
	private static final String URL = "http://api.xfyun.cn/v1/service/v1/tts";
//	private static final String URL = "http://openapi.xfyun.cn/v2/aiui";
	private static final String APPID = "5bea3cdf";
	private static final String API_KEY = "833c65d6feed4585b19480a9e52523b9";
	private static final String DATA_TYPE = "text";
	private static final String SCENE = "main";
	private static final String SAMPLE_RATE = "16000";
	private static final String AUTH_ID = "27a11e62db3ead0a2688daf15378887e";
	private static final String AUE = "raw";
	private static final String FILE_PATH = "D:\\workspace\\yksdym\\yksdy_2018\\ttsDemo\\source\\demo.txt";
	// 个�?�化参数，需转义
	private static final String PERS_PARAM = "{\\\"auth_id\\\":\\\"27a11e62db3ead0a2688daf15378887e\\\"}";
	
	public static void main(String[] args) throws IOException,ParseException, InterruptedException{
		Map<String, String> header = buildHeader();
		byte[] dataByteArray = readFile(FILE_PATH);
		String result = httpPost(URL, header, dataByteArray);
		System.out.println(result);		
	}

	private static Map<String, String> buildHeader() throws UnsupportedEncodingException, ParseException {
		String curTime = System.currentTimeMillis() / 1000L + "";
		// "voice_name = xiaoyan, text_encoding = UTF8, sample_rate = 16000, speed = 50, volume = 50, pitch = 50, rdn = 2"
		String param = "{"
		+"\"auf\":\""+"audio/L16;rate=16000"+"\","+"\"aue\":\"raw"+""+"\","+"\"voice_name\":\""+"xiaoyan"+"\","
		+"\"speed\":\""+"50"+"\","+"\"volume\":\""+"50"+"\","+"\"pitch\":\""+"50"+"\","+"\"engine_type\":\""+"intp65"+"\","
		+ "\"auth_id\":\""+AUTH_ID+"\",\"data_type\":\""+DATA_TYPE+"\",\"scene\":\""+SCENE+"\"}";
//		+ "\"auth_id\":\""+AUTH_ID+"\",\"data_type\":\""+DATA_TYPE+"\",\"scene\":\""+SCENE+"\"}";		
		System.out.println(" param "+param);
		//使用个�?�化参数时参数格式如下：
		//String param = "{\"aue\":\""+AUE+"\",\"sample_rate\":\""+SAMPLE_RATE+"\",\"auth_id\":\""+AUTH_ID+"\",\"data_type\":\""+DATA_TYPE+"\",\"scene\":\""+SCENE+"\",\"pers_param\":\""+PERS_PARAM+"\"}";
		String paramBase64 = new String(Base64.encodeBase64(param.getBytes("UTF-8")));
		System.out.println(" paramBase64 "+paramBase64);
		String checkSum = DigestUtils.md5Hex(API_KEY + curTime + paramBase64);
		

		Map<String, String> header = new HashMap<String, String>();
		header.put("X-Param", paramBase64);
		header.put("X-CurTime", curTime);
		header.put("X-CheckSum", checkSum);
		header.put("X-Appid", APPID);
		for(Map.Entry<String, String> entry: header.entrySet()) {
			System.out.println(entry.getKey()+"  "+entry.getValue());
		}
		return header;
	}
	
	private static byte[] readFile(String filePath) throws IOException {
		InputStream in = new FileInputStream(filePath);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024 * 4];
		int n = 0;
		while ((n = in.read(buffer)) != -1) {
			out.write(buffer, 0, n);
		}
		byte[] data = out.toByteArray();
		in.close();
		return data;
	}
	
	private static String httpPost(String url, Map<String, String> header, byte[] body) {
		String result = "";
		BufferedReader in = null;
		OutputStream out = null;
		try {
			URL realUrl = new URL(url);
			HttpURLConnection connection = (HttpURLConnection)realUrl.openConnection();
			for (String key : header.keySet()) {
				connection.setRequestProperty(key, header.get(key));
			}
			connection.setDoOutput(true);
			connection.setDoInput(true);
			
			//connection.setConnectTimeout(20000);
			//connection.setReadTimeout(20000);
			try {
				out = connection.getOutputStream();
				out.write(body);
				out.flush();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			try {
				in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				String line;
				while ((line = in.readLine()) != null) {
					result += line;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
