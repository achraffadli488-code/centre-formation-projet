package com.centreformation.app;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.*;

import com.centreformation.service.CentreFormationManager;
import com.centreformation.model.*;

// ============================================================================
//   SITE WEB COMPLET — Serveur HTTP + UI + API JSON en un seul fichier
//   Version compatible avec ton CentreFormationManager
// ============================================================================

public class SiteWebComplet {

    // ========================================================================
    //   ⛓️ MINI SERVEUR HTTP
    // ========================================================================
    public static class MiniServer {

        private final int port;
        private final CentreFormationManager manager;

        public MiniServer(int port, CentreFormationManager manager) {
            this.port = port;
            this.manager = manager;
        }

        public void start() throws IOException {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("🌐 Serveur web démarré : http://localhost:" + port);

            while (true) {
                Socket client = serverSocket.accept();
                handleClient(client);
            }
        }

        // ====================================================================
        //   ROUTER PRINCIPAL
        // ====================================================================
        private void handleClient(Socket client) {
            try (
                BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()))
            ) {
                String requestLine = reader.readLine();
                if (requestLine == null) {
                    client.close();
                    return;
                }

                String[] parts = requestLine.split(" ");
                String method = parts[0];
                String path = parts[1];

                // Lire les headers
                while (reader.ready()) {
                    String line = reader.readLine();
                    if (line == null || line.isEmpty()) break;
                }

                // -------------------------------
                //        ROUTES API JSON
                // -------------------------------
                if (path.equals("/api/formations")) {
                    sendJson(writer, apiFormations());
                    return;
                }

                if (path.equals("/api/apprenants")) {
                    sendJson(writer, apiApprenants());
                    return;
                }

                if (path.equals("/api/formateurs")) {
                    sendJson(writer, apiFormateurs());
                    return;
                }

                if (path.equals("/api/sessions")) {
                    sendJson(writer, apiSessions());
                    return;
                }

                // -------------------------------
                //          PAGE HTML
                // -------------------------------
                sendHtml(writer, buildHtmlPage());

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // ====================================================================
        //   API JSON — totalement compatible avec TON CentreFormationManager
        // ====================================================================
        private String apiFormations() {
            StringBuilder json = new StringBuilder("[");
            boolean first = true;

            for (Formation f : manager.getFormations()) {
                if (!first) json.append(",");

                json.append("{")
                        .append("\"id\":").append(f.getIdFormation()).append(",")
                        .append("\"titre\":\"").append(escape(f.getTitre())).append("\",")
                        .append("\"duree\":").append(f.getDuree()).append(",")
                        .append("\"categorie\":\"").append(escape(f.getCategorie().toString())).append("\"")
                        .append("}");

                first = false;
            }

            json.append("]");
            return json.toString();
        }

        private String apiApprenants() {
            StringBuilder json = new StringBuilder("[");
            boolean first = true;

            for (Apprenant a : manager.getApprenants()) {
                if (!first) json.append(",");

                json.append("{")
                        .append("\"id\":").append(a.getIdApprenant()).append(",")
                        .append("\"nom\":\"").append(escape(a.getNom())).append("\",")
                        .append("\"prenom\":\"").append(escape(a.getPrenom())).append("\",")
                        .append("\"email\":\"").append(escape(a.getEmail())).append("\"")
                        .append("}");

                first = false;
            }

            json.append("]");
            return json.toString();
        }

        private String apiFormateurs() {
            StringBuilder json = new StringBuilder("[");
            boolean first = true;

            for (Formateur f : manager.getFormateurs()) {
                if (!first) json.append(",");

                json.append("{")
                        .append("\"id\":").append(f.getIdFormateur()).append(",")
                        .append("\"nom\":\"").append(escape(f.getNom())).append("\",")
                        .append("\"prenom\":\"").append(escape(f.getPrenom())).append("\"")
                        .append("}");

                first = false;
            }

            json.append("]");
            return json.toString();
        }

        private String apiSessions() {
            StringBuilder json = new StringBuilder("[");
            boolean first = true;

            for (Session s : manager.getSessions()) {
                if (!first) json.append(",");

                json.append("{")
                        .append("\"id\":").append(s.getIdSession()).append(",")
                        .append("\"formation\":\"").append(escape(s.getFormation().getTitre())).append("\",")
                        .append("\"formateur\":\"").append(escape(s.getFormateur().getNom())).append("\",")
                        .append("\"etat\":\"").append(escape(s.getEtat().toString())).append("\"")
                        .append("}");

                first = false;
            }

            json.append("]");
            return json.toString();
        }

        private String escape(String s) {
            return s.replace("\"", "\\\"");
        }
        // ====================================================================
        //   UTILITAIRES HTTP
        // ====================================================================
        private void sendHtml(BufferedWriter writer, String html) throws IOException {
            byte[] body = html.getBytes(StandardCharsets.UTF_8);
            writer.write("HTTP/1.1 200 OK\r\n");
            writer.write("Content-Type: text/html; charset=UTF-8\r\n");
            writer.write("Content-Length: " + body.length + "\r\n");
            writer.write("\r\n");
            writer.write(html);
            writer.flush();
        }

        private void sendJson(BufferedWriter writer, String json) throws IOException {
            byte[] body = json.getBytes(StandardCharsets.UTF_8);
            writer.write("HTTP/1.1 200 OK\r\n");
            writer.write("Content-Type: application/json; charset=UTF-8\r\n");
            writer.write("Content-Length: " + body.length + "\r\n");
            writer.write("\r\n");
            writer.write(json);
            writer.flush();
        }

        private String buildHtmlPage() {
            return getHTMLPage();
        }

        // ====================================================================
        //   PAGE HTML + CSS + JS
        // ====================================================================
        private String getHTMLPage() {
            return """
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Centre de Formation</title>

    <style>
        body {
            font-family: Arial, sans-serif;
            background: #f4f4f4;
            margin: 0;
            padding: 0;
        }

        header {
            background: #2c3e50;
            padding: 20px;
            text-align: center;
            color: white;
            font-size: 28px;
            font-weight: bold;
        }

        nav {
            background: #34495e;
            display: flex;
            justify-content: center;
            gap: 20px;
            padding: 10px;
        }

        nav button {
            background: #1abc9c;
            border: none;
            padding: 10px 20px;
            font-size: 16px;
            cursor: pointer;
            color: white;
            border-radius: 5px;
        }

        nav button:hover {
            background: #16a085;
        }

        #content {
            padding: 20px;
        }

        h2 {
            color: #2c3e50;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            background: white;
        }

        th, td {
            padding: 10px;
            border: 1px solid #ddd;
        }

        th {
            background: #1abc9c;
            color: white;
        }
    </style>

    <script>
    async function charger(endpoint, title) {
        const res = await fetch(endpoint);
        const data = await res.json();

        let html = `<h2>${title}</h2><table><tr>`;

        if (data.length === 0) {
            document.getElementById("content").innerHTML =
                "<h3>Aucune donnée trouvée.</h3>";
            return;
        }

        // En-têtes dynamiques
        Object.keys(data[0]).forEach(k => {
            html += `<th>${k}</th>`;
        });
        html += "</tr>";

        for (let item of data) {
            html += "<tr>";
            Object.values(item).forEach(v => {
                html += `<td>${v}</td>`;
            });
            html += "</tr>";
        }

        html += "</table>";
        document.getElementById("content").innerHTML = html;
    }
    </script>

</head>

<body>

<header>Plateforme du Centre de Formation</header>

<nav>
    <button onclick="charger('/api/formations','Liste des Formations')">Formations</button>
    <button onclick="charger('/api/apprenants','Liste des Apprenants')">Apprenants</button>
    <button onclick="charger('/api/formateurs','Liste des Formateurs')">Formateurs</button>
    <button onclick="charger('/api/sessions','Liste des Sessions')">Sessions</button>
</nav>

<div id="content">
    <h2>Bienvenue 🎓</h2>
    <p>Choisissez une catégorie dans le menu ci-dessus.</p>
</div>

</body>
</html>
""";
        }
        // ====================== FIN DE MiniServer ============================
    } // ← fermeture de la classe MiniServer


