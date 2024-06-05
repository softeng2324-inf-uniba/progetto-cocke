# Report
## Indice

---
[1. Introduzione](#1-introduzione)

[2. Modello di dominio](#2-modello-di-dominio)

[3. Requisiti specifici](#3-requisiti-specifici)

- [3.1 Requisiti funzionali](#31-requisiti-funzionali)
- [3.2 Requisiti non funzionali](#32-requisiti-non-funzionali)

[7. Manuale utente](#7-manuale-utente)

[9. Analisi retrospettiva](#9-analisi-retrospettiva)

- [9.1 Sprint 0](#91-sprint-0)

---

# **1. Introduzione**

Il seguente progetto riguarda la realizzazione del gioco Ataxx a riga di comando utilizzando Java. 
Ataxx è un gioco da tavolo strategico per due giocatori, i quali si sfidano su una griglia di 7x7.

In Ataxx, il gioco si svolge in turni alternati. Durante ogni turno, un giocatore potrà scegliere di:
- **Clonare** una pedina esistente in una cella adiacente vuota (muovendosi di una casella in qualsiasi direzione).
- **Spostare** una pedina esistente in una cella vuota che si trova entro una distanza di due celle.

Lo scopo del gioco è conquistare il maggior numero di celle possibili trasformando le pedine avversarie in proprie.

Il gioco termina quando non è più possibile effettuare mosse valide, il vincitore è il giocatore che ha conquistato il maggior numero di celle.

---

# **2. Modello di dominio**

Questo rappresentato è il modello di dominio del gioco Ataxx:
![ModelloDiDominio](./img/Report/ModelloDiDominio.png)

---

# **3. Requisiti specifici**
## 3.1 Requisiti funzionali

---

Il programma permette all'utente di creare una nuova partita con il comando ***`/gioca`***.

Una volta creata la partita, è possibile mostrare le mosse disponibili per il giocatore di turno con il comando ***`/qualimosse`***.

Il giocatore di turno può effettuare una mossa specificando la casella in cui è situata la pedina di partenza, seguita dalla casella in cui si desidera spostare la pedina.
Il formato con cui indicare la mossa che si intende effettuare è:
**`Aa-Bb`**

>**`A`**: è il carattere indicante la colonna della pedina di partenza. ***`[a...g]`**(lowercase)*
**`a`**: è l'intero rappresentante la riga della pedina di partenza. ***`[1...7]`***
**`-`**: carattere separatore. ***`[-]`***
**`B`**: è il carattere indicante la colonna della casella di arrivo. ***`[a...g]`**(lowercase)*
**`b`**: è l'intero rappresentante la riga della casella di arrivo. ***`[1...7]`***

Con il comando ***`/tavoliere`*** è possibile visualizzare la posizione attuale di tutte le pedine presenti sul campo da gioco.

È possibile vedere lo storico delle mosse effettuate dall'inizio della partita con il comando ***`/mosse`***, ed è inoltre possibile visualizzare il tempo trascorso dall'inizio della partita con il comando ***`/tempo`***.

Prima dell'inizio di una nuova partita, è possibile bloccare fino ad un massimo di 9 caselle, a scelta tra quelle sulla colonna D o sulla riga 4.
Una volta bloccata, la casella non può più essere sbloccata.
Il comando con cui indicare la casella da bloccare è:
***`/blocca Xy`***

>***`X`***: è il carattere indicante la colonna della casella da bloccare. ***`[a...g]`**(lowercase)*
***`y`***: è l'intero rappresentante la riga della casella da bloccare. ***`[1...7]`***

Per visualizzare la plancia di gioco vuota, con le eventuali caselle bloccate, occorre usare il comando ***`/vuoto`***.

Per abbandonare una partita in corso, è possibile utilizzare il comando ***`/abbandona`***. In tal caso il giocatore avversario sarà decretato vincitore.

Per mostrare una schermata di aiuto, è possibile invocare l'applicazione con la flag **`--help`** o **`-h`**, oppure con il comando ***`/help`***.

Con il comando ***`/esci`*** è possibile terminare l'esecuzione del programma.

---

## 3.2 Requisiti non funzionali

---

Il programma è scritto utilizzando il linguaggio di programmazione Java, linguaggio indipendente dalla piattaforma hardware di esecuzione; ciò rende il programma multipiattaforma.

La sicurezza del programma è garantita con l'analisi statica del codice per mezzo del programma SpotBugs.

Sono stati effettuati test sul codice del programma, in modo da rendere il programma affidabile, scongiurando la presenza di errori di logica.

L'applicazione è distribuita come Container Docker.

---

# **7. Manuale utente**

---

Per poter avviare l'applicazione è necessario utilizzare docker,
il container docker dell’app deve essere eseguito da terminali che supportano Unicode con encoding UTF-8 o UTF-16.

**Comando per l’esecuzione del container**

Dopo aver eseguito il comando docker pull copiandolo da GitHub Packages, il comando Docker da usare per eseguire il container contenente l’applicazione è:
> docker run --rm -it ghcr.io/softeng2324-inf-uniba/ataxx-cocke:latest


Ataxx è un gioco da tavolo strategico per due giocatori, l'obiettivo è conquistare il maggior numero di celle possibili trasformando le pedine avversarie in proprie.

_Ecco i comandi disponibili per il giocatore:_
> - **/gioca** : Crea una nuova partita se non ci sono partite in corso.
> - **/vuoto** : Mostra il tavoliere vuoto.
> - **/tavoliere** : Mostra la posizione delle pedine sul tavoliere.
> - **/qualimosse** : Mostra le mosse disponibili al giocatore.
> - **/abbandona** : Chiude la partita in gioco e viene dichiarata vittoria all' avversario.
> - **/esci** : Esci dal gioco.


---

# **9. Analisi retrospettiva**

---

## 9.1 Sprint 0

---
![Retrospettiva0](./img/Report/Retrospettiva0.jpg)