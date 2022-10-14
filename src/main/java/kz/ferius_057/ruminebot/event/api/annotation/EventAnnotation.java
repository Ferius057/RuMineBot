package kz.ferius_057.ruminebot.event.api.annotation;

import kz.ferius_057.ruminebot.event.api.model.media.MessageActionStatus;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface EventAnnotation {

    MessageActionStatus[] statuses();

}