    // ========================================================================
    //   CLASSE PRINCIPALE : SiteWebComplet
    // ========================================================================

    private final CentreFormationManager manager;

    public SiteWebComplet() {
        this.manager = CentreFormationManager.getInstance();
        chargerDonneesDemo();
    }

    // ========================================================================
    //   CHARGEMENT DES DONNÉES EXEMPLE (automatique au lancement)
    // ========================================================================
    private void chargerDonneesDemo() {

        // ------------------ Formations ------------------
        manager.ajouterFormation(1, "Java Débutant", 30, "INFORMATIQUE");
        manager.ajouterFormation(2, "Marketing Digital", 20, "MARKETING");
        manager.ajouterFormation(3, "Excel Avancé", 15, "BUREAUTIQUE");

        // ------------------ Apprenants ------------------
        manager.ajouterApprenant(1, "Hassan", "El Yousfi", "hassan@mail.com");
        manager.ajouterApprenant(2, "Nadia", "Karimi", "nadia@mail.com");
        manager.ajouterApprenant(3, "Youssef", "Bennani", "youssef@mail.com");

        // ------------------ Formateurs ------------------
        manager.ajouterFormateur(1, "Rachid", "Lahlou");
        manager.ajouterFormateur(2, "Leila", "Mouh");
        manager.ajouterFormateur(3, "Amine", "Saber");

        // ------------------ Sessions ------------------
        manager.ajouterSession(
                1,
                1,   // formation id
                1,   // formateur id
                LocalDate.parse("2025-01-10"),
                LocalDate.parse("2025-01-20"),
                20
        );

        manager.ajouterSession(
                2,
                2,
                2,
                LocalDate.parse("2025-02-01"),
                LocalDate.parse("2025-02-15"),
                15
        );
    }
    // ========================================================================
    //   CONSTRUIT LA PAGE HTML COMPLETE
    // ========================================================================
    private String buildFinalPage() {
        return """
        HTTP/1.1 200 OK
        Content-Type: text/html; charset=UTF-8

        <!DOCTYPE html>
        <html lang="fr">
        <head>
            <meta charset="UTF-8">
            <title>Centre de Formation</title>

            <style>
                body {
                    font-family: Arial, sans-serif;
                    background: #eef2f3;
                    margin: 0;
                    padding: 0;
                }
                header {
                    background: #1e3c72;
                    padding: 20px;
                    color: white;
                    text-align: center;
                    font-size: 26px;
                    font-weight: bold;
                }
                nav {
                    background: #2a5298;
                    display: flex;
                    justify-content: center;
                    gap: 20px;
                    padding: 15px;
                }
                nav button {
                    background: #00c6ff;
                    border: none;
                    padding: 10px 20px;
                    border-radius: 6px;
                    font-size: 15px;
                    cursor: pointer;
                    color: #fff;
                    font-weight: bold;
                    transition: 0.3s;
                }
                nav button:hover {
                    background: #0072ff;
                }
                #content {
                    padding: 30px;
                }
                table {
                    width: 100%;
                    border-collapse: collapse;
                    margin-top: 20px;
                    background: white;
                    border-radius: 6px;
                    overflow: hidden;
                }
                th {
                    background: #0072ff;
                    padding: 12px;
                    color: white;
                    text-align: left;
                }
                td {
                    padding: 10px;
                    border-bottom: 1px solid #ddd;
                }
            </style>

            <script>
                async function charger(endpoint, title) {
                    const res = await fetch(endpoint);
                    const data = await res.json();

                    let html = `<h2>${title}</h2><table><tr>`;

                    if (data.length === 0) {
                        document.getElementById("content").innerHTML =
                            "<h3>Aucune donnée trouvée.</h3>";
                        return;
                    }

                    Object.keys(data[0]).forEach(k => {
                        html += `<th>${k}</th>`;
                    });
                    html += "</tr>";

                    for (let item of data) {
                        html += "<tr>";
                        Object.values(item).forEach(v => {
                            html += `<td>${v}</td>`;
                        });
                        html += "</tr>";
                    }

                    html += "</table>";
                    document.getElementById("content").innerHTML = html;
                }
            </script>

        </head>

        <body>

            <header>Plateforme du Centre de Formation</header>

            <nav>
                <button onclick="charger('/api/formations','Liste des Formations')">Formations</button>
                <button onclick="charger('/api/apprenants','Liste des Apprenants')">Apprenants</button>
                <button onclick="charger('/api/formateurs','Liste des Formateurs')">Formateurs</button>
                <button onclick="charger('/api/sessions','Liste des Sessions')">Sessions</button>
            </nav>

            <div id="content">
                <h2>Bienvenue 👋</h2>
                <p>Sélectionne un bouton pour afficher les données.</p>
            </div>

        </body>
        </html>
        """;
    }


    // ========================================================================
    //   ENVOI HTML ET JSON
    // ========================================================================
    private void sendHtml(BufferedWriter writer, String html) throws IOException {
        writer.write(html);
        writer.flush();
    }

    private void sendJson(BufferedWriter writer, String json) throws IOException {
        writer.write("""
        HTTP/1.1 200 OK
        Content-Type: application/json; charset=UTF-8

        """ + json);
        writer.flush();
    }


    // ========================================================================
    //   LANCEMENT DU SERVEUR
    // ========================================================================
    public void startServer() throws Exception {
        MiniServer server = new MiniServer(8080, manager);
        server.start();
    }

    // ========================================================================
    //   MAIN
    // ========================================================================
    public static void main(String[] args) throws Exception {
        new SiteWebComplet().startServer();
    }
}
