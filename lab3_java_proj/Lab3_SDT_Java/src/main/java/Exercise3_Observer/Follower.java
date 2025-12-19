package Exercise3_Observer;

public class Follower {
    private String name;

    public Follower(String name) {
        this.name = name;
    }

    public void update(String status, User user) {
        System.out.println(name + " was notified that " + user.getName() + " posted: '" + status + "'");
    }

    public String getName() {
        return name;
    }
}

