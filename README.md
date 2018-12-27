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

## LICENSE

    Copyright 2015 The RxAndroid authors

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
