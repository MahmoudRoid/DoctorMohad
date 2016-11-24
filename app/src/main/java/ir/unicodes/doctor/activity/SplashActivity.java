package ir.unicodes.doctor.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ir.unicodes.doctor.R;
import ir.unicodes.doctor.database.database;

public class SplashActivity extends AppCompatActivity {

    public static Runnable runnable = null;
    public Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        database db = new database(this);
        db.useable();

        handler = new Handler();
        runnable = new Runnable() {
            public void run() {
                startActivity(new Intent(SplashActivity.this,MainActivity.class));
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                finish();
            }
        };

        handler.postDelayed(runnable, 200);

    }

}
