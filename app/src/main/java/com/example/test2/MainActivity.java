package com.example.test2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.text.ParseException;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Adapter adapter;
    RecyclerView rvSach;
    Database database;
    List<Sach> sachList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvSach = findViewById(R.id.rvSach);

        database = new Database(this);
        sachList = database.getAll();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        adapter = new Adapter(this,sachList);

        rvSach.setLayoutManager(layoutManager);
        rvSach.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.item_them){
            startActivity(new Intent(MainActivity.this,ThemActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        sachList.clear();
        adapter.setDataChange(sachList);
        super.onResume();
    }
}