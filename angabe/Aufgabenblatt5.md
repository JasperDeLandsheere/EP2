# Aufgabenblatt 5

## Allgemeine Anmerkungen

Ihre Lösung für dieses Aufgabenblatt ist bis Montag, 15.5., 11h durch `git commit` und `push` 
abzugeben. Mit der Angabe werden folgende Dateien mitgeliefert: `RasterizedRGB.java`, 
`HashPointColorMap.java`, `HashSparseRasterRGB.java`, `UnsafeFactory.java`, `UnsafeOperation.java`,
`UnsafeConvolveFactory.java`, `UnsafeCropFactory.java`, `UnsafeLineFactory.java`, 
`UnsafeFillFactory.java`, `UnsafeConvolveOperation.java`, `UnsafeCropOperation.java`, 
`UnsafeLineOperation.java`, `UnsafeFillOperation.java`, 
`PhotoApp5.java` und `Aufgabe5Test.java`. 
Diese Klassen dürfen nur an den Stellen verändert werden, die mit TODO markiert sind. 
Zusätzliche Klassen, Interfaces, Methoden und Variablen dürfen aber eingefügt werden. Wenn Sie 
zusätzlich zu den gefragten Klassen weitere Klassen definieren, achten Sie darauf, dass die 
Klassennamen mit `My` beginnen, um Konflikte mit späteren Aufgabenblättern zu vermeiden.

## Ziel

Ziel der Aufgabe ist die Anwendung der Konzepte: Interfaces, dynamisches Binden, 
Implementierung von toString(), Gleichheit und Hash-Werte, Hash-Tabelle (siehe Skriptum Seite 
75-91).

## Beschreibung der gegebenen Klassen
- [RasterizedRGB](../src/RasterizedRGB.java) ist das vollständig vorgegebene 
  Interface für Klassen, die ein 2D Raster repräsentieren. 
- [HashPointColorMap](../src/HashPointColorMap.java) ist das Gerüst für eine 
  Implementierung einer assoziativen Datenstruktur mittels Hash-Tabelle.
- [HashSparseRasterRGB](../src/HashSparseRasterRGB.java) ist das Gerüst für eine
  Implementierung einer Klasse zur Repräsentation von dünn besetzten Rastern.
- [UnsafeFactory](../src/UnsafeFactory.java) ist das vollständig vorgegebene,
  gemeinsame Interface für Klassen, die Objekte repräsentieren, die zur Erzeugung und 
  Initialisierung von anderen Objekten genutzt werden (daher die Bezeichnung "Factory" => 
  "Fabrik"). Klassen, die dieses Interface implementieren, sind:
  - [UnsafeConvolveFactory](../src/UnsafeConvolveFactory.java),
  - [UnsafeCropFactory](../src/UnsafeCropFactory.java),
  - [UnsafeFillFactory](../src/UnsafeFillFactory.java),
  - [UnsafeLineFactory](../src/UnsafeLineFactory.java).
- [UnsafeOperation](../src/UnsafeOperation.java) ist das vollständig vorgegebene,
  gemeinsame Interface für Klassen, die Operationen auf Rastern repräsentieren. Klassen, die dieses
  Interface implementieren, sind:
  - [UnsafeConvolveOperation](../src/UnsafeConvolveOperation.java),
  - [UnsafeCropOperation](../src/UnsafeCropOperation.java),
  - [UnsafeFillOperation](../src/UnsafeFillOperation.java),
  - [UnsafeLineOperation](../src/UnsafeLineOperation.java).

- [PhotoApp5](../src/PhotoApp5.java) ist die ausführbare Klasse, die Sie ebenfalls modifizieren 
  sollen.
- [Aufgabe5Test](../src/Aufgabe5Test.java) ist eine vorgegebene Klasse, die Sie zum Testen Ihrer 
  Implementierung verwenden sollten. Bei einer fehlerfreien Implementierung sollten bei der  
  Ausführung dieser Klasse keine Exceptions geworfen werden und alle Tests als erfolgreich 
  ("successful") ausgegeben werden. Sie müssen diese Klasse nicht verändern, können aber eigene 
  Testfälle hinzufügen.

## Aufgaben

Ihre Aufgaben sind folgende:

1. Überschreiben Sie in der Klasse [Point](../src/Point.java) folgende Methoden:
```
    @Override
    // Returns 'true' if 'o' is of class 'Point' and has coordinates equal to those of 'this'.
    // (This means that for two objects p1 and p2 of 'Point', p1.equals(p2) == true if and only if 
    // p1.compareTo(p2) == 0.)
    // Return 'false' otherwise.
    public boolean equals(Object o) {
        
        //TODO: implement method.
    }

    @Override
    // Returns the hash code of 'this'.
    public int hashCode() {
        
        //TODO: implement method.
    }

    @Override
    // Returns a string representation of 'this'.
    public String toString() {
    
        return "["+getX()+", "+getY()+"]";
    }
```
2. Vervollständigen Sie die Klassendefinition von [HashPointColorMap](../src/HashPointColorMap.java)
   gemäß den Kommentaren in der java-Datei. Sie sollen eine eigene Hash-Tabelle implementieren. 
   Benutzen Sie keine vorgefertigten Klassen aus dem Java-Collection-Framework!
3. Vervollständigen Sie die Klassendefinition von 
   [HashSparseRasterRGB](../src/HashSparseRasterRGB.java) 
   gemäß den Kommentaren in der java-Datei. Die Klasse repräsentiert ein dünn besetzes Raster 
   ähnlich wie die Klasse [SimpleSparseRasterRGB](../src/SimpleSparseRasterRGB.java) aus 
   Aufgabenblatt2. In [HashSparseRasterRGB](../src/HashSparseRasterRGB.java) soll nun die 
   Klasse [HashPointColorMap](../src/HashPointColorMap.java) für die Implementierung genutzt werden.
