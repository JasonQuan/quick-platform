package com.quick.ext.primefaces.converter;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Jason
 */
@FacesConverter(value = "com.primefaces.ext.converter.DateTimeConverter")
public class DateTimeConverter implements Converter, Serializable {

    //TODO: other format from page
    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
    

    @Override
    public Date getAsObject(FacesContext context, UIComponent component, String value) {        
        try {
            return sdf.parse(value);
        } catch (ParseException ex) {
            Logger.getLogger(DateTimeConverter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value instanceof Date) {
            return sdf.format(value);
        }
        return "";
    }

}
