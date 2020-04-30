package enums;

public enum Mood {
  HAPPY, UNHAPPY, UNDEFINED;

  @Override
  public String toString() {
    return super.toString().toLowerCase();
  }
}
