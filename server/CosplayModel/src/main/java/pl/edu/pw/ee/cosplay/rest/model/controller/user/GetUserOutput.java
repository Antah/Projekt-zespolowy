package pl.edu.pw.ee.cosplay.rest.model.controller.user;

import java.io.Serializable;
import java.util.HashSet;

/**
 * Created by Micha³ on 2016-06-13.
 */
public class GetUserOutput implements Serializable {
    private byte[] avatarBinaryData;
    private String username;
    private HashSet<String> observerOf = new HashSet<>();
    private HashSet<String> observedBy = new HashSet<>();

    public HashSet<String> getObserverOf() {
        return observerOf;
    }

    public void setObserverOf(HashSet<String> observerOf) {
        this.observerOf = observerOf;
    }

    public HashSet<String> getObservedBy() {
        return observedBy;
    }

    public void setObservedBy(HashSet<String> observedBy) {
        this.observedBy = observedBy;
    }

    public byte[] getAvatarBinaryData() {
        return avatarBinaryData;
    }

    public void setAvatarBinaryData(byte[] avatarBinaryData) {
        this.avatarBinaryData = avatarBinaryData;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
