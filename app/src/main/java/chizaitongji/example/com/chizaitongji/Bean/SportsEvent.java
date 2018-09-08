package chizaitongji.example.com.chizaitongji.Bean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SportsEvent {
    public enum SportsGender{MALE,FAMALE}
    public enum RankValid{THREE_VALID,FIVE_VALID}
    public static final int SCORE1IN5=7;
    public static final int SCORE2IN5=5;
    public static final int SCORE3IN5=3;
    public static final int SCORE4IN5=2;
    public static final int SCORE5IN5=1;
    public static final int SCORE1IN3=5;
    public static final int SCORE2IN3=3;
    public static final int SCORE3IN3=2;



    private String GameName;
    private SportsGender sportsGender;
    private RankValid rankValid;
    private Map<Integer,Nation> winners=new HashMap<Integer, Nation>();
    private boolean hasOutcome;

    public String getGameName() {
        return GameName;
    }

    public void setGameName(String gameName) {
        GameName = gameName;
    }

    public SportsGender getSportsGender() {
        return sportsGender;
    }

    public void setSportsGender(SportsGender sportsGender) {
        this.sportsGender = sportsGender;
    }

    public RankValid getRankValid() {
        return rankValid;
    }

    public void setRankValid(RankValid rankValid) {
        this.rankValid = rankValid;
    }

    public Map<Integer,Nation>  getWinners() {
        return winners;
    }

    public void setWinners(Map<Integer,Nation>  winners) {
        this.winners = winners;
    }

    public boolean hasOutcome() {
        return hasOutcome;
    }

    public void setHasOutcome(boolean hasOutcome) {
        this.hasOutcome = hasOutcome;
    }
}
