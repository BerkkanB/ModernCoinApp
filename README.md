# Simple Coin App

#  Summary
The application consists of **four pages.** The first two pages show the **login** and **sign up** screens. The last two pages show **coin list** screen and **detail** screen.

## Techs used
 - Jetpack Compose
 - Compose Navigation
 - Dagger Hilt
 - Room
 - Coil
 - Retrofit
 - okhttp3
 - Coroutines - Flows

 
## Architecture used
 - Mvvm
 - Clean Architecture (with missing strategies, mentioned below)
 - Single Compose Activity

## What's missing ?
Since we had a very busy period at my current job, I could only spare a few hours for this project. While doing this, I installed the general structures, but some features were missing such as refresh handler and favorite queries.


## What could be better ?
Of course, this repo could not be prepared in the best way due to the highly limited time and lack of motivation. Here are some steps to make this repo more professional;
 - **App State** should be added in order to drive **navigation events** and **handle errors** with a **generic** way
 - Store api information and base url more appropriate way
 - **Usecases** could be added to **domain layer**
 - **Multimodule structure** could be used
 - Some data **mappers** and **result classes** could be implemented to **data layers** (also error handling)
 - Also data layer data classes should be converted to UI data classes for UI layer
 - With multimodule structure, Compose Navigation will be implemented better, in accordance with the **SOLID principles**
 - Unit tests and Portrait mode could be added
 - Queries from data layer to ui layer could be handled with **flows**.
 - Network data classes could be more simple, unnecessary fields implemented

## Note
The UI-UX never cared
