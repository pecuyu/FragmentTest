package com.yu.fragmenttest.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.yu.fragmenttest.R;
import com.yu.fragmenttest.adapter.MyListItemRecyclerViewAdapter;
import com.yu.fragmenttest.bean.InfoBean;
import com.yu.fragmenttest.ui.DetailActivity;

import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class ListItemFragment extends Fragment implements MyListItemRecyclerViewAdapter.OnListFragmentItemLongClickListener {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;
    private MyListItemRecyclerViewAdapter adapter;
    public ListItemFragment() {

    }

    public static ListItemFragment newInstance(int columnCount) {  // 静态方法，设置fragment参数
        ListItemFragment fragment = new ListItemFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_list_recycler, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {  // 根据列数设置LayoutManager
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            adapter = new MyListItemRecyclerViewAdapter(InfoBean.ITEMS, mListener);
            adapter.setOnListFragmentItemLongClickListener(this); // set long click listener
            recyclerView.setAdapter(adapter);

        }
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {  // c初始化监听器
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    //长按则从Fragment启动Activity
    @Override
    public void onListFragmentItemLongClick(InfoBean.InfoItem item,int pos) {
        Log.e(getClass().getName().toString(), "onLongClick");
        Intent intent = new Intent(getActivity(), DetailActivity.class);
        Bundle data = new Bundle();
        data.putString("id", item.id);
        data.putString("pos", String.valueOf(pos));
        intent.putExtras(data);
        startActivityForResult(intent,1);
        Toast.makeText(getActivity(), "长按从Fragment启动Activity", Toast.LENGTH_SHORT).show();

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     */
    public interface OnListFragmentInteractionListener {
        void onListFragmentInteraction(InfoBean.InfoItem item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 6) {
            String id = (String) data.getExtras().get("id");
            String pos = (String) data.getExtras().get("pos");
            List<InfoBean.InfoItem> infoItems = adapter.getValues();
            infoItems.get(Integer.parseInt(pos)).id = id;   // 获得返回的id并修改
            adapter.notifyItemChanged(Integer.parseInt(pos));  // 刷新某个条目
        }
    }
}
