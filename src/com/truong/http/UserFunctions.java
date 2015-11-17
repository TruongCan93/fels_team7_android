package com.truong.http;

import org.json.JSONArray;
import org.json.JSONObject;

import android.net.Uri;
import android.util.Log;

public class UserFunctions {
	// private static final String Url_login =
	// "http://192.168.1.40:3030/elearnning/elearnning_api/index.php";
	//
	// private static final String Url_register =
	// "http://192.168.1.64/ServerAndroidDemo/";
	//
	// private static final String Url_load_Category =
	// "http://192.168.1.40:3030/elearnning/elearnning_api/category.php";
	//
	// private static final String Url_create_lesson =
	// "http://192.168.1.40:3030/elearnning/elearnning_api/lesson.php";
	//
	// private static final String Url_create_lesson_word =
	// "http://192.168.1.40:3030/elearnning/elearnning_api/lesson_word.php";
	//
	// private static final String Url_load_question =
	// "http://192.168.1.40:3030/elearnning/elearnning_api/load_question.php";
	//
	// private static final String Url_update_lesson_word =
	// "http://192.168.1.40:3030/elearnning/elearnning_api/update_question.php";

	// ======================================================================================
	private static final String Url_login = "http://192.168.137.1:3030/elearnning/elearnning_api/index.php";

	private static final String Url_register = "http://192.168.137.1/ServerAndroidDemo/";

	private static final String Url_load_Category = "http://192.168.137.1:3030/elearnning/elearnning_api/category.php";

	private static final String Url_create_lesson = "http://192.168.137.1:3030/elearnning/elearnning_api/lesson.php";

	private static final String Url_create_lesson_word = "http://192.168.137.1:3030/elearnning/elearnning_api/lesson_word.php";

	private static final String Url_load_question = "http://192.168.137.1:3030/elearnning/elearnning_api/load_question.php";

	private static final String Url_update_lesson_word = "http://192.168.137.1:3030/elearnning/elearnning_api/update_question.php";

	private static final String Url_update_lesson_result = "http://192.168.137.1:3030/elearnning/elearnning_api/update_result.php";

	private static final String Url_load_lesson = "http://192.168.137.1:3030/elearnning/elearnning_api/load_lesson.php";

	private static final String Url_delete_lesson = "http://192.168.137.1:3030/elearnning/elearnning_api/delete_lesson.php";

	private static final String Url_load_lesson_detail = "http://192.168.137.1:3030/elearnning/elearnning_api/load_lesson_detail.php";

	private static final String Url_load_word = "http://192.168.137.1:3030/elearnning/elearnning_api/word.php";

	JSONParser parser;

	public UserFunctions() {
		parser = new JSONParser();
	}

	public JSONObject loginUser(String email, String password) {
		Uri.Builder builder = new Uri.Builder();
		builder.appendQueryParameter("TAG", "login");
		builder.appendQueryParameter("EMAIL", email);
		builder.appendQueryParameter("PASSWORD", password);
		Log.d("Truong", "Login User completed");
		JSONObject json = parser.makeHttpRequest(Url_login, "POST", builder);
		if (json != null)
			return json;
		return null;
	}

	public JSONObject Register(String name, String email, String password) {
		// List<NameValuePair> valuePair = new ArrayList<NameValuePair>();
		// valuePair.add(new BasicNameValuePair("NAME", name));
		// valuePair.add(new BasicNameValuePair("TAG", "login"));
		// valuePair.add(new BasicNameValuePair("EMAIL", email));
		// valuePair.add(new BasicNameValuePair("PASSWORD", password));
		// JSONObject object = parser.getJsonFormUrl(Url_register, valuePair);
		// return object;
		return null;
	}

	public JSONArray loadCategory() {
		Uri.Builder builder = new Uri.Builder();
		builder.appendQueryParameter("TAG", "category");
		Log.d("Truong", "Load Category completed");
		JSONArray json = parser.getJsonArrayFromUrl(Url_load_Category, "POST",
				builder);
		if (json != null)
			return json;
		return null;
	}

