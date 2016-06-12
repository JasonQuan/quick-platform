package com.quick.ext.primefaces.base.web;

import javax.faces.context.ExceptionHandlerFactory;

import com.quick.ext.primefaces.base.exception.ViewExpiredExceptionHandlerFactory;

public class ViewExpriedExceptionHandlerImpl extends ViewExpiredExceptionHandlerFactory {

    public ViewExpriedExceptionHandlerImpl(ExceptionHandlerFactory parent) {
        super(parent);
    }

    @Override
    public String redirectPage() {
        return "/";
    }

}
