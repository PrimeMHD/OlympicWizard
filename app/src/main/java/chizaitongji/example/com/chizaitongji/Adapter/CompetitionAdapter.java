package chizaitongji.example.com.chizaitongji.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


import chizaitongji.example.com.chizaitongji.Bean.CompetitionThumb;
import chizaitongji.example.com.chizaitongji.Bean.SportsEvent;
import chizaitongji.example.com.chizaitongji.Listener.OnItemClickListener;
import chizaitongji.example.com.chizaitongji.R;


public class CompetitionAdapter extends RecyclerView.Adapter<CompetitionAdapter.MyViewHolder> {

    private List<CompetitionThumb> competitionThumbList;
    private OnItemClickListener mClickListener;
    private Context mContext;
    private static final String TAG="CompetitionAdapter";


    public CompetitionAdapter(List<CompetitionThumb> competitionThumbList) {
        this.competitionThumbList = competitionThumbList;
    }

    public CompetitionAdapter() {
        this.competitionThumbList = new ArrayList<CompetitionThumb>();
    }

    @Override
    public CompetitionAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.item_competition, parent, false);
        final CompetitionAdapter.MyViewHolder holder = new CompetitionAdapter.MyViewHolder(view);
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
    public void onBindViewHolder(final CompetitionAdapter.MyViewHolder holder, int position) {
        //设置显示内容为list里的对应项
        CompetitionThumb competitionThumb = competitionThumbList.get(position);


        holder.textView_compName.setText(competitionThumb.getSportsEvent().getGameName());
        holder.textView_compName.append(competitionThumb.getSportsEvent().getSportsGender()== SportsEvent.SportsGender.FAMALE?"(女子组)":"(男子组)");

        if(competitionThumb.hasOutcome()){
            holder.textView_compState.setText("比赛结束");
            holder.textView_compState.setBackground(mContext.getResources().getDrawable(R.drawable.cirsquare_status_ok));
        }
        else {
            holder.textView_compState.setText("待比赛");
        }

//        holder.button_remove_nation.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int removePos = holder.getAdapterPosition();
//                nationThumbList.remove(removePos);
//                notifyItemRemoved(removePos);
//            }
//        });


    }

    public CompetitionThumb getItem(int position) {
        return competitionThumbList.get(position);
    }

    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }


    //要显示的子项数量
    @Override
    public int getItemCount() {
        return competitionThumbList.size();
    }

    //这里定义的是子项的类，不要在这里直接对获取对象进行操作
    static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textView_compName;
        TextView textView_compState;


        public MyViewHolder(View itemView) {
            super(itemView);
            textView_compName = itemView.findViewById(R.id.textView_compName);
            textView_compState = itemView.findViewById(R.id.textView_compState);

        }
    }

    public void setData(List<CompetitionThumb> list) {
        competitionThumbList.clear();
        competitionThumbList.addAll(list);
    }

    public void addItem(CompetitionThumb competitionThumb) {
        this.competitionThumbList.add(competitionThumb);
        notifyDataSetChanged();
    }

    public List<CompetitionThumb> getCompetitionThumbList() {
        return competitionThumbList;
    }

    public void setCompetitionThumbList(List<CompetitionThumb> competitionThumbList) {
        this.competitionThumbList = competitionThumbList;
    }


    public void setmContext(Context mContext) {
        this.mContext = mContext;
        Log.d(TAG,"设置了mContext"+mContext);
    }
}



