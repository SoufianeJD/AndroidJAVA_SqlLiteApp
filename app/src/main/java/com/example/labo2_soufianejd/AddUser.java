package com.example.labo2_soufianejd;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddUser extends AppCompatActivity {

    private EditText name;
    private EditText lastname;
    private EditText password;
    private EditText balance;
    private EditText credit;
    private Button button;
    private ClassUsers c;
    private MyAdapter m;
    String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);


        name = findViewById(R.id.name);
        lastname = findViewById(R.id.lastname);
        password = findViewById(R.id.password);
        balance = findViewById(R.id.balance);
        credit= findViewById(R.id.credit);
        button= findViewById(R.id.button);
        m=new MyAdapter(this);
        m.open();

        Bundle   b = getIntent().getExtras();
        type=b.getString("name");

        if(!type.contains("non")){

            ClassUsers c=m.getclient(type);
            name.setText(c.name);
            lastname.setText(c.lastname);
            password.setText(c.password);
            balance.setText(Double.toString(c.balance));
            credit.setText(Double.toString(c.credit));
            button.setText("UPDATE");
            setTitle("Update-User");

        }
        else{
            button.setText("ADD-User");
            setTitle("Add-User");
        }

    }

    public void done(View view){
        c=new ClassUsers(String.valueOf(name.getText()),String.valueOf(lastname.getText()),String.valueOf(password.getText()),Double.valueOf(String.valueOf(balance.getText())),Double.valueOf(String.valueOf((credit.getText()))));

        if(type.contains("non")) {


            m.insertclient(c.name, c.lastname, c.password, c.balance, c.credit);
            Toast.makeText(getApplicationContext(),"User added successfully !",Toast.LENGTH_LONG).show();
        }
        else{
            m.updateone(c);
            Toast.makeText(getApplicationContext(),"User updated successfully !",Toast.LENGTH_LONG).show();
        }


    }

}