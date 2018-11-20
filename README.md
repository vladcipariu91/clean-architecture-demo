# clean-architecture-demo

Here's a list of technologies used to build this project:
 - Kotlin as our language to code in
 - Lifecycle Architecture Components for our View Model classes
 - The Room Architecture Component for local data persistence
 - Retrofit for our network request
 - RxJava to handle the orchestration and execution of data operations
 - Dagger 2 for Dependency Injection
 - Design support library for our UI components
 - Espresso for our Android UI tests
 - Mockito for handling mock data in our tests

Each layer contains it’s own model representation, this helps us remove the dependencies on other modules
An inner layer will provide the interface with the operations that must be implemented and the implementation of that interface will be provided by an outside layer

The benefits of using clean architecture
- Clear separation of concerns between responsibilities
- Decoupled responsibilities reduces frictions between teams
- Easier to maintain, navigate and extend on implementations
- Code becomes more testable, tests follow the same practices
- Makes sense for larger more complex projects
- But can apply simplified version of concepts to smaller projects

![alt text](read_me_resources/clean_architecture_diagram.png)
