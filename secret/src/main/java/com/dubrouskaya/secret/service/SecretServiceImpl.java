package com.dubrouskaya.secret.service;

import com.dubrouskaya.secret.model.Secret;
import com.dubrouskaya.secret.repository.SecretRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SecretServiceImpl implements SecretService {
    @Autowired
    private SecretRepository repository;

    @Override
    public String saveSecretData(String secretText) {
        final String secretDataHash = String.valueOf(secretText.hashCode());
        Secret secret = Secret.builder().text(secretText).hash(secretDataHash).build();
        repository.save(secret);
        return secretDataHash;
    }

    @Override
    public String getSecretData(String hash) {
        return repository.findByHash(hash).getText();
    }

    @Override
    @Transactional
    public void removeSecretData(String hash) {
        repository.deleteByHash(hash);
    }
}
