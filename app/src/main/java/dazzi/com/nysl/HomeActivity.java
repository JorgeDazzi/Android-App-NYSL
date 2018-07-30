package dazzi.com.nysl;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import dazzi.com.nysl.Schedule.LoadSchedule;
import dazzi.com.nysl.menu.MenuBar;


public class HomeActivity extends AppCompatActivity {
    private static final String TAG = "HomeActivity";
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //Menu
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout,R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        MenuBar menu = new MenuBar(
                this,
                (NavigationView) findViewById(R.id.menuBar),
                new Intent(HomeActivity.this, MainActivity.class)
        );

        menu.setEmail(user.getEmail());
        menu.setUserDisplayName(user.getDisplayName());

        Log.i(TAG, "onCreate: "+ user.getUid());
        Log.i(TAG, "onCreate: "+ user.getEmail());
        Log.i(TAG, "onCreate: "+ user.getDisplayName());


        //Init Schedule
        LoadSchedule schedule = new LoadSchedule(this, (RecyclerView) findViewById(R.id.events_view));
        schedule.getConnApi();

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);

    }
}
