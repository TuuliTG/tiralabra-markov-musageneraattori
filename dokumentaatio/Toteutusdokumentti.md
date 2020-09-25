# Toteutusdokumentti

## Ohjelman yleisrakenne

Opetusmateriaali tallennetaan Triehen halutun Markovin asteen mukaan. Generaattori-luokka vastaa opetusmateriaalin tallentamisesta triehen sekä uuden kappaleen generoimisesta opetusmateriaaliin pohjautuen. Tekstinkäsittelijä-luokka vastaa tekstimuotoisen nuotin kääntämisestä trien ja generaattorin ymmärtämään byte-muotoon. Lisäksi tekstinkäsittelijä kääntää generoidun kappaleen byteistä taas tekstiksi. 
Käyttöliittymä-luokka luo käyttäjälle tekstimuotoisen valikon. Käyttäjä voi valita käytettävän opetusmateriaalin, Markovin asteen, generoidun kappaleen pituuden sekä kansion, jonne generoitu kappale talletetaan. Käyttäjä voi myös ajaa suorituskykytestit. 

## Aika- ja tilavaativuudet 
* Triehen tallennus: aikavaativuus O(n * m), missä n on tallennettavien alkioiden määrä ja m on markovin aste. Tilavaativuus samoin O(n * m).
* Triestä hakeminen onnistuu ajassa O(n), missä n on hakuavaimen pituus. Hakuavaimen pituus voi olla enintään sen Markovin asteen pituinen, mitä on käytetty triehen tallentamisessa. Triestä hakeminen muutaman merkin pituisella hakuavaimella on siis erittäin nopeaa.  