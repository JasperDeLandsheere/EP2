# Aufgabenblatt 1

## Allgemeine Anmerkungen

Ihre Lösung für dieses Aufgabenblatt ist bis Montag, 20.3.2023 11:00 Uhr durch `git commit` und
`push` abzugeben. Mit der Angabe werden folgende Dateien mitgeliefert, die sie gemäß der Angabe
verändern müssen: `PhotoApp1.java`, `SimpleRasterRGB.java`, `SimpleDataBufferInt.java` und zum
Testen `Aufgabe1Test.java`.
Die zusätzliche Datei `CodeDraw.jar` wird nur zum Zeichnen verwendet und sollte nicht entfernt oder
verändert werden.

Vorgegebene Programmteile dürfen nur an den Stellen verändert werden, die mit TODO markiert sind.
Zusätzliche Klassen, Interfaces, Methoden und Variablen dürfen aber eingefügt werden. Wenn Sie
zusätzlich zu den gefragten Klassen weitere Klassen definieren, achten Sie darauf, dass die
Klassennamen mit `My` beginnen, um Konflikte mit späteren Aufgabenblättern zu vermeiden.

## Verwendung in IntelliJ

Diese Aufgabenstellung ist ein vollständiges IntelliJ-Projekt, das Sie bereits in IntelliJ öffnen
können. Sie müssen daher kein eigenes Projekt anlegen. Öffnen Sie nach dem Klonen des Repos in
IntelliJ einfach den entsprechenden Ordner. Gegebenenfalls muss noch folgender Schritt ausgeführt
werden:

- Einstellen des _Project SDK_: Öffnen Sie dazu in IntelliJ die Projekteinstellungen (_File_ -> _
  Project Structure..._) und wählen Sie ein JDK unter _Project | Project SDK_ aus.

## Thema

