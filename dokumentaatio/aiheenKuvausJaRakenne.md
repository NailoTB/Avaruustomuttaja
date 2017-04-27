# Projektin kuvaus

**Aihe:** Fysiikkasimulaattori avaruuden kappaleiden kiertoradoista ja interaktioista. 
Kappaleet liikkuvat avaruudessa vaikuttaen toisiinsa gravitaatiovuorovaikutuksella. Kappaleet voivat myös törmätä ja yhdistyä.

**Käyttäjät:** Tähtitieteen opiskelijat ja aiheesta kiinnostuneet.

**Kaikkien käyttäjien toiminnot:** 

* kappaleiden liikkeiden seuraaminen
* kappaleiden lisääminen
* simulaation pysäyttäminen


# Luokkakaavio

![Luokkakaavio](https://github.com/NailoTB/Avaruustomuttaja/blob/master/dokumentaatio/luokkakaavio.png)

Ohjelma koostuu Kappale-luokista, joihin sisältyy yksi Vektori, joka tallentaa Kappaleen nopeuden.
Luonnonlait-luokka laskee Kappaleiden vuorovaikutukset toisiinsa ja liikuttaa Kappaleita. Laskuapuri-luokkaa käytetään erinäisiin Luonnonlait-luokan sisäisiin laskutoimituksiin.

# Sekvenssikaaviot

![SimulaationKaynnistaminen](https://github.com/NailoTB/Avaruustomuttaja/blob/master/dokumentaatio/sekvenssi_simulaationKaynnistaminen.png)

![SimulaationPysayttaminen](https://github.com/NailoTB/Avaruustomuttaja/blob/master/dokumentaatio/sekvenssi_simulaationPysayttaminen.png)
