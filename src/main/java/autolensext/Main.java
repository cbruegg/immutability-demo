package autolensext;

import me.tatarka.lens.Lens;

public class Main {

    public static void main(String[] args) {
        Employee employee = createEmployee();

        // Manual way (repetitive, non-reusable)
        Employee manuallyModifiedEmployee = employee.withCompany(
            employee.company().withAddress(
                employee.company().address().withStreet(
                    employee.company().address().street().withName("Teststreet 2")
                )
            )
        );

        // Lens way
        Lens<Employee, String> createEmployeeCompanyStreetNameLens = createEmployeeCompanyStreetNameLens();
        Employee autoModifiedEmployee = createEmployeeCompanyStreetNameLens.set(employee, "Teststreet 2");
    }

    // Can be done once, reduces boilerplate everywhere else
    private static Lens<Employee, String> createEmployeeCompanyStreetNameLens() {
        return Employee.lenses().company()
            .andThen(Company.lenses().address())
            .andThen(Address.lenses().street())
            .andThen(Street.lenses().name());
    }

    private static Employee createEmployee() {
        return Employee.create(
            "John",
            30,
            Company.create(
                "UPB",
                Address.create(
                    Street.create("Teststreet"),
                    42
                )
            )
        );
    }

}
