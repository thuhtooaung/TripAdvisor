package go.tripadvisor.com.tripadvisor;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class aboutus extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutus);

        LinearLayout hplus=findViewById(R.id.leader);
        LinearLayout mkh=findViewById(R.id.minkhanthein);
        LinearLayout karoo=findViewById(R.id.karoo);
        LinearLayout saw=findViewById(R.id.sawgay);
        LinearLayout tha=findViewById(R.id.tha);
        RelativeLayout page=findViewById(R.id.page);

        final String hn="https://www.facebook.com/htetthu.ya.56863";
        final String hid="htetthu.ya.56863";
        final String mn="https://www.facebook.com/minkhant.hein.1000";
        final String mid="minkhant.hein.1000";
        final String an="https://www.facebook.com/karr.oo.39";
        final String aid="karr.oo.39";
        final String sn="https://www.facebook.com/saw.gay.58173";
        final String sid="saw.gay.58173";
        final String tn="https://www.facebook.com/Software.Developer.thuhtooaung";
        final String tid="Software.Developer.thuhtooaung";
        final String page_name="https://www.facebook.com/Trip-Advisor-515058869316396/";
        final String page_id="Trip Advisor";

        hplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent facebookIntent=new Intent(Intent.ACTION_VIEW);
                String fburl=getFacebookPageURL(aboutus.this,hn,hid);
                facebookIntent.setData(Uri.parse(fburl));
                startActivity(facebookIntent);
            }
        });

        mkh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent facebookIntent=new Intent(Intent.ACTION_VIEW);
                String fburl=getFacebookPageURL(aboutus.this,mn,mid);
                facebookIntent.setData(Uri.parse(fburl));
                startActivity(facebookIntent);
            }
        });

        karoo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent facebookIntent=new Intent(Intent.ACTION_VIEW);
                String fburl=getFacebookPageURL(aboutus.this,an,aid);
                facebookIntent.setData(Uri.parse(fburl));
                startActivity(facebookIntent);
            }
        });

        saw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent facebookIntent=new Intent(Intent.ACTION_VIEW);
                String fburl=getFacebookPageURL(aboutus.this,sn,sid);
                facebookIntent.setData(Uri.parse(fburl));
                startActivity(facebookIntent);
            }
        });

        tha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent facebookIntent=new Intent(Intent.ACTION_VIEW);
                String fburl=getFacebookPageURL(aboutus.this,tn,tid);
                facebookIntent.setData(Uri.parse(fburl));
                startActivity(facebookIntent);
            }
        });

        page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent facebookIntent=new Intent(Intent.ACTION_VIEW);
                String fburl=getFacebookPageURL(aboutus.this,page_name,page_id);
                facebookIntent.setData(Uri.parse(fburl));
                startActivity(facebookIntent);
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

}
