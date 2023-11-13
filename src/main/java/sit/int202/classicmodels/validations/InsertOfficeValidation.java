package sit.int202.classicmodels.validations;

import jakarta.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lombok.experimental.UtilityClass;

@UtilityClass
public class InsertOfficeValidation {

    private static final List<String> REQUIRED_FIELDS = new ArrayList<>(
        Arrays.asList("addressLine1", "city", "country", "postalCode", "territory", "phone"));

    public static boolean validate(HttpServletRequest request) {
        return REQUIRED_FIELDS.stream().allMatch(
            field -> request.getParameter(field) != null && !request.getParameter(field).isEmpty());
    }
}
