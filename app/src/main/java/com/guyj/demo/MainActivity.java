package com.guyj.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.guyj.rvgallery.RvGallery;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RvGallery gallery;
    Button btn;
    private List benas=new ArrayList();
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gallery=(RvGallery)findViewById(R.id.gallery);
        btn=(Button)findViewById(R.id.btn);

        initGallery();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < 10; i++) {
                    benas.add("");
                }
                adapter.setData(benas);
            }
        });

    }

    private void initGallery() {
        gallery.setAdapter(adapter=new MyAdapter(this));
        adapter.setData(benas);
        gallery.setOnItemSelectedListener(new RvGallery.OnItemSelectedListener() {
            @Override
            public void itemSelected(int position) {
                Toast.makeText(MainActivity.this, "index="+position, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
