package my.project.race.track.pit;

import my.project.race.track.Car;
import my.project.race.track.RaceTrack;

public class PitTeam extends Thread{

    private final int id;
    private final Pit pitStops;
    private int pitStoppedCar;
    private RaceTrack raceTrack;

    public PitTeam(int id, Pit pitStop, RaceTrack raceTrack) {
     this.id = id;
     this.pitStops = pitStop;
     this.raceTrack = raceTrack;

    }

    @Override
    public void run() {
        while(true){

          Car car =  pitStops.getCar(); // this will block if we don't have a car

            //if the thread was interrupted break
            if(car == null || car.getCarId() == -1) {
                break;
            }

            try {
                // simulates service in the pit
                Thread.sleep((long) (Math.random() * 200));
            } catch (InterruptedException e) {
               Thread.currentThread().interrupt();
               break;
            }

            car.decrementPitStops();

            pitStoppedCar++;

            raceTrack.enterPit(car);

        }
    }

    public int getPitStoppedCars() {
       return pitStoppedCar;
    }
}
