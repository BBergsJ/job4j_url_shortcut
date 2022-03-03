package ru.job4j.shortcut.job4j_url_shortcut.domain;

import javax.validation.constraints.NotBlank;

public class DTOUserRequest {

    @NotBlank(message = "Site must be not empty")
    private String site;

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }
}
