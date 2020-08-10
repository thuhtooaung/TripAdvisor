package go.tripadvisor.com.tripadvisor;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.StringTokenizer;

import static android.content.Context.MODE_PRIVATE;

public class tab1 extends Fragment {
    CardView cv1,cv2,cv3;
    ProgressBar pb1,pb2,pb3;
    StringTokenizer st;
    //String[] site;
    String str1;
    String str2;
    String str3;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.tab1,null);
        cv1=(CardView) v.findViewById(R.id.historic);
        cv2=(CardView) v.findViewById(R.id.beaches);
        cv3=(CardView) v.findViewById(R.id.mountain);

        //progress bar
        pb1=v.findViewById(R.id.progress_bar_his);
        pb2=v.findViewById(R.id.progress_bar_bea);
        pb3=v.findViewById(R.id.progress_bar_mou);

        cv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getStr1();
                pb1.setVisibility(View.VISIBLE);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent in1=new Intent(getContext(),MainActivity.class);
                        if (str1!=null){
                            pb1.setVisibility(View.INVISIBLE);
                            in1.putExtra("pass",str1);
                            startActivity(in1);
                        }
                        else getStr1();
                    }
                },2000);

            }
        });
        cv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getStr2();
                pb2.setVisibility(View.VISIBLE);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent in1=new Intent(getContext(),MainActivity.class);
                        if (str2!=null){
                            pb2.setVisibility(View.INVISIBLE);
                            in1.putExtra("pass",str2);
                            startActivity(in1);
                        }
                        else getStr2();
                    }
                },2000);
            }
        });

        cv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getStr3();
                pb3.setVisibility(View.VISIBLE);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent in1=new Intent(getContext(),MainActivity.class);
                        if (str3!=null){
                            pb3.setVisibility(View.INVISIBLE);
                            in1.putExtra("pass",str3);
                            startActivity(in1);
                        }
                        else getStr3();
                    }
                },2000);
            }
        });
        return v;
    }
    public void getStr1(){
        DatabaseReference mref=FirebaseDatabase.getInstance().getReference();
        DatabaseReference myRef=mref.child("historic");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                str1=dataSnapshot.getValue(String.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void getStr2(){
        DatabaseReference mref=FirebaseDatabase.getInstance().getReference();
        DatabaseReference myRef=mref.child("beache");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                str2=dataSnapshot.getValue(String.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    public void getStr3(){
        DatabaseReference mref=FirebaseDatabase.getInstance().getReference();
        DatabaseReference myRef=mref.child("mountain");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                str3=dataSnapshot.getValue(String.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
