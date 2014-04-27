package com.test.android_stackoverflow.server;

import org.json.JSONException;
import org.json.JSONObject;

public abstract class SOParser {
	
	public abstract void parse(JSONObject jsonObject) throws JSONException;
	public abstract void dispatch();
}
