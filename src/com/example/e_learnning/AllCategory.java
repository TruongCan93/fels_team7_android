package com.example.e_learnning;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
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

import com.adapter.MyAdapter;
import com.adapter.ResultListAdapter;
import com.truong.handle.CreateLesson;
import com.truong.modle.Answer;
import com.truong.modle.Category;
import com.truong.modle.Data;
import com.truong.modle.Lesson;
import com.truong.modle.ResutlModle;
import com.truong.modle.User;

public class AllCategory extends Activity {
	ListView list;
	Button previous, previous_le, start_le;
	public static ViewFlipper viewFlipper;
	TextView title_lesson;
	ImageView img;
	TextView toast, tt_ca;

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
		tt_ca=(TextView)findViewById(R.id.tt_ca_tt);
		
		tt_ca.setTypeface(MainActivity.tf5);
		title_lesson.setTypeface(MainActivity.tf5);
		A.setTypeface(MainActivity.tf6);
		B.setTypeface(MainActivity.tf6);
		C.setTypeface(MainActivity.tf6);
		D.setTypeface(MainActivity.tf6);
		an.setTypeface(MainActivity.tf7);

		// Set cac su kien
		previous.setOnClickListener(backHome());
		previous_le.setOnClickListener(backCategory());
		list.setOnItemClickListener(onClickItem());
		start_le.setOnClickListener(startLesson());
		A.setOnClickListener(answerQuestion());
		B.setOnClickListener(answerQuestion());
		C.setOnClickListener(answerQuestion());
		D.setOnClickListener(answerQuestion());

		adapter = new MyAdapter(this, Category.data);
		if (Lesson.scale_toast == null)
			Lesson.scale_toast = AnimationUtils.loadAnimation(AllCategory.this,
					R.anim.scale_toast);
		toast.setAnimation(com.truong.modle.Lesson.scale_toast);

		list.setAdapter(adapter);
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

	public boolean check = false;

	public View.OnClickListener answerQuestion() {
		return new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (Lesson.index <= 4 && !check) {
					ArrayList<Answer> array = Data.data.get(Lesson.index)
							.getAnswer();

					for (Answer part : array) {
						Button a = (Button) v;

						if (part.getContent().equals(a.getText().toString())) {
							if (part.isCorrect()) {
								Lesson.result++;
							}
							User.result.add(new ResutlModle(an.getText()
									.toString(), a.getText().toString(), part
									.isCorrect()));

							A.setEnabled(false);
							B.setEnabled(false);
							C.setEnabled(false);
							D.setEnabled(false);
						}
					}
				}

				if (Lesson.index < 4) {
					Lesson.index++;
					showQuestion(Lesson.index);
				} else {
					createDialog(AllCategory.this, title_lesson.getText()
							.toString());
					A.setEnabled(true);
					B.setEnabled(true);
					C.setEnabled(true);
					D.setEnabled(true);
					check = true;
				}
			}
		};
	}

	public static void createDialog(Context context, String title) {
		if (dialog != null)
			dialog.cancel();
		ResultListAdapter resultAdapter;

		dialog = new Dialog(context, R.style.Transparent);
		dialog.setContentView(R.layout.lesson_result);
		dialog.getWindow().getAttributes().windowAnimations = R.style.Dialogdown;
		ListView list = (ListView) dialog.findViewById(R.id.result_list);
		ImageView bt = (ImageView) dialog.findViewById(R.id.buttonPanel);
		TextView tt_rl = (TextView) dialog.findViewById(R.id.result_le);
		TextView id = (TextView) dialog.findViewById(R.id.tb_id_le);
		TextView result = (TextView) dialog.findViewById(R.id.tb_result_le);
		TextView category = (TextView) dialog
				.findViewById(R.id.tb_category_id_le);
		TextView created_at = (TextView) dialog
				.findViewById(R.id.tb_created_at_le);

		resultAdapter = new ResultListAdapter(User.result, context);
		list.setAdapter(resultAdapter);
		list.setEnabled(false);
		id.setText("Id: " + Lesson.Lesson_id);
		result.setText("Result: " + Lesson.result);
		category.setText("Category: " + title);
		tt_rl.setText(title);
		created_at.setText("Created_at: " + Lesson.created_at_le);

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
				Lesson.index = 0;
				Lesson.result = 0;
				User.result.clear();
				check = false;

				showQuestion(Lesson.index);
				layout13.setVisibility(View.GONE);
			}
		};
	}

	public void showQuestion(int id) {
		Data object = Data.data.get(id);
		ArrayList<Answer> array = object.getAnswer();

		an.setText(object.getContent());

		A.setText(array.get(0).getContent());
		B.setText(array.get(1).getContent());
		C.setText(array.get(2).getContent());
		D.setText(array.get(3).getContent());
		number.setText((Lesson.index + 1) + "/5");

		A.setEnabled(true);
		B.setEnabled(true);
		C.setEnabled(true);
		D.setEnabled(true);

		AllCategory.layout14.setVisibility(View.VISIBLE);
	}

	public AdapterView.OnItemClickListener onClickItem() {
		return new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				final int index = position;
				final View v = view;
				AlertDialog.Builder builder = new AlertDialog.Builder(
						AllCategory.this);
				builder.setTitle("Are you start lesson: "
						+ Category.data.get(position).getName());
				builder.setCancelable(false);
				builder.setNegativeButton("Cancel",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								// TODO Auto-generated method stub
							}
						});
				builder.setPositiveButton("OK",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								// TODO Auto-generated method stub
								ImageView a = (ImageView) v
										.findViewById(R.id.img_ca);
								title_lesson.setText(Category.data.get(index)
										.getName());
								img.setImageDrawable(a.getDrawable());

								User.category_id = Category.data.get(index)
										.getId() + "";
								new CreateLesson().execute(Category.functions);
							}
						});
				builder.show();
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
