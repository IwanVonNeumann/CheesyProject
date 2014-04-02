package models;

import domain.Cheese;
import org.apache.wicket.model.CompoundPropertyModel;

/**
 * Created by Iwan on 02.04.2014
 */

public class CheesePropertyModel extends CompoundPropertyModel {

    public CheesePropertyModel(Cheese cheese) {
        super(cheese);

    }
}
