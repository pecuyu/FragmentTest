package com.yu.fragmenttest.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yu.fragmenttest.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment {

    public OnDetailFragmentDetachListener mListener;

    public DetailFragment() {
        // Required empty public constructor
    }

    public static DetailFragment newInstance(Bundle data){
        DetailFragment fragment=new DetailFragment();
        fragment.setArguments(data);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {  // 注册监听
        super.onAttach(context);
        if (context instanceof OnDetailFragmentDetachListener){
            mListener= (OnDetailFragmentDetachListener) context;
        }else{
            // 处理其他，抛异常或其他手段
        }

    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (mListener != null) {
            mListener.onDetailFragmentDetach();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mListener != null) {    // 注销监听
            mListener = null;
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

    public interface OnDetailFragmentDetachListener {
        void onDetailFragmentDetach();
    }

}
