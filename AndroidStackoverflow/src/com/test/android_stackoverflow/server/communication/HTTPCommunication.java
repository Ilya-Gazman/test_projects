package com.test.android_stackoverflow.server.communication;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.http.client.ClientProtocolException;

public class HTTPCommunication {
	private byte[] buffer = new byte[81920];

	public String post(String url) throws ClientProtocolException, IOException {
		InputStream stream = null;
		try {
			URL urlConnection = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) urlConnection
					.openConnection();
			stream = connection.getInputStream();
			int byteData = stream.read();
			int counter = 0;
			while (byteData != -1) {
				buffer[counter] = (byte) byteData;
				counter++;
				byteData = stream.read();
			}

			return new String(buffer, 0, counter, "UTF8");
			
		} finally {
			if (stream != null) {
				stream.close();
			}
		}
	}
}
