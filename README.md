# Todo-App mit Vaadin

Eine einfache Todo-Webanwendung entwickelt mit **Vaadin**, einem Java Fullstack Framework.  
Die App ermöglicht das Anlegen, Bearbeiten und Löschen von Aufgaben mit Persistenz über eine **H2 In-Memory Datenbank**.

---

## Features

- Vollständig in Java entwickeltes Fullstack-Webprojekt mit Vaadin
- Aufgabenverwaltung: Erstellen, Bearbeiten, Löschen von Todo-Items
- Statusverwaltung (erledigt/offen)
- Persistenz mit H2 In-Memory Datenbank (keine externe Datenbank nötig)
- Serverseitiges State Management
- Responsive UI dank Vaadin-Komponenten

---

## Technologie-Stack

- **Frontend & Backend:** Vaadin Flow (Java)
- **Datenbank:** H2 In-Memory
- **Build-Tool:** Maven
- **Java-Version:** 17+

---

## Schnellstart

1. schnellstart mit Docker Image

Du kannst die Anwendung schnell starten, ohne sie lokal bauen zu müssen, indem du das Docker-Image nutzt.

Falls du das Image selbst bauen möchtest, verwende dazu den folgenden Befehl:

```bash

docker build -t vaadin-todo-app .

```

Starte anschließend die Anwendung mit:

```bash

docker run -p 8080:8080 vaadin-todo-app

```

\*\*\*\* Öffne dann im Browser http://localhost:8080, um die Todo-App zu verwenden.

2. Repository klonen

   ```bash
   git clone <repo-url>
   cd todo-vaadin-app
   ./mvnw clean install
   ```

   Öffne dann im Browser http://localhost:8080, um die Todo-App zu verwenden.
