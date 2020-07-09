# REST – województwa

Na początku należy zmienić adres / użytkownika / hasło do bazy danych w application.properties ( ./src/main/resources/ ).

Package całej aplikacji jest dostępny w katalogu java ( ./src/main/java ) jako REST.demo. Pakiet podzielony jest z kolei na 4 kolejne:
    1. config – zawiera ustawienia Web Security oraz Swaggera,
    2. controllers – zawiera zdefiniowane end pointy API,
    3. domains – tutaj zdefiniowane są po kolei każda z klas oraz ich składowe,
    4. utils – tutaj natomiast są narzędzia (unikalny Mapper dla DTO klas, oraz logika paginacji).

Gdy web service zostanie uruchomiony, pozostaje tylko wpisać hasło i login użytkownika ze spring security – w/w ustawione są jako:

```bash
Login: admin
Hasło: admin
```

Po zalogowaniu mamy w zasadzie dwa kontrolery - „/cities” oraz „/provinces”

### Zapytania - przykłady

Projekt wystarczy tylko rozpakować, jest w zasadzie gotowy do użytku. Testy REST przeprowadzałem przez konsolę (przez program httpie).

```bash
http GET http://localhost:9090/cities/province/1 page=”0” size=”5”
```

Parametr ‘size’ ustala podział w podanej dla tego parametru liczbie, która z kolei oznacza ile elementów przypada na jedną stronę (zapytanie pod paginację). Parametr ‘page’ określa którą ze stron zgodnie z podziałem w ‘size’ chcemy pobrać. W tym przypadku zostanie zwróconych 5 pierwszych elementów z tabeli.

Jest to nietypowe zapytanie, reszta jest dokładnie udokumentowana w nakładce swaggera pod adresem

```bash
http://localhost:9090/swagger-ui.html
```

Nakładka pozwala testować API bez ograniczeń, przykład z httpie był przykładem zapytania ręcznego.
