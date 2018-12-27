# Ciconiabase Android Components

This project contains all main components used for building our apps. It mainly focuses on components build with 
[Epoxy](https://github.com/airbnb/epoxy), [RxJava2](https://github.com/ReactiveX/RxAndroid) but also on other useful material designed widgets.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Installation

Gradle is the only supported build configuration, so just add the dependency to your project build.gradle file:

```
dependencies {
    implementation 'com.github.ciconiasoftware:ciconiabase-android:x.y.z'
}
```

And to your buildscript
```
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}

```
## Authors

* [Mustafa Gercek](https://www.linkedin.com/in/mustafagercek/)

See also the list of [contributors](https://github.com/your/project/contributors) who participated in this project.

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details
