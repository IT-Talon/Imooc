package com.hzcwtech.imooc.ui.course;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hzcwtech.imooc.R;
import com.hzcwtech.imooc.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class ConsultFragment extends BaseFragment {


    public ConsultFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_consult, container, false);
    }

}
