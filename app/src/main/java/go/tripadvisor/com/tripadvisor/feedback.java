package go.tripadvisor.com.tripadvisor;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class feedback extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feedback);

        Button sendbtn=findViewById(R.id.fbsend);
        final EditText edifb=findViewById(R.id.edifb);

        FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
        FirebaseUser user=firebaseAuth.getCurrentUser();
        if(user==null){
            AlertDialog ad = new AlertDialog.Builder(feedback.this).create();
            ad.setTitle("No Account");
            ad.setMessage("Please create account...");
            ad.setButton(ad.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    startActivity(new Intent(feedback.this, account.class));
                    finish();
                }
            });
            ad.show();
        }

        sendbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text=edifb.getText().toString().trim();
                if (TextUtils.isEmpty(text)){
                    Toast.makeText(getApplicationContext(),"Please write your feedback message",Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent mail=new Intent(Intent.ACTION_SEND);
                    String tos="tripadvisor.ta@gmail.com";
                    mail.putExtra(Intent.EXTRA_EMAIL,new String[]{tos});
                    mail.putExtra(Intent.EXTRA_SUBJECT,"FEEDBACK");
                    mail.putExtra(Intent.EXTRA_TEXT,text);

                    mail.setType("message/rfc822");
                    startActivity(Intent.createChooser(mail,"Choose gmail app to send feedback"));
                }
            }
        });

    }
}
