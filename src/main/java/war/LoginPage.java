package war;

import panels.login.AuthenticationPanel;
import panels.login.AvailableUsersPanel;

public class LoginPage extends CheesePage {

    public LoginPage() {

        add(new AuthenticationPanel("authentication"));
        add(new AvailableUsersPanel("available-users"));
    }
}
