package com.example.labo2_soufianejd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ListUsers extends AppCompatActivity {

    public ArrayList<String> list ;
    public ArrayAdapter<String> listAdapter;
    private ListView list1;
    public ArrayList<ClassUsers> cl ;
    MyAdapter m;
    String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_users);

        m=new MyAdapter(this);
        Bundle   b = getIntent().getExtras();
        type=b.getString("name");

        list = new ArrayList<String>();
        listAdapter= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list);

        list1 = (ListView)findViewById(R.id.list);
        list1.setAdapter(listAdapter);
        m.open();

        if(type.contains("non")){
            cl=m.select();
            setTitle("All Users-Infos");
        }

        else{
            cl=m.selectpositif();
            setTitle("Clients with GOOD balance sheet");
        }

        for (ClassUsers c:  cl ) {
            list.add(c.name);

        }





        list1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent Facteur = new Intent(ListUsers.this,AddUser.class);
                Facteur.putExtra("name",list.get(position));
                startActivity(Facteur);

            }
        });





    }
}
