/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.g905.bars;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;

/**
 *
 * @author g905
 */
public class DocumentsController {
    
    public String getJson() throws Exception {
        URL url = new URL("http://localhost:9090/spring/getAll");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        
        con.getResponseCode(); // 200
        
        BufferedReader in = new BufferedReader(
            new InputStreamReader(con.getInputStream()));
          String inputLine;
          StringBuffer content = new StringBuffer();
          while ((inputLine = in.readLine()) != null) {
              content.append(inputLine);
          }
          in.close();
        
        con.disconnect();
        return content.toString();
    }
    
    public List<Document> getAllDocuments() {
        List<Document> allDocs = new ArrayList<>();
        String jsonString = "";
        try {
            jsonString = getJson();
        }
        catch (Exception e) {
            
        }
        JSONArray ja = new JSONArray(jsonString);
        
        for (int i = 0; i < ja.length(); i++) {
            Document doc = new Document();
            doc.fromJson(ja.getJSONObject(i));
            allDocs.add(doc);
        }
        
        return allDocs;
    }
    
}
