package com.dubrouskaya.secret.controller;

import com.dubrouskaya.secret.service.SecretService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RequestMapping("/secret")
@RestController
public class SecretController {
    @Autowired
    private SecretService secretService;

    @GetMapping("/getSecretForm")
    public ModelAndView getSecretForm() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("secretForm");

        return mav;
    }

    @PostMapping("/saveSecretData")
    public ModelAndView saveSecretData(@RequestParam("secretText") String secretText) {
        String secretDataHash = secretService.saveSecretData(secretText);

        String link = prepareOneTimeLink(secretDataHash);

        ModelAndView mav = new ModelAndView();
        mav.setViewName("oneTimeLink");
        mav.addObject("notify","This link will work only once!");
        mav.addObject("oneTimeLink",link);

        return mav;
    }

    private String prepareOneTimeLink(String secretDataHash) {
        String serviceUri = ServletUriComponentsBuilder.fromCurrentServletMapping().build().toString();

        return serviceUri + "/secret/getSecretData/" + secretDataHash;
    }

    @GetMapping(value = "/getSecretData/{secretDataHash}")
    public String getSecretData(@PathVariable("secretDataHash") String secretDataHash) {
        String secretData = secretService.getSecretData(secretDataHash);
        secretService.removeSecretData(secretDataHash);
        return secretData;
    }
}
