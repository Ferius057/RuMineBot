package kz.ferius_057.ruminebot.command.api;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Ferius_057 (Charles_Grozny)
 * ⭐ 04.07.2022 | 22:22 ⭐
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Permission {
    int value() default 0;
}