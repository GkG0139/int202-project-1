package sit.int202.classicmodels.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import sit.int202.classicmodels.models.Office;
import sit.int202.classicmodels.repositories.OfficeRepository;
import sit.int202.classicmodels.utils.ServletRedirectUtils;
import sit.int202.classicmodels.utils.ServletRedirectUtils.PageStatus;

@WebServlet(name = "AddOfficeServlet", value = "/add-office")
public class AddOfficeServlet extends HttpServlet {

    private static final List<String> REQUIRED_FIELDS = new ArrayList<>(
        Arrays.asList(
            "addressLine1", "city", "country", "postalCode", "territory",
            "phone"));

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        req.getRequestDispatcher("/add_office.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        boolean isValidated = REQUIRED_FIELDS.stream().allMatch(
            field -> req.getParameter(field) != null && !req.getParameter(field)
                .isEmpty());

        if (!isValidated) {
            req.setAttribute("error", "Missing field(s) is not provided!");
            req.getRequestDispatcher("/add_office.jsp").forward(req, resp);
            return;
        }

        Office newOffice = createNewOfficeFromRequest(req);
        OfficeRepository repository = new OfficeRepository();
        boolean isInsertSuccess = repository.insert(newOffice);
        if (isInsertSuccess) {
            ServletRedirectUtils.redirectToPageWithStatus(req, resp,
                "/office-list",
                PageStatus.SUCCESS, "Successfully added new office!");
            return;
        }
        req.setAttribute("error", "Error while inserting new office!");
        req.getRequestDispatcher("/add_office.jsp").forward(req, resp);
    }

    private Office createNewOfficeFromRequest(HttpServletRequest req) {
        Office newOffice = new Office();
        newOffice.setAddressLine1(req.getParameter("addressLine1"));
        newOffice.setAddressLine2(req.getParameter("addressLine2"));
        newOffice.setCity(req.getParameter("city"));
        newOffice.setState(req.getParameter("state"));
        newOffice.setCountry(req.getParameter("country"));
        newOffice.setPostalCode(req.getParameter("postalCode"));
        newOffice.setTerritory(req.getParameter("territory"));
        newOffice.setPhone(req.getParameter("phone"));

        OfficeRepository repository = new OfficeRepository();
        String nextOfficeCode = String.valueOf(repository.getNextId());
        newOffice.setOfficeCode(nextOfficeCode);

        return newOffice;
    }
}