4. Refaktorisierung und Vereinfachung mittels Abstraktionshierarchien: 
   In [PhotoApp4](../src/PhotoApp4.java) wurden verschiedene Operationen mittels Verzweigungen 
   unterschieden. Das Hinzufügen weiterer Operationen auf dem Raster würde Änderungen 
   an mehreren Stellen im bestehenden Programmcode notwendig machen (weitere Methoden in den 
   Raster-Klassen, Verzweigungen in der ausführbaren Klasse, etc.).
   In [PhotoApp5](../src/PhotoApp5.java) wird das Programm neu organisiert und mit Hilfe von 
   Abstraktionshierarchien vereinfacht, sodass es leichter lesbar und erweiterbar wird. Dazu 
   werden die Methoden zur Durchführung von Operationen auf Rastern in eigene Klassen mit dem 
   gemeinsamen Obertyp [UnsafeOperation](../src/UnsafeOperation.java) 
   ausgelagert. (Das Interface [RasterizedRGB](../src/RasterizedRGB.java) spezifiziert Methoden,
   die nach wie vor vom Raster-Objekt zur Verfügung gestellt werden müssen, da sie von der 
   Implementierung der Klasse abhängen. Diese Methoden können von den
   [UnsafeOperation](../src/UnsafeOperation.java) -Klassen aufgerufen werden.)
   Für jede Operation gibt es eine Klasse mit dem gemeinsamen Obertyp 
   [UnsafeFactory](../src/UnsafeFactory.java). Diese 
   [UnsafeFactory](../src/UnsafeFactory.java)-Klassen lesen eine je nach Operation unterschiedliche 
   Anzahl an Parametern vom Scanner ein, und erzeugen daraus die entsprechende Operation, die nun 
   durch ein Objekt der entsprechenden Klasse repräsentiert wird. Das Ausführen der Operation geschieht durch 
   Aufruf der 'execute' Methode, wobei der Raster spezifiziert wird.
   Sie sollen zunächst die oben erwähnten Definitionen der Untertypen von
   [UnsafeOperation](../src/UnsafeOperation.java) vervollständigen und danach die von
   [UnsafeFactory](../src/UnsafeFactory.java). 'Unsafe' bedeutet hier, dass die
   Objekte der Klassen nur dann richtig funktionieren, wenn die Eingaben, die vom Scanner kommen, sinnvoll
   sind. Die Klassen überprüfen die Eingaben nicht (die Überprüfung wird Thema in einem der
   folgenden Aufgabenblätter sein). Durch die Nutzung der Obertypen 
   [UnsafeFactory](../src/UnsafeFactory.java) und [UnsafeOperation](../src/UnsafeOperation.java) 
   können die Programmzweige zur Unterscheidung von speziellen Operationen zusammengefasst werden.
   Ändern Sie dazu [PhotoApp5](../src/PhotoApp5.java), sobald Sie alle anderen Klassen 
   implementiert haben. Sie dürfen hier die Klasse `HashMap<String, UnsafeFactory>` verwenden.
   `HashMap<String, UnsafeFactory>` ist eine vordefinierte assoziative Datenstruktur in den 
   Java-Bibliotheken, die mithilfe einer Hashtabelle implementiert ist. Dabei sind die Schlüssel 
   vom Typ `String` und die zugeordneten Werte vom Typ `UnsafeFactory`.
   Diese Hashtabelle soll für einen eingelesenen Befehl (z.B. "line") die passende 
   "Fabrik" liefern, um weitere Token vom Scanner einzulesen und daraus eine Operation zu erzeugen. 
5. [optional, ohne Bewertung] Erstellen Sie zusätzlich die Klassen 
   [UnsafeSetColorFactory](../src/UnsafeSetColorFactory.java) und
   [UnsafeSetColorOperation](../src/UnsafeSetColorOperation.java), um auch den Befehl "setcolor"
   gemeinsam mit anderen Befehlen zu behandeln. 'execute' würde in diesem Fall den Raster 
   unverändert lassen.

### Denkanstöße (ohne Bewertung)
Folgende Fragen sind als Denkanstöße gedacht und bilden die Grundlage für eine Diskussion in der
Übungseinheit zu diesem Aufgabenblatt.

1. An welchen Stellen in Ihrem Programm wird der Programmcode durch "dynamisches Binden" einfacher?
2. Was ändert sich am Verhalten von [HashPointColorMap](../src/HashPointColorMap.java), wenn man 
   in [Point](../src/Point.java) die Methoden `equals` und `hashCode` nicht überschreiben würde?
3. Welche der Klassen aus früheren Aufgabenblättern könnten ebenfalls das Interface 
   [RasterizedRGB](../src/RasterizedRGB.java) implementieren? Was wäre dafür zu tun?

#### _Punkteaufteilung_

- Überschreiben der drei Methoden in [Point](../src/Point.java): 0.5 Punkte
- Implementierung von [HashPointColorMap](../src/HashPointColorMap.java): 2 Punkte
- Implementierung von [HashSparseRasterRGB](../src/HashSparseRasterRGB.java): 1.5 Punkte
- Vervollständigung der Definition der Untertypen von [UnsafeOperation](../src/UnsafeOperation.java) 
  und [UnsafeFactory](../src/UnsafeFactory.java): 3 Punkte
- Modifikation von [PhotoApp5](../src/PhotoApp5.java): 1 Punkt
- Gesamt: 8 Punkte
