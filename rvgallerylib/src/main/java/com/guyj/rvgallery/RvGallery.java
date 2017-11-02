package com.guyj.rvgallery;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * Created by gu.yj on 2017/10/23.
 */

public class RvGallery extends RecyclerView {

    public RvGallery(Context context) {
        this(context,null);
    }
    public RvGallery(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }
    public RvGallery(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context=context;
        init();
    }

    public interface OnItemSelectedListener{
        void itemSelected(int position);
    }
    private OnItemSelectedListener onItemSelectedListener;
    public void setOnItemSelectedListener(OnItemSelectedListener onItemSelectedListener){
        this.onItemSelectedListener=onItemSelectedListener;
    }

    private RecyclerView rv;
    private LinearSnapHelper linearSnapHelper;
    private LinearLayoutManager linearLayoutManager;
    private boolean OpenPositionTag;
    private Context context;

    private void init() {
        rv=this;
        rv.setLayoutManager(linearLayoutManager=new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
        linearSnapHelper=new LinearSnapHelper();
        linearSnapHelper.attachToRecyclerView(rv);
        rv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                switch (newState){
                    case SCROLL_STATE_DRAGGING://手势拖动
                        OpenPositionTag=true;
                        break;
                    case SCROLL_STATE_IDLE://停止
                        if (OpenPositionTag){
                            int i=recyclerView.getChildLayoutPosition(linearSnapHelper.findSnapView(linearLayoutManager));
                            if (null!=onItemSelectedListener&&recyclerView.getAdapter().getItemCount()>2)
                                onItemSelectedListener.itemSelected(i-1);
                            OpenPositionTag=false;
                        }
                        break;
                }
            }
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }
}
