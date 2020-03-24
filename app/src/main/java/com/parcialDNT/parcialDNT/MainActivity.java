package com.parcialDNT.parcialDNT;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.parcialDNT.parcialandroid.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private ListView listView;
    private adaptador adapter;
    private List<Contacto> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        initListener();
    }

    private void initViews(){
        //initToolbar

        //initListView
        list = new ArrayList<>();
        listView = findViewById(R.id.list);
        registerForContextMenu(listView);
        adapter = new adaptador(this, list);
        listView.setAdapter(adapter);

    }

    private void initListener(){
        findViewById(R.id.add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, activity_agregar.class);
                startActivityForResult(intent, 450);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 450) {
            if (resultCode == RESULT_OK) {
                list.add((Contacto) data.getParcelableExtra("newPerson"));
                adapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_contextual, menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_lista_personas, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_ordenar_nom:
                Collections.sort(list, new Comparator<Contacto>() {
                    public int compare(Contacto obj1, Contacto obj2) {
                        return obj1.getName().compareTo(obj2.getName());
                    }
                });
                adapter.notifyDataSetChanged();
                return true;
            case R.id.menu_ordenar_grupo:
                Collections.sort(list, new Comparator<Contacto>() {
                    public int compare(Contacto obj1, Contacto obj2) {
                        return obj1.getGroup().compareTo(obj2.getGroup());
                    }
                });
                adapter.notifyDataSetChanged();
                return true;
            case R.id.menu_eliminar:
                list.clear();
                adapter.notifyDataSetChanged();
                return true;
            case R.id.menu_invertir:
                Collections.reverse(list);
                adapter.notifyDataSetChanged();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int index = info.position;

        switch (item.getItemId()){
            case R.id.phone:
                list.get(index).setPhone(getNumberRandom());
                adapter.notifyDataSetChanged();
                return true;
            case R.id.menu_convertir:
                list.get(index).setName(list.get(index).getName().toUpperCase());
                adapter.notifyDataSetChanged();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    private String getNumberRandom() {
        String phone = "";
        switch (getNumRandom(0,2)){
            case 0:
                phone = "300";
            for (int i=0; i<7; i++){
                phone +=""+getNumRandom(0,9);
            }
            return phone;
            case 1:
                phone = "310";
                for (int i=0; i<7; i++){
                    phone +=""+getNumRandom(0,9);
                }
                return phone;
            case 2:
                phone = "320";
                for (int i=0; i<7; i++){
                    phone +=""+getNumRandom(0,9);
                }
                return phone;
            default:
                return "No number";
        }
    }

    private int getNumRandom(int min, int max){
        int range = (max - min) + 1;
        return (int)(Math.random() * range) + min;
    }
}
