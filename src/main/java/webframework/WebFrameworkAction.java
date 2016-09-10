package webframework;

import org.reflections.Reflections;
import webframework.impls.WebClassFramework;
import webframework.impls.WebMethodFramework;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;

/**
 * Created by shyslav on 9/10/16.
 */
public class WebFrameworkAction {
    public static void findController(HttpServletRequest req, HttpServletResponse resp) throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, ServletException, IOException {
        System.out.println(req.getRequestURL());
        Reflections reflections = new Reflections("com.shyslav.controller");
        Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(WebClassFramework.class);
        for (Class cls : annotated) {
            if (cls.isAnnotationPresent(WebClassFramework.class)) {
                WebClassFramework classFramework = (WebClassFramework) cls.getAnnotation(WebClassFramework.class);
                if (classFramework.urlPath().equals(getClassPath(req.getRequestURI()))) {
                    System.out.println("class annotated ; name  -  " + cls.getAnnotation(WebClassFramework.class));
                    Method[] method = cls.getMethods();
                    for (Method md : method) {
                        WebMethodFramework ta = md.getAnnotation(WebMethodFramework.class);
                        if (md.isAnnotationPresent(WebMethodFramework.class)
                                && generateLinkToMethod(classFramework.urlPath(), ta.url(), req)) {
                            System.out.println("URL: " + ta.url() + " ROLE:" + ta.role());
                            generateContent(req, resp, ta.jspPath());
                            md.invoke(cls.newInstance(), req, resp);
                            redirect(req, resp, classFramework.layout(),ta.jspPath());
                            return;
                        }
                    }
                }
            }
        }
    }

    /**
     * Generate redirect to layout
     *
     * @param request  - servlet request
     * @param response - servlet response
     * @param layout   - link to layout jsp
     * @param path     - link to file
     * @throws ServletException
     * @throws IOException
     */
    private static void redirect(HttpServletRequest request, HttpServletResponse response, String layout, String path) throws ServletException, IOException {
        if (!path.equalsIgnoreCase("ajax")) {
            request.getRequestDispatcher("/WEB-INF/app/layout/" + layout.trim() + ".jsp").forward(request, response);
        }
    }

    /**
     * Read jsp file and set to attribute _content_
     *
     * @param req  - servlet request
     * @param resp - servlet response
     * @param path - link to file
     * @throws ServletException
     * @throws IOException
     */
    private static void generateContent(HttpServletRequest req, HttpServletResponse resp, String path) throws ServletException, IOException {
        if (!path.equalsIgnoreCase("ajax")) {
            HttpServletResponseWrapper responseWrapper = new HttpServletResponseWrapper(resp) {
                private final StringWriter sw = new StringWriter();

                @Override
                public PrintWriter getWriter() throws IOException {
                    return new PrintWriter(sw);
                }

                @Override
                public String toString() {
                    return sw.toString();
                }
            };
            req.getRequestDispatcher("/WEB-INF/app/" + path + ".jsp").include(req, responseWrapper);
            String content = responseWrapper.toString();
            req.setAttribute("_content_", content);
        }
    }

    /**
     * Get path to controller
     *
     * @param uri - request uri
     * @return - uri to controller
     */
    private static String getClassPath(String uri) {
        String[] splits = uri.trim().split("/");
        for (String split : splits) {
            if (!split.trim().equals("/") && split.length() != 0) {
                return split;
            }
        }
        return null;
    }

    /**
     * Check if class have link method
     *
     * @param classURI  class uri
     * @param methodURI method uri
     * @param req       servlet request
     * @return result of search
     */
    private static boolean generateLinkToMethod(String classURI, String methodURI, HttpServletRequest req) {
        String result = "";
        if (req.getRequestURI().charAt(0) == '/') {
            result = "/";
        }
        result += classURI.trim();
        if (methodURI.length() != 0) {
            result += "/" + methodURI;
        }
        return result.equalsIgnoreCase(req.getRequestURI());
    }

}
