package my.project.race.track;

import java.util.concurrent.atomic.AtomicInteger;

public class Car implements Runnable {

    private final int id;
    private final Track track;
    private int nPitStops;


    public Car(int id, int nPitStops, Track track) {
        this.id = id;
        this.nPitStops = nPitStops;
        this.track = track;
    }

    @Override
    public void run() {

           try {
               // with sleep  simulate racing car
               Thread.sleep((long) (Math.random() * 1000));
               // car need to ender the pit
               track.enterPit(this);

           } catch (InterruptedException e) {
               throw new RuntimeException(e);
           }

    }

    public int getCarId() {
        return id;
    }

    public synchronized int getNPitStops() {
        return nPitStops;
    }

    public Track getTrack() {
        return track;
    }

    public synchronized void decrementPitStops() {
        if (nPitStops > 0) {
            nPitStops--;
        }

    }

}
