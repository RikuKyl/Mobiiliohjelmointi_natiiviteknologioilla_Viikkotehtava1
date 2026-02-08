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

## Mitä tarkoittaa navigointi Jetpack Composessa
Navigation Compose on Jetpack Composen navigointikirjasto, joka mahdollistaa sujuvan liikkumisen näkymien välillä sovelluksessa.

## Mitä ovat NavHost ja NavController
  - NavHost on säiliö, joka näyttää nykyisen määränpään, eli määrittelee navigaatiokaavion.
  - NavController hallitsee navigointia NavHostissa. Se tarjoaa metodit kuten navigate() ja popBackStack().

## Miten sovelluksesi navigaatiorakenne on toteutettu (Home ↔ Calendar).
Navigaatio on toteutettu Jetpack Navigation Compose -kirjastolla:
  - NavHost määrittää reitit (home, calendar)
  - NavController hoitaa siirtymät näkymien välillä
  - HomeScreenistä siirrytään CalendarScreeniin navController.navigate(ROUTE_CALENDAR)
  - CalendarScreenistä palataan Homeen navController.popBackStack()
  - Molemmat ruudut jakavat saman TaskViewModel-instanssin.

## Miten MVVM ja navigointi yhdistyvät (yksi ViewModel kahdelle screenille).
MVVM ja navigointi yhdistyvät tässä siten, että yksi ViewModel jaetaan usean screenin kesken, kun taas navigointi vastaa vain näkymien vaihtamisesta.

## Miten ViewModelin tila jaetaan kummankin ruudun välillä.
  - TaskViewModel luodaan MainActivityssä kutsulla viewModel()
  - Sama ViewModel-instanssi välitetään parametrina sekä HomeScreen- että CalendarScreen-Composableille
  - Molemmat ruudut keräävät saman tilan (tasks, selectedTask) collectAsState()-kutsulla
  - Kun tila muuttuu yhdessä ruudussa, Compose recomposoi molemmat näkymät automaattisesti

## Miten CalendarScreen on toteutettu (miten tehtävät ryhmitellään / esitetään kalenterimaisesti).
  - Tehtävät haetaan ViewModelista
  - Ne ryhmitellään groupBy-funktiolla dueDate-kentän perusteella
  - Jokaiselle päivämäärälle näytetään otsikko (päivä)
  - Otsikon alle renderöidään saman päivän tehtävät LazyColumnissa
  - Yksittäinen tehtävä esitetään Card-komponenttina (CalendarTaskCard)

## Miten AlertDialog hoitaa addTask ja editTask.
AddTask:
   - Avataan, kun addTaskDialogVisible on true
   - Käyttäjä syöttää tehtävän tiedot
   - onUpdate -> viewModel.addTask(task)
   - Dialogin sulkeminen ei riipu näkymästä vaan ViewModelin tilast

EditTask:
  - Avataan, kun selectedTask ei ole null
  - Dialogi täytetään valitun tehtävän nykyisillä tiedoilla
  - onUpdate -> viewModel.updateTask(task)
  - onDelete -> viewModel.removeTask(id)
