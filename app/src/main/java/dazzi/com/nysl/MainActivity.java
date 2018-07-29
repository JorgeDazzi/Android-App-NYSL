package dazzi.com.nysl;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import dazzi.com.nysl.Schedule.LoadSchedule;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        LoadSchedule schedule = new LoadSchedule(this, (RecyclerView) findViewById(R.id.events_view));
        schedule.getConnApi();

    }


}
