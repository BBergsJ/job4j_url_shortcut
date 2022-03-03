package ru.job4j.shortcut.job4j_url_shortcut.domain;

import javax.validation.constraints.NotBlank;

public class DTOUrl {

    @NotBlank(message = "Url must be not empty")
    private String url;
    private int count;

    public static DTOUrl of(String url, int count) {
        DTOUrl dtoUrl = new DTOUrl();
        dtoUrl.url = url;
        dtoUrl.count = count;
        return dtoUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
