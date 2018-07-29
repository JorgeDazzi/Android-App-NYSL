package dazzi.com.nysl.Schedule;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

import dazzi.com.nysl.FutAPI;
import dazzi.com.nysl.R;
import dazzi.com.nysl.RecyclerViewAdapter;
import dazzi.com.nysl.models.Events;
import dazzi.com.nysl.models.Match;
import dazzi.com.nysl.models.Matches;
import dazzi.com.nysl.utils.Date;
import dazzi.com.nysl.utils.Settings;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoadSchedule {

    private static final String TAG = "LoadSchedule";

    ArrayList<Events> events = new ArrayList<>();
    Context context;
    RecyclerView events_view;

    public LoadSchedule(Context context, RecyclerView events_view) {
        this.context = context;
        this.events_view = events_view;
    }

    public void getConnApi(){
        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl(Settings.API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        FutAPI service = retrofit.create(FutAPI.class);
        Call<Matches> requestMatches = service.ListMatches(Settings.API_KEY, new Date().getDatesParam(), new Date().getDatesParam(6));

        requestMatches.enqueue(new Callback<Matches>() {
            @Override
            public void onResponse(Call<Matches> call, Response<Matches> response) {

                if(!response.isSuccessful()){



                }else{
                    //Sucesso!
                    Matches games = response.body();

                    Events e;

                    for(Match g : games.getMatches()) {

                        e = new Events();
                        e.date = g.getUtcDate();
                        e.teamHomeName = g.getHomeTeam().getName();
                        e.teamVisitorName = g.getAwayTeam().getName();

                        events.add(e);
                    }


                    initRecyclerView();
                }
            }

            @Override
            public void onFailure(Call<Matches> call, Throwable t) {
                Log.i(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: init RecyclerView");

        RecyclerView recyclerView = events_view;
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(context, events);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
    }
}
