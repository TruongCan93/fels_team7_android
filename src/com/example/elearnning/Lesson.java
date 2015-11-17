package com.example.elearnning;

import java.util.ArrayList;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.framgia.truong.handler.CreateLesson;
import com.framgia.truong.handler.LoadLessonById;
import com.framgia.truong.handler.LoadQuestion;
import com.framgia.truong.handler.UpdateLessonResult;
import com.framgia.truong.handler.UpdateQuestion;
import com.truong.adapter.MyAdapter;
import com.truong.adapter.ResultListAdapter;
import com.truong.modle.Answer;
import com.truong.modle.Category;
import com.truong.modle.ResutlModle;
import com.truong.modle.User;

public class Lesson extends Activity {
	ListView list;
	Button previous, previous_le, start_le;
	public static ViewFlipper viewFlipper;
	TextView title_lesson;
	ImageView img;
	TextView toast;

	public static RelativeLayout layout13, layout14;
	public static Button A, B, C, D;
	public static TextView an, number;
	public static Dialog dialog;

	MyAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lessonslide);
		// Tham chieu den cac view tuong ung
		list = (ListView) findViewById(R.id.list_category);
		previous = (Button) findViewById(R.id.previous_ca);
		previous_le = (Button) findViewById(R.id.previous_ca_le);
		viewFlipper = (ViewFlipper) findViewById(R.id.vfMainFliper2);
		title_lesson = (TextView) findViewById(R.id.title_le);
		img = (ImageView) findViewById(R.id.img_le);
		toast = (TextView) findViewById(R.id.lesson_toast);
		layout13 = (RelativeLayout) findViewById(R.id.layout13);
		layout14 = (RelativeLayout) findViewById(R.id.layout14);
		start_le = (Button) findViewById(R.id.start_le);
		A = (Button) findViewById(R.id.A_le);
		B = (Button) findViewById(R.id.B_le);
		C = (Button) findViewById(R.id.C_le);
		D = (Button) findViewById(R.id.D_le);
		an = (TextView) findViewById(R.id.answer_le);
		number = (TextView) findViewById(R.id.socau);

		// Set su kien
		previous.setOnClickListener(backHome());
		previous_le.setOnClickListener(backCategory());
		start_le.setOnClickListener(startLesson());
		list.setOnItemLongClickListener(goLesson());
		A.setOnClickListener(answerQuestion());
		B.setOnClickListener(answerQuestion());
		C.setOnClickListener(answerQuestion());
		D.setOnClickListener(answerQuestion());

		// Khoi tao cac bien
		adapter = new MyAdapter(this, Category.data);
		if (com.truong.modle.Lesson.scale_toast == null)
			com.truong.modle.Lesson.scale_toast = AnimationUtils.loadAnimation(
					Lesson.this, R.anim.scale_toast);
		toast.setAnimation(com.truong.modle.Lesson.scale_toast);

		list.setAdapter(adapter);

	}

	public View.OnClickListener answerQuestion() {
		return new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (com.truong.modle.Lesson.index <= 19) {
					for (Answer part : Answer.data) {
						Button a = (Button) v;
						if (part.getContent().equals(a.getText().toString())) {
							if (part.getCorrect().equals("1")) {
								com.truong.modle.Lesson.result++;
								User.result.add(new ResutlModle(an.getText()
										.toString(), part.getContent(), true));
							} else
								User.result.add(new ResutlModle(an.getText()
										.toString(), part.getContent(), false));
							com.truong.modle.Lesson.perent_id = part.getId();
							A.setEnabled(false);
							B.setEnabled(false);
							C.setEnabled(false);
							D.setEnabled(false);
							new UpdateQuestion().execute(Category.functions);
						}
					}
				} else {
					createDialog(Lesson.this);
					new UpdateLessonResult().execute(Category.functions);
					new LoadLessonById().execute(Category.functions);
				}
			}
		};
	}

	public static void createDialog(Context context) {
		if (dialog != null)
			dialog.cancel();
		ResultListAdapter resultAdapter;
		dialog = new Dialog(context, R.style.Transparent);
		dialog.setContentView(R.layout.result);
		dialog.getWindow().getAttributes().windowAnimations = R.style.Dialogdown;
		ImageView bt = (ImageView) dialog.findViewById(R.id.buttonPanel);
		TextView txt = (TextView) dialog.findViewById(R.id.result_le);
		ListView list = (ListView) dialog.findViewById(R.id.result_list);
		txt.setText(com.truong.modle.Lesson.result + "/20");
		resultAdapter = new ResultListAdapter(User.result, context);
		list.setAdapter(resultAdapter);
		Typeface tf = Typeface.createFromAsset(context.getAssets(),
				"fonts/Molot.otf");
		txt.setTypeface(tf);
		bt.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.d("Truong", "dfdfd");
				dialog.cancel();
			}
		});
		dialog.show();
	}

	public View.OnClickListener startLesson() {
		return new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (User.result == null)
					User.result = new ArrayList<ResutlModle>();
				User.result.clear();
				com.truong.modle.Lesson.index = 0;
				com.truong.modle.Lesson.result = 0;
				new LoadQuestion().execute(Category.functions);
				layout13.setVisibility(View.GONE);
			}
		};
	}

	public AdapterView.OnItemLongClickListener goLesson() {
		return new AdapterView.OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				ImageView a = (ImageView) view.findViewById(R.id.img_ca);
				title_lesson.setText(Category.data.get(position).getName()
						+ " Lesson");
				img.setImageDrawable(a.getDrawable());
				com.truong.modle.Lesson.Category_id = Category.data.get(
						position).getId()
						+ "";
				com.truong.modle.Lesson.User_id = User.id + "";
				new CreateLesson().execute(Category.functions);
				return false;
			}
		};
	}

	public View.OnClickListener backCategory() {
		return new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				viewFlipper.setInAnimation(com.truong.modle.Lesson.go_prev_in);
				viewFlipper
						.setOutAnimation(com.truong.modle.Lesson.go_prev_out);
				layout13.setVisibility(View.GONE);
				layout14.setVisibility(View.GONE);
				an.setText("");
				number.setText("");
				viewFlipper.showPrevious();
			}
		};
	}

	public View.OnClickListener backHome() {
		return new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		};
	}
}
