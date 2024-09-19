package com.teamx.retroStore.report.exporter;


import com.teamx.retroStore.dto.common.report.output.ReportOutput;
import com.teamx.retroStore.exception.impl.AppsException;
import lombok.Setter;
import net.sf.jasperreports.engine.JasperPrint;

import java.util.Map;

public abstract class BaseExporter {

    protected JasperPrint print;

    protected Map<String, Object> parameters;

    @Setter
    protected Boolean noResults;

    protected BaseExporter(JasperPrint print, Map<String, Object> parameters) {
        this.print = print;
        this.parameters = parameters;
    }

    public Boolean isNoResults() {
        return noResults;
    }

    public abstract ReportOutput generateOutput() throws AppsException;

}
