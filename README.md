# RecyclerViewGallery
通过RecyclerView封装实现的Gallery，并优化了回调

### 一 效果图
---
![rvgallery.gif](http://upload-images.jianshu.io/upload_images/3344501-4609ee1b5a7e040a.gif?imageMogr2/auto-orient/strip)
### 二 使用方法
---
![最新版本号](https://jitpack.io/v/kaxi4it/RecyclerViewGallery.svg)
1. 在你root的gradle中添加引用
```java
allprojects {
    repositories {
    ...
    maven { url "https://jitpack.io" }
    }
 }
```
然后在module的gradle中添加引用
```java
dependencies {
    compile 'com.github.kaxi4it:RecyclerViewGallery:1.3'
}
```
2. 在你的布局文件中添加xml代码如：
```java
<com.guyj.rvgallery.RvGallery
        android:id="@+id/gallery"
        android:layout_width="match_parent"
        android:layout_height="match_parent"/>
```
然后需要继承RvGalleryAdapter并实现一一实现里面的方法如：
```java
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
```
最后在我们的Activity类中实现如下方法，即可正常使用该控件了：
```java
 private void initGallery() {
        gallery.setAdapter(adapter=new MyAdapter(this));
        adapter.setData(benas);
        gallery.setOnItemSelectedListener(new RvGallery.OnItemSelectedListener() {
            @Override
            public void itemSelected(int position) {
                Toast.makeText(MainActivity.this, "index="+position, Toast.LENGTH_SHORT).show();
            }
        });
    }
```
**补充说明：** RvGalleryAdapter<String,MyAdapter.MyViewHolder>中string是你数据集合的泛型类型，viewholder则是你的item的viewholder类型，由于我的viewholder为了方便写在了adapter中，正常情况下，建议拆分开。
