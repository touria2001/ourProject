package com.example.myapplication4.ViewHolder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication4.Interface.ItemClickListner;
import com.example.myapplication4.R;

public class MessageUserViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView ourMsg;
    public TextView ourMsg2;
    public ItemClickListner listner;






    public MessageUserViewHolder(@NonNull View itemView) {
        super(itemView);
        ourMsg = (TextView) itemView.findViewById(R.id.ourMsg);
        ourMsg2 = (TextView) itemView.findViewById(R.id.ourMsg2);




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
