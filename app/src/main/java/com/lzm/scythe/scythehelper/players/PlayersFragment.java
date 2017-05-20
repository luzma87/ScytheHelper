package com.lzm.scythe.scythehelper.players;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;

import com.lzm.scythe.scythehelper.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PlayersFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PlayersFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PlayersFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public PlayersFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment PlayersFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PlayersFragment newInstance() {
        return new PlayersFragment();
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
        Context context = this.getContext();

        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.players_fragment, container, false);

//Animations
        final Animation show_fab_1 = AnimationUtils.loadAnimation(context, R.anim.fab1_show);
        final Animation hide_fab_1 = AnimationUtils.loadAnimation(context, R.anim.fab1_hide);
        final Animation show_fab_2 = AnimationUtils.loadAnimation(context, R.anim.fab2_show);
        final Animation hide_fab_2 = AnimationUtils.loadAnimation(context, R.anim.fab2_hide);
        final Animation show_fab_3 = AnimationUtils.loadAnimation(context, R.anim.fab3_show);
        final Animation hide_fab_3 = AnimationUtils.loadAnimation(context, R.anim.fab3_hide);

        final FloatingActionButton fab1 = (FloatingActionButton) view.findViewById(R.id.fab_1);
        final FloatingActionButton fab2 = (FloatingActionButton) view.findViewById(R.id.fab_2);
        final FloatingActionButton fab3 = (FloatingActionButton) view.findViewById(R.id.fab_3);
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showMiniFab(fab1, show_fab_1, 1.7);
                showMiniFab(fab2, show_fab_2, 2.75);
                showMiniFab(fab3, show_fab_3, 3.8);
            }
        });
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideMiniFab(fab1, hide_fab_1, 1.7);
            }
        });
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideMiniFab(fab2, hide_fab_2, 2.75);
            }
        });
        fab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideMiniFab(fab3, hide_fab_3, 3.8);
            }
        });

        return view;
    }


    private void hideMiniFab(FloatingActionButton fab, Animation animation, double y) {
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) fab.getLayoutParams();
        layoutParams.rightMargin -= (int) (fab.getWidth() * 0.25);
        layoutParams.bottomMargin -= (int) (fab.getHeight() * y);
        fab.setLayoutParams(layoutParams);
        fab.startAnimation(animation);
        fab.setClickable(false);
    }

    private void showMiniFab(FloatingActionButton fab, Animation animation, double y) {
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) fab.getLayoutParams();
        layoutParams.rightMargin += (int) (fab.getWidth() * 0.25);
        layoutParams.bottomMargin += (int) (fab.getHeight() * y);
        fab.setLayoutParams(layoutParams);
        fab.startAnimation(animation);
        fab.setClickable(true);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
