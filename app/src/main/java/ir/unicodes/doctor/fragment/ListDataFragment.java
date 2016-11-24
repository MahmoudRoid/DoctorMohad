package ir.unicodes.doctor.fragment;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
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
import ir.unicodes.doctor.objects.Object_Data;

public class ListDataFragment extends Fragment {

    private ViewGroup layout;

    private static final String ARG_PARAM1 = "FACTION";
    private static final String ARG_PARAM2 = "param2";

    private String FACTION;
    private String mParam2;

    private int page = 1;
    private Timer timer;
    private ViewPager vp;
    private List<String> myList = new ArrayList<>();
    private RecyclerView rv;
    private FloatingActionButton fab;
    private Typeface San;

    private OnFragmentInteractionListener mListener;

    public ListDataFragment() {}

    public static ListDataFragment newInstance(String param1, String param2) {
        ListDataFragment fragment = new ListDataFragment();
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
            FACTION = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        layout = (ViewGroup) inflater.inflate(R.layout.fragment_list_data, container, false);
        return layout;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        fab = (FloatingActionButton) layout.findViewById(R.id.fab);
        rv = (RecyclerView) layout.findViewById(R.id.rv);
        LinearLayoutManager lm = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(lm);
        /*get Intent data from the activity which call fragment*/
        getWhat();
        /*set situation of fab*/
        setFab();
        /*set data of recycleview*/
        setRecycleView();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            //mParam1 = bundle.getString("title");
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
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    private void refreshAdapter(){

        //mAdapter = new RecycleViewAdapter_aboutus(rvList,San,getActivity());
        //rv.setAdapter(mAdapter);
    }// end refreshAdapter()

    private void getWhat(){

    }// end getWhat()

    private void setFab() {

    }// end setFab()

    private void setRecycleView(){

        /*refresh the adapter*/
        refreshAdapter();
    }// end setRecycleView()

}// end class