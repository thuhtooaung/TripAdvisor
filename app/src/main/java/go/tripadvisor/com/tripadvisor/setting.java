package go.tripadvisor.com.tripadvisor;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class setting extends AppCompatActivity {

    ImageButton zi,ui;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);

        LinearLayout zawgyi=findViewById(R.id.zawgyi);
        LinearLayout unicode=findViewById(R.id.unicode);
        zi=findViewById(R.id.zaw);
        ui=findViewById(R.id.uni);

        SharedPreferences shpe=getSharedPreferences("shp",MODE_PRIVATE);

        String font=shpe.getString("font","z");
        if (font.equals("u")){
            ui.setVisibility(View.VISIBLE);
        }
        else {
            zi.setVisibility(View.VISIBLE);
        }
        zawgyi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ui.setVisibility(View.INVISIBLE);
                zi.setVisibility(View.VISIBLE);
                comfirm("Unicode to Zawgyi","z");
            }
        });

        unicode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                zi.setVisibility(View.INVISIBLE);
                ui.setVisibility(View.VISIBLE);
                comfirm("Zawgyi to Unicode","u");
            }
        });
    }

    private void fontChanger(String str) {
        SharedPreferences shpe=getSharedPreferences("shp",MODE_PRIVATE);
        SharedPreferences.Editor shp=shpe.edit();
        shp.putString("font",str);
        shp.apply();
    }

    private void comfirm(String s, final String f) {
        AlertDialog ad=new AlertDialog.Builder(this).create();
        ad.setTitle("! Font Change");
        ad.setMessage("Are you sure to change font "+s+". And you need to reopen the app.");
        ad.setButton(ad.BUTTON_POSITIVE, "Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (f.equals("u")){
                    fontChanger("u");
                }
                else {
                    fontChanger("z");
                }
                finish();
            }
        });
        ad.setButton(ad.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (f.equals("u")){
                    ui.setVisibility(View.INVISIBLE);
                    zi.setVisibility(View.VISIBLE);
                }
                else {
                    ui.setVisibility(View.VISIBLE);
                    zi.setVisibility(View.INVISIBLE);
                }
                dialogInterface.dismiss();
            }
        });
        ad.show();
    }



}
