package chizaitongji.example.com.chizaitongji.Bean;

import java.util.List;

public class SportsEvent {
    public enum SportsGender{MALE,FAMALE}
    public enum RankValid{THREE_VALID,FIVE_VALID}
    private String GameName;
    private SportsGender sportsGender;
    private RankValid rankValid;
    private List<Nation> winners;
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

    public List<Nation> getWinners() {
        return winners;
    }

    public void setWinners(List<Nation> winners) {
        this.winners = winners;
    }

    public boolean hasOutcome() {
        return hasOutcome;
    }

    public void setHasOutcome(boolean hasOutcome) {
        this.hasOutcome = hasOutcome;
    }
}
