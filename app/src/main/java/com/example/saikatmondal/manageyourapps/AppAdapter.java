package com.example.saikatmondal.manageyourapps;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class AppAdapter extends RecyclerView.Adapter<AppAdapter.AppViewHolder>{

    private List<AppInfo> appList;
    private Context context;

    public AppAdapter(List<AppInfo> appList, Context context) {
        this.appList = appList;
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return appList.size();
    }

    @Override
    public void onBindViewHolder(AppViewHolder appViewHolder, int i) {
        AppInfo appInfo = appList.get(i);
        appViewHolder.vName.setText(appInfo.getName());
        appViewHolder.vApk.setText(appInfo.getApk());
        appViewHolder.vIcon.setImageDrawable(appInfo.getIcon());

        setButtonEvents(appViewHolder,appInfo);
    }

    private void setButtonEvents(AppViewHolder appViewHolder,final AppInfo appInfo){

        Button appExtract = appViewHolder.vExtract;
        Button appShare = appViewHolder.vShare;
        final ImageView icon = appViewHolder.vIcon;
        final CardView cardView = appViewHolder.vCardView;

        appExtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        appShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    //@NonNull
    @Override
    public AppViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
         View appAdapterView =LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.app_list_layout,viewGroup,false);
         return new AppViewHolder(appAdapterView);
    }

    public static class AppViewHolder extends RecyclerView.ViewHolder{

        protected TextView vName;
        protected TextView vApk;
        protected ImageView vIcon;
        protected Button vExtract;
        protected Button vShare;
        protected CardView vCardView;
        public AppViewHolder( View view) {
            super(view);
            vName = view.findViewById(R.id.txtName);
            vApk = view.findViewById(R.id.txtApk);
            vIcon = view.findViewById(R.id.imgIcon);
            vExtract = view.findViewById(R.id.btnExtract);
            vShare = view.findViewById(R.id.btnShare);
            vCardView = view.findViewById(R.id.app_card);
        }
    }
}
