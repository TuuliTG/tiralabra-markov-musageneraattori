# Käyttöohje

## Ohjelman ajaminen

Kloonaa ensin repositorio omalle koneellesi ja siirry kloonattuun repositorioon:
```
git clone https://github.com/TuuliTG/tiralabra-markov-musageneraattori.git
cd tiralabra-markov-musageneraattori/tiralabra-markov-musageneraattori
```
Voit käynnistää ohjelman komentoriviltä ohjelman juurikansiossa komennolla `./gradlew run. `
Aja yksikkötestit komennolla `./gradlew test`
Jos haluat vain rakentaa ohjelman, se onnistuu komennolla  `./gradlew build`

Muodosta Javadocit komennolla `./gradlew javadoc`

Tämän jälkeen tiedot löytyvät seuraavasti:
* Luokat: ./build/classes/java/
* Jar-tiedosto: ./build/libs/
* Javadoc: ./build/docs/javadoc/
* Checkstyle raportti: ./build/reports/checkstyle/
* JaCoCo-raportti ./build/reports/tests/test/index.html

## Ohjelman käyttäminen

Valikko ohjaa käyttäjää, joten en kirjoita tähän enempää ohjeistusta. Kansio, jonne generoitu kappale talletaan annetaan muodossa */kansio/kansio/kansio* eli loppuun ei enää /-merkkiä. 

## Lilypond

Ohjelman tuottaman .ly -tiedoston saa pdf-nuotiksi ja midi-äänitiedostoksi Lilypond-ohjelmalla. Sen voi ladata [täältä](https://lilypond.org/download.html)

**Kun Lilypond on asennettu koneellesi:**
Aja komentorivillä siinä hakemistossa, minne .ly-tiedosto on tallennettu, "lilypond tiedostonnimi.ly". Ohjelma tekee samaan hakemistoon pdf- ja midi-tiedostot. 

Chrome-selaimella ainakin [tällä](http://midiplayer.ehubsoft.net/) sivustolla voi soittaa midi-tiedoston.
