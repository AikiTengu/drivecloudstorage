package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialsMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import org.springframework.stereotype.Service;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CredentialService {
    private CredentialsMapper credentialsMapper;
    private EncryptionService encryptionService;

    public CredentialService(CredentialsMapper credentialsMapper, EncryptionService encryptionService) {
        this.credentialsMapper = credentialsMapper;
        this.encryptionService = encryptionService;
    }

    public List<Credential> getCredentials(int userId) {
        List<Credential> credentials = credentialsMapper.getCredential(userId);
        return credentials.stream().map(this::decrypt).collect(Collectors.toList());
    }

    public void insert(Credential credential) {
        credentialsMapper.insert(encrypt(credential));
    }

    public void updateCredential(Credential credential) {
        credentialsMapper.update(encrypt(credential));
    }

    public void deleteCredential(int credentialId) {
        credentialsMapper.delete(credentialId);
    }

    private Credential encrypt(Credential credential) {
        SecureRandom random = new SecureRandom();
        byte[] bkey = new byte[16];
        random.nextBytes(bkey);
        String key = Base64.getEncoder().encodeToString(bkey);
        credential.setKey(key);
        credential.setPassword(encryptionService.encryptValue(credential.getPassword(), key));
        return credential;
    }

    private Credential decrypt(Credential credential) {
        credential.setPassworddc(encryptionService.decryptValue(credential.getPassword(), credential.getKey()));
        return credential;
    }
}