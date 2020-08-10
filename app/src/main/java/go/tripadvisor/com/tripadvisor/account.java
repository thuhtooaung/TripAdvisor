package go.tripadvisor.com.tripadvisor;

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

public class account extends AppCompatActivity {
    String em;
    String pwd;
    FirebaseUser firebaseUser;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account);
        TextView tv=findViewById(R.id.acctv);
        Button login=findViewById(R.id.loginbtn);
        final EditText email=findViewById(R.id.email);
        final EditText password=findViewById(R.id.password);
        final ProgressBar pb=findViewById(R.id.accpb);

        //firebase authenticate
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser= firebaseAuth.getCurrentUser();

        //show profile if user is logged in or current user
        if (firebaseUser != null) {
            if (firebaseUser.isEmailVerified()) {
                startActivity(new Intent(account.this, success.class));
                finish();
            } else {
                Toast.makeText(getApplicationContext(), "Email is not verified", Toast.LENGTH_SHORT).show();
            }

        } else {
            Toast.makeText(this, "Please Create Account..", Toast.LENGTH_SHORT).show();
        }


        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent(getApplicationContext(),signup.class);
                startActivity(it);
                account.this.finish();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                em=email.getText().toString().trim();
                pwd=password.getText().toString().trim();
                if (TextUtils.isEmpty(em)) {
                    //email is empty
                    Toast.makeText(getApplicationContext(), "Please enter email...", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(pwd)) {
                    //password is empty
                    Toast.makeText(getApplicationContext(), "Please enter password...", Toast.LENGTH_SHORT).show();
                    return;
                }
                pb.setVisibility(View.VISIBLE);
                //login
                firebaseAuth.signInWithEmailAndPassword(em,pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            pb.setVisibility(View.GONE);
                            startActivity(new Intent(account.this, success.class));
                            finish();
                        } else {
                            pb.setVisibility(View.INVISIBLE);
                            Toast.makeText(getApplicationContext(), "Some thing was wrong...Try again...", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });
    }
}
