package com.truong.modle;

public class ResutlModle {
	String content;
	String answer;
	boolean check;

	public boolean isCheck() {
		return check;
	}

	public void setCheck(boolean check) {
		this.check = check;
	}

	public ResutlModle(String content, String answer, boolean check) {
		super();
		this.content = content;
		this.answer = answer;
		this.check = check;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

}
