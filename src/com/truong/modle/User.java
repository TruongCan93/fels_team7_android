package com.truong.modle;

import java.util.ArrayList;

public class User {
	public static int id;
	public static String email;
	public static String fullname;
	public static String password;

	public static String lessn_id;
	public static String category_id;
	public static String Action;

	public static ArrayList<ResutlModle> result = new ArrayList<ResutlModle>();
	public static ArrayList<String> data;

	String mEmail;
	String mPassword;

	public String getmEmail() {
		return mEmail;
	}

	public void setmEmail(String mEmail) {
		this.mEmail = mEmail;
	}

	public String getmPassword() {
		return mPassword;
	}

	public void setmPassword(String mPassword) {
		this.mPassword = mPassword;
	}

	public String getCheck() {
		return check;
	}

	public void setCheck(String check) {
		this.check = check;
	}

	public User(String mEmail, String mPassword, String check) {
		super();
		this.mEmail = mEmail;
		this.mPassword = mPassword;
		this.check = check;
	}

	String check;

}
