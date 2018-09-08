package chizaitongji.example.com.chizaitongji.Bean;

public class RankBoardThumb {
    private int NationCode;
    private String NationName;
    private int TotalScore;
    private int MaleScore;
    private int FemaleScore;


    public RankBoardThumb(int nationCode, String nationName, int totalScore, int maleScore, int femaleScore) {
        NationCode = nationCode;
        NationName = nationName;
        TotalScore = totalScore;
        MaleScore = maleScore;
        FemaleScore = femaleScore;
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
