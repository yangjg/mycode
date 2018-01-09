package com.example.yjq.androidlearn.myview;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ActionMenuView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.yjq.androidlearn.R;
import com.example.yjq.androidlearn.Views;

import org.w3c.dom.Text;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created by yjq on 2016/4/18.
 */
public class RetrieveDataActivity extends Activity {

    TextView result;
    Executor executor;
    ListView listView;
    ContactsAdapter adapter;
    private String[] columns = {ContactsContract.Contacts._ID, ContactsContract.Contacts.DISPLAY_NAME};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.retrieve_activity);
        result = Views.findViewById(this, R.id.result);
        listView = Views.findViewById(this, R.id.contacts);

      //  listView.setAdapter();
        adapter = new ContactsAdapter();
        //adapter.n

        executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                adapter.setCursor(getQueryData());
                RetrieveDataActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        listView.setAdapter(adapter);
                    }
                });

            }
        });
        result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.notifyDataSetChange();
            }
        });
    }

    private Cursor getQueryData() {
        StringBuilder sb = new StringBuilder();
        ContentResolver resolver = getContentResolver();
        Cursor cursor = resolver.query(ContactsContract.Contacts.CONTENT_URI, columns, null, null, null);
        return cursor;
    }

    public static void close(Cursor cursor) {
        if (cursor != null) {
            cursor.close();
        }
    }

    class ContactsAdapter implements ListAdapter {
        DataSetObserver observer;
        Cursor cursor;
        int idIndex;
        int displayIndex;
      //  int phone_number;
      //  int contact_id;

        public ContactsAdapter() {

        }

        public void release(){
            close(cursor);
        }

        public void setCursor(Cursor cursor) {
            if (cursor != this.cursor) {
                close(this.cursor);
                this.cursor = cursor;
                if (null != cursor) {
                    idIndex = cursor.getColumnIndex(ContactsContract.Contacts._ID);
                    displayIndex = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);

                }
            }

        }

        @Override
        public boolean areAllItemsEnabled() {
            return true;
        }

        @Override
        public boolean isEnabled(int position) {
            return true;
        }

        @Override
        public void registerDataSetObserver(DataSetObserver observer) {
           this.observer = observer;
        }

        @Override
        public void unregisterDataSetObserver(DataSetObserver observer) {
              this.observer =null;
        }

        public void notifyDataSetChange(){
            if(null!=observer){
                observer.onChanged();
            }
        }

        @Override
        public int getCount() {
            return cursor.getCount();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return createView(position,convertView,parent);
        }

        @Override
        public int getItemViewType(int position) {
            return 0;
        }

        @Override
        public int getViewTypeCount() {
            return 1;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        private View createView(int position, View convertView, ViewGroup parent) {

            View layout;
            if (null != convertView) {
                layout = (LinearLayout) convertView;
            } else {
                layout = getLayoutInflater().inflate(R.layout.contacts_item, parent, false);
            }
            TextView idIndex = Views.findViewById(layout, R.id.idIndex);
            TextView display = Views.findViewById(layout, R.id.displayName);
            LinearLayout.LayoutParams params = ( LinearLayout.LayoutParams)idIndex.getLayoutParams();
           // LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
         /*   params.gravity = Gravity.CENTER_HORIZONTAL;
            idIndex.setGravity(Gravity.CENTER);
            display.setGravity(Gravity.CENTER);*/
            idIndex.setLayoutParams(params);
            display.setLayoutParams(params);
            makeTextView(idIndex, display, position);

            return layout;
        }

        private void makeTextView(TextView idView, TextView displayView, int position) {
            String id = "none";
            String name = "empty";
            String phoneNumber="000";
            if (null != cursor && cursor.moveToPosition(position)) {
                id = String.valueOf(cursor.getInt(idIndex));
                name = cursor.getString(displayIndex);
                ContentResolver resolver = getContentResolver();
                Cursor cursor= resolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=" + id, null, null);
                while (cursor.moveToNext()){
                //   int  phone_number = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                  // int  contact_id =cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.CONTACT_ID);
                    int numberIndex= cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                    phoneNumber =cursor.getString(numberIndex);
                    close(cursor);
                    break;
                }
            }
            idView.setText(phoneNumber);
            displayView.setText(name);
        }


    }
}
