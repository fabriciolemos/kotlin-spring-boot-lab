# kotlin-spring-boot-lab

Welcome to the Spring Boot Kotlin lab. We're going to learn some of the Kotlin syntax and features while developing a Spring Boot app. Please follow the steps below:

# Install IntelliJ
Kotlin is a language developed by JetBrains, the company behind IntelliJ, so even though you can use any text editor, IntelliJ is recommended due to better integration with the language. Please, [download](https://www.jetbrains.com/idea/download/) and install the Ultimate or Community edition.

# Bootstrap your application
1. Go to [Spring Initializr](https://start.spring.io/)
1. Select Kotlin as a language
1. Use "blog" for Artifact
1. For dependencies, select: Web, JPA, and H2
1. Click on Generate Project link
1. Unpack the downloaded project
1. On command line run the application with `./mvnw spring-boot:run`

# Import the project on IntelliJ
1. File -> new -> Project from existing sources
1. Select the project folder
1. Chose "Import project from external model" -> Maven
1. Follow the next screens and Finish

# Look at the application bootstrapped
1. Open the file [`com.example.blog.BlogApplication`](https://github.com/fabriciolemos/kotlin-spring-boot-lab/blob/ae8f2121b0b346dbbda377fd2d60f31f758a3f3e/src/main/kotlin/com/example/blog/BlogApplication.kt). There are a few things you can notice:
   * Since the class has no body, the curly braces can be omitted
   * The main function is a top-level function, meaning there's no need to create a class to hold it, like languages such as Java, C# or Scala
   * The default visibility is `public`, which can be omitted for the main function
   * If you go to the declaration of `runApplication`, you'll see the that it's declared in a Kotlin extension class. It's part of the [Spring Boot Kotlin support](https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-kotlin.html), enabling writing more idiomatic code
   * TODO: return
1. Open you `pom.xml` file. There are a few things you can notice:
