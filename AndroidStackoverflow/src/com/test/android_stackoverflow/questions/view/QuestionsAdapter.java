package com.test.android_stackoverflow.questions.view;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;

import com.test.android_stackoverflow.R;
import com.test.android_stackoverflow.questions.model.IQuestionsChange;
import com.test.android_stackoverflow.questions.model.QuestionsModel;
import com.test.android_stackoverflow.questions.parser.QuestionData;

public class QuestionsAdapter extends BaseAdapter {
	private Handler handler = new Handler(Looper.getMainLooper());
	private OnCheckedChangeListener checkBoxHandler = new OnCheckedChangeListener() {

		public void onCheckedChanged(CompoundButton view, boolean checked) {
			// With proper event mechanism this logic could be moved to controller
			int index = (Integer) view.getTag();
			QuestionData questionData = getItem(index);
			questionData.checked = checked;
		}
	};

	public QuestionsAdapter() {
		QuestionsModel.instance.callBack = new IQuestionsChange() {

			@Override
			public void questionsChangedHandler() {
				handler.post(new Runnable() {

					@Override
					public void run() {
						notifyDataSetChanged();
					}
				});
			}
		};
	}

	@Override
	public int getCount() {
		return QuestionsModel.instance.getQuestions().size();
	}

	@Override
	public QuestionData getItem(int index) {
		return QuestionsModel.instance.getQuestions().get(index);
	}

	@Override
	public long getItemId(int id) {
		return id;
	}

	@Override
	public View getView(int index, View view, ViewGroup parent) {
		if (view == null) {
			Activity activity = (Activity) parent.getContext();
			view = activity.getLayoutInflater().inflate(
					R.layout.question_layout, parent, false);
		}
		QuestionData data = getItem(index);
		TextView indexText = (TextView) view.findViewById(R.id.index);
		indexText.setText(Integer.toString(index + 1));
		TextView titleText = (TextView) view.findViewById(R.id.title);
		titleText.setText(data.title);
		CheckBox checkBox = (CheckBox) view.findViewById(R.id.checkBox1);
		checkBox.setOnCheckedChangeListener(checkBoxHandler);
		view.setTag(index);
		checkBox.setTag(index);
		checkBox.setChecked(data.checked);

		return view;
	}

}
