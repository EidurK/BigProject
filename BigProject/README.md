# Big Project
This project expands the library system built in assignment 8. In this library system the user has the ability to sign
in as a faculty member or student. The faculty members can add books and omnibuses, while the students can lend books and
omnibuses as well as return them and extend the lendings.

---

## Installation

How to install (e.g. needed Java version) and run the project.

This is a Maven project. i.e. it uses the standard Maven project structure that your IDE hopefully understands when 
you `git clone` it. The provided Maven POM includes the algs4 & the JUnit4 dependency

### Jar

 - First navigate to the BigProject directory.
 - Next, in your terminal run the command `sh createjar.cmd` to generate a compressed jar file and place it in the current directory.
 - Finally, in your terminal the command `sh runjar.cmd` to run the jar file

---
## Building

Maven:

- `mvn compile` compiles all implementation classes.
- `mvn test` runs all test cases (i.e. all classes with a name that either starts with `Test` or ends with `Test`, `Tests`, or `TestCase`).
- `mvn exec` executes a main method.
- `mvn site` generates a website that provides detailed information about the project, including project summary, reports, dependencies, team information, and source code repository link.


---
## Usage

The user has the ability to sign in as a faculty member or student. As a faculty members he can add books and omnibuses
as well as view all lendings in the library system. As a student the user can lend books and omnibuses as well as 
return them and extend the lendings.

---
## Credits

To report an issues please send us an email at eik20@hi.is or jbb24@hi.is.

---
## Design

You can find information about the design [here](src/site/markdown/Design.md)


---
## License

You can find the licence [here](LICENSE).