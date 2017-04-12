package com.android.dongnan.taskmaster;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.dongnan.taskmaster.data.DataItem;

import java.util.ArrayList;

/**
 * Created by dream on 16/7/25.
 *
 * Good Luck
 */

public class MainFragment extends Fragment {
    private static final String TAG = MainFragment.class.getSimpleName();

    private LayoutInflater mLayoutInflater;
    private DataAdapter mDataAdapter;
    private ArrayList<DataItem> mDataItems;

    public MainFragment() {
        mDataItems = new ArrayList<>();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mLayoutInflater = inflater;
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ListView listView = (ListView)view.findViewById(R.id.listView);
        mDataAdapter = new DataAdapter();
        listView.setAdapter(mDataAdapter);
        updateData();
        return view;
    }

    public void updateData() {
        Log.v(TAG, "updateData");
        for(int i=0; i<10; i++) {
            mDataItems.add(DataItem.getInstance().setTitle("Title-" + i)
                .setContent("Content-" + i));
        }
        mDataAdapter.notifyDataSetChanged();
    }

    private class DataAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mDataItems.size();
        }

        @Override
        public Object getItem(int position) {
            return mDataItems.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = mLayoutInflater.inflate(R.layout.data_view, parent, false);
                holder = new ViewHolder();
                holder.title = (TextView)convertView.findViewById(R.id.title);
                holder.content = (TextView)convertView.findViewById(R.id.content);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder)convertView.getTag();
            }

            DataItem item = mDataItems.get(position);
            holder.title.setText(item.getTitle());
            holder.content.setText(item.getContent());
            return convertView;
        }
    }

    private static class ViewHolder {
        TextView title;
        TextView content;
    }
}
