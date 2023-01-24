package es.codeurjc.books.domain.port;

import java.util.Collections;
import java.util.List;


public class ShoppingCartDto {

    private Long id;

    private String userNick;

    private String status;

    private List<ShoppingCartDetailDto> details = Collections.emptyList();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserNick() {
        return userNick;
    }

    public void setUserNick(String userNick) {
        this.userNick = userNick;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ShoppingCartDetailDto> getDetails() {
        return details;
    }

    public void setDetails(List<ShoppingCartDetailDto> details) {
        this.details = details;
    }
}
