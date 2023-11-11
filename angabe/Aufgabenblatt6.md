# Aufgabenblatt 6

## Allgemeine Anmerkungen

Ihre Lösung für dieses Aufgabenblatt ist bis Freitag, 26.5., 11h durch `git commit` und `push` 
abzugeben. Mit der Angabe werden folgende Dateien mitgeliefert: `Layered.java`, `SingleLayer.java`, 
`TreeSparseRasterRGBA.java`, `MultiLayerRasterRGBA.java`, `RasterizedRGBIterable.java`,
`RasterizedRGBIterator.java`, `UnsafeNewLayerFactory.java`, `UnsafeNewLayerOperation.java`, 
`PhotoApp6.java` und `Aufgabe6Test.java`. 
Diese Klassen dürfen nur an den Stellen verändert werden, die mit TODO markiert sind. 
Zusätzliche Klassen, Interfaces, Methoden und Variablen dürfen aber eingefügt werden. Wenn Sie 
zusätzlich zu den gefragten Klassen weitere Klassen definieren, achten Sie darauf, dass die 
Klassennamen mit `My` beginnen, um Konflikte mit späteren Aufgabenblättern zu vermeiden.

## Ziel

Ziel der Aufgabe ist die Anwendung der Konzepte: Interfaces, dynamisches Binden, 
iterierbare Abstraktionen (siehe Skriptum Seite 91-105).

## Beschreibung der gegebenen Klassen
- [Layered](../src/Layered.java) ist das vollständig vorgegebene 
  Interface für Klassen, die ein 2D-Raster repräsentieren, das aus mehreren Ebenen bestehen kann.
- [SingleLayer](../src/SingleLayer.java) ist das vollständig vorgegebene
  Interface für Klassen, die ein 2D-Raster repräsentieren, das aus genau einer Ebene besteht.
- [TreeSparseRasterRGBA](../src/TreeSparseRasterRGBA.java) ist die teilweise vorgegebene
  Implementierung einer Klasse zur Repräsentation von dünn besetzten Rastern. Objekte dieser 
  Klasse sind Ebenen in Rastern und daher vom Typ [SingleLayer](../src/SingleLayer.java).
- [MultiLayerRasterRGBA](../src/MultiLayerRasterRGBA.java) ist das Gerüst für die
  Implementierung einer Klasse, die Raster repräsentieren, die aus mindestens zwei Ebenen bestehen.
  Objekte dieser Klasse sind Ebenen vom Typ [Layered](../src/Layered.java).
- [UnsafeNewLayerFactory](../src/UnsafeNewLayerFactory.java) ist eine Klasse, die eine Fabrik 
  für Objekte vom Typ [UnsafeNewLayerOperation](../src/UnsafeNewLayerOperation.java) darstellt.
- [UnsafeNewLayerOperation](../src/UnsafeNewLayerOperation.java) ist das Gerüst für eine Klasse, 
  die Operationen repräsentiert, die in einem Raster eine neue transparente oberste Ebene anlegen.
- [RasterizedRGBIterable](../src/RasterizedRGBIterable.java) ist das vollständig vorgegebene
  Interface für Klassen, die iterierbare Objekte repräsentieren, wobei die Elemente der Iterationen
  Raster sind.
- [RasterizedRGBIterator](../src/RasterizedRGBIterator.java) ist das vollständig vorgegebene
  Interface für Klassen, die Iteratoren über Raster als Elemente repräsentieren.
- [PhotoApp6](../src/PhotoApp6.java) ist die ausführbare Klasse, die Sie ebenfalls modifizieren 
  sollen.
- [Aufgabe6Test](../src/Aufgabe6Test.java) ist eine vorgegebene Klasse, die Sie zum Testen Ihrer 
  Implementierung verwenden sollten. Bei einer fehlerfreien Implementierung sollten bei der  
  Ausführung dieser Klasse keine Exceptions geworfen werden und alle Tests als erfolgreich 
  ("successful") ausgegeben werden. Sie müssen diese Klasse nicht verändern, können aber eigene 
  Testfälle hinzufügen.

## Aufgaben

Ihre Aufgaben sind folgende:
1. Ergänzen Sie in der Klassendefinition von [Point](../src/Point.java) in Zeile 2 folgende
   `implements`-Klausel:

```
public class Point implements Comparable<Point> {
```

   `Comparable<Point>` ist ein vordefiniertes Interface, das die Methode `compareTo` beschreibt,
   die bereits in `Point` implementiert wurde. Durch das Hinzufügen der Klausel ist `Point` 
   als Elementtyp in vorgefertigten Java-Klassen benutzbar, die eine sortierte Sammlung 
   repräsentieren (z.B. `TreeSet<Point>`). 
