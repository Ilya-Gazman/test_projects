package com.test.android_stackoverflow.questions.model;

import java.util.ArrayList;

import com.test.android_stackoverflow.questions.parser.QuestionData;


public class QuestionsModel {
	public static final QuestionsModel instance = new QuestionsModel();
	
	private ArrayList<QuestionData> questions = new ArrayList<QuestionData>();
	public IQuestionsChange callBack = null; // Change to event mechanism in the future

	public ArrayList<QuestionData> getQuestions() {
		return questions;
	}

	public void setQuestions(ArrayList<QuestionData> questions) {
		this.questions = questions;
		if(callBack != null){
			callBack.questionsChangedHandler();
		}
	}
}
