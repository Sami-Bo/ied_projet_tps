package code;

import javax.xml.xpath.*;

import java.io.StringReader;

import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.InputSource;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class Omdb {

private String resume;

public Omdb() {
}

public String getResume() {
return resume;
}

public void setResume(String titre) {
        String apiKey = "3162b60e";
        String apiUrl = "http://www.omdbapi.com/?apikey=" + apiKey + "&t=" + titre + "&r=xml";
        String plot;
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet httpGet = new HttpGet(apiUrl);
            try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                if (response.getStatusLine().getStatusCode() == 200) {
                    String xmlResponse = EntityUtils.toString(response.getEntity());
                    plot = extractPlotFromXML(xmlResponse);
                } else {
                    System.out.println("La requête a échoué avec le code de statut : " + response.getStatusLine().getStatusCode());
                    plot = "";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            plot = "";
        }
        this.resume = plot;
    }

private String extractPlotFromXML(String xml) {
    
    String plot = "";
    try {
        DocumentBuilderFactory fabrique = DocumentBuilderFactory.newInstance();
        DocumentBuilder parseur = fabrique.newDocumentBuilder();
        Document document = parseur.parse(new InputSource(new StringReader(xml)));

        XPathFactory xfabrique = XPathFactory.newInstance();
        XPath xpath = xfabrique.newXPath();

        XPathExpression exp = xpath.compile("/root/movie/@plot");
        String plotValue = (String) exp.evaluate(document, XPathConstants.STRING);

        if (!plotValue.isEmpty()) {
            plot = plotValue;
        } else {
            System.out.println("Résumé du film non trouvé.");
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return plot;
}
}