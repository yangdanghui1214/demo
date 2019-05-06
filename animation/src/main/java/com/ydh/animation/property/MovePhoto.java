package com.ydh.animation.property;

import android.animation.ObjectAnimator;
import android.content.Context;

import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.ydh.animation.R;


public class MovePhoto extends LinearLayout {

    private RecyclerView rv1,rv2,rv3;
    private LinearLayout llContent;
    private Context context;
    private int count;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            count = 2;
            rv1.smoothScrollBy(count,0);
            rv2.smoothScrollBy(-count,0);
            rv3.smoothScrollBy(count+1,0);
            handler.sendEmptyMessageDelayed(0,100);
        }
    };

    public MovePhoto(Context context) {
        this(context,null);
    }

    public MovePhoto(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MovePhoto(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }

    private void init(){
        LayoutInflater.from(context).inflate(R.layout.move_view, this, true);
        rv1 = findViewById(R.id.rv_1);
        rv2 = findViewById(R.id.rv_2);
        rv3 = findViewById(R.id.rv_3);

        llContent = findViewById(R.id.ll_content);

        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false);
        rv1.setLayoutManager(linearLayoutManager1);
        rv1.setAdapter(new ImageAdapter());

        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false);
        rv2.setLayoutManager(linearLayoutManager2);
        rv2.setAdapter(new ImageAdapter());

        LinearLayoutManager linearLayoutManager3 = new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false);
        rv3.setLayoutManager(linearLayoutManager3);
        rv3.setAdapter(new ImageAdapter());

        ObjectAnimator animator = ObjectAnimator.ofFloat(llContent,"rotation",0,30);
        animator.setDuration(0);
        animator.start();


        rv1.scrollToPosition(Integer.MAX_VALUE/2);
        rv2.scrollToPosition(Integer.MAX_VALUE/2);
        rv3.scrollToPosition(Integer.MAX_VALUE/2);
        handler.sendEmptyMessage(0);


    }


    class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder>{
        public ImageAdapter() {
        }

        @NonNull
        @Override
        public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_image,null);
            return new ImageViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {

            Glide.with(context)
                    .load(R.drawable.dog)
                    .asBitmap()
                    .into(holder.imageView);
        }

        @Override
        public int getItemCount() {
            return Integer.MAX_VALUE;
        }

        class ImageViewHolder extends RecyclerView.ViewHolder {
            ImageView imageView;
            public ImageViewHolder(View itemView) {
                super(itemView);
                imageView = itemView.findViewById(R.id.iv_img);
            }
        }
    }

}
