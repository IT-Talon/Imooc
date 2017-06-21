package com.hzcwtech.imooc.ui.course;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hzcwtech.imooc.R;
import com.hzcwtech.imooc.adapter.ConsultAdapter;
import com.hzcwtech.imooc.base.BaseFragment;
import com.hzcwtech.imooc.entity.model.ConsultModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class ConsultFragment extends BaseFragment {
    private static final String CONSULT_LIST = "Consults";

    private List<ConsultModel> consults;
    private ConsultAdapter mAdapter;

    @BindView(R.id.consult_recView)
    RecyclerView mRecyclerView;
    Unbinder unbinder;

    public static ConsultFragment newInstance(ArrayList<ConsultModel> consults) {

        Bundle args = new Bundle();
        args.putParcelableArrayList(CONSULT_LIST, consults);
        ConsultFragment fragment = new ConsultFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_consult, container, false);
        unbinder = ButterKnife.bind(this, view);
        consults = getArguments().getParcelableArrayList(CONSULT_LIST);
        initView();
        return view;
    }

    private void initView() {
        mAdapter = new ConsultAdapter(consults);
        mAdapter.addHeaderView(LayoutInflater.from(getContext()).inflate(R.layout.consult_headview, null));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mAdapter);


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
