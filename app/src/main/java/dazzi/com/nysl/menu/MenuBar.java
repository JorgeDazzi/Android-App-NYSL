package dazzi.com.nysl.menu;


import android.content.Context;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import dazzi.com.nysl.HomeActivity;
import dazzi.com.nysl.MainActivity;
import dazzi.com.nysl.R;

public class MenuBar {

    private Context context;
    private NavigationView navigationView;
    private View headerView;
    private TextView email;
    private TextView userDisplayName;
    private ImageView headerIconLogout;
    private Intent intent;



//    public void setHeaderIconLogout(ImageView headerIconLogout) {
//        this.headerIconLogout = headerIconLogout;
//    }

    public MenuBar(Context context, NavigationView navigationView, Intent intent) {
        this.intent = intent;
        this.context = context;
        this.navigationView = navigationView;
        this.headerView = this.navigationView.getHeaderView(0);

        //fields
        this.email = (TextView) headerView.findViewById(R.id.userEmail);
        this.userDisplayName = (TextView) headerView.findViewById(R.id.userDisplayName);
        this.headerIconLogout = (ImageView) headerView.findViewById(R.id.headerIconLogout);

        this.setLogoutListener();


    }

    public void setEmail(String email) {
        this.email.setText(email);
    }

    public void setUserDisplayName(String userDisplayName) {
        this.userDisplayName.setText(userDisplayName);
    }


    private void setLogoutListener(){
        this.headerIconLogout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                context.startActivity(intent);
            }
        });
    }


}
