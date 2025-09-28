import my.project.race.track.Car;
import my.project.race.track.RaceTrack;
import my.project.race.track.pit.PitTeam;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws InterruptedException {
        RaceTrack raceTrack = new RaceTrack(2);

        Thread [] cars = new Thread[5];
        for(int i = 0; i < cars.length;i++){

            int pitStops = 1 + (int) (Math.random() * 3);
            Car car = new Car(i, pitStops ,raceTrack);
            cars[i] = new Thread(car,"Car-" + i);
            cars[i].start();
        }

        //wait until all cars finish
        for(Thread car : cars){
            car.join();
        }

        raceTrack.getPit().finishRace();

        //we give time to the pit teams to finish
        Thread.sleep(1000);

        System.out.println("Общо завършили коли: " + raceTrack.getNumberOfFinishedCars());
        System.out.println("ID на завършилите коли: " + raceTrack.getFinishedCarsIds());

        System.out.println("Общо pit стопове: " + raceTrack.getPit().getPitStopsCount());

        for(PitTeam team : raceTrack.getPit().getPitTeams()){
            System.out.println("PitTeam " + team.getName() + "обслужи " + team.getPitStoppedCars() + " коли");
        }
    }
}