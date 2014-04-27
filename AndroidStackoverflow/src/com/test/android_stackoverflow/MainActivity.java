package com.test.android_stackoverflow;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.test.android_stackoverflow.questions.parser.QuestionsParser;
import com.test.android_stackoverflow.questions.view.QuestionsAdapter;
import com.test.android_stackoverflow.server.SOServer;
import com.test.android_stackoverflow.server.communication.UrlBuilder;

public class MainActivity extends Activity {

	private ListView questions;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		questions = (ListView) findViewById(R.id.questions_list);
		questions.setAdapter(new QuestionsAdapter());
		questions.setOnItemClickListener(questionClickHandler );
		callServer();
	}
	
	private OnItemClickListener questionClickHandler = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//			QuestionsAdapter adapter = (QuestionsAdapter)questions.getAdapter();
//			QuestionData item = adapter.getItem(position);
			Toast.makeText(MainActivity.this, "Question number " + position + "been selected", Toast.LENGTH_LONG).show();
		}
		
		
		
//		public void onItemClick(android.widget.AdapterView<?> arg0, View arg1, int arg2, long arg3) {};
//		
//			int index = (Integer) v.getTag();
//			Toast.makeText(MainActivity.this, "Question number " + index + " selected", Toast.LENGTH_SHORT).show();
//		}
	};

	private void callServer() {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				SOServer soServer = new SOServer();
				UrlBuilder urlBuilder = new UrlBuilder("http://api.stackexchange.com/questions/");
				urlBuilder.addParameter("order", "desc");
				urlBuilder.addParameter("sort", "votes");
				urlBuilder.addParameter("filter", "default");
				urlBuilder.addParameter("site", "stackoverflow");
				
				soServer.send(urlBuilder, new QuestionsParser());
			}
		}).start();
	}
}
