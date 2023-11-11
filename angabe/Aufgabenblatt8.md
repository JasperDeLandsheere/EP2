# Aufgabenblatt 8

## Allgemeine Anmerkungen

Ihre Lösung für dieses Aufgabenblatt ist bis Montag, 12.6., 11h durch `git commit` und `push` 
abzugeben. Mit der Angabe werden folgende Dateien mitgeliefert: `SafeFactory.java`, 
`SafeCreateFactory.java`, `SafeLineFactory.java`, `SafeNewLayerFactory.java`, 
`SafeLoadSequenceFactory.java`, `SafeOperation.java`, `SafeOperationSingle.java`, 
`SafeCreateOperation.java`, `SafeLineOperation.java`, `SafeNewLayerOperation.java`, 
`SafeOperationSequence.java`, `SafeDoNothing.java`, `FactoryException.java`, 
`OperationException.java`, `SafeOperationIterable.java`, `SafeOperationIterator.java`, `PhotoApp8.
java`, `Aufgabe8Test.java` und 
das Verzeichnis `data`, 
das 
vier Dateien mit Endung `.txt` enthält. 
Die Klassen dürfen nur an den Stellen verändert werden, die mit TODO markiert sind. 
Zusätzliche Klassen, Interfaces, Methoden und Variablen dürfen aber eingefügt werden. 

## Ziel

Ziel der Aufgabe ist einerseits, die Implementierung von Iteratoren zu üben (in diesem
Aufgabenblatt gibt es zwei weitere Varianten). Andererseits geht es um die Anwendung der Konzepte: 
Kopie vs. Sichtweise, Java-Collections, Eingabe mit Validierung, Exceptions (S. 105-136).

## Beschreibung der gegebenen Klassen und Dateien
- [SafeFactory](../src/SafeFactory.java) ist das vollständig vorgegebene,
  gemeinsame Interface für Klassen, die Objekte repräsentieren, die zur Erzeugung und
  Initialisierung von anderen Objekten genutzt werden. Klassen, die dieses Interface 
  implementieren, sind: 
    - [SafeCreateFactory](../src/SafeCreateFactory.java),
    - [SafeNewLayerFactory](../src/SafeNewLayerFactory.java),
    - [SafeLineFactory](../src/SafeLineFactory.java),
    - [SafeLoadSequenceFactory](../src/SafeLoadSequenceFactory.java). 
- Das vollständig vorgegebene Interface [SafeOperationSingle](../src/SafeOperationSingle.java) 
  erweitert das ebenfalls vollständig vorgegebene Interface [SafeOperation](../src/SafeOperation.java). 
  Klassen, die [SafeOperationSingle](../src/SafeOperationSingle.java) implementieren, sind:
    - [SafeCreateOperation](../src/SafeCreateOperation.java),
    - [SafeNewLayerOperation](../src/SafeNewLayerOperation.java),
    - [SafeLineOperation](../src/SafeLineOperation.java).
  
  Die Klassen
    - [SafeOperationSequence](../src/SafeOperationSequence.java)
    - [SafeDoNothing](../src/SafeDoNothing.java)
  
  sind vom allgemeineren Typ [SafeOperation](../src/SafeOperation.java). Die Klasse
  [SafeDoNothing](../src/SafeDoNothing.java) ist bereits vollständig vorgegeben. Von dieser 
  Klasse gibt es nur ein einziges Objekt, das anstelle von `null` verwendet werden soll. Die 
  `execute`-Methode dieses Objekts lässt den angegebenen Raster unverändert.
- [FactoryException](../src/FactoryException.java) ist das Gerüst für eine Klasse, die
  Ausnahmen repräsentiert, die beim Ablauf der `create` Methode eines 
  [SafeFactory](../src/SafeFactory.java)-Objekts auftreten können.
- [OperationException](../src/OperationException.java) ist das Gerüst für eine Klasse, die
  Ausnahmen repräsentiert, die beim Ablauf der `execute` Methode eines
  [SafeOperation](../src/SafeOperation.java)-Objekts auftreten können.
