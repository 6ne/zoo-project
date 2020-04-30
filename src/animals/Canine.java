package animals;

import enums.Mood;

public class Canine extends Animal {
  private Canine() {}

  @Override
  public Mood getMood() {
    return getCanineMood();
  }

  @Override
  public Animal setMood(long currentGenus, long maxGenus) {
    if ( currentGenus == 1 ) {
      setMood(Mood.HAPPY);
    } else if ( currentGenus < maxGenus ) {
      setMood(Mood.HAPPY);
    } else {
      setMood(Mood.UNHAPPY);
    }

    return this;
  }

  @Override
  public void roam(int roamingTime) {
    int duration = roamingTime;

    long totalRange = Math.round(Math.random() * duration + 1) * getSpeed();

    System.out.printf("Roaming for total range of %d in %d minutes.\n", totalRange, duration);
  }

  public static Canine create() {
    return new Canine();
  }
}
