package dazzi.com.nysl.FirebaseDatabase;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;

import dazzi.com.nysl.models.firebase.Msg;

public class AppendChatMsg {

    private DatabaseReference msgsRef;
    private String ref = "/Chats/General/msgs/";
    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    public AppendChatMsg() {

        this.msgsRef = FirebaseDatabase.getInstance().getReference(this.ref);

    }

    public void appendMsg(String msg){

        HashMap<String, Object> fireMsg = new HashMap<>();

        fireMsg.put("displayName", user.getDisplayName());
        fireMsg.put("msg", msg.trim());
        fireMsg.put("timestamp", new Date().getTime());
        fireMsg.put("user", user.getEmail());

        String key = this.msgsRef.push().getKey();

        this.msgsRef.child(key).setValue(fireMsg);
    }
}
