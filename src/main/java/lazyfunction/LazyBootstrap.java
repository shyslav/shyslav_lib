package lazyfunction;

/**
 * Created by shyslav on 9/11/16.
 */
public class LazyBootstrap {
    public static String generateAlert(String alertType, String title, String text ){
        StringBuilder builder = new StringBuilder();
        builder.append("<div class='alert alert-").append(alertType.trim()).append("'>");
        builder.append("<strong>").append(title).append("</strong><br>");
        builder.append(text);
        builder.append("</div>");
        return builder.toString();
    }
}
