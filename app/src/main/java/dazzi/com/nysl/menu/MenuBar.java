package dazzi.com.nysl.menu;


import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import dazzi.com.nysl.ChatActivity;
import dazzi.com.nysl.HomeActivity;
import dazzi.com.nysl.MainActivity;
import dazzi.com.nysl.R;

public class MenuBar {
    private static final String TAG = "MenuBar";

    private Activity currentAct;
    private BottomNavigationView menu;





    public MenuBar(Activity currentAct, BottomNavigationView menu) {

        this.currentAct = currentAct;
        this.menu = menu;
        this.mappingMenu();

    }

    private void mappingMenu(){
        this.menu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Log.i(TAG, "onNavigationItemSelected: received click here");

                switch ( item.getItemId() ){

                    case R.id.btn_menu_home:
                        currentAct.startActivity(new Intent(currentAct.getBaseContext(), HomeActivity.class));
                        return true;

                    case R.id.btn_menu_schedule:
                        return true;


                    case R.id.btn_menu_chat:
                        currentAct.startActivity(new Intent(currentAct.getBaseContext(), ChatActivity.class));
                        return true;


                    default:
                        return false;


                }

            }
        });
    }



}
