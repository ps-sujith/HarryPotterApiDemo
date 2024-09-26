# HARRY POTTER API DEMO


"A sample app to showcase the Harry Potter API, built in Kotlin using Jetpack Compose and architecture components, following Clean Code principles and modularization."




## PROJECT SPECIFICATION

### TECH STACK
* Kotlin
* Jetpack Compose for the UI
* Hilt for DI
* Coroutines and Flow - for making asynchronous calls
* Retrofit - for networking
* mockk - for unit testing
* Coil - Image Loader library.

### API
* https://hp-api.onrender.com/
  
### FEATURES
* HomeScree - Lists all the harry potter characterdetails with image and name
* Character -  Details Scren: Detail screen that displays more information about a character, such as their name, role, house, dob, etc.
* Search Option -Search functionality that allows the user to search for a specific character by character’s name
  
### ARCHITECTURE & DESIGN PATTERN
* SOLID PRINCIPLE - The app follows SOLID design principles to ensure scalability and maintainability.
* CLEAN CODE ARCHITECTURE -The app's architecture is designed to be clean, separating concerns into distinct layers (e.g., UI, Domain, Data).
* MODULAR - The app codebase is modularized by layers (UI, Data, Domain), with the Domain module being independent of the other modules.
* DESIGN PATTERN - Application is developed using Test-Driven Development (TDD) and follows the Model-View-ViewModel (MVVM) design pattern








