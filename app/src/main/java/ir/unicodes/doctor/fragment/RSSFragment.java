package ir.unicodes.doctor.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import ir.unicodes.doctor.Interface.OnFragmentInteractionListener;
import ir.unicodes.doctor.R;
import ir.unicodes.doctor.activity.MainActivity;

public class RSSFragment extends Fragment {

    private ViewGroup layout;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private LinearLayout layNews,layRSS,layMagazine,layQuestions,layDrugs;

    public RSSFragment() {}

    public static RSSFragment newInstance(String param1, String param2) {
        RSSFragment fragment = new RSSFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        layout = (ViewGroup) inflater.inflate(R.layout.fragment_rss, container, false);
        return layout;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MainActivity.isMain = false;

        layNews     = (LinearLayout) layout.findViewById(R.id.layNews);
        layRSS      = (LinearLayout) layout.findViewById(R.id.layRSS);
        layMagazine = (LinearLayout) layout.findViewById(R.id.layMagazine);
        layQuestions= (LinearLayout) layout.findViewById(R.id.layQuestions);
        layDrugs    = (LinearLayout) layout.findViewById(R.id.layDrugs);

        layNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onButtonPressed(30);
            }
        });

        layRSS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onButtonPressed(31);
            }
        });

        layMagazine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onButtonPressed(32);
            }
        });

        layQuestions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onButtonPressed(33);
            }
        });

        layDrugs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onButtonPressed(34);
            }
        });

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            mParam1 = bundle.getString("param1");
            mParam2 = bundle.getString("param2");
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

}
