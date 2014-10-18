package models.loaders;

import org.apache.wicket.model.IModel;

/**
 * Created by Iwan on 12.10.2014
 */

public interface ModelLoader {

    IModel getAddressModel();

    IModel getCartEntriesModel();

    IModel getCartsModel();

    IModel getCheesesModel();
}
