package com.darshan.tutorial07;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class Welcome extends AppCompatActivity {

    TextView txtWelcomeMsg;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        txtWelcomeMsg = findViewById(R.id.txtWelcomeMsg);
        preferences = getSharedPreferences("session", MODE_PRIVATE);
        if(!preferences.getBoolean("LoggedIn",false)){
            Intent intent = new Intent(Welcome.this,Login.class);
            startActivity(intent);
            finish();
        }
        txtWelcomeMsg.setText("Welcome, "+preferences.getString("email",""));
        editor = preferences.edit();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.lgt_menu:
                editor.remove("email");
                editor.remove("LoggedIn");
                editor.commit();
                startActivity(new Intent(Welcome.this,Login.class));
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}