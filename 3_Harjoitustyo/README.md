This proof of concept ([POC](https://en.wikipedia.org/wiki/Proof_of_concept)) for FullStack skeleton is based on this [<i>Business Case</i>](https://web.archive.org/web/20210803084701/https://github.com/solita/vaccine-exercise-2021) exercise

## Ops and Dev: ReadMe
Both BackEnd and FrontEnd directories have their own ReadMe-files which serve both Ops and Dev

### Enviroment
Development enviroment is VirtualBox. To scale virtual screen

    sudo apt update
    sudo apt upgrade -y
    sudo apt install -y linux-headers-$(uname -r)
    #VirtualBox ribbon: Devices --> Insert Guest Additions CD image...
    #In Linux terminal: On VirtualBox Guest additions folder ("media/USERNAME/VBox_GAs_VERSION_NUMBER")
    sudo bash VBoxLinuxAdditions.run
    #Logout: Restart

# Technology Stack

### Object oriented programming
Create Java [SpringBoot](https://start.spring.io/ "Spring.io") BackEnd objects and allow Maven to handle dependencies. Those '@Entity'-objects represents RelationalDataBase tables (and vice versa) for [SQL](https://en.wikipedia.org/wiki/SQL)
* Allow SpringData annotation '@Autowired' to automate spiral-spring-connection into '@Repository'-configuration (interface I_[foobar](https://en.wikipedia.org/wiki/Foobar "FI.wikipedia.org/wiki/Foobar")_[DAO](https://www.tutorialspoint.com/design_pattern/data_access_object_pattern.htm) extends JpaRepository) for [Hibernate](http://hibernate.org/orm/) to do all the heavy lifting for setting it up the DataBase (based on BackEnd objects).
* Allow [Spring Fox Boot Starter](https://springfox.github.io/springfox/docs/current/#maven) (for [Swagger Core](https://github.com/swagger-api/swagger-core)) to represent all the entity-object variable types in machine readable JSON format
* Allow Spring Fox Boot Starter (for [Swagger UI](https://swagger.io/tools/swagger-ui/)) also to do all the documentation for us humans how to interact with BE API's via '@RestController' & '@GetMapping' ( --> [@Service](https://www.baeldung.com/spring-component-repository-service) --> for '@Repository' DataBase tables)
* Based on Spring Fox Boot Starter results (Swagger Core) let [OpenAPI Generator](https://openapi-generator.tech/docs/generators/typescript-axios) to do all the entity-object variable type specifications for FrontEnd (Single Page Application: React with TypeScript & [Material UI](https://material-ui.com/components/material-icons/) with [Sass](https://sass-lang.com/guide) + Redux + Thunk + Axios)

BackEnd Swagger for human FrontEnd developers
<br>
http://localhost:8080/swagger-ui/
<br>
* machine readable version for Swagger Code Generator
<br>
http://localhost:8080/v2/api-docs?group=backbone-api

BackEnd HealthCheck (Ops: <b>Note</b>, endpoint e.g. for Docker)
<br>
http://localhost:8080/actuator/health/


Docker + Kubernetes

Linode / Digital Ocean / AWS / Azure / Google Cloud / Oracle Cloud