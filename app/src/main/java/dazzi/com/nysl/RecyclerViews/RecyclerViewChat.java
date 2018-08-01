package dazzi.com.nysl.RecyclerViews;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

import dazzi.com.nysl.R;
import dazzi.com.nysl.models.firebase.Msg;


public class RecyclerViewChat extends RecyclerView.Adapter<RecyclerViewChat.ViewHolder>  {
    private static final String TAG = "Coconut";

    private ArrayList<Msg> msgs;
    private Context mContext;
    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private int ballonCount = 0;

    public RecyclerViewChat(Context mContext, ArrayList<Msg> msgs) {
        this.msgs = msgs;
        this.mContext = mContext;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {


            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_balloon, parent, false);
            ViewHolder holder = new ViewHolder(view);
            this.ballonCount++;
            return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        viewHolder.msg.setText(msgs.get(i).getMsg());
        viewHolder.details.setText(String.format("%s # %d", msgs.get(i).getUser(), msgs.get(i).getTimestamp()));

        viewHolder.msgYou.setText(msgs.get(i).getMsg());
        viewHolder.detailsYou.setText(String.format("%s # %d", msgs.get(i).getUser(), msgs.get(i).getTimestamp()));

        if (!user.getEmail().equals(msgs.get(i).getUser())) {
            viewHolder.chatCard.setVisibility(View.VISIBLE);
            viewHolder.chatCardYou.setVisibility(View.GONE);
        }else {
            viewHolder.chatCardYou.setVisibility(View.VISIBLE);
            viewHolder.chatCard.setVisibility(View.GONE);
        }






    }

    @Override
    public int getItemCount() {
        return this.msgs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        CardView chatCard;
        CardView chatCardYou;
        TextView msg;
        TextView details;
        ConstraintLayout balloon;
        TextView msgYou;
        TextView detailsYou;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            chatCard = itemView.findViewById(R.id.chatCard);
            chatCardYou = itemView.findViewById(R.id.chatCardYou);
            balloon = itemView.findViewById(R.id.chatBalloon);
            msg = itemView.findViewById(R.id.chatMsg);
            details = itemView.findViewById(R.id.msgDetails);
            msgYou = itemView.findViewById(R.id.chatMsgYou);
            detailsYou = itemView.findViewById(R.id.msgDetailsYou);

        }
    }
}
