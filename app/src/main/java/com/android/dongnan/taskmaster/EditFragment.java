package com.android.dongnan.taskmaster;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.android.dongnan.taskmaster.data.DataConstants;
import com.android.dongnan.taskmaster.data.DataManager;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by dream on 2017/5/1.
 */

public class EditFragment extends Fragment {

    private static final String TAG = EditFragment.class.getSimpleName();

    private DataConstants.StoryData mData;
    private View mContentView;

    public EditFragment() {
        Bundle bundle = getArguments();
        int id = bundle.getInt(DataConstants.StoryTable.ID);
        ArrayList<DataConstants.StoryData> datas = DataManager.queryStory(DataConstants.StoryTable.ID + "=?",
                new String[] {String.valueOf(id)});
        if (datas != null && !datas.isEmpty()) {
            mData = datas.get(0);
        } else {
            mData = DataConstants.StoryData.builder();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        mContentView = inflater.inflate(R.layout.fragment_edit, container, false);
        updateView();
        return mContentView;
    }

    @Override
    public void onDestroyView() {
        saveData();
        mContentView = null;
        mData = null;
        super.onDestroyView();
    }

    private void saveData() {
        mData.title = getEditText(R.id.title);
        mData.comment = getEditText(R.id.comment);
        mData.content = getEditText(R.id.content);
        mData.result = getEditText(R.id.result);
        mData.address = getEditText(R.id.address);
        mData.time = new Date().getTime();

        if (mData.id != -1) {
            DataManager.updateStory(mData);
        } else {
            DataManager.updateStory(mData);
        }
    }

    public void updateView() {
        updateEditText(R.id.title, mData.title);
        updateEditText(R.id.comment, mData.comment);
        updateEditText(R.id.content, mData.content);
        updateEditText(R.id.result, mData.result);
        updateEditText(R.id.address, mData.address);
        updateEditText(R.id.time, mData.time + "");
    }

    private void updateEditText(int id, String text) {
        EditText editText = (EditText)mContentView.findViewById(id);
        editText.setText(text);
    }

    private String getEditText(int id) {
        EditText editText = (EditText)mContentView.findViewById(id);
        return editText.getText().toString();
    }
}
