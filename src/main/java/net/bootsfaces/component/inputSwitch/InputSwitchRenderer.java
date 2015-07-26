package net.bootsfaces.component.inputSwitch;

import net.bootsfaces.render.CoreRenderer;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.render.FacesRenderer;
import java.io.IOException;

/**
 * Created by yeraysb on 25/07/15.
 */
@FacesRenderer(componentFamily = InputSwitch.COMPONENT_FAMILY, rendererType = InputSwitch.DEFAULT_RENDERER)
public class InputSwitchRenderer extends CoreRenderer{

    @Override
    public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
        if (!component.isRendered()) {
            return;
        }

    }

    @Override
    public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
    }
}
