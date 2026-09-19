# 🐍🪜 Scale e Serpenti (Snakes & Ladders) - Simulatore Automatico

Questo repository contiene il progetto realizzato per il corso di **Ingegneria del Software** presso l'Università della Calabria.

Il progetto consiste in un simulatore digitale del celebre gioco da tavolo "Scale e Serpenti". A differenza delle versioni tradizionali, questa applicazione offre **un'esperienza automatica e passiva**: l'utente configura i parametri iniziali della partita e il sistema si occupa di simulare interamente l'andamento del gioco, aggiornando l'interfaccia grafica in tempo reale fino alla vittoria di uno dei giocatori.

---

## ✨ Funzionalità Principali (Features)

Il sistema offre un'ampia gamma di opzioni di personalizzazione tramite un'interfaccia grafica (GUI) intuitiva:

*   👥 **Numero di Giocatori:** Configurabile da un minimo di 2 a un massimo di 6.
*   🎲 **Numero di Dadi:** Possibilità di giocare con 1 o 2 dadi.
*   🔲 **Dimensione del Tabellone:** Completamente personalizzabile (da 36 a 300 caselle).
*   ⚙️ **Strategie di Generazione del Tabellone:**
    *   **Strategia Casuale:** Il sistema posiziona automaticamente tutte le caselle speciali sul tabellone.
    *   **Strategia Utente (Manuale):** L'utente ha il controllo totale. Può inserire da 1 a 5 scale e serpenti, scegliendo esplicitamente le posizioni di partenza e di arrivo (con validazione automatica degli input per evitare posizioni duplicate o non valide).
*   👁️ **Simulazione in Tempo Reale:** Movimenti fluidi con un delay (1 secondo) tra i turni, visualizzazione dinamica sulla griglia e log testuale laterale per seguire le azioni dei giocatori.

---

## 🏗️ Architettura e Ingegneria del Software (Design Patterns)

L'applicativo è stato sviluppato in **Java**, ponendo una forte enfasi sulla corretta progettazione architetturale e sull'utilizzo dei **Design Pattern (GoF)** per garantire manutenibilità, estendibilità e disaccoppiamento del codice:

1.  **Factory Method (`CasellaFactory`)**
    *   *Scopo:* Centralizzare e astrarre la creazione delle caselle speciali.
    *   *Implementazione:* Una factory valuta un `enum` (es. `SCALA`, `SERPENTE`, `PANCHINA`) e istanzia dinamicamente l'oggetto corretto che estende `AbstractCasella`. Questo permette di aggiungere in futuro nuove tipologie di caselle senza alterare la logica principale.

2.  **Strategy (`GestioneBoardStrategy`)**
    *   *Scopo:* Gestire la flessibilità nella creazione del tabellone.
    *   *Implementazione:* Due strategie distinte (`GestioneCasualeStrategy` e `GestioneUtenteStrategy`) implementano la stessa interfaccia per popolare la `Board`. Il client cambia comportamento a runtime in base alla scelta dell'utente nella GUI.

3.  **Observer**
    *   *Scopo:* Disaccoppiare la logica di dominio (il modello) dall'interfaccia grafica (la vista).
    *   *Implementazione:* I giocatori (`Player`) notificano la GUI dei propri spostamenti. L'interfaccia, agendo da observer, riceve gli aggiornamenti di stato e ridisegna in automatico le pedine sul tabellone senza che la logica di gioco conosca i dettagli della GUI.

---

## 🗺️ Legenda delle Caselle Speciali

Il tabellone è arricchito da caselle speciali colorate, ciascuna con effetti specifici sul giocatore che vi atterra:

| Colore | Tipo di Casella | Effetto |
| :--- | :--- | :--- |
| 🟩 **Verde** | **Testa del Serpente** | Il giocatore retrocede fino alla coda del serpente. |
| ⬜ **Grigia** | **Base della Scala** | Il giocatore avanza fino alla cima della scala. |
| 🟨 **Gialla** | **Premio 'DADI'** | Il giocatore ottiene un turno extra (lancia di nuovo). |
| 🟪 **Rosa** | **Premio 'MOLLA'** | Il giocatore avanza di ulteriori caselle bonus. |
| 🟧 **Arancione** | **Sosta 'PANCHINA'** | Penalità: il giocatore resta fermo per un turno. |
| 🟥 **Rossa** | **Sosta 'LOCANDA'** | Penalità: il giocatore resta fermo per più turni. |
| 🟦 **Celeste** | **'PESCA UNA CARTA'** | Azione casuale (bonus o malus a sorpresa). |

*(Nota: L'arrivo e la partenza esatta non possono coincidere mai sulla casella 1 o sull'ultima casella per garantire la corretta dinamica del gioco).*

---

## 🚀 Come Eseguire il Progetto
Per l'esecuzione del progetto eseguire la classe "GUI" all'interno del package swing.
