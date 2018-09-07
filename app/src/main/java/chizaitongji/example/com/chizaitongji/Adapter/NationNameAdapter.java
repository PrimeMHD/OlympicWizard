package chizaitongji.example.com.chizaitongji.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import chizaitongji.example.com.chizaitongji.Bean.NationThumb;

import chizaitongji.example.com.chizaitongji.Listener.OnItemClickListener;
import chizaitongji.example.com.chizaitongji.R;

public class NationNameAdapter extends RecyclerView.Adapter<NationNameAdapter.MyViewHolder>{

    private List<NationThumb> nationThumbList;
    private OnItemClickListener mClickListener;



    public NationNameAdapter(List<NationThumb> nationThumbList) {
        this.nationThumbList = nationThumbList;
    }

    public NationNameAdapter() {
        this.nationThumbList=new ArrayList<NationThumb>();
    }

    @Override
    public NationNameAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.item_nationname_thumbnail, parent, false);
        final NationNameAdapter.MyViewHolder holder = new NationNameAdapter.MyViewHolder(view);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                if (mClickListener != null) {
                    mClickListener.onItemClick(position, view, holder);
                }
            }
        });
        return holder;
    }

    //在这里可以获得每个子项里面的控件的实例，比如这里的TextView,子项本身的实例是itemView，
// 在这里对获取对象进行操作
    //holder.itemView是子项视图的实例，holder.textView是子项内控件的实例
    //position是点击位置
    @Override
    public void onBindViewHolder(final NationNameAdapter.MyViewHolder holder, int position) {
        //设置显示内容为list里的对应项
        NationThumb nationThumb = nationThumbList.get(position);

        String NationCodeString=nationThumb.getNationCode()+"";
        holder.textView_NationName.setText(nationThumb.getNationName());
        holder.textView_NationCode.setText(NationCodeString);
        holder.button_remove_nation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int removePos=holder.getAdapterPosition();
                nationThumbList.remove(removePos);
                notifyItemRemoved(removePos);
            }
        });





    }

    public NationThumb getItem(int position) {
        return nationThumbList.get(position);
    }

    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }


    //要显示的子项数量
    @Override
    public int getItemCount() {
        return nationThumbList.size();
    }

    //这里定义的是子项的类，不要在这里直接对获取对象进行操作
    static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textView_NationName;
        TextView textView_NationCode;
        Button button_remove_nation;

        public MyViewHolder(View itemView) {
            super(itemView);
            textView_NationName=itemView.findViewById(R.id.textView_NationName);
            button_remove_nation=itemView.findViewById(R.id.button_remove_nation);
            textView_NationCode=itemView.findViewById(R.id.textView_NationCode);
        }
    }

    public void setData(List<NationThumb> list) {
        nationThumbList.clear();
        nationThumbList.addAll(list);
    }
    public void addItem(NationThumb nationThumb){
        this.nationThumbList.add(nationThumb);
        notifyDataSetChanged();
    }



}
