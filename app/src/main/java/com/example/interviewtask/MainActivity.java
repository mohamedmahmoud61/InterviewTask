package com.example.interviewtask;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private LinearLayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        ArrayList<String> appNames = new ArrayList<>();

        PackageManager pm = getPackageManager();

        List<ApplicationInfo> packages = pm.getInstalledApplications(PackageManager.GET_META_DATA);



        for (ApplicationInfo packageInfo : packages) {
            if (!isSTOPPED(packageInfo) && !isSYSTEM(packageInfo)) {
                String appName =  getApplicationLabel(this, packageInfo.packageName)  ;
                        appNames.add(appName);
            }
        }
        mAdapter = new MyAdapter(this, appNames);
        recyclerView.setAdapter(mAdapter);

    }


    private  boolean isSYSTEM(ApplicationInfo pkgInfo) {

        return ((pkgInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0);
    }


    private  boolean isSTOPPED (ApplicationInfo pkgInfo){

        return ((pkgInfo.flags & ApplicationInfo.FLAG_STOPPED) != 0);
    }

    private  String getApplicationLabel(Context context, String packageName) {

        PackageManager        packageManager = context.getPackageManager();
        List<ApplicationInfo> packages       = packageManager.getInstalledApplications(PackageManager.GET_META_DATA);
        String                label          = null;

        for (int i = 0; i < packages.size(); i++) {

            ApplicationInfo temp = packages.get(i);

            if (temp.packageName.equals(packageName))
                label = packageManager.getApplicationLabel(temp).toString();
        }

        return label;
    }

}