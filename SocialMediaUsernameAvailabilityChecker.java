import java.util.*;

public class SocialMediaUsernameAvailabilityChecker {

    // username -> userId
    private HashMap<String, Integer> users = new HashMap<>();

    // username -> number of attempts
    private HashMap<String, Integer> attempts = new HashMap<>();

    // constructor (simulate existing users)
    public SocialMediaUsernameAvailabilityChecker() {
        users.put("john_doe", 101);
        users.put("alice", 102);
        users.put("admin", 103);
    }

    // check availability
    public boolean checkAvailability(String username) {

        // increase attempt count
        attempts.put(username, attempts.getOrDefault(username, 0) + 1);

        return !users.containsKey(username);
    }

    // suggest alternative usernames
    public List<String> suggestAlternatives(String username) {

        List<String> suggestions = new ArrayList<>();

        for (int i = 1; i <= 3; i++) {
            String suggestion = username + i;
            if (!users.containsKey(suggestion)) {
                suggestions.add(suggestion);
            }
        }

        suggestions.add(username.replace("_", "."));

        return suggestions;
    }

    // get most attempted username
    public String getMostAttempted() {

        String most = "";
        int max = 0;

        for (String key : attempts.keySet()) {
            if (attempts.get(key) > max) {
                max = attempts.get(key);
                most = key;
            }
        }

        return most + " (" + max + " attempts)";
    }

    public static void main(String[] args) {

        SocialMediaUsernameAvailabilityChecker checker =
                new SocialMediaUsernameAvailabilityChecker();

        System.out.println("john_doe available? " +
                checker.checkAvailability("john_doe"));

        System.out.println("jane_smith available? " +
                checker.checkAvailability("jane_smith"));

        System.out.println("Suggestions for john_doe: " +
                checker.suggestAlternatives("john_doe"));

        // simulate more attempts
        checker.checkAvailability("admin");
        checker.checkAvailability("admin");
        checker.checkAvailability("admin");

        System.out.println("Most attempted username: " +
                checker.getMostAttempted());
    }
}