package com.teamx.retroStore.dto.common.report;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class ReportCriteria implements Serializable {

    /**
     * Search Parameters
     */
    private Map<String, Object> parameters;

    /**
     * report Parameters for Report template
     */
    private Map<String, Object> reportParameters;

    public Map<String, Object> getReportParameters() {
        if (reportParameters == null) {
            this.reportParameters = new HashMap<>();
        }
        return reportParameters;
    }

    public void setReportParameters(Map<String, Object> reportParameters) {
        this.reportParameters = reportParameters;
    }

    public Map<String, Object> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, Object> parameters) {
        this.parameters = parameters;
    }
}
