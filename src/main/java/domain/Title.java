package domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Iwan on 26.04.2014
 */

public enum Title {
    MR("Mr."), MS("Ms."), MRS("Mrs."), DR("Dr.");

    private String state;

    Title(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return state;
    }

    public static List<String> toStringArray() {
        ArrayList<String> result =
                new ArrayList<>(Title.values().length);
        for (Title title : Title.values()) {
            result.add(title.toString());
        }
        return result;
    }
}