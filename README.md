# Repozytorium pomocnicze dla prezentacji na temat Spocka

Wymagania:
* java >= 8
* zalecane korzystanie z IDE IntelliJ
* dostęp do internetu

Przed warsztatami:
* sklonować repozytorium i przebudować projekt, aby pobrać zależności
```
git checkout before-workshop
./mvnw install
```

Dostępne są trzy branche:
* `master` - główne informacje o projekcie
* `before-workshop` - kod zawierający przykłady i kod, do którego będą pisane testy podczas warsztatów. Na tym branchu build może zakończyć się błędem, aby omówić przykład na warsztatach.
* `after-workshop` - kod z dopisanymi testami na warsztatach. Na tym branchu build powinien zakończyć się sukcesem.

W pakietach `example` są przykłady do omówienia podczas prezentacji.
W pakietach `workshop` są funkcjonalności, do których będą pisane testy na warsztatach

Linki do oficjalnej dokumentacji:
* https://spockframework.org/spock/docs/2.3/index.html - Spock
* http://groovy-lang.org/documentation.html - Groovy

Przykłady testów aplikacji napisanej przy pomocy springboot'a
* https://github.com/spockframework/spock-example/tree/master/src/test/groovy
* https://allegro.tech/2018/04/Spring-WebMvcTest-with-Spock.html
* https://objectpartners.com/2018/06/14/spock-1-2-annotations-for-spring-integration-testing/

Inne:
* https://mrhaki.blogspot.com/search/label/Spocklight - ciekawy blog dot. Groovy i Spock
* https://speakerdeck.com/szpak/interesting-nooks-and-crannies-of-spock-you-may-have-never-seen-before - prezentacja Marcina Zajączkowskiego
