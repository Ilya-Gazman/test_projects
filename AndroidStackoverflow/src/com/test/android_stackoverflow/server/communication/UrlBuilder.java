package com.test.android_stackoverflow.server.communication;

/**
 * Simple url builder. Currently do not support editing parameters
 *
 */
public class UrlBuilder {
	
	public final String baseUrl;
	private StringBuilder builder = new StringBuilder();
	private boolean firstParamaterAdded = false;
	
	public UrlBuilder(String baseUrl) {
		this.baseUrl = baseUrl;
	}
	
	public void addParameter(String key, String value){
		if(firstParamaterAdded){
			builder.append("&");
		}
		else{
			firstParamaterAdded = true;
		}
		builder.append(key);
		builder.append("=");
		builder.append(value);
	}
	
	@Override
	public String toString() {
		return baseUrl + (firstParamaterAdded ? "?" + builder : "");
	}
}
