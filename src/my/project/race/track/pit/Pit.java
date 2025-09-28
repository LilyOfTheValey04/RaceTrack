package my.project.race.track.pit;

import my.project.race.track.Car;
import my.project.race.track.RaceTrack;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Pit  {
    private BlockingQueue<Car> cars ;
    private List<PitTeam> pitTeams ;
    private final AtomicInteger pitStopsForAllCars = new AtomicInteger(0);
    private static final Car POISON_PILL = new Car(-1,0,null);
    private RaceTrack raceTrack;
    //poison pill will tell the threads that the race is over
    // they can spot waiting for new cars

    public Pit(int nPitTeams, RaceTrack raceTrack) {

        this.raceTrack = raceTrack;
        this.cars = new LinkedBlockingQueue<>();
        this.pitTeams = Collections.synchronizedList(new ArrayList<>());

        for(int i = 0; i < nPitTeams; i++){
            PitTeam p = new PitTeam(i, this , raceTrack);
            pitTeams.add(p);
            p.start();
        }
    }

    public void submitCar(Car car) {
        try {
            cars.put(car);
            pitStopsForAllCars.incrementAndGet();

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public Car getCar() {
        try {
            return cars.take();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return null;
        }
    }

    public int getPitStopsCount() {
       return pitStopsForAllCars.get();
    }

    public List<PitTeam> getPitTeams() {
        return pitTeams;
    }

    public void finishRace() {
        //this is a signal for each pit team to stop
        // we don't stop the thread just signals it that the race is over
        for (int i = 0; i < pitTeams.size(); i++) {
            try {
                cars.put(POISON_PILL);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

    }
}
