package go.tripadvisor.com.tripadvisor;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.StringTokenizer;

import san.zgyi.uni.SanZtoU;

public class readmore extends AppCompatActivity {
    TextView tv;
    ImageView iv;
    DatabaseReference myRef=FirebaseDatabase.getInstance().getReference().child("ads");
    String rm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.readmore);
        tv=findViewById(R.id.rmdetail);
        iv=findViewById(R.id.readmoreIv);
    }

    SanZtoU sanZtoU=new SanZtoU(this);
    @Override
    protected void onStart() {
        super.onStart();
        SharedPreferences shp=getApplicationContext().getSharedPreferences("shp",Context.MODE_PRIVATE);
        final String font=shp.getString("font","z");
        //realtime db
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                rm=dataSnapshot.getValue(String.class);
                if (rm!=null){
                    StringTokenizer st=new StringTokenizer(rm,"*");
                    ArrayList<String> read=new ArrayList<>();
                    while (st.hasMoreTokens()){
                        read.add(st.nextToken());
                    }
                    if (font.equals("u")){
                        Picasso.with(getApplicationContext())
                                .load(Uri.parse(read.get(0)))
                                .into(iv);
                        tv.setText((String)sanZtoU.ZawGyiToUni(read.get(1),true));
                    }
                    else {
                        Picasso.with(getApplicationContext())
                                .load(Uri.parse(read.get(0)))
                                .into(iv);
                        tv.setText(read.get(1));
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        //end of realtime db
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }
}
