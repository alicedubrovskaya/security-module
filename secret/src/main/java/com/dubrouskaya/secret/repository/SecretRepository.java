package com.dubrouskaya.secret.repository;

import com.dubrouskaya.secret.model.Secret;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecretRepository extends JpaRepository<Secret, Long> {
    Secret findByHash(String hash);

    void deleteByHash(String hash);
}
