package com.d2956987215.mow.widgets.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.LinkedList;
import java.util.List;


public abstract class BaseRecyclerAdapter<T> extends
        RecyclerView.Adapter<BaseRecyclerViewHolder> {
    public final String TAG=getClass().getSimpleName();
    protected Context mContext;
    protected LayoutInflater mInflater;
    protected List<T> mDatas = new LinkedList<T>();
    public OnItemClickListener<T> mOnItemClickListener;
    public OnItemLongClickListener<T> mOnItemLongClickListener;
    private boolean longClickReturn=true;

    public BaseRecyclerAdapter(Context context, List<T> datas) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        if (datas != null) {
            mDatas = datas;
        }
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        int count = 0;
        if (mDatas.size() > 0) {
            count = mDatas.size();
        }
        return count;
    }

    public void addItemLast(List<T> datas) {
        mDatas.addAll(datas);
    }

    public void addItemTop(List<T> datas) {
        mDatas = datas;
    }

    public void remove(int position) {
        mDatas.remove(position);
    }

    public void removeAll() {
        mDatas.clear();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public List<T> getDatas() {
        return mDatas;
    }

    // 点击事件接口
    public interface OnItemClickListener<T> {
        void onItemClick(View view, int position, T model);
    }

    public interface OnItemLongClickListener<T> {
        void onItemLongClick(View view, int position, T model);
    }

    public void setOnItemClickListener(OnItemClickListener<T> listener) {
        this.mOnItemClickListener = listener;
    }public void setOnItemLongClickListener(OnItemLongClickListener<T> listener) {
        this.mOnItemLongClickListener = listener;
    }

    @Override
    public void onBindViewHolder(final BaseRecyclerViewHolder holder, final int position) {
        if(mOnItemClickListener!=null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onItemClick(holder.itemView, position, mDatas.get(position));
                }
            });
        }
        if (mOnItemLongClickListener!=null) {
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mOnItemLongClickListener.onItemLongClick(holder.itemView,position,mDatas.get(position));
                    //返回为false，click仍会执行，返回true,onclick不会执行
                    return longClickReturn;
                }
            });
        }
        showViewHolder(holder,position);
    }

    //设置Longclick的返回值，false，click仍会执行，true,onclick不会执行,默认为true
    public void setLongClickReturn(boolean longClickReturn)
    {
        this.longClickReturn = longClickReturn;
    }

    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return createViewMyHolder(parent, viewType);
    }
    
    protected abstract void showViewHolder(BaseRecyclerViewHolder holder, int position);
    
    /***
     * 
     * @param parent
     * @param viewType
     * @return
     */
    protected abstract BaseRecyclerViewHolder createViewMyHolder(ViewGroup parent,
            int viewType);



}