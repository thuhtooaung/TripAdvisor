package go.tripadvisor.com.tripadvisor;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
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

public class tab2 extends Fragment {
    String ans;
    DatabaseReference mref=FirebaseDatabase.getInstance().getReference();
    DatabaseReference myRef=mref.child("ann");
    TextView tv;
    TextView tvconnect;
    ImageView iv;
    SanZtoU sanZtoU=new SanZtoU(getActivity());
    TextView tit;
    String title;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.tab2,null);
        tv=v.findViewById(R.id.annmes);
        iv=v.findViewById(R.id.announceIv);
        tvconnect=v.findViewById(R.id.rm);
        tit=v.findViewById(R.id.annTitle);
        title="TripAdvisorဆိုသည္မွာ...";

        tvconnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent(getContext(),readmore.class);
                startActivity(it);
            }
        });
        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        SharedPreferences shp=getContext().getSharedPreferences("shp",Context.MODE_PRIVATE);
        final String font=shp.getString("font","z");
        //realtime db
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ans=dataSnapshot.getValue(String.class);
                if (ans!=null){
                    StringTokenizer st=new StringTokenizer(ans,"*");
                    ArrayList<String> arr=new ArrayList<>();
                    while (st.hasMoreTokens()){
                        arr.add(st.nextToken());
                    }
                    if (font.equals("u")){
                        Picasso.with(getContext())
                                .load(Uri.parse(arr.get(0)))
                                .into(iv);
                        tit.setText((String) sanZtoU.ZawGyiToUni(arr.get(1),true));
                        tv.setText((String)sanZtoU.ZawGyiToUni(arr.get(2),true));
                    }
                    else {
                        Picasso.with(getContext())
                                .load(Uri.parse(arr.get(0)))
                                .into(iv);
                        tit.setText(arr.get(1));
                        tv.setText(arr.get(2));
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        //end of realtime db
    }
}
