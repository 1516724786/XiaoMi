package arrol.com.xiaomi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import arrol.com.xiaomi.R;

/**
 * Created by User on 2016/4/28.
 */
public class AdapterGridView extends BaseAdapter{

    private  List<String> list;
    private LayoutInflater mInflater;

    public AdapterGridView(Context context,List<String> list){
        this.list=list;
        mInflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;

        if(convertView == null){
            convertView=mInflater.inflate(R.layout.item_gridview_secret,null);
            viewHolder=new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder=(ViewHolder)convertView.getTag();
        }

        if(position == list.size()-1){
            viewHolder.imageView.setVisibility(View.VISIBLE);
            viewHolder.textView.setVisibility(View.GONE);
        }
        else{
            viewHolder.textView.setText(list.get(position));
        }
        return convertView;
    }

    class ViewHolder{
        private TextView textView;
        private ImageView imageView;
        public ViewHolder(View itemView){
            textView=(TextView)itemView.findViewById(R.id.textView_item_secret);
            imageView=(ImageView)itemView.findViewById(R.id.image_item_secret);
        }
    }
}
