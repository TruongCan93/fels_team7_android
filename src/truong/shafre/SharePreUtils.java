package truong.shafre;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SharePreUtils {

	public static String PRE_FILE_NAME = "ELearnningInformations";

	public static String USER_ID = "userid";
	public static String EMAIL = "email";
	public static String PASSWORD="password";
	public static String FULL_NAME = "fullname";

	public static void setString(Context context, String key, String value) {
		SharedPreferences pref = context.getSharedPreferences(PRE_FILE_NAME,
				Context.MODE_APPEND);
		Editor editor = pref.edit();
		editor.putString(key, value);
		editor.commit();
	}

	public static String getString(Context context, String key, String defValue) {
		String ret = defValue;
		SharedPreferences pref = context.getSharedPreferences(PRE_FILE_NAME,
				Context.MODE_APPEND);
		ret = pref.getString(key, defValue);
		return ret;
	}
}
