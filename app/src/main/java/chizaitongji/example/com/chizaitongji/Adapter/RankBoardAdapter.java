package chizaitongji.example.com.chizaitongji.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import chizaitongji.example.com.chizaitongji.Bean.RankBoardThumb;
import chizaitongji.example.com.chizaitongji.Listener.OnItemClickListener;
import chizaitongji.example.com.chizaitongji.R;

public class RankBoardAdapter extends RecyclerView.Adapter<RankBoardAdapter.MyViewHolder>{
    private List<RankBoardThumb> rankBoardThumbList;
    private OnItemClickListener mClickListener;



    public RankBoardAdapter(List<RankBoardThumb> rankBoardThumbList) {
        this.rankBoardThumbList = rankBoardThumbList;
    }

    public RankBoardAdapter() {
        this.rankBoardThumbList=new ArrayList<RankBoardThumb>();
    }

    @Override
    public RankBoardAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.item_rankboardthumb, parent, false);
        final RankBoardAdapter.MyViewHolder holder = new RankBoardAdapter.MyViewHolder(view);
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
    public void onBindViewHolder(final RankBoardAdapter.MyViewHolder holder, int position) {
        //设置显示内容为list里的对应项
        RankBoardThumb rankBoardThumb = rankBoardThumbList.get(position);

        String NationCodeString=rankBoardThumb.getNationCode()+"";
        String TotalScoreString=rankBoardThumb.getTotalScore()+"";
        String MaleScoreString=rankBoardThumb.getMaleScore()+"";
        String FemaleScoreString=rankBoardThumb.getFemaleScore()+"";


        holder.textView_RankNationCode.setText(NationCodeString);
        holder.textView_RankNationName.setText(rankBoardThumb.getNationName());
        holder.textView_RankTotalScore.setText(TotalScoreString);
        holder.textView_RankMaleScore.setText(MaleScoreString);
        holder.textView_RankFemaleScore.setText(FemaleScoreString);

    }

    public RankBoardThumb getItem(int position) {
        return rankBoardThumbList.get(position);
    }

    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }


    //要显示的子项数量
    @Override
    public int getItemCount() {
        return rankBoardThumbList.size();
    }

    //这里定义的是子项的类，不要在这里直接对获取对象进行操作
    static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textView_RankNationCode;
        TextView textView_RankNationName;
        TextView textView_RankTotalScore;
        TextView textView_RankMaleScore;
        TextView textView_RankFemaleScore;

        public MyViewHolder(View itemView) {
            super(itemView);
            textView_RankNationCode=itemView.findViewById(R.id.textView_RankNationCode);
            textView_RankNationName=itemView.findViewById(R.id.textView_RankNationName);
            textView_RankTotalScore=itemView.findViewById(R.id.textView_RankTotalScore);
            textView_RankMaleScore=itemView.findViewById(R.id.textView_RankMaleScore);
            textView_RankFemaleScore=itemView.findViewById(R.id.textView_RankFemaleScore);



        }
    }

    public void setData(List<RankBoardThumb> list) {
        rankBoardThumbList.clear();
        rankBoardThumbList.addAll(list);
    }
    public void addItem(RankBoardThumb rankBoardThumb){
        this.rankBoardThumbList.add(rankBoardThumb);
        notifyDataSetChanged();
    }



}
