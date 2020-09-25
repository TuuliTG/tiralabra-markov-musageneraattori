# Toteutusdokumentti

## Ohjelman yleisrakenne
Opetusmateriaali tallennetaan Triehen halutun Markovin asteen mukaan. Generaattori-luokka käsittelee vastaa opetusmateriaalin tallentamisesta triehen sekä uuden kappaleen generoimisesta sen perusteella. Tekstinkäsittelijä-luokka vastaa tekstimuotoisen nuotin kääntämisestä trien ja generaattorin ymmärtämään byte-muotoon. Lisäksi tekstinkäsittelijä kääntää generoidun kappaleen byteistä taas tekstiksi. 
Käyttöliittymä-luokka luo käyttäjälle tekstimuotoisen valikon. Käyttäjä voi valita käytettävän opetusmateriaalin, Markovin asteen, generoidun kappaleen pituuden sekä kansion, jonne generoitu kappale talletetaan. Käyttäjä voi myös ajaa suorituskykytestit. 