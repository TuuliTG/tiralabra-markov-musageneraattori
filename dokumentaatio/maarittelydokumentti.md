# Määrittelydokumentti
### Opinto-ohjelma: Tietojenkäsittelytieteen kandidaatti
### Kieli: Suomi
## Aihe:
Projektin tarkoituksena on luoda musiikkiohjelma, joka tuottaa opetusmateriaaliin pohjautuvaa uutta musiikkia. Projektissa käytetään Markovin ketjua eli ohjelma tutkii n edeltävää merkkiä ja päättää todennäköisyyksiin pohjautuen seuraavan merkin. Nuotinnukseen käytetään LilyPond-ohjelmaa ja -nuotinnusmenetelmää. LilyPondin avulla on mahdollista käsitellä nuotteja tekstimuodossa ja muuntaa nuotit nuottiviivastolle(pdf) tai ääneksi (midi). 
## Syöte
Musiikki on nuotinnettu ASCII-muotoisena tekstinä. Tekstitiedosto täytyy parsia ennen ohjelmaan syöttämistä erillisiksi merkeiksi. 
Tekstimuotoisessa nuotissa on määritelty mm. sävelkorkeus, nuotin pituus, mahdolliset tauot ja oktaavialat. Lisäksi monenlaisia kaaria ja musiikillisia merkkejä voi olla mukana. Näistä oleellisia tässä projektissa on sävelkorkeudet ja rytmit. Merkit muunnetaan ennen käsittelyä byte-muotoon. 

## Käytettävät tietorakenteet ja algoritmit
* Markovin ketju:
 * 1. asteen: ottaa huomioon vain yhden edeltävän merkin
 * 2. asteen: ottaa huomioon kaksi edeltävää merkkiä
 * 3. asteen: ottaa huomioon kolme edeltävää merkkiä
 * jne.
* Trie 
 * Mahdollistaa merkkien tallentamisen ja etsimisen tehokkaasti
 * Haun ja lisäyksen aikavaativuus on O(m), missä m on haettavan/lisättävän merkkijonon pituus. Trien huono puoli on suuri tilavaativuus, mutta tässä tapauksessa eri merkkijonoja on sen verran rajoitettu määrä (käytettäessä esim. byte-tyyppiä mahdolliset arvot ovat välillä -128 ... 127), että tällä ei liene väliä.
 * Lista
 	* Trien solmulle talletetaan seuraajat listaan

 ## Lähteet
 * (http://web.stanford.edu/class/archive/cs/cs166/cs166.1146/lectures/09/Small09.pdf)
 * https://www.geeksforgeeks.org/trie-insert-and-search/
 * https://en.wikipedia.org/wiki/Markov_chain
 * https://lilypond.org/doc/v2.20/Documentation/learning/index

