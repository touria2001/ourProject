package com.example.myapplication4.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication4.Interface.ItemClickListner;
import com.example.myapplication4.R;

public class MessageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{
    public TextView ourUser;
   public ImageView ourUserImg;
    public ItemClickListner listner;






    public MessageViewHolder(@NonNull View itemView) {
        super(itemView);
        ourUser = (TextView) itemView.findViewById(R.id.ourUser);
        ourUserImg = (ImageView) itemView.findViewById(R.id.ourUserImg);




    }
    public void setItemClickListner(ItemClickListner listner)
    {
        this.listner= listner;
    }

    @Override
    public void onClick(View view) {
        listner.onClick(view, getAdapterPosition(),false);
    }
}
