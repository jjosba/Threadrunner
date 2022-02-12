Vastaa tehtävän 3 kysymyksiin tässä tiedostossa. Vastaa suomeksi.
Use this file to answer to the questions in exercise 3

# Question 1: Program structure
Käyttöliittymän Job queue -puolella valitaan tehtävien tyyppi (DummyWork tai ForLoop), raskaus (Light, Medium, Heavy, Mixed) sekä töiden määrä.

Generate jobs -painike luo säikeille töitä valitun tyypin ja määrän mukaan käyttäen fi.utu.tech.threadrunner2.works-paketin luokkia.

Threads working -puolella valitaan säikeiden määrä, samaan aikaan suoritettavaksi siirtyvien töiden määrä sekä säikeen tyyppi (Single, Thread, Executor). fi.utu.tech.threadrunner2.mediator-paketin luokat luovat listan suoritettavista töistä sekä jakaa listan kerrallaan suoritettaviin.

fi.utu.tech.threadrunner2.assignment-paketissa luodaan säikeet ja jaetaan työt tehtäväksi säikeille. Painamalla Execute-painiketta valitun säietyypin suoritus käynnistyy. Eri säietyyppien toteutukset eroavat ensinnäkin luotujen säikeiden määrässä: ExampleSingleThreadDistributor-luokka luo vain yhden säikeen, kun taas Task1UsingThreadDistributor- sekä Task2UsingExecutorDistributor-luokka luo valitun määrän säikeitä. Toisaalta ohjelmien suoritus eroaa siinä, mikä säikeet luo: Task1UsingThreadDistributor-luokassa useamman säikeen luonti tapahtuu for loopissa ja Task2UsingExecutorDistributor-luokassa säikeiden luonti hoituu Executor-kirjaston kautta luodulla Executorilla. Executor luo valikoiman (pool) säikeitä ja delegoi niille valitun määrän tehtäviä.


# Question 2: More threads than cores 
Yksi ydin voi suorittaa kerrallaan vain yhden säikeen. Jos säikeitä olisi enemmän kuin ytimiä, pitää säikeiden suorittaminen aikatauluttaa. Toisin sanoen säikeiden suoritusta pyritään jakamaan tasaisesti, ja kun jokin ydin vapautuu, sille annetaan seuraava säie ajettavaksi. Säikeiden prioriteetit vaikuttavat myös suoritusjärjestykseen.

Laskenta-aika loogisesti pitenee, kun säikeet joutuvat odottamaan suoritustaan. Myös kaikkien muiden prosessien suorittaminen hidastuu.


# Question 3: workQueue
LinkedBlockingQueue implementoi BlockingQueue-rajapintaa. Koska BlockingQueue on säieturvallinen, myös sen implementaatiot, kuten LinkedBlockingQueue, ovat säieturvallisia. LinkedBlockingQueuen metodit käyttävät lukkoja, mikä mahdollistaa säikeiden samanaikaisen toiminnan aiheuttamatta vahinkoa. Metodi estää (blocks) säikeen toiminnan, kunnes muokattava olio vapautuu. Toisin sanoen, ennen kuin säie palauttaa muokatun objektin LinkedBlockingQueue-jonoon, toinen säie ei pääse siihen käsiksi.

Toisaalta taas esimerkiksi ArrayList ei ole säieturvallinen. Jos useammat säikeet muokkaavat yhtä listaa samaan aikaan, voi tapahtua tietojen törmäyksiä ja päällekkäisiä muutoksia, jolloin listaan saatetaan esimerkiksi lisätä väärä määrä töitä.
