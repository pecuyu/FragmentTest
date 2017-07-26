package com.ck_telecom.fragmenttest.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ck_telecom.fragmenttest.R;
import com.ck_telecom.fragmenttest.fragment.ListItemFragment.OnListFragmentInteractionListener;
import com.ck_telecom.fragmenttest.bean.InfoBean.InfoItem;

import java.util.List;

public class MyListItemRecyclerViewAdapter extends RecyclerView.Adapter<MyListItemRecyclerViewAdapter.ViewHolder> {

    private final List<InfoItem> mValues;
    private final OnListFragmentInteractionListener mListener;
    private OnListFragmentItemLongClickListener mItemLongClickListener;

    public MyListItemRecyclerViewAdapter(List<InfoItem> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view =null;
        if (viewType == 0) {    // 判断viewType来加载不同的view布局
            view = LayoutInflater.from(parent.getContext())
                            .inflate(R.layout.fragment_recycler_view_listitem, parent, false);
        } else if (viewType==1){
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.fragment_recycler_view_listitem2, parent, false);
        }

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(mValues.get(position).id);
        holder.mContentView.setText(mValues.get(position).content);

        // 设置条目点击事件
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });

        holder.mView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mItemLongClickListener != null) {
                    mItemLongClickListener.onListFragmentItemLongClick(holder.mItem,position);
                }
                return false;
            }
        });
    }

    public List<InfoItem> getValues() {
        return mValues;
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public InfoItem mItem;

        public ViewHolder(View view) {
            super(view);

            mView = view;
            mIdView = (TextView) view.findViewById(R.id.id);
            mContentView = (TextView) view.findViewById(R.id.content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }


    /**
     * 获取view的类型
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        return position%2;
    }

    /**
     * 长按监听器
     */
    public interface OnListFragmentItemLongClickListener{
        void onListFragmentItemLongClick(InfoItem item,int pos);
    }

    /**
     * 设置监听器
     * @param listener
     */
    public void setOnListFragmentItemLongClickListener(OnListFragmentItemLongClickListener listener) {
        this.mItemLongClickListener=listener;
    }
}
