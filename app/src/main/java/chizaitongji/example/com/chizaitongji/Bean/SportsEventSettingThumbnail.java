package chizaitongji.example.com.chizaitongji.Bean;

import android.widget.Button;

public class SportsEventSettingThumbnail {
    private String GameName;
    private SportsEvent.SportsGender gender;
    private SportsEvent.RankValid rankValid;


    public SportsEventSettingThumbnail(String gameName, SportsEvent.SportsGender gender, SportsEvent.RankValid rankValid) {
        GameName = gameName;
        this.gender = gender;
        this.rankValid = rankValid;
    }

    public SportsEventSettingThumbnail(SportsEvent sportsEvent) {
        this.GameName=sportsEvent.getGameName();
        this.gender=sportsEvent.getSportsGender();
        this.rankValid=sportsEvent.getRankValid();
    }

    public String getGameName() {
        return GameName;
    }

    public void setGameName(String gameName) {
        GameName = gameName;
    }

    public SportsEvent.SportsGender getGender() {
        return gender;
    }

    public void setGender(SportsEvent.SportsGender gender) {
        this.gender = gender;
    }

    public SportsEvent.RankValid getRankValid() {
        return rankValid;
    }

    public void setRankValid(SportsEvent.RankValid rankValid) {
        this.rankValid = rankValid;
    }


}