- [SafeOperationIterable](../src/SafeOperationIterable.java) und 
  [SafeOperationIterator](../src/SafeOperationIterator.java) sind vorgegebene Interfaces für 
  die Implementierung eines Iterators.
- [PhotoApp8](../src/PhotoApp8.java) ist die ausführbare Klasse, die Sie ebenfalls modifizieren 
  sollen.
- [Aufgabe8Test](../src/Aufgabe8Test.java) ist eine vorgegebene Klasse, die Sie zum Testen Ihrer 
  Implementierung verwenden sollten. Bei einer fehlerfreien Implementierung sollten bei der 
  Ausführung dieser Klasse keine Exceptions geworfen werden und alle Tests als erfolgreich 
  ("successful") ausgegeben werden. Sie müssen diese Klasse nicht verändern, können aber eigene 
  Testfälle hinzufügen.
- Die Textdateien im Verzeichnis [data](../data) sind gespeicherte Befehlssequenzen, die von
  [Aufgabe8Test](../src/Aufgabe8Test.java) gelesen werden. Diese können Sie auch zum Testen von
  [PhotoApp8](../src/PhotoApp8.java) nutzen.

## Aufgaben

Ihre Aufgaben sind folgende:
1. Ergänzen Sie in der Klassendefinition von [MultiLayerRasterRGBA](../src/MultiLayerRasterRGBA.java)
   folgende Methoden. Der Iterator soll über alle Ebenen des Rasters iterieren. Die Reihenfolge der 
   Iterationen ist also umgekehrt zu der des von `iterator()` zurückgelieferten Iterators.
```
// Returns an iterator that iterates over the layers of this raster in a bottom-to-top order.
// (The first iteration returns the bottom-most layer.)
public RasterizedRGBIterator reversedIterator() {

    // TODO: implement method.
}

// Returns a new list with all the layers of this raster in top-to-bottom order. The size of the 
// list equals the value returned by 'numberOfLayers()'.
public java.util.ArrayList<RasterizedRGB> asList() {

    // TODO: implement method.
}
```
2. Vervollständigen Sie die Definition aller Untertypen von 
   [SafeOperation](../src/SafeOperation.java) und [SafeFactory](../src/SafeFactory.java). Achten 
   Sie dabei darauf, dass die Methoden dieser Klassen Ausnahmen vom Typ
   [FactoryException](../src/FactoryException.java) bzw. 
   [OperationException](../src/OperationException.java) werfen können. Sie können die Definition der
   Exceptionklassen bei Bedarf auch verändern (z.B. weitere Konstruktoren mit einem Parameter 
   für eine Nachricht hinzufügen). 
   Beachten Sie, dass [SafeOperationSequence](../src/SafeOperationSequence.java) über einen 
   Iterator verfügen soll.
   [SafeLoadSequenceFactory](../src/SafeLoadSequenceFactory.java) liest Befehle aus einer Datei,
   wobei jede Zeile der Datei genau einen Befehl mit seinen Argumenten enthält. Die erste Zeile der 
   Datei muss dem Befehl "create" entsprechen.
   Im Fall von Ausnahmen beim Lesen der Datei werden Objekte vom 
   Typ [FactoryException](../src/FactoryException.java) geworfen (auch eine `IOException` wird 
   gefangen und stattdessen ein Objekt vom Typ [FactoryException](../src/FactoryException.java) 
   weitergereicht). Nutzen Sie die Methode 
   `after` von [SafeOperation](../src/SafeOperation.java), um zusammengesetzte Operationen (Typ 
   [SafeOperationSequence](../src/SafeOperationSequence.java)) zu erzeugen.
