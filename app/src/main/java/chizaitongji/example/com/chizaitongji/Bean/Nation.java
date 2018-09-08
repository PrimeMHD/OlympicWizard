package chizaitongji.example.com.chizaitongji.Bean;

import java.util.HashMap;
import java.util.Map;

public class Nation {

    private String NationName;
    private int TotalScore;
    private int NationCode;
    private Map<String,Integer>HonorBoard;
    private int MaleScore=0;
    private int FemaleScore=0;


    public Nation(String nationName, int totalScore) {
        NationName = nationName;
        TotalScore = totalScore;
        HonorBoard=new HashMap<>();
    }

    public Nation(String nationName) {
        NationName = nationName;
        TotalScore=0;
        HonorBoard=new HashMap<>();

    }

    public Nation() {
        TotalScore=0;
        NationName="NoName";
        HonorBoard=new HashMap<>();

    }

    public void addTotalScore(int AddScore){
        TotalScore+=AddScore;
    }
    public void addMaleScore(int AddScore){
        MaleScore+=AddScore;
    }

    public void addFemaleScore(int AddScore){
        FemaleScore+=AddScore;
    }

    public int getNationCode() {
        return NationCode;
    }

    public void setNationCode(int nationCode) {
        NationCode = nationCode;
    }

    public String getNationName() {
        return NationName;
    }

    public void setNationName(String nationName) {
        NationName = nationName;
    }

    public int getTotalScore() {
        return TotalScore;
    }

    public void setTotalScore(int totalScore) {
        TotalScore = totalScore;
    }

    public boolean isGameOnHonorBoard(String GameNamePlus){

        return HonorBoard.containsKey(GameNamePlus);
    }
    public void addOnHonorBoard(String GameNamePlus,int addScore){
        if(HonorBoard.containsKey(GameNamePlus)){
            HonorBoard.put(GameNamePlus,HonorBoard.get(GameNamePlus)+addScore);
        }else {
            HonorBoard.put(GameNamePlus,addScore);
        }
    }

    public Map<String, Integer> getHonorBoard() {
        return HonorBoard;
    }

    public int getMaleScore() {
        return MaleScore;
    }

    public void setMaleScore(int maleScore) {
        MaleScore = maleScore;
    }

    public int getFemaleScore() {
        return FemaleScore;
    }

    public void setFemaleScore(int femaleScore) {
        FemaleScore = femaleScore;
    }
}
