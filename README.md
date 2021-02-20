# AF1 - Java kötelező program

Alkalmazás fejlesztés I. kurzus keretén Java nyelven történő alkalmazás fejlesztése volt a feladat.
A feladat folyamán egy számológépet, és váltó (valuta, súly, stb.) alkalmazást kellett megvalósítani.

A fejlesztéshez a leadott technológiákat kellett használni: Maven, JavaFX, JDBC.
Az alkalmazásnak egy Core modulra épülve kell működnie mind webes, mind asztali környezetben.
A fő cél egy moduláris programozás alapjai, és egy példa program elkészítése volt.

## Beüzemelés

A db/resources/calc.db fájl helyezd át a tomcat szerver mappájába ez alapértelmezetten a C/apps/tomcatxxx/bin mappa.

A db/resources mappában található az sqlite.exe, és a táblát létrehozó kód.

### Asztali futtatás

Bármilyen probléma esetén először maven ablakon belül

    -Kotprog_szamologep (root)
        -lifecycle
            -clean  < projekt tisztítása
            -install < dependency-k telepítése
    -szamologep-desktop
        -plugins
            -compiler
                -compile < program lefordítása
            -javafx
                -javafx:run < futtatása
                
### Webes futtatás

Add Configuration -> "+" -> Tomcat server / local

Itt meg kell adni a tomcat mappáját az "Application server" alatt, és Deployment alatt kiválasztod a
"szamologep-web:war"-t (nem az exploded-et)

### Környezet

Java 11.0.7

Tomcat 9.0.34