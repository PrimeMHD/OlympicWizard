package chizaitongji.example.com.chizaitongji.Bean;

public class Nation {

    private String NationName;
    private int TotalScore;
    private int NationCode;

    public Nation(String nationName, int totalScore) {
        NationName = nationName;
        TotalScore = totalScore;
    }

    public Nation(String nationName) {
        NationName = nationName;
        TotalScore=0;
    }

    public Nation() {
        TotalScore=0;
        NationName="NoName";
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
