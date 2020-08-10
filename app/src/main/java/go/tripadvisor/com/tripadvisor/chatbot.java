package go.tripadvisor.com.tripadvisor;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;

import san.zgyi.uni.SanZtoU;

public class chatbot extends AppCompatActivity {
    String s;
    String mes1,mes2;
    String titl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatbot);
        final ListView lv=findViewById(R.id.lv);
        final EditText edi=findViewById(R.id.edi);
        ImageButton send=findViewById(R.id.send);
        Button connect=findViewById(R.id.connect);

        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent(Intent.ACTION_VIEW,Uri.parse("https://m.me/515058869316396"));
                startActivity(it);
            }
        });

        SanZtoU sanZtoU=new SanZtoU(this);
        SharedPreferences shp=getSharedPreferences("shp",MODE_PRIVATE);
        String text1="Chat Botအားအသုံးပြုလိုပါက App Botအားနှိပ်ပါ။";
        String text2="Adminနှင့်တိုက်ရိုက်ချိတ်ဆက်လိုပါကConnectအားနှိပ်ပါ။";
        String tit="မင်္ဂလာပါအသုံးပြုသူများခင်ဗျာ...";
        final String page_name="https://www.facebook.com/Trip-Advisor-515058869316396/";
        final String page_id="Trip Advisor";
        if (shp.getString("font","z").equals("u")){
            titl=tit;
            mes1=text1;
        }
        else {
            titl=(String) sanZtoU.ZawGyiToUni(tit,false);
            mes1=(String)sanZtoU.ZawGyiToUni(text1,false);
            mes2=(String)sanZtoU.ZawGyiToUni(text2,false);
        }
        final AlertDialog alertDialog=new AlertDialog.Builder(this).create();
        alertDialog.setTitle(titl);
        alertDialog.setMessage(mes1+"-"+mes2);
        alertDialog.setButton(alertDialog.BUTTON_POSITIVE, "App Bot", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                alertDialog.dismiss();
            }
        });
        alertDialog.setButton(alertDialog.BUTTON_NEGATIVE, "Connect", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent facebookIntent=new Intent(Intent.ACTION_VIEW);
                String fburl=getFacebookPageURL(chatbot.this,page_name,page_id);
                facebookIntent.setData(Uri.parse(fburl));
                startActivity(facebookIntent);
            }
        });
        alertDialog.show();
        final ArrayList<String> arr=new ArrayList<String>();
        final botadapter adapter=new botadapter(getApplicationContext(),arr);
        lv.setAdapter(adapter);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s=edi.getText().toString().trim();
                arr.add(s);
                adapter.notifyDataSetChanged();
                lv.smoothScrollToPosition(arr.size()-1);
                edi.setText("");
            }
        });
    }

    private String getFacebookPageURL(Context context, String name, String id) {
        PackageManager packageManager=context.getPackageManager();
        try {
            int versionCode=packageManager.getPackageInfo("com.facebook.katana",0).versionCode;
            if (versionCode>=3002850){
                return "fb://facewebmodal/f?href="+name;
            }
            else {
                return "fb://profile/"+id;
            }
        }
        catch (Exception e) {
            return name;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }
}
