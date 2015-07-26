package net.bootsfaces.component.inputSwitch;

import net.bootsfaces.component.SelectBooleanCheckbox;
import net.bootsfaces.render.Tooltip;

import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.component.FacesComponent;
import javax.faces.component.html.HtmlInputText;

/**
 *
 */
@ResourceDependencies({ @ResourceDependency(library = "bsf", name = "css/core.css", target = "head"),
        
        })
@FacesComponent("net.bootsfaces.component.inputSwitch.InputSwitch")
public class InputSwitch extends HtmlInputText implements net.bootsfaces.render.IHasTooltip {

    public static final String COMPONENT_TYPE = "net.bootsfaces.component.inputSwitch.InputSwitch";

    public static final String COMPONENT_FAMILY = "net.bootsfaces.component";

    public static final String DEFAULT_RENDERER = "net.bootsfaces.component.inputSwitch.InputSwitch";

    public InputSwitch() {
        Tooltip.addResourceFile();
        setRendererType(DEFAULT_RENDERER);
    }

    public String getFamily() {
        return COMPONENT_FAMILY;
    }


    protected enum PropertyKeys {
        tooltip,
        tooltipDelay,
        tooltipDelayHide,
        tooltipDelayShow,
        tooltipPosition,
        valueChangeListener
        ;

        String toString;

        PropertyKeys(String toString) {
            this.toString = toString;
        }

        PropertyKeys() {}

        public String toString() {
            return ((this.toString != null) ? this.toString : super.toString());
        }
    }

    /**
     * The text of the tooltip. <br />
     * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
     */
    public String getTooltip() {
        String value = (String)getStateHelper().eval(PropertyKeys.tooltip);
        return  value;
    }

    /**
     * The text of the tooltip. <br />
     * Usually this method is called internally by the JSF engine.
     */
    public void setTooltip(String _tooltip) {
        getStateHelper().put(PropertyKeys.tooltip, _tooltip);
    }


    /**
     * The tooltip is shown and hidden with a delay. This value is the delay in milliseconds. Defaults to 0 (no delay). <br />
     * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
     */
    @Override
    public Integer getTooltipDelay() {
        int value = (Integer)getStateHelper().eval(PropertyKeys.tooltipDelay);
        return  value;
    }

    /**
     * The tooltip is shown and hidden with a delay. This value is the delay in milliseconds. Defaults to 0 (no delay). <br />
     * Usually this method is called internally by the JSF engine.
     */
    public void setTooltipDelay(Integer _tooltipDelay) {
        getStateHelper().put(PropertyKeys.tooltipDelay, _tooltipDelay);
    }


    /**
     * The tooltip is hidden with a delay. This value is the delay in milliseconds. Defaults to 0 (no delay). <br />
     * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
     */
    @Override
    public Integer getTooltipDelayHide() {
        Integer value = (Integer)getStateHelper().eval(PropertyKeys.tooltipDelayHide);
        return  value;
    }

    /**
     * The tooltip is hidden with a delay. This value is the delay in milliseconds. Defaults to 0 (no delay). <br />
     * Usually this method is called internally by the JSF engine.
     */
    public void setTooltipDelayHide(Integer _tooltipDelayHide) {
        getStateHelper().put(PropertyKeys.tooltipDelayHide, _tooltipDelayHide);
    }


    /**
     * The tooltip is shown with a delay. This value is the delay in milliseconds. Defaults to 0 (no delay). <br />
     * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
     */
    @Override
    public Integer getTooltipDelayShow() {
        Integer value = (Integer)getStateHelper().eval(PropertyKeys.tooltipDelayShow);
        return  value;
    }

    /**
     * The tooltip is shown with a delay. This value is the delay in milliseconds. Defaults to 0 (no delay). <br />
     * Usually this method is called internally by the JSF engine.
     */
    public void setTooltipDelayShow(String _tooltipDelayShow) {
        getStateHelper().put(PropertyKeys.tooltipDelayShow, _tooltipDelayShow);
    }


    /**
     * Where is the tooltip to be displayed? Possible values: "top", "bottom", "right", "left", "auto", "auto top", "auto bottom", "auto right" and "auto left". Default to "bottom". <br />
     * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
     */
    public String getTooltipPosition() {
        String value = (String)getStateHelper().eval(PropertyKeys.tooltipPosition);
        return  value;
    }

    /**
     * Where is the tooltip to be displayed? Possible values: "top", "bottom", "right", "left", "auto", "auto top", "auto bottom", "auto right" and "auto left". Default to "bottom". <br />
     * Usually this method is called internally by the JSF engine.
     */
    public void setTooltipPosition(String _tooltipPosition) {
        getStateHelper().put(PropertyKeys.tooltipPosition, _tooltipPosition);
    }
}
