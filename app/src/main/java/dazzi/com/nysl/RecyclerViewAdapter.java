package dazzi.com.nysl;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import dazzi.com.nysl.models.Events;

import java.text.ParseException;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";

    private ArrayList<Events> events = new ArrayList<>();
    private Context mContext;


    public RecyclerViewAdapter(Context mContext, ArrayList<Events> events) {
        this.events = events;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_events, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {
        Log.d(TAG, "onBindViewHolder: called");
//        Glide.with(mContext)
//                .asBitmap()
//                .load(mHomeImgNames.get(position))
//                .into(viewHolder.homeTeamImg);


        try {

            Log.i(TAG, "Game Time: "+events.get(position).date);
            Log.i(TAG, "Game Time: "+events.get(position).getGameTime());

            viewHolder.homeTeamName.setText(events.get(position).teamHomeName);
            viewHolder.visitorTeamName.setText(events.get(position).teamVisitorName);
            viewHolder.gameTime.setText(events.get(position).getGameTime());
            viewHolder.gameMap.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(events.get(position).getGoogleMapLink()));
                    mContext.startActivity(i);
                }
            });


        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        Log.i(TAG, "getItemCount: " + events.size());
        return events.size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView homeTeamImg;
        CircleImageView visitorTeamImg;
        TextView homeTeamName;
        TextView visitorTeamName;
        TextView gameTime;
        ImageButton gameMap;

        RelativeLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            homeTeamImg = itemView.findViewById(R.id.home_img);
            visitorTeamImg = itemView.findViewById(R.id.visitor_img);

            homeTeamName = itemView.findViewById(R.id.home_txt);
            visitorTeamName = itemView.findViewById(R.id.visitor_txt);

            gameTime = itemView.findViewById(R.id.gameTime);
            gameMap = itemView.findViewById(R.id.gameMap);

            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }
}
