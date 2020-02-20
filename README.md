# Projektantrag

| Projektkürzel | Datum      |
| ------------- | ---------- |
| LFG           | 18.02.2020 |

## Team

* Floris Staub <f1staub@hsr.ch> ([Floris Staub · GitLab](https://gitlab.dev.ifs.hsr.ch/flObvious))
* Denis Nauli <dnauli@hsr.ch> ([Denis Nauli · GitLab](https://gitlab.dev.ifs.hsr.ch/DNA))
* Yael Schärer <yschaere@hsr.ch> ([Yael Schärer · GitLab](https://gitlab.dev.ifs.hsr.ch/yschaere))
* Nadine Sennhauser <nsennhau@hsr.ch> ([Nadine Sennhauser · GitLab](https://gitlab.dev.ifs.hsr.ch/se-na))
* Andreas Baumgartner <abaumgar@hsr.ch> ([Andreas Baumgartner · GitLab](https://gitlab.dev.ifs.hsr.ch/abaumgar))

## Beratungs- und Review-Zeitslots

```
 X  = Slot ist dem Team möglich
(X) = Slot ist für das Team nicht optimal, wäre aber möglich
    = Slot ist nicht möglich
```


|   *Zeitslot*    | Montag | Dienstag | Mittwoch | Donnerstag | Freitag |
| --------------- | ------ | -------- | -------- | ---------- | ------- |
| **08:00-09:00** |        |          |          |            |         |
| **09:00-10:00** |        |          |          |            |         |
| **10:00-11:00** |        |          |          |            |         |
| **11:00-12:00** |        |          |          |            |         |
| **12:00-13:00** |        |          | X        | (X)        |         |
| **13:00-14:00** |        |          | (X)      |            |         |
| **14:00-15:00** |        |          | (X)      |            |         |
| **15:00-16:00** |        |          | (X)      |            |         |
| **16:00-17:00** |        |          | (X)      |            |         |
| **17:00-18:00** |        |          | X        |            |         |
| **18:00-19:00** |        |          | X        |            |         |


## Motivation

Das Ziel ist es eine Plattform zu erstellen, die helfen soll Teams für Projekte zu bilden. Da wir im Rahmen des Engineering Projekts und diverser anderer Arbeiten immer wieder Gruppen bilden mussten, wollen wir eine Applikation entwickeln, die diesen Prozess in Zukunft erleichtert. So erhält ein User Gruppenvorschläge und Gruppen entsprechende Teilnehmervorschläge. Diese können per “Swipe” an- oder abgelehnt werden. Das Grundsystem der App soll an bekannte Partnervermittlungs Apps wie Tinder angelehnt werden.

Das System ist im ersten Schritt für Studenten gedacht, soll aber auf verschiedenste Szenarios erweitert werden können. Beispiele dafür sind Sportgruppen, Arbeitsmarkt oder Ähnliches.


## Programmidee

Im System soll sich der User registrieren und anschliessend eine Gruppe suchen und oder erstellen können. Der User muss hierfür ein kurzes Fähigkeitsprofil erstellen. Anhand diesem Profil werden die vorzuschlagenden Gruppen kalkuliert. Bei Gruppen werden entsprechend Anforderungsprofile hinterlegt, um passende Teammitglieder zu finden.

Als Einzelperson können die Vorschläge per “Swipes” akzeptiert oder abgelehnt werden. Bei der Gruppe kann der verantwortliche User vorgeschlagene Teammitglieder an- oder ablehnen. Bei Match-ups werden Kontaktinformationen ausgetauscht.

Vorerst konzentrieren wir uns auf die simple Vermittlung der User. Zusätzlich könnten Gruppeninformationen wie zum Beispiel aktuelle Listen von Teammitgliedern eingeführt werden.


## Realisierung

Plattform: Android 5.0
Programmiersprachen: Java 11
Entwicklungsumgebung: Android Studio, IntelliJ
Versionierung/Projektmanagement: GitLab
Benutzerschnittstelle: Android App
Backend: Java 11 (Matching Logik)
Datenhaltung: REST API, JSON (provisorisch)
Coding Guidelines: [Google Java Guidelines](https://google.github.io/styleguide/javaguide.html)
Design Guidelines: Android Material Design
