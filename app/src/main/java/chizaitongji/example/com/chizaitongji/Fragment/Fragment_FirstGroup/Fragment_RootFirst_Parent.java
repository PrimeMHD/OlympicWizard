package chizaitongji.example.com.chizaitongji.Fragment.Fragment_FirstGroup;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import chizaitongji.example.com.chizaitongji.Activity.AddGameActivity;
import chizaitongji.example.com.chizaitongji.Activity.AddNationActivity;
import chizaitongji.example.com.chizaitongji.Activity.MainActivity;
import chizaitongji.example.com.chizaitongji.Adapter.NationNameAdapter;
import chizaitongji.example.com.chizaitongji.Adapter.SportsEventSettingThumbnailAdapter;
import chizaitongji.example.com.chizaitongji.Bean.Nation;
import chizaitongji.example.com.chizaitongji.Bean.NationThumb;
import chizaitongji.example.com.chizaitongji.Bean.SportsEvent;
import chizaitongji.example.com.chizaitongji.Bean.SportsEventSettingThumbnail;
import chizaitongji.example.com.chizaitongji.Fragment.BaseMainFragment;
import chizaitongji.example.com.chizaitongji.R;
import chizaitongji.example.com.chizaitongji.Utils.NationCodeGenerator;

/**
 * Created by Mi HD on 2018/2/7.
 */

public class Fragment_RootFirst_Parent extends BaseMainFragment {
    protected Activity mActivity;
    private MainActivity mainActivity;
    private static final String TAG = "Fragment_RootFirst_Parent";
    //private List<SportsEventSettingThumbnail> sportsEventSettingThumbnailList;
    private Button button_addGame;
    private Button button_addNation;
    private RecyclerView recyclerView1;
    private RecyclerView recyclerView_NationNames;
    private static final int REQUESTCODE_ADDGAME = 0;
    private static final int REQUESTCODE_MODIFYGAME = 1;
    private static final int REQUESTCODE_ADDNATION = 2;
    private SportsEventSettingThumbnailAdapter adapter;
    private NationNameAdapter nationNameAdapter;


    public static Fragment_RootFirst_Parent newInstance() {
        Bundle args = new Bundle();
        Fragment_RootFirst_Parent fragment = new Fragment_RootFirst_Parent();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
        mainActivity = (MainActivity) mContext;
        mainActivity.getToolbar().setTitle("奥运赛程设定");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.layout_fragment_rootfirst_parent, container, false);
        recyclerView1 = (RecyclerView) v.findViewById(R.id.recyclerview_GameSettingThumbnails);
        recyclerView_NationNames=(RecyclerView)v.findViewById(R.id.recyclerview_NationNameThumbnails) ;
        button_addGame = (Button) v.findViewById(R.id.button_addGame);
        button_addNation = (Button) v.findViewById(R.id.button_addNationName);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(mContext);


        initView();

        recyclerView_NationNames.setLayoutManager(layoutManager1);
        recyclerView1.setLayoutManager(layoutManager);
        //sportsEventSettingThumbnailList = new ArrayList<SportsEventSettingThumbnail>();
        //sportsEventSettingThumbnailList.add(new SportsEventSettingThumbnail("足球", SportsEvent.SportsGender.FAMALE,SportsEvent.RankValid.FIVE_VALID));

        //adapter = new SportsEventSettingThumbnailAdapter(sportsEventSettingThumbnailList);
        adapter = new SportsEventSettingThumbnailAdapter();
        adapter.setMainActivity((MainActivity) mContext);
        recyclerView1.setAdapter(adapter);

        nationNameAdapter=new NationNameAdapter();
        nationNameAdapter.setMainActivity((MainActivity)mContext);
        recyclerView_NationNames.setAdapter(nationNameAdapter);

        return v;
    }

    //    @Override
//    public void onAttach(Activity activity) {
//        super.onAttach(activity);
//        this.mActivity = activity;
//        Log.d(TAG,"on Attach");
//    }
    private void initView() {
        button_addGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getContext(), AddGameActivity.class);
                startActivityForResult(intent, REQUESTCODE_ADDGAME);


            }
        });
        button_addNation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AddNationActivity.class);
                startActivityForResult(intent, REQUESTCODE_ADDNATION);
            }
        });


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUESTCODE_ADDGAME && resultCode == Activity.RESULT_OK) {
            SportsEvent returnedSportsEvent = new SportsEvent();
            returnedSportsEvent.setGameName(data.getStringExtra("GameName"));

            if (data.getStringExtra("GameGender").equals("FEMALE")) {
                returnedSportsEvent.setSportsGender(SportsEvent.SportsGender.FAMALE);
            } else {
                returnedSportsEvent.setSportsGender(SportsEvent.SportsGender.MALE);
            }


            if (data.getStringExtra("RankValid").equals("THREE_VALID")) {
                returnedSportsEvent.setRankValid(SportsEvent.RankValid.THREE_VALID);
            } else {
                returnedSportsEvent.setRankValid(SportsEvent.RankValid.FIVE_VALID);
            }
            //sportsEventSettingThumbnailList.add(new SportsEventSettingThumbnail(returnedSportsEvent));
            //sportsEventSettingThumbnailList.add(new SportsEventSettingThumbnail("pp", SportsEvent.SportsGender.FAMALE,SportsEvent.RankValid.FIVE_VALID));

            //Log.d(TAG,sportsEventSettingThumbnailList.get(1).getGameName()+"");
            //Log.d(TAG,"adapter 后"+sportsEventSettingThumbnailList);

            if(mainActivity.getOlympicGame().SportsEventExists(returnedSportsEvent)){
                Toast.makeText(getContext(),"项目已存在，不能重复添加",Toast.LENGTH_LONG).show();
            }else {
                mainActivity.getOlympicGame().addSportsEvent(returnedSportsEvent);
                adapter.addItem(new SportsEventSettingThumbnail(returnedSportsEvent));
            }


            //adapter.notifyDataSetChanged();
            //adapter.notifyItemInserted(sportsEventSettingThumbnailList.size());
            //mActivity.
        } else if (requestCode == REQUESTCODE_ADDNATION && resultCode == Activity.RESULT_OK){
            Nation NewNation=new Nation();
            NewNation.setNationName(data.getStringExtra("NationName"));
            if(mainActivity.getOlympicGame().NationExists(NewNation)){
                Toast.makeText(getContext(),"国家已存在，不能重复添加",Toast.LENGTH_LONG).show();
            }else {
                NewNation.setNationCode(NationCodeGenerator.Generate(mainActivity.getOlympicGame().getNations()));
                mainActivity.getOlympicGame().addNation(NewNation);
                nationNameAdapter.addItem(new NationThumb(NewNation));
            }





        }
    }
}
