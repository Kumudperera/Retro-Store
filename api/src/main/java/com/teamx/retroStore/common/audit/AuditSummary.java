package com.teamx.retroStore.common.audit;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value = ElementType.FIELD)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface AuditSummary {

    String name() default "";

    String[] auditFields() default "ALL";

    String fetchType() default "EAGER";
}