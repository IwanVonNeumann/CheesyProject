package panels;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import war.LoginPage;

/**
 * Created by IRuskevich on 14.24.2.
 */
public class UserStatusPanel extends CheesePanel {

    public UserStatusPanel(String id) {
        super(id);

        // Profile/Logoff
        if (getCheeseSession().getAddress() == null) {
            setResponsePage(LoginPage.class);
        }

        String message = "Logged in as " +
                getCheeseSession().getAddress().getName();

        add(new Label("userID", message));

        add(new Link("logout") {
            @Override
            public void onClick() {
                getCheeseSession().logout();
                setResponsePage(LoginPage.class);
            }
        });
    }
}
