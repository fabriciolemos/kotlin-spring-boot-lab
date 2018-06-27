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
   * Lack of semicolons
   * Since the class has no body, the curly braces can be omitted
   * The main function is a top-level function, meaning there's no need to create a class to hold it, like languages such as Java, C# or Scala
   * The default visibility is `public`, which can be omitted for the main function
   * If you navigate to the declaration of `runApplication`, you'll see the that it's declared in a Kotlin extension class. It's part of the [Spring Boot Kotlin support](https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-kotlin.html), enabling writing more idiomatic code
1. Open you `pom.xml` file. There are a few things you can notice:
   * [`kotlin-maven-plugin`](https://kotlinlang.org/docs/reference/using-maven.html#compiling-kotlin-only-source-code): used to compile Kotlin code. `sourceDirectory` and `testSourceDirectory` are also configured
   * [`kotlin-maven-allopen`](https://kotlinlang.org/docs/reference/compiler-plugins.html#all-open-compiler-plugin): Kotlin has classes and their members `final` by default, which makes it inconvenient to use frameworks such as Spring that require classes to be open. The all-open plugin, which has [Spring support](https://kotlinlang.org/docs/reference/compiler-plugins.html#spring-support), makes classes annotated with a specific annotation and their members open without the explicit open keyword
   
# Create the first Rest Resource
1. Implement the class [`HelloWorldResource`](https://github.com/fabriciolemos/kotlin-spring-boot-lab/blob/4e0a14b7bf62e58f15473cda25fc2bd3e9f72e19/src/main/kotlin/com/example/blog/HelloWorldResource.kt)
1. Re-run the application with `./mvnw spring-boot:run`
1. Open your browser on [http://localhost:8080/hello](http://localhost:8080/hello). You should see "Hello" on the page displayed.
### Understanding the code
   * `hello()` is a [single-expression function](https://kotlinlang.org/docs/reference/functions.html#single-expression-functions), so the curly braces can be omitted
   * For single-expression functions explicitly declaring the return type is optional when this can be inferred by the compile

# Create the Article model and Resource
1. Implement the [`Article`](https://github.com/fabriciolemos/kotlin-spring-boot-lab/blob/397ba363438c729f6bd637aacd95edb57a05cc49/src/main/kotlin/com/example/blog/Article.kt) class with the properties `title` and `content`
1. Implement the [`ArticleResource`](https://github.com/fabriciolemos/kotlin-spring-boot-lab/blob/397ba363438c729f6bd637aacd95edb57a05cc49/src/main/kotlin/com/example/blog/ArticleResource.kt) Rest endpoint returning a list of Articles.
1. Re-run the application with `./mvnw spring-boot:run`
1. Open your browser on [http://localhost:8080/article](http://localhost:8080/article). You should see the JSON representation of the list returned
### Understanding the code
* `Article`
   * Is a class whose main purpose is to hold data, making it a good candidate to be a [Data Class](https://kotlinlang.org/docs/reference/data-classes.html), which automatically provide `equals()`, `hashCode()`, `toString()`, `componentN()` and `copy()` functions 
   * It uses its [primary constructor](https://kotlinlang.org/docs/reference/classes.html#constructors) to declare and initialize its properties in a concise way. Properties declared in the primary constructor can be mutable (`var`) or read-only (`val`)
* `ArticleResource`
   * To create instances of the `Article` class, we call the constructor as if it were a regular function
   * Kotlin does not have a `new` keyword
   * `listOf`function is part of [Kotlin Standard Library](https://kotlinlang.org/api/latest/jvm/stdlib/index.html), which provides living essentials for everyday work with Kotlin
   * You didn't have to import `listOf`. Just like Java has `java.lang`, Kotlin has some packages that are [imported by default](https://kotlinlang.org/docs/reference/packages.html#default-imports)

# Add Article properties with default values
1. Add the properties `createdAt` of type `LocalDateTime` and `id` of type `Long?` with default values
1. Re-run the application with `./mvnw spring-boot:run`
1. Open your browser on [http://localhost:8080/article](http://localhost:8080/article). You should see the new properties on the objects in the list
### Understanding the code
* Function parameters can have [default values](https://kotlinlang.org/docs/reference/functions.html#default-arguments), which are used when a corresponding argument is omitted. 
* Kotlin's [type system](https://kotlinlang.org/docs/reference/null-safety.html) is aimed at eliminating the danger of null references from code. Since the `Article.id` is only specified when it's persisted (to be implemented in the next section), its supposed to allow `null`, so the nullable `Long?` is used
* Since we are satisfied with the default values of the parameter, we do not need to change the `Article` instantiation on `Articleget.getArticles()`

# Test with JUnit 5
While JUnit 4 is still the default testing framework provided with Spring Boot, and can be used with Kotlin, JUnit 5 provides various features very handy with Kotlin, including autowiring of constructor / method parameters which allows to use non-nullable `val` properties and the possibility to use `@BeforeAll`/`@AfterAll` on regular non-static methods. Follow the steps below:
1. Exclude `junit` maven transitive dependency from `spring-boot-starter-test`
1. Add `junit-jupiter-engine` Maven dependency
1. Add `junit-platform-surefire-provider` Maven dependency to `maven-surefire-plugin`
1. Refactor `BlogApplicationTests.kt` to Junit 5
1. Run `./mvnw clean install` and check if the build is still successful
To make it easier, see the necessary changes in this [diff](https://github.com/fabriciolemos/kotlin-spring-boot-lab/commit/8885f82ad43934d22d9573ebb99df4b7a078fe45) or the expected final file content: [`pom.xml`](https://github.com/fabriciolemos/kotlin-spring-boot-lab/blob/8885f82ad43934d22d9573ebb99df4b7a078fe45/pom.xml) and [`BlogApplicationTests.kt`](https://github.com/fabriciolemos/kotlin-spring-boot-lab/blob/8885f82ad43934d22d9573ebb99df4b7a078fe45/src/test/kotlin/com/example/blog/BlogApplicationTests.kt)

# Add test to `ArticleResource`
1. Implement the [`ArticleResourceTest`](https://github.com/fabriciolemos/kotlin-spring-boot-lab/blob/2a35ad0685fdddf4014e14dd15280124c45f77de/src/test/kotlin/com/example/blog/ArticleResourceTest.kt). Make sure to put it in the `src/test/kotlin/com/example/blog/` folder. This short test is enough for the purpose of this lab, but I hope in real life you test more than the result size.
1. Run `./mvnw clean install` and check if the test runs and the build is still successful
### Understanding the code
* We're using [`SpringExtension`](https://docs.spring.io/spring/docs/current/spring-framework-reference/testing.html#testcontext-junit-jupiter-di) which is able to inject dependencies into test constructors. This makes it a good fit with Kotlin immutable and non-nullable properties
* We use real sentences in test function names. Backticks are of part of the language to help with the [Java interop](https://kotlinlang.org/docs/reference/java-interop.html#escaping-for-java-identifiers-that-are-keywords-in-kotlin) but they also help writing more expressive test function names, instead of the usual of camel-case naming
* [`getForEntity`](https://docs.spring.io/spring-framework/docs/5.0.7.RELEASE/kdoc-api/spring-framework/org.springframework.web.client/get-for-entity.html#) is another [Spring Kotlin extension](https://docs.spring.io/spring/docs/5.0.7.RELEASE/spring-framework-reference/languages.html#kotlin-extensions). It takes advantage of Kotlin [reified type parameters](https://kotlinlang.org/docs/reference/inline-functions.html#reified-type-parameters), overcoming Java [type erasure](https://docs.oracle.com/javase/tutorial/java/generics/erasure.html) limitation which would require the usage of `ParameterizedTypeReference<List<Article>>`

# Persistence with JPA
1. Annotate `Article` with `@Entity` and `Article.id` with `@Id @GeneratedValue`. Final result: [`Article`](https://github.com/fabriciolemos/kotlin-spring-boot-lab/blob/9520f15705ad607c949c7e2391b0257811914166/src/main/kotlin/com/example/blog/Article.kt)
1. Implement [`ArticleRepository`](https://github.com/fabriciolemos/kotlin-spring-boot-lab/blob/9520f15705ad607c949c7e2391b0257811914166/src/main/kotlin/com/example/blog/ArticleRepository.kt)
1. Use the `ArticleRepository` to retrieve the list of Articles on [`ArticleResource`](https://github.com/fabriciolemos/kotlin-spring-boot-lab/blob/master/src/main/kotlin/com/example/blog/ArticleResource.kt)
1. Add the Kotlin JPA plugin and add `kotlin-maven-noarg` dependency to the [`pom.xml](https://github.com/fabriciolemos/kotlin-spring-boot-lab/commit/1a3f0dbae6888f1250f367a1b97a325cb9dd934a)
1. Re-run the application with `./mvnw spring-boot:run`
1. Open your browser on [http://localhost:8080/article](http://localhost:8080/article). The result is not very exciting and you won't see anything since the database is empty. We'll solve this on next section
### Understanding the code
* JPA annotations can be used in the primary constructors, contributing for a concise code
* Primary constructors will also have its dependencies automatically autowired, which is the case of `ArticleResource`
* JPA requires the entities to have a zero-argument constructor. Since we added the primary constructor to `Article`, the entity is no longer satisfying that requirement. Kotlin [no-arg plugin](https://kotlinlang.org/docs/reference/compiler-plugins.html#no-arg-compiler-plugin) with JPA [support](https://kotlinlang.org/docs/reference/compiler-plugins.html#jpa-support) adds a zero-argument constructor for classes annotated with `@Entity`, `@Embeddable` and `@MappedSuperclass`

# Add some Articles to the database
1. Implement the function [`BlogApplication.initDatabase()`](https://github.com/fabriciolemos/kotlin-spring-boot-lab/blob/master/src/main/kotlin/com/example/blog/BlogApplication.kt#L12)
1. Re-run the application with `./mvnw spring-boot:run`
1. Open your browser on [http://localhost:8080/article](http://localhost:8080/article). Now you should see some Articles on the page
### Understanding the code
* Now that `BlogApplication` has a body, it needs curly braces
* The function `initDatabase` produces a `@Bean` of type [`CommandLineRunner`](https://docs.spring.io/spring-boot/docs/current/api/org/springframework/boot/CommandLineRunner.html) which implements a callback to run specific pieces of code when an application is fully started
* `CommandLineRunner` is a [`FunctionalInterface`](https://docs.oracle.com/javase/8/docs/api/java/lang/FunctionalInterface.html), meaning it's a Single Abstract Method ([SAM](http://cr.openjdk.java.net/~briangoetz/lambda/lambda-state-3.html)) interface, which can be [automatically converted](https://kotlinlang.org/docs/reference/java-interop.html#sam-conversions) from Kotlin [function literals](https://kotlinlang.org/docs/reference/lambdas.html#lambda-expressions-and-anonymous-functions). If it got too complicated, the bottom line is the Kotlin support for functional programming allows us to implement the method the way we did, instead of having to [explicitly](https://docs.spring.io/spring-boot/docs/current-SNAPSHOT/reference/htmlsingle/#boot-features-command-line-runner) declare a class to instantiate a single method

