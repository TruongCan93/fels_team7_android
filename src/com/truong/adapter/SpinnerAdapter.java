package com.truong.adapter;

import java.util.ArrayList;

import com.example.elearnning.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class SpinnerAdapter extends BaseAdapter {
	ArrayList<String> data;
	Context context;

	public SpinnerAdapter(ArrayList<String> data, Context context) {
		super();
		this.data = data;
		this.context = context;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		LayoutInflater inflater = (LayoutInflater) this.context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		ViewHodle hodle;
		if (convertView != null) {
			hodle = (ViewHodle) convertView.getTag();
		} else {
			convertView = inflater
					.inflate(R.layout.spinner_item, parent, false);
			hodle = new ViewHodle();
			hodle.title = (TextView) convertView
					.findViewById(R.id.text_spinner_item);
			convertView.setTag(hodle);
		}
		hodle.title.setText(data.get(position));
		return convertView;
	}

	private class ViewHodle {
		TextView title;
	}

}
