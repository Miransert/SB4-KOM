package dk.sdu.student.miser21.common.util;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceConfigurationError;
import java.util.ServiceLoader;

public class SPILocator {

    private static final List<ServiceLoader<?>> loaderList = new ArrayList<>();

    private SPILocator() {
    }

    public static <T> List<T> locateAll(Class<T> service) {
        ServiceLoader<T> loader = findLoader(service);

        boolean isNewLoader = (loader == null);
        if (isNewLoader) {
            loader = ServiceLoader.load(service);
            loaderList.add(loader);
        }

        List<T> list = new ArrayList<>();

        try {
            for (T instance : loader) {
                list.add(instance);
            }
        } catch (ServiceConfigurationError serviceError) {
            serviceError.printStackTrace();
        }

        if (isNewLoader) {
            System.out.println("Amount of implementation: " + list.size() + " Interface: " + service.getName());
        }

        return list;
    }

    @SuppressWarnings("unchecked")
    private static <T> ServiceLoader<T> findLoader(Class<T> service) {
        for (ServiceLoader<?> loader : loaderList) {
            if (loader.iterator().hasNext() && loader.iterator().next().getClass().isAssignableFrom(service)) {
                return (ServiceLoader<T>) loader;
            }
        }
        return null;
    }
}