3. Vervollständigen Sie [PhotoApp8](../src/PhotoApp8.java) gemäß den Kommentaren in der Datei. Die 
   Implementierung soll ähnlich aussehen wie die von [PhotoApp6](../src/PhotoApp6.java), mit dem 
   Unterschied, dass die Objekte, die bisher vom Typ [UnsafeFactory](../src/UnsafeFactory.java) 
   bzw. [UnsafeOperation](../src/UnsafeOperation.java) waren, nun vom Typ 
   [SafeFactory](../src/SafeFactory.java) bzw. [SafeOperation](../src/SafeOperation.java) sein 
   sollen. Der Befehl "create" benötigt zwei weitere positive ganzzahlige Argumente und erzeugt 
   einen neuen leeren Raster mit entsprechender Größe. Der aktuelle Raster wird durch 
   diesen ersetzt (Bildinhalte gehen verloren).
   Der Befehl "load" liest eine Befehlssequenz aus einer Datei.
   Die Ausnahmen (Objekte von `FactoryException` und `OperationException`) sollen in 
   [PhotoApp8](../src/PhotoApp8.java) gefangen und behandelt werden, so dass das Programm bei 
   fehlerhafter Eingabe nach entsprechender Rückmeldung weiterlaufen kann. Auch wenn ein 
   unbekannter Befehl eingegebenen wird, soll nach Rückmeldung das Programm weiterlaufen.
   Um den Umfang der Aufgabe zu begrenzen, werden einige der Operationen (wie zum Beispiel 
   `UnsafeConvolveOperation`) nicht aus den früheren Aufgabenblättern übernommen. Die 
   entsprechenden Befehle müssen Sie nicht berücksichtigen. Sie dürfen aber natürlich 
   zusätzliche Operationen definieren.

## Zusatzaufgaben (mit 2 Extrapunkten):
4. Erweitern Sie Ihr Programm um einen weiteren Befehl `save`, der die Befehle, die den 
   aktuellen Raster erzeugt haben (ab dem letzten `create`), in einer Datei speichert. `save` erwartet als Argument den Namen der Datei. Das Dateiformat ist kompatibel zu `load`. In der ersten Zeile der Datei muss der Befehl `create` stehen, mit dem der Raster erzeugt wurde. Für die Implementierung brauchen Sie zumindest eine entsprechende `SafeFactory`-Implementierung. Nutzen Sie die `toString`-Methode von `SafeOperation`.

   (Die 2 Zusatzpunkte ersetzen etwaige in früheren Aufgabenblättern / Übungstests verlorene Punkte. Insgesamt können weiterhin nicht mehr als 100 Punkte im Übungsteil erreicht werden).



### Denkanstöße (ohne Bewertung)
Folgende Fragen sind als Denkanstöße gedacht und bilden die Grundlage für eine Diskussion in der
Übungseinheit zu diesem Aufgabenblatt.

1. Haben Sie Ihre Iteratoren als Sichtweise oder Kopie implementiert? Wie verhält sich der 
   Iterator, wenn zwischen Iterationen die Datenstruktur verändert wird?
2. Ist es richtig zu sagen, dass `execute` eine `RasterizedRGB`-Sichtweise auf 
   `SafeOperation`-Objekte liefert? Warum? Warum nicht?

#### _Punkteaufteilung_

- Implementierung von `reversedIterator()` und `asList()` in 
  [MultiLayerRasterRGBA](../src/MultiLayerRasterRGBA.java): 1.5 Punkte
- Vervollständigung der Definition beiden Exceptionklassen: 0.5 Punkte
- Vervollständigung der Definition von [SafeOperationSequence](../src/SafeOperationSequence.java)
  und [SafeLoadSequenceFactory](../src/SafeLoadSequenceFactory.java) inklusive Iterator: 2.5 Punkte
- Vervollständigung der Definition aller anderen Untertypen von 
  [SafeOperation](../src/SafeOperation.java) und [SafeFactory](../src/SafeFactory.java): 2 Punkte
- Modifikation von [PhotoApp8](../src/PhotoApp5.java): 1.5 Punkt
- Gesamt: 8 Punkte (+2 mögliche Zusatzpunkte)
