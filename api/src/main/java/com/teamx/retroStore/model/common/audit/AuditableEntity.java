package com.teamx.retroStore.model.common.audit;

import com.teamx.retroStore.model.common.UserTraceableEntity;
import com.teamx.retroStore.util.CalendarUtil;
import com.teamx.retroStore.util.DecimalCalculator;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public interface AuditableEntity extends Serializable {

    String getAuditSummary();

    String getAuditContent();

    default String getStringValue(Object ob) {
        if (ob == null || StringUtils.isBlank(ob.toString())) {
            return "-";
        }
        return ob.toString().replaceAll("\"", "\\\\\"");
    }

    default String getStringDate(Object object) {
        if (isNull(object)) {
            return "-";
        }
        return CalendarUtil.getDefaultFormattedDateTime((Date) object);
    }

    default String getSummaryString(AuditableEntity auditableEntity) {
        if (isNull(auditableEntity)) {
            return "\"-\"";
        } else {
            return auditableEntity.getAuditSummary();
        }
    }

    default String getContentString(AuditableEntity auditableEntity) {
        if (isNull(auditableEntity)) {
            return "\"-\"";
        } else {
            return auditableEntity.getAuditContent();
        }
    }

    default String getStringBigDecimal(BigDecimal value) {
        if (isNull(value)) {
            return "-";
        } else {
            return getRoundValue(value).toString();
        }
    }

    default String getStringDouble(Double value) {
        if (isNull(value)) {
            return "-";
        } else {
            return getRoundValue(value).toString();
        }
    }

    default String getStringInteger(Integer value) {
        if (isNull(value)) {
            return "-";
        } else {
            return getRoundValue(value).toString();
        }
    }


    default <T extends AuditableEntity> String getSummaryStringList(Collection<T> entitySet) {
        if (entitySet == null || entitySet.isEmpty()) {
            return "\"-\"";
        } else {
            return "[" + entitySet.stream().map(AuditableEntity::getAuditSummary).collect(Collectors.joining(",")) + "]";
        }
    }

    default <T extends AuditableEntity> String getAuditContentList(Collection<T> entitySet) {
        if (entitySet == null || entitySet.isEmpty()) {
            return "\"-\"";
        } else {
            return "[" + entitySet.stream().map(AuditableEntity::getAuditContent).collect(Collectors.joining(",")) + "]";
        }
    }

    default <T extends UserTraceableEntity> List<T> getSortedListByDate(Collection<T> entitySet) {
        if (entitySet != null && !entitySet.isEmpty()) {
            return entitySet.stream().sorted((o1, o2) -> {
                if (CalendarUtil.isBefore(getLastUpdatedDate(o1), getLastUpdatedDate(o2))) {
                    return 1;
                } else if (CalendarUtil.isEqual(getLastUpdatedDate(o1), getLastUpdatedDate(o2))) {
                    return 0;
                } else {
                    return -1;
                }
            }).collect(Collectors.toList());
        } else {
            return null;
        }
    }

    default BigDecimal getRoundValue(BigDecimal value) {
        if (value != null) {
            value = DecimalCalculator.round(value, DecimalCalculator.getDefaultCurrencyMathContext());
        } else {
            value = DecimalCalculator.getDefaultZero();
        }
        return value;
    }

    default Double getRoundValue(Double value) {
        if (value == null) {
            return 0.0;
        }
        BigDecimal bgValue = DecimalCalculator.round(DecimalCalculator.parseDouble(value)
                , DecimalCalculator.getDefaultCurrencyMathContext());
        bgValue = DecimalCalculator.round(bgValue, DecimalCalculator.getDefaultCurrencyMathContext());
        return bgValue.doubleValue();
    }

    default Integer getRoundValue(Integer value) {
        if (value == null) {
            return 0;
        }
        return value;
    }

    default Date getLastUpdatedDate(UserTraceableEntity entity) {
        return entity.getModifiedDate() != null ? entity.getModifiedDate() : entity.getCreatedDate();
    }

    default boolean isNull(Object object) {
        return object == null;
    }

}
