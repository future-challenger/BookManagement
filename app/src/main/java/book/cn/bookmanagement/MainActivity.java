package book.cn.bookmanagement;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import book.cn.bookmanagement.Utils.Constants;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (loginedIn() == false) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(i);
                }
            }, 300);
        }
    }

    boolean loginedIn(){
        SharedPreferences sharedPreferences = getSharedPreferences(Constants.PREF_NAME, MODE_PRIVATE);
        String userId = sharedPreferences.getString(Constants.SETTINGS_USER_ID, "");
       return !userId.equals("");
    }
}
