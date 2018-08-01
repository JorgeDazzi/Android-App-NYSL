package dazzi.com.nysl;

import android.app.Activity;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import dazzi.com.nysl.FirebaseDatabase.AppendChatMsg;
import dazzi.com.nysl.FirebaseDatabase.ReadChatMsgs;
import dazzi.com.nysl.menu.MenuBar;

public class ChatActivity extends Activity {

    private static final String TAG = "ChatActivity";

    private ImageView btnSendMsg;
    private EditText msgInput;
    private AppendChatMsg fireSend = new AppendChatMsg();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        MenuBar menu = new MenuBar(ChatActivity.this, (BottomNavigationView) findViewById(R.id.mainMenu));


        ReadChatMsgs mainChat = new ReadChatMsgs(this, (RecyclerView) findViewById(R.id.chatBody));

        //activate send btn listener
        setListenerSendMsg();

    }

    private void setListenerSendMsg(){
        btnSendMsg = findViewById(R.id.btn_send_msg);
        msgInput = findViewById(R.id.chatInputMsg);

        btnSendMsg.setOnClickListener(new ImageButton.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg = msgInput.getText().toString().trim();

                Log.i(TAG, "onClick: sending msg (if not ''||empty) = "+msg);

                if( msg.length() > 0 ){
                    fireSend.appendMsg(msg);
                    msgInput.setText("");
                }

            }
        });
    }
}
