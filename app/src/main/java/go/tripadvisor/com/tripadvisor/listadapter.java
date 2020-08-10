package go.tripadvisor.com.tripadvisor;

import android.content.Context;
import android.content.Intent;
import android.graphics.ColorSpace;
import android.media.Image;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;
import java.util.logging.Handler;

import san.zgyi.uni.SanZtoU;

class listadapter extends BaseAdapter {
    Context context;
    LayoutInflater inflater;
    String[] name;
    String Type;
    public listadapter(Context mcontext,String[] name1,String t) {
        context = mcontext;
        inflater=LayoutInflater.from(mcontext);
        this.name=name1;
        this.Type=t;
    }

    //viewhoder
    public class ViewHolder{
        TextView tv;
        ImageView iv;
    }

    @Override
    public int getCount() {
        return name.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view==null){
            holder=new ViewHolder();
            view=inflater.inflate(R.layout.listviewitem,null);

            //locate the views in row.xml
            holder.iv=view.findViewById(R.id.lv_iv);
            holder.tv=view.findViewById(R.id.lv_tv);

            view.setTag(holder);
        }
        else {
            holder=(ViewHolder) view.getTag();
        }
        //set result into view
        String[] h=new String[]{"https://htya.000webhostapp.com/TripAdvisor/hiscv/mucv.png",
                "https://htya.000webhostapp.com/TripAdvisor/hiscv/incv.jpg",
                "https://htya.000webhostapp.com/TripAdvisor/hiscv/TaungGcv.jpg",
                "https://htya.000webhostapp.com/TripAdvisor/hiscv/Mogokcv.jpg",
                "https://htya.000webhostapp.com/TripAdvisor/hiscv/Sittwecv.jpg",
                "https://htya.000webhostapp.com/TripAdvisor/hiscv/mancv.JPG",
                "https://htya.000webhostapp.com/TripAdvisor/hiscv/Hpancv.jpg",
                "https://htya.000webhostapp.com/TripAdvisor/hiscv/PoLcv.JPG",
                "https://htya.000webhostapp.com/TripAdvisor/hiscv/bgcv.jpg",
                "https://htya.000webhostapp.com/TripAdvisor/hiscv/kalcv.jpg",
                "https://htya.000webhostapp.com/TripAdvisor/hiscv/Loikawcv.jpg"};
        String[] b=new String[]{"https://htya.000webhostapp.com/TripAdvisor/ct.jpg",
                "https://htya.000webhostapp.com/TripAdvisor/gyg1.jpg",
                "https://htya.000webhostapp.com/TripAdvisor/m.jpg",
                "https://htya.000webhostapp.com/TripAdvisor/nga5.jpg",
                "https://htya.000webhostapp.com/TripAdvisor/ns.JPG",
                "https://htya.000webhostapp.com/TripAdvisor/kbw.jpg"};
        String[] m=new String[]{"https://htya.000webhostapp.com/TripAdvisor/mtcv/ttg.jpg",
                "https://htya.000webhostapp.com/TripAdvisor/mtcv/yn.jpg",
                "https://htya.000webhostapp.com/TripAdvisor/mtcv/nn.jpg",
                "https://htya.000webhostapp.com/TripAdvisor/mtcv/kns.jpg"};
        holder.tv.setText(name[i]);
        if (Type.equals("h")){
            Picasso.with(context)
                    .load(Uri.parse(h[i]))
                    .into(holder.iv);
        }
        if (Type.equals("b")){
            Picasso.with(context)
                    .load(Uri.parse(b[i]))
                    .into(holder.iv);
        }
        if (Type.equals("m")){
            Picasso.with(context)
                    .load(Uri.parse(m[i]))
                    .into(holder.iv);
        }
        return view;
    }
}
