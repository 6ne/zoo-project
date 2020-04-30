package animals;

import enums.Mood;

public class Feline extends Animal {
  private Feline() {}

  @Override
  public Mood getMood() {
    return getFelineMood();
  }

  @Override
  public Animal setMood(long currentGenus, long maxGenus) {
    if ( currentGenus == 1 ) {
      setMood(Mood.UNHAPPY);
    } else if ( currentGenus < maxGenus ) {
      setMood(Mood.UNHAPPY);
    } else {
      setMood(Mood.HAPPY);
    }

    return this;
  }

  @Override
  public void roam(int roamingTime) {
    long randomNumber = Math.round(Math.random());
    int duration;

    if (randomNumber == 0) {
      System.out.println("Felines doesn't feel like roaming at all today...");
      return;
    }

    if (roamingTime == 60) {
      duration = 30;
    } else {
      duration = roamingTime / 2;
    }

    long totalRange = Math.round(Math.random() * duration + 1) * getSpeed();

    System.out.printf("Roaming for total range of %d in %d minutes.\n", totalRange, duration);
  }

  public static Feline create() {
    return new Feline();
  }
}
