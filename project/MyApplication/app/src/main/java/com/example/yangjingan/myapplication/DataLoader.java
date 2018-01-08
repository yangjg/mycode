package com.example.yangjingan.myapplication;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.os.AsyncTask;

public abstract class DataLoader<T> extends AsyncTaskLoader<T> {
    private T mData;
    public DataLoader(Context context) {
        super(context);
    }

    @Override
    public abstract T loadInBackground();
    
    @Override
    protected void onStartLoading() {
        if (mData != null) {
            deliverResult(mData);
        }
        if (takeContentChanged() || mData == null) {
            forceLoad();
        }
    }

    /**
     * Must be called from the UI thread
     */
    @Override
    protected void onStopLoading() {
        // Attempt to cancel the current load task if possible.
        cancelLoad();
    }

    @Override
    public void onCanceled(T data) {
    }

    @Override
    public void cancelLoadInBackground() {

    }

    @Override
    protected void onReset() {
        super.onReset();
        
        // Ensure the loader is stopped
        onStopLoading();

        mData = null;
    }
}