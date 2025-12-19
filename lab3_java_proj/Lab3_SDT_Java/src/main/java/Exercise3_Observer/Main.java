package Exercise3_Observer;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        // Create users
        User user1 = new User("User1");
        User user2 = new User("User2");
        User user3 = new User("User3");

        // Create followers
        Follower followerA = new Follower("FollowerA");
        Follower followerB = new Follower("FollowerB");
        Follower followerC = new Follower("FollowerC");

        // Add followers to users
        user1.addFollower(followerA);
        user1.addFollower(followerB);
        user2.addFollower(followerA);
        user2.addFollower(followerC);
        user3.addFollower(followerB);

        // Simulate random posting
        User[] users = {user1, user2, user3};
        String[] statuses = {
                "made omelette, looks delicious",
                "finished a marathon",
                "reading a new book",
                "watching a movie",
                "coding in Java"
        };

        Random random = new Random();

        // Each user posts 3 random statuses
        for (int i = 0; i < 3; i++) {
            for (User user : users) {
                String status = statuses[random.nextInt(statuses.length)];
                user.postStatus(status);
                try {
                    Thread.sleep(500); // short delay for readability
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