Es soll ein Programm erstellt werden, mit dem einfache Operationen auf Farbbildern, wie das 
Zeichnen von Linien in einem Bild und das Filtern eines Bildes mittels
[Faltungsoperation](https://de.wikipedia.org/wiki/Faltungsmatrix), durchgeführt werden können. 

Das vorgegebene Programm ist im prozeduralen Programmierstil verfasst worden, das heisst es werden
keine Objekte benutzt, sondern nur statische Methoden. Die gegebene Implementierung hat 
dadurch einige Nachteile:

1. Alle Variablen sind `public`: Die Organisation in Klassen `SimpleRasterRGB` und  
   `SimpleDataBufferInt` bringt auf diese Weise wenig. Jeder Programmteil und jede Methode kann auf 
   alle diese Variablen zugreifen, wodurch es zu Abhängigkeiten zwischen verschiedenen  
   Programmteilen kommt. Beispielsweise greifen Methoden aus der Klasse `SimpleDataBufferInt` 
   auf Variablen der Klasse `SimpleRasterRGB` zu und umgekehrt.
2. Alle Variablen und Methoden sind `static`: Im Programm werden die Klassen nicht instanziert, 
   es werden nur statische Methoden aufgerufen, um die Operationen durchzuführen.
   Es wäre aber sinnvoll, das Bild, das gefiltert werden soll, und das Ergebnisbild der Filterung 
   durch zwei verschiedene Objekte der Klasse `SimpleRasterRGB` darzustellen. Jedes der Objekte hätte 
   dann eigene Variablen, die Objekte könnten getrennt voneinander bearbeitet werden. Das ist 
   mit der gegebenen Lösung aber nicht möglich, da die Variablen `static` sind. So braucht die 
   Methode `convolve`, da es nur ein Array gibt, einen eigenen Bereich im Array wo das 
   Ergebnis der Filterung zwischengespeichert wird.

## Ziel

Ziel der Aufgabe ist die Anwendung der Konzepte: Objekt- vs. Klassenmethode, Datensatz, Data Hiding,
Konstruktoren (siehe Skriptum Seiten 31-50).

Sie sollen das gegebene Programm so umschreiben, dass jedes Bild durch ein eigenes Objekt vom
Typ `SimpleRasterRGB` dargestellt wird. Jedes `SimpleRasterRGB`-Objekt enthält wiederum ein
eigenes `SimpleDataBufferInt` Objekt. Dadurch können mehrere Bilder gleichzeitig existieren und  
unabhängig bearbeitet werden.

Durch die Trennung von `SimpleRasterRGB` und `SimpleDataBufferInt` haben wir später mehr
Flexibilität, die Pixeldaten anders zu organisieren (z.B. Pixel-interleaved-Anordnung statt  
Komponentenanordnung). `SimpleRasterRGB` ermöglicht die 2D-Sicht auf die Daten,
`SimpleDataBufferInt` ermöglicht getrennt davon das Organisieren großer Datenmengen (wie sie bei 
Bildern auftreten).

## Aufgaben

1. Lesen Sie sich die Kommentare in den Dateien durch und führen Sie die Klasse `PhotoApp1` aus.
2. Data Hiding:
    1. Machen Sie in den Klassen `SimpleRasterRGB` und `SimpleDataBufferInt` alle
       statischen Variablen zu privaten Objektvariablen (Modifier `private` statt `public static`).
    2. Definieren Sie entsprechende Konstruktoren, um die Objektvariablen zu
       initialisieren. `PhotoApp1` soll nur noch diese nutzen und nicht mehr direkt auf die
       Objektvariablen zugreifen dürfen. 
3. Datenkapselung: Statt die gegebenen statischen Methoden aufzurufen, sollen
   Objekte von `SimpleRasterRGB` und `SimpleDataBufferInt` erzeugt werden und nur noch über 
   entsprechende Objektmethoden in den Klassen `SimpleRasterRGB` und `SimpleDataBufferInt` 
   angesprochen werden (mit Ausnahme der Methode 
   `public static SimpleRasterRGB convolve(SimpleRasterRGB toBeFiltered, double[][] filterKernel)`
   der Klasse `SimpleRasterRGB`, die weiterhin statisch bleiben soll). Implementieren Sie dazu die 
   spezifizierten Methoden und bauen Sie `PhotoApp1` so um, dass anstelle der Aufrufe statischer 
   Methoden Objektmethoden genutzt werden. Sie sollen alle in `SimpleRasterRGB` und `SimpleDataBufferInt` 
   spezifizierten Methoden implementieren, auch wenn nicht alle von `PhotoApp1` genutzt werden. 
   Verwenden Sie dazu die bereits implementierten statischen Methoden als Vorlage. Die 
   statischen Methoden in den Klassen `SimpleRasterRGB` und `SimpleDataBufferInt`können dann 
   entfernt werden.
4. Testen Sie die vervollständigten Klassen
   `SimpleRasterRGB` und `SimpleDataBufferInt` auch mit der Klasse `Aufgabe1Test`. Entfernen Sie 
   dazu die Kommentarzeichen in der Klassendefinition.

## Zusatzfragen

Beantworten Sie folgende Zusatzfragen als Kommentar in `PhotoApp1.java`:

1. Was versteht man unter _Datenkapselung_? Geben Sie ein Beispiel, wo dieses Konzept in dieser
   Aufgabenstellung angewendet wird.
2. Was versteht man unter _Data Hiding_? Geben Sie ein Beispiel, wo dieses Konzept in dieser
   Aufgabenstellung angewendet wird.
3. Was steht bei einem Methodenaufruf links vom `.` (z.B. bei `SimpleRasterRGB.getPixelColor()` oder
   `r1.getPixelColor()`)? Woran erkennt man dabei Objektmethoden?
4. Wofür steht `this`?
5. Erklären Sie den Unterschied zwischen den Methoden
   `public static SimpleRasterRGB convolve(SimpleRasterRGB toBeFiltered, double[][] filterKernel)`
   und `public SimpleRasterRGB convolve(double[][] filterKernel)`.
6. Kann innerhalb einer Objektmethode von `SimpleRasterRGB` auf die privaten Variablen eines 
   anderen `SimpleRasterRGB` Objekts zugegriffen werden (z.B. `this.width = r.width;`)?

### Denkanstöße (ohne Bewertung)

Folgende Fragen sind als Denkanstöße gedacht und bilden die Grundlage für eine Diskussion in der
Übungseinheit zu diesem Aufgabenblatt.

1. Welche anderen oder weiteren Objektmethoden hätten Sie zur Verfügung gestellt, wenn es keine
   Vorgaben gegeben hätte?
2. Wäre es sinnvoll, dass zwei Bilder (Objekte vom Typ `SimpleRasterRGB`) dasselbe 
   `SimpleDataBufferInt`-Objekt zum Speichern der Pixel verwenden?

#### _Punkteaufteilung_

- Korrekte Sichtbarkeit von Objektvariablen in `SimpleDataBufferInt` und `SimpleRasterRGB` und 
  Initialisierung mittels Konstruktoren: 1 Punkt
- Korrekte Objektmethoden in `SimpleDataBufferInt`: 1 Punkt
- Korrekte Methoden in `SimpleRasterRGB`: 2.5 Punkte
- Korrekte Verwendung der Objektmethoden in `PhotoApp1`: 1.5 Punkte
- Korrekte Beantwortung der Zusatzfragen: 1 Punkt
- Gesamt: 7 Punkte   