	public JSONObject createLesson(String user_id, String category_id) {
		Uri.Builder builder = new Uri.Builder();
		builder.appendQueryParameter("TAG", "create");
		builder.appendQueryParameter("USER_ID", user_id);
		builder.appendQueryParameter("CATEGORY_ID", category_id);
		Log.d("Truong", "Create Lesson completed");
		JSONObject json = parser.makeHttpRequest(Url_create_lesson, "POST",
				builder);
		if (json != null)
			return json;
		return null;
	}

	public JSONArray createLessonWord(String lesson_id, String category_id) {
		Uri.Builder builder = new Uri.Builder();
		builder.appendQueryParameter("TAG", "create");
		builder.appendQueryParameter("LESSON_ID", lesson_id);
		builder.appendQueryParameter("CATEGORY_ID", category_id);
		Log.d("Truong", "Create Lesson word completed");
		JSONArray json = parser.getJsonArrayFromUrl(Url_create_lesson_word,
				"POST", builder);
		if (json != null)
			return json;
		return null;
	}

	public JSONObject loadQuestion(String id) {
		Uri.Builder builder = new Uri.Builder();
		builder.appendQueryParameter("TAG", "load");
		builder.appendQueryParameter("WORD_ID", id);
		Log.d("Truong", "Create word completed");
		JSONObject json = parser.makeHttpRequest(Url_load_question, "POST",
				builder);
		if (json != null)
			return json;
		return null;
	}

	public JSONObject updateQuestion(String id, String word_answer_id) {
		Uri.Builder builder = new Uri.Builder();
		builder.appendQueryParameter("TAG", "update");
		builder.appendQueryParameter("ID", id);
		builder.appendQueryParameter("WORD_ANSWER_ID", word_answer_id);
		JSONObject json = parser.makeHttpRequest(Url_update_lesson_word,
				"POST", builder);
		if (json != null)
			return json;
		return null;
	}

	public JSONObject updateLessonResult(String value, String lesson_id) {
		Uri.Builder builder = new Uri.Builder();
		builder.appendQueryParameter("TAG", "update");
		builder.appendQueryParameter("ID", lesson_id);
		builder.appendQueryParameter("RESULT", value);
		JSONObject json = parser.makeHttpRequest(Url_update_lesson_result,
				"POST", builder);
		if (json != null)
			return json;
		return null;
	}

	public JSONArray loadLesson(String user_id) {
		Uri.Builder builder = new Uri.Builder();
		builder.appendQueryParameter("TAG", "load");
		builder.appendQueryParameter("USER_ID", user_id);
		JSONArray json = parser.getJsonArrayFromUrl(Url_load_lesson, "POST",
				builder);
		if (json != null)
			return json;
		return null;
	}

	public JSONObject deleteLesson(String lesson_id) {
		Uri.Builder builder = new Uri.Builder();
		builder.appendQueryParameter("TAG", "delete");
		builder.appendQueryParameter("ID", lesson_id);
		JSONObject json = parser.makeHttpRequest(Url_delete_lesson, "POST",
				builder);
		if (json != null)
			return json;
		return null;
	}

	public JSONArray loadLessonDetail(String id) {
		Uri.Builder builder = new Uri.Builder();
		builder.appendQueryParameter("TAG", "load");
		builder.appendQueryParameter("ID", id);
		JSONArray json = parser.getJsonArrayFromUrl(Url_load_lesson_detail,
				"POST", builder);
		if (json != null)
			return json;
		return null;
	}

	public JSONArray loadWordByCategoryId(String category_id) {
		Uri.Builder builder = new Uri.Builder();
		builder.appendQueryParameter("TAG", "load");
		builder.appendQueryParameter("ID", category_id);
		JSONArray json = parser.getJsonArrayFromUrl(Url_load_word, "POST",
				builder);
		if (json != null)
			return json;
		return null;
	}

}
