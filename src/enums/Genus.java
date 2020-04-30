package enums;

import animals.Animal;
import animals.Canine;
import animals.Feline;
import util.Util;

import java.util.Arrays;
import java.util.List;

public enum Genus {
  CANINE, FELINE, UNDEFINED;

  public boolean isCanine() {
    return this.equals(CANINE);
  }

  public boolean isFeline() {
    return this.equals(FELINE);
  }

  public boolean isUndefined() {
    return this.equals(UNDEFINED);
  }

  public Animal toAnimal() {
    Animal animal;

    if (isFeline()) {
      animal = Feline.create();
    } else {
      animal = Canine.create();
    }

    return animal.setGenus(this);
  }

  public static Genus from(String genus) {
    if (Util.isEmpty(genus)) {
      return UNDEFINED;
    }

    switch (genus) {
      case "canine": return CANINE;
      case "feline": return FELINE;
    }

    return UNDEFINED;
  }

  public static List<Genus> getValidGenus() {
    return Arrays.asList(CANINE, FELINE);
  }

  @Override
  public String toString() {
    return super.toString().toLowerCase();
  }
}
