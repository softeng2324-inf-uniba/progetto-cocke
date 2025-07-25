# Ataxx [![Docker Build & Push](https://github.com/softeng2324-inf-uniba/progetto-cocke/actions/workflows/docker_build&push.yml/badge.svg)](https://github.com/softeng2324-inf-uniba/progetto-cocke/actions/workflows/docker_build&push.yml)

La struttura della repository si presenta nel seguente modo:

```plaintext
|–– .github
|     |–– workflows
|     |     |–– ingsw2122.yml
|     |     |–– docker_build&push.yml
|     |     |–– gradle_build.yml
|–– build
|     |–– reports
|     |     |–– checkstyle
|     |     |–– spotbugs
|     |     |–– tests/test
|–– config
|     |–– checkstyle
|     |–– pmd
|–– docs
|     |–– img
|     |     |–– Report
|     |     |–– EmptyFieldTest.png
|     |–– Assegnazione progetto.md
|     |–– CODE_OF_CONDUCT.md
|     |–– Guida per lo studente.md
|     |–– img
|     |–– ISPIRATORE.md
|     |–– Report.md
|–– drawings
|–– gradle
|–– lib
|–– res
|–– src
|     |–– main
|     |–– test
|–– .gitattributes
|–– .gitignore
|–– Dockerfile
|–– README.md
|–– build.gradle
|–– gradlew
|–– gradle.bat
|–– settings.gradle
```

Nel seguito si dettagliano i ruoli dei diversi componenti:

- `.github/workflows/ingsw2122.yml`: dettaglia le direttive per assicurare la *continuous integration* attraverso l’uso di GitHub Actions;
- `build/`: ospita la sottocartella `reports/`, contenente gli output dei tool automatici di test e controllo di qualità;
- `config/`: ospita i file di configurazione. L’unica configurazione di base richiesta è quella per il tool checkstyle;
- `docs/`: ospita la documentazione di progetto, incluse le figure (nella sottocartella `img/`).
  Il file `Report.md` verrà usato per redigere la relazione finale del progetto.
  La cartella raccoglie inoltre:
  - La sottocartella `img`: contiene immagini di supporto alla documentazione, al progetto stesso e nella sua sottocartella `Report` quelle utili per la stesura del report; 
  - `Assegnazione progetto.md`: contenente la descrizione dettagliata del progetto assegnato;
  - `CODE_OF_CONDUCT.md`: contenente la descrizione del codice di condotta per il gruppo e le firme simboliche dei componenti;
  - `Guida per lo studente.md`: contenente la descrizione di tutti i passi di configurazione necessari per l'attivazione del flusso di lavoro a supporto dello sviluppo del progetto;
  - `ISPIRATORE.md`: contenente una breve biografia e i principali contributi di John Cocke, cui il nome del nostro gruppo si ispira;
- `gradle/`: ospita il `.jar` relativo al sistema di gestione delle dipendenze *Gradle*;
- `lib`: include eventuali librerie esterne utilizzate dal progetto;
- `res`: contiene risorse varie utilizzate dal sistema;
- `src`: cartella principale del progetto, in cui scrivere tutto il codice dell’applicazione. In `main/` ci saranno i file sorgente e `test/` conterrà i test di unità previsti;
- `drawings/`: contiene tutti i diagrammi UML usati per descrivere il progetto;
- `.gitignore`: specifica tutti i file che devono essere esclusi dal sistema di controllo versione;
- `build.gradle`: esplicita le direttive e la configurazione di *Gradle*;
- `gradlew` e `gradlew.bat`: eseguibili di *Gradle*, rispettivamente dedicati a Unix e Windows;
- `settings.gradle`: file di configurazione di *Gradle*.

In alcune cartelle è possibile notare la presenza di un unico file nascosto `.keep`: questo ha il solo scopo di richiedere a Git l’inclusione delle cartelle in cui è contenuto (Git esclude dal *versioning* le cartelle vuote). Pertanto, il file può essere ignorato o eventualmente cancellato nel momento in cui si inserisca almeno un altro file all’interno della cartella.
