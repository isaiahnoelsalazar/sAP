package com.salazarisaiahnoel.sap;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class MainActivity extends AppCompatActivity implements sAPModuleAdapter.OnItemClickListener {

    Map<String, String> modules = new LinkedHashMap<>();
    List<String> list = new ArrayList<>();
    List<String> packageList = new ArrayList<>();

    TextView t1;

    RecyclerView rv;
    LinearLayoutManager llm;
    sAPModuleAdapter sma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        t1 = findViewById(R.id.thint);

        rv = findViewById(R.id.rv);
        llm = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(llm);

        checkModules();

        rv.addItemDecoration(new RecyclerViewVerticalSpace(8));
    }

    @Override
    protected void onResume() {
        super.onResume();

        checkModules();
    }

    void setModules(){
        PackageManager packageManager = getPackageManager();
        List<ApplicationInfo> packages = packageManager.getInstalledApplications(PackageManager.GET_META_DATA);

        for (ApplicationInfo info : packages) {
            if (info.packageName.contains("salazarisaiahnoel")) {
                CharSequence label = info.loadLabel(packageManager);
                if (label.toString().contains("sAP: ")) {
                    modules.put(label.toString().replace("sAP: ", ""), info.packageName);
                }
            }
        }
    }

    void checkModules(){
        list.clear();
        packageList.clear();
        setModules();
        Map<String, String> sortedModules = new TreeMap<>(modules);

        boolean modulesInstalled = false;

        for (Map.Entry<String, String> e : sortedModules.entrySet()) {
            if (isAppInstalled(e.getValue())) {
                list.add(e.getKey());
                packageList.add(e.getValue());
                modulesInstalled = true;
            }
        }

        if (modulesInstalled) {
            t1.setText("");
        } else {
            t1.setText("No modules installed.");
        }

        sma = new sAPModuleAdapter(this, list, MainActivity.this);
        rv.setAdapter(sma);
    }

    boolean isAppInstalled(String packageName){
        try {
            getPackageManager().getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
            return true;
        } catch (Exception ignored){
        }
        return false;
    }

    @Override
    public void onItemClick(int position) {
        Intent i = new Intent();
        i.setComponent(new ComponentName(packageList.get(position), packageList.get(position) + ".MainActivity"));
        startActivity(i);
    }
}