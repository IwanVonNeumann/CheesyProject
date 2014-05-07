package domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Iwan on 26.04.2014
 */

// TODO: исправить несоответствие между строкой и константой (DropDown)
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

    public static Title getConstant(String value) {
        for (Title title : Title.values()) {
            if (title.toString().equals(value)) return title;
        }
        throw new IllegalArgumentException(
                "[Java.Enum] No constant with text '" + value + "' found for Title...");
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