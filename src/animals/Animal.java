package animals;

import enums.Gender;
import enums.Genus;
import enums.Mood;

import java.util.Map;

public abstract class Animal {
  private String name;
  private Gender gender;
  private Genus genus;
  private int speed;
  private static Mood canineMood;
  private static Mood felineMood;

  // <editor-fold desc="setter-getter">
  public String getName() {
    return name;
  }

  public Animal setName(String name) {
    this.name = name;
    return this;
  }

  public Gender getGender() {
    return gender;
  }

  public Animal setGender(Gender gender) {
    this.gender = gender;
    return this;
  }

  public boolean isCanine() {
    return getGenus().isCanine();
  }

  public boolean isFeline() {
    return getGenus().isFeline();
  }

  public Genus getGenus() {
    return genus;
  }

  public Animal setGenus(Genus genus) {
    this.genus = genus;
    return this;
  }

  public int getSpeed() {
    return speed;
  }

  public Animal setSpeed(int speed) {
    this.speed = speed;
    return this;
  }

  public final Animal setMood(Mood mood) {
    switch (genus) {
      case CANINE: canineMood = mood;
      break;
      case FELINE: felineMood = mood;
      break;
    }

    return this;
  }

  public static Mood getCanineMood() {
    return canineMood;
  }

  public static Mood getFelineMood() {
    return felineMood;
  }
  // </editor-fold desc="setter-getter">

  // <editor-fold desc="abstract">
  public abstract Mood getMood();
  public abstract Animal setMood(long currentGenus, long maxGenus);
  public abstract void roam(int roamingTime);
  // </editor-fold desc="abstract">

  // <editor-fold desc="static">
  public static void setMood(Map<String, Long> zooPopulationMap) {
    for (Genus genus: Genus.getValidGenus()) {
      long currentGenus = zooPopulationMap.get(genus + "-current");
      long maxGenus = zooPopulationMap.get(genus + "-max");

      Animal animal = genus.toAnimal();
      animal.setMood(currentGenus, maxGenus);
    }
  }

  public static void resetMood() {
    canineMood = Mood.UNDEFINED;
    felineMood = Mood.UNDEFINED;
  }

  public static Animal from(String name, Gender gender, Genus genus, int speed) {
    Animal animal;

    if (genus.isCanine()) {
      animal = Canine.create();
    } else {
      animal = Feline.create();
    }

    return animal.setName(name)
      .setGender(gender)
      .setGenus(genus)
      .setSpeed(speed);
  }
  // </editor-fold desc="static">

  @Override
  public String toString() {
    return String.format("%-15s | %-6s | %-7s | %-6s", name, genus, "unhappy", gender);
  }
}
