Käyttäjä pystyy tehdä seuraavat painamalla eri nappeja, napit tekevät seuraavaa
  1. Lisätä taskin
  2. Näyttää vain TRUE-tilassa olevat taskit, toisen kerran painettaessa näyttää taas kaikki
  3. Järjestää listan päivämäärällä

Jokaisella taskilla on yksi oma nappi
 Kääntää taskin tilan TRUE --> FALSE tai toisinpäin


## Miten Compose tilanhallinta toimii?
Compose tilanhallinta perustuu käyttöliittymämalliin, missä käyttöliittymä kertoo sovelluksen nykyistä tilaa. Compose seuraa tilaa ja silloin kun tilan arvo muuttuu, compose "uudelleenrakentaa" automaattisesti ne käyttöliittymän osat, jotka käyttävät kyseistä tilaa.

## Miksi ViewModel on parempi kuin pelkkä remember?
ViewModel erottaa sovelluslogiikan käyttöliittymästä, koodi on selkeämpää ja helpommin testattavissa. ViewModel myös säilyttää tilan konfiguraation muutosten yli.


## Mikä on MVVM ja miksi se on hyödyllinen Compose-sovellusksissa?
MVVM tulee sanoista View, Model ja ViewModel. MVVM:n hyödyt:
  **View**
  - Vain UI-renderöinti
  - Ei liiketoimintalogiikkaa
  - Helppo testata Preview:lla
  - Uudelleenkäytettävät komponentit

  **Model**
  - Eristetty datalogiikka
  - Vaihdettavat lähteet
  - Testattavissa mock-datalla
  - Offline-tuki helppo lisätä

  **ViewModel**
  - Säilyttää tilan rotationissa
  - Sisältää UI-logiikan
  - Helppo yksikkötestata
  - Ei Android-riippuvuuksia

  **Tiivistettynä:**
  - Selkeä vastuunjako: UI pysyy kevyenä, logiikka ViewModelissa
  - Luonteva state-hallinta: ViewModel tarjoaa tilan, Compose renderöi sen
  - Helppo testata: ViewModel ei riipu UI:sta
  - Hyvä elinkaarituki: tila säilyy konfiguraatiomuutoksissa
  - Skaalautuva rakenne: toimii hyvin myös isommissa Compose-sovelluksissa

## Miten stateFlow toimii?
StateFlow on graafinen ohjelmointiympäristö, joka toimii Simulinkin sisällä ja kuvaa järjestelmän käyttäytymistä tilakaavioiden, vuokaavioiden ja totuustaulukoiden avulla. Se ohjaa logiikkaa reagoimalla syötteisiin, tapahtumiin ja aikoihin, mahdollistaen monimutkaisten, ajasta riippuvien järjestelmien simuloinnin ja testauksen. 
