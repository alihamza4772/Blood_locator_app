package com.example.blood_locator_app.Activities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.blood_locator_app.JavaClasses.Users;
import com.example.blood_locator_app.R;

import java.util.ArrayList;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.MyViewHolder> {

    Context context;
    ArrayList<Users> userlist;
    Bundle bun;

    public RecycleViewAdapter(Context context, ArrayList<Users> arrayList) {
        this.context = context;
        this.userlist = arrayList;
//     this.bun = bundle;


    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view ;
        LayoutInflater mInflater = LayoutInflater.from(context);
        view = mInflater.inflate(R.layout.custom_layout, parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        final Users user = userlist.get(position);
        holder.name.setText(user.getName());
        holder.city.setText(user.getCity());
        if(user.getBloodgroup().equals("A+")) {
            holder.iv.setImageResource(R.drawable.acolor);
        }
        else if(user.getBloodgroup().equals("O+"))
        {
            holder.iv.setImageResource(R.drawable.opluscolor);
        }
        else if (user.getBloodgroup().equals("B+"))
        {
            holder.iv.setImageResource(R.drawable.bpluscolor);
        }
        else if(user.getBloodgroup().equals("B-"))
        {
            holder.iv.setImageResource(R.drawable.bnegativecolor);
        }
        else if (user.getBloodgroup().equals("AB+"))
        {
            holder.iv.setImageResource(R.drawable.abpluscolor);
        }
        else if(user.getBloodgroup().equals("AB-"))
        {
            holder.iv.setImageResource(R.drawable.abnegitivecolor);
        }
        else if (user.getBloodgroup().equals("O-"))
        {
            holder.iv.setImageResource(R.drawable.onegativecolor);
        }
        else if(user.getBloodgroup().equals("A-"))
        {
            holder.iv.setImageResource(R.drawable.anegativecolor);
        }
        else if (user.getBloodgroup().equals("B"))
        {
            holder.iv.setImageResource(R.drawable.bcolor);
        }

holder.phone.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:"+user.getPhoneno()));
        context.startActivity(intent);
    }
});
       holder.msg.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent sendIntent = new Intent(Intent.ACTION_VIEW);
               sendIntent.setData(Uri.parse("sms:"+user.getPhoneno()));
               context.startActivity(sendIntent);
           }
       });
    }

    @Override
    public int getItemCount() {
        return userlist.size();
    }



    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView city,name;
        ImageView iv;
        Button phone,msg;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            city = itemView.findViewById(R.id.lcity);
            name = itemView.findViewById(R.id.lname);
            iv = itemView.findViewById(R.id.listimage);
            phone = itemView.findViewById(R.id.btnphone);
            msg = itemView.findViewById(R.id.btnmsg);
        }
    }
}
