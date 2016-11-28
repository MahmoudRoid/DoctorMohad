package ir.unicodes.doctor.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import ir.unicodes.doctor.R;

public class ClubActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView txtToolbar, txtNews;
    private Typeface San;
    private ImageView ivMailBox;
    private LinearLayout layMathces,layConsult,layChat,layPoll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club);
        define();
        onClickListener();
    }// end onCreate()
    /*onClick listener*/
    private void onClickListener() {

        layMathces.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        layConsult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO : open telegram channel
            }
        });

        layChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ClubActivity.this, ChatActivity.class);
                startActivity(intent);
            }
        });

        layPoll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ClubActivity.this, PollActivity.class);
                startActivity(intent);
            }
        });

        ivMailBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }// end onClickListener()
    /*set typeface findViewByIds set toolbar text and navigation*/
    private void define() {
        San = Typeface.createFromAsset(getAssets(), "fonts/SansLight.ttf");
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        txtToolbar = (TextView) findViewById(R.id.txtToolbar);
        txtNews = (TextView) findViewById(R.id.txtNews_appbar);
        ivMailBox = (ImageView) findViewById(R.id.ivMailBox);
        layMathces = (LinearLayout) findViewById(R.id.layMatches);
        layConsult = (LinearLayout) findViewById(R.id.layConsult);
        layChat = (LinearLayout) findViewById(R.id.layChat);
        layPoll = (LinearLayout) findViewById(R.id.layPoll);
        txtToolbar.setTypeface(San);
        txtNews.setTypeface(San);
        txtToolbar.setText("درباره ما");
        // TODO set txtNews from database
        txtNews.setText("");
    }// end define()

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_empty, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch(id){
            case android.R.id.home:
                finish();
                break;

            default:
                break;

        }
        return false;
    }

}// end class
