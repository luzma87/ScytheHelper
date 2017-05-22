package com.lzm.score.helper;

import android.app.Fragment;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class StartFragment extends Fragment {
    private OnStartFragmentInteractionListener mListener;
    private Context context;

    public StartFragment() {
    }

    public static StartFragment newInstance() {
        return new StartFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        context = this.getActivity();
        View view = inflater.inflate(R.layout.start_fragment, container, false);

        TextView appVersion = (TextView) view.findViewById(R.id.start_app_version);
        appVersion.setText(getAppVersion());

        Button popularityContinue = (Button) view.findViewById(R.id.start_continue);
        popularityContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onStartCalculating();
                }
            }
        });

        return view;
    }

    private String getAppVersion() {
        try {
            PackageInfo pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            String version = pInfo.versionName;
            return getString(R.string.app_version, version);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnStartFragmentInteractionListener) {
            mListener = (OnStartFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnStartFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnStartFragmentInteractionListener {
        void onStartCalculating();
    }
}
