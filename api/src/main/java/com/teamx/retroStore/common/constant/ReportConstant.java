package com.teamx.retroStore.common.constant;

import org.apache.commons.lang3.StringUtils;

import java.util.LinkedHashMap;
import java.util.Map;

public class ReportConstant {

    public enum ReportExportFormat {

        HTML("Show HTML"),
        PDF("PDF"),
        PDF_SHOW("Show PDF in new tab"),
        PDF_SHOW_INLINE("Show PDF inline"),
        PDF_DOWNLOAD("Download PDF"),
        PDF_ZIP_EMAIL("Email PDF as Zip"),
        CSV("Download CSV"),
        CSV_ZIP_EMAIL("Email CSV as Zip"),
        EMAIL_REPORT("Email Report"),
        XLS("Download XLS"),
        XLS_ZIP_EMAIL("Email XLS as Zip");

        private String description;

        private ReportExportFormat(String description) {
            this.description = description;
        }

        public static ReportExportFormat resolve(String reportExportFormatStr) {
            ReportExportFormat matchingReportExportFormat = null;
            if (!StringUtils.isEmpty(reportExportFormatStr)) {
                matchingReportExportFormat = valueOf(reportExportFormatStr.trim());
            }
            return matchingReportExportFormat;
        }

        public static Map<String, String> getReportExportFormatMap() {
            Map<String, String> map = new LinkedHashMap<>();
            for (ReportExportFormat reportExportFormat : ReportExportFormat.values()) {
                map.put(reportExportFormat.toString(), reportExportFormat.getDescription());
            }
            return map;
        }

        public String getDescription() {
            return description;
        }
    }

    public enum ReportType {

        INCIDENT_REPORT("Incident Report");

        private String description;

        private ReportType(String description) {
            this.description = description;
        }

        public static ReportType resolve(String reportTypeStr) {
            ReportType matchingReportType = null;
            if (!StringUtils.isEmpty(reportTypeStr)) {
                matchingReportType = valueOf(reportTypeStr.trim());
            }
            return matchingReportType;
        }

        public String getDescription() {
            return description;
        }
    }

    public enum FileType {
        PDF(".pdf"),
        CSV(".csv"),
        XLS(".xls"),
        ZIP(".zip");

        private String extension;

        FileType(String extension) {
            this.extension = extension;
        }

        public String getExtension() {
            return this.extension;
        }
    }

    public enum ReportExportType {
        DOWNLOAD("Download"),
        EMAIL("Email"),
        VIEW("View");

        private String name;

        private ReportExportType(String view) {
        }

        public static ReportExportType resolve(String exportTypeStr) {
            ReportExportType matchingExportType = null;
            if (!StringUtils.isEmpty(exportTypeStr)) {
                matchingExportType = valueOf(exportTypeStr.trim());
            }
            return matchingExportType;
        }

        public String getName() {
            return name;
        }
    }
}
