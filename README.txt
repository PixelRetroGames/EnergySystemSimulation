Cioltan Marian Alexandru 321CA
Proiect POO 2020 - etapa 1
https://github.com/PixelRetroGames/EnergySystemSimulation

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