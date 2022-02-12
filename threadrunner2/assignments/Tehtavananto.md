
# ThreadRunner 2

## Hajautetut ohjelmistojärjestelmät 2021 harjoitustyö 1 tehtävänanto


Ensimmäisessä työssä harjoitellaan säikeiden luontia ja tehtävien jakamista niille. Lisäksi tutkitaan, miten tehtävien suoritusajat vaihtelevat erilaisilla tehtävätyypeillä. Harjoitus pohjautuu valmiiseen ohjelmarunkoon, johon ryhmän tehtävänä on täydentää puuttuvat osat. ThreadRunner2-ohjelma käyttää JavaFx-ympäristöä graafisen käyttöliittymänsä toteutukseen. Harjoitustyön tekemiseen ei kuitenkaan tarvita JavaFx-osaamista.

Työn pohja on Maven-projekti (https://maven.apache.org/). Kurssilla ei vaadita Maven-osaamista, mutta sen käyttö on pakollista. Palautettavan version on käännyttävä ja käynnistyttävä oikein komentoriviltä Mavenia käyttäen. 


## Ohjelman käyttöliittymä

![Main window](/assignments/images/UI_at_beginning_lowres.jpg "Käyttöliittymä ohjelman käynnistyessä.")

Ohjelman käyttöliittymä jakaantuu kahteen osaan. Ruudussa vasemmalla hallitaan säikeissä suoritettavia töitä ja oikealla itse säikeitä. 


![Main window](/assignments/images/UI_running_lowres.jpg "Käyttöliittymä säikeiden suorituksen aikana.") 

Vasemmanpuoleinen taulukko kertoo jonon tilan. Type- ja load-kentät ilmoittavat tehtävän tyypin ja Status sen tilan. Status voi olla waiting, queued, calculating tai done. Waiting-tilassa olevat tehtävät ovat työjonossa eikä niitä ole mikään säie ottanut itselleen. Queued-tilassa olevat työt ovat siirtyneet säikeelle. Calculating-tilassa oleva työ on sillä hetkellä laskennassa. Calculating-tilassa voi samanaikaisesti olla vain niin monta työtä kuin säikeitä on käytössä. Done-tilassa oleva työ on laskettu.

Työn tila etenee seuraavasti: waiting -> queued -> calculating -> done. Tilanäkymän muutos perustuu säikeestä tehtyyn raportointiin. Siihen löytyy valmiiksi luodut metodit harjoitustyöpohjasta.

Oikeanpuoleinen taulukko kertoo säikeiden tilan. Tätä taulukkoa ei voi tyhjentää. Jokainen execute-napin painallus lisää rivejä. Thread id on säieolion hash-arvo. Se on taulussa vain tunnistamista varten. Thread type kertoo säikeen tyypin. Status on created, running tai ended. Tilan raportointi käyttöliittymälle tapahtuu samoin kuin tehtävien kohdalla. 

Säikeen tila etenee seuraavasti: created -> running -> ended. 


### Valikot

#### Job queue

- Task type

   Tällä valinnalla asetetaan tehtävän tyyppi. Vaihtoehtoja on kaksi: DummyWork ja ForLoop. Molemmissa suoritettava työ on lyhyt viive.
   
- Load

   Asettaa käytettävän tehtävän keston. Vaihtoehtoja on neljä: Light, medium, hard ja mixed. Näistä viimeinen on sekoitus kolmesta ensimmäisestä.
   
- Number of job to generate

   Luotavien tehtävien määrä.
   
- Generate-nappi

   Luo työt asetusten mukaan. Luodut työ ilmestyvät job queue -listaan. Töitä voi generoida lisää, vaikka laskenta olisi käynnissä.
    
- Clear list -nappi

   Tyhjentää työjonon. Työjonon voi tyhjentää vaikka säikeet olisivat käynnissä. Jonotusratkaisu on säieturvallinen, mikäli sitä on käytetty oikein. 

#### Theads working
   
- Number of threads to run
   
   Asettaa käytettävien säikeiden määrän. Valikon maksimimäärä on automaattisesti suurin mahdollinen, jonka käytetty tietokone sallii. Huomaa, että virtuaalikoneessa prosessoriytimien määrä on oletuksena rajattu kahteen. Jos tietokoneen prosessorissa on enemmän ytimiä, voi arvoa nostaa virtuaalikoneen asetuksista.

- Block size 
   
   Jokainen säie hakee jonosta tämän arvon mukaisen määrän töitä kerrallaan. 

- Thread type

   Valitsee käynnistettävien säikeiden tyypin. Vaihtoehtoja valikossa on kolme: Single, Thread, Executor. Näistä vain Single toimii tehtäväpohjassa. Kaksi muuta vaihtoehtoa toteutetaan tässä harjoitustyössä.

- Execute-nappi

   Käynnistää suorituksen asetetuilla arvoilla. Uuden säie-erän voi käynnistää, vaikka vanhoja olisi vielä käynnissä, mutta kaikki käynnissä olevat säikeet jakavat saman työjonon.

--- 


## Ohjelman rakenteen kuvaus



### App.java ja fi.utu.tech.threadrunner2.ui pakkaus

`App.java` on ohjelman pääluokka, joka sisältää `main`-metodin. Se sisältää myös ajojen käynnistykseen ja suoritusajan mittaamiseen käytetyt metodit. Pakkaus `UI` sisältää graafiseen käyttöliittymän toimintaan liittyvät luokat.

Näitä luokkia ei harjoitustyössä saa muuttaa, eikä niitä käytetä työssä suoraan.


### fi.utu.tech.threadrunner2.mediator

Tämä pakkaus hoitaa yhteydenpidon ohjelman eri osien välillä. Se sisältää metodit tiedon saamiseksi käyttöliittymästä ja metodit tilojen raportointiin.

`ControlSet`-luokan oliot välittävät tiedon luotavien säikeiden määrästä ja niiden kerrallaan jonosta ottamasta tehtävämäärästä. `Mediator`-luokkaa puolestaan käytetään viestimään muutoksista käyttöliittymälle. Mediatorilla tehdyt muutokset ovat säieturvallisia, eli mediatoria voi käyttää useasta säikeestä ilman sen kummempia järjestelyjä.

Näitä luokkia ei harjoitustyössä saa muuttaa, mutta ControlSet-luokan ja Mediator-rajapinnan tarjoamia palveluita käytetään tehtävässä.


### fi.utu.tech.threadrunner2.works

Tehtävälistojen generointi. Pakkauksesta löytyy erityyppisiä tehtävälistoja generoivia luokkia, sekä säikeissä suoritettavat työt.

Näitä luokkia ei harjoitustyössä saa muuttaa, eikä niitä käytetä työssä suoraan.
 

### fi.utu.tech.threadrunner2.assignment

fi.utu.tech.threadrunner2.assignment pakkaus sisältää töiden säikeille jakamiseen liittyvät toiminteet.

Tämä pakkaus on harjoitustyön suorittamisen kannalta tärkein. Täältä löytyvät luokat, `Task1UsingThreadDistributor` ja `Task2UsingExecutorDistributor`, joissa tehtävissä 1 ja 2 vaaditut töiden jaot säikeille suoritetaan.

Lisäksi tähän pakkaukseen luodaan muut tarvittavat luokat, joita työ vaatii.


## Tehtävät

### Tehtävä 1. Thread-luokan käyttö

Ensimmäisessä tehtävässä toteutetaan töiden laskenta käyttäen `Thread`-luokkaa tai `Runnable`-rajapintaa. `Executor`-palvelun käyttö on kielletty.

Tehtävä ohjelmoidaan käyttäen `Task1UsingThreadDistributor`-luokkaa. Käyttöliittymä suorittaa tästä luokasta löytyvän `execute`-metodin, kun Execute-nappia painetaan ja Thead type -valikosta on valittuna *Thread*.

Tehtävässä käynnistetään säikeitä käyttöliittymässä valittu määrä ja jokainen käynnistetty säie noutaa tehtäviä yhteisestä tehtävälistasta niin kauan kuin niitä on jäljellä. Jos lista on tyhjä, säie päätetään.

Tehtäviä haetaan kerrallaan vain käyttöliittymässä määrätty määrä (block size). Jos jonossa on töitä vähemmän tarjolla, säie saa automaattisesti vain tämän määrän.

Tehtävät ovat `BlockingQueue<Work>`-tietorakenteessa. `Work`-oliot omaavat `work`-metodin, jota kutsumalla työ suoritetaan.



### Tehtävä 2. ExecutorService

Toisessa tehtävässä toteutetaan töiden laskenta käyttäen `Executor`-palvelua. Töiden suoritus ilman Executor-palvelua on kielletty.

Tehtävä ohjelmoidaan käyttäen `Task2UsingExecutorDistributor`-luokkaa. Käyttöliittymä suorittaa tästä luokasta löytyvän `execute`-metodin, kun Execute-nappia painetaan ja Thead type -valikosta on valittuna *Executor*.

Muilta osin tehtävä suoritetaan samoin kuin tehtävä 1.


### Tehtävä 3. Selvitystyö

Vastatkaa seuraaviin kysymyksiin lyhyesti perustellen. Apuna voi käyttää työssä toteutettua ohjelmaa ja sillä kokeilla, miten suoritusajat muuttuvat erilaisilla asetuksilla ja tehtävienjakotyypeillä. Kirjoittakaa vastaukset projektin juuressa olevaan `answers.md`-tiedostoon.

1.  Tutustukaa Threadrunner2-ohjelman rakenteeseen. Selostakaa lyhyesti, miten ohjelman suoritus kulkee. Käyttöliittymäluokkia ei tarvitse läpikäydä.
2. Teoriassa olisi mahdollista valita useampia säikeitä työskentelemään yhtäaikaisesti kuin mitä prosessorissa olisi ytimiä. Mitä tässä tapauksessa tapahtuisi? Miten tämä oletettavasti vaikuttaisi laskenta-aikoihin? (Olettaen, että työt olisivat CPU-intensiivisiä)
3. MediatorService sisältää workQueue-nimisen olion, joka puolestaan sisältää jonossa olevat työt. Käyttöliittymäsäikeestä käsin jonoon lisätään töitä samalla kun useat eri työskentelijäsäikeet niitä noutavat. Miksi workingQueue on tässä kohtaa toimiva ratkaisu? Miksi esimerkiksi ArrayList ei olisi?

