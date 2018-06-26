package com.expenseManager.fixer.api.serviceImpl;

import com.expenseManager.fixer.api.service.ConnectorService;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class ConnectorServiceImpl implements ConnectorService {

    @Override
    public StringBuffer sendRequest() throws IOException {
        String url = "http://data.fixer.io/api/latest?access_key=a8f1dc2901186e07c83f8b610b5f658d";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response;
    }
}
