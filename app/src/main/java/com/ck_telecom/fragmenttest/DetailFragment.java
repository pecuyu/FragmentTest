package com.ck_telecom.fragmenttest;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment {

    public OnDetailFragmentDestoryListener mListener;

    public DetailFragment() {
        // Required empty public constructor
    }

    public static DetailFragment newInstance(Bundle data){
        DetailFragment fragment=new DetailFragment();
        fragment.setArguments(data);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnDetailFragmentDestoryListener){
            mListener= (OnDetailFragmentDestoryListener) context;
        }else{

        }

    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (mListener != null) {
            mListener.onDetailFragmentDestory();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_detail, container, false);
        TextView tv = (TextView) view.findViewById(R.id.id_detail_tv);
        String id = (String) getArguments().get("id");
        tv.setText("item id:"+id);
        return view;
    }

    interface OnDetailFragmentDestoryListener{
        void onDetailFragmentDestory();
    }

}
