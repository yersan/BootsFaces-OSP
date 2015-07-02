 /**
  *  Copyright 2014 Riccardo Massera (TheCoder4.Eu)
  *
  *  This file is part of BootsFaces.
  *
  *  BootsFaces is free software: you can redistribute it and/or modify
  *  it under the terms of the GNU Lesser General Public License as published by
  *  the Free Software Foundation, either version 3 of the License, or
  *  (at your option) any later version.
  *
  *  BootsFaces is distributed in the hope that it will be useful,
  *  but WITHOUT ANY WARRANTY; without even the implied warranty of
  *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  *  GNU Lesser General Public License for more details.
  *
  *  You should have received a copy of the GNU Lesser General Public License
  *  along with BootsFaces. If not, see <http://www.gnu.org/licenses/>.
  */
package net.bootsfaces.component.commandButton;

import java.io.IOException;
import java.util.Map;
import javax.faces.FacesException;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.event.ActionEvent;
import javax.faces.render.FacesRenderer;
import net.bootsfaces.C;
import net.bootsfaces.render.A;
import net.bootsfaces.render.CoreRenderer;
import net.bootsfaces.render.H;
import net.bootsfaces.render.R;
import net.bootsfaces.render.Tooltip;

/** This class generates the HTML code of &lt;b:commandButton /&gt;. */
@FacesRenderer(componentFamily = "net.bootsfaces.component", rendererType = CommandButton.DEFAULT_RENDERER)
public class CommandButtonRenderer extends CoreRenderer{
    
    @Override
    public void decode(FacesContext context, UIComponent component) {
	if (componentIsDisabledOrReadonly(component)) {
	    return;
	}
	
	String param = component.getClientId(context);
	if (context.getExternalContext().getRequestParameterMap().containsKey(param)) {
	    component.queueEvent(new ActionEvent(component));
	}
    }
    
    @Override
    public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
	if (!component.isRendered()) {
	    return;
	}
        
	Map<String, Object> attrs = component.getAttributes();
	ResponseWriter rw = context.getResponseWriter();
	
	String CID = component.getClientId(context);
	
	// 2) get the type (submit, button, reset ; default submit)
	String type = A.asString(attrs.get(A.TYPE), A.SUBMIT);
	// 3) is it Ajax? (default= false)
	String style = A.asString(attrs.get(H.STYLE));
	
	rw.startElement(H.BUTTON, component);
	rw.writeAttribute(H.TYPE, type, null);
	rw.writeAttribute(H.ID, CID, H.ID);
	rw.writeAttribute(H.NAME, CID, H.NAME);
	
	Tooltip.generateTooltip(context, attrs, rw);

	if (style != null) {
	    rw.writeAttribute(H.STYLE, style, H.STYLE);
	}
	
	rw.writeAttribute(H.CLASS, getStyleClasses(component), H.CLASS);
	
	String title = A.asString(attrs.get(A.TITLE), null);
	if (title != null && title.length() > 0) {
	    rw.writeAttribute(H.TITLE, title, H.TITLE);
	}
	
	generateOnClickHandler(context, component, rw, CID, type, A.toBool(attrs.get(A.AJAX), false));
	
	// TODO : write DHTML attrs - onclick
	// Encode attributes (HTML 4 pass-through + DHTML)
	R.encodeHTML4DHTMLAttrs(rw, attrs, A.ALLBUTTON_ATTRS);
    }

    
    @Override
    public boolean getRendersChildren() {
	return true;
    }
    
    
    
    
    @Override
    public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
	ResponseWriter rw = context.getResponseWriter();
	Map<String, Object> attrs = component.getAttributes();
	
	Object value = attrs.get(A.VALUE);
	String icon = A.asString(attrs.get(A.ICON));
	String faicon = A.asString(attrs.get(A.ICONAWESOME));
	boolean fa = false; // flag to indicate wether the selected icon set is
        
	// Font Awesome or not.
	if (faicon != null) {
	    icon = faicon;
	    fa = true;
	}
        
	if (icon != null) {
	    Object ialign = attrs.get(A.ICON_ALIGN); // Default Left
	    if (ialign != null && ialign.equals(A.RIGHT)) {
		writteText(rw, value + C.SP, null);
                
		R.encodeIcon(rw, component, icon, fa);
		// !//R.encodeIcon(rw, this, icon, white);
	    } else {
		R.encodeIcon(rw, component, icon, fa);
		// !//R.encodeIcon(rw, this, icon, white);
		writteText(rw, C.SP + value, null);
	    }
	    
	} else {
	    writteText(rw, value, null);
	}
	rw.endElement(H.BUTTON);
	
	Tooltip.activateTooltips(context, attrs, component);
    }
    
    private void generateOnClickHandler(FacesContext context, UIComponent component, ResponseWriter rw, String CID, String type, boolean ajax)
	    throws IOException {
	StringBuilder cJS = new StringBuilder(150); // optimize int
	Map<String, Object> attrs = component.getAttributes();
	
	String render = A.asString(attrs.get(A.UPDATE));
	String complete = A.asString(attrs.get(A.ONCOMPLETE));
	// 3) is it a Submit?
	if (!type.equals(A.RESET) && !type.equals(A.BUTTON)) {
	    // Check if it is in a Form
	    String formId = R.findComponentFormId(context, component);
	    if (formId == null) {
		throw new FacesException("CommandButton : '" + CID + "' must be inside a form element");
	    }
	    
	    if (ajax) {
		cJS.append(encodeClick(component)).append("return BsF.ajax.cb(this, event")
			.append(render == null ? "" : (",'" + render + "'"));
		if (complete != null) {
		    cJS.append(",function(){" + complete + "}");
		}
		cJS.append(");");
	    } else {
		cJS = new StringBuilder(encodeClick(component));// Fix
		// Chrome//+"document.forms['"+formId+"'].submit();");
	    }
	}
	if (ajax && type.equals(A.RESET)) {
	    cJS.append(encodeClick(component)).append("return BsF.ajax.cb(this, event")
		    .append(render == null ? "" : (",'" + render + "'"));
	    if (complete != null) {
		cJS.append(",function(){" + complete + "}");
	    }
	    cJS.append(");");
	    // cJS.append("execute: this.id, ");
	}
	if (cJS.toString().length() > 1) {// Fix Chrome
	    rw.writeAttribute(A.CLICK, cJS.toString(), null);
	}
    }
    
    
    private String getStyleClasses(UIComponent component) {
	Map<String, Object> attrs = component.getAttributes();
	StringBuilder sb = new StringBuilder(40); // optimize int
	
	sb.append("btn");
	String size = A.asString(attrs.get(A.SIZE));
	if (size != null) {
	    sb.append(" btn-").append(size);
	}
	// TBS3 Si usa look, non severity
	String look = A.asString(attrs.get(A.LOOK));
	if (look != null) {
	    sb.append(" btn-").append(look);
	} else {
	    sb.append(" btn-default");
	}
	
	if (A.toBool(attrs.get(A.DISABLED))) {
	    sb.append(C.SP + A.DISABLED);
	}
	// TODO add styleClass and class support
	String sclass = A.asString(attrs.get(H.STYLECLASS));
	if (sclass != null) {
	    sb.append(C.SP).append(sclass);
	}
	
	return sb.toString().trim();
	
    }
    
    
    
    private String encodeClick(UIComponent component) {
	    String js;
	    String oc = (String) component.getAttributes().get("onclick");
	    if (oc != null) {
		    js = oc.endsWith(C.SCOLON) ? oc : oc + C.SCOLON;
	    } else {
		    js = "";
	    }

	    return js;
    }
}
