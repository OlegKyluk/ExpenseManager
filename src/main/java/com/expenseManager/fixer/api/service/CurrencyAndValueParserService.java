package com.expenseManager.fixer.api.service;

import org.json.JSONException;

import java.util.Map;

public interface CurrencyAndValueParserService {

    Map<String, String> parse(StringBuffer response) throws JSONException;
}
