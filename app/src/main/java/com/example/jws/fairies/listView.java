package com.example.jws.fairies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class listView extends AppCompatActivity implements AdapterView.OnItemClickListener {

    public String[] arrlist = {
            "1",
            "2",
            "3",
            "4"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        ArrayAdapter<String> Adapter;
        Adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrlist);

        ListView list = (ListView)findViewById(R.id.list);
        list.setAdapter(Adapter);

        list.setOnItemClickListener((AdapterView.OnItemClickListener) this);
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        String c_list = arrlist[i];
        Intent intent = null;
        switch(i)
        {
            case 1 :
                intent = new Intent(listView.this, dramaPage.class);
                break;
            case 2 :
                intent = new Intent(listView.this, dramaPage.class);
                break;
            case 3 :
                intent = new Intent(listView.this, dramaPage.class);
                break;
            case 4 :
                intent = new Intent(listView.this, dramaPage.class);
                break;
        }

        startActivity(intent);
    }
}