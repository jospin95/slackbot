package com.slack.bot;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.simple.JSONObject;

//import com.accl.util.ACCLConstant;

public class EndpointConnectUtils {
//	public JSONObject getRequest(String slack_url) {
//		JSONObject responseObject = null;
//		try {
//			URL obj = new URL(slack_url);
//			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
//
//			// optional default is GET
//			con.setRequestMethod("GET");
//
//			// add request header
//			//con.setRequestProperty("User-Agent", ACCLConstant.USER_AGENT);
//
//			int responseCode = con.getResponseCode();
//			// System.out.println("\nSending 'GET' request to URL : " + url);
//			// System.out.println("Response Code : " + responseCode);
//
//			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
//			String inputLine;
//			StringBuffer response = new StringBuffer();
//
//			while ((inputLine = in.readLine()) != null) {
//				response.append(inputLine);
//			}
//			in.close();
//
//			// print result
//			JSONParser parser = new JSONParser();
//			responseObject = (JSONObject) parser.parse(response.toString());
//			System.out.println("Response=====");
//			System.out.println(responseObject);
//		} catch (Exception e) {
//
//		}
//		return responseObject;
//	}

	public JSONObject postRequest(JSONObject requestBody, String slack_url) {
		try {
			slack_url = "https://slack.com/api/chat.postMessage";
			HttpPost httpPost = createConnectivity(slack_url);
			executeReq(requestBody.toString(), httpPost);
		} catch (Exception e) {

		}

		return null;
	}

	private void executeReq(String string, HttpPost httpPost) {
		try {
			HttpResponse response = null;
			String line = "";
			StringBuffer result = new StringBuffer();
			httpPost.setEntity(new StringEntity(string));
			HttpClient client = HttpClientBuilder.create().build();
			response = client.execute(httpPost);
			System.out.println("Post parameters : " + string);
			System.out.println("Response Code : " + response.getStatusLine().getStatusCode());
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			while ((line = reader.readLine()) != null) {
				result.append(line);
			}
			System.out.println("checking");
			System.out.println(result.toString());
		} 
			catch (Exception e) {

		}
	}

	

	private HttpPost createConnectivity(String url) {
		HttpPost post = new HttpPost(url);
		post.setHeader("Content-Type", "application/json");
		post.setHeader("Accept", "application/json");
		post.setHeader("X-Stream", "true");
		System.out.println("checking");
		System.out.println("=="+post);
		return post;
	}

//	public static String getTimeStamp() {
//		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd'T'HHmmss'Z'");
//		dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));// server timezone
//		return dateFormat.format(new Date());
//	
//	}

	//return responseObject;
//
	public JSONObject postRequest(String url, Map<String, String> reqMap) {
		JSONObject responseObject = null;

		try {

			CloseableHttpClient httpClient = HttpClientBuilder.create().build();
			JSONObject json = new JSONObject();
			//json.put("inputText", "hello");
			System.out.println("New auth===> " + reqMap.get("Authorization"));

			HttpPost request = new HttpPost(url);
			StringEntity params = new StringEntity(json.toString());
			request.addHeader("content-type", "application/json");
			request.addHeader("Authorization", reqMap.get("Authorization"));
			//request.addHeader("x-amz-date", reqMap.get("x-amz-date"));
			//request.addHeader("host", "runtime.lex.us-east-1.amazonaws.com");
			//request.addHeader("basic", "3otyfpriyli");
			request.addHeader("charset", "utf-8");
			//// request.addHeader("content-length", "2700");
			request.setEntity(params);
			System.out.println("===>" + httpClient.execute(request));
			System.out.println("ddd");

		} catch (Exception e) {
		}
		

		
		return responseObject;
	}
}
	
	

