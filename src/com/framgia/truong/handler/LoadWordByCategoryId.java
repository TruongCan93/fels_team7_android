package com.framgia.truong.handler;

import org.json.JSONArray;

import com.example.elearnning.WordList;
import com.truong.http.UserFunctions;
import com.truong.modle.Category;

import android.os.AsyncTask;

public class LoadWordByCategoryId extends
		AsyncTask<UserFunctions, Void, JSONArray> {
	@Override
	protected JSONArray doInBackground(UserFunctions... params) {
		// TODO Auto-generated method stub
		if (WordList.category_id.equals("0"))
			return params[0].loadWordByCategoryId("0");
		return params[0].loadWordByCategoryId("1");
	}

	@Override
	protected void onPostExecute(JSONArray result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
	}

}
