package com.example.diary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        setSupportActionBar(findViewById(R.id.mp_toolbar));


//        findViewById(R.id.mp_btn_add_album).setOnClickListener(view -> {
//            Log.i("Album", "add button is clicked and fragment should begin");
//            FragmentTransaction replaceTransaction =getSupportFragmentManager().beginTransaction();
//            replaceTransaction.replace(R.id.mp_container, new PhotoFragment());
//            replaceTransaction.addToBackStack(null);
//            replaceTransaction.commit();
//        });

    }

}