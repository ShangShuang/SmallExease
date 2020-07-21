package com.example.smallexease.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.example.smallexease.R;
import com.example.smallexease.bean.ListBean;
import com.example.smallexease.utils.SharedPrefence;

import java.util.ArrayList;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private Context context;
    private ArrayList<ListBean.DatasBean> list;
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public ListAdapter(Context context, ArrayList<ListBean.DatasBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.tv_title.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        holder.tv_title.setText(list.get(position).getTitle());
        holder.tv_author.setText(list.get(position).getAuthor());
        holder.tv_author.setTypeface(Typeface.defaultFromStyle(Typeface.ITALIC));
        Glide.with(context).load(list.get(position).getThumbnail()).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(holder.iv_img);
        holder.btn_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(position);
                }
            }
        });
        boolean isClick = (boolean) SharedPrefence.getParam(context, position + "", false);
        if (!isClick) {
            holder.btn_click.setText("关注");
        } else {
            holder.btn_click.setText("取消");
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView iv_img;
        public TextView tv_title;
        public TextView tv_author;
        public Button btn_click;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.iv_img = (ImageView) itemView.findViewById(R.id.iv_img);
            this.tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            this.tv_author = (TextView) itemView.findViewById(R.id.tv_author);
            this.btn_click = (Button) itemView.findViewById(R.id.btn_click);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
}
