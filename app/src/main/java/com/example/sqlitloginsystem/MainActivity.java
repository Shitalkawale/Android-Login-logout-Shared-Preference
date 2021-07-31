package com.example.sqlitloginsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText userNameedt,passwordedt;
    SqliteDatabaseHelper sqliteDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sqliteDatabaseHelper=new SqliteDatabaseHelper(this);
        userNameedt=findViewById(R.id.loginmobile);
        passwordedt=findViewById(R.id.loginpassword);

    }

    public void register(View view) {
        startActivity(new Intent(MainActivity.this,RegisterPage.class));
    }

    public void gotohomepage(View view) {

        User user=new User();
        user.setPhone(userNameedt.getText().toString());
        user.setPassword(passwordedt.getText().toString());

        boolean status=sqliteDatabaseHelper.loginUser(user);
        Log.d("Status",String.valueOf(status));
        if(status)
        {
            SharedPreferences sharedPreferences=getSharedPreferences("Myfile",MODE_PRIVATE);
            SharedPreferences.Editor editor=sharedPreferences.edit();
            editor.putString("LOGEDIN","1");
            editor.putString("username",user.getUsername());
            editor.putString("mobile",user.getPhone());
            editor.putString("email",user.getEmail());
            //  editor.putString("password",user.getPassword());

            editor.commit();
            Intent intent=new Intent(MainActivity.this,HomePage.class);
            intent.putExtra("userPhone",userNameedt.getText().toString());
            startActivity(intent);
        }
        else
        {
            Toast.makeText(MainActivity.this,"please enter valid username or password",Toast.LENGTH_LONG).show();
        }
    }
}