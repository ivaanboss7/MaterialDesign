package com.bosnjakovic.ivan.materialdesign;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText etAddItem;
    private ImageButton fabAdd;
    private ListView lvItems;
    private ArrayList<String> list;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initWidgets();
        fillList();

    }

    private void setUpListeners() {
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String item = etAddItem.getText().toString();
                if (item.length()>0){
                    list.add(item);
                    adapter.notifyDataSetChanged();
                    etAddItem.setText("");
                }else {
                    Toast.makeText(MainActivity.this, "Add something", Toast.LENGTH_SHORT).show();
                    etAddItem.requestFocus();
                }
            }
        });
        lvItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                list.remove(position);
                adapter.notifyDataSetChanged();
                return true;
            }
        });
    }

    private void initWidgets() {
        etAddItem = (EditText) findViewById(R.id.etItem);
        fabAdd = (ImageButton) findViewById(R.id.fabAdd);
        lvItems = (ListView) findViewById(R.id.lvItem);
    }
    private void fillList() {
        list = new ArrayList<String>();
        list.add("One");
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list);
        lvItems.setAdapter(adapter);
        setUpListeners();
    }

}
