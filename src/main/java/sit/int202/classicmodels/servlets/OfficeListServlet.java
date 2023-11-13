package sit.int202.classicmodels.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import sit.int202.classicmodels.models.Office;
import sit.int202.classicmodels.repositories.OfficeRepository;

@WebServlet(name = "OfficeListServlet", value = "/office-list")
public class OfficeListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        OfficeRepository repository = new OfficeRepository();
        List<Office> offices = repository.findAll();
        Map<String, Set<String>> countryToCitiesMap = generateCountryToCitiesMap(
            offices);

        filterOfficesBySearchText(req, offices, repository);
        filterOfficesBySearchSelect(req, offices);

        req.setAttribute("countryToCities", countryToCitiesMap);
        req.setAttribute("offices", offices);
        req.getRequestDispatcher("/office_list.jsp").forward(req, resp);
    }

    private void filterOfficesBySearchText(HttpServletRequest req,
        List<Office> offices, OfficeRepository repository) {
        String searchText = req.getParameter("searchText");

        if (searchText == null || searchText.isEmpty()) {
            return;
        }

        List<Office> filteredOffices = repository.findByCityOrCountry(
            searchText);
        offices.clear();
        offices.addAll(filteredOffices);
    }

    private void filterOfficesBySearchSelect(
        HttpServletRequest req,
        List<Office> offices
    ) {
        String searchSelect = req.getParameter("searchSelect");
        if (searchSelect == null || searchSelect.isEmpty()) {
            return;
        }

        String[] parts = searchSelect.split("\\|");
        String country = parts[0];
        String city = parts[1];
        offices.removeIf(office -> !office.getCountry().equals(country)
            || !office.getCity().equals(city));
    }

    private Map<String, Set<String>> generateCountryToCitiesMap(
        List<Office> offices
    ) {
        Map<String, Set<String>> countryToCitiesMap = new HashMap<>();
        for (Office office : offices) {
            countryToCitiesMap.computeIfAbsent(office.getCountry(),
                k -> new HashSet<>()).add(office.getCity());
        }
        return countryToCitiesMap;
    }
}

