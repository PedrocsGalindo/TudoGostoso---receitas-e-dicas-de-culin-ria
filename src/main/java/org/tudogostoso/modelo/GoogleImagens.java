package org.tudogostoso.modelo;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class GoogleImagens {
    private static final String API_KEY = "106151324710272841288";
    private static final String CX = "702669bb08f8e4a1f";

    /**
     * Busca imagens no Google Custom Search API.
     *
     * @param consulta O termo de pesquisa para buscar imagens.
     * @param numImagens O número de imagens a ser retornado.
     * @return Uma lista de URLs de imagens.
     * @throws Exception Se houver um erro durante a chamada da API.
     */
    public static List<String> buscarImagens(String consulta, int numImagens) throws Exception {
        // Construa a URL com o número de imagens especificado
        String urlString = "https://www.googleapis.com/customsearch/v1?q=" + consulta +
                "&cx=" + CX + "&key=" + API_KEY + "&searchType=image&num=" + numImagens;
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

        JSONObject json = new JSONObject(content.toString());
        JSONArray items = json.getJSONArray("items");

        List<String> linksImagens = new ArrayList<>();
        for (int i = 0; i < items.length(); i++) {
            linksImagens.add(items.getJSONObject(i).getString("link"));
        }

        if (linksImagens.isEmpty()) {
            throw new Exception("Nenhuma imagem encontrada");
        }

        return linksImagens;
    }
}
