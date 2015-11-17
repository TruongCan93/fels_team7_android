package com.truong.adapter;

import java.util.ArrayList;

import com.example.elearnning.R;
import com.truong.modle.ResutlModle;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ResultListAdapter extends BaseAdapter {
	ArrayList<ResutlModle> data;
	Context context;

	public ResultListAdapter(ArrayList<ResutlModle> data, Context context) {
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
		if (convertView != null)
			hodle = (ViewHodle) convertView.getTag();
		else {
			convertView = inflater.inflate(R.layout.result_item, parent, false);
			hodle = new ViewHodle();
			hodle.word = (TextView) convertView.findViewById(R.id.result_word);
			hodle.answer = (TextView) convertView
					.findViewById(R.id.result_answer);
			hodle.image = (ImageView) convertView
					.findViewById(R.id.check_result);
			convertView.setTag(hodle);
		}
		hodle.word.setText(data.get(position).getContent());
		hodle.answer.setText(data.get(position).getAnswer());
		if (data.get(position).isCheck())
			hodle.image.setImageDrawable(this.context.getResources()
					.getDrawable(R.drawable.true_img));
		else
			hodle.image.setImageDrawable(this.context.getResources()
					.getDrawable(R.drawable.fale_img));
		return convertView;
	}

	private class ViewHodle {
		TextView word;
		TextView answer;
		ImageView image;
	}

}
