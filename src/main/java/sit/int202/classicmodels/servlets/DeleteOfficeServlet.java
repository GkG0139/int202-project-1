package sit.int202.classicmodels.servlets;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import sit.int202.classicmodels.repositories.OfficeRepository;
import sit.int202.classicmodels.utils.ServletRedirectUtils;
import sit.int202.classicmodels.utils.ServletRedirectUtils.PageStatus;

@WebServlet(name = "DeleteOfficeServlet", value = "/delete-office")
public class DeleteOfficeServlet extends HttpServlet {

    private static final String OFFICE_LIST_PAGE = "/office-list";
    private static final String ERROR_MESSAGE = "Something went wrong while deleting the office!";
    private static final String SUCCESS_MESSAGE_PREFIX = "Successfully deleted office with id ";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws IOException {
        String officeCode = req.getParameter("code");

        if (officeCode == null || officeCode.isEmpty()) {
            resp.sendRedirect(OFFICE_LIST_PAGE);
            return;
        }

        OfficeRepository repository = new OfficeRepository();
        boolean isDeleted = repository.delete(officeCode);

        if (isDeleted) {
            String successMessage = SUCCESS_MESSAGE_PREFIX + officeCode + "!";
            ServletRedirectUtils.redirectToPageWithStatus(
                req,
                resp,
                OFFICE_LIST_PAGE,
                PageStatus.SUCCESS,
                successMessage
            );
        } else {
            ServletRedirectUtils.redirectToPageWithStatus(
                req,
                resp,
                OFFICE_LIST_PAGE,
                PageStatus.ERROR,
                ERROR_MESSAGE
            );
        }
    }
}
