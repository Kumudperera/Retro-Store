package com.retroStore.common.constant;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class DomainConstants {

    public enum UserType {
        ADMIN, COMPANY_USER;
    }

    public enum EmailSendType {
        TEXT, HTML
    }

    public enum EmailPurpose {
        OTHER, REPORT, NOTIFICATION
    }

    public enum UploadFileType {
        IMAGE,
        PDF,
        VIDEO,
        SIGNATURE
    }

    public enum PrivilegeModule {
        ADMIN, COMMON, CLIENT;
    }

    public enum LocationUserStatus {
        ACTIVE("Active"),
        BLOCKED("Blocked"),
        REMOVED("Removed");

        private String description;

        LocationUserStatus(String description) {
            this.description = description;
        }

        public static CompanyRoadmapStatus resolveStatus(String statusStr) {
            CompanyRoadmapStatus matchingStatus = null;
            if (!StringUtils.isEmpty(statusStr)) {
                matchingStatus = CompanyRoadmapStatus.valueOf(statusStr.trim());
            }
            return matchingStatus;
        }

        public static Map<String, String> getStatusMap() {
            Map<String, String> result = new HashMap<>();
            Arrays.asList(CompanyRoadmapStatus.values()).forEach(status -> result.put(status.toString(), status.getDescription()));
            return result;
        }

        public String getDescription() {
            return description;
        }
    }

    public enum DocumentType {
        DETAIL, SUMMARY;

        public static DocumentType resolveDocumentType(String type) {
            DocumentType matchingType = null;
            if (!StringUtils.isEmpty(type)) {
                matchingType = DocumentType.valueOf(type.trim());
            }
            return matchingType;
        }

    }

    public enum DocumentStatus {
        ENABLED, DISABLED;

        public static DocumentStatus resolveStatus(String statusStr) {
            DocumentStatus matchingStatus = null;
            if (!StringUtils.isEmpty(statusStr)) {
                matchingStatus = DocumentStatus.valueOf(statusStr.trim());
            }
            return matchingStatus;
        }
    }

    public enum SummaryDocumentType {
        VIDEO,
        TEXT
    }

    public enum CompanyRoadmapStatus {
        DRAFT("Draft"),
        COMPLETED("Completed"),
        IN_PROGRESS("In Progress");

        private String description;

        CompanyRoadmapStatus(String description) {
            this.description = description;
        }

        public static CompanyRoadmapStatus resolveStatus(String statusStr) {
            CompanyRoadmapStatus matchingStatus = null;
            if (!StringUtils.isEmpty(statusStr)) {
                matchingStatus = CompanyRoadmapStatus.valueOf(statusStr.trim());
            }
            return matchingStatus;
        }

        public static Map<String, String> getStatusMap() {
            Map<String, String> result = new HashMap<>();
            Arrays.asList(CompanyRoadmapStatus.values()).forEach(status -> result.put(status.toString(), status.getDescription()));
            return result;
        }

        public String getDescription() {
            return description;
        }
    }

    public enum RoadmapQuestionAnswers {
        YES("Yes"),
        NO("No");

        private String description;

        RoadmapQuestionAnswers(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }

        public static RoadmapQuestionAnswers resolveAnswer(String answerStr) {
            RoadmapQuestionAnswers matchingAnswer = null;
            if (!StringUtils.isEmpty(answerStr)) {
                matchingAnswer = RoadmapQuestionAnswers.valueOf(answerStr.trim());
            }
            return matchingAnswer;
        }
    }

    public enum FileFormat {
        JPG(".jpg"),
        PNG(".png"),
        PDF(".pdf"),
        XLSX(".xlsx");

        private String description;

        FileFormat(String description) {
            this.description = description;
        }

        public static FileFormat resolveStatus(String fileFormatStr) {
            FileFormat status = null;
            if (!StringUtils.isEmpty(fileFormatStr)) {
                status = FileFormat.valueOf(fileFormatStr.trim());
            }
            return status;
        }

        public String getDescription() {
            return description;
        }
    }

    public enum UserRoadmapModuleStatus {
        DRAFT("Draft"),
        PENDING("Pending"),
        COMPLETED("Completed"),
        IN_PROGRESS("In Progress");

        private String description;

        UserRoadmapModuleStatus(String description) {
            this.description = description;
        }

        public static UserRoadmapModuleStatus resolveStatus(String statusStr) {
            UserRoadmapModuleStatus matchingStatus = null;
            if (!StringUtils.isEmpty(statusStr)) {
                matchingStatus = UserRoadmapModuleStatus.valueOf(statusStr.trim());
            }
            return matchingStatus;
        }

        public static Map<String, String> getStatusMap() {
            Map<String, String> result = new HashMap<>();
            Arrays.asList(CompanyRoadmapStatus.values()).forEach(status -> result.put(status.toString(), status.getDescription()));
            return result;
        }

        public String getDescription() {
            return description;
        }
    }

    public enum PasswordUpdateAction {
        UPDATE, RESET, FORGOT
    }

    public enum CompanyRoadmapModuleStatus {
        DRAFT("Drafted"),
        PENDING("Pending"),
        COMPLETED("Completed"),
        IN_PROGRESS("In Progress");

        private String description;

        CompanyRoadmapModuleStatus(String description) {
            this.description = description;
        }

        public static CompanyRoadmapModuleStatus resolveStatus(String statusStr) {
            CompanyRoadmapModuleStatus matchingStatus = null;
            if (!StringUtils.isEmpty(statusStr)) {
                matchingStatus = CompanyRoadmapModuleStatus.valueOf(statusStr.trim());
            }
            return matchingStatus;
        }

        public static Map<String, String> getStatusMap() {
            Map<String, String> result = new HashMap<>();
            Arrays.asList(CompanyRoadmapModuleStatus.values()).forEach(status -> result.put(status.toString(), status.getDescription()));
            return result;
        }

        public String getDescription() {
            return description;
        }
    }

    public enum ConfidentialityType {
        STANDARD("Standard"),
        SENSITIVE("Sensitive");

        private String description;

        ConfidentialityType(String description) {
            this.description = description;
        }

        public static ConfidentialityType resolveStatus(String statusStr) {
            ConfidentialityType matchingStatus = null;
            if (!StringUtils.isEmpty(statusStr)) {
                matchingStatus = ConfidentialityType.valueOf(statusStr.trim());
            }
            return matchingStatus;
        }

        public static Map<String, String> getStatusMap() {
            Map<String, String> result = new HashMap<>();
            Arrays.asList(ConfidentialityType.values()).forEach(status -> result.put(status.toString(), status.getDescription()));
            return result;
        }

        public String getDescription() {
            return description;
        }
    }

    public enum IncidentStatus {
        ENTERED("Entered"),
        PENDING("Pending"),
        CLOSED("Closed"),
        INACTIVE("Inactive");

        private String description;

        IncidentStatus(String description) {
            this.description = description;
        }

        public static IncidentStatus resolveStatus(String statusStr) {
            IncidentStatus matchingStatus = null;
            if (!StringUtils.isEmpty(statusStr)) {
                matchingStatus = IncidentStatus.valueOf(statusStr.trim());
            }
            return matchingStatus;
        }

        public static Map<String, String> getStatusMap() {
            Map<String, String> result = new HashMap<>();
            Arrays.asList(AppsConstants.Status.values()).forEach(status -> result.put(status.toString(), status.getDescription()));
            return result;
        }

        public String getDescription() {
            return description;
        }
    }

    public enum IncidentInvestigationStatus {
        DISMISSED("Dismissed"),
        CREATED("Created"),
        STARTED("Started"),
        IN_PROGRESS("In Progress"),
        COMPLETED("Completed");

        private String description;

        IncidentInvestigationStatus(String description) {
            this.description = description;
        }

        public static IncidentInvestigationStatus resolveStatus(String statusStr) {
            IncidentInvestigationStatus matchingStatus = null;
            if (!StringUtils.isEmpty(statusStr)) {
                matchingStatus = IncidentInvestigationStatus.valueOf(statusStr.trim());
            }
            return matchingStatus;
        }

        public static Map<String, String> getStatusMap() {
            Map<String, String> result = new HashMap<>();
            Arrays.asList(AppsConstants.Status.values()).forEach(status -> result.put(status.toString(), status.getDescription()));
            return result;
        }

        public String getDescription() {
            return description;
        }
    }

    public enum LRDocumentType {
        REGULATION("Regulation"),
        ACT("Act");

        private String description;

        LRDocumentType(String description) {
            this.description = description;
        }

        public static LRDocumentType resolveLRDocumentType(String lrDocumentTypeStr) {
            LRDocumentType matchingLRDocumentType = null;
            if (!StringUtils.isEmpty(lrDocumentTypeStr)) {
                matchingLRDocumentType = LRDocumentType.valueOf(lrDocumentTypeStr.trim());
            }
            return matchingLRDocumentType;
        }

        public static Map<String, String> getLRDocumentMap() {
            Map<String, String> result = new HashMap<>();
            Arrays.asList(LRDocumentType.values()).forEach(lrDocumentType -> result.put(lrDocumentType.toString(), lrDocumentType.getDescription()));
            return result;
        }

        public String getDescription() {
            return description;
        }
    }

    public enum PlantEquipmentRegistrationStatus {
        ENTERED("Entered"),
        PENDING("Pending"),
        COMPLETED("Completed");
//        PLANT_ENTERED("Plant entered"), PLANT_EXPIRED("Plant expired"), ASSESSMENT_COMPLETED("Assessment completed"), ASSESSMENT_ON_HOLD("Assessment on hold"), ACTIONS_COMPLETED("Actions completed"), ACTIONS_CLOSED("Actions closed");

        private String description;

        PlantEquipmentRegistrationStatus(String description) {
            this.description = description;
        }

        public static PlantEquipmentRegistrationStatus resolveStatus(String statusStr) {
            PlantEquipmentRegistrationStatus matchingStatus = null;
            if (!StringUtils.isEmpty(statusStr)) {
                matchingStatus = PlantEquipmentRegistrationStatus.valueOf(statusStr.trim());
            }
            return matchingStatus;
        }

        public static Map<String, String> getStatusMap() {
            Map<String, String> result = new HashMap<>();
            Arrays.asList(AppsConstants.Status.values()).forEach(status -> result.put(status.toString(), status.getDescription()));
            return result;
        }

        public String getDescription() {
            return description;
        }
    }

    public enum PlantRiskAssessmentStatus {
        CREATED("Created"),
        COMPLETED("Completed"),
        EXPIRED("Expired"),
        ON_HOLD("On hold");

        private String description;

        PlantRiskAssessmentStatus(String description) {
            this.description = description;
        }

        public static PlantRiskAssessmentStatus resolveStatus(String statusStr) {
            PlantRiskAssessmentStatus matchingStatus = null;
            if (!StringUtils.isEmpty(statusStr)) {
                matchingStatus = PlantRiskAssessmentStatus.valueOf(statusStr.trim());
            }
            return matchingStatus;
        }

        public static Map<String, String> getStatusMap() {
            Map<String, String> result = new HashMap<>();
            Arrays.asList(AppsConstants.Status.values()).forEach(status -> result.put(status.toString(), status.getDescription()));
            return result;
        }

        public String getDescription() {
            return description;
        }
    }

    public enum ReviewFrequency {
        DAILY(1, "Daily"),
        WEEKLY(2, "Weekly"),
        FORTNIGHTLY(3, "Fortnightly"), // a period of two weeks
        MONTHLY(4, "Monthly"),
        QUARTERLY(5, "Quarterly (4 times per year)"),
        BI_ANNUALLY(6, "Biannually (Twice per year)"),
        ANNUALLY(7, "Annually (Once per year)"),
        BIENNIALLY(8, "Biennially (Every 2 years)");

        private final int reviewFrequencyID;
        private final String reviewFrequencyName;

        ReviewFrequency(int reviewFrequencyID, String reviewFrequencyName) {
            this.reviewFrequencyID = reviewFrequencyID;
            this.reviewFrequencyName = reviewFrequencyName;
        }

        public int getReviewFrequencyID() {
            return reviewFrequencyID;
        }

        public String getReviewFrequencyName() {
            return reviewFrequencyName;
        }

    }

    public enum DurationOptions {
        ONE_WEEK("One week"),
        ONE_MONTH("One month"),
        ONE_QUARTER("One quarter"),
        SIX_MONTHS("Six months"),
        ONE_YEAR("One year"),
        TWO_YEARS("Two years"),
        FIVE_YEARS("Five years");

        private String description;

        DurationOptions(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }

        public static DurationOptions resolveOptions(String optionStr) {
            DurationOptions matchingOption = null;
            if (!StringUtils.isEmpty(optionStr)) {
                matchingOption = DurationOptions.valueOf(optionStr.trim());
            }

            return matchingOption;
        }

    }

    public enum FrequencyOptions {
        ONCE_OFF("Once off"),
        EVERY_DAY("Every day"),
        EVERY_WEEKDAY("Every weekday"),
        EVERY_WEEK("Every week"),
        EVERY_FORTNIGHT("Every fortnight"),
        EVERY_MONTH("Every month"),
        EVERY_QUARTER("Every quarter"),
        EVERY_SIX_MONTHS("Every 6 months"),
        EVERY_YEAR("Every year");

        private String description;

        FrequencyOptions(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }

        public static FrequencyOptions resolveOptions(String optionStr) {
            FrequencyOptions matchingOption = null;
            if (!StringUtils.isEmpty(optionStr)) {
                matchingOption = FrequencyOptions.valueOf(optionStr.trim());
            }

            return matchingOption;
        }
    }

    public enum IndicatorType {
        LEAD("Lead"),
        LAG("Lag");

        private final String type;

        IndicatorType(String type) {
            this.type = type;
        }

        public String getType() {
            return this.type;
        }
    }

    public enum ActivityType {
        PERCENTAGE("Percentage"),
        NUMBER("Number");

        private final String type;

        ActivityType(String type) {
            this.type = type;
        }

        public String getType() {
            return this.type;
        }
    }

    public enum RegisterAnswers {
        YES("Yes"),
        NO("No"),
        NA("Na");

        private final String type;

        RegisterAnswers(String type) {
            this.type = type;
        }

        public String getType() {
            return this.type;
        }

        public static RegisterAnswers resolve(String optionStr) {
            RegisterAnswers matchingOption = null;
            if (!StringUtils.isEmpty(optionStr)) {
                matchingOption = RegisterAnswers.valueOf(optionStr.trim());
            }

            return matchingOption;
        }

    }

    public enum ObjectiveRegistrationStatus {
        DRAFT("Draft"),
        PENDING("Pending"),
        HOLD("Hold"),
        ACTIVE("Active"),
        COMPLETE("Complete"),
        DISMISS("Dismiss");

        private String description;

        ObjectiveRegistrationStatus(String description) {
            this.description = description;
        }

        public static ObjectiveRegistrationStatus resolveStatus(String statusStr) {
            ObjectiveRegistrationStatus matchingStatus = null;
            if (!StringUtils.isEmpty(statusStr)) {
                matchingStatus = ObjectiveRegistrationStatus.valueOf(statusStr.trim());
            }
            return matchingStatus;
        }

        public String getDescription() {
            return description;
        }

    }

    public enum ObjectiveStatus {
        PENDING("Pending"),
        COMPLETED("Completed");

        private String description;

        ObjectiveStatus(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }

        public static ObjectiveStatus resolve(String categoryStr) {
            ObjectiveStatus matchingCategory = null;
            if (!StringUtils.isEmpty(categoryStr)) {
                matchingCategory = ObjectiveStatus.valueOf(categoryStr.trim());
            }

            return matchingCategory;
        }

    }

    public enum ChangeManagementStatus {
        PENDING("Pending"),
        ASSESSED_PENDING_AUTHORIZATION("Assessed / Pending Authorization"),
        ACTIONS_COMPLETED_AUTHORIZED("Actions completed / Authorized");

        private String description;

        ChangeManagementStatus(String description) {
            this.description = description;
        }

        public static ChangeManagementStatus resolveStatus(String statusStr) {
            ChangeManagementStatus matchingStatus = null;
            if (!StringUtils.isEmpty(statusStr)) {
                matchingStatus = ChangeManagementStatus.valueOf(statusStr.trim());
            }
            return matchingStatus;
        }

        public static Map<String, String> getStatusMap() {
            Map<String, String> result = new HashMap<>();
            Arrays.asList(AppsConstants.Status.values()).forEach(status -> result.put(status.toString(), status.getDescription()));
            return result;
        }

        public String getDescription() {
            return description;
        }
    }

    public enum ActionObjectType {
        GENERAL("General"),
        INCIDENT("Incident"),
        INCIDENT_INVESTIGATION("Incident investigation"),
        PLANT_AND_EQUIPMENT("Plant and equipment"),
        INTERESTED_PARTIES("Interested Parties"),
        CHANGE_MANAGEMENT("Change Management"),
        BUSINESS_RISK("Business Risk"),
        NEW_CONTRACTOR("New Contractor"),
        CONTRACTOR_INDUCTION("Contractor Induction"),
        RISK_ASSESSMENT("Risk Assessment"),
        SWMS("SWMS"),
        CHEMICAL_RISK_ASSESSMENT("Chemical risk assessment"),
        HAZARD("Hazard");

        private String description;

        ActionObjectType(String description) {
            this.description = description;
        }

        public static ActionObjectType resolveStatus(String statusStr) {
            ActionObjectType matchingStatus = null;
            if (!StringUtils.isEmpty(statusStr)) {
                matchingStatus = ActionObjectType.valueOf(statusStr.trim());
            }
            return matchingStatus;
        }

        public static Map<String, String> getStatusMap() {
            Map<String, String> result = new HashMap<>();
            Arrays.asList(AppsConstants.Status.values()).forEach(status -> result.put(status.toString(), status.getDescription()));
            return result;
        }

        public String getDescription() {
            return description;
        }
    }

    public enum ComplianceType {
        SAFETY("Health and safety"),
        ENVIRONMENT("Environment"),
        QUALITY("Quality or customer service"),
        HR("Environment relations or HR");

        private String description;

        ComplianceType(String description) {
            this.description = description;
        }

        public static ComplianceType resolveType(String statusStr) {
            ComplianceType matchingStatus = null;
            if (!StringUtils.isEmpty(statusStr)) {
                matchingStatus = ComplianceType.valueOf(statusStr.trim());
            }
            return matchingStatus;
        }

        public static Map<String, String> getStatusMap() {
            Map<String, String> result = new HashMap<>();
            Arrays.asList(AppsConstants.Status.values()).forEach(status -> result.put(status.toString(), status.getDescription()));
            return result;
        }

        public String getDescription() {
            return description;
        }
    }

    public enum ActionStatus {
        CREATED("Created"),
        PENDING("Pending"),
        DISMISSED("Dismissed"),
        CLOSED("Closed");

        private String description;

        ActionStatus(String description) {
            this.description = description;
        }

        public static ActionStatus resolveStatus(String statusStr) {
            ActionStatus matchingStatus = null;
            if (!StringUtils.isEmpty(statusStr)) {
                matchingStatus = ActionStatus.valueOf(statusStr.trim());
            }
            return matchingStatus;
        }

        public static Map<String, String> getStatusMap() {
            Map<String, String> result = new HashMap<>();
            Arrays.asList(AppsConstants.Status.values()).forEach(status -> result.put(status.toString(), status.getDescription()));
            return result;
        }

        public String getDescription() {
            return description;
        }
    }

    public enum HazardStatus {
        CREATED("Created"),
        PENDING("Pending"),
        COMPLETED("Completed"),
        INACTIVE("Inactive");

        private String description;

        HazardStatus(String description) {
            this.description = description;
        }

        public static HazardStatus resolveStatus(String statusStr) {
            HazardStatus matchingStatus = null;
            if (!StringUtils.isEmpty(statusStr)) {
                matchingStatus = HazardStatus.valueOf(statusStr.trim());
            }
            return matchingStatus;
        }

        public static Map<String, String> getStatusMap() {
            Map<String, String> result = new HashMap<>();
            Arrays.asList(AppsConstants.Status.values()).forEach(status -> result.put(status.toString(), status.getDescription()));
            return result;
        }

        public String getDescription() {
            return description;
        }
    }

    public enum ContractorStatus {
        ENTERED("Entered"),
        EXPIRED("Expired"),
        PENDING("Pending"),
        APPROVED("Approved"),
        REJECTED("Rejected");

        private String description;

        ContractorStatus(String description) {
            this.description = description;
        }

        public static ContractorStatus resolveStatus(String statusStr) {
            ContractorStatus matchingStatus = null;
            if (!StringUtils.isEmpty(statusStr)) {
                matchingStatus = ContractorStatus.valueOf(statusStr.trim());
            }
            return matchingStatus;
        }

        public static Map<String, String> getStatusMap() {
            Map<String, String> result = new HashMap<>();
            Arrays.asList(ContractorStatus.values()).forEach(status -> result.put(status.toString(), status.getDescription()));
            return result;
        }

        public String getDescription() {
            return description;
        }
    }

    public enum InductionStatus {
        EXPIRED("Expired"),
        APPROVED("Approved");

        private String description;

        InductionStatus(String description) {
            this.description = description;
        }

        public static InductionStatus resolveStatus(String statusStr) {
            InductionStatus matchingStatus = null;
            if (!StringUtils.isEmpty(statusStr)) {
                matchingStatus = InductionStatus.valueOf(statusStr.trim());
            }
            return matchingStatus;
        }

        public static Map<String, String> getStatusMap() {
            Map<String, String> result = new HashMap<>();
            Arrays.asList(InductionStatus.values()).forEach(status -> result.put(status.toString(), status.getDescription()));
            return result;
        }

        public String getDescription() {
            return description;
        }
    }

    public enum IpEngagementType {
        INTERNAL("Internal"),
        EXTERNAL("External");

        private String description;

        IpEngagementType(String description) {
            this.description = description;
        }

        public static IpEngagementType resolveIpEngagementType(String ipEngagementTypeStr) {
            IpEngagementType matchingIpEngagementType = null;
            if (!StringUtils.isEmpty(ipEngagementTypeStr)) {
                matchingIpEngagementType = IpEngagementType.valueOf(ipEngagementTypeStr.trim());
            }
            return matchingIpEngagementType;
        }

        public static Map<String, String> getIpEngagementTypeMap() {
            Map<String, String> result = new HashMap<>();
            Arrays.asList(IpEngagementType.values()).forEach(ipEngagementType -> result.put(ipEngagementType.toString(), ipEngagementType.getDescription()));
            return result;
        }

        public String getDescription() {
            return description;
        }
    }

    public enum IpRecordStatus {
        ENTERED("Entered"),
        COMPLETED("Completed");

        private String description;

        IpRecordStatus(String description) {
            this.description = description;
        }

        public static IpRecordStatus resolveIpRecordStatus(String ipRecordStatusStr) {
            IpRecordStatus matchingIpRecordStatus = null;
            if (!StringUtils.isEmpty(ipRecordStatusStr)) {
                matchingIpRecordStatus = IpRecordStatus.valueOf(ipRecordStatusStr.trim());
            }
            return matchingIpRecordStatus;
        }

        public static Map<String, String> getIpRecordStatus() {
            Map<String, String> result = new HashMap<>();
            Arrays.asList(IpRecordStatus.values()).forEach(ipRecordStatus -> result.put(ipRecordStatus.toString(), ipRecordStatus.getDescription()));
            return result;
        }

        public String getDescription() {
            return description;
        }
    }

    public enum RiskAssessmentStatus {
        ENTERED("Entered"),
        PENDING("Pending"),
        CLOSED("Closed"),
        INACTIVE("Inactive");

        private String description;

        RiskAssessmentStatus(String description) {
            this.description = description;
        }

        public static RiskAssessmentStatus resolveStatus(String statusStr) {
            RiskAssessmentStatus matchingStatus = null;
            if (!StringUtils.isEmpty(statusStr)) {
                matchingStatus = RiskAssessmentStatus.valueOf(statusStr.trim());
            }
            return matchingStatus;
        }

        public String getDescription() {
            return description;
        }

    }

    public enum ChemicalStatus {
        ENTERED("Entered"),
        EXPIRED("Expired");

        private String description;

        ChemicalStatus(String description) {
            this.description = description;
        }

        public static ChemicalStatus resolveStatus(String statusStr) {
            ChemicalStatus matchingStatus = null;
            if (!StringUtils.isEmpty(statusStr)) {
                matchingStatus = ChemicalStatus.valueOf(statusStr.trim());
            }
            return matchingStatus;
        }

        public static Map<String, String> getStatusMap() {
            Map<String, String> result = new HashMap<>();
            Arrays.asList(ChemicalStatus.values()).forEach(status -> result.put(status.toString(), status.getDescription()));
            return result;
        }

        public String getDescription() {
            return description;
        }
    }

    public enum AssessmentStatus {
        ENTERED("Entered"),
        INACTIVE("Inactive"),
        PENDING("Pending"),
        CLOSED("Closed");

        private String description;

        AssessmentStatus(String description) {
            this.description = description;
        }

        public static AssessmentStatus resolveStatus(String statusStr) {
            AssessmentStatus matchingStatus = null;
            if (!StringUtils.isEmpty(statusStr)) {
                matchingStatus = AssessmentStatus.valueOf(statusStr.trim());
            }
            return matchingStatus;
        }

        public static Map<String, String> getStatusMap() {
            Map<String, String> result = new HashMap<>();
            Arrays.asList(ChemicalStatus.values()).forEach(status -> result.put(status.toString(), status.getDescription()));
            return result;
        }

        public String getDescription() {
            return description;
        }
    }


    public enum SwmsStatus {
        ENTERED("Entered"),
        PENDING("Pending"),
        CLOSED("Closed"),
        INACTIVE("Inactive");

        private String description;

        SwmsStatus(String description) {
            this.description = description;
        }

        public static SwmsStatus resolveStatus(String statusStr) {
            SwmsStatus matchingStatus = null;
            if (!StringUtils.isEmpty(statusStr)) {
                matchingStatus = SwmsStatus.valueOf(statusStr.trim());
            }
            return matchingStatus;
        }

        public static Map<String, String> getStatusMap() {
            Map<String, String> result = new HashMap<>();
            Arrays.asList(AppsConstants.Status.values()).forEach(status -> result.put(status.toString(), status.getDescription()));
            return result;
        }

        public String getDescription() {
            return description;
        }
    }

    public enum SwmsActionStatus {
        CREATED("Created"),
        PENDING("Pending"),
        CLOSED("Closed");

        private String description;

        SwmsActionStatus(String description) {
            this.description = description;
        }

        public static SwmsStatus resolveStatus(String statusStr) {
            SwmsStatus matchingStatus = null;
            if (!StringUtils.isEmpty(statusStr)) {
                matchingStatus = SwmsStatus.valueOf(statusStr.trim());
            }
            return matchingStatus;
        }

        public static Map<String, String> getStatusMap() {
            Map<String, String> result = new HashMap<>();
            Arrays.asList(AppsConstants.Status.values()).forEach(status -> result.put(status.toString(), status.getDescription()));
            return result;
        }

        public String getDescription() {
            return description;
        }
    }

    public enum SwmsAssessmentStatus {
        ENTERED("Entered"),
        PENDING("Pending"),
        CLOSED("Closed"),
        INACTIVE("Inactive");

        private String description;

        SwmsAssessmentStatus(String description) {
            this.description = description;
        }

        public static SwmsAssessmentStatus resolveStatus(String statusStr) {
            SwmsAssessmentStatus matchingStatus = null;
            if (!StringUtils.isEmpty(statusStr)) {
                matchingStatus = SwmsAssessmentStatus.valueOf(statusStr.trim());
            }
            return matchingStatus;
        }

        public static Map<String, String> getStatusMap() {
            Map<String, String> result = new HashMap<>();
            Arrays.asList(AppsConstants.Status.values()).forEach(status -> result.put(status.toString(), status.getDescription()));
            return result;
        }

        public String getDescription() {
            return description;
        }
    }

    public enum ChemicalRiskStatus {
        ENTERED("Entered"),
        PENDING("Pending"),
        CLOSED("Closed"),
        INACTIVE("Inactive");

        private String description;

        ChemicalRiskStatus(String description) {
            this.description = description;
        }

        public static ChemicalRiskStatus resolveStatus(String statusStr) {
            ChemicalRiskStatus matchingStatus = null;
            if (!StringUtils.isEmpty(statusStr)) {
                matchingStatus = ChemicalRiskStatus.valueOf(statusStr.trim());
            }
            return matchingStatus;
        }

        public static Map<String, String> getStatusMap() {
            Map<String, String> result = new HashMap<>();
            Arrays.asList(ChemicalRiskStatus.values()).forEach(status -> result.put(status.toString(), status.getDescription()));
            return result;
        }

        public String getDescription() {
            return description;
        }
    }


    public enum RegisterNotifyRegType {
        RISK_ASSESSMENT("Risk Assessment Form"),
        CHEMICAL_RISK_ASSESSMENT("Chemical Risk Assessment Form"),
        SAFE_WORKING_METHOD_STATEMENT("Safe Working Method Statement"),
        PLANT_RISK_ASSESSMENT("Plant Risk Assessment"),
        IMPROVEMENT_ACTION_HAZARD("Improvement Action or Hazard Form"),
        INCIDENT_REPORT("Incident Report (Confidential)");

        private String description;

        RegisterNotifyRegType(String description) {
            this.description = description;
        }

        public static RegisterNotifyRegType resolveRegType(String regTypeStr) {
            RegisterNotifyRegType matchingRegType = null;
            if (!StringUtils.isEmpty(regTypeStr)) {
                matchingRegType = RegisterNotifyRegType.valueOf(regTypeStr.trim());
            }
            return matchingRegType;
        }

        public static Map<String, String> getRegTypeMap() {
            Map<String, String> result = new HashMap<>();
            Arrays.asList(RegisterNotifyRegType.values()).forEach(regType -> result.put(regType.toString(), regType.getDescription()));
            return result;
        }

        public String getDescription() {
            return description;
        }
    }

    public enum RegisterNotifyLevel {
        LOW("Low"),
        MEDIUM("Medium"),
        MODERATE("Moderate"),
        HIGH("High"),
        VERY_HIGH("Very High"),
        EXTREME("Extreme"),
        LEAST_EFFECTIVE("Least Effective"),
        LESS_EFFECTIVE("Less Effective"),
        MOST_EFFECTIVE("Most Effective"),
        STANDARD("Standard"),
        SENSITIVE("Sensitive");

        private String description;

        RegisterNotifyLevel(String description) {
            this.description = description;
        }

        public static RegisterNotifyLevel resolveNotifyLevel(String levelStr) {
            RegisterNotifyLevel matchingNotifyLevel = null;
            if (!StringUtils.isEmpty(levelStr)) {
                matchingNotifyLevel = RegisterNotifyLevel.valueOf(levelStr.trim());
            }
            return matchingNotifyLevel;
        }

        public static Map<String, String> getNotifyLevelMap() {
            Map<String, String> result = new HashMap<>();
            Arrays.asList(RegisterNotifyLevel.values()).forEach(level -> result.put(level.toString(), level.getDescription()));
            return result;
        }

        public String getDescription() {
            return description;
        }
    }

    public enum ActivityObjectType {
        GENERAL("General"),
        BUSINESS_RISK("Business Risk"),
        INTERESTED_PARTIES("Interested Parties"),
        CHEMICAL("Chemical");
        private String description;

        ActivityObjectType(String description) {
            this.description = description;
        }

        public static ActivityObjectType resolveStatus(String statusStr) {
            ActivityObjectType matchingStatus = null;
            if (!StringUtils.isEmpty(statusStr)) {
                matchingStatus = ActivityObjectType.valueOf(statusStr.trim());
            }
            return matchingStatus;
        }

        public static Map<String, String> getStatusMap() {
            Map<String, String> result = new HashMap<>();
            Arrays.asList(AppsConstants.Status.values()).forEach(status -> result.put(status.toString(), status.getDescription()));
            return result;
        }

        public String getDescription() {
            return description;
        }
    }

    public enum ActivityStatus {
        DRAFT("Draft"),
        PENDING("Pending"),
        COMPLETED("Completed"),
        HOLD("Hold"),
        ACTIVE("Active"),
        DEACTIVATED("Deactivated");

        private String description;

        ActivityStatus(String description) {
            this.description = description;
        }

        public static ActivityStatus resolveStatus(String statusStr) {
            ActivityStatus matchingStatus = null;
            if (!StringUtils.isEmpty(statusStr)) {
                matchingStatus = ActivityStatus.valueOf(statusStr.trim());
            }
            return matchingStatus;
        }

        public static Map<String, String> getStatusMap() {
            Map<String, String> result = new HashMap<>();
            Arrays.asList(ActivityStatus.values()).forEach(status -> result.put(status.toString(), status.getDescription()));
            return result;
        }

        public String getDescription() {
            return description;
        }
    }

    public enum ActivityLogStatus {
        CREATED("Created"),
        COMPLETED("Completed"),
        MISSED("Missed"),
        LATE("Late"),
        DEACTIVATED("Deactivated");

        private String description;

        ActivityLogStatus(String description) {
            this.description = description;
        }

        public static ActivityLogStatus resolveStatus(String statusStr) {
            ActivityLogStatus matchingStatus = null;
            if (!StringUtils.isEmpty(statusStr)) {
                matchingStatus = ActivityLogStatus.valueOf(statusStr.trim());
            }
            return matchingStatus;
        }

        public static Map<String, String> getStatusMap() {
            Map<String, String> result = new HashMap<>();
            Arrays.asList(ActivityLogStatus.values()).forEach(status -> result.put(status.toString(), status.getDescription()));
            return result;
        }

        public String getDescription() {
            return description;
        }
    }

    public enum AnnouncementStatus {
        DRAFT("Draft"),
        PUBLISHED("Published"),
        CANCELLED("Cancelled");

        private String description;

        AnnouncementStatus(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }

        public static AnnouncementStatus resolveStatus(String categoryStr) {
            AnnouncementStatus matchingCategory = null;
            if (!StringUtils.isEmpty(categoryStr)) {
                matchingCategory = AnnouncementStatus.valueOf(categoryStr.trim());
            }
            return matchingCategory;
        }
    }

    public enum AnnouncementType {
        ANNOUNCEMENT("Announcement"),
        EVENT("Event");

        private String description;

        AnnouncementType(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }

        public static AnnouncementType resolveStatus(String categoryStr) {
            AnnouncementType matchingCategory = null;
            if (!StringUtils.isEmpty(categoryStr)) {
                matchingCategory = AnnouncementType.valueOf(categoryStr.trim());
            }
            return matchingCategory;
        }
    }

    public enum AnnouncementAckStatus {
        ACKNOWLEDGED("Acknowledged"),
        ACK_REQUIRED("Acknowledgement Required"),
        NOT_APPLICABLE("Not Applicable");

        private String description;

        AnnouncementAckStatus(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }

        public static AnnouncementAckStatus resolveStatus(String categoryStr) {
            AnnouncementAckStatus matchingCategory = null;
            if (!StringUtils.isEmpty(categoryStr)) {
                matchingCategory = AnnouncementAckStatus.valueOf(categoryStr.trim());
            }
            return matchingCategory;
        }
    }

    public enum RiskScore {
        LOW("Low"),
        MEDIUM("Medium"),
        HIGH("High"),
        EXTREME("Extreme");

        private String description;

        RiskScore(String description) {
            this.description = description;
        }

        public static RiskScore resolveStatus(String riskScore) {
            RiskScore matchingRiskScore = null;
            if (!StringUtils.isEmpty(riskScore)) {
                matchingRiskScore = RiskScore.valueOf(riskScore);
            }
            return matchingRiskScore;
        }

        public String getDescription() {
            return description;
        }

    }

    public enum TodoActionType {
        ACTION("Action"),
        ACTIVITY("Activity"),
        CHEMICAL_ASSESSMENT("ChemicalAssessment"),
        RISK_ASSESSMENT("RiskAssessment"),
        SWMS_ASSESSMENT("SwmsAssessment");

        private String description;

        TodoActionType(String description) {
            this.description = description;
        }

        public static TodoActionType resolveStatus(String todoActionType) {
            TodoActionType matchingTodoActionType = null;
            if (!StringUtils.isEmpty(todoActionType)) {
                matchingTodoActionType = TodoActionType.valueOf(todoActionType);
            }
            return matchingTodoActionType;
        }

        public String getDescription() {
            return description;
        }

    }

    public enum TodoStatus {
        PENDING("Pending"),
        COMPLETED("Completed");

        private String description;

        TodoStatus(String description) {
            this.description = description;
        }

        public static TodoStatus resolveStatus(String todoStatus) {
            TodoStatus matchingTodoStatus = null;
            if (!StringUtils.isEmpty(todoStatus)) {
                matchingTodoStatus = TodoStatus.valueOf(todoStatus);
            }
            return matchingTodoStatus;
        }

        public String getDescription() {
            return description;
        }

    }

    public enum ReportFileType {
        HTML("html"),
        PDF("pdf");

        private String description;

        ReportFileType(String description) {
            this.description = description;
        }

        public static ReportFileType resolveStatus(String reportFileTypeStr) {
            ReportFileType status = null;
            if (!StringUtils.isEmpty(reportFileTypeStr)) {
                status = ReportFileType.valueOf(reportFileTypeStr.trim());
            }
            return status;
        }

        public String getDescription() {
            return description;
        }
    }
}
