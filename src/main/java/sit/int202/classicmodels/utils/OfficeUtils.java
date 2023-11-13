package sit.int202.classicmodels.utils;

import jakarta.servlet.http.HttpServletRequest;
import lombok.experimental.UtilityClass;
import sit.int202.classicmodels.models.Office;

@UtilityClass
public class OfficeUtils {

    public static Office createNewOfficeFromRequest(HttpServletRequest req, String officeCode) {
        Office newOffice = new Office();
        newOffice.setOfficeCode(officeCode);
        newOffice.setAddressLine1(req.getParameter("addressLine1"));
        newOffice.setAddressLine2(req.getParameter("addressLine2"));
        newOffice.setCity(req.getParameter("city"));
        newOffice.setState(req.getParameter("state"));
        newOffice.setCountry(req.getParameter("country"));
        newOffice.setPostalCode(req.getParameter("postalCode"));
        newOffice.setTerritory(req.getParameter("territory"));
        newOffice.setPhone(req.getParameter("phone"));
        return newOffice;
    }
}
