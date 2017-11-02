package com.guyj.demo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.guyj.rvgallery.adapter.RvGalleryAdapter;


/**
 * Created by gu.yj on 2017/11/2.
 */
class MyAdapter extends RvGalleryAdapter<String,MyAdapter.MyViewHolder> {

    public MyAdapter(Context context) {
        super(context);
    }

    @Override
    protected MyViewHolder setViewHolder(View viewImage) {
        return new MyViewHolder(viewImage);
    }


    @Override
    public int setViewLayoutID() {
        return R.layout.item_image;
    }

    @Override
    protected void convert(MyViewHolder holder, int position, String bean) {
        if (position%3==0){
            holder.img.setImageResource(R.mipmap.guide_2);
        }else{
            holder.img.setImageResource(R.mipmap.guide_1);
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView img;

        public MyViewHolder(View itemView) {
            super(itemView);
            img=(ImageView)itemView.findViewById(R.id.img);
        }
    }
}