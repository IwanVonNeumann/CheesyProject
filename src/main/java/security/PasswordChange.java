package security;

/**
 * Created by Iwan on 07.05.2014
 */
public class PasswordChange {

    private String oldPassword;
    private String newPassword;

    public PasswordChange() {}

    public String getOldPassword() {
        return oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
