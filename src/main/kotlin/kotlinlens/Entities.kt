package kotlinlens

import arrow.optics.optics

// data classes (like Scala's case classes) include niceties
// such as equals, hashCode, toString, destructuring, copying

// @optics belongs to the arrow-kt library and generates lenses.
// It adds them as extensions to each class'es "companion object" = "a singleton associated with the class"

@optics
data class Street(val name: String) {
    companion object
}

@optics
data class Address(val street: Street, val number: Int) {
    companion object
}

@optics
data class Company(val name: String, val address: Address) {
    companion object
}

@optics
data class Employee(val name: String, val age: Int, val company: Company) {
    companion object
}