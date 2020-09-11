# Viikkoraportti 2

## Mitä olen tehnyt tällä viikolla?
* Koodausta, koodausta, koodausta
* Projektin rajaaminen ja jatkosuunnittelu
* Testien tekeminen
* Tietorakenteiden viilaus

## Miten ohjelma on edistynyt?
Alkukankeuden jälkeen tuntuu, että olen päässyt vauhtiin ja projekti selkiytyy koko ajan mielessäni. Olen saanut ohjelman ydinasiat eli trien ja generaattorin jo toimimaan. Voin nyt antaa ohjelmalle syötteenä yksinkertaisen LilyPond-nuotinnukseen käytettävän tekstin (tiedostonlukijaa ei ole vielä ohjelmoitu), ja ohjelma generoi sen perusteella uuden "kappaleen". Kokeilin myös rytmin generoimista samalla tavalla, mutta se osoittautui hankalaksi monien musiikillisten "sääntöjen" takia. Trie, TrieSolmu ja Generaattori -luokkiin on luotu jo melko kattavat yksikkötestit. Jacocolla saan näistä luokista jo 95-100 %. Tämä ei luonnollisesti kerro vielä todellisesta kattavuudesta paljoakaan ja työtä vielä riittää. Sain oman listarakenteen aloitettua ja melko valmiiksikin. 

## Mitä opin tällä viikolla?
Kokeilin ensimmäistä kertaa Jacocoa testikattavuuden seuraamiseen. Sen avulla helppo ainakin näennäisesti seurata sitä, että koodista tulee olennaiset osat testattua. JavaDoc-dokumentointi on minulle uutta ja ryhdyin jo dokumentoimaan ensimmäisiä luokkia. Trien ohjelmoiminen ja Markovin ketjun luominen trien avulla on ollut opettavaista. 

## Mikä jäi epäselväksi tai tuottanut vaikeuksia?
Se, minkälaista opetusmateriaalia tai syötettä ohjelmalle voi antaa, on mietityttänyt paljon. LilyPond-ohjelmalla voi muuntaa nuoteiksi hyvinkin monimutkaisia musiikillisia rakenteita. Tässä projektissa todella monimutkaisen tekstitiedoston käsitteleminen ohjelmallisesti paisuisi kuitenkin liian suureksi työksi. Luultavasti tässä työssä täytyy rajata musiikilliset ominaisuudet hyvin alkeellisiksi ja käyttää vain siihen sopivaa, mahdollisesti itse kirjoitettua opetusmateriaalia. 

## Mitä teen seuraavaksi?
Käyttöliittymän suunnittelu ja aloitus. Listan viimeistely: Virhetilanteet yms.. Ohjelman ominaisuuksien laajentaminen. Testien parantelu ja laajentaminen.