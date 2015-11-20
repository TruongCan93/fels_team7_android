package com.adapter;

import java.net.ContentHandler;
import java.util.ArrayList;

import com.example.e_learnning.R;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class WordAdapter extends BaseAdapter {
	ArrayList<String> data;
	Context context;

	public WordAdapter(ArrayList<String> data, Context context) {
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
			convertView = inflater.inflate(R.layout.word_item, parent, false);
			hodle = new ViewHodle();
			hodle.content = (TextView) convertView
					.findViewById(R.id.waa_content);
			convertView.setTag(hodle);
		}
		hodle.content.setText(data.get(position));
		return convertView;
	}

	private class ViewHodle {
		TextView content;
	}

}
