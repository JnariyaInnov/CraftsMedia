package com.ijp.app.craftmedia.Adapter.PicstaFragmentViewHolders;

import android.content.Context;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ijp.app.craftmedia.CategoryListWallpaper;
import com.ijp.app.craftmedia.Interface.IitemClickListner;
import com.ijp.app.craftmedia.Model.PicstaModel.CategoryFragmentItem;
import com.ijp.app.craftmedia.R;
import com.ijp.app.craftmedia.Utils.Common;
import com.ijp.app.craftmedia.WallpaperDetailActivity;
import com.squareup.picasso.Picasso;

import java.net.MalformedURLException;
import java.util.List;

public class CategoryFragmentAdapter extends RecyclerView.Adapter<CategoryFragmentAdapter.CatrgoryFragmentViewHolder> {
    private Context mContext;
    private List<CategoryFragmentItem> categoryFragmentItemList;



    public CategoryFragmentAdapter(Context mContext, List<CategoryFragmentItem> categoryFragmentItemList)  {
        this.mContext = mContext;
        this.categoryFragmentItemList = categoryFragmentItemList;
    }

    @NonNull
    @Override
    public CatrgoryFragmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater=LayoutInflater.from(mContext);
        view=inflater.inflate(R.layout.category_fragment_item,parent,false);

        return new CatrgoryFragmentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CatrgoryFragmentViewHolder holder, final int position) {

        Picasso.with(mContext).load(categoryFragmentItemList.get(position).image_url)
                .into(holder.categoryImage);


        holder.categoryText.setText(categoryFragmentItemList.get(position).category);

        holder.setItemClickListner(new IitemClickListner() {
            @Override
            public void onClick(View v) {
                Common.currentCategoryFragmentsItem=categoryFragmentItemList.get(position);
                mContext.startActivity(new Intent(mContext, CategoryListWallpaper.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryFragmentItemList.size();
    }

    public class CatrgoryFragmentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView categoryImage;
        TextView  categoryText;
        IitemClickListner iitemClickListner;

        public void setItemClickListner(IitemClickListner itemClickListner) {
            this.iitemClickListner = itemClickListner;
        }

        public CatrgoryFragmentViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);

            categoryImage=itemView.findViewById(R.id.category_image);
            categoryText=itemView.findViewById(R.id.category_text);
        }

        @Override
        public void onClick(View v) {
            iitemClickListner.onClick(v);
        }
    }
}