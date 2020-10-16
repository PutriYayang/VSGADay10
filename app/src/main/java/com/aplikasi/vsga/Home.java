package com.aplikasi.vsga;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void Puzzle(View view) {
        startActivity(new Intent(this, MainActivity.class));
    }

    public void InputNama(View view) {
        startActivity(new Intent(this, InputNama.class));
    }

    public void Kalkulator(View view) {
        startActivity(new Intent(this, Kalkulator.class));
    }

    public void ListView(View view) {
        startActivity(new Intent(this, AplikasiListView.class));
    }

    public void InternalStorage(View view) {
        startActivity(new Intent(this, InternalStorage.class));
    }

    public void HalamanMain(View view) {
        startActivity(new Intent(this, HalamanMain.class));
    }

    public void MyLibrary(View view) {
        startActivity(new Intent(this, MyLibrary.class));
    }

    public void Day11(View view) {
        startActivity(new Intent(this, AplikasiSQLite.class));
    }
}
