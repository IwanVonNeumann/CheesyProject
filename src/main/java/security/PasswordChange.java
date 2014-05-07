package security;

/**
 * Created by Iwan on 07.05.2014
 */
public class PasswordChange {

    private String oldPassword;
    private String newPassword1;
    private String newPassword2;

    public PasswordChange() {}

    public String getOldPassword() {
        return oldPassword;
    }

    public String getNewPassword1() {
        return newPassword1;
    }

    public String getNewPassword2() {
        return newPassword2;
    }


    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public void setNewPassword1(String newPassword1) {
        this.newPassword1 = newPassword1;
    }

    public void setNewPassword2(String newPassword2) {
        this.newPassword2 = newPassword2;
    }

    public boolean passwordsEqual() {
        return newPassword1.equals(newPassword2);
    }
}
