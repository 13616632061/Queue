package com.yzx.lib.weight;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2019/7/4.
 * ListView单一布局万能适配器
 */

public abstract class OneLayoutBaseAdapter<T> extends BaseAdapter {
    private Context context;
    private List<T> datas;
    private int resid;

    public OneLayoutBaseAdapter(Context context, int resid) {
        this.context = context;
        this.resid = resid;
        datas = new ArrayList<>();
    }

    public void setDatas(List<T> datas) {
        this.datas = datas;
        this.notifyDataSetChanged();
    }

    public void addDatas(List<T> datas) {
        this.datas.addAll(datas);
        this.notifyDataSetChanged();
    }

    public T getDatas(int position) {
        return datas.get(position);
    }

    @Override
    public int getCount() {
        return datas == null ? 0 : datas.size();
    }

    @Override
    public Object getItem(int i) {
        return datas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View contentView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (contentView == null) {
            contentView = LayoutInflater.from(context).inflate(resid, null);
            viewHolder = new ViewHolder(contentView);
            contentView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) contentView.getTag();
        }
        bindView(viewHolder, datas.get(i));
        return contentView;
    }

    public abstract void bindView(ViewHolder viewHolder, T datas);

    public class ViewHolder {
        View layoutView;
        SparseArray<View> sparseArray = new SparseArray<>();

        public ViewHolder(View layoutView) {
            this.layoutView = layoutView;
        }

        public View getView(int id) {
            View view = sparseArray.get(id);
            if (view == null) {
                view = layoutView.findViewById(id);
                sparseArray.put(id, view);
            }
            return view;
        }

        public ViewHolder bindTextView(int id, String value) {
            TextView textView = (TextView) getView(id);
            textView.setText(value);
            return this;
        }

        public ViewHolder bindImageView(int id, String URL) {
            ImageView imageView = (ImageView) getView(id);
            Glide.with(context).load(URL).diskCacheStrategy(DiskCacheStrategy.ALL).into(imageView);
            return this;
        }
    }

}
