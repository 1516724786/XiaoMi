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
 * Created by User on 2016/6/3.
 */
public class AdapterRecordIn extends BaseAdapter{

    private List<Map<String,Object>> data;
    private LayoutInflater inflater;

    public AdapterRecordIn(Context context,List<Map<String,Object>> data){
        this.data=data;
        inflater=LayoutInflater.from(context);
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
            convertView=inflater.inflate(R.layout.item_record_in,null);
            viewHolder=new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder=(ViewHolder)convertView.getTag();
        }
        viewHolder.time.setText(data.get(position).get("time").toString());
        viewHolder.money.setText(data.get(position).get("money").toString());
        if (!data.get(position).get("remark").toString().equals("")){
            viewHolder.remark.setText(data.get(position).get("remark").toString());
        }
        else {
            viewHolder.remark.setText("暂无~");
        }
        return convertView;
    }

    class ViewHolder{
        private TextView time;
        private TextView money;
        private TextView remark;
        public ViewHolder(View view){
            time=(TextView)view.findViewById(R.id.time_item_in);
            money=(TextView)view.findViewById(R.id.money_item_in);
            remark=(TextView)view.findViewById(R.id.remark_item_in);
        }
    }
}
