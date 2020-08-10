package go.tripadvisor.com.tripadvisor;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.net.InetAddress;

public class splash extends AppCompatActivity {
    String str;
    DatabaseReference mref=FirebaseDatabase.getInstance().getReference();
    DatabaseReference myRef=mref.child("main");
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);
        TextView tv=findViewById(R.id.sptv);
        final Button spbtn=findViewById(R.id.spbtn);

        //realtime db
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                str=dataSnapshot.getValue(String.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        //end of realtime db

        //Shared preference
        SharedPreferences shp=getSharedPreferences("shp",MODE_PRIVATE);
        final SharedPreferences.Editor shpedi=shp.edit();

        if (isnetworkconnected()||isInternetAva()) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent it = new Intent(splash.this, ta_main.class);
                        if (str!=null){
                            shpedi.putString("server",str);
                            shpedi.apply();
                            splash.this.startActivity(it);
                            splash.this.finish();
                        }
                        else {
                            splash.this.recreate();
                        }
                    }
                }, 2000);
        }
        else {
            tv.setText("No internet connection....");
            spbtn.setVisibility(View.VISIBLE);
        }
        //btn click
        spbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                splash.this.recreate();
            }
        });
        //end of btn click
    }
   private boolean isnetworkconnected(){
        ConnectivityManager cm=(ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo()!=null;
    }
    public boolean isInternetAva(){
        try{
            final InetAddress address=InetAddress.getByName("www.google.com");
            return !address.equals("");
        }
        catch (Exception e){
            //log
        }
        return false;
    }

}
