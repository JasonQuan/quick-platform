package com.quick.ext.primefaces.base.exporter;

import org.primefaces.component.export.Exporter;

public interface ExtExporterFactory {

    Exporter getExporterForType(String type);

}
