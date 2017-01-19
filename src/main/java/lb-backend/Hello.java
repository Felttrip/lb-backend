package lb_backend;

import static spark.Spark.get;

public class Hello {

    public static void main(String[] args) {
        get("/", (req, res) -> {
            return "Hello World";
        });
    }
}