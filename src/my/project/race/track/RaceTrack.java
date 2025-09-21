package my.project.race.track;

import my.project.race.track.pit.Pit;

import java.util.List;

public class RaceTrack implements Track{
    private final int nTeamsInBox;

    public RaceTrack(int nTeamsInBox){
        this.nTeamsInBox = nTeamsInBox;

    }

    @Override
    public void enterPit(Car car) {

    }

    @Override
    public int getNumberOfFinishedCars() {
        return 0;
    }

    @Override
    public List<Integer> getFinishedCarsIds() {
        return List.of();
    }

    @Override
    public Pit getPit() {
        return null;
    }
}
