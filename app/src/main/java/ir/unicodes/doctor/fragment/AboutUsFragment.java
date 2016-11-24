package ir.unicodes.doctor.fragment;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import ir.unicodes.doctor.Interface.OnFragmentInteractionListener;
import ir.unicodes.doctor.R;
import ir.unicodes.doctor.activity.MainActivity;
import ir.unicodes.doctor.adapter.ItemClickSupport;
import ir.unicodes.doctor.adapter.RecycleViewAdapter_aboutus;
import ir.unicodes.doctor.adapter.ViewPagerAdapter_main;
import ir.unicodes.doctor.classes.Variables;
import ir.unicodes.doctor.objects.Object_Aboutus;

public class AboutUsFragment extends Fragment {

    private ViewGroup layout;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private int page = 1;
    private Timer timer;
    private ViewPager vp;
    private List<String> myList = new ArrayList<>();
    private RecyclerView rv;
    private ArrayList<Object_Aboutus> rvList = new ArrayList<>();
    private RecycleViewAdapter_aboutus mAdapter;
    private Typeface San;

    private OnFragmentInteractionListener mListener;

    public AboutUsFragment() {}

    public static AboutUsFragment newInstance(String param1, String param2) {
        AboutUsFragment fragment = new AboutUsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        San = Typeface.createFromAsset(getActivity().getAssets(), "fonts/SansLight.ttf");
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        layout = (ViewGroup) inflater.inflate(R.layout.fragment_about_us, container, false);
        return layout;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MainActivity.isMain = false;
        vp = (ViewPager) layout.findViewById(R.id.viewPager);
        rv = (RecyclerView) layout.findViewById(R.id.rv);
        LinearLayoutManager lm = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(lm);

        vp.setAdapter(new ViewPagerAdapter_main(getActivity(),myList,true));
        refreshAdapter();
        onItemClickListener();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            mParam1 = bundle.getString("title");
        }
    }

    public void onButtonPressed(int tagNumber) {
        if (mListener != null) {
            mListener.onFragmentInteraction(tagNumber);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        pageSwitcher(4);
    }

    @Override
    public void onPause() {
        super.onPause();
        timer.cancel();
        timer.purge();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void pageSwitcher(int seconds) {
        timer = new Timer();
        timer.scheduleAtFixedRate(new RemindTask(), 0, seconds * 1000);
    }

    class RemindTask extends TimerTask {
        @Override
        public void run() {
            getActivity().runOnUiThread(new Runnable() {
                public void run() {
                    if (page > 4) {
                        page = 0;
                        vp.setCurrentItem(page++);
                    } else {
                        Log.i(Variables.Tag,"in run");
                        vp.setCurrentItem(page++);
                    }
                }
            });
        }// end run()
    }// end RemindTask{}

    private void refreshAdapter(){
        rvList.add(new Object_Aboutus(R.drawable.biz,"درباره ما"));
        rvList.add(new Object_Aboutus(R.drawable.apps,"بخش های تخصصی"));
        rvList.add(new Object_Aboutus(R.drawable.camera2,"گالری مولتی مدیا"));
        rvList.add(new Object_Aboutus(R.drawable.telegram,"شبکه های اجتماعی"));
        rvList.add(new Object_Aboutus(R.drawable.map,"تماس با ما"));
        mAdapter = new RecycleViewAdapter_aboutus(rvList,San,getActivity());
        rv.setAdapter(mAdapter);
    }// end refreshAdapter()

    private void onItemClickListener() {
        ItemClickSupport.addTo(rv).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                switch (position){
                    case 0:
                        onButtonPressed(11);
                        break;

                    case 1:
                        onButtonPressed(12);
                        break;

                    case 2:
                        onButtonPressed(13);
                        break;

                    case 3:
                        onButtonPressed(14);
                        break;

                    case 4:
                        onButtonPressed(15);
                        break;

                    default:
                        break;
                }// end switch
            }// end onItemClicked()
        });
    }// end onItemClickListener()

}// end class