# Aufgabenblatt 4

## Allgemeine Anmerkungen

Ihre Lösung für dieses Aufgabenblatt ist bis Montag, 24.4., 11h durch `git commit` und `push` 
abzugeben. Mit der Angabe werden folgende Dateien mitgeliefert: `RasterRGBA.java`, 
`LinkedListRasterRGBA.java`, `LayeredRasterRGBA.java`, `TreeSetRasterRGBA.java`, `PhotoApp4.java` 
und `Aufgabe4Test.java`. 
Diese Klassen dürfen nur an den Stellen verändert werden, die mit TODO markiert sind. 
Zusätzliche Klassen, Interfaces, Methoden und Variablen dürfen aber eingefügt werden. Wenn Sie 
zusätzlich zu den gefragten Klassen weitere Klassen definieren, achten Sie darauf, dass die 
Klassennamen mit `My` beginnen, um Konflikte mit späteren Aufgabenblättern zu vermeiden.

Aufgabenblatt 4 ist das letzte Aufgabenblatt, das inhaltlich noch zum Stoff für den praktischen 
Teil von Test 1 zählt.

## Ziel

Ziel der Aufgabe ist die Implementierung einer doppelt verketteten Liste für eine lineare 
Datenstruktur und eines binären Suchbaums für die Implementierung eines Sets (siehe Skriptum Seiten 
60-73).

## Beschreibung der gegebenen Klassen
- [RasterRGBA](../src/RasterSRGB.java) ist die vollständig vorgegebene Implementierung einer 
  Klasse, die ein 2D Raster repräsentiert (ähnlich zu `SimpleRasterRGB`). In diesem Datentyp wird zusätzlich zu den RGB-Farben pro Pixel noch ein für die _Transparenz_ gespeichert.
- [LinkedListRasterRGBA](../src/RasterSRGBLinkedList.java) ist das Gerüst für eine 
  Implementierung einer linearen Datenstruktur mittels doppelt verketteter Liste.
- [LayeredRasterRGBA](../src/LayeredRasterSRGB.java) ist das Gerüst für eine
  Implementierung einer Klasse zur Repräsentation von Rastern, die aus mehreren Ebenen bestehen.
- [TreeSetRasterRGBA](../src/TreeRasterSRGBSet.java) ist das Gerüst für eine
  Implementierung einer Klasse zur Repräsentation von Mengen von `RasterRGBA`-Objekten.
- [PhotoApp4](../src/PhotoApp4.java) ist die vollständig vorgegebene ausführbare Klasse.
- [Aufgabe4Test](../src/Aufgabe4Test.java) ist eine vorgegebene Klasse, die Sie zum Testen Ihrer Implementierung verwenden sollten. Bei einer fehlerfreien Implementierung sollten bei der Ausführung dieser Klasse keine Exceptions geworfen werden und alle Tests als erfolgreich 
  ("successful") ausgegeben werden. Sie müssen diese Klasse nicht verändern, können aber eigene 
  Testfälle hinzufügen.

## Aufgaben

Ihre Aufgaben sind folgende:

1. Vervollständigen Sie die Klassendefinition von [LinkedListRasterRGBA](../src/RasterSRGBLinkedList.java)
   gemäß den Kommentaren in der java-Datei. Die Implementierung soll mit Hilfe einer 
   doppelt verketteten Liste erfolgen. Benutzen Sie keine Arrays oder vorgefertigten Klassen aus 
   dem Java-Collection-Framework!
2. Vervollständigen Sie die Klassendefinition von [LayeredRasterRGBA](../src/LayeredRasterSRGB.java) 
   gemäß den Kommentaren in der java-Datei. Die Klasse repräsentiert ein Raster mit mehreren 
   Ebenen vom Typ `RasterRGBA`. Nutzen Sie ein Objekt der Klasse `LinkedListRasterRGBA` für die 
   Implementierung. RGBA steht für (Red, Green, Blue, Alpha), d.h. zusätzlich zu den 
   drei Farbkomponenten hat jedes Pixel auch einen alpha-Wert, der die Transparenz des Pixels 
   im Bereich 0 (völlig transparent) bis 255 (opak) angibt 
   (siehe Klasse [java.awt.Color](https://docs.oracle.com/javase/7/docs/api/java/awt/Color.html)).
   Die Ebenen des Rasters können separat bearbeitet werden. Zum Beispiel können unterschiedliche 
   Linien in unterschiedliche Ebenen gezeichnet werden. Ebenso können einzelne Ebenen mit 
   `concolve` gefiltert werden. Dazu gibt es in [RasterRGBA](../src/RasterSRGB.java) eine 
   vorgegebene Implementierung von `convolve`, die auch die Alpha-Werte berücksichtigt.
   Die Ebenen können auch unter Berücksichtigung der Transparenz ihrer Pixel zu einem einzigen Bild 
   kombiniert werden. Das resultierende Bild ist eine Überblendung der Ebenen. Dieser Vorgang wird 
   auch als _Compositing_ bezeichnet. Dazu gibt es in der Klasse [RasterRGBA](../src/RasterSRGB.java) 
   Methoden, die die Überblendung von zwei Rastern bzw. zwei Farbwerten berechnet (Methoden `over`). 
   Wenn mehr als zwei Ebenen vorhanden sind, wird der Zusammenführungsprozess mit `over` 
   iterativ wiederholt. Die korrekte Reihenfolge beim Zusammenführen von Ebenen mit `over` ist, dass
   Sie mit den untersten beiden Ebenen beginnen und sich bis zur obersten Ebene vorarbeiten.
   Das bedeutet, dass die unterste Ebene zunächst mit der Ebene darüber überblendet wird. 
   Das Ergebnis wird mit der nächsthöheren Ebene überblendet, und so weiter, bis alle Ebenen 
   zu einem einzigen endgültigen Bild verschmolzen sind.
3. Vervollständigen Sie die Klassendefinition von [TreeSetRasterRGBA](../src/TreeRasterSRGBSet.java) 
   gemäß den Kommentaren in der java-Datei. Die Klasse repräsentiert eine Menge von Elementen 
   des Typs `RasterRGBA`. Die Implementierung soll mit Hilfe eines binären Suchbaums erfolgen, 
   in dem die Anzahl der Pixel mit der Farbe (0,0,0,0) im Rasterobjekt der Schlüssel und das 
   Rasterobjekt selbst der Wert ist. Beachten Sie, dass der Baum mehrere Objekte mit demselben 
   Schlüssel beinhalten kann (ein Unterbaum eines Knotens kann beispielsweise nicht nur 
   kleinere, sondern auch gleiche Schlüssel enthalten). Der Baum darf aber nicht dasselbe Objekt 
   mehrmals enthalten (siehe Spezifikation der Methode `contains`). Benutzen Sie keine Arrays 
   oder vorgefertigten Klassen aus dem Java-Collection-Framework!
4. [opional, ohne Bewertung] Führen Sie die gegebene Klasse [PhotoApp4](../src/PhotoApp4.java) aus,
   um ihr Programm zu testen. Die Klasse enthält einfachen Programmcode, der zur Laufzeit Befehle von der 
   Konsole einliest und entsprechende Modifikationen in den Ebenen des Rasters durchführt. Die 
   Klasse funktioniert analog zu [PhotoApp3](../src/PhotoApp3.java). [PhotoApp4](../src/PhotoApp4.java) 
   unterscheidet sich von [PhotoApp3](../src/PhotoApp3.java) dadurch, dass 
   mit einem Objekt von `LayeredRasterRGBA` statt `SimpleRasterRGB` gearbeitet wird. Die 
   Eingaben werden nicht auf Korrektheit überprüft. Sie können mit der Klasse
   [PhotoApp4](../src/PhotoApp4.java) auch experimentieren und weitere Befehle hinzufügen.

### Denkanstöße (ohne Bewertung)
Folgende Fragen sind als Denkanstöße gedacht und bilden die Grundlage für eine Diskussion in der 
Übungseinheit zu diesem Aufgabenblatt.

1. Was passiert, wenn die Elemente von `TreeSetRasterRGBA` nach dem Hinzufügen verändert werden 
   (z.B. Pixelwerte geändert werden)?
2. In welchen Fällen ist `TreeSetRasterRGBA` nicht effizient (degeneriert der Baum zu einer Liste)? 

#### _Punkteaufteilung_

- Implementierung von [LinkedListRasterRGBA](../src/RasterSRGBLinkedList.java): 2.5 Punkte
- Implementierung von [LayeredRasterRGBA](../src/LayeredRasterSRGB.java): 2 Punkte
- Implementierung von [TreeSetRasterRGBA](../src/TreeRasterSRGBSet.java): 1.5 Punkte
- Gesamt: 6 Punkte

