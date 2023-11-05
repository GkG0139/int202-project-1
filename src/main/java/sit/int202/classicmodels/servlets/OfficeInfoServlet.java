package sit.int202.classicmodels.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import sit.int202.classicmodels.models.Office;
import sit.int202.classicmodels.repositories.OfficeRepository;

@WebServlet(name = "OfficeInfoServlet", value = "/office")
public class OfficeInfoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        String officeCode = req.getParameter("code");
        if (officeCode == null || officeCode.isEmpty()) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        OfficeRepository repository = new OfficeRepository();
        Office foundOffice = repository.find(officeCode);

        if (foundOffice == null) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        req.setAttribute("office", foundOffice);
        req.getRequestDispatcher("/office.jsp").forward(req, resp);
    }
}
