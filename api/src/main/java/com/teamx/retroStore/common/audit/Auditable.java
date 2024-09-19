package com.teamx.retroStore.common.audit;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation is going to use audit the entities for the audit.
 * Annotation is used in the field levels only in the
 * praticular entity.
 */
@Target(value = ElementType.FIELD)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface Auditable {

    String name() default "";

    String[] auditFields() default "ALL";

    String fetchType() default "EAGER";
}