package com.example.e_learnning;

import java.util.ArrayList;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.adapter.SpinnerAdapter;
import com.adapter.WordAdapter;
import com.truong.handle.LoadWord;
import com.truong.modle.Category;
import com.truong.modle.Lesson;
import com.truong.modle.User;

public class WordList extends Activity {
	Spinner spin;
	ArrayList<String> dataSpinner;
	ListView list;
	RadioGroup radioGroup;
	Button filter, previous;

	public static String category_id;
	public static String learn;
	public static WordAdapter wordAdapter;

	SpinnerAdapter adapter;
	int position_spinner;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.wordlist);
		spin = (Spinner) findViewById(R.id.spinner);
		list = (ListView) findViewById(R.id.wl_list);
		radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
		filter = (Button) findViewById(R.id.filter);
		dataSpinner = new ArrayList<String>();
		User.data=new ArrayList<String>();
		previous=(Button)findViewById(R.id.previous_wl);
		wordAdapter = new WordAdapter(User.data, this);
		for (Category part : Category.data) {
			dataSpinner.add(part.getName());
		}

		radioGroup.clearCheck();
		adapter = new SpinnerAdapter(dataSpinner, this);
		// Thiết lập adapter cho Spinner
		spin.setAdapter(adapter);
		list.setAdapter(wordAdapter);
		new LoadWord().execute(Category.functions);

		radioGroup.setOnCheckedChangeListener(radioCheck());
		spin.setOnItemSelectedListener(spinnerSelected());
		filter.setOnClickListener(onSubmit());
		previous.setOnClickListener(close());
	}
	
	public View.OnClickListener close(){
		return new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Lesson.action="";
				finish();
			}
		};
	}

	public View.OnClickListener onSubmit() {
		return new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				RadioButton rb = (RadioButton) findViewById(radioGroup
						.getCheckedRadioButtonId());
				category_id = Category.data.get(position_spinner).getId() + "";

				if (rb == null) {
					Lesson.action = "Filter not learn";
					new LoadWord().execute(Category.functions);
				} else {
					Lesson.action = "Filter has learn";
					if (rb.getText().toString().equals("not learned"))
						learn = "not+learned";
					else
						learn = rb.getText().toString();
					new LoadWord().execute(Category.functions);
				}

			}
		};
	}

	public RadioGroup.OnCheckedChangeListener radioCheck() {
		return new RadioGroup.OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				RadioButton rb = (RadioButton) group.findViewById(checkedId);
				if (null != rb && checkedId > -1) {
					// CODE HERE...
				}
			}
		};
	}

	public AdapterView.OnItemSelectedListener spinnerSelected() {
		return new AdapterView.OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				TextView text = (TextView) ((RelativeLayout) parent
						.getChildAt(0)).findViewById(R.id.text_spinner_item);
				text.setTextColor(Color.RED);
				text.setTypeface(Typeface.DEFAULT_BOLD);
				((RelativeLayout) parent.getChildAt(0))
						.setBackgroundColor(Color.WHITE);
				position_spinner = position;
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub

			}
		};
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		learn = "";
		category_id = "";
	}
}
