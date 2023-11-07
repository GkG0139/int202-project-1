package sit.int202.classicmodels.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@Entity
@Table(name = "offices")
@NamedQuery(name = "Office.findAll", query = "SELECT o FROM Office o")
@NamedQuery(name = "Office.findMaxId", query = "SELECT MAX(o.officeCode) FROM Office o")
@ToString
public class Office {

    @Id
    private String officeCode;
    private String city;
    private String phone;
    private String addressLine1;
    private String addressLine2;
    private String state;
    private String country;
    private String postalCode;
    private String territory;

    @OneToMany(mappedBy = "office")
    private List<Employee> employeeList;
}
