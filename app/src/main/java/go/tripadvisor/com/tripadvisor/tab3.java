package go.tripadvisor.com.tripadvisor;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

public class tab3 extends Fragment {
    TextView acc,set,feed,about,version;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.tab3,null);
        acc=v.findViewById(R.id.account);
        set=v.findViewById(R.id.setting);
        feed=v.findViewById(R.id.feedback);
        about=v.findViewById(R.id.aboutus);
        version=v.findViewById(R.id.version);

        //click listener
        acc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent(getContext(),account.class);
                startActivity(it);
            }
        });

        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent(getContext(),setting.class);
                startActivity(it);
            }
        });

        feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent(getContext(),feedback.class);
                startActivity(it);
            }
        });

        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent(getContext(),aboutus.class);
                startActivity(it);
            }
        });

        version.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent(getContext(),checkversion.class);
                startActivity(it);
            }
        });

        return v;
    }

}
