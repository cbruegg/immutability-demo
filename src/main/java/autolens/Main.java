package autolens;

import lib.Lens;

public class Main {

    // TODO Writing AutoValue plugins seems fairly straightforward. We could even consider
    //      writing an own plugin that generates the Lenses for us.
    //      https://github.com/gabrielittner/auto-value-with/tree/master/auto-value-with/src/main/java/com/gabrielittner/auto/value/with

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
        return Lens.of(Employee::company, Employee::withCompany)
            .compose(Lens.of(Company::address, Company::withAddress))
            .compose(Lens.of(Address::street, Address::withStreet))
            .compose(Lens.of(Street::name, Street::withName));
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
