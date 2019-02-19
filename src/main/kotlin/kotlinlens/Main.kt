package kotlinlens

fun main() {
    val employee = createEmployee()

    // Manual way (repetitive, non-reusable)
    val manuallyModifiedEmployee = employee.copy(
        company = employee.company.copy(
            address = employee.company.address.copy(
                street = employee.company.address.street.copy(
                    name = "Teststreet 2"
                )
            )
        )
    )

    // Lens way
    val createEmployeeCompanyStreetNameLens = createEmployeeCompanyStreetNameLens()
    val autoModifiedEmployee = createEmployeeCompanyStreetNameLens.set(employee, "Teststreet 2")
}

fun createEmployeeCompanyStreetNameLens() =
    Employee.company compose Company.address compose Address.street compose Street.name

fun createEmployee() = Employee(
    name = "John",
    age = 30,
    company = Company(
        name = "UPB",
        address = Address(
            street = Street("Teststreet"),
            number = 42
        )
    )
)