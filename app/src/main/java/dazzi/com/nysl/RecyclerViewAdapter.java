package dazzi.com.nysl;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import dazzi.com.nysl.models.Events;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>  {

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
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        Log.d(TAG, "onBindViewHolder: called");
//        Glide.with(mContext)
//                .asBitmap()
//                .load(mHomeImgNames.get(position))
//                .into(viewHolder.homeTeamImg);
        Log.i(TAG, "onBindViewHolder: "+events.get(position).teamHomeName);

        viewHolder.homeTeamName.setText(events.get(position).teamHomeName);
        viewHolder.visitorTeamName.setText(events.get(position).teamVisitorName);
    }

    @Override
    public int getItemCount() {
        Log.i(TAG, "getItemCount: "+events.size());
        return events.size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        CircleImageView homeTeamImg;
        CircleImageView visitorTeamImg;
        TextView homeTeamName;
        TextView visitorTeamName;
        RelativeLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            homeTeamImg = itemView.findViewById(R.id.home_img);
            visitorTeamImg = itemView.findViewById(R.id.visitor_img);

            homeTeamName = itemView.findViewById(R.id.home_txt);
            visitorTeamName = itemView.findViewById(R.id.visitor_txt);

            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }
}
