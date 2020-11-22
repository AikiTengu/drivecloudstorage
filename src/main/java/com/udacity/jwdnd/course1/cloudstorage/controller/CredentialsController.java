package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller()

public class CredentialsController {
    private CredentialService credentialService;
    private UserService userService;

    public CredentialsController(CredentialService credentialService, UserService userService) {
        this.userService = userService;
        this.credentialService = credentialService;
    }

    @GetMapping("/credentials")
    public String getCredentials(Authentication authentication) {
        return "credentials";
    }

    @GetMapping("/credentials/delete")
    public String deleteCredential(@RequestParam("id") int credentialId) {
        credentialService.deleteCredential(credentialId);
        return "redirect:/result?delcredsuccess";
    }

    @PostMapping("/credentials")
    public String postCredentials (Credential credential, Authentication authentication){
        int userId = userService.getUserId(authentication.getName());
        credential.setUserId(userId);
        if (credential.getCredentialId() != null) {
            credentialService.updateCredential(credential);
            return "redirect:/result?editcredsuccess";
        }
        else {
            credentialService.insert(credential);
            return "redirect:/result?addcredsuccess";
        }
    }
}