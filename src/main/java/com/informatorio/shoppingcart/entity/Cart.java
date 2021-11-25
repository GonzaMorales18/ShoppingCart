package com.informatorio.shoppingcart.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


import com.fasterxml.jackson.annotation.JsonIgnore;


import org.hibernate.annotations.CreationTimestamp;

@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
    @CreationTimestamp
    private LocalDateTime creationDate;
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartLine> cartLines = new ArrayList<>();

    private String device;
    private boolean close;
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public LocalDateTime getCreationDate() {
        return creationDate;
    }
    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }
    public boolean isClose() {
        return close;
    }
    public void setClose(boolean close) {
        this.close = close;
    }

    public String getDevice() {
        return device;
    }
    public void setDevice(String device) {
        this.device = device;
    }

    public List<CartLine> getCartLines() {
        return cartLines;
    }

    public void addCartLine(CartLine cartLine) {
        cartLines.add(cartLine);
        cartLine.setCart(this);
    }

    public void removeCartLine(CartLine cartLine) {
        cartLines.remove(cartLine);
        cartLine.setCart(null);
    }

    public void setCartLines(List<CartLine> cartLines) {
        this.cartLines = cartLines;
    }

    @Override
    public String toString() {
        return "Cart [close=" + close + ", creationDate=" + creationDate + ", device=" + device + ", id=" + id
                + ", user=" + user + "]";
    }

}
