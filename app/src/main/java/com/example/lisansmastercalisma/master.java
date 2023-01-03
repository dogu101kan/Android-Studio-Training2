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

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class master extends AppCompatActivity implements View.OnClickListener {

    ListView lv;
    Button geri;

    ArrayAdapter<String> adapter;
    ArrayList<String> ogrencilers = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master);

        geri = findViewById(R.id.gerigitmaster);
        lv = findViewById(R.id.lvmaster);

        try {
            String tumnotlar="";
            FileInputStream dosyaoku=openFileInput("master.txt");
            int size=dosyaoku.available();
            byte[] tumu=new byte[size];
            dosyaoku.read(tumu);
            dosyaoku.close();
            tumnotlar=new String(tumu);
            ogrencilers=new ArrayList<String>(Arrays.asList(tumnotlar.split("\n")));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
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
                    yenidosya = openFileOutput("master.txt", Context.MODE_PRIVATE);
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
        if(view.getId() == geri.getId()){
            Intent geri = new Intent(master.this, MainActivity.class);
            startActivity(geri);
        }
    }
}