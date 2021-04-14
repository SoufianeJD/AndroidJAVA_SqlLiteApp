package com.example.labo2_soufianejd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.item2:
               ajouter();

                return true;

            case R.id.item3:
getlist();
                return true;

            case R.id.item4:
getlist2();
                return true;

            case R.id.item5:
               getlista();
                return true;




        }
        return super.onOptionsItemSelected(item);
    }


    public void getlist(){


        Intent Facteur = new Intent(this,ListUsers.class);
        Facteur.putExtra("name","non");
        startActivity(Facteur);
    }

    public void getlist2(){


        Intent Facteur = new Intent(this,ListUsers2.class);
        Facteur.putExtra("name","non");
        startActivity(Facteur);
    }


    public void getlista(){


        Intent Facteur = new Intent(this,ListUsers.class);
        Facteur.putExtra("name","yes");
        startActivity(Facteur);
    }

    public void ajouter(){


        Intent Facteur = new Intent(this,AddUser.class);
        Facteur.putExtra("name","non");
        startActivity(Facteur);
    }
}

