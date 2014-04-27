package look;

import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.MarkupStream;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.IModel;

/**
 * Created by Iwan on 26.04.2014
 */
public class HashLabel extends Label {

    private int width;

    public HashLabel(String id, int width) {
        super(id);
        this.width = width;
    }

    public HashLabel(String id, String label) {
        super(id, label);
    }

    public HashLabel(String id, IModel model) {
        super(id, model);
    }

    @Override
    protected void onComponentTagBody(MarkupStream markupStream, ComponentTag openTag) {
        String formattedHash;

        byte[] hash = (byte[]) getModelObject();

        if (hash != null) {
            String symbol = "<br/>";

            StringBuilder sb = new StringBuilder();
            for (int i = 1; i <= hash.length; i++) {
                sb.append(String.format("%02X ", hash[i - 1]));
                if ((i % width == 0) & (i != hash.length))
                    sb.append(symbol);
            }
            formattedHash = sb.toString();
        } else {
            formattedHash = "[empty hash]";
        }

        replaceComponentTagBody(markupStream, openTag, formattedHash);
    }
}