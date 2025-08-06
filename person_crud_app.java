// File: src/main/java/com/example/demo/DemoApplication.java
package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}

// File: src/main/java/com/example/demo/entity/Person.java
package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String mobile;

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getMobile() { return mobile; }
    public void setMobile(String mobile) { this.mobile = mobile; }
}

// File: src/main/java/com/example/demo/repository/PersonRepository.java
package com.example.demo.repository;

import com.example.demo.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {}

// File: src/main/java/com/example/demo/service/PersonService.java
package com.example.demo.service;

import com.example.demo.entity.Person;
import com.example.demo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;

    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    public Optional<Person> getPersonById(Long id) {
        return personRepository.findById(id);
    }

    public Person savePerson(Person person) {
        return personRepository.save(person);
    }

    public void deletePerson(Long id) {
        personRepository.deleteById(id);
    }
}

// File: src/main/java/com/example/demo/controller/PersonController.java
package com.example.demo.controller;

import com.example.demo.entity.Person;
import com.example.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("persons", personService.getAllPersons());
        return "index";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("person", new Person());
        return "add";
    }

    @PostMapping("/save")
    public String savePerson(@ModelAttribute("person") Person person) {
        personService.savePerson(person);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("person", personService.getPersonById(id).orElse(new Person()));
        return "edit";
    }

    @PostMapping("/update")
    public String updatePerson(@ModelAttribute("person") Person person) {
        personService.savePerson(person);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deletePerson(@PathVariable("id") Long id) {
        personService.deletePerson(id);
        return "redirect:/";
    }
}

// File: src/main/resources/templates/index.html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head><title>Persons</title></head>
<body>
    <h1>Persons List</h1>
    <a href="/add">Add New Person</a>
    <table border="1">
        <tr><th>ID</th><th>Name</th><th>Email</th><th>Mobile</th><th>Actions</th></tr>
        <tr th:each="person : ${persons}">
            <td th:text="${person.id}"/>
            <td th:text="${person.name}"/>
            <td th:text="${person.email}"/>
            <td th:text="${person.mobile}"/>
            <td>
                <a th:href="@{/edit/{id}(id=${person.id})}">Edit</a>
                <a th:href="@{/delete/{id}(id=${person.id})}">Delete</a>
            </td>
        </tr>
    </table>
</body>
</html>

// File: src/main/resources/templates/add.html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head><title>Add Person</title></head>
<body>
    <h1>Add Person</h1>
    <form action="/save" method="post" th:object="${person}">
        Name: <input type="text" th:field="*{name}"/><br/>
        Email: <input type="text" th:field="*{email}"/><br/>
        Mobile: <input type="text" th:field="*{mobile}"/><br/>
        <button type="submit">Save</button>
    </form>
</body>
</html>

// File: src/main/resources/templates/edit.html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head><title>Edit Person</title></head>
<body>
    <h1>Edit Person</h1>
    <form action="/update" method="post" th:object="${person}">
        <input type="hidden" th:field="*{id}"/>
        Name: <input type="text" th:field="*{name}"/><br/>
        Email: <input type="text" th:field="*{email}"/><br/>
        Mobile: <input type="text" th:field="*{mobile}"/><br/>
        <button type="submit">Update</button>
    </form>
</body>
</html>

// File: src/main/resources/application.properties
spring.datasource.url=jdbc:oracle:thin:@localhost:1521:xe
spring.datasource.username=your_oracle_username
spring.datasource.password=your_oracle_password
spring.datasource.driver-class-name=oracle.jdbc.OracleDriver
spring.jpa.hibernate.ddl-auto=update
spring.jpa.database-platform=org.hibernate.dialect.Oracle10gDialect
spring.thymeleaf.cache=false
