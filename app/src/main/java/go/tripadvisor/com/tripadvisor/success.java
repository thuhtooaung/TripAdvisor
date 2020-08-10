package go.tripadvisor.com.tripadvisor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class success extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.success);
        TextView tv=findViewById(R.id.profile);
        Button btn=findViewById(R.id.logout);
        final FirebaseAuth auth=FirebaseAuth.getInstance();
        final FirebaseUser user=auth.getCurrentUser();
        if (user!=null){
            String pro=user.getEmail();
            tv.setText("User Email: "+pro);
        }

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                auth.signOut();
                Toast.makeText(getApplicationContext(),"Logged Out",Toast.LENGTH_SHORT).show();
                success.this.finish();

            }
        });
    }
}
