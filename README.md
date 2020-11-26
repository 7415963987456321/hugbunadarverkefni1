# HBV501G Hugbúnaðarverkefni 1
Software Development project at the University of Iceland.

## Team Members
| Name:  | Email  |
|---|---|
| Fannar Sævarsson | <fas19@hi.is> |
| Harpa Dögg Hafsteinsdóttir  | <hdh20@hi.is> |
| Hrafnkell Sigurðarson   | <hrs70@hi.is> |
| Skúli Þór Bjarnason | <sthb13@hi.is> |

## Requirements
### Java 14
```java --version~```
### Maven 3.6.3
(may run on earlier versions)

```mvn -v```
## Install and run
To run the project extract the zip file or alternatively

```git clone https://github.com/7415963987456321/hugbunadarverkefni1.git```

then enter the root directory of the project

```cd hugbunadarverkefni1-master```

and run the application by executing the following Maven command

```mvn clean spring-boot:run```

To change to mysql backend, you need to comment the data initialization in `DemoCommanlineRunner` component in the `TaeknilaesiApplication.java` class

```Java
    @Override
    @Transactional
    public void run(String... args) throws Exception {
        ////////////////////////////////////////////
        //below needs to be commented to run on mysql
        ////////////////////////////////////////////
```



and in `application.properties` two configuration blocks need to be switched, that is commented and uncommented.

Beware though, we have had errors running the application with `too many connections errrors`.
