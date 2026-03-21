package code;

import java.util.ArrayList;
import java.util.List;

import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;

public class DBpedia {

	public static List<String> getActeursByTitre(String titre) {
        List<String> acteurs = new ArrayList<>();
        String sparqlQueryString = 
            "PREFIX dbo: <http://dbpedia.org/ontology/>\r\n" +
            "PREFIX dbp: <http://dbpedia.org/property/>\r\n" +
            "SELECT DISTINCT ?actor " +
            "WHERE { " +
            "  ?film a dbo:Film ; " +
            "        dbo:starring ?actor ; " +
            "        dbp:name \"" + titre + "\"@en . " +
            "}";

        try (QueryExecution queryExec = QueryExecutionFactory.sparqlService("http://dbpedia.org/sparql", QueryFactory.create(sparqlQueryString))) {
            ResultSet results = queryExec.execSelect();
            
            while (results.hasNext()) {
                QuerySolution soln = results.nextSolution();
                Resource actorResource = soln.getResource("actor");
                String actor = actorResource.getLocalName();
                acteurs.add(actor);
            }
        }

        return acteurs;
    }
    
	public static List<String> getDirectorByTitre(String titre) {
        List<String> directors = new ArrayList<>();

        String sparqlQueryString = 
            "PREFIX dbo: <http://dbpedia.org/ontology/>\r\n" +
            "PREFIX dbp: <http://dbpedia.org/property/>\r\n" +
            "SELECT DISTINCT ?director " +
            "WHERE { " +
            "  ?film a dbo:Film ; " +
            "        dbo:director ?director ; " +
            "        dbp:name \"" + titre + "\"@en . " +
            "}";
        
        try (QueryExecution queryExec = QueryExecutionFactory.sparqlService("http://dbpedia.org/sparql", QueryFactory.create(sparqlQueryString))) {
            ResultSet results = queryExec.execSelect();
            
            while (results.hasNext()) {
                QuerySolution soln = results.nextSolution();
                Resource directorResource = soln.getResource("director");
                String directorURI = directorResource.getURI();
                String[] parts = directorURI.split("/");
                String directorName = parts[parts.length - 1]; 
                directors.add(directorName);
            }
        }

        return directors;
    }
    
    
	public static List<String> getProducerByTitre(String titre) {
	    List<String> producers = new ArrayList<>();
	    
	    String sparqlQueryString = 
	        "PREFIX dbo: <http://dbpedia.org/ontology/>\r\n" +
	        "PREFIX dbp: <http://dbpedia.org/property/>\r\n" +
	        "SELECT DISTINCT ?producer\r\n" +
	        "WHERE {\r\n" +
	        "  ?film a dbo:Film ;\r\n" +
	        "        dbo:producer ?producer ;\r\n" +
	        "        dbp:name \"" + titre + "\"@en .\r\n" +
	        "}";

	    QueryExecution queryExec = QueryExecutionFactory.sparqlService("http://dbpedia.org/sparql", QueryFactory.create(sparqlQueryString));
	    ResultSet results = queryExec.execSelect();
	    
	    while (results.hasNext()) {
	        QuerySolution soln = results.nextSolution();
	        Resource producerResource = soln.getResource("producer");
	        if (producerResource != null) {
	            String producer = producerResource.getURI();
	            //System.out.println(producer);
                String[] parts = producer.split("/");
                String producerName = parts[parts.length - 1]; 
	            producers.add(producerName);
	        }
	    }

	    queryExec.close();
	    return producers;
	}



    
    
    
    public static List<String> getFilmsParActeur(String nomActeur) {
        List<String> films = new ArrayList<>();
        String sparqlQuery = 
            "PREFIX dbo: <http://dbpedia.org/ontology/>\n" +
            "PREFIX foaf: <http://xmlns.com/foaf/0.1/>\n" +
            "SELECT ?titre\n" +
            "WHERE {\n" +
            "  ?film a dbo:Film ;\n" +
            "        foaf:name ?titre ;\n" +
            "        dbo:starring ?a .\n" +
            "  ?a foaf:name \"" + nomActeur + "\"@en .\n" +
            "}";


        QueryExecution queryExec = QueryExecutionFactory.sparqlService("http://dbpedia.org/sparql", QueryFactory.create(sparqlQuery));
        ResultSet results = queryExec.execSelect();
        
        while (results.hasNext()) {
            QuerySolution soln = results.nextSolution();
            Literal filmTitleLiteral = soln.getLiteral("titre");
            if (filmTitleLiteral != null) {
                String filmTitle = filmTitleLiteral.getString();
                films.add(filmTitle);
            }
        }

        queryExec.close();
        return films;
    }
    
}
