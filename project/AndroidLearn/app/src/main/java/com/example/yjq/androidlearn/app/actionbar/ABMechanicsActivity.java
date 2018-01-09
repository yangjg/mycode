package com.example.yjq.androidlearn.app.actionbar;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Toast;

import com.example.yjq.androidlearn.R;

/**
 * Created by yjq on 2016/6/14.
 */
public class ABMechanicsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(Window.FEATURE_ACTION_BAR);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("Normal Item0");
        MenuItem actionmenu =  menu.add("ActionBar");
        actionmenu.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        actionmenu.setIcon(android.R.drawable.ic_menu_share);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Toast.makeText(this, "Selected Item: " + item.getTitle(), Toast.LENGTH_SHORT).show();
        return true;
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
