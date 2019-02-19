package autolensext;

import com.google.auto.value.AutoValue;
import me.tatarka.lens.Lens;
import me.tatarka.lens.autovalue.AutoValueLenses;

// AutoValue + with-plugin generates for us:
// Body implementations + equals, hashCode, toString.
// More extensions (e.g. for Builders) are available.

@AutoValue
abstract class Street {
    @AutoValueLenses
    interface Lenses {
        Lens<Street, String> name();
    }

    static Lenses lenses() {
        return AutoValue_Street.Lenses.instance;
    }

    static Street create(String name) {
        return new AutoValue_Street(name);
    }

    abstract String name();

    abstract Street withName(String name);
}

@AutoValue
abstract class Address {
    @AutoValueLenses
    interface Lenses {
        Lens<Address, Street> street();
    }

    static Lenses lenses() {
        return AutoValue_Address.Lenses.instance;
    }

    static Address create(Street street, int number) {
        return new AutoValue_Address(street, number);
    }

    abstract Street street();

    abstract Address withStreet(Street street);

    abstract int number();

    abstract Address withNumber(int number);
}

@AutoValue
abstract class Company {
    @AutoValueLenses
    interface Lenses {
        Lens<Company, Address> address();
    }

    static Lenses lenses() {
        return AutoValue_Company.Lenses.instance;
    }

    static Company create(String name, Address address) {
        return new AutoValue_Company(name, address);
    }

    abstract String name();

    abstract Company withName(String name);

    abstract Address address();

    abstract Company withAddress(Address address);
}

@AutoValue
abstract class Employee {
    @AutoValueLenses
    interface Lenses {
        Lens<Employee, Company> company();
    }

    static Lenses lenses() {
        return AutoValue_Employee.Lenses.instance;
    }

    static Employee create(String name, int age, Company company) {
        return new AutoValue_Employee(name, age, company);
    }

    abstract String name();

    abstract Employee withName(String name);

    abstract int age();

    abstract Employee withAge(int age);

    abstract Company company();

    abstract Employee withCompany(Company company);
}