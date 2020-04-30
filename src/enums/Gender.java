package enums;

import util.Util;

public enum Gender {
  MALE, FEMALE, UNDEFINED;

  public boolean isUndefined() {
    return this.equals(UNDEFINED);
  }

  public static Gender from(String gender) {
    if (Util.isEmpty(gender)) {
      return UNDEFINED;
    }

    switch (gender) {
      case "male": return MALE;
      case "female": return FEMALE;
    }

    return UNDEFINED;
  }

  @Override
  public String toString() {
    return super.toString().toLowerCase();
  }
}
