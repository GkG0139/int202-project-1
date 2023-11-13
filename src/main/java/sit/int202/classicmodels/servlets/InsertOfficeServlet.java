package sit.int202.classicmodels.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import sit.int202.classicmodels.models.Office;
import sit.int202.classicmodels.repositories.OfficeRepository;
import sit.int202.classicmodels.utils.OfficeUtils;
import sit.int202.classicmodels.utils.ServletRedirectUtils;
import sit.int202.classicmodels.utils.ServletRedirectUtils.PageStatus;
import sit.int202.classicmodels.validations.InsertOfficeValidation;

@WebServlet(name = "InsertOfficeServlet", value = "/add-office")
public class InsertOfficeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        req.getRequestDispatcher("/add_office.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        if (!InsertOfficeValidation.validate(req)) {
            req.setAttribute("error", "Missing field(s) is not provided!");
            req.getRequestDispatcher("/add_office.jsp").forward(req, resp);
            return;
        }

        OfficeRepository repository = new OfficeRepository();
        String nextOfficeCode = String.valueOf(repository.getNextId());
        Office newOffice = OfficeUtils.createNewOfficeFromRequest(req, nextOfficeCode);
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
}
