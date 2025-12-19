package Exercise3_Observer;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String name;
    private List<Follower> followers = new ArrayList<>();

    public User(String name) {
        this.name = name;
    }

    public void addFollower(Follower follower) {
        followers.add(follower);
    }

    public void removeFollower(Follower follower) {
        followers.remove(follower);
    }

    public void postStatus(String status) {
        System.out.println(name + " posted: " + status);
        notifyFollowers(status);
    }

    private void notifyFollowers(String status) {
        for (Follower f : followers) {
            f.update(status, this);
        }
    }

    public String getName() {
        return name;
    }
}
