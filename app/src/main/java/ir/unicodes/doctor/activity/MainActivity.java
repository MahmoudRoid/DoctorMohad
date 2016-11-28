package ir.unicodes.doctor.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import ir.unicodes.doctor.Interface.OnFragmentInteractionListener;
import ir.unicodes.doctor.R;
import ir.unicodes.doctor.classes.Variables;
import ir.unicodes.doctor.fragment.AboutUsFragment;
import ir.unicodes.doctor.fragment.LoginFragment;
import ir.unicodes.doctor.fragment.MainFragment;
import ir.unicodes.doctor.fragment.RSSFragment;
import ir.unicodes.doctor.fragment.ServicesFragment;
import ir.unicodes.doctor.fragment.SignUpFragment;


import com.yalantis.contextmenu.lib.ContextMenuDialogFragment;
import com.yalantis.contextmenu.lib.MenuObject;
import com.yalantis.contextmenu.lib.MenuParams;
import com.yalantis.contextmenu.lib.interfaces.OnMenuItemClickListener;
import com.yalantis.contextmenu.lib.interfaces.OnMenuItemLongClickListener;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements
        OnFragmentInteractionListener,
        OnMenuItemClickListener,
        OnMenuItemLongClickListener
{

    private FragmentManager fragmentManager;
    private ContextMenuDialogFragment mMenuDialogFragment;
    public static boolean isMain = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        define();
    }// end onCreate()

    private void define() {
        fragmentManager = getSupportFragmentManager();
        initToolbar();
        /*set slidingMenu fragment*/
        initMenuFragment();
        /*set main fragment*/
        addFragment(new MainFragment(), true, R.id.frame_fragments);
    }// end define()

    private void initToolbar() {
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar_test);
        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        /*
        TextView mToolBarTextView = (TextView) findViewById(R.id.txtToolbar_appbar);
        mToolbar.setNavigationIcon(R.drawable.ic_menu);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        mToolBarTextView.setText("Doctor");
        */
    }// end initToolbar()

    private void initMenuFragment() {
        MenuParams menuParams = new MenuParams();
        menuParams.setActionBarSize((int) getResources().getDimension(R.dimen.tool_bar_height));
        menuParams.setMenuObjects(getMenuObjects());
        menuParams.setClosableOutside(false);
        mMenuDialogFragment = ContextMenuDialogFragment.newInstance(menuParams);
        mMenuDialogFragment.setItemClickListener(this);
        mMenuDialogFragment.setItemLongClickListener(this);
    }// end initMenuFragment()

    private List<MenuObject> getMenuObjects() {
        // You can use any [resource, bitmap, drawable, color] as image:
        // item.setResource(...)
        // item.setBitmap(...)
        // item.setDrawable(...)
        // item.setColor(...)
        // You can set image ScaleType:
        // item.setScaleType(ScaleType.FIT_XY)
        // You can use any [resource, drawable, color] as background:
        // item.setBgResource(...)
        // item.setBgDrawable(...)
        // item.setBgColor(...)
        // You can use any [color] as text color:
        // item.setTextColor(...)
        // You can set any [color] as divider color:
        // item.setDividerColor(...)

        List<MenuObject> menuObjects = new ArrayList<>();

        MenuObject close = new MenuObject();
        close.setResource(R.drawable.icn_closes);

        MenuObject like = new MenuObject("علاقمندی ها");
        Bitmap b = BitmapFactory.decodeResource(getResources(), R.drawable.favorite_black);
        like.setBitmap(b);

        MenuObject addFr = new MenuObject("انتقادات و پیشنهادات");
        BitmapDrawable bd = new BitmapDrawable(getResources(),
                BitmapFactory.decodeResource(getResources(), R.drawable.my_library_books_black));
        addFr.setDrawable(bd);

        MenuObject addFav = new MenuObject("ارتباط با پشتیبانی");
        addFav.setResource(R.drawable.settings_black);

        MenuObject send = new MenuObject("معرفی به دوستان");
        send.setResource(R.drawable.share_black);

        MenuObject block = new MenuObject("داروخانه ها");
        block.setResource(R.drawable.my_location_black);

        menuObjects.add(close);
        menuObjects.add(like);
        menuObjects.add(addFr);
        menuObjects.add(addFav);
        menuObjects.add(send);
        menuObjects.add(block);
        return menuObjects;
    }// end getMenuObjects()

    protected void addFragment(Fragment fragment, boolean addToBackStack, int containerId) {
        invalidateOptionsMenu();
        String backStackName = fragment.getClass().getName();
        boolean fragmentPopped = fragmentManager.popBackStackImmediate(backStackName, 0);
        if (!fragmentPopped) {
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.add(containerId, fragment, backStackName)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            if (addToBackStack)
                transaction.addToBackStack(backStackName);
            transaction.commit();
        }
    }// end addFragment()

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.context_menu :
                if (fragmentManager.findFragmentByTag(ContextMenuDialogFragment.TAG) == null) {
                    mMenuDialogFragment.show(fragmentManager, ContextMenuDialogFragment.TAG);
                }
                break;

            case android.R.id.home :
                onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (mMenuDialogFragment != null && mMenuDialogFragment.isAdded()) {
            mMenuDialogFragment.dismiss();
        } else {
            if(!isMain){
                super.onBackPressed();
            }
        }
    }

    @Override
    public void onFragmentInteraction(int tagNumber) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        Intent intent;
        switch (tagNumber){
            case 1:
                // درباره ما
                AboutUsFragment aboutUsFragment = new AboutUsFragment();
                ft.setCustomAnimations(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
                ft.replace(R.id.frame_fragments, aboutUsFragment);
                ft.addToBackStack(null);
                ft.commit();
                break;

            case 2:
                // خدمات
                ServicesFragment servicesFragment = new ServicesFragment();
                ft.setCustomAnimations(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
                ft.replace(R.id.frame_fragments, servicesFragment);
                ft.addToBackStack(null);
                ft.commit();
                break;

            case 3:
                // دانستنی ها
                RSSFragment rssFragment = new RSSFragment();
                ft.setCustomAnimations(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
                ft.replace(R.id.frame_fragments, rssFragment);
                ft.addToBackStack(null);
                ft.commit();
                break;

            case 4:
                // باشگاه من
                LoginFragment loginFragment = new LoginFragment();
                ft.setCustomAnimations(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
                ft.replace(R.id.frame_fragments, loginFragment);
                ft.addToBackStack(null);
                ft.commit();
                break;

            case 11:
                // درباره کلینیک
                intent = new Intent(MainActivity.this, AboutUsActivity.class);
                startActivity(intent);
                break;

            case 12:
                // بخش های تخصصی
                intent = new Intent(MainActivity.this, SpecialPartsActivity.class);
                startActivity(intent);
                break;

            case 13:
                // گالری مولتی مدیا
                break;

            case 14:
                // شبکه های اجتماعی
                break;

            case 15:
                // تماس با ما
                break;

            case 20:
                // بیمه های تحت پوشش
                intent = new Intent(MainActivity.this, InsuranceActivity.class);
                startActivity(intent);
                break;

            case 21:
                // سرویس ها
                intent = new Intent(MainActivity.this, ServicesActivity.class);
                startActivity(intent);
                break;

            case 22:
                // نوبت دهی
                break;

            case 23:
                // تخمین هزینه
                break;

            case 24:
                // مراقبت های بعد
                intent = new Intent(MainActivity.this, ServicesActivity.class);
                intent.putExtra("FACTION", Variables.getCareAfter);
                startActivity(intent);
                break;

            case 25:
                // مراقبت های قبل
                intent = new Intent(MainActivity.this, ServicesActivity.class);
                intent.putExtra("FACTION", Variables.getCareBefore);
                startActivity(intent);
                break;

            case 30:
                // اخبار
                intent = new Intent(MainActivity.this, NewsActivity.class);
                startActivity(intent);
                break;

            case 31:
                // RSS
                intent = new Intent(MainActivity.this, NewsActivity.class);
                startActivity(intent);
                break;

            case 32:
                // مجله
                intent = new Intent(MainActivity.this, MagazineActivity.class);
                startActivity(intent);
                break;

            case 33:
                // سوالات متداول
                break;

            case 34:
                // دارو ها و عوارض
                break;

            case 41:
                // باشگاه مشتریان
                // TODO : send data to server and after authornaziation go to ClubActivity
                intent = new Intent(MainActivity.this, ClubActivity.class);
                startActivity(intent);
                break;

            case 42:
                // ثبت نام
                SignUpFragment signUpFragment = new SignUpFragment();
                ft.setCustomAnimations(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
                ft.replace(R.id.frame_fragments, signUpFragment);
                ft.addToBackStack(null);
                ft.commit();
                break;

            default:
                break;
        }
    }// end onFragmentInteraction()

    @Override
    public void onMenuItemClick(View clickedView, int position) {
        Intent intent;
        switch (position){
            case 0:
                // بستن
                break;

            case 1:
                // علاقمندی ها
                intent = new Intent(MainActivity.this, FavoriteActivity.class);
                startActivity(intent);
                break;

            case 2:
                // انتقادات و پیشنهادات
                intent = new Intent(MainActivity.this, SendIdeasActivity.class);
                startActivity(intent);
                break;

            case 3:
                // ارتباط با پشتیبانی
                intent = new Intent(MainActivity.this, DeveloperActivity.class);
                startActivity(intent);
                break;

            case 4:
                // معرفی به دوستان
                try{
                    ArrayList<Uri> uris = new ArrayList<>();
                    Intent sendIntent = new Intent(Intent.ACTION_SEND_MULTIPLE);
                    sendIntent.setType("application/*");
                    uris.add(Uri.fromFile(new File(getApplicationInfo().publicSourceDir)));
                    sendIntent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, uris);
                    startActivity(Intent.createChooser(sendIntent, null));
                }catch(Exception e){e.printStackTrace();}
                break;

            case 5:
                // داروخانه ها
                break;
        }
    }

    @Override
    public void onMenuItemLongClick(View clickedView, int position) {
        Toast.makeText(this, "Long clicked on position: " + position, Toast.LENGTH_SHORT).show();
    }

}// end class
