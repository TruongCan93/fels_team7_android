package com.truong.handle;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.adapter.WordAdapter;
import com.example.e_learnning.Home;
import com.example.e_learnning.WordList;
import com.truong.http.UserFunctions;
import com.truong.modle.Lesson;
import com.truong.modle.User;

import android.os.AsyncTask;
import android.util.Log;

public class LoadWord extends AsyncTask<UserFunctions, Void, JSONArray> {

	@Override
	protected JSONArray doInBackground(UserFunctions... params) {
		// TODO Auto-generated method stub
		return params[0].getListWord();
	}

	@Override
	protected void onPostExecute(JSONArray result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		if (User.data == null)
			User.data = new ArrayList<String>();
		User.data.clear();
		if (result != null) {
			try {
				JSONObject object = null;
				for (int i = 0; i < result.length(); i++) {
					object = result.getJSONObject(i);
					User.data.add(object.getString("content"));
				}
				WordList.wordAdapter.notifyDataSetChanged();

			} catch (Exception e) {
				// TODO: handle exception
				Log.d("Truong", "Loi load Lesson " + e);
			}
		}
	}

}
