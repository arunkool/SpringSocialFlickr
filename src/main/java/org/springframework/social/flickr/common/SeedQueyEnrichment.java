package org.springframework.social.flickr.common;


/*
import com.hp.hpl.jena.query.ParameterizedSparqlString;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;*/

public class SeedQueyEnrichment {

	public static void main( String[] args ) {
	/*    final String dbpedia = "http://dbpedia.org/sparql";
	    final ParameterizedSparqlString queryString
	      = new ParameterizedSparqlString(
	            "PREFIX geo: <http://www.w3.org/2003/01/geo/wgs84_pos#>"+
	            "PREFIX dbo: <http://dbpedia.org/ontology/>" +
	            "CONSTRUCT WHERE {" +
	            "  ?s a dbo:Alps peaks ." +
	            "  ?s geo:lat ?lat ." +
	            "  ?s geo:long ?long ." +
	            "}" );
	    Model allResults = ModelFactory.createDefaultModel();
	    for ( String mountain : new String[] { "Mount_Monadnock",
	"Mount_Lafayette" } ) {
	      queryString.setIri( "?s", "http://dbpedia.org/resource/" + mountain );
	      QueryExecution exec = QueryExecutionFactory.sparqlService(
	dbpedia, queryString.toString() );
	      Model results = exec.execConstruct();
	      allResults.add( results );
	    }
	    allResults.setNsPrefix( "geo", "http://www.w3.org/2003/01/geo/wgs84_pos#" );
	    allResults.setNsPrefix( "dbo", "http://dbpedia.org/ontology/" );
	    allResults.setNsPrefix( "dbr", "http://dbpedia.org/resource/" );
	    allResults.write( System.out, "N3" );*/
	  }}
 // end class
