package com.dubrouskaya.secret.service;

public interface SecretService {
    String saveSecretData(String secretText);

    String getSecretData(String secretDataHash);

    void removeSecretData(String secretDataHash);
}
