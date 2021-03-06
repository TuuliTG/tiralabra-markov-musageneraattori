# Toteutusdokumentti

## Ohjelman yleisrakenne

### Trie
Opetusmateriaali tallennetaan Triehen halutun Markovin asteen mukaan. Triestä voi hakea halutulle merkkijonoketjulle mahdolliset seuraajat (solmut).

### TrieSolmu

### Taulukkolista
Taulukkolistaa käytetään ohjelmassa mm. seuraajien tallentamiseen solmulle

### Pino
Pinoa käytetään, kun merkkijonosta poimitaan sävelnimet, oktaavialat ja rytmit.

### Generaattori
Generaattori-luokka vastaa opetusmateriaalin tallentamisesta triehen sekä uuden kappaleen generoimisesta opetusmateriaaliin pohjautuen.

### RytmiGeneraattori
Luo rytmin ensisijaisesti trien perusteella. Se ottaa kuitenkin kappaleessa käytettävän tahtilajin huomioon ja laittaa tarvittaessa tahdin loppuun jonkun muun sopivan rytmin, mikäli triestä ei löydy tahtiin sopivaa.

### SekvenssiApuri
Generaattori ja RytmiGeneraattori -luokat käyttävät Sekvenssiapuria melodian tai rytmin luomiseen. Solmulle tai solmuketjulle haetaan mahdolliset seuraajat, joista ohjelma arpoo todennäköisyyksiin perustuen sopivan solmun. 

### Tekstinkasittelija
Tekstinkäsittelijä-luokka vastaa tekstimuotoisen nuotin kääntämisestä trien ja generaattorin ymmärtämään byte-muotoon. Lisäksi tekstinkäsittelijä kääntää generoidun kappaleen byteistä taas tekstiksi. 

### RytmiArpoja 
RytmiArpoja on vaihtoehtoinen rytmingenerointitapa RytmiGeneraattorille. Tässä ei käytetä Trietä, vaan arvotaan valmiista 4/4 tahdeista sopiva määrä tahteja. 

### RytminMuuntaja
RytminMuuntajalla on kaksi tehtävää: Ensinnäkin se muuntaa byte-muotoisen rytmin doubleksi ja takaisinpäin. Double-muotoa tarvitaan siihen, kun RytmiGeneraattori laskee, kuinka paljon tahdissa on vielä tilaa. RytminMuuntaja muuntaa myös bytesta merkiksi, mitä tarvitaan lopuksi, kun generoitu kappale muunnetaan takaisin tekstimuotoon. 

### Kayttoliittyma
Käyttöliittymä-luokka luo käyttäjälle tekstimuotoisen valikon. Käyttäjä voi valita käytettävän opetusmateriaalin, Markovin asteen, generoidun kappaleen pituuden sekä kansion, jonne generoitu kappale talletetaan. Käyttäjä voi myös ajaa suorituskykytestit.
 
## Aika- ja tilavaativuudet 
* Triehen tallennus: aikavaativuus O(n * m), missä n on tallennettavien alkioiden määrä ja m on markovin aste. Tilavaativuus samoin O(n * m).
* Triestä hakeminen onnistuu ajassa O(n), missä n on hakuavaimen pituus. Hakuavaimen pituus voi olla enintään sen Markovin asteen pituinen, mitä on käytetty triehen tallentamisessa. Triestä hakeminen muutaman merkin pituisella hakuavaimella on siis erittäin nopeaa.  

## Saavutetut aika- ja tilavaativuudet
Triehen tallentaminen ja sieltä hakeminen saavutettiin tavoitteessa O(n * m). Musiikin generoimiseen tarvittavia aika- ja tilavaativuuksia ei tässä sen tarkemmin analysoida. 

## Generoidun musiikin laadusta
Bachissa rytmi pysyy koko ajan samanlaisena, joten generoitu musiikki kuulostaa jo 3. asteelta ylöspäin luontevalta ja siitä tunnistaa alkuperäisen säveltäjän. 5. astetta käytettäessä melodia alkaa olla jo huomattavalta osalta suoraa kopiota alkuperäisestä näytteestä. 

Lastenlaulussa rytmin voi generoida trien avulla tai arpomalla valmiista tahdeista. Trien perusteella tehtynä rytmistä tulee melko outo. Tahdit ovat kyllä teknisesti ottaen oikeanlaisia (oikea määrä iskuja / tahti), mutta ne ovat epäluonnollisia, matemaattisia. Arpomalla rytmi on luonnollisempi, mutta riippuu vähän arpaonnesta, kuinka onnistunut rytmistä tulee. Lastenlaulussa opetusmateriaalina on käytetty useaa eri laulua, joten 5. asteenkaan ketjulla generoituna musiikki ei kuulosta suoraan miltään tutulta. Tähän vaikuttaa tietysti myös uusi rytmi. Lastenlaulugeneraattoria voisin kuvitella käyttäväni jopa ihan käytännössä. Esimerkiksi [tämä](https://github.com/TuuliTG/tiralabra-markov-musageneraattori/tree/master/tiralabra-markov-musageneraattori/nuotitjamidit/laulu5asteArvottu.pdf) 5. asteen ketjulla ja arvotulla rytmillä generoitu kappale kelpaisi jo ihan lauluksi, jos siihen keksisi sanat. 

Ohjelma lisää automaattisesti sekä Bachista että Lastenlaulusta generoituun musiikkiin viimeiseksi säveleksi sävellajin perussävelen. Näin kappaleesta saadaan ehjän ja kokonaisen kuuloinen. 

## Työn puutteet ja parannusehdotukset
Tämä musiikkigeneraattori ei ole yleiskäyttöinen, sillä se pystyy hyödyntämään vain sitä opetusmateriaalia, mikä sille on nyt valmiiksi kirjoitettu. Jos ohjelmaa haluaisi laajentaa, voisi siitä tehdä erilaista materiaalia lukevan ja kirjoittavan. 
Lisäksi olisi ollut hyvä, jos tässä käytetty opetusmateriaali olisi ollut laajempi ja monipuolisempi. Nyt tuotettu musiikki jäi melko yksinkertaiseksi. 
Käytännössä laajentaminen olisi vaatinut moninkertaisen työn, sillä musiikkia on niin monenlaista ja erilaiset notaatiot ja musiikilliset ilmiöt olisi pitänyt ohjelmoida kaikki erikseen. Internetistä löytyy paljon .ly-muotoon nuotinnettua musiikkia, mutta niiden käyttäminen tässä projektissa opetusmateriaalina oli käytännössä mahdotonta. Tiedostot ovat eri kieliasuissa, ja nuoteissa on valtavasti sellaisia ominaisuuksia, joita tämä ohjelma ei ymmärrä. 

Kokeilin myös luoda SekvenssiApuriin ominaisuuden, jossa seuraajan valintaan liittyviä todennäköisyyksiä pehmennetään. Käytännössä käytössä olevalla opetusmateriaalilla pehmennyksellä ei ollut mitään merkitystä. Jakaumat sävelten välillä olivat niin tasaisia jo muutenkin, ettei pehmennys tullut käyttöön juuri koskaan. 

## Lähteet
 * http://web.stanford.edu/class/archive/cs/cs166/cs166.1146/lectures/09/Small09.pdf
 * https://www.geeksforgeeks.org/trie-insert-and-search/
 * https://en.wikipedia.org/wiki/Markov_chain
 * https://lilypond.org/doc/v2.20/Documentation/learning/index
 * http://midiplayer.ehubsoft.net/