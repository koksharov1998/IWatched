import static spark.Spark.*;

public class Main {
  public static void main(String[] args) {
    get("/hello", (request, response) -> "hello world!");
    // System.out.print("Hello world!");
  }
}
