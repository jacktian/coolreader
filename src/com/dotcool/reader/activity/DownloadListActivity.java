package com.dotcool.reader.activity;

import java.util.ArrayList;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuItem;
import com.dotcool.reader.LNReaderApplication;
import com.dotcool.R;
import com.dotcool.reader.UIHelper;
import com.dotcool.reader.adapter.DownloadListAdapter;
import com.dotcool.reader.model.DownloadModel;

public class DownloadListActivity extends SherlockActivity {
	private static final String TAG = DownloadListActivity.class.toString();
	ArrayList<DownloadModel> downloadList;
	ListView downloadListView;
	DownloadListAdapter adapter;
	private static DownloadListActivity instance;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		UIHelper.SetTheme(this, R.layout.activity_download_list);
		UIHelper.SetActionBarDisplayHomeAsUp(this, true);
		// setContentView(R.layout.activity_download_list);
		instance = this;
		downloadListView = (ListView) findViewById(R.id.download_list);
		downloadList = LNReaderApplication.getInstance().getDownloadList();
		updateContent();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			finish();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public static DownloadListActivity getInstance() {
		return instance;
	}

	public int getDownloadListCount() {
		return downloadList.size();
	}

	public void updateContent() {
		try {
			int resourceId = R.layout.download_list_item;
			adapter = new DownloadListAdapter(this, resourceId, downloadList);
			downloadListView.setAdapter(adapter);
		} catch (Exception e) {
			Log.e(TAG, e.getMessage(), e);
			Toast.makeText(this, getResources().getString(R.string.error_update) + ": " + e.getMessage(), Toast.LENGTH_LONG).show();
		}
	}
}
