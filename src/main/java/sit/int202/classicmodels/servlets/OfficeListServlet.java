package sit.int202.classicmodels.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import sit.int202.classicmodels.models.Office;
import sit.int202.classicmodels.repositories.OfficeRepository;

@WebServlet(name = "OfficeListServlet", value = "/office-list")
public class OfficeListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        OfficeRepository repository = new OfficeRepository();
        List<Office> offices = repository.findAll();
        req.setAttribute("offices", offices);
        req.getRequestDispatcher("/office_list.jsp").forward(req, resp);
    }
}
