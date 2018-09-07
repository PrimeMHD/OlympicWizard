package chizaitongji.example.com.chizaitongji.Bean;

import android.widget.TextView;
import android.widget.Toast;

public class NationThumb {
    private String NationName;
    private int TotalScore;
    private int NationCode;


    public NationThumb(String nationName, int totalScore) {
        NationName = nationName;
        TotalScore = totalScore;
    }

    public NationThumb() {
        NationName="NoName";
        TotalScore=0;
    }

    public NationThumb(String nationName) {
        NationName = nationName;
        TotalScore=0;
    }

    public NationThumb(Nation nation){
        NationName=nation.getNationName();
        TotalScore=nation.getTotalScore();
        NationCode=nation.getNationCode();
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
}
