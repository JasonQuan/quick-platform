package com.quick.ext.primefaces.base.web;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import com.quick.ext.primefaces.base.service.BaseEJB;
import com.quick.ext.primefaces.base.entity.AbstractEntity;

/**
 *
 * @author Jason
 * @param <T,E>
 */
public class BaseConverter<T extends AbstractEntity> implements Converter {
	private final static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(BaseConverter.class);
	private final BaseEJB<T, ?> baseSB;

	public BaseConverter(BaseEJB<T, ?> baseDao) {
		this.baseSB = baseDao;
	}

	@Override
	public T getAsObject(FacesContext context, UIComponent component, String value) {
		// if (value == null || value.equals("") ||
		// !ObjectUtil.canConverterToInteger(value)) {
		// if (value == null || value.equals("") ||
		// value.equals(MessageBundle.getLocalizedString("select"))) {
		if (value == null || value.equals("") || ("select").equals(value)) {
			return null;
		}

		return baseSB.find(value);
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object object) {
		if (object == null || object.toString().equals("")) {
			return null;
		}
		@SuppressWarnings("unchecked")
		T o = (T) object;
		try {
			Object id = o.getId();
			if (id != null) {
				return String.valueOf(id);
			}
		} catch (NullPointerException e) {
			logger.error(e);
		} catch (ClassCastException e) {
			logger.error(e);
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}
}
