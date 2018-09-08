package chizaitongji.example.com.chizaitongji.Bean;

import java.util.ArrayList;
import java.util.List;

public class OlympicGame {

    private List<SportsEvent> games;
    private List<Nation> nations;


    public OlympicGame(List<SportsEvent> games, List<Nation> nations) {
        this.games = games;
        this.nations = nations;
    }

    public OlympicGame() {
        games = new ArrayList<SportsEvent>();
        nations = new ArrayList<Nation>();
    }

    public void addSportsEvent(SportsEvent sportsEvent) {
        games.add(sportsEvent);
    }

    public void removeSportsEvent(String SportsEventName) {

    }

    public void addNation(Nation nation) {
        nations.add(nation);
    }

    public boolean SportsEventExists(SportsEvent sportsEvent) {
        for (SportsEvent sportsEvent1 : games) {
            if (sportsEvent1.getSportsGender() == sportsEvent.getSportsGender()
                    && sportsEvent1.getGameName().equals(sportsEvent.getGameName())
                    )
                return true;
        }
        return false;
    }

    public boolean NationExists(Nation nation) {
        for (Nation nation1 : nations) {
            if (nation1.getNationName().equals(nation.getNationName())) {
                return true;
            }
        }
        return false;
    }

    public List<SportsEvent> getGames() {
        return games;
    }

    public void setGames(List<SportsEvent> games) {
        this.games = games;
    }

    public List<Nation> getNations() {
        return nations;
    }

    public Nation getNationByName(String Name) {
        for (Nation nation : nations){
            if (nation.getNationName().equals(Name)){
                return nation;
            }
        }
        return null;

    }
    public SportsEvent getSportsEventByNameAndGender(String name,SportsEvent.SportsGender gender){
        for (SportsEvent sportsEvent:games){
            if (sportsEvent.getSportsGender()==gender&&sportsEvent.getGameName().equals(name))
                return sportsEvent;
        }
        return null;
    }


    public void setNations(List<Nation> nations) {
        this.nations = nations;
    }
}
