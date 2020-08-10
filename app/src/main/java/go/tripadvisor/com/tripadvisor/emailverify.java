package go.tripadvisor.com.tripadvisor;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class emailverify extends AppCompatActivity {
    //Firebase
    FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
    FirebaseUser user=firebaseAuth.getCurrentUser();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.emailverify);
        final SwipeRefreshLayout swipeRefreshLayout=findViewById(R.id.swipeRefreshLayout);
        final TextView tv=(TextView) findViewById(R.id.verifyEmailNotice);
        final Button btn=(Button) findViewById(R.id.emailVerify);

        btn.setEnabled(false);
        //refreshing
        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                user.reload();
                if (user.isEmailVerified()){
                    tv.setText(R.string.not_verified);
                    btn.setEnabled(true);
                }
                else {
                    tv.setText(R.string.verified);
                }
                swipeRefreshLayout.setRefreshing(false);
            }
        });


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(emailverify.this,account.class));
                emailverify.this.finish();
            }
        });
    }
}
