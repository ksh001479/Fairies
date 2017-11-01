package com.example.jws.fairies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class dramaList extends AppCompatActivity implements AdapterView.OnItemClickListener {

    public String[] arrlist = {
            "도깨비 하이라이트1",
            "도깨비 하이라이트2",
            "그녀는 예뻤다 하이라이트1"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drama_list);

        ArrayAdapter<String> Adapter;
        Adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrlist);

        ListView list = (ListView)findViewById(R.id.dramalist);
        list.setAdapter(Adapter);

        list.setOnItemClickListener((AdapterView.OnItemClickListener) this);
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        String c_list = arrlist[i];
        Intent intent = null;
        switch(i)
        {
            case 0 :
                intent = new Intent(dramaList.this, dramaPage.class);
                break;
            case 1 :
                intent = new Intent(dramaList.this, dramaPage2.class);
                break;
            case 2 :
                intent = new Intent(dramaList.this, dramaPage3.class);
                break;
            default:
                Toast.makeText(this, "영상이 비었습니다.", Toast.LENGTH_LONG).show();

        }
        if(intent != null)
            startActivity(intent);
    }
}
