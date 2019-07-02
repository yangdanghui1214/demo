package com.ydh.animation.property;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ydh.animation.R;
import com.ydh.animation.databinding.ActivityPropertyRotateRvBinding;

public class PropertyRotateRvActivity extends AppCompatActivity {

    ActivityPropertyRotateRvBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_property_rotate_rv);

//        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(binding.constraintLayout,"rotation",0,30);
//        objectAnimator.setDuration(0);
//        objectAnimator.start();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        binding.rv1.setLayoutManager(linearLayoutManager);
        binding.rv1.setAdapter(new MyAdapter());

    }

    class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{
        public MyAdapter() {
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(PropertyRotateRvActivity.this).inflate(R.layout.item_my,null);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

//            Glide.with(ImageActivity.this)
//                    .load(R.drawable.dog)
//                    .asBitmap()
//                    .into(holder.imageView);


        }

        @Override
        public int getItemCount() {
            return 1;
        }

        class MyViewHolder extends RecyclerView.ViewHolder {
            MovePhoto movePhoto;
            public MyViewHolder(View itemView) {
                super(itemView);
                movePhoto = findViewById(R.id.movephoto);
            }
        }
    }
}
