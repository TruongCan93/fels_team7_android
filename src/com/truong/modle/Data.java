package com.truong.modle;

import java.util.ArrayList;
import java.util.jar.Attributes.Name;

public class Data {
	public static ArrayList<Data> data;

	public String content;
	public ArrayList<Answer> answer;

	public Data(String content, ArrayList<Answer> answer) {
		super();
		this.content = content;
		this.answer = answer;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public ArrayList<Answer> getAnswer() {
		return answer;
	}

	public void setAnswer(ArrayList<Answer> answer) {
		this.answer = answer;
	}

}
