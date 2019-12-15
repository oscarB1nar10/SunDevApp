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
  
  # Pictures
  
![SunDevPicture1](https://user-images.githubusercontent.com/43419630/70868023-0dee8d80-1f4a-11ea-8df3-5734178d312c.jpg)
![SunDevPicture2](https://user-images.githubusercontent.com/43419630/70868024-0dee8d80-1f4a-11ea-8bca-f5df7aa22cc2.jpg)

