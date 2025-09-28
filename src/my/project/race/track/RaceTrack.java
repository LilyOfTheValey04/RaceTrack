package my.project.race.track;

import my.project.race.track.pit.Pit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RaceTrack implements Track{

    private final Pit pit ;
    private List<Car> finishedCars = Collections.synchronizedList(new ArrayList<>());


    public RaceTrack(int nTeamsInBox){
        this.pit = new Pit(nTeamsInBox, this);
    }

    @Override
    public void enterPit(Car car) {
    // racetrack decides if the car is finished or if the car need to be submitted to pit
    if( car.getNPitStops() == 0){
     System.out.println("The car " +car.getCarId()+ " has finished the the race");
     finishedCars.add(car);
    }

    else {
        pit.submitCar(car);
     }

    }

    @Override
    public int getNumberOfFinishedCars() {
        return finishedCars.size();
    }

    @Override
    public List<Integer> getFinishedCarsIds() {
       return finishedCars.stream()
               .map(Car::getCarId)
               .toList();
    }

    @Override
    public Pit getPit() {
     return  pit;
    }
}
