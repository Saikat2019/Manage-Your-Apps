package com.example.saikatmondal.manageyourapps;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
//TODO - done upto how to get applist , next have to work on how to show them in recycler view

    //general variables
    private List<AppInfo> appList;
    private List<AppInfo> appSystemList;
    private List<AppInfo> appHiddenList;

    private AppAdapter appAdapter;

    //configuration variables
    private Boolean doubleBackToExitDone = false;
    private Toolbar toolbar;
    private Activity activity; //TODO know more about activity and context
    private Context context;
    private RecyclerView recyclerView;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.activity = this;
        this.context = this;

//        setInitialConfiguration();
//        checkAndAddPermission(activity);
//        setAppDir();

        recyclerView = findViewById(R.id.appList);

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        new getInstalledApps().execute();

    }


    class getInstalledApps extends AsyncTask<Void,String ,Void>{   //TODO know more about AsyncTask
        private Integer totalApps;
        private Integer actualApps;

        public getInstalledApps(){
            actualApps = 0;

            appList = new ArrayList<>();
            appSystemList = new ArrayList<>();
            appHiddenList = new ArrayList<>();
        }

        @Override
        protected Void doInBackground(Void... voids) // variable number of arguments coz in background processing we
                                                     //don't know how many arguments it would take
        {
             final PackageManager packageManager = getPackageManager();
             List<PackageInfo> packages = packageManager.getInstalledPackages(PackageManager.GET_META_DATA);

             totalApps = packages.size();

            Collections.sort(packages, new Comparator<PackageInfo>() {
                @Override
                public int compare(PackageInfo p1, PackageInfo p2) {
                    return packageManager.getApplicationLabel(p1.applicationInfo).toString().toLowerCase().compareTo(packageManager.getApplicationLabel(p2.applicationInfo).toString().toLowerCase());
                }
            });

            for(PackageInfo packageInfo : packages){
                if(!packageManager.getApplicationLabel(packageInfo.applicationInfo).equals("")|| packageInfo.packageName.equals("")){
                    if((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM )==0){
                        try {
                            AppInfo tempApp = new AppInfo(packageManager.getApplicationLabel(packageInfo.applicationInfo).toString(),packageInfo.packageName,packageInfo.versionName,packageInfo.applicationInfo.sourceDir,packageInfo.applicationInfo.dataDir,packageManager.getApplicationIcon(packageInfo.applicationInfo),false);
                            appList.add(tempApp);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }
                actualApps++;
                publishProgress(Double.toString((actualApps * 100) / totalApps));
            }

             return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            appAdapter = new AppAdapter(appList,context);

            recyclerView.setAdapter(appAdapter);

        }
    }


}

