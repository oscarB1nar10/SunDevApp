# SunDevApp

Is an App that list a set of comics and offer specific detail about selected comic.

# Most important used libraris
- Navigation component
- Dagger2
- Coroutines
- Material
- LiveData and ViewModel

# Design patterns
* Creational
- Builder
- Dependency Injection
- Singleton

* Structural
- Adapter

* Behavioral
- Command
- Observer
- Model View Controller
- Model View ViewModel
- Clean Architecture

# Architecture
* MVVM 
  - BaseApplication {AppComponent(AppModule, ViewModelFactoryModule, ActivityBuilderModule, UtilClassBuilderModule)
  - MainActivity (ComicBook, ComicBookDetail)
  - comicBook {ComicBook, ComicBookViewModel, ComicBookRepository}
  - ComicBookDetail {ComicBookDetail, ComicBookDetailViewModel, ComicBookDetailRepository}

