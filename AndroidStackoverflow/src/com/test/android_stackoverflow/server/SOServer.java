package com.test.android_stackoverflow.server;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;
import org.json.JSONObject;

import com.test.android_stackoverflow.factory.Factory;
import com.test.android_stackoverflow.server.communication.HTTPCommunication;
import com.test.android_stackoverflow.server.communication.UrlBuilder;


public class SOServer {
	
	private HTTPCommunication communication = Factory.INSTANCE.getHTTPCommunication();
	
	public void send(UrlBuilder urlBuilder, SOParser parser){
		try {
			String data = communication.post(urlBuilder.toString());
			try {
				JSONObject jsonObject = new JSONObject(data);
				parser.parse(jsonObject);
				parser.dispatch();
			} catch (JSONException e) {
				// TODO parsing errors handling goes here
				e.printStackTrace();
			}
		} catch (ClientProtocolException e) {
			// TODO communication handling goes here
			e.printStackTrace();
		} catch (IOException e) {
			// TODO communication handling goes here
			e.printStackTrace();
		}
	}
	
	
}
