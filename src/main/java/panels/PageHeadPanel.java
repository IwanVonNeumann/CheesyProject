package panels;


import views.BasicView;

public class PageHeadPanel extends CheesePanel {
    public PageHeadPanel(String id) {
        super(id);
        add(new UserStatusPanel("userStatus"));
        add(new MenuPanel("menu"));
    }

    public BasicView getView() {
        return (BasicView) getParent();
    }
}
