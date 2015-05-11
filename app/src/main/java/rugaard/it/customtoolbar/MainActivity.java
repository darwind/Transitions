package rugaard.it.customtoolbar;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().getAttributes().flags &= (~WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        DrawerLayout mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mToolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close
        );
        mDrawerLayout.setStatusBarBackground(android.R.color.holo_red_light);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        mDrawerToggle.syncState();

//        Spinner spinner = (Spinner) findViewById(R.id.spinner);
//        spinner.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, new String[]{"Test 1", "Test 2", "Test 3", "Test 4", "Test 5", "Test 6", "Test 7", "Test 8", "Test 9", "Test 10"}));

        ListView listview = (ListView) findViewById(R.id.listview);
        listview.setAdapter(new CustomArrayAdapter(this, R.layout.list_item_layout, new String[]{"Test 1", "Test 2", "Test 3", "Test 4", "Test 5", "Test 6", "Test 7", "Test 8", "Test 9", "Test 10", "Test 1", "Test 2", "Test 3", "Test 4", "Test 5", "Test 6", "Test 7", "Test 8", "Test 9", "Test 10"}));
    }

    private class CustomArrayAdapter extends ArrayAdapter<String> {

        public CustomArrayAdapter(Context context, int resource, String[] objects) {
            super(context, resource, objects);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final ViewHolder viewHolder;

            if (convertView == null) {
                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_layout, null, false);
                viewHolder = new ViewHolder();
                viewHolder.blueBoxView = convertView.findViewById(R.id.blueBoxView);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            final int randomColor = getRandomColor();
            viewHolder.blueBoxView.setBackgroundColor(getResources().getColor(randomColor));
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent transitionIntent = new Intent(MainActivity.this, MainActivity2Activity.class);
                    transitionIntent.putExtra("color", randomColor);
                    Bundle transitionBundle = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this, Pair.create(viewHolder.blueBoxView, "blueBox")).toBundle();
                    startActivity(transitionIntent, transitionBundle);
                }
            });
            return convertView;
        }

        private int getRandomColor() {
            int randomInt = new Random().nextInt(10);
            switch (randomInt) {
                case 0:
                    return android.R.color.darker_gray;
                case 1:
                    return android.R.color.holo_red_dark;
                case 2:
                    return android.R.color.holo_green_dark;
                case 3:
                    return android.R.color.holo_blue_dark;
                case 4:
                    return android.R.color.holo_orange_dark;
                case 5:
                    return android.R.color.holo_orange_light;
                case 6:
                    return android.R.color.holo_purple;
                case 7:
                    return android.R.color.holo_red_light;
                case 8:
                    return android.R.color.black;
                case 9:
                    return android.R.color.white;
                default:
                    return android.R.color.holo_purple;
            }
        }
    }

    static class ViewHolder {
        View blueBoxView;
    }
}