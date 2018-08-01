package dazzi.com.nysl.FirebaseDatabase;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import dazzi.com.nysl.RecyclerViews.RecyclerViewChat;
import dazzi.com.nysl.models.firebase.Msg;


public class ReadChatMsgs {
    private static final String TAG = "ReadChatMsgs";

    private Query msgsRef;
    private String ref = "/Chats/General/msgs/";
    private ArrayList<Msg> msgs = new ArrayList<>();
    private Context context;
    private RecyclerView chat_view;


    public ReadChatMsgs(Context context, RecyclerView chat_view) {
        this.chat_view = chat_view;
        this.context = context;
        this.msgsRef = FirebaseDatabase.getInstance().getReference(this.ref);


        this.msgsRef.addValueEventListener(new  ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                showData(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());

            }
        });
    }

    private void showData(DataSnapshot data){
        Msg m;

        for (DataSnapshot ds : data.getChildren()){
              m = ds.getValue(Msg.class);

            Log.i(TAG, "showData: "+m.getMsg());
            Log.i(TAG, "showData: "+m.getTimestamp());


            this.msgs.add(m);
        }

        initRecyclerViewChat();
    }

    private void initRecyclerViewChat(){

        RecyclerView recyclerView = chat_view;
        RecyclerViewChat adapter = new RecyclerViewChat(context, msgs);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(layoutManager);
    }
}
