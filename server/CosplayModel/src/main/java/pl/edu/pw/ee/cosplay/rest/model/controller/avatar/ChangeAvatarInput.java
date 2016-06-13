package pl.edu.pw.ee.cosplay.rest.model.controller.avatar;

import pl.edu.pw.ee.cosplay.rest.model.security.AuthenticationData;

import java.io.Serializable;

/**
 * Created by Micha³ on 2016-06-13.
 */
public class ChangeAvatarInput implements Serializable {
    private AuthenticationData authenticationData;
    private byte[] avatarBinaryData;

    public AuthenticationData getAuthenticationData() {
        return authenticationData;
    }

    public void setAuthenticationData(AuthenticationData authenticationData) {
        this.authenticationData = authenticationData;
    }

    public byte[] getAvatarBinaryData() {
        return avatarBinaryData;
    }

    public void setAvatarBinaryData(byte[] avatarBinaryData) {
        this.avatarBinaryData = avatarBinaryData;
    }
}
