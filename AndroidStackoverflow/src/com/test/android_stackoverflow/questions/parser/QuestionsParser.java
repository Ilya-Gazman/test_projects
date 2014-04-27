package com.test.android_stackoverflow.questions.parser;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.test.android_stackoverflow.questions.model.QuestionsModel;
import com.test.android_stackoverflow.server.SOParser;


public class QuestionsParser extends SOParser {

	private ArrayList<QuestionData> questions = new ArrayList<QuestionData>();
	@Override
	public void parse(JSONObject jsonObject) throws JSONException{
		JSONArray jsonArray = jsonObject.getJSONArray("items");
		for(int i = 0; i < jsonArray.length(); i++){
			JSONObject questionJsonData = (JSONObject) jsonArray.get(i);
			String title = questionJsonData.getString("title");
			QuestionData questionData = new QuestionData();
			questionData.title = title;
			questions.add(questionData);
		}
		

	}
	
	@Override
	public void dispatch() {
		QuestionsModel.instance.setQuestions(questions);
	}

}
