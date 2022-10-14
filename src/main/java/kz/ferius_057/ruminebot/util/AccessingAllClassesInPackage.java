package kz.ferius_057.ruminebot.util;

import com.google.common.reflect.ClassPath;
import kz.ferius_057.ruminebot.command.api.impl.AbstractCommand;
import kz.ferius_057.ruminebot.command.api.annotation.CommandAnnotation;
import kz.ferius_057.ruminebot.event.api.impl.AbstractEvent;
import kz.ferius_057.ruminebot.event.api.annotation.EventAnnotation;
import lombok.experimental.UtilityClass;

import java.io.IOException;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@UtilityClass
public class AccessingAllClassesInPackage {

    private List<Class<?>> getClassesInPackage(final String packageName, Predicate<Class<?>> filter) throws IOException {
        return ClassPath.from(ClassLoader.getSystemClassLoader())
                .getAllClasses()
                .stream()
                .filter(x -> x.getPackageName().startsWith(packageName))
                .map(ClassPath.ClassInfo::load)
                .filter(filter)
                .collect(Collectors.toList());
    }

    public List<Class<?>> getClassesCommand(final String packageName) throws IOException {
        return getClassesInPackage(packageName,  filter -> AbstractCommand.class.isAssignableFrom(filter) && filter.getDeclaredAnnotation(CommandAnnotation.class) != null);
    }

    public List<Class<?>> getClassesEvent(final String packageName) throws IOException {
        return getClassesInPackage(packageName,  filter -> AbstractEvent.class.isAssignableFrom(filter) && filter.getDeclaredAnnotation(EventAnnotation.class) != null);
    }
}