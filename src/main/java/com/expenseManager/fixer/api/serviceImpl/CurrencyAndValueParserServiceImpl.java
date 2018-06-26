package com.expenseManager.fixer.api.serviceImpl;

import com.expenseManager.fixer.api.service.CurrencyAndValueParserService;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CurrencyAndValueParserServiceImpl implements CurrencyAndValueParserService {

    @Override
    public Map<String, String> parse(StringBuffer response) throws JSONException {
        JSONObject jsonObject = new JSONObject(response.toString()).getJSONObject("rates");
        Map<String, String> currencyMap = new HashMap<>();
        for (int i = 0; i < jsonObject.length(); i++) {
            JSONArray names = jsonObject.names();
            String currency = names.get(i).toString();
            String value = jsonObject.get(currency).toString();
            currencyMap.put(currency, value);
        }
        return currencyMap;
    }
}
