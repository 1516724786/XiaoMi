package arrol.com.xiaomi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

import arrol.com.xiaomi.R;

/**
 * Created by User on 2017/4/20.
 */

public class AdapterTotal extends BaseAdapter {
    private List<Map<String,Object>> data;
    private LayoutInflater layoutInflater;

    public AdapterTotal(Context context, List<Map<String, Object>> data){
        layoutInflater=LayoutInflater.from(context);
        this.data=data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null){
            convertView=layoutInflater.inflate(R.layout.item_total,null);
            viewHolder=new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder=(ViewHolder)convertView.getTag();
        }
        viewHolder.totalOut.setText(data.get(position).get("outMoney").toString());
        viewHolder.totalIn.setText(data.get(position).get("inMoney").toString());
        viewHolder.totalTime.setText(data.get(position).get("time").toString());
        return convertView;
    }
    class ViewHolder{
        private TextView totalTime;
        private TextView totalOut;
        private TextView totalIn;
        public ViewHolder(View view){
            totalTime = (TextView)view.findViewById(R.id.tv_total_time);
            totalIn = (TextView)view.findViewById(R.id.tv_total_in);
            totalOut = (TextView)view.findViewById(R.id.tv_total_out);
        }
    }
}
