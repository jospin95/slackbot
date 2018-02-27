package com.slack.bot;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;


//import java.text.ParseException;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;

//import com.accl.util.EndpointConnectUtils;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class slackEventHandler implements RequestHandler<JSONObject, String> {

    @Override
    public String handleRequest (JSONObject request, Context context) {
    	  context.getLogger().log(""+request);
        context.getLogger().log("Input: " + request);
       // System.out.println(request);
      
        String Output=null;
       // String BOT_TOKEN="xoxb-230716597798-66NVFxgS5JXTeF0XEwLDhy1b";
        String BOT_TOKEN=System.getenv("BOT_TOKEN");
        String text=null;
        try
		{
		 Output=JSONValue.toJSONString(request.get("challenge"));
		 System.out.println("input is"+request);
		 String event=JSONValue.toJSONString(request.get("event"));
		 JSONParser parser=new JSONParser();
		 JSONObject eventReq=(JSONObject)parser.parse(event);
		 System.out.println("entered");
		 //System.out.println("challenge is"+Output);
		text= eventReq.get("text").toString();
		String channel= eventReq.get("channel").toString();
		//String bot_id= eventReq.get("bot_id").toString();
		System.out.println("text is new "+text);
		System.out.println("channelId is new "+channel);
		if(eventReq.containsKey("bot_id"))
		{
			System.out.println("bot response");
		}
		else
		{

		String url = "https://slack.com/api/chat.postMessage?token="+BOT_TOKEN+"&channel="+channel+"&text="+URLEncoder.encode(text, "UTF-8");
		String USER_AGENT = "Mozilla/5.0";
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("User-Agent", USER_AGENT);
		int res = con.getResponseCode();
		System.out.println("Response Code : "+res);
		
		}
		}
        catch(Exception e)
		{
			System.out.println(e.toString());
		}
        return Output;
		}
    
		
//		EndpointConnectUtils endPointCon = new EndpointConnectUtils();
//		String slack_url="https://slack.com/api/chat.postMessage";
//		endPointCon.postRequest(obj, slack_url);
		

        // TODO: implement your handler
     //   return null;
    

}


