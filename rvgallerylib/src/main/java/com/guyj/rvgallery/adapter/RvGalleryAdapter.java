package com.guyj.rvgallery.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.guyj.rvgallery.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gu.yj on 2017/11/2.
 */
public abstract class RvGalleryAdapter<T,VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private List<T> beans;
    private Context context;

    public RvGalleryAdapter(Context context){
        this.context=context;
        beans=new ArrayList<>();
    }

    @Override
    public int getItemViewType(int position) {
        if(position==0||position==getItemCount()-1){
            return 0;
        }else{
            return 1;
        }
    }
    ViewGroup.LayoutParams lpEmptyView,lpImage;
    View viewEmpty,viewCustom;
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        viewEmpty= LayoutInflater.from(context).inflate(R.layout.item_empty,parent,false);
        viewCustom= LayoutInflater.from(context).inflate(setViewLayoutID(),parent,false);
        switch (viewType){
            case 0:
                lpEmptyView=viewEmpty.getLayoutParams();
                lpImage=viewCustom.getLayoutParams();
                int pWidth=parent.getWidth();
                int view1Width=lpImage.width;
                lpEmptyView.width=pWidth/2-view1Width/2;
                viewEmpty.setLayoutParams(lpEmptyView);
                return new EmptyView(viewEmpty);
            case 1:
                return setViewHolder(viewCustom);
        }
        return null;
    }

    @Override
    public int getItemCount() {
        if (null!=beans&&beans.size()!=0){
            return beans.size()+2;
        }
        return 0;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {//0-11
        if (position>0&&position<getItemCount()-1) {//1-10
            convert((VH) holder, position - 1, beans.get(position - 1));
        }
    }

    /**
     * 用户自己的viewHolder对象，用来创建布局
     * @return ViewHolder
     * @param viewImage
     */
    protected abstract VH setViewHolder(View viewImage);

    /**
     * onCreateViewHolder里需要生成的layoutID
     * @return
     */
    protected abstract int setViewLayoutID();

    protected abstract void convert(VH holder, int position, T bean);

    /**
     * 数据集合
     */
    public void setData(List<T> beans){
        if (null==beans){
            this.beans.clear();
        }else{
            this.beans=beans;
        }
        notifyDataSetChanged();
    }


    protected class EmptyView extends RecyclerView.ViewHolder {
        public EmptyView(View itemView) {
            super(itemView);
        }
    }
}