Käyttäjä pystyy tehdä seuraavat painamalla eri nappeja, napit tekevät seuraavaa
  1. Lisätä taskin
  2. Näyttää vain TRUE-tilassa olevat taskit, toisen kerran painettaessa näyttää taas kaikki
  3. Järjestää listan päivämäärällä

Jokaisella taskilla on yksi oma nappi
 Kääntää taskin tilan TRUE --> FALSE tai toisinpäin


Miten Compose tilanhallinta toimii?
Compose tilanhallinta perustuu käyttöliittymämalliin, missä käyttöliittymä kertoo sovelluksen nykyistä tilaa. Compose seuraa tilaa ja silloin kun tilan arvo muuttuu, compose "uudelleenrakentaa" automaattisesti ne käyttöliittymän osat, jotka käyttävät kyseistä tilaa.

Miksi ViewModel on parempi kuin pelkkä remember?
ViewModel erottaa sovelluslogiikan käyttöliittymästä, koodi on selkeämpää ja helpommin testattavissa. ViewModel myös säilyttää tilan konfiguraation muutosten yli.
