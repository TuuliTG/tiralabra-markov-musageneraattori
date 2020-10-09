# Testaus

## Yksikkötestit
Tietorakenteet, generaattori sekä tekstinkäsittelijä-luokka on testattu yksikkötesteillä. Käyttöliittymään on luotu vain pieni testi, joka tarkistaa, että valikot toimivat oikein. Muuten käyttöliittymää on testattu vain manuaalisesti. Yksikkötestien kattavuutta on seurattu JaCoCo-raporttien avulla. Raportti on nähtävillä [tässä](https://github.com/TuuliTG/tiralabra-markov-musageneraattori/blob/master/tiralabra-markov-musageneraattori/kuvatJaTestitulokset/JaCoCo%20raportti.png)

## Suorituskykytestit
Suorituskykyä on testattu 10, 100, 1000, 10 000, 100 000 ja 1000 000 -kokoisilla taulukoilla. Suorituskykytesteissä testataan erikseen Trien ja generaattorin toimintaa. Trie-testi testaa triehen lisäämistä sekä triestä hakemista satunnaisilla alkioilla. Generaattori-testissä testataan aikaa, joka kuluu satunnaisesti luodun opetusmateriaalin lukemiseen ja siitä musiikin generoimiseen. Siihen ei ole otettu mukaan tuotetun musiikin kääntämistä tekstitiedostoksi. 
Tulokset löytyvät [tästä](https://github.com/TuuliTG/tiralabra-markov-musageneraattori/blob/master/tiralabra-markov-musageneraattori/kuvatJaTestitulokset/Suorituskykytestien%20tuloksia.png)

## Checkstyle
* Tämänhetkinen raportti nähtävillä [täällä](https://github.com/TuuliTG/tiralabra-markov-musageneraattori/blob/master/tiralabra-markov-musageneraattori/kuvatJaTestitulokset/CheckStyleRaportti2509.png)

## Tulokset ja päätelmät