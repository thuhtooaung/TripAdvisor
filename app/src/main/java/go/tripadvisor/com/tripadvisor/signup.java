package go.tripadvisor.com.tripadvisor;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class signup extends AppCompatActivity {

    String ema;
    String pass;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        TextView tv=findViewById(R.id.signuptv);
        Button btn=findViewById(R.id.singupbtn);
        final EditText email=findViewById(R.id.mailsign);
        final EditText password=findViewById(R.id.passsign);
        final ProgressBar pb=findViewById(R.id.signuppb);

        //initializing firebase
        firebaseAuth=FirebaseAuth.getInstance();

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent(getApplicationContext(),account.class);
                startActivity(it);
                signup.this.finish();
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ema=email.getText().toString().trim();
                pass=password.getText().toString().trim();

                Toast.makeText(getApplicationContext(),"sign up",Toast.LENGTH_SHORT).show();
                if (TextUtils.isEmpty(ema)){
                    Toast.makeText(getApplicationContext(),"Please enter email!",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password.getText().toString().trim())) {
                    Toast.makeText(getApplicationContext(),"Please enter password!",Toast.LENGTH_SHORT).show();
                    return;
                }

                pb.setVisibility(View.VISIBLE);
                firebaseAuth.createUserWithEmailAndPassword(ema,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            //firebase get user
                            FirebaseUser user=firebaseAuth.getCurrentUser();
                            user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        AlertDialog ad = new AlertDialog.Builder(signup.this).create();
                                        ad.setTitle("Verify Email");
                                        ad.setMessage("Verifcation email sent to your email.Please Check your email inbox or spam box " +
                                                "and Verify your email.After verified,Please log in.");
                                        ad.setButton(ad.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                dialog.dismiss();
                                                startActivity(new Intent(signup.this, emailverify.class));
                                                finish();
                                            }
                                        });
                                        ad.setButton(ad.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                dialog.dismiss();
                                            }
                                        });
                                        //stop progress
                                        pb.setVisibility(View.GONE);
                                        Toast.makeText(getApplicationContext(),"Suceess",Toast.LENGTH_SHORT).show();
                                        ad.show();
                                    }
                                }
                            });
                            Toast.makeText(getApplicationContext(),"success",Toast.LENGTH_SHORT).show();

                        }
                        else {
                            Toast.makeText(getApplicationContext(),"Failed to create account",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}
