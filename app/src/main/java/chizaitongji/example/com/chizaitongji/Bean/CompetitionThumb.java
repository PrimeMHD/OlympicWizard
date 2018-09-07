package chizaitongji.example.com.chizaitongji.Bean;

public class CompetitionThumb {
    private SportsEvent sportsEvent;
    private boolean hasOutcome;


    public CompetitionThumb(SportsEvent sportsEvent, boolean hasOutcome) {
        this.sportsEvent = sportsEvent;
        this.hasOutcome = hasOutcome;
    }

    public CompetitionThumb() {
    }

    public SportsEvent getSportsEvent() {
        return sportsEvent;
    }

    public void setSportsEvent(SportsEvent sportsEvent) {
        this.sportsEvent = sportsEvent;
    }

    public boolean hasOutcome() {
        return hasOutcome;
    }

    public void setHasOutcome(boolean hasOutcome) {
        this.hasOutcome = hasOutcome;
    }
}
