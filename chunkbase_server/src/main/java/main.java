import static spark.Spark.*;
import com.google.gson.Gson;

public class main {
    public static void main(String[] args) {
        // Use Railway's PORT environment variable, fallback to 8080
        int port = Integer.parseInt(System.getenv().getOrDefault("PORT", "8080"));
        port(port);
        
        get("/coords", (req, res) -> {
            res.type("application/json");
            return new Gson().toJson(new Coords(120, 64, -300));
        });
        
        get("/seed", (req, res) -> {
            res.type("application/json");
            return new Gson().toJson(new Seed(123456789L));
        });
        
        System.out.println("Server started on port: " + port);
    }
    
    static class Coords {
        int x, y, z;
        Coords(int x, int y, int z) {
            this.x = x; this.y = y; this.z = z;
        }
    }
    
    static class Seed {
        long seed;
        Seed(long seed) {
            this.seed = seed;
        }
    }
}
