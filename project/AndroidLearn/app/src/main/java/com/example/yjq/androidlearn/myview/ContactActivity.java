package com.example.yjq.androidlearn.myview;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.CursorAdapter;
import android.widget.Filter;
import android.widget.FilterQueryProvider;
import android.widget.Filterable;
import android.widget.TextView;

import com.example.yjq.androidlearn.R;
import com.example.yjq.androidlearn.Views;

/**
 * Created by yjq on 2016/4/19.
 */
public class ContactActivity extends Activity {

    private String[] columns = new String[]{ContactsContract.Contacts._ID, ContactsContract.Contacts.DISPLAY_NAME};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_activiy);
        ContentResolver resolver = getContentResolver();
        Cursor cursor = resolver.query(ContactsContract.Contacts.CONTENT_URI, columns, null, null, null);
        final ContactListAdapter adapter = new ContactListAdapter(ContactActivity.this, cursor);
        AutoCompleteTextView textView = Views.findViewById(ContactActivity.this, R.id.edit);
        textView.setAdapter(adapter);


        //new TestThread().start();
    }


    class TestThread extends Thread {
        @Override
        public void run() {
            // super.run();
            ContentResolver resolver = getContentResolver();
            Cursor cursor = resolver.query(ContactsContract.Contacts.CONTENT_URI, columns, null, null, null);
            final ContactListAdapter adapter = new ContactListAdapter(ContactActivity.this, cursor);
            ContactActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    AutoCompleteTextView textView = Views.findViewById(ContactActivity.this, R.id.edit);
                    textView.setAdapter(adapter);

                }
            });

        }
    }

    class ContactListAdapter extends CursorAdapter implements Filterable {

        private ContentResolver resolver;
        private String[] columns = new String[]{ContactsContract.Contacts._ID, ContactsContract.Contacts.DISPLAY_NAME};

        public ContactListAdapter(Context context, Cursor c) {
            this(context, c, false);
        }

        public ContactListAdapter(Context context, Cursor c, boolean autoRequery) {
            super(context, c, autoRequery);
            init(context);
        }

        public ContactListAdapter(Context context, Cursor c, int flags) {
            super(context, c, flags);
            init(context);
        }

        private void init(Context context) {
            resolver = context.getContentResolver();
        }

        @Override
        public View newView(Context context, Cursor cursor, ViewGroup parent) {
            LayoutInflater inflater = LayoutInflater.from(context);
            TextView view = (TextView) inflater.inflate(android.R.layout.simple_dropdown_item_1line, parent, false);
            //   view.setText(cursor.getString(1));
            return view;
            // return null;
        }

        @Override
        public void bindView(View view, Context context, Cursor cursor) {
            ((TextView) view).setText(cursor.getString(1));
        }

        @Override
        public Filter getFilter() {
            return super.getFilter();
        }

        @Override
        public CharSequence convertToString(Cursor cursor) {
            return cursor.getString(1);
        }

        @Override
        public Cursor runQueryOnBackgroundThread(CharSequence constraint) {
            FilterQueryProvider filter = getFilterQueryProvider();
            if (null != filter) {
                return filter.runQuery(constraint);
            }
            Uri uri = Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_FILTER_URI, Uri.encode(constraint.toString()));
            return resolver.query(uri, columns, null, null, null);
        }
    }
}
