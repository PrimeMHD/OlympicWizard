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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import static chizaitongji.example.com.chizaitongji.Bean.SportsEvent.SCORE1IN3;
import static chizaitongji.example.com.chizaitongji.Bean.SportsEvent.SCORE1IN5;
import static chizaitongji.example.com.chizaitongji.Bean.SportsEvent.SCORE2IN3;
import static chizaitongji.example.com.chizaitongji.Bean.SportsEvent.SCORE2IN5;
import static chizaitongji.example.com.chizaitongji.Bean.SportsEvent.SCORE3IN3;
import static chizaitongji.example.com.chizaitongji.Bean.SportsEvent.SCORE3IN5;
import static chizaitongji.example.com.chizaitongji.Bean.SportsEvent.SCORE4IN5;
import static chizaitongji.example.com.chizaitongji.Bean.SportsEvent.SCORE5IN5;


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
        competitionAdapter.setmContext(getContext());
        competitionAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position, View view, RecyclerView.ViewHolder vh) {
                Intent intent = new Intent(getContext(),SetCompActivity.class);
                Bundle bundle=new Bundle();
                ArrayList<String> nations_string=new ArrayList<String>();

                for(Nation nation:mainActivity.getOlympicGame().getNations()){
                    nations_string.add(nation.getNationName());
                }

                if(nations_string.size()==0){
                    Toast.makeText(getContext(),"请先回首页添加参赛国",Toast.LENGTH_LONG).show();

                }else{
                    if(mainActivity.getOlympicGame().getGames().get(position).hasOutcome()){

                        Toast.makeText(getContext(),"该场比赛已经结束",Toast.LENGTH_LONG).show();

                    }
                    else{
                        bundle.putStringArrayList("nations",nations_string);
                        bundle.putString("GameName",mainActivity.getOlympicGame().getGames().get(position).getGameName());
                        if(mainActivity.getOlympicGame().getGames().get(position).getRankValid()== SportsEvent.RankValid.FIVE_VALID){
                            bundle.putInt("RankValid",5);
                        }else{
                            bundle.putInt("RankValid",3);

                        }
                        bundle.putInt("GamePos",position);
                        if(mainActivity.getOlympicGame().getGames().get(position).getSportsGender()== SportsEvent.SportsGender.FAMALE)
                            bundle.putInt("GameGender",0);
                        else
                            bundle.putInt("GameGender",1);


                        intent.putExtras(bundle);

                        startActivityForResult(intent, REQUESTCODE_SETCOMP);
                        //启动设定比赛
                    }


                }



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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQUESTCODE_SETCOMP&&resultCode == Activity.RESULT_OK){
            int GamePos=data.getIntExtra("GamePos",0);
            int GameGender=data.getIntExtra("GameGender",0);
            String GameName=data.getStringExtra("GameName");
            String firstName=data.getStringExtra("first");
            String secondName=data.getStringExtra("second");
            String thirdName=data.getStringExtra("third");
            int RankValid=data.getIntExtra("RankValid",3);



            Map<Integer,Nation> winnerMap=new HashMap<Integer,Nation>();
            winnerMap.put(1,mainActivity.getOlympicGame().getNationByName(firstName));
            winnerMap.put(2,mainActivity.getOlympicGame().getNationByName(secondName));
            winnerMap.put(3,mainActivity.getOlympicGame().getNationByName(thirdName));
//            Log.d(TAG,"GameName是"+GameName);
//            Log.d(TAG,"firstName是"+firstName);
//            Log.d(TAG,"secondName是"+secondName);

            if (RankValid==3){
                mainActivity.getOlympicGame().getNationByName(firstName).addTotalScore(SCORE1IN3);
                mainActivity.getOlympicGame().getNationByName(secondName).addTotalScore(SCORE2IN3);
                mainActivity.getOlympicGame().getNationByName(thirdName).addTotalScore(SCORE3IN3);
                mainActivity.getOlympicGame().getNationByName(firstName).addOnHonorBoard(GameName+GameGender,SCORE1IN3);
                mainActivity.getOlympicGame().getNationByName(secondName).addOnHonorBoard(GameName+GameGender,SCORE2IN3);
                mainActivity.getOlympicGame().getNationByName(thirdName).addOnHonorBoard(GameName+GameGender,SCORE3IN3);
                if(GameGender==1){
                    mainActivity.getOlympicGame().getNationByName(firstName).addMaleScore(SCORE1IN3);
                    mainActivity.getOlympicGame().getNationByName(secondName).addMaleScore(SCORE2IN3);
                    mainActivity.getOlympicGame().getNationByName(thirdName).addMaleScore(SCORE3IN3);
                }else{
                    mainActivity.getOlympicGame().getNationByName(firstName).addFemaleScore(SCORE1IN3);
                    mainActivity.getOlympicGame().getNationByName(secondName).addFemaleScore(SCORE2IN3);
                    mainActivity.getOlympicGame().getNationByName(thirdName).addFemaleScore(SCORE3IN3);
                }



            }
           else if(RankValid==5){
                String fourthName=data.getStringExtra("fourth");
                String fifthName=data.getStringExtra("fifth");
                winnerMap.put(4,mainActivity.getOlympicGame().getNationByName(fourthName));
                winnerMap.put(5,mainActivity.getOlympicGame().getNationByName(fifthName));

                mainActivity.getOlympicGame().getNationByName(firstName).addTotalScore(SCORE1IN5);
                mainActivity.getOlympicGame().getNationByName(secondName).addTotalScore(SCORE2IN5);
                mainActivity.getOlympicGame().getNationByName(thirdName).addTotalScore(SCORE3IN5);
                mainActivity.getOlympicGame().getNationByName(fourthName).addTotalScore(SCORE4IN5);
                mainActivity.getOlympicGame().getNationByName(fifthName).addTotalScore(SCORE5IN5);

                mainActivity.getOlympicGame().getNationByName(firstName).addOnHonorBoard(GameName+GameGender,SCORE1IN5);
                mainActivity.getOlympicGame().getNationByName(secondName).addOnHonorBoard(GameName+GameGender,SCORE2IN5);
                mainActivity.getOlympicGame().getNationByName(thirdName).addOnHonorBoard(GameName+GameGender,SCORE3IN5);
                mainActivity.getOlympicGame().getNationByName(fourthName).addOnHonorBoard(GameName+GameGender,SCORE4IN5);
                mainActivity.getOlympicGame().getNationByName(fifthName).addOnHonorBoard(GameName+GameGender,SCORE5IN5);

                if(GameGender==1){
                    mainActivity.getOlympicGame().getNationByName(firstName).addMaleScore(SCORE1IN5);
                    mainActivity.getOlympicGame().getNationByName(secondName).addMaleScore(SCORE2IN5);
                    mainActivity.getOlympicGame().getNationByName(thirdName).addMaleScore(SCORE3IN5);
                    mainActivity.getOlympicGame().getNationByName(fourthName).addMaleScore(SCORE4IN5);
                    mainActivity.getOlympicGame().getNationByName(fifthName).addMaleScore(SCORE5IN5);
                }else{
                    mainActivity.getOlympicGame().getNationByName(firstName).addFemaleScore(SCORE1IN5);
                    mainActivity.getOlympicGame().getNationByName(secondName).addFemaleScore(SCORE2IN5);
                    mainActivity.getOlympicGame().getNationByName(thirdName).addFemaleScore(SCORE3IN5);
                    mainActivity.getOlympicGame().getNationByName(fourthName).addFemaleScore(SCORE4IN5);
                    mainActivity.getOlympicGame().getNationByName(fifthName).addFemaleScore(SCORE5IN5);
                }




            }
            mainActivity.getOlympicGame().getGames().get(GamePos).setWinners(winnerMap);
            mainActivity.getOlympicGame().getGames().get(GamePos).setHasOutcome(true);




            //Log.d(TAG,"WinnderMap是："+mainActivity.getOlympicGame().getGames().get(GamePos).getWinners().get(1).getNationName());
            //Log.d(TAG,"WinnderMap是："+mainActivity.getOlympicGame().getGames().get(GamePos).getWinners().get(2).getNationName());
            //Log.d(TAG,"WinnderMap是："+mainActivity.getOlympicGame().getGames().get(GamePos).getWinners().get(3).getNationName());

            /*for(Map.Entry<String,Integer> entry:mainActivity.getOlympicGame().getNations().get(0).getHonorBoard().entrySet()){
                Log.d(TAG,"查看map"+entry.getKey()+";"+entry.getValue());
            }*/






        }

    }


}
