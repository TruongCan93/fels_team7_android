package com.example.e_learnning;

import java.util.ArrayList;

import truong.shafre.SharePreUtils;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.adapter.LessonAdapter;
import com.truong.handle.LoadCategory;
import com.truong.handle.LoadLesson;
import com.truong.modle.Category;
import com.truong.modle.Lesson;
import com.truong.modle.User;

public class Home extends Activity {
	Button btn_word_list, btn_lesson, previous_up;
	TextView fullname, email,tt_home, learned_ho;
	ViewFlipper viewFlipper;
	ImageView edit_ho;
	ListView list;

	public static LessonAdapter adapter;
	public static Dialog dialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mainslide);

		// Tham chieu den cac view tuong unng
		btn_lesson = (Button) findViewById(R.id.lesson_ho);
		btn_word_list = (Button) findViewById(R.id.word_list_ho);
		viewFlipper = (ViewFlipper) findViewById(R.id.vfMainFliper1);
		edit_ho = (ImageView) findViewById(R.id.edit_ho);
		previous_up = (Button) findViewById(R.id.previous_up);
		fullname = (TextView) findViewById(R.id.full_name_ho);
		email = (TextView) findViewById(R.id.email_ho);
		learned_ho=(TextView)findViewById(R.id.learned_ho);
		list = (ListView) findViewById(R.id.list_le);
		tt_home=(TextView)findViewById(R.id.tt_home_tt);

		// Set du lieu
		fullname.setText(User.fullname);
		email.setText(User.email);
		
		tt_home.setTypeface(MainActivity.tf5);
		email.setTypeface(MainActivity.tf6);
		fullname.setTypeface(MainActivity.tf6);
		learned_ho.setTypeface(MainActivity.tf6);

		Lesson.data = new ArrayList<Lesson>();
		adapter = new LessonAdapter(Lesson.data, this);
		list.setAdapter(adapter);

		// Load file Animation
		Lesson a = new Lesson();
		Lesson.go_next_in = AnimationUtils.loadAnimation(this,
				R.anim.go_next_in);
		Lesson.go_next_out = AnimationUtils.loadAnimation(this,
				R.anim.go_next_out);
		Lesson.go_prev_in = AnimationUtils.loadAnimation(this,
				R.anim.go_prev_in);
		Lesson.go_prev_out = AnimationUtils.loadAnimation(this,
				R.anim.go_prev_out);

		// Set su kien cho cac view
		btn_lesson.setOnTouchListener(onRedBtnLessonClickListener());
		btn_lesson.setOnClickListener(goCategory());
		edit_ho.setOnClickListener(goUpdateProfiles());
		previous_up.setOnClickListener(backHome());
		btn_word_list.setOnTouchListener(onRedBtnLessonClickListener());
		btn_word_list.setOnClickListener(goWordList());
		list.setOnItemClickListener(itemClick());
		btn_word_list.setOnClickListener(goWordList());

		// Load lesson
		new LoadLesson().execute(Category.functions);

	}
	
	public View.OnClickListener goWordList() {
		return new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				User.Action = "WordList";
				Category.activity=Home.this;
				new LoadCategory().execute(Category.functions);
			}
		};
	}

	public AdapterView.OnItemClickListener itemClick() {
		return new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				showPopupOfLessonItem(view, position);
			}
		};
	}

	public static void createDialog(Context context, int position) {
		if (dialog != null)
			dialog.cancel();

		int index = position;
		Lesson lesson = Lesson.data.get(index);

		dialog = new Dialog(context, R.style.Transparent);
		dialog.setContentView(R.layout.result);
		dialog.getWindow().getAttributes().windowAnimations = R.style.Dialogdown;
		ImageView bt = (ImageView) dialog.findViewById(R.id.tb_delete);
		TextView id = (TextView) dialog.findViewById(R.id.tb_id);
		TextView result = (TextView) dialog.findViewById(R.id.tb_result);
		TextView category = (TextView) dialog.findViewById(R.id.tb_category_id);
		TextView created_at = (TextView) dialog
				.findViewById(R.id.tb_created_at);

		id.setText("Id: " + lesson.getId());
		result.setText("Result: "+lesson.getLesson_result());
		if (lesson.getCategory_id().equals("1"))
			category.setText("Category: Basic 500 words");
		else if (lesson.getCategory_id().equals("3"))
			category.setText("Category: Family");
		else if (lesson.getCategory_id().equals("4"))
			category.setText("Category: Job");
		created_at.setText("Created_at: " + lesson.getCreated_at());

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

	public void showPopupOfLessonItem(View v, int position) {
		final int index = position;
		PopupMenu popup = new PopupMenu(Home.this, v);
		popup.getMenuInflater().inflate(R.menu.list_lesson_menu,
				popup.getMenu());
		popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

			@Override
			public boolean onMenuItemClick(MenuItem item) {
				// TODO Auto-generated method stub
				switch (item.getItemId()) {
				case R.id.show_lesson_detail: {
					createDialog(Home.this, index);
					break;
				}
				case R.id.delete_lesson: {

					break;
				}
				}
				return false;
			}
		});
		popup.show();
	}

	public void showPopupMenu(View v) {
		PopupMenu popup = new PopupMenu(Home.this, v);
		popup.getMenuInflater().inflate(R.menu.sms_menu, popup.getMenu());
		popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

			@Override
			public boolean onMenuItemClick(MenuItem item) {
				// TODO Auto-generated method stub
				switch (item.getItemId()) {
				case R.id.edit_profile: {
					viewFlipper.setInAnimation(Lesson.go_next_in);
					viewFlipper.setOutAnimation(Lesson.go_next_out);
					viewFlipper.showNext();
					break;
				}
				case R.id.sign_out: {

					SharePreUtils.setString(Home.this, SharePreUtils.EMAIL, "");
					SharePreUtils.setString(Home.this, SharePreUtils.PASSWORD,
							"");
					SharePreUtils.setString(Home.this, SharePreUtils.FULL_NAME,
							"");
					SharePreUtils.setString(Home.this, SharePreUtils.USER_ID,
							"");

					Intent intent = new Intent(Home.this, MainActivity.class);
					startActivity(intent);
					finish();
					break;
				}
				}
				return false;
			}
		});
		popup.show();
	}

	public View.OnClickListener goCategory() {
		return new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				User.Action = "Lesson";
				Category.activity = Home.this;
				new LoadCategory().execute(Category.functions);
			}
		};
	}

	public View.OnClickListener backHome() {
		return new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				viewFlipper.setInAnimation(Lesson.go_prev_in);
				viewFlipper.setOutAnimation(Lesson.go_prev_out);
				viewFlipper.showPrevious();
			}
		};
	}

	public View.OnClickListener goUpdateProfiles() {
		return new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showPopupMenu(v);
			}
		};
	}

	public View.OnTouchListener onRedBtnLessonClickListener() {
		return new View.OnTouchListener() {
			int xdau, xcuoi;
			int ydau, ycuoi;

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				Button chan = (Button) v;
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					xdau = (int) event.getX();
					ydau = (int) event.getY();
					chan.setTextColor(Color.parseColor("#dbE42217"));
				}
				if (event.getAction() == MotionEvent.ACTION_MOVE) {
					xcuoi = (int) event.getX();
					ycuoi = (int) event.getY();
					if (Math.abs(xdau - xcuoi) > 70
							|| Math.abs(ydau - ycuoi) > 30)
						chan.setTextColor(Color.parseColor("#ffffff"));
					else
						chan.setTextColor(Color.parseColor("#dbE42217"));
				}
				if (event.getAction() == MotionEvent.ACTION_UP)
					chan.setTextColor(Color.parseColor("#ffffff"));
				return false;
			}
		};
	}

}
