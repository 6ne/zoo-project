public class Main {
  public static void main(String[] args) {
    /*
      Polymorph => Animal to Feline & Canine
      Final Method => setMood(Mood mood) on Animal class
      Adhoc Polymorphic there's 2 setMood => setMood(long currentGenus, long maxGenus) & setMood(Mood mood)
      Overriding => roam(int roamingTime) & setMood(long currentGenus, long maxGenus) & getMood()
    * */
    ZooApplication.create().run();
  }
}
