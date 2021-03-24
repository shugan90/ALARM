package com.example.shugan.myapplicationalarm;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.AbstractCursor;
import android.database.Cursor;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;




public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private Context mcontext;
    private Cursor mcursor;
    private Activity activity;


    MyDatabaseHelper myDB;
    public RecyclerViewAdapter(Context context,Cursor cursor) {
        mcontext = context;
        mcursor=cursor;


    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mcontext);
        View view = inflater.inflate(R.layout.rec, parent, false);
        return new MyViewHolder(view);


    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        if (!mcursor.moveToPosition(position)) {
            return;
        }
        String alartimes = mcursor.getString(mcursor.getColumnIndex(Datamodel.DataEntry.COLUMN_TITLE));
        String alarmnames = mcursor.getString(mcursor.getColumnIndex(Datamodel.DataEntry.COLUMN_AUTHOR));
        long id = (mcursor.getLong(mcursor.getColumnIndex(Datamodel.DataEntry.COLUMN_ID)));
        // holder.alarmid.setTag(alarmids);
        holder.alarmtime.setText(alartimes);
        holder.alarmname.setText(alarmnames);
        holder.itemView.setTag(id);

    }


    class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView  alarmtime, alarmname;
        LinearLayout mainLayout;
        Button del;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);

            alarmtime = itemView.findViewById(R.id.alarmtime);
            alarmname = itemView.findViewById(R.id.alarmname);
            del = itemView.findViewById(R.id.del);

            mainLayout = itemView.findViewById(R.id.mainLayout);
            //Animate Recyclerview
            Animation translate_anim = AnimationUtils.loadAnimation(mcontext, R.anim.translate_anim);
            mainLayout.setAnimation(translate_anim);


        }
    }
    @Override
    public int getItemCount() {
        return mcursor.getCount();
    }
    public void swapCursor(Cursor newCursor) {
        if (mcursor != null) {
            mcursor.close();
        }
        mcursor = newCursor;
        if (newCursor != null) {
            notifyDataSetChanged();
        }
    }
}
