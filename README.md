# RickAndMortyApp

# Architecture
This application uses MVVM pattern, with Dagger Hilt Dependency Injection.
Using `DiffUtil` to calculate updates for a RecyclerView Adapter instead of using `notifyDataSetChanged`. Make it more efficient.

## Note
If you want to build and run this app.
You must create a file named `apikey.properties` under the Project level, and put the `API_URL` in that file in order for this code to compile and build.

For using the fake repository, switch `CharactersRepository(charactersApi)` with `FakeCharacterRepository()` on di/AppModule.kt

