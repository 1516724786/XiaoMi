package arrol.com.xiaomi.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

import arrol.com.xiaomi.R;

/**
 * Created by User on 2016/5/27.
 *
 */
public class AdapterRecordOut extends BaseAdapter{

    private List<Map<String,Object>> data;
    private LayoutInflater layoutInflater;

    public AdapterRecordOut(Context context, List<Map<String, Object>> data){
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
            convertView=layoutInflater.inflate(R.layout.item_record_out,null);
            viewHolder=new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder=(ViewHolder)convertView.getTag();
        }

        viewHolder.time.setText(data.get(position).get("time").toString());
        viewHolder.money.setText(data.get(position).get("money").toString());
        if (!data.get(position).get("remark").toString().equals("")){
            viewHolder.remark.setText(data.get(position).get("remark").toString());}
        else {
            viewHolder.remark.setText("暂无~");
        }
        viewHolder.category.setText(data.get(position).get("category").toString());
        return convertView;
    }
    class ViewHolder{
        private TextView category;
        private TextView time;
        private TextView money;
        private TextView remark;
        public ViewHolder(View view){
            category=(TextView)view.findViewById(R.id.category_item_out);
            time=(TextView)view.findViewById(R.id.time_item_out);
            money=(TextView)view.findViewById(R.id.money_item_out);
            remark=(TextView)view.findViewById(R.id.remark_item_out);
        }
    }
}
