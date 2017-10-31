package com.example.jws.fairies;

import android.Manifest;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.util.ArrayList;

public class firstMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_menu_page);

        PermissionListener permissionlistener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                Toast.makeText(getApplicationContext(), "권한이 정상적으로 허용되었습니다.", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPermissionDenied(ArrayList<String> deniedPermissions) {
               // Toast.makeText(getApplicationContext(), "" + deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
            }
        };

        TedPermission.with(this)
                .setPermissionListener(permissionlistener)
                .setDeniedMessage("설정에 들어가서 권한을 주세요")
                .setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO)
                .check();

        Button drama_button = (Button)findViewById(R.id.drama_button);
        drama_button.setOnClickListener(new Button.OnClickListener(){


            @Override
            public void onClick(View view) {
                Intent intent = new Intent(firstMenu.this, dramaList.class);
                startActivity(intent);
            }
        });

        Button moive_button = (Button)findViewById(R.id.movie_button);
        moive_button.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(firstMenu.this, movieList.class);
                startActivity(intent);
            }
        });

        Button search_button = (Button)findViewById(R.id.search_button);
        search_button.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(firstMenu.this, searchPage.class);
                startActivity(intent);
            }
        });

        Button education_button = (Button)findViewById(R.id.eductaion_button);
        education_button.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(firstMenu.this, educationPage.class);
                startActivity(intent);
            }
        });

        Button entertain_button = (Button)findViewById(R.id.entertain_button);
        entertain_button.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(firstMenu.this, entertainmentList.class);
                startActivity(intent);
            }
        });
    }
}
