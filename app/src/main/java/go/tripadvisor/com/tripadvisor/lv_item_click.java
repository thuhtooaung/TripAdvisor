package go.tripadvisor.com.tripadvisor;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;
import java.util.StringTokenizer;

import san.zgyi.uni.SanZtoU;

public class lv_item_click extends AppCompatActivity {
    String mtv;
    String s;
    int t;
    String x;
    String[] all_item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lv_item_click);
        TextView title=findViewById(R.id.content_tv);

        //Get textview
        final TextView tv1=findViewById(R.id.part1);
        final TextView tv2=findViewById(R.id.part2);
        final TextView tv3=findViewById(R.id.part3);
        final TextView tv4=findViewById(R.id.part4);
        final TextView tv5=findViewById(R.id.part5);

        //Get image view
        final ImageView iv1=findViewById(R.id.iv1);
        ImageView iv2=findViewById(R.id.iv2);
        ImageView iv3=findViewById(R.id.iv3);
        ImageView iv4=findViewById(R.id.iv4);
        ImageView iv5=findViewById(R.id.iv5);

        //Z to U
        final SanZtoU sanZtoU=new SanZtoU(this);

        final ImageView[] iv=new ImageView[]{iv1,iv2,iv3,iv4,iv5};
        SharedPreferences preferences=getSharedPreferences("shp",MODE_PRIVATE);
        String pre=preferences.getString("all","error");
        final String font=preferences.getString("font","z");
        StringTokenizer sall=new StringTokenizer(pre,",");
        all_item=new String[20];
        int all_count=0;
        while (sall.hasMoreTokens()){
            all_item[all_count]=sall.nextToken();
            all_count++;
        }

        //int z=getIntent().getIntExtra("p",-1);
        //s=all_item[z+1]
        s=getIntent().getStringExtra("data");
        t=getIntent().getIntExtra("po",-1);
        x=all_item[t+1];
        /*if (font.equals("u")){
            title.setText(sanZtoU.ZawGyiToUni(s,false));
        }
        else {
            title.setText(s);
        } */
        title.setText(s);


        DatabaseReference mref=FirebaseDatabase.getInstance().getReference();
        DatabaseReference myRef=mref.child(x);

        //arry
        final String[] all=new String[10];

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mtv=dataSnapshot.getValue(String.class);
                if (mtv!=null){
                    if (font.equals("u")){
                        String uni=(String) sanZtoU.ZawGyiToUni(mtv,true);
                        int tvcount=0;
                        StringTokenizer stringTokenizer=new StringTokenizer(uni,"*");
                        while(stringTokenizer.hasMoreTokens()){
                            all[tvcount]=stringTokenizer.nextToken();
                            tvcount++;
                        }
                    }else {
                        int tvcount=0;
                        StringTokenizer stringTokenizer=new StringTokenizer(mtv,"*");
                        while(stringTokenizer.hasMoreTokens()){
                            all[tvcount]=stringTokenizer.nextToken();
                            tvcount++;
                        }
                    }
                    //set to textview
                    tv1.setText(all[5]);
                    tv2.setText(all[6]);
                    tv3.setText(all[7]);
                    tv4.setText(all[8]);
                    tv5.setText(all[9]);
                    for(int pi=0;pi<5;pi++){
                        Picasso.with(getApplicationContext())
                                .load(all[pi])
                                .into(iv[pi]);
                    }
                }
                else {
                    recreate();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }
}
