package chizaitongji.example.com.chizaitongji.Fragment.Fragment_SecondGroup;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import chizaitongji.example.com.chizaitongji.Activity.MainActivity;
import chizaitongji.example.com.chizaitongji.Bean.Nation;
import chizaitongji.example.com.chizaitongji.Bean.SportsEvent;
import chizaitongji.example.com.chizaitongji.R;
import me.yokeyword.fragmentation.SupportFragment;

public class Fragment_RootSecond_PagerSecond extends SupportFragment {

    protected Context mContext;
    private static final String TAG = "Fragment_RootSecond_PagerSecond";
    private MainActivity mainActivity;

    private EditText editText_NationNameToSearch;
    private EditText editText_GameNameToSearch;
    private EditText editText_GameAloneToSearch;
    private Button button_searchWhithNationGame;
    private Button button_searchWhithGame;
    private TextView textView_SearchResult1;
    private TextView textView_SearchResult2;
    private Spinner spinner_select_gameAlongGendeToSearch;
    private Spinner spinner_select_gamegendeToSearch;
    private String GameNameToSearch;
    private String NationNameToSearch;
    private String GameAloneToSearch;
    private int SearchArea1Gender;
    private int SearchArea2Gender;


    public Fragment_RootSecond_PagerSecond() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static Fragment_RootSecond_PagerSecond newInstance() {
        Fragment_RootSecond_PagerSecond fragment = new Fragment_RootSecond_PagerSecond();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Log.d(TAG,"on Create");

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.layout_fragment_rootsecond_pagersecond, container, false);
        editText_NationNameToSearch = v.findViewById(R.id.editText_NationNameToSearch);
        editText_GameNameToSearch = v.findViewById(R.id.editText_GameNameToSearch);
        editText_GameAloneToSearch = v.findViewById(R.id.editText_GameAloneToSearch);
        button_searchWhithNationGame = v.findViewById(R.id.button_searchWhithNationGame);
        button_searchWhithGame = v.findViewById(R.id.button_searchWhithGame);
        textView_SearchResult1 = v.findViewById(R.id.textView_SearchResult1);
        textView_SearchResult2 = v.findViewById(R.id.textView_SearchResult2);
        spinner_select_gameAlongGendeToSearch = v.findViewById(R.id.spinner_select_gameAlongGendeToSearch);
        spinner_select_gamegendeToSearch = v.findViewById(R.id.spinner_select_gamegendeToSearch);


