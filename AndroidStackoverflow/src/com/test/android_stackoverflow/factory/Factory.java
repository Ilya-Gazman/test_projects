package com.test.android_stackoverflow.factory;

import com.test.android_stackoverflow.server.communication.HTTPCommunication;

public final class Factory {
	
	public static final Factory INSTANCE = new Factory();
	
	public HTTPCommunication getHTTPCommunication(){
		return new HTTPCommunication();
	}
}
