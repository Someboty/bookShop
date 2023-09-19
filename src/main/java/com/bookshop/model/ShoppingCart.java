package com.bookshop.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.HashSet;
import java.util.Set;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "carts")
@Getter
@Setter
@EqualsAndHashCode
public class ShoppingCart {
    @Id
    private Long id;

    @OneToOne
    @MapsId
    private User user;

    @OneToMany(mappedBy = "shoppingCart")
    @EqualsAndHashCode.Exclude
    private Set<CartItem> cartItems = new HashSet<>();
}
