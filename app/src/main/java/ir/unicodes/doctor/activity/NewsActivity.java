package ir.unicodes.doctor.activity;

import android.graphics.Typeface;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import ir.unicodes.doctor.R;
import ir.unicodes.doctor.classes.Variables;
import ir.unicodes.doctor.fragment.ListDataFragment;
import ir.unicodes.doctor.fragment.MainFragment;

public class NewsActivity extends AppCompatActivity {

    private Typeface San;
    private Toolbar toolbar;
    private TextView txtToolbar;
    private FragmentManager fragmentManager;
    private String FACTION = Variables.getNews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        define();
        /*set fragment*/
        setFragment();
    }// end onCreate()

    private void define() {
        San = Typeface.createFromAsset(getAssets(), "fonts/SansLight.ttf");
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        txtToolbar = (TextView) findViewById(R.id.txtToolbar_appbar);
        txtToolbar.setTypeface(San);
        txtToolbar.setText("اخبار");
        fragmentManager = getSupportFragmentManager();
    }// end define()

    protected void setFragment() {

        ListDataFragment myFragment = new ListDataFragment();
        Bundle bundle = new Bundle();
        bundle.putString("FACTION", FACTION);
        bundle.putString("param2", "بای بای");
        myFragment.setArguments(bundle);

        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.add(R.id.frame, myFragment);
        ft.commit();

    }// end setFragment()

}// end class
