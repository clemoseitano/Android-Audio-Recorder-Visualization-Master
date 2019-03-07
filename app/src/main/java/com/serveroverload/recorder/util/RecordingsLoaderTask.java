package com.serveroverload.recorder.util;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.ListView;

import com.serveroverload.recorder.R;
import com.serveroverload.recorder.ui.HomeActivity;

import java.util.ArrayList;

/**
 * The Class ImageLoaderTask.
 */
public class RecordingsLoaderTask extends
        AsyncTask<String, Void, ArrayList<String>> {

    ListView recordingsListView;
    private Context context;
    private SwipeRefreshLayout swipeLayout;

    /**
     * Instantiates a new image loader task.
     *
     * @param contacts the contacts
     * @param position the position
     */
    public RecordingsLoaderTask(SwipeRefreshLayout swipeLayout,
                                ListView listView, Context context) {

        this.swipeLayout = swipeLayout;
        this.recordingsListView = listView;
        this.context = context;
    }

    /*
     * (non-Javadoc)
     *
     * @see android.os.AsyncTask#onPostExecute(java.lang.Object)
     */
    @Override
    protected void onPostExecute(final ArrayList<String> listOfRecordings) {

        if (null != recordingsListView) {

            RecordingsListArrayAdapter arrayAdapter2 = new RecordingsListArrayAdapter(
                    context, R.layout.swipe_layout_recording_list_item,
                    ((HomeActivity) context).getRecordings());

            recordingsListView.setAdapter(arrayAdapter2);

            recordingsListView.setFastScrollEnabled(true);

        }

        if (null != swipeLayout) {
            swipeLayout.setRefreshing(false);
        }

        super.onPostExecute(listOfRecordings);
    }

    @Override
    protected ArrayList<String> doInBackground(String... loadTask) {

        ((HomeActivity) context).setRecordings(Helper.getHelperInstance()
                .getAllRecordings());

        return ((HomeActivity) context).getRecordings();
    }
}