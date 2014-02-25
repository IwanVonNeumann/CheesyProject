package panels;


public class PageHeadPanel extends CheesePanel {
    public PageHeadPanel(String id) {
        super(id);
        add(new UserStatusPanel("userStatus"));
        add(new MenuPanel("menu"));
    }
}
