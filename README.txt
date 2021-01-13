Cioltan Marian Alexandru 321CA
Proiect POO 2020
https://github.com/PixelRetroGames/EnergySystemSimulation

## Etapa 2
Pentru rezolvarea cerintelor din etapa a 2-a am continuat dezvoltarea solutiei de la etapa 1.
Am adaugat entitatea Producer, am creat un repository pentru aceasta si l-am instantiat in
Unit of Work. Am implementat functionalitatea ceruta prin adaugarea de noi actiuni. Pentru a
respecta ordinea de rulare a actiunilor din enunt (acum monthly update-ul are doua faze), am
modificat putin implementarea EnergySystemServer.

Am folosit Strategy pattern pentru metodele de sortare folosite in alegerea producatorilor de
catre distribuitori.

Intrucat in implementarea mea toate entitatile sunt raw-data, adica obiecte ce contin doar date,
fara functionalitate, nu se preteaza implementarea pattern-ului Observer in logica de baza a programului.

Nu se poate folosi Observer in actiuni, intrucat ele nu sunt persistente (deci nu pot salva stari),
ci sunt mai mult niste query-uri. Chiar daca Unit of Work este clasa ce stocheaza date, daca as fi
folosit Observer in ea, atunci as fi ajuns mai degraba la un Event Queue / Event Bus. Cum serverul
apartine celui mai inalt nivel de abstractizare, nu pot folosi Observer nici in el, pentru ca nu ar avea
sens sa salvez starea unor entitati ce apartin unui nivel de abstractizare inferior.

Deoarece am reusit sa rezolv toate cerintele fara a folosi acest design pattern, iar introducerea acestuia
ar duce la folosirea celorlalte design pattern-uri intr-un mod gresit, am ales sa-l folosesc in modulul
de debug.

Astfel, atunci cand modulul de debug isi schimba starea (este activat / dezactivat) toate mecanismele
de debug sunt notificate sa-si schimbe si ele starea.

Server-ul a fost modificat in asa fel incat sa nu mai trateze special Monthly Update-ul si sa permita
folosirea datelor din Monthly Update in interiorul tuturor actiunilor. Astfel poate trata cazuri in
care este nevoie de mai multe update-uri in mijlocul unor actiuni scriptate.

Fata de etapa 1, am mai adaugat o lista de actiuni, acum avand una pentru runda initiala si una pentru
actiunile lunare. Datorita acestor liste, putem fi siguri ca programul le va rula mereu in aceeasi ordine
si ca le va rula pe toate, iar in cazul in care apar bug-uri, se pot gasi usor, intrucat flow-ul programului
este predictibil. Prin folsirea pattern-ului Observer pentru notificarea schimbarilor in entitati as fi
distrus predictibilitatea programului, intrucat anumite functii ar fi putut fi trigger-uite ocazional, iar
eventualele bug-uri ar fi fost mai ascunse. De asemenea, intrucat eventualele modificari ale entitatilor
nu trebuie procesate instant, si se asteapta o anumita etapa, nu are rost sa sacrificam flow-ul programului
pentru a seta un flag care va fi verificat abia la runda urmatoare.

## Etapa 1
Pentru a rezolva problema propusa am refolosit partea de engine pe care am facut-o pentru tema 1 si
am implementat noile cerinte. (https://github.com/PixelRetroGames/Database)

Engine-ul foloseste MVC pattern pentru a decupla datele de functionalitate. Datele sunt stocate in
cate un repository in functie de tipul lor. Fiecare repository foloseste un resource manager care
stocheaza datele intr-o anumita structura de date.

Un model este raw-data, iar schimbarile asupra lui se fac prin actiuni. Aceasta abordare este echi-
valenta cu Entity Component System fara Component, unde modelul este fix o entitate, iar actiunea 
este exact un sistem. Astfel, se pot adauga usor functionalitati noi si este mai usor de respectat
SRP.

Actiunile sunt rulate de un Controller care obtine actiunile obiect folosind un Factory care le in-
stantiaza in functie de un string ce determina tipul de date.

Actiunile au un "link" la Unit of Work, care e contextul aplicatiei. Unit of Work detine toate repo-
sitory-urile. Pentru a crea o actiune noua, trebuie sa suprascriem functiile de start si execute, 
functiile fiind echivalente cu start si update din Unity. Start este folosita pentru a face "fetch"
la datele de care are nevoie entitatea si in cazul in care apar erori nu se va mai apela execute.

Serverul primeste un input, initializeaza Unit of Work cu valorile din input, iar apoi executa comen-
zile din input folosindu-se de Action Controller. Dupa ce executa toate comenzile, genereaza un output.

Mi-am facut si un sistem de debug: un logger Singleton ca sa il pot dezactiva si reactiva usor si un
utility class pentru a activa si dezactiva debugging-ul suplimentar. Acea variabila este folosita de
Server pentru a activa salvarea in format json a starii "jocului" la finalul rundei curente.

Pentru implementarea propriu-zisa am rulat si niste comenzi "artificiale" intr-un fel de gameloop.
Aceste comenzi apar sub forma unei liste de String :
		"distributor-set-price",
                "consumers-clean-contracts",
                "distributors-remove-finished-contracts",
                "consumers-choose-contract",
                "consumers-get-paid",
                "consumers-pay-distributors",
                "distributors-pay-action",
                "distributors-kick-bankrupt-consumers",
                "month-ended"
si se executa in ordinea din lista in fiecare runda.