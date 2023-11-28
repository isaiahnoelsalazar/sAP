package com.salazarisaiahnoel.sap;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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

        t1 = findViewById(R.id.thint);

        checkModules();

        rv = findViewById(R.id.rv);
        llm = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(llm);

        sma = new sAPModuleAdapter(this, list, MainActivity.this);
        rv.setAdapter(sma);
    }

    @Override
    protected void onResume() {
        super.onResume();

        checkModules();
    }

    void setModules(){
        modules.put("QR Generator", "com.salazarisaiahnoel.sapmodule_qrgn");
        modules.put("QR Scanner", "com.salazarisaiahnoel.sapmodule_qrsc");
    }

    void checkModules(){
        list.clear();
        packageList.clear();
        setModules();
        for (Map.Entry<String, String> e : modules.entrySet()){
            if (isAppInstalled(e.getValue())){
                t1.setText("");
                list.add(e.getKey());
                packageList.add(e.getValue());
            } else {
                t1.setText("No modules installed.");
            }
        }
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