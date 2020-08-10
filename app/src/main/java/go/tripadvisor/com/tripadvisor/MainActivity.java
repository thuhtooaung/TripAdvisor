package go.tripadvisor.com.tripadvisor;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.view.Display;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.StringTokenizer;

import san.zgyi.uni.SanZtoU;

public class MainActivity extends AppCompatActivity{
    String fire;
    ArrayList<String> arrList;
    String fdata;
    String cnt;
    listadapter adapter;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Shared Preference
        SharedPreferences sh = getSharedPreferences("shp", MODE_PRIVATE);
        final String font=sh.getString("font","z");
        //Z to U
        SanZtoU sanZtoU=new SanZtoU(this);

        fire = getIntent().getStringExtra("pass");

        StringTokenizer st = new StringTokenizer(fire, ",");
        //to array
        arrList=new ArrayList<>();
        while (st.hasMoreTokens()) {
            fdata = st.nextToken();
            arrList.add(fdata);
        }
        //array copy
        String type=arrList.get(0);
        cnt = arrList.get(1);

        int mcnt = Integer.parseInt(cnt);
        int name_count = mcnt + 2;
        final String[] name = new String[mcnt];

        String name_str = "";
        if (font.equals("u")){
            for (int i = 2; i < name_count; i++) {

                name[i - 2] =(String) sanZtoU.ZawGyiToUni(arrList.get(i),true);
            }
        }
        else {
            for (int i = 2; i < name_count; i++) {
                name[i - 2] =arrList.get(i);
            }
        }
        for (int f = 0; f < name_count; f++) {
            name_str += arrList.get(f+1) + ",";
        }

        SharedPreferences.Editor shed = sh.edit();
        shed.putString("all", name_str);
        shed.apply();

        ArrayList<String> urlList=new ArrayList<>();
        //end of arraycopy

        //start of listview
        lv = findViewById(R.id.lv);
        adapter = new listadapter(this,name,type);
        lv.setAdapter(adapter);
        //end of listview

        //listview click
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it=new Intent(getApplicationContext(),lv_item_click.class);
                it.putExtra("data",name[i]);
                it.putExtra("po",i);
                startActivity(it);
            }
        });

        //Start of floating button
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inc = new Intent(MainActivity.this, chatbot.class);
                MainActivity.this.startActivity(inc);
            }
        });
        //End of floating button
    }
}
