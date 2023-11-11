# Aufgabenblatt 3

## Allgemeine Anmerkungen

Ihre Lösung für dieses Aufgabenblatt ist bis Montag, 17.4., 11h durch `git commit` und `push` abzugeben. Mit der Angabe werden folgende Dateien mitgeliefert: `PointLinkedList.java`, `TreePointColorMap.java`, `PhotoApp3.java` und `Aufgabe3Test.java`. Diese Klassen 
dürfen nur an den Stellen verändert werden, die mit TODO markiert sind. Zusätzliche Klassen, Interfaces, Methoden und Variablen dürfen aber eingefügt werden. Wenn Sie zusätzlich zu den gefragten Klassen weitere Klassen definieren, achten Sie darauf, dass die Klassennamen mit `My` beginnen, um Konflikte mit späteren Aufgabenblättern zu vermeiden.

## Ziel

Ziel der Aufgabe ist die Implementierung einer Liste für eine lineare und eines Baums für eine assoziative Datenstruktur (siehe Skriptum Seiten 60-69).

## Beschreibung der gegebenen Klassen
- [PointLinkedList](../src/PointLinkedList.java) ist das Gerüst für eine Implementierung einer 
  linearen Datenstruktur zur Verwaltung von Objekten des Typs `Point`.
- [TreePointColorMap](../src/TreePointColorMap.java) ist das Gerüst für eine Implementierung einer assoziativen Datenstruktur, die eine Pixelposition (2D-Punkt) mit der Farbe des Pixels assoziiert.
- [PhotoApp3](../src/PhotoApp3.java) ist ein Gerüst für die ausführbare Klasse.
- [Aufgabe3Test](../src/Aufgabe3Test.java) ist eine vorgegebene Klasse, die Sie zum Testen Ihrer Implementierung verwenden sollten. Bei einer fehlerfreien Implementierung sollten bei der Ausführung dieser Klasse keine Exceptions geworfen werden und alle Tests als erfolgreich ("successful") ausgegeben werden. Sie müssen diese Klasse nicht verändern, können aber eigene Testfälle hinzufügen.

## Aufgaben

Ihre Aufgaben sind folgende:

1. Vervollständigen Sie die Klassendefinition von [PointLinkedList](../src/PointLinkedList.java)
   gemäß den Kommentaren in der java-Datei. Die Implementierung soll mit Hilfe einer 
   einfach verketteten Liste erfolgen. Benutzen Sie keine Arrays oder vorgefertigten Klassen aus dem Java-Collection-Framework!
2. Vervollständigen Sie die Klassendefinition von [TreePointColorMap](../src/TreePointColorMap.java) gemäß den Kommentaren in der java-Datei. Die Implementierung soll mit Hilfe eines binären Suchbaums erfolgen, in dem die Positionen (Schlüssel vom Typ `Point`) gemäß der durch die Methode `compareTo` gegebenen Ordnungsrelation sortiert sind. Benutzen Sie keine Arrays oder vorgefertigten Klassen aus dem Java-Collection-Framework!
3. Ergänzen und vervollständigen Sie in der Klasse [SimpleRasterRGB](../src/SimpleRasterRGB.java)
   folgende Methodendefinition: 
```
   // Returns a mapping from all width*height pixel positions (Point) to corresponding colors 
   // (Color) of the pixels. Values of color (0,0,0) are also included in the mapping.
   public TreePointColorMap asMap() {  
   
        // TODO: implement method.
   }                                                       
```
4. Vervollständigen Sie die gegebene Klasse [PhotoApp3](../src/PhotoApp3.java) unter der
   Verwendung der oben erwähnten Klassen. Die Klasse enthält einfachen Programmcode, der zur 
   Laufzeit Befehle von der Konsole einliest und entsprechende Modifikationen am Raster 
   durchführt. Die Eingaben werden dabei nicht auf Korrektheit überprüft. Sie müssen nur 
   die Anweisungen zur Bearbeitung des Befehls "crop" ergänzen, wobei es mehrere Lösungswege 
   gibt. Hinweis: Hilfreich sind die Methoden der Klasse 
   [TreePointColorMap](../src/TreePointColorMap.java). Es kann aber zusätzlich auch 
   [PointLinkedList](../src/PointLinkedList.java) genutzt werden. Versuchen Sie eine Lösung zu 
   finden, die mit den gegebenen Methoden der Klassen auskommt (ohne eigens weitere Methoden zu 
   definieren). Sie können mit der Klasse [PhotoApp3](../src/PhotoApp3.java) auch experimentieren 
   und weitere Befehle hinzufügen.

### Denkanstöße (ohne Bewertung)
Folgende Fragen sind als Denkanstoß gedacht und bilden die Grundlage für eine Diskussion in der 
Übungseinheit zu diesem Aufgabenblatt.

1. Haben Sie bei der Implementierung darauf geachtet, dass die Zugriffe möglichst effizient erfolgen können (Z.B. ohne die Liste beim Zugriff wiederholt durchlaufen zu müssen)? Was ist in dem Zusammenhang der Vorteil der verketteten Liste?
2. Wofür eignen sich eher die Queue-Methoden `addFirst`, `addLast`, `pollFirst` bzw.
   `pollLast` und wofür eher die List-Methoden `get`?
3. [TreePointColorMap](../src/TreePointColorMap.java) erlaubt `null`-Werte. Was muss man im Unterschied zu [SimplePointColorMap](../src/SimplePointColorMap.java) diesbezüglich 
   berücksichtigen?

#### _Punkteaufteilung_

- Implementierung von [PointLinkedList](../src/PointLinkedList.java): 3 Punkte
- Implementierung von [TreePointColorMap](../src/PointColorTreeMap.java): 3 Punkte
- Implementierung von `asMap()` in [SimpleRasterRGB](../src/SimpleRasterRGB.java): 1 Punkt
- Anpassung von `PhotoApp3`: 1 Punkt
- Gesamt: 8 Punkte

