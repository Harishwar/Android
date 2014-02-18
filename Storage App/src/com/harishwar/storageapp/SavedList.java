package com.harishwar.storageapp;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.harishwar.storageapp.R;

public class SavedList extends BaseAdapter {
	private List<String> StringList = new ArrayList<String>();
	private Context context;

	public SavedList(List<String> StringList,
			Context applicationContext) {
		this.StringList = StringList;
		this.context = applicationContext;
	}

	public void setStringList(List<String> StringList) {
		this.StringList = StringList;
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		return StringList.size();
	}

	@Override
	public Object getItem(int position) {
		return StringList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ContactsViewHolder contactsViewHolder;
		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.activity_saved_list, null);
			contactsViewHolder = new ContactsViewHolder();
			contactsViewHolder.name = (TextView) convertView
					.findViewById(R.id.item);
		} else {
			contactsViewHolder = (ContactsViewHolder) convertView.getTag();
		}
		String item = StringList.get(position);

		contactsViewHolder.name.setText(item);

		convertView.setTag(R.id.id, "");
		convertView.setTag(contactsViewHolder);
		return convertView;
	}

	private class ContactsViewHolder {
		TextView name;
	}
}
