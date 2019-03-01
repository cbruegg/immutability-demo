package manuallens;

import lib.Lens;

public class Main {
    public static void main(String[] args) {
        Employee employee = createEmployee();

        // Manual way (repetitive, non-reusable)
        Employee manuallyModifiedEmployee = employee.withCompany(
            employee.getCompany().withAddress(
                employee.getCompany().getAddress().withStreet(
                    employee.getCompany().getAddress().getStreet().withName("Teststreet 2")
                )
            )
        );

        // Lens way
        Lens<Employee, String> employeeCompanyStreetNameLens = createEmployeeCompanyStreetNameLens();
        Employee autoModifiedEmployee = employeeCompanyStreetNameLens.set(employee, "Teststreet 2");
    }

    // Can be done once, reduces boilerplate everywhere else
    private static Lens<Employee, String> createEmployeeCompanyStreetNameLens() {
        return Lens.of(Employee::getCompany, Employee::withCompany)
            .compose(Lens.of(Company::getAddress, Company::withAddress))
            .compose(Lens.of(Address::getStreet, Address::withStreet))
            .compose(Lens.of(Street::getName, Street::withName));
    }

    private static Employee createEmployee() {
        return new Employee(
            "John",
            30,
            new Company(
                "UPB",
                new Address(
                    new Street("Teststreet"),
                    42
                )
            )
        );
    }
}
