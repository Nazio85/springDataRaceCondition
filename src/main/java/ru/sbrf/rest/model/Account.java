package ru.sbrf.rest.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Set;

@Entity
public class Account {
    @Id
    @GeneratedValue
    private long id;

    @NotNull
    private BigDecimal amount;


    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Transactions> transactions;

    public Account() {
        amount = BigDecimal.ZERO;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BigDecimal getBigDecimal() {
        return amount;
    }

    public void setBigDecimal(BigDecimal bigDecimal) {
        this.amount = bigDecimal;
    }

    public Set<Transactions> getTransactions() {
        return transactions;
    }

    public void setTransactions(Set<Transactions> transactions) {
        this.transactions = transactions;
    }
}
