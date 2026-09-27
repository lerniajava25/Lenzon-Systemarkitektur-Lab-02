package se.lernia.di.container;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Container {
    private final Map<Class<?>, Class<?>> bindings = new HashMap<>();

    public <T> Container bind(Class<T> type, Class<? extends T> implementation) {
        bindings.put(type, implementation);
        return this;
    }

    public <T> T get(Class<T> type) {
        return type.cast(create(type, new ArrayDeque<>()));
    }

    private Object create(Class<?> type, Deque<Class<?>> path) {
        Class<?> implementation = bindings.getOrDefault(type, type);

        if (implementation.isInterface() || Modifier.isAbstract(implementation.getModifiers())) {
            throw new IllegalStateException("No implementation bound for " + type.getName());
        }
        if (path.contains(implementation)) {
            throw new IllegalStateException("Circular dependency: " + describe(path) + " -> " + implementation.getSimpleName());
        }

        Constructor<?>[] constructors = implementation.getConstructors();
        if (constructors.length != 1) {
            throw new IllegalStateException(implementation.getName() + " must have exactly one public constructor");
        }
        Constructor<?> constructor = constructors[0];

        path.addLast(implementation);
        Object[] arguments = new Object[constructor.getParameterCount()];
        Class<?>[] parameterTypes = constructor.getParameterTypes();
        for (int i = 0; i < parameterTypes.length; i++) {
            arguments[i] = create(parameterTypes[i], path);
        }
        path.removeLast();

        try {
            return constructor.newInstance(arguments);
        } catch (InvocationTargetException e) {
            throw new IllegalStateException("Constructor of " + implementation.getName() + " failed", e.getCause());
        } catch (ReflectiveOperationException e) {
            throw new IllegalStateException("Could not create " + implementation.getName(), e);
        }
    }

    private String describe(Deque<Class<?>> path) {
        return path.stream().map(Class::getSimpleName).collect(Collectors.joining(" -> "));
    }
}
