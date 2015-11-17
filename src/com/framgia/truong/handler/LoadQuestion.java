package com.framgia.truong.handler;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

import com.truong.http.UserFunctions;
import com.truong.modle.Answer;
import com.truong.modle.Lesson;

public class LoadQuestion extends AsyncTask<UserFunctions, Void, JSONObject> {
	JSONObject object;
	String contentQuestion;
	JSONArray answers;

	@Override
	protected JSONObject doInBackground(UserFunctions... params) {
		// TODO Auto-generated method stub
		if (Lesson.index <= 19)
			object = params[0].loadQuestion(Lesson.allWord.get(Lesson.index)
					.getWord_id());
		return object;
	}

	@Override
	protected void onPostExecute(JSONObject result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		if (object != null) {
			if (Answer.data == null)
				Answer.data = new ArrayList<Answer>();
			Answer.data.clear();
			try {
				contentQuestion = object.getString("content");
				answers = object.getJSONArray("answers");
				for (int i = 0; i < answers.length(); i++) {
					JSONObject a = answers.getJSONObject(i);
					Answer.data.add(new Answer(a.getString("id"), a
							.getString("content"), a.getString("correct")));
				}
				com.example.elearnning.Lesson.A.setText(Answer.data.get(0)
						.getContent());
				com.example.elearnning.Lesson.B.setText(Answer.data.get(1)
						.getContent());
				com.example.elearnning.Lesson.C.setText(Answer.data.get(2)
						.getContent());
				com.example.elearnning.Lesson.D.setText(Answer.data.get(3)
						.getContent());
				com.example.elearnning.Lesson.number.setText((Lesson.index + 1)
						+ "/20");
				com.example.elearnning.Lesson.an.setText(contentQuestion);
				com.example.elearnning.Lesson.layout14
						.setVisibility(View.VISIBLE);

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Log.d("Truong", "Loi load tung cau hoi " + e);
			}

		}
	}
}
