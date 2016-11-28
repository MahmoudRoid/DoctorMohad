package ir.unicodes.doctor.activity;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import cn.pedant.SweetAlert.SweetAlertDialog;
import ir.unicodes.doctor.R;
import ir.unicodes.doctor.classes.Variables;

public class PollActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView txtToolbar, txtQuestion;
    private Typeface San;
    private Button btnSendVote;
    private SweetAlertDialog pDialog;
    private RadioGroup rGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poll);
        define();
        ArcLoader();

        btnSendVote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO : send choosen answer to server
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
        txtQuestion = (TextView) findViewById(R.id.txtQuestion);
        btnSendVote = (Button) findViewById(R.id.btnConfirm);
        btnSendVote.setTypeface(San);
        txtToolbar.setTypeface(San);
        txtQuestion.setTypeface(San);
        txtToolbar.setText("نظرسنجی");
        // TODO set txtQuestion from server


    }// end define()
    /*prepare ProgressBar dialog*/
    private void ArcLoader(){
        String tmp = "";
        pDialog= new SweetAlertDialog(PollActivity.this, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setTitleText(tmp);
        pDialog.setCancelable(true);
    }// end ArcLoader()
    /*make dynamic RadioButton*/
    private void cRadioButton(String text){
        Log.i(Variables.Tag,"radioButton text: "+text);
        RadioButton rb = new RadioButton(PollActivity.this);
        rb.setTypeface(San);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.setMargins(0, 10, 0, 10);
        lp.gravity= Gravity.RIGHT | Gravity.CENTER_VERTICAL;
//        rb.setPadding(10, 10, 10, 10);
        rb.setLineSpacing(30,1);
        rb.setTypeface(San);
        rb.setText(text);
        rGroup.addView(rb,lp);
    }// end cRadioButton()
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
