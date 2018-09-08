package chizaitongji.example.com.chizaitongji.Fragment.Fragment_SecondGroup;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import chizaitongji.example.com.chizaitongji.Activity.MainActivity;
import chizaitongji.example.com.chizaitongji.Adapter.RankBoardAdapter;
import chizaitongji.example.com.chizaitongji.Bean.Nation;
import chizaitongji.example.com.chizaitongji.Bean.RankBoardThumb;
import chizaitongji.example.com.chizaitongji.R;
import me.yokeyword.fragmentation.SupportFragment;

public class Fragment_RootSecond_PagerFirst extends SupportFragment {

    protected Context mContext;
    private MainActivity mainActivity;
    private static final String TAG = "Fragment_RootSecond_PagerFirst";
    private RankKeyType rankKeyType = RankKeyType.NationCode;
    private RankBoardAdapter adapter;
    private RankSequence rankSequence=RankSequence.SmallToBig;
    private Button button_setRankSeqSTB;
    private Button button_setRankSeqBTS;





    private enum RankSequence{SmallToBig,BigToSmall}
    private enum RankKeyType {NationCode, TotalScore, MaleScore, FemaleScore}

    private RadioGroup radioGroup_RankTypeSelector;
    private List<RankBoardThumb> RankedrankBoardThumbList;


    public Fragment_RootSecond_PagerFirst() {
        // Required empty public constructor
    }

    @Override
    public void onSupportVisible() {
        super.onSupportVisible();

        refreshThumbList();
    }

    // TODO: Rename and change types and number of parameters
    public static Fragment_RootSecond_PagerFirst newInstance() {
        Fragment_RootSecond_PagerFirst fragment = new Fragment_RootSecond_PagerFirst();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Log.d(TAG,"on Create");
        mainActivity = (MainActivity) getActivity();
        RankedrankBoardThumbList = new ArrayList<RankBoardThumb>();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.layout_fragment_rootsecond_pagerfirst, container, false);

        radioGroup_RankTypeSelector = (RadioGroup) v.findViewById(R.id.radioGroup_RankTypeSelector);

        button_setRankSeqSTB=(Button)v.findViewById(R.id.button_setRankSeqSTB);
        button_setRankSeqBTS=(Button)v.findViewById(R.id.button_setRankSeqBTS);





        RecyclerView recyclerview_rankBoard = (RecyclerView) v.findViewById(R.id.recyclerview_rankBoard);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        recyclerview_rankBoard.setLayoutManager(layoutManager);
        adapter = new RankBoardAdapter();

        recyclerview_rankBoard.setAdapter(adapter);


        initView();
        refreshThumbList();

        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    private void refreshThumbList() {

        RankedrankBoardThumbList.clear();
        for(Nation nation:mainActivity.getOlympicGame().getNations()){
            RankedrankBoardThumbList.add(new RankBoardThumb(nation.getNationCode(),nation.getNationName(),nation.getTotalScore(),nation.getMaleScore(),nation.getFemaleScore()));
        }





        Collections.sort(RankedrankBoardThumbList, new Comparator<RankBoardThumb>() {
            @Override
            public int compare(RankBoardThumb o1, RankBoardThumb o2) {

                if(rankSequence==RankSequence.SmallToBig){
                    switch (rankKeyType) {
                        case MaleScore:
                            return o1.getMaleScore() - o2.getMaleScore();
                        case NationCode:
                            return o1.getNationCode() - o2.getNationCode();
                        case TotalScore:
                            return o1.getTotalScore() - o2.getTotalScore();
                        case FemaleScore:
                            return o1.getFemaleScore() - o2.getFemaleScore();
                        default:
                            return 0;

                    }
                }else{
                    switch (rankKeyType) {
                        case MaleScore:
                            return o2.getMaleScore() - o1.getMaleScore();
                        case NationCode:
                            return o2.getNationCode() - o1.getNationCode();
                        case TotalScore:
                            return o2.getTotalScore() - o1.getTotalScore();
                        case FemaleScore:
                            return o2.getFemaleScore() - o1.getFemaleScore();
                        default:
                            return 0;

                    }
                }





            }
        });
        adapter.setData(RankedrankBoardThumbList);
        adapter.notifyDataSetChanged();


    }

    public void initView() {

        radioGroup_RankTypeSelector.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radioButton_RankByNationCode) {
                    rankKeyType = RankKeyType.NationCode;
                    refreshThumbList();
                } else if (checkedId == R.id.radioButton_RankByTotalScore) {
                    rankKeyType = RankKeyType.TotalScore;
                    refreshThumbList();
                } else if (checkedId == R.id.radioButton_RankByMaleScore) {
                    rankKeyType = RankKeyType.MaleScore;
                    refreshThumbList();
                } else if (checkedId == R.id.radioButton_RankByFemaleScore) {
                    rankKeyType = RankKeyType.FemaleScore;
                    refreshThumbList();
                }
            }
        });
        ((RadioButton) radioGroup_RankTypeSelector.getChildAt(1)).setChecked(true);


        button_setRankSeqSTB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rankSequence=RankSequence.SmallToBig;
                refreshThumbList();
            }
        });
        button_setRankSeqBTS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rankSequence=RankSequence.BigToSmall;
                refreshThumbList();
            }
        });

    }

}
