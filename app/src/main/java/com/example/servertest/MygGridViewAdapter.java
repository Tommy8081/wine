package com.example.servertest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;


import java.util.List;

public class MygGridViewAdapter extends BaseAdapter {
    //声明引用
    private Context mContext;//这个变量用于第三方图片加载时用到
    private LayoutInflater mlayoutInflater;

    //创建一个构造函数
    public MygGridViewAdapter(Context context){

        this.mContext = context;
        //利用LayoutInflater把控件所在的布局文件加载到当前类中
        mlayoutInflater = LayoutInflater.from ( context );

    }


    static class ViewHolder{
        public ImageView Grid_imageview;
        public TextView Grid_textview;
    }

    @Override
    public int getCount() {
        //返回的是GridView的数量
        return 10;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if(view == null){
            //填写ListView的图标和标题等控件的来源，来自layout_item这个布局文件
            //把控件所在的布局文件加载到当前类中
            view = mlayoutInflater.inflate ( R.layout.layout_item ,null);
            //生成一个ViewHolder的对象
            viewHolder = new ViewHolder ();
            //获取控件对象
            viewHolder.Grid_imageview = view.findViewById ( R.id.grid_IV_Id);
            viewHolder.Grid_textview = view.findViewById ( R.id.grid_TV_Id );
            view.setTag ( viewHolder );
        }else {
            viewHolder = (ViewHolder)view.getTag ();
        }

        //修改控件属性
        viewHolder.Grid_textview.setText ( "汽车" );
        //加载第三方网络图片
        Glide.with ( mContext ).load ( "https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=2497539197,266911617&fm=26&gp=0.jpg" ).into ( viewHolder.Grid_imageview );
        return view;
    }
}
