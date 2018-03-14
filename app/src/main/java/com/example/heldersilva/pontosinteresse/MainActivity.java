package com.example.heldersilva.pontosinteresse;

import android.app.Dialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private static final String TAG ="MainActivity";

    private static final int ERROR_DIALOG_REQUEST=9001;

    protected Button btnMap;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (isServiceOK()){
            init();
        }
    }
    private void init(){
        btnMap = (Button) findViewById(R.id.btnMap);
        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent map = new Intent(MainActivity.this,MapActivity.class);
                startActivity(map);
            }
        });
    }

    public boolean isServiceOK(){
        Log.d(TAG,"isServicesOK: verificaçao do google services version");

        int availabel = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(MainActivity.this);

        if(availabel == ConnectionResult.SUCCESS){
            Log.d(TAG,"isServicesOK: Google play serveices is working");
            return true;
        }
        else if(GoogleApiAvailability.getInstance().isUserResolvableError(availabel)){
            Log.d(TAG,"isServicesOK: an error occured but we can fix it");
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(MainActivity.this,availabel,ERROR_DIALOG_REQUEST);
            dialog.show();

        }else {
            Toast.makeText(this,"nao é possibel fazer o request do map",Toast.LENGTH_LONG).show();
        }
        return false;
    }


}
