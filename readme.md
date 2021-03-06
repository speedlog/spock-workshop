# Repozytorium pomocnicze dla prezentacji na temat Spocka

Wymagania:
* java >= 8
* zalecane korzystanie z IDE IntelliJ

Przed warsztatami:
* sklonować repozytorium i uruchomić
```
git checkout before-workshop
./mvnw clean install
```
Część z przykładów zakończy się błędem - jest to celowe działanie na potrzeby prezentacji.
* zalecane zainstalowanie pluginów do IntelliJ
  * https://plugins.jetbrains.com/plugin/7442-gmavenplus-intellij-plugin
  * https://plugins.jetbrains.com/plugin/7114-spock-framework-enhancements

Dostępne są dwa branche:
* before-workshop - kod zawierający przykłady i kod, do którego będą pisane testy podczas warsztatów
* after-workshop  - kod dopisanymi testami na warsztatach

W pakietach "example" są przykłady do omówienia podczas prezentacji.
W pakietach "workshop" są funkcjonalności, do których będą pisane testy na warsztatach

Linki do oficjalnej dokumentacji:
* http://spockframework.org/spock/docs/1.2/index.html - Spock
* http://groovy-lang.org/documentation.html - Groovy

Przykłady testów aplikacji napisanej przy pomocy springboot'a
* https://github.com/spockframework/spock/tree/master/spock-spring/boot-test/src/test/groovy/org/spockframework/boot
* https://allegro.tech/2018/04/Spring-WebMvcTest-with-Spock.html
* https://objectpartners.com/2018/06/14/spock-1-2-annotations-for-spring-integration-testing/

Inne:
* https://mrhaki.blogspot.com/search/label/Spocklight - ciekawy blog dot. Groovy i Spock
* https://speakerdeck.com/szpak/interesting-nooks-and-crannies-of-spock-you-may-have-never-seen-before - prezentacja Marcina Zajączkowskiego
