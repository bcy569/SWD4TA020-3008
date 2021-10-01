# Backend

### Ops guide

Linux Debian 10
    
    cat /etc/os-release | grep VERSION_CODENAME | cut -d = -f 2

Java 8
PostgreSQL 11.12

http://localhost:8080/actuator/health/



### Dev guide (Linux Debian environment setup)

Java 8 + Spring Framework 
<br>
Guru Developers use Gradle to handle dependencies https://docs.gradle.org/current/userguide/what_is_gradle.html
<br>
Normal mortals let Maven to handle dependencies https://maven.apache.org/guides/getting-started/maven-in-five-minutes.html 

Maven: Project structure (object model, POM) and dependencies
<br>
https://start.spring.io
<br>
<br>
/backend-fullstack-skeleton-java-spring-maven/pom.xml
<br>

    <?xml version="1.0" encoding="UTF-8"?>
    <project xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns="http://maven.apache.org/POM/4.0.0"
            xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
        <modelVersion>4.0.0</modelVersion>
        <parent>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-parent</artifactId>
            <version>2.5.1</version>
            <relativePath/> <!-- lookup parent from repository -->
        </parent>
        <groupId>fi.dev.academy</groupId>
        <artifactId>vaccinationdatabase</artifactId>
        <version>0.0.1-SNAPSHOT</version>
        <name>vaccinationdatabase</name>
        <description>POC for THL</description>

        <properties>
            <java.version>1.8</java.version>
        </properties>

        <dependencies>
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-starter-data-jpa</artifactId>
            </dependency>
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-starter-web</artifactId>
            </dependency>
            <dependency>
                <groupId>org.postgresql</groupId>
                <artifactId>postgresql</artifactId>
                <scope>runtime</scope>
            </dependency>

            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-devtools</artifactId>
                <scope>runtime</scope>
                <optional>true</optional>
            </dependency>
            <dependency>
                <groupId>org.projectlombok</groupId>
                <artifactId>lombok</artifactId>
                <optional>true</optional>
            </dependency>
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-starter-test</artifactId>
                <scope>test</scope>
            </dependency>
            <dependency>
                <groupId>com.fasterxml.jackson.core</groupId>
                <artifactId>jackson-databind</artifactId>
                <version>2.12.3</version>
            </dependency>

            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-starter-actuator</artifactId>
            </dependency>

            <!-- https://mvnrepository.com/artifact/io.springfox/springfox-boot-starter -->
            <dependency>
                <groupId>io.springfox</groupId>
                <artifactId>springfox-boot-starter</artifactId>
                <version>3.0.0</version>
            </dependency>
            <dependency>
                <groupId>com.google.guava</groupId>
                <artifactId>guava</artifactId>
                <version>28.2-android</version>
            </dependency>
            <dependency>
                <groupId>org.apache.velocity</groupId>
                <artifactId>velocity</artifactId>
                <version>1.5</version>
            </dependency>

        </dependencies>

        <!-- https://springfox.github.io/springfox/docs/current/#maven -->
        <repositories>
            <repository>
                <id>jcenter-snapshots</id>
                <name>jcenter</name>
                <url>https://jcenter.bintray.com/</url>
            </repository>
        </repositories>

        <build>
            <plugins>
                <plugin>
                    <groupId>org.apache.maven.plugins</groupId>
                    <artifactId>maven-compiler-plugin</artifactId>
                    <version>3.7.0</version>
                    <configuration>
                        <source>1.8</source>
                        <target>1.8</target>
                        <encoding>UTF-8</encoding>
                        <excludes>
                            <exclude>
                                <groupId>org.projectlombok</groupId>
                                <artifactId>lombok</artifactId>
                            </exclude>
                        </excludes>
                    </configuration>
                </plugin>
            </plugins>
        </build>
    </project>


Java

    sudo apt update
    sudo apt install -y wget apt-transport-https gnupg
    wget https://adoptopenjdk.jfrog.io/adoptopenjdk/api/gpg/key/public
    gpg --no-default-keyring --keyring ./adoptopenjdk-keyring.gpg --import public
    gpg --no-default-keyring --keyring ./adoptopenjdk-keyring.gpg --export --output adoptopenjdk-archive-keyring.gpg
    rm adoptopenjdk-keyring.gpg
    sudo mv adoptopenjdk-archive-keyring.gpg /usr/share/keyrings 
    echo "deb [signed-by=/usr/share/keyrings/adoptopenjdk-archive-keyring.gpg] https://adoptopenjdk.jfrog.io/adoptopenjdk/deb buster main" | sudo tee /etc/apt/sources.list.d/adoptopenjdk.list
    sudo apt update
    sudo apt install adoptopenjdk-8-hotspot
    
    JAVA_HOME=/usr/lib/jvm/adoptopenjdk-8-hotspot-amd64
    PATH=$PATH:$JAVA_HOME/bin
    export JRE_HOME

IDE: IntelliJ from JetBrains

    wget https://download.jetbrains.com/idea/ideaIC-2021.1.2.tar.gz

    tar -xf /home/USERNAME/Downloads/ideaIC-2021.1.2.tar.gz -C /home/USERNAME/
    
    /home/USERNAME/intellij/idea-IC-211.7442.40/bin/idea.sh

Setup SDK in IntelliJ if needed (e.g. if your codebase with pom.xml is located in VirtualBox's shared folder at host machine like Windows)
PÖÖÖÖÖÖÖÖÖ Väärä Java versio???? Pitäisikö olla 8
    /usr/lib/jvm/java-1.11.0-openjdk-amd64

IDE shortcuts (IntelliJ Live templates)

https://youtu.be/ffBeoE6NBSs?t=260


Spring Boot Actuator

https://docs.spring.io/spring-boot/docs/2.5.0/reference/htmlsingle/#actuator


JSON

https://www.baeldung.com/jackson-object-mapper-tutorial

DataBase management system PostgreSQL

    sudo apt install -y postgresql
    sudo systemctl restart postgresql
    sudo -u postgres createdb USERNAME
    sudo -u postgres createuser USERNAME
    
    #Test PostgreSQL installation
    psql
    select (1+1);
    #Output 2
    
    exit


Note! Even though DataBase might use snake_convention (resposible_person) after Hibernate handling do use camelCase (resposiblePerson) with Java (Python uses snake_convention) because SpringData @Repository interface does not work with snake_convention (findByResponsible_person)

    DROP TABLE public.orders CASCADE;
    DROP TABLE public.vaccinations CASCADE;
    DROP SEQUENCE public.hibernate_sequence;



Browser live reload

https://docs.spring.io/spring-boot/docs/current/reference/html/using.html#using.devtools.livereload

IDE hot reload

https://stackoverflow.com/questions/43129647/intellij-idea-spring-boot-hot-reload-on-manual-save
