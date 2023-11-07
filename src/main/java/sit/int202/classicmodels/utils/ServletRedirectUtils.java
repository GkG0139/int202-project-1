package sit.int202.classicmodels.utils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ServletRedirectUtils {

    public static void redirectToPageWithStatus(
        HttpServletRequest request,
        HttpServletResponse response,
        String page,
        PageStatus status,
        String message
    ) throws IOException {
        response.sendRedirect(
            response.encodeRedirectURL(
                request.getContextPath() + page + "?status=" + status
                    + "&message=" + message
            )
        );
    }

    public enum PageStatus {
        ERROR, SUCCESS
    }
}
