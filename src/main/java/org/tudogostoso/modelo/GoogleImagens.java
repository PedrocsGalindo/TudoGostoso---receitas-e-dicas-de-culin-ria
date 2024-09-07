package org.tudogostoso.modelo;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GoogleImagens {
    private static final String API_KEY = "106151324710272841288";
    private static final String CX ="702669bb08f8e4a1f";

    // Método para buscar imagens no Google
    public static String buscarImagem(String consulta) throws Exception {
        String urlString = "https://www.googleapis.com/customsearch/v1?q=" + consulta +
                "&cx=" + CX + "&key=" + API_KEY + "&searchType=image&num=1";
        URL url = new URL(urlString);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder content = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }

        in.close();
        con.disconnect();

        // Parseando o JSON retornado
        JSONObject json = new JSONObject(content.toString());
        JSONArray items = json.getJSONArray("items");
        if (items.length() > 0) {
            // Retorna o link da primeira imagem encontrada
            String linkImagem = items.getJSONObject(0).getString("link");
            return linkImagem;
        } else {
            throw new Exception("Nenhuma imagem encontrada");
        }
    }
}
