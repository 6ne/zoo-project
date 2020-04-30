import animals.Animal;
import enums.Gender;
import enums.Genus;
import util.Util;

import java.util.*;

public class ZooApplication {
  private static ZooApplication instance;
  private List<Animal> animalList;
  private Map<String, Long> zooPopulationMap;
  private int DEFAULT_SPEED = 20;

  private ZooApplication() {
    animalList = new ArrayList<>();
    zooPopulationMap = new HashMap<>();

    for (Genus genus : Genus.getValidGenus()) {
      zooPopulationMap.put(genus + "-current", 0L);
      zooPopulationMap.put(genus + "-max", 0L);
    }
  }

  private int getMenu() {
    System.out.println("+=======================+");
    System.out.println("+ Menu List             +");
    System.out.println("+=======================+");
    System.out.println("+ 1. Add Zoo Animal     +");
    System.out.println("+ 2. View Zoo Animal    +");
    System.out.println("+ 3. Take for a Walk    +");
    System.out.println("+ 4. Release Zoo Animal +");
    System.out.println("+ 5. Close Program      +");
    System.out.println("+=======================+");

    int menu;

    do {
      System.out.print("Choice >> ");
      menu = Util.scanNumber();
    } while ( menu < 1 || menu > 5 );

    return menu;
  }

  private void add() {
    String name = "";
    Gender gender = Gender.UNDEFINED;
    Genus genus = Genus.UNDEFINED;
    int speed = Integer.MIN_VALUE;

    do {
      System.out.print("What's the name [5 - 15 characters]? ");
      name = Util.scanString();
    } while ( name.length() < 5 || name.length() > 15 );

    do {
      System.out.print("What's its gender [male | female]? ");
      gender = Gender.from(Util.scanString());
    } while ( gender.isUndefined() );

    do {
      System.out.print("What's its genus [canine | feline]? ");
      genus = Genus.from(Util.scanString());
    } while ( genus.isUndefined() );

    if (genus.isCanine()) {
      do {
        System.out.print("What's its speed [1 - 80]? ");
        speed = Util.scanNumber();
      } while ( speed < 1 || speed > 80 );
    } else {
      speed = DEFAULT_SPEED;
    }

    Animal animal = Animal.from(name, gender, genus, speed);

    animalList.add(animal);

    recalculatePopulation(genus);
    System.out.println("Another animal has joined the zoo!");
  }

  private void view() {
    System.out.println("+===================================================+");
    System.out.printf(" No. | Name            | Genus  | Mood    | Gender |\n");
    System.out.println("+===================================================+");

    if (animalList.isEmpty()) {
      System.out.println("+ No Zoo Animal Spotted +");
    } else {
      int size = animalList.size();

      Animal.setMood(zooPopulationMap);

      for (int i = 0 ; i < size ; i++ ) {
        Animal animal = animalList.get(i);

        System.out.printf("| %-2d | %-15s | %-6s | %-7s | %-6s |\n",
          i+1, animal.getName(), animal.getGenus(), animal.getMood(), animal.getGender());
      }

      Animal.resetMood();
    }

    System.out.println("+===================================================+");
  }

  private void takeForWalk() {
    view();

    int animalToTake, roamingTime;

    int totalAnimal = animalList.size();

    do {
      System.out.printf("Choose animal to take for a stroll [1 - %d]: ", totalAnimal);
      animalToTake = Util.scanNumber();
    } while (animalToTake < 1 || animalToTake > totalAnimal);

    do {
      System.out.print("Roaming time [0 - 50]: ");
      roamingTime = Util.scanNumber();
    } while (roamingTime < 0 || roamingTime > 50);

    Animal animal = animalList.get(animalToTake - 1);

    if (roamingTime == 0) {
      roamingTime = 60;
    }

    animal.roam(roamingTime);

  }

  private void release() {
    view();

    int animalToTake;

    int totalAnimal = animalList.size();

    do {
      System.out.printf("Choose animal that wants to be released [1 - %d]: ", totalAnimal);
      animalToTake = Util.scanNumber();
    } while (animalToTake < 1 || animalToTake > totalAnimal);

    Animal chosenAnimal = animalList.get(--animalToTake);

    int randomAnimalIdx = (int)Math.floor(Math.random() * totalAnimal);
    Animal randomAnimal = animalList.get(randomAnimalIdx);

    if ( chosenAnimal.isCanine() && randomAnimal.isCanine() ) {
      if ( chosenAnimal.getGender().equals(randomAnimal.getGender()) ) {
        animalList.remove(animalToTake);
        System.out.println("awooo!!");
      } else {
        animalList.remove(animalToTake);
        animalList.remove(randomAnimalIdx);
        System.out.println("ruff ruff <3");
      }
    }

    if (chosenAnimal.isFeline() && randomAnimal.isFeline()) {
      if ( chosenAnimal.getGender().equals(randomAnimal.getGender()) ) {
        animalList.remove(animalToTake);
        System.out.println("hiss");
      } else {
        animalList.remove(animalToTake);
        animalList.remove(randomAnimalIdx);
        System.out.println("meow meow <3");
      }
    }

    recalculatePopulation(chosenAnimal.getGenus());
  }

  private void doAction(int menu) {
    switch (menu) {
      case 1: add();
      break;
      case 2: view();
      break;
      case 3: takeForWalk();
      break;
      case 4: release();
      break;
      case 5:
      default: System.exit(0);
    }
  }

  private void recalculatePopulation(Genus genus) {
    long currentGenus = animalList.stream()
      .filter(animal -> animal.getGenus().equals(genus))
      .count();

    long maxGenus = zooPopulationMap.get(genus + "-max");

    if (currentGenus > maxGenus) {
      maxGenus = currentGenus;
    }

    zooPopulationMap.put(genus + "-current", currentGenus);
    zooPopulationMap.put(genus + "-max", maxGenus);

  }


  public void run() {
    while (true) {

      Util.clearScreen();
      int menu = getMenu();
      Util.clearScreen();
      doAction(menu);

      Util.pressEnter();
    }
  }

  public static ZooApplication create() {
    if (Objects.isNull(instance)) {
      instance = new ZooApplication();
    }

    return instance;
  }
}
