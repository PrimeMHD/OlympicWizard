package chizaitongji.example.com.chizaitongji.Adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

import chizaitongji.example.com.chizaitongji.Activity.MainActivity;
import chizaitongji.example.com.chizaitongji.Bean.SportsEvent;
import chizaitongji.example.com.chizaitongji.Bean.SportsEventSettingThumbnail;
import chizaitongji.example.com.chizaitongji.Listener.OnItemClickListener;
import chizaitongji.example.com.chizaitongji.R;

public class SportsEventSettingThumbnailAdapter extends RecyclerView.Adapter<SportsEventSettingThumbnailAdapter.MyViewHolder>{
    private List<SportsEventSettingThumbnail> sportsEventSettingThumbnails;
    private OnItemClickListener mClickListener;
    private MainActivity mainActivity;
    private static final  String TAG="SportsEventSettingThumbnailAdapter";

    public SportsEventSettingThumbnailAdapter(List<SportsEventSettingThumbnail> sportsEventSettingThumbnails) {
        this.sportsEventSettingThumbnails = sportsEventSettingThumbnails;

    }

    public SportsEventSettingThumbnailAdapter() {
        this.sportsEventSettingThumbnails=new ArrayList<SportsEventSettingThumbnail>();
    }

    @Override
    public SportsEventSettingThumbnailAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.item_gamesetting, parent, false);
        final SportsEventSettingThumbnailAdapter.MyViewHolder holder = new SportsEventSettingThumbnailAdapter.MyViewHolder(view);
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
    public void onBindViewHolder(final SportsEventSettingThumbnailAdapter.MyViewHolder holder, final int position) {
        //设置显示内容为list里的对应项
        SportsEventSettingThumbnail sportsEventSettingThumbnail = sportsEventSettingThumbnails.get(position);
        holder.textView_GameName.setText(sportsEventSettingThumbnail.getGameName());
        if(sportsEventSettingThumbnail.getGender()== SportsEvent.SportsGender.FAMALE){
            holder.textView_GameGender.setText("女子项目");
        }else{
            holder.textView_GameGender.setText("男子项目");
        }
        if(sportsEventSettingThumbnail.getRankValid()== SportsEvent.RankValid.FIVE_VALID){
            holder.textView_RankValid.setText("前五名计分");
        }else{
            holder.textView_RankValid.setText("前三名计分");

        }
        holder.button_modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mainActivity.getOlympicGame().getGames().get(position).hasOutcome()){
                    Toast.makeText(mainActivity,"该场比赛已经结束！",Toast.LENGTH_LONG).show();

                }else {
                    Toast.makeText(mainActivity,"请发送邮件联系奥组委进行修改",Toast.LENGTH_LONG).show();

                }
            }
        });
        holder.button_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //唤起删除
                int removePos=holder.getAdapterPosition();
                sportsEventSettingThumbnails.remove(removePos);
                if(mainActivity!=null){
                    mainActivity.getOlympicGame().getGames().remove(removePos);
                }else {

                }
                notifyItemRemoved(removePos);
            }
        });




    }

    public SportsEventSettingThumbnail getItem(int position) {
        return sportsEventSettingThumbnails.get(position);
    }

    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }


    //要显示的子项数量
    @Override
    public int getItemCount() {
        return sportsEventSettingThumbnails.size();
    }

    //这里定义的是子项的类，不要在这里直接对获取对象进行操作
    static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView gamePic;
        TextView textView_GameName;
        TextView textView_GameGender;
        TextView textView_RankValid;
        Button button_modify;
        Button button_delete;

        public MyViewHolder(View itemView) {
            super(itemView);
            gamePic=itemView.findViewById(R.id.gamePic);
            textView_GameName=itemView.findViewById(R.id.textView_GameName);
            textView_GameGender=itemView.findViewById(R.id.textView_GameGender);
            textView_RankValid=itemView.findViewById(R.id.textView_RankValid);
            button_modify=itemView.findViewById(R.id.button_modify);
            button_delete=itemView.findViewById(R.id.button_delete);


        }
    }

    public void setData(List<SportsEventSettingThumbnail> list) {
        sportsEventSettingThumbnails.clear();
        sportsEventSettingThumbnails.addAll(list);
    }
    public void addItem(SportsEventSettingThumbnail sportsEventSettingThumbnail){
        this.sportsEventSettingThumbnails.add(sportsEventSettingThumbnail);
        notifyDataSetChanged();
    }


    public void setMainActivity(MainActivity mainActivity) {

        this.mainActivity = mainActivity;
    }
}
