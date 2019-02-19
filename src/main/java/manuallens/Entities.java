package manuallens;

class Street {
    private final String name;

    Street(String name) {
        this.name = name;
    }

    Street withName(String name) {
        return new Street(name);
    }

    String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Street { " +
            "name = '" + name + '\'' +
            " }";
    }
}

class Address {
    private final Street street;
    private final int number;

    Address(Street street, int number) {
        this.street = street;
        this.number = number;
    }

    Address withStreet(Street s) {
        return new Address(s, number);
    }

    Address withNumber(int n) {
        return new Address(street, n);
    }

    Street getStreet() {
        return street;
    }

    int getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return "Address { " +
            "street=" + street +
            ", number=" + number +
            " }";
    }
}

class Company {
    private final String name;
    private final Address address;

    Company(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    Company withName(String n) {
        return new Company(n, address);
    }

    Company withAddress(Address a) {
        return new Company(name, a);
    }

    String getName() {
        return name;
    }

    Address getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "Company { " +
            "name='" + name + '\'' +
            ", address=" + address +
            " }";
    }
}

class Employee {
    private final String name;
    private final int age;
    private final Company company;

    Employee(String name, int age, Company company) {
        this.name = name;
        this.age = age;
        this.company = company;
    }

    Employee withName(String n) {
        return new Employee(n, age, company);
    }

    Employee withAge(int a) {
        return new Employee(name, a, company);
    }

    Employee withCompany(Company c) {
        return new Employee(name, age, c);
    }

    String getName() {
        return name;
    }

    int getAge() {
        return age;
    }

    Company getCompany() {
        return company;
    }

    @Override
    public String toString() {
        return "Employee { " +
            "name='" + name + '\'' +
            ", age=" + age +
            ", company=" + company +
            " }";
    }
}