package ir.unicodes.doctor.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import ir.unicodes.doctor.R;
import ir.unicodes.doctor.classes.Variables;
import ir.unicodes.doctor.fragment.ListDataFragment;

public class MultimediaActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView txtToolbar;
    private Typeface San;
    private ImageView ivImageGallery,ivVideoGallery;
    private FragmentManager fragmentManager;
    private String FACTION = "";
    private boolean isFolder = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multimedia);
        define();

        ivImageGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FACTION = Variables.getImages;
                //setFragment();
                Intent intent = new Intent(MultimediaActivity.this, MultimediaFoldersActivity.class);
                intent.putExtra("FACTION",FACTION);
                startActivity(intent);
            }
        });

        ivVideoGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FACTION = Variables.getVideos;
                //setFragment();
                Intent intent = new Intent(MultimediaActivity.this, MultimediaFoldersActivity.class);
                intent.putExtra("FACTION",FACTION);
                startActivity(intent);
            }
        });

    }// end onCreate()
    /*set typeface findViewByIds set toolbar text and navigation*/
    private void define() {
        San = Typeface.createFromAsset(getAssets(), "fonts/SansLight.ttf");
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        txtToolbar = (TextView) findViewById(R.id.txtToolbar_appbar);
        txtToolbar.setTypeface(San);
        txtToolbar.setText("گالری مولتی مدیا");

        ivImageGallery = (ImageView) findViewById(R.id.ivImageGallery);
        ivVideoGallery = (ImageView) findViewById(R.id.ivViGalleryGallery);

        fragmentManager = getSupportFragmentManager();

    }// end define()
    /*set fragment*/
    protected void setFragment() {

        ListDataFragment myFragment = new ListDataFragment();
        Bundle bundle = new Bundle();
        bundle.putString("FACTION", FACTION);
        bundle.putBoolean("KIND", isFolder);
        myFragment.setArguments(bundle);

        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.add(R.id.frame, myFragment);
        ft.commit();

    }// end setFragment()

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
