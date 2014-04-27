package war;

import domain.Address;
import domain.Title;
import org.apache.wicket.model.CompoundPropertyModel;
import panels.RegisterUserPanel;

/**
 * Created by IRuskevich on 14.28.2
 */
public class SignupPage extends CheesePage {

    public SignupPage() {

        add(new RegisterUserPanel("registration",
                new CompoundPropertyModel(new Address())));
    }
}
