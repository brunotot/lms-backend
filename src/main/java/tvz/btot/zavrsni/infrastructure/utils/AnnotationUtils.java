package tvz.btot.zavrsni.infrastructure.utils;

import com.google.common.reflect.ClassPath;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.*;
import tvz.btot.zavrsni.domain.AllowableUrlMethods;
import tvz.btot.zavrsni.security.preauthorization.AllowAnonymous;

import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class AnnotationUtils {
    private static final String SOCKET_PACKAGE_URI = "tvz.btot.zavrsni.web.controller.socket";
    private static final String CONTROLLER_PACKAGE_URI = "tvz.btot.zavrsni.web.controller";

    @SneakyThrows
    public static List<AllowableUrlMethods> getAllowableUrlMethodsListForAllControllerClassNames() {
        List<AllowableUrlMethods> list = new ArrayList<>();
        List<String> controllerClassNames = getClassNamesForAllControllers();
        for (final String controllerClassName : controllerClassNames) {
            List<AllowableUrlMethods> allowableUrlMethodsList = getAllowableUrlMethodsListForControllerClassName(controllerClassName);
            list = Stream.of(list, allowableUrlMethodsList)
                    .flatMap(Collection::stream)
                    .collect(Collectors.toList());
        }
        return list;
    }

    @SneakyThrows
    public static List<String> getClassNamesForAllControllers() {
        return Stream.of(getControllerClassNames(CONTROLLER_PACKAGE_URI), getControllerClassNames(SOCKET_PACKAGE_URI))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    public static List<String> getControllerClassNames(final String packageName) {
        List<String> controllerClassNames = new ArrayList<>();
        final ClassLoader loader = Thread.currentThread().getContextClassLoader();
        try {
            final ClassPath classpath = ClassPath.from(loader);
            for (final ClassPath.ClassInfo classInfo : classpath.getTopLevelClasses(packageName)) {
                if (!classInfo.getSimpleName().endsWith("_")) {
                    controllerClassNames.add(classInfo.getName());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return controllerClassNames;
    }

    @SneakyThrows
    public static List<AllowableUrlMethods> getAllowableUrlMethodsListForControllerClassName(final String className) {
        Set<AllowableUrlMethods> allowableUrlMethodsList = new TreeSet<>((o1, o2) ->
               Objects.equals(o1.getUri(), o2.getUri())
            && Objects.equals(o1.getMethods(), o2.getMethods())
            && Objects.equals(o1.getControllerContextUri(), o2.getControllerContextUri())
                ? 0 : 1);
        Class<?> clazz = Class.forName(className);
        if (clazz.isAnnotationPresent(RequestMapping.class)) {
            String[] controllerValueArray = clazz.getDeclaredAnnotation(RequestMapping.class).value();
            String controllerContextUri = String.join("/", controllerValueArray);
            Method[] clazzMethods = clazz.getMethods();
            for (final Method method : clazzMethods) {
                if (method.isAnnotationPresent(AllowAnonymous.class)) {
                    AllowableUrlMethods aum = new AllowableUrlMethods(controllerContextUri);
                    if (method.isAnnotationPresent(RequestMapping.class)) {
                        RequestMapping mappingAnnotation = method.getDeclaredAnnotation(RequestMapping.class);
                        aum.setMethods(mappingAnnotation.method());
                        String uri = String.join("/", mappingAnnotation.value());
                        aum.setUri(uri);
                        allowableUrlMethodsList.add(aum);
                    } else if (method.isAnnotationPresent(GetMapping.class)) {
                        GetMapping mappingAnnotation = method.getDeclaredAnnotation(GetMapping.class);
                        aum.addMethod(RequestMethod.GET);
                        String uri = String.join("/", mappingAnnotation.value());
                        aum.setUri(uri);
                        allowableUrlMethodsList.add(aum);
                    } else if (method.isAnnotationPresent(PostMapping.class)) {
                        PostMapping mappingAnnotation = method.getDeclaredAnnotation(PostMapping.class);
                        aum.addMethod(RequestMethod.POST);
                        String uri = String.join("/", mappingAnnotation.value());
                        aum.setUri(uri);
                        allowableUrlMethodsList.add(aum);
                    } else if (method.isAnnotationPresent(PutMapping.class)) {
                        PutMapping mappingAnnotation = method.getDeclaredAnnotation(PutMapping.class);
                        aum.addMethod(RequestMethod.PUT);
                        String uri = String.join("/", mappingAnnotation.value());
                        aum.setUri(uri);
                        allowableUrlMethodsList.add(aum);
                    } else if (method.isAnnotationPresent(DeleteMapping.class)) {
                        DeleteMapping mappingAnnotation = method.getDeclaredAnnotation(DeleteMapping.class);
                        aum.addMethod(RequestMethod.DELETE);
                        String uri = String.join("/", mappingAnnotation.value());
                        aum.setUri(uri);
                        allowableUrlMethodsList.add(aum);
                    } else if (method.isAnnotationPresent(PatchMapping.class)) {
                        PatchMapping mappingAnnotation = method.getDeclaredAnnotation(PatchMapping.class);
                        aum.addMethod(RequestMethod.PATCH);
                        String uri = String.join("/", mappingAnnotation.value());
                        aum.setUri(uri);
                        allowableUrlMethodsList.add(aum);
                    }
                }
            }

        }
        return new ArrayList<>(allowableUrlMethodsList);
    }

    private AnnotationUtils() {
        throw new UnsupportedOperationException();
    }
}
