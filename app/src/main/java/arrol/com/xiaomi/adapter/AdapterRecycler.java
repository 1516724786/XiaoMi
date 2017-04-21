package arrol.com.xiaomi.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import arrol.com.xiaomi.R;

/**
 * Created by User on 2016/4/8.
 */
public class AdapterRecycler extends RecyclerView.Adapter<AdapterRecycler.MyViewHolder>{

    private ArrayList<Map<String,String>> data;
    private LayoutInflater inflater;

    private OnItemListener listener=null;
    private OnItemLongListener onItemLongListener;


    public AdapterRecycler(Context context, ArrayList<Map<String,String>> data){
        inflater=LayoutInflater.from(context);
        this.data=data;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder=new MyViewHolder(inflater
                .inflate(R.layout.item_fragment_add_alert_recycler, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        if(position < data.size()-1){
            holder.linly.setVisibility(View.VISIBLE);
            holder.imageView.setVisibility(View.GONE);
            holder.textView.setText(data.get(position).get("time"));
            if (!data.get(position).get("remark").equals(""))
                holder.remark.setText(data.get(position).get("remark"));
        }
        else{
            holder.linly.setVisibility(View.GONE);
            holder.imageView.setVisibility(View.VISIBLE);
        }


        if (listener != null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getPosition();
                    listener.onItemClickListener(v, pos);
                }
            });
        }
        if (onItemLongListener!=null){
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int pos = holder.getPosition();
                    onItemLongListener.onItemLongClickListener(v,pos);
                    return true;//返回值为true，表示这个点击事件被长点击独占！
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public void removeData(int position){
        data.remove(position);
        notifyItemRemoved(position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        ImageView imageView;
        LinearLayout linly;
        TextView remark;
        public MyViewHolder(View itemView) {
            super(itemView);
            textView=(TextView) itemView.findViewById(R.id.item_alert_time);
            imageView=(ImageView)itemView.findViewById(R.id.item_imageView);
            linly=(LinearLayout)itemView.findViewById(R.id.item_alert_linly);
            remark = (TextView)itemView.findViewById(R.id.item_alert_remark);
        }
    }

    public interface OnItemListener{
        void onItemClickListener(View view, int position);
    }
    public interface OnItemLongListener{
        void onItemLongClickListener(View view, int position);
    }
    public void setItemClickListener(OnItemListener listener){
        this.listener=listener;
    }
    public void setItemLongClickListener(OnItemLongListener onItemLongListener){

        this.onItemLongListener=onItemLongListener;
    }
}
