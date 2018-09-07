package chizaitongji.example.com.chizaitongji.Fragment.Fragment_ThirdGroup;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import chizaitongji.example.com.chizaitongji.Activity.AddNationActivity;
import chizaitongji.example.com.chizaitongji.Activity.MainActivity;
import chizaitongji.example.com.chizaitongji.Activity.SetCompActivity;
import chizaitongji.example.com.chizaitongji.Adapter.CompetitionAdapter;
import chizaitongji.example.com.chizaitongji.Bean.CompetitionThumb;
import chizaitongji.example.com.chizaitongji.Bean.Nation;
import chizaitongji.example.com.chizaitongji.Bean.SportsEvent;
import chizaitongji.example.com.chizaitongji.Fragment.BaseMainFragment;
import chizaitongji.example.com.chizaitongji.Listener.OnItemClickListener;
import chizaitongji.example.com.chizaitongji.R;


public class Fragment_RootThird_Parent extends BaseMainFragment {


    private RecyclerView recyclerview_Competitions;
    private CompetitionAdapter competitionAdapter;
    private MainActivity mainActivity;
    private static final int REQUESTCODE_SETCOMP=1;
    private static final String TAG="Fragment_RootThird_Parent";

    public Fragment_RootThird_Parent() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment CommunityFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_RootThird_Parent newInstance() {
        Fragment_RootThird_Parent fragment = new Fragment_RootThird_Parent();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
        mainActivity = (MainActivity) mContext;
        mainActivity.getToolbar().setTitle("设置成绩");
        refreshCompeteTionList();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_rootthird_parent, container, false);

        recyclerview_Competitions=(RecyclerView) view.findViewById(R.id.recyclerview_Competitions);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(mContext);
        recyclerview_Competitions.setLayoutManager(linearLayoutManager);
        competitionAdapter=new CompetitionAdapter();
        competitionAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position, View view, RecyclerView.ViewHolder vh) {
                Intent intent = new Intent(getContext(),SetCompActivity.class);
                Bundle bundle=new Bundle();
                ArrayList<String> nations_string=new ArrayList<String>();

                for(Nation nation:mainActivity.getOlympicGame().getNations()){
                    nations_string.add(nation.getNationName());
                    Log.d(TAG,"准备装填intent"+nation.getNationName());
                }

                if(nations_string.size()==0){
                    Toast.makeText(getContext(),"请先回首页添加参赛国",Toast.LENGTH_LONG).show();

                }


                bundle.putStringArrayList("nations",nations_string);
                bundle.putString("GameName",mainActivity.getOlympicGame().getGames().get(position).getGameName());
                if(mainActivity.getOlympicGame().getGames().get(position).getRankValid()== SportsEvent.RankValid.FIVE_VALID){
                    bundle.putInt("RankValid",5);
                }else{
                    bundle.putInt("RankValid",3);

                }

                intent.putExtras(bundle);

                startActivityForResult(intent, REQUESTCODE_SETCOMP);
                //启动设定比赛
            }
        });


        recyclerview_Competitions.setAdapter(competitionAdapter);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    public void refreshCompeteTionList(){
        List<SportsEvent> games=mainActivity.getOlympicGame().getGames();
        List<CompetitionThumb>list=new ArrayList<CompetitionThumb>();
        for(SportsEvent game:games){
            CompetitionThumb competitionThumb=new CompetitionThumb();
            competitionThumb.setHasOutcome(game.hasOutcome());
            competitionThumb.setSportsEvent(game);
            list.add(competitionThumb);

        }

        competitionAdapter.setData(list);
        competitionAdapter.notifyDataSetChanged();
    }




}
