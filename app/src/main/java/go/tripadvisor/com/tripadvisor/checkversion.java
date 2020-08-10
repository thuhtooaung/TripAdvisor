package go.tripadvisor.com.tripadvisor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class checkversion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checkversion);
        WebView wb=findViewById(R.id.wbver);
        wb.loadUrl("https://htya.000webhostapp.com/TripAdvisor/web/tripadvisorupdate.html");
    }
}