        initView();
        return v;
    }

    private void initView() {
        button_searchWhithNationGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText_GameNameToSearch.getText().toString().trim().equals("") ||
                        editText_NationNameToSearch.getText().toString().trim().equals("")
                        ) {
                    Toast.makeText(getContext(), "查询条件不完整!", Toast.LENGTH_LONG).show();
                    return;
                }

                GameNameToSearch = editText_GameNameToSearch.getText().toString();
                NationNameToSearch = editText_NationNameToSearch.getText().toString();
                SportsEvent.SportsGender GenderToSearch;
                SportsEvent sportsEvent = new SportsEvent();
                sportsEvent.setGameName(GameNameToSearch);
                if (SearchArea1Gender == 1) {
                    sportsEvent.setSportsGender(SportsEvent.SportsGender.MALE);
                    GenderToSearch = SportsEvent.SportsGender.MALE;
                } else {
                    sportsEvent.setSportsGender(SportsEvent.SportsGender.FAMALE);
                    GenderToSearch = SportsEvent.SportsGender.FAMALE;

                }

                if (!mainActivity.getOlympicGame().SportsEventExists(sportsEvent)) {
                    Toast.makeText(getContext(), "没有搜到指定比赛！", Toast.LENGTH_LONG).show();
                    textView_SearchResult1.setText("没有搜到指定比赛！");
                    return;
                }
                Nation nation = mainActivity.getOlympicGame().getNationByName(NationNameToSearch);
                if (nation == null) {
                    textView_SearchResult1.setText("没有搜到指定国家！");
                    Toast.makeText(getContext(), "没有搜到指定比赛！", Toast.LENGTH_LONG).show();
                    return;
                }

                if (!mainActivity.getOlympicGame().getSportsEventByNameAndGender(GameNameToSearch, GenderToSearch).hasOutcome()) {
                    textView_SearchResult1.setText(GameNameToSearch + "还没有比完");
                    return;
                }


                if (!nation.getHonorBoard().containsKey(GameNameToSearch + SearchArea1Gender)) {
                    textView_SearchResult1.setText(NationNameToSearch + "没有在" + GameNameToSearch + "中获奖");
                } else {
                    textView_SearchResult1.setText(NationNameToSearch + "在" + GameNameToSearch + "中获得的积分为" + nation.getHonorBoard().get(GameNameToSearch + SearchArea1Gender));
                }
            }


        });


        button_searchWhithGame.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (editText_GameAloneToSearch.getText().toString().trim().equals("")) {
                    Toast.makeText(getContext(), "查询条件不完整!", Toast.LENGTH_LONG).show();
                    return;
                }
                GameAloneToSearch=editText_GameAloneToSearch.getText().toString();
                SportsEvent sportsEvent = new SportsEvent();
                sportsEvent.setGameName(GameAloneToSearch);
                if (SearchArea2Gender == 1) {
                    sportsEvent.setSportsGender(SportsEvent.SportsGender.MALE);
                } else {
                    sportsEvent.setSportsGender(SportsEvent.SportsGender.FAMALE);
                }


                if (!mainActivity.getOlympicGame().SportsEventExists(sportsEvent)) {
                    Toast.makeText(getContext(), "没有搜到指定比赛！", Toast.LENGTH_LONG).show();
                    textView_SearchResult2.setText("没有搜到指定比赛！");
                    return;
                }

                SportsEvent resultSportEvent=mainActivity.getOlympicGame().getSportsEventByNameAndGender(GameAloneToSearch, sportsEvent.getSportsGender());

                if (!resultSportEvent.hasOutcome()) {
                    textView_SearchResult2.setText(GameAloneToSearch + "还没有比完");
                    return;
                }


                if (resultSportEvent.getRankValid()== SportsEvent.RankValid.FIVE_VALID){
                    textView_SearchResult2.setText(getResources().getString(R.string.FirstPrize)+"："+resultSportEvent.getWinners().get(1).getNationName()+"\n");
                    textView_SearchResult2.append(getResources().getString(R.string.SecondPrize)+"："+resultSportEvent.getWinners().get(2).getNationName()+"\n");
                    textView_SearchResult2.append(getResources().getString(R.string.ThirdPrize)+"："+resultSportEvent.getWinners().get(3).getNationName()+"\n");
                    textView_SearchResult2.append(getResources().getString(R.string.FourthPrize)+"："+resultSportEvent.getWinners().get(4).getNationName()+"\n");
                    textView_SearchResult2.append(getResources().getString(R.string.FifthPrize)+"："+resultSportEvent.getWinners().get(5).getNationName()+"\n");
                }else {
                    textView_SearchResult2.setText(getResources().getString(R.string.FirstPrize)+"："+resultSportEvent.getWinners().get(1).getNationName()+"\n");
                    textView_SearchResult2.append(getResources().getString(R.string.SecondPrize)+"："+resultSportEvent.getWinners().get(2).getNationName()+"\n");
                    textView_SearchResult2.append(getResources().getString(R.string.ThirdPrize)+"："+resultSportEvent.getWinners().get(3).getNationName()+"\n");
                }






            }
        });


        spinner_select_gameAlongGendeToSearch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] genders = getResources().getStringArray(R.array.GameGenders);
                if (genders[position].equals("男子项目"))
                    SearchArea2Gender = 1;
                else if (genders[position].equals("女子项目")) {
                    SearchArea2Gender = 0;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner_select_gamegendeToSearch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] genders = getResources().getStringArray(R.array.GameGenders);
                if (genders[position].equals("男子项目"))
                    SearchArea1Gender = 1;
                else if (genders[position].equals("女子项目")) {
                    SearchArea1Gender = 0;

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
        mainActivity = (MainActivity) mContext;
    }


}
