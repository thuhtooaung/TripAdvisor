package go.tripadvisor.com.tripadvisor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class botadapter extends BaseAdapter {
    Context context;
    ArrayList arrayList;
    LayoutInflater inflater;
    public botadapter(Context applicationContext, ArrayList arr) {
        context=applicationContext;
        arrayList=arr;
        inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v=inflater.inflate(R.layout.conversation,null);
        TextView usr=v.findViewById(R.id.users);
        TextView bot=v.findViewById(R.id.bot);
        usr.setText(arrayList.get(i).toString());
        cbot c=new cbot();
        String s=c.conversation(arrayList.get(i).toString());
        bot.setText(s);
        return v;
    }
}
