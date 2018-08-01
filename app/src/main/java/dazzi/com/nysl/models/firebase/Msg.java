package dazzi.com.nysl.models.firebase;

public class Msg {

    private String displayName;
    private String msg;
    private long timestamp;
    private String user;

    public Msg(String displayName, String msg, int timestamp, String user) {
        this.displayName = displayName;
        this.msg = msg;
        this.timestamp = timestamp;
        this.user = user;
    }

    public Msg() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getMsg() {
        return msg;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getUser() {
        return user;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
