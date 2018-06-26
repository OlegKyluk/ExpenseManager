package com.expenseManager.fixer.api.service;

import java.io.IOException;

public interface ConnectorService {
    StringBuffer sendRequest() throws IOException;
}
