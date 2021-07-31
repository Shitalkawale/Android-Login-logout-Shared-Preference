package com.example.sqlitloginsystem;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import java.util.List;

public class Profile_fragment extends Fragment
{
    TextView nametxt,emailtxt,phonetext;

    List<User> userList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.profile_fragment,container,false);

        nametxt=view.findViewById(R.id.profilename);
        emailtxt=view.findViewById(R.id.profileemail);
        phonetext=view.findViewById(R.id.profilemobile);

        Bundle bundle=this.getArguments();
        String phone = null;
        if(getArguments()!=null)
        {
            phone=getArguments().getString("phone");

        }


        @SuppressLint("WrongConstant") SharedPreferences sharedPreferences=getActivity().getSharedPreferences("Myfile",Context.MODE_APPEND);
        String namee=sharedPreferences.getString("username","");
        String mobilee=sharedPreferences.getString("mobile","");
        String emaill=sharedPreferences.getString("email","");
        String password=sharedPreferences.getString("password","");

        SqliteDatabaseHelper sqliteDatabaseHelper=new SqliteDatabaseHelper(getContext());
        User user=new User();
        user.setPhone(phone);
        userList=sqliteDatabaseHelper.getUserDetails(user);

        for (User user1:userList)
        {
            nametxt.setText(user1.getUsername());
            emailtxt.setText(user1.getEmail());
            phonetext.setText(user1.getPhone());

        }

//        emailtxt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(Intent.ACTION_SEND);
//                intent.putExtra(Intent.EXTRA_EMAIL,new String[]{user.getEmail()});
//                intent.setType("message/rfc822");
//                startActivity(Intent.createChooser(intent,"Choose client"));
//            }
//        });
//
//        phonetext.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent calling=new Intent(Intent.ACTION_CALL);
//                calling.setData(Uri.parse("tel:"+phonetext.getText()));
//                startActivity(calling);
//
//                Toast.makeText(context,"You clicked on: "+phonetext.getText(),Toast.LENGTH_LONG).show();
//            }
//        });


        return view;
    }
}
