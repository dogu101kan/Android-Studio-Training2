package com.example.lisansmastercalisma;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class lisans extends AppCompatActivity implements View.OnClickListener {

    ListView lv;
    Button geri;

    ArrayList<ogrenciler> ogrenciler = new ArrayList<ogrenciler>();
    ArrayList<String> ogrencilers = new ArrayList<String>();

    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lisans);

        lv = findViewById(R.id.lv);
        geri = findViewById(R.id.gerigitlisans);

        ogrenciler = (ArrayList<ogrenciler>) getIntent().getSerializableExtra("LisansAl");

        for (int i = 0; i<ogrenciler.size(); i++){
            ogrencilers.add(ogrenciler.get(i).String());
        }

        adapter=new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, ogrencilers);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                ogrencilers.remove(i);
                adapter.notifyDataSetChanged();

                FileOutputStream yenidosya= null;
                try {
                    yenidosya = openFileOutput("lisans.txt", Context.MODE_PRIVATE);
                    for(int k=0;k<ogrencilers.size();k++) {
                        String yeni=ogrencilers.get(k)+"\n";
                        yenidosya.write(yeni.getBytes());
                    }
                    yenidosya.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

        geri.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent anaaktivite=new Intent(lisans.this,MainActivity.class);
        startActivity(anaaktivite);
    }
}