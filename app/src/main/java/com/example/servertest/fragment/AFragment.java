package com.example.servertest.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.servertest.MygGridViewAdapter;
import com.example.servertest.R;

import java.util.List;


public class AFragment extends Fragment {
    private GridView mGv;
    private MygGridViewAdapter mygGridViewAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate ( R.layout.fragment_a,container,false);
        mGv = view.findViewById ( R.id.gv );
        mGv.setAdapter ( new MygGridViewAdapter(this.getActivity ()) );
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated ( view, savedInstanceState );

    }
}
