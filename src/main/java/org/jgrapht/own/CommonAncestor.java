package org.jgrapht.own;

import org.jgraph.graph.DefaultEdge;
import org.jgrapht.Graph;
import org.jgrapht.alg.NaiveLcaFinder;
import org.jgrapht.graph.SimpleDirectedGraph;
import org.jgrapht.io.DOTImporter;
import org.jgrapht.io.GraphImporter;
import org.jgrapht.io.ImportException;


import java.io.*;

public class CommonAncestor {
    public static void main(String args[]) throws IOException,ImportException{

        String fileName= args[0];             //Taking first argument as the dot file input
        String a= args[1];                    //Taking second argument as first person's name
        String b= args[2];                    //Taking third argument as second person's name

        String answer=findingAncestor(fileName,a,b);  //calling the function to find the common ancestor of the above given two persons

        System.out.println(answer);     //printing the common ancestor

    }

    //fuction to find the common ancestor from a file of two given people
    public static String findingAncestor(String fName,String vertexOne,String vertexTwo) throws IOException,ImportException{

        GraphImporter<String, DefaultEdge> importer = new DOTImporter<>(
                (label, attributes) -> label,
                (from, to, label, attributes) -> new DefaultEdge()
        );  //Makes an object of the DOTImporter interface which implements the GraphImporter interface

        Graph<String, DefaultEdge> result = new SimpleDirectedGraph<>(DefaultEdge.class);   //Makes an object of the SimpleDirectedGraph class that implements the Graph interface
        importer.importGraph(result, new FileInputStream(fName)); //Imports the dot file to the result graph



        NaiveLcaFinder naiveLcaFinder = new NaiveLcaFinder(result);   //creating an object to the NaiveLcaFinder

        String resultOne =(String)naiveLcaFinder.findLca(vertexOne,vertexTwo); //string declaration to store the common ancestor of the two persons using the NaiveLcaFinder class's findLca method

        return resultOne;    //returning the common ancestor string


    }

}
