package webframework;

import org.reflections.Reflections;
import webframework.impls.WebClassFramework;
import webframework.impls.WebMethodFramework;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;

/**
 * Created by shyslav on 9/10/16.
 */
public class WebFrameworkAction {
    public static void findController(HttpServletRequest req, HttpServletResponse resp) throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException {
        System.out.println(req.getRequestURL());
        Reflections reflections = new Reflections("com.shyslav.controller");
        Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(WebClassFramework.class);
        for (Class cls : annotated) {
            if (cls.isAnnotationPresent(WebClassFramework.class)) {
                WebClassFramework classFramework = (WebClassFramework) cls.getAnnotation(WebClassFramework.class);
                if (("/" + classFramework.urlPath()).equals(req.getRequestURI())) {
                    System.out.println("class annotated ; name  -  " + cls.getAnnotation(WebClassFramework.class));
                    Method[] method = cls.getMethods();
                    for (Method md : method) {
                        WebMethodFramework ta = md.getAnnotation(WebMethodFramework.class);
                        if (md.isAnnotationPresent(WebMethodFramework.class) && ta.url().equals(req.getRequestURI())) {
                            System.out.println("URL: " + ta.url() + " ROLE:" + ta.role());
                            md.invoke(cls.newInstance(), req, resp);
                        }
                    }
                    return;
                }
            }
        }
    }
}
