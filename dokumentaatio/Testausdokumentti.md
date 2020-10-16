# Testaus

## Yksikkötestit
Suurin osa luokista on testattu kattavasti yksikkötesteillä. Käyttöliittymään on luotu vain pieni testi, joka tarkistaa, että valikot toimivat oikein. Muuten käyttöliittymää on testattu vain manuaalisesti. RytminMuuntaja-luokkaan ei ole tehty erikseen testejä, sillä se on hyvin yksinkertainen eikä mielestäni kaivannut sen suurempaa testaamista. Yksikkötestien kattavuutta on seurattu JaCoCo-raporttien avulla. Raportti on nähtävillä [tässä](https://github.com/TuuliTG/tiralabra-markov-musageneraattori/blob/master/tiralabra-markov-musageneraattori/kuvatJaTestitulokset/JaCoCoRaportti.png)

## Suorituskykytestit
### Yleistä suorituskykytesteistä
Suorituskykyä on testattu 10, 100, 1000, 10 000, 100 000 ja 1000 000 -kokoisilla taulukoilla.  Generaattori-luokassa suurin taulukkokoko jätettiin pois ajan säästämisen vuoksi. Testit tehdään jokaisella taulukolla Trietesteissä 100 kertaa ja Generaattoritesteissä 50 kertaa, ja tuloksista talletetaan mediaani. Näin vähennetään roskien keräykseen ja JIT- kääntämiseen menevän ajan vaikutusta. Ajanottoon käytetään System.nanotimea. 

### Trie
  Trie-testi testaa triehen lisäämistä sekä triestä hakemista satunnaisilla alkioilla. Generaattori-testissä testataan aikaa, joka kuluu satunnaisesti luodun opetusmateriaalin lukemiseen Tekstinkäsittelijällä ja sen jälkeen musiikin generoimiseen. Testiin ei ole otettu mukaan tuotetun musiikin kääntämistä tekstitiedostoksi. 
Suorituskykytestit tehtiin asteilla 1-5.
Trie-testeissä tuli 5. asteen kohdalla (6:n pituisilla merkkijonoilla) tila vastaan ("Exception in thread "main" java.lang.OutOfMemoryError: Java heap space"), joten sillä asteella ei testattu suurinta taulukkoa. 

### Generaattori
**Rytmi Trien perusteella**
Testi testaa tilannetta, jossa opetusmateriaalin lukemisen yhteydessä kerätään myös rytmi, ja tehdään siitä oma trie. Uusi kappale luodaan generoimalla ensin rytmi trien perusteella ja sen jälkeen melodia melodiatrien perusteella. 
**Rytmi arpomalla valmiista tahdeista**
Tässä testissä rytmiä ei tallenneta triehen eikä kappaletta generoidessa tarvita kuin melodiatrie.

Edeltävät testit eivät ole täysin vertailukelpoiset, sillä ensimmäisessä tilanteessa molempiin Trieihin talletetaan n alkiota (10,100, 1000 jne.). Toisessa arvotaan ensin n/2 määrä tahtia, ja luodaan sitten melodia sille määrälle nuotteja, joita syntyy tahtien arpomisen perusteella. Tahdissa on keskimäärin 4,6 säveltä, joten todennäköisesti säveliä generoidaan noin kaksinkertainen määrä verrattuna edelliseen testiin. Molemmissa edeltävissä testeissä jokainen taulukkokoko testattiin 50 kertaa ja tuloksista talletettiin mediaani. Tällä kertaa testejä ei tehty 100 kertaa ajan säästämisen takia. 

## Checkstyle
* Raportti nähtävillä [täällä](https://github.com/TuuliTG/tiralabra-markov-musageneraattori/blob/master/tiralabra-markov-musageneraattori/kuvatJaTestitulokset/CheckstyleRaportti.png)
* Testiluokkien raportti [täällä](https://github.com/TuuliTG/tiralabra-markov-musageneraattori/blob/master/tiralabra-markov-musageneraattori/kuvatJaTestitulokset/CheckstyleTestit.png)

## Tulokset ja päätelmät
Taulukoidut tulokset on nähtävillä [tässä](https://github.com/TuuliTG/tiralabra-markov-musageneraattori/blob/master/tiralabra-markov-musageneraattori/kuvatJaTestitulokset/Suorituskykytestit.pdf)

Testit tehtiin Intel(R) Core(TM) i5-8250 CPU:lla, jossa on 15,6 GiB muistia. 

![lisäys ja haku](https://github.com/TuuliTG/tiralabra-markov-musageneraattori/blob/master/tiralabra-markov-musageneraattori/kuvatJaTestitulokset/lisaysjahaku.png)
Ajat ovat millisekunteina


### Trie
Trien suuri tilavaativuus tuli käytännössäkin esiin, kun suurimmalla taulukolla 5. asteen trien luominen, ei enää onnistunut tilanpuutteen takia. 
Suurella asteella on nähtävissä trien alustamisen hitaus, mikä olikin oletettavaa. Pienemmissä määrissä ja asteissa sekä triehen lisääminen, että siitä hakeminen toimivat nopeasti. 

### Generaattori
![generaattori](https://github.com/TuuliTG/tiralabra-markov-musageneraattori/blob/master/tiralabra-markov-musageneraattori/kuvatJaTestitulokset/generaattori.png)
Vaikka eri versiot generaattoritestistä eivät olekaan täysin vertailukelpoiset, on niistä selvästi nähtävissä, että rytmin arpominen ilman Trietä on nopeampaa, jos käytetään isoa Markovin ketjun astelukua. Suurilla syötteillä alle neljän asteluvulla rytmin generoiminen arpomalla näyttäisi olevan jopa hitaampaa. Tässä saattaa olla harhaanjohtavaa se, että syötteen todellinen koko melodiaa generoitaessa on todellisuudessa todennäköisesti suurempi. Testeistä voitaneen silti päätellä, että arpominen on pääsääntöisesti nopeampaa, etenkin isommalla asteella. 

## Testien toistettavuus
Suorituskykytestit voidaan toistaa helposti käyttöliittymän kautta.
