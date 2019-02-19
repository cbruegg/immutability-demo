# Immutability in Java & Kotlin

This repository contains three examples for how to achieve immutability in a JVM project.
All approaches are based on the concept of [Lenses](https://arrow-kt.io/docs/optics/lens/#composition),
which in essence are simply objects storing a getter and a setter of a variable `bar` of a class `Foo`.
However, the setter doesn't modify the existing instance. Instead, it returns a copy that reuses all variables of `Foo` but `bar`.

The advantage of lenses is that they can be composed, which is very useful for deep copying.

## Option 1: Java + Manual entity definition

There is no need to use a library for lenses. The file `Lens.java` contains a sufficient implementation in less than 50 lines of code.
The package `manuallens` contains an example for how to use lenses without any library support. Inside `Entities.java`, we have definitions of our immutable entities with concrete implementations of the aforementioned getters and setters.

The file `Main.java` demonstrates the usage. It should already be obvious that lenses avoid boilerplate, since lenses can be reused and their use-site code is minimal.

## Option 2: Java + code generation

The majority of boilerplate in _Option 1_ stems from the implementation of entity classes. Maintaining the implementations of many getters, setters, `equals`, `hashCode`, etc. is time-consuming and error-prone.
It can be easily automated using code-generation. It's worth nothing that this step doesn't involve any dangerous bytecode rewriting. Instead, this approach relies on annotation processing: We add an annotation to a Java class and an annotation processor will generate more Java code based on the class contents that is then compiled alongside with the manually-written code. There is no magic involved.

As can be seen in the `Entities.java`, the boilerplate is now considerably smaller. This is achieved using [Google AutoValue](https://github.com/google/auto/tree/master/value) plus the [With Extension](https://github.com/gabrielittner/auto-value-with) that generates the immutable equivalent of setters for us. We get concrete implementations including `equals`, `hashCode` and `toString` for free.
The respective `Main.java` does not look very different from the previous version. This also demonstrates that if for some reason AutoValue should not be used anymore, it is easy to replace with manual implementations without breaking use-sites.

_Sidenote_: AutoValue extensions don't seem to require a lot of code ([example](https://github.com/gabrielittner/auto-value-with/tree/master/auto-value-with/src/main/java/com/gabrielittner/auto/value/with)). Due to this, it could even be feasible to write an extension that automatically generates lenses.

## Option 3: Kotlin + code generation

Even though _Option 2_ already reduces boilerplate, more can be done to make the code easier to _read_. The POJOs/data structures defined in `Entities.java` are quite simple, yet even with code-generation this requires almost 70 lines of code.
Using Kotlin, we can cut this in half. This makes data classes easier to write, but more importantly, reading many in a row becomes much easier since there are no manual getters and setters that don't add any meaningful information for the reader.

On top of this, we're using the [Arrow-Kt](https://arrow-kt.io/docs/optics/lens/#composition) library to generate the lenses for us using a similar code-generation approach as in _Option 2_. Thus, we don't need to manually specify any lenses anymore. The more lenses are used in an application, the more useful this advantage becomes.

In this option, we've simplified both the entity definitions in `Entity.Kt` and the usage in `Main.kt`.