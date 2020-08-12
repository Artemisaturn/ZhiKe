package com.example.ZhiKe.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ZhiKe.R;
import com.example.ZhiKe.activities.SelectTimeActivity;
import com.example.ZhiKe.models.PositionItems;

import java.util.ArrayList;
import java.util.List;

public class PositionGridAdapter extends RecyclerView.Adapter<PositionGridAdapter.ViewHolder> {

    private  Context mContext;
    private View mItemView;
    private List<PositionItems> items;

    public PositionGridAdapter(Context context){
        mContext=context;

        items = new ArrayList<PositionItems>()
        {{
            add(new PositionItems("逸夫楼",R.mipmap.yifu));
            add(new PositionItems("教学楼",R.mipmap.jiaoxue));
            add(new PositionItems("机电楼",R.mipmap.jidian));
            add(new PositionItems("经管楼",R.mipmap.jingguan));
        }};

    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_grid_position,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final PositionItems item = items.get(position);
        holder.imageView.setImageResource(item.getBuildingPicture());
        holder.textView.setText(item.getBuildingName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, SelectTimeActivity.class);
                //Intent intent = new Intent(mContext, TestActivity.class);
                intent.putExtra("position",item.getBuildingName());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        View itemView;
        ImageView imageView;
        TextView textView;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            this.itemView = itemView;
            imageView = itemView.findViewById(R.id.building_picture);
            textView = itemView.findViewById(R.id.building_name);
        }
    }

}
