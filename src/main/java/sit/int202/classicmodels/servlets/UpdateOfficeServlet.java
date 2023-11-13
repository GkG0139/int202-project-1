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
import sit.int202.classicmodels.validations.UpdateOfficeValidation;

@WebServlet(name = "UpdateOfficeServlet", value = "/edit-office")
public class UpdateOfficeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        String officeCode = req.getParameter("code");

        OfficeRepository repository = new OfficeRepository();
        Office foundOffice = repository.find(officeCode);
        if (foundOffice == null) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        req.setAttribute("office", foundOffice);
        req.getRequestDispatcher("/update_office.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        String officeCode = req.getParameter("code");

        OfficeRepository repository = new OfficeRepository();
        Office foundOffice = repository.find(officeCode);

        if (foundOffice == null) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        Office updatedOffice = OfficeUtils.createNewOfficeFromRequest(req, officeCode);

        if (!UpdateOfficeValidation.validate(req)) {
            req.setAttribute("office", updatedOffice);
            req.setAttribute("error", "Missing field(s) is not provided!");
            req.getRequestDispatcher("/update_office.jsp").forward(req, resp);
            return;
        }

        boolean isUpdatedSuccess = repository.update(updatedOffice);
        if (isUpdatedSuccess) {
            ServletRedirectUtils.redirectToPageWithStatus(req, resp, "/office-list",
                PageStatus.SUCCESS, "Successfully update the office!");
            return;
        }
        req.setAttribute("office", updatedOffice);
        req.setAttribute("error", "Error while updating the office!");
        req.getRequestDispatcher("/update_office.jsp").forward(req, resp);
    }
}
