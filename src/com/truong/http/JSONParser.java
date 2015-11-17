package com.truong.http;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.net.Uri;
import android.util.Log;

public class JSONParser {
	String charset = "UTF-8";
	HttpURLConnection conn;
	BufferedWriter writer;
	StringBuilder result = new StringBuilder();
	URL urlObj;
	JSONObject jObj = null;
	JSONArray jArr = null;
	String paramsString;

	public JSONParser() {
	}

	public void doConnect(String url, String method, Uri.Builder builder) {
		result.setLength(0);
		// convert the NameValuePairs to a Stream which can be written to the
		// HttpURLConnection
		String query = builder.build().getEncodedQuery();

		if (method.equals("POST")) {
			// request method is POST
			try {
				urlObj = new URL(url);

				conn = (HttpURLConnection) urlObj.openConnection();

				conn.setDoOutput(true);

				conn.setRequestMethod("POST");

				conn.setRequestProperty("Accept-Charset", charset);

				conn.setReadTimeout(10000);
				conn.setConnectTimeout(60000);

				OutputStream os = conn.getOutputStream();

				writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
				writer.write(query);
				writer.flush();
				writer.close();
				os.close();

				conn.connect();

			} catch (IOException e) {
				e.printStackTrace();
				Log.d("Truong", "loi 1" + e);
			}
		} else if (method.equals("GET")) {
			// request method is GET

			try {
				urlObj = new URL(url);

				conn = (HttpURLConnection) urlObj.openConnection();

				conn.setDoOutput(false);

				conn.setRequestMethod("GET");

				conn.setRequestProperty("Accept-Charset", charset);

				conn.setConnectTimeout(15000);

				conn.connect();

			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		try {
			// Receive the response from the server
			InputStream in = new BufferedInputStream(conn.getInputStream());
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(in));

			String line;
			while ((line = reader.readLine()) != null) {
				result.append(line);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		conn.disconnect();
	}

	public JSONObject makeHttpRequest(String url, String method,
			Uri.Builder builder) {

		doConnect(url, method, builder);
		Log.d("Truong", "Gia tri result "+result.toString());

		// try parse the string to a JSON object
		try {
			jObj = new JSONObject(result.toString());
		} catch (JSONException e) {
			Log.e("Truong", "Error parsing data " + e.toString());
		}

		// return JSON Object
		return jObj;
	}

	public JSONArray getJsonArrayFromUrl(String url, String method,
			Uri.Builder builder) {

		doConnect(url, method, builder);
		Log.d("Truong", "Gia tri result "+result.toString());
		// try parse the string to a JSON object
		try {
			jArr = new JSONArray(result.toString());
		} catch (JSONException e) {
			Log.e("Truong", "Error parsing data " + e.toString());
		}

		// return JSON Object
		return jArr;
	}
}
