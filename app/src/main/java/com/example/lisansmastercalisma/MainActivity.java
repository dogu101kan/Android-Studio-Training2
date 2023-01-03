package com.example.lisansmastercalisma;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    String[] okullist = {"DAU", "Diğer"};

    EditText isim, no;
    Spinner okul;
    String okulString;
    RadioButton lisans, master;
    Button ekle, lisansagit, masteragit;
    FileOutputStream outputStream;
    FileOutputStream outputStream2;


    ArrayList<ogrenciler> lisansogrenci = new ArrayList<ogrenciler>();
    ArrayList<ogrenciler> masterogrenci = new ArrayList<ogrenciler>();
    ArrayList<String> masterogrencis = new ArrayList<String>();

    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        isim = findViewById(R.id.Isim);
        no = findViewById(R.id.No);
        lisans = findViewById(R.id.rblisans);
        master = findViewById(R.id.rbmaster);

        ekle = findViewById(R.id.ekle);
        lisansagit = findViewById(R.id.lisansagit);
        masteragit = findViewById(R.id.masteragit);

        okul = findViewById(R.id.spinner);

        adapter=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,okullist);
        okul.setAdapter(adapter);

        okul.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        okulString = okullist[0];
                        break;
                    case 1:
                        okulString = okullist[1];
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }

        });

        ekle.setOnClickListener(this);
        lisansagit.setOnClickListener(this);
        masteragit.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        try {
            outputStream = openFileOutput("lisans.txt", Context.MODE_APPEND);
        }catch (Exception e){
            System.out.println(e);
        }

        try {
            outputStream2 = openFileOutput("master.txt", Context.MODE_APPEND);
        }catch (Exception e){
            System.out.println(e);
        }

        if (view.getId() == ekle.getId()){
            if (isim.getText().toString().trim().length() == 0 || no.getText().toString().trim().length() == 0 ||
                    (!lisans.isChecked() && !master.isChecked())){

                Toast.makeText(this, "Eksik bilgi girdiniz", Toast.LENGTH_SHORT).show();

            }else{

                if (lisans.isChecked()){
                    ogrenciler yeni = new ogrenciler(isim.getText().toString(), no.getText().toString(), "Lisans", okulString);
                    lisansogrenci.add(yeni);

                    try {
                        outputStream.write((yeni.String() + "\n").getBytes());
                        outputStream.close();
                    }catch (IOException e){
                        e.printStackTrace();
                    }

                }else{
                    ogrenciler yeni = new ogrenciler(isim.getText().toString(), no.getText().toString(), "Master", okulString);
                    masterogrenci.add(yeni);

                    try {
                        outputStream2.write((yeni.String() + "\n").getBytes());
                        outputStream2.close();
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                }

            }
        }

        switch (view.getId()){

            case R.id.lisansagit:

                Intent lisansaktivitesi = new Intent(MainActivity.this, lisans.class);
                lisansaktivitesi.putExtra("LisansAl",  lisansogrenci);
                startActivity(lisansaktivitesi);

                break;

            case R.id.masteragit:

                Intent masteraktivitesi=new Intent(MainActivity.this,master.class);
                masteraktivitesi.putExtra("MasterAL",masterogrenci);
                startActivity(masteraktivitesi);

                break;
        }

    }
}

