2. Vervollständigen Sie die Definition von [MultiLayerRasterRGBA](../src/MultiLayerRasterRGBA.java)
   gemäß den Kommentaren in der java-Datei. Die Klasse implementiert das Interface
   [Layered](../src/Layered.java) und [RasterizedRGBIterable](../src/RasterizedRGBIterable.java).
   Definieren Sie die fehlenden, in den Interfaces beschriebenen Methoden. Für die Implementierung
   müssen Sie eine zusätzliche eigene Klasse definieren, die [RasterizedRGBIterator](../src/RasterizedRGBIterator.java)
   implementiert. Der Iterator soll über alle Ebenen des Rasters iterieren, beginnend mit der
   obersten Ebene. Jede Iteration liefert die nächste Ebene darunter, bis zur untersten
   Ebene. Gibt es keine weitere Ebene, liefert die nächste Iteration 'null'.
3. Vervollständigen Sie die Definition von [TreeSparseRasterRGBA](../src/TreeSparseRasterRGBA.java)
   gemäß den Kommentaren in der java-Datei. Die Klasse implementiert das Interface
   [SingleLayer](../src/SingleLayer.java). Definieren Sie die fehlenden, im Interface beschriebenen 
   Methoden.
4. Ändern Sie die Klasse [LayeredRasterRGBA](../src/LayeredRasterRGBA.java) aus
   [Aufgabenblatt4](Aufgabenblatt4.md) so, dass sie ebenfalls
   [RasterizedRGBIterable](../src/RasterizedRGBIterable.java) implementiert.
   Fügen Sie dazu die `implements`-Klausel ein:
```
public class LayeredRasterRGBA implements RasterizedRGBIterable {
```
   und implementieren Sie die Methode `iterator()`. Dazu müssen Sie wieder das Interface 
   [RasterizedRGBIterator](../src/RasterizedRGBIterator.java) in einer eigenen Klasse 
   implementieren. Der Iterator verhält sich so wie der von
   [MultiLayerRasterRGBA](../src/MultiLayerRasterRGBA.java). Zur Implementierung des Iterators 
   muss [RasterRGBA](../src/RasterRGBA.java) zu einem Untertyp von 
   [RasterizedRGB](../src/RasterizedRGB.java) gemacht werden.
   Zusätzlich darf auch die Klasse [LinkedListRasterRGBA](../src/LinkedListRasterRGBA.java) adaptiert werden.
5. Vervollständigen Sie die Klassen [UnsafeNewLayerFactory](../src/UnsafeNewLayerFactory.java) und
   [UnsafeNewLayerOperation](../src/UnsafeNewLayerOperation.java). 
6. Vervollständigen Sie [PhotoApp6](../src/PhotoApp6.java) gemäß den Kommentaren in der Datei. Die 
   Implementierung soll ähnlich aussehen wie die von [PhotoApp5](../src/PhotoApp5.java), mit dem 
   Unterschied, dass der bearbeitete Raster vom Typ [Layered](../src/Layered.java) sein soll und 
   der Befehl "newlayer" zur Verfügung stehen soll.

### Denkanstöße (ohne Bewertung)
Folgende Fragen sind als Denkanstöße gedacht und bilden die Grundlage für eine Diskussion in der
Übungseinheit zu diesem Aufgabenblatt.

1. Wann kann für Objekte eigener Klassen eine ForEach-Schleife benutzt werden? Wo könnten Sie sie 
   bei Ihrer Implementierung einsetzen?
2. Angenommen Sie nutzen in [TreeSparseRasterRGBA](../src/TreeSparseRasterRGBA.java) anstelle
   von `TreeMap<Point>` die Klasse `HashMap<Point>`, was müssten Sie ändern? Welche Vor- und 
   Nachteile haben die Varianten?
3. Welche Änderungen wären notwendig, damit [LayeredRasterRGBA](../src/LayeredRasterRGBA.java)
   das Interface [Layered](../src/Layered.java) implementiert? Was wäre der Vorteil?

#### _Punkteaufteilung_

- Implementierung von [MultiLayerRasterRGBA](../src/MultiLayerRasterRGBA.java) inklusive  
  Iterator: 2.5 Punkte
- Vervollständigung von [TreeSparseRasterRGBA](../src/TreeSparseRasterRGBA.java): 1 Punkt
- Implementierung von [RasterizedRGBIterable](../src/RasterizedRGBIterable.java) 
  in [LayeredRasterRGBA](../src/LayeredRasterRGBA.java): 2 Punkte
- Implementierung von [UnsafeNewLayerFactory](../src/UnsafeNewLayerFactory.java) und
  [UnsafeNewLayerOperation](../src/UnsafeNewLayerOperation.java): 1 Punkt
- Vervollständigung von [PhotoApp6](../src/PhotoApp6.java): 1.5 Punkte
- Gesamt: 8 Punkte
