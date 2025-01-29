public class TestDriver {
  public static void main(String[] args) {
    try {
      Class.forName("org.postgresql.Driver");
      System.out.println("Driver loaded successfully");
    } catch (Exception e) {
      System.out.println("Driver not found: " + e.getMessage());
    }
  }
}
