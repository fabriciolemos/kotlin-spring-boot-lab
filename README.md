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
   * TODO: function return
1. Open you `pom.xml` file. There are a few things you can notice:
   * [`kotlin-maven-plugin`](https://kotlinlang.org/docs/reference/using-maven.html#compiling-kotlin-only-source-code): used to compile Kotlin code. `sourceDirectory` and `testSourceDirectory` are also configured
   * [`kotlin-maven-allopen`](https://kotlinlang.org/docs/reference/compiler-plugins.html#all-open-compiler-plugin): Kotlin has classes and their members `final` by default, which makes it inconvenient to use frameworks such as Spring that require classes to be open. The all-open plugin, which has [Spring support](https://kotlinlang.org/docs/reference/compiler-plugins.html#spring-support), makes classes annotated with a specific annotation and their members open without the explicit open keyword
   
# Create the first Rest Resource
1. Implement the class [`HelloWorldResource`](https://github.com/fabriciolemos/kotlin-spring-boot-lab/blob/4e0a14b7bf62e58f15473cda25fc2bd3e9f72e19/src/main/kotlin/com/example/blog/HelloWorldResource.kt)
1. Re-run the application with `./mvnw spring-boot:run`
1. Open your browser on [http://localhost:8080/hello](http://localhost:8080/hello). You should see "Hello" on the page displayed.
1. A few things to notice:
   * `hello()` is a [single-expression function](https://kotlinlang.org/docs/reference/functions.html#single-expression-functions), so the curly braces can be omitted
   * For single-expression functions explicitly declaring the return type is optional when this can be inferred by the compile

# Create the Article model and Resource
1. Implement the [`Article`](https://github.com/fabriciolemos/kotlin-spring-boot-lab/blob/397ba363438c729f6bd637aacd95edb57a05cc49/src/main/kotlin/com/example/blog/Article.kt) class with the properties `title` and `content`
1. Implement the [`ArticleResource`](https://github.com/fabriciolemos/kotlin-spring-boot-lab/blob/397ba363438c729f6bd637aacd95edb57a05cc49/src/main/kotlin/com/example/blog/ArticleResource.kt) Rest endpoint returning a list of Articles.
1. Re-run the application with `./mvnw spring-boot:run`
1. Open your browser on [http://localhost:8080/article](http://localhost:8080/article). You should see the JSON representation of the list returned
### Understanding the code:
* `Article`
   * Is a class whose main purpose is to hold data, making it a good candidate to be a [Data Class](https://kotlinlang.org/docs/reference/data-classes.html), which automatically provide `equals()`, `hashCode()`, `toString()`, `componentN()` and `copy()` functions 
   * It uses its [primary constructor](https://kotlinlang.org/docs/reference/classes.html#constructors) to declare and initialize its properties in a concise way. Properties declared in the primary constructor can be mutable (`var`) or read-only (`val`)
* `ArticleResource`
   * To create instances of the `Article` class, we call the constructor as if it were a regular function
   * Kotlin does not have a `new` keyword
   * `listOf`function is part of [Kotlin Standard Library](https://kotlinlang.org/api/latest/jvm/stdlib/index.html), which provides living essentials for everyday work with Kotlin
   * You didn't have to import `listOf`. Just like Java has `java.lang`, Kotlin has some packages that are [imported by default](https://kotlinlang.org/docs/reference/packages.html#default-imports)

# Add Article optional properties
TODO
