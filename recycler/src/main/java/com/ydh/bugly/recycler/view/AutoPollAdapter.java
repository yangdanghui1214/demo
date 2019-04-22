package com.ydh.bugly.recycler.view;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ydh.bugly.recycler.R;

import java.util.List;

public class AutoPollAdapter extends RecyclerView.Adapter<AutoPollAdapter.BaseViewHolder> {
    private final List<String> mData;
    private boolean deviation = false;
    private int value = 0;
    private int price = 0;

    public AutoPollAdapter(List<String> list) {
        this.mData = list;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_auto_poll, parent, false);
        return new BaseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        int positions = Math.abs(position - price);
        int a = 0;
        if (deviation) {
            deviation = false;
            a = value;
            price = position - value;
        } else {
            a = positions % mData.size();
        }
        value = a + 1;
        Log.e("zxy", "onBindViewHolder: " + position);
        Log.e("zxy", "显示的Item: " + a);
        String data = mData.get(a);

        holder.tv.setText(data);
    }

    @Override
    public int getItemCount() {
        return Integer.MAX_VALUE;
    }

    public void addItem(String string) {
        deviation = true;
        mData.add(string);
    }

    public void updateItem(int a, String string) {
        mData.remove(a);
        mData.add(a, string);
    }

    public void rmItem(int i) {
        deviation = true;
        mData.remove(i);
    }

    class BaseViewHolder extends RecyclerView.ViewHolder {
        TextView tv;

        BaseViewHolder(View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv_content);
        }
    }
}
