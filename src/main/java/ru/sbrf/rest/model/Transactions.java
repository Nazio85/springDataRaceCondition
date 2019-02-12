package ru.sbrf.rest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Transactions {
    @Id
    @GeneratedValue
    private long id;

    @NotNull
    private LocalDateTime created;

    @NotNull
    private BigDecimal bigDecimal;

    @NotNull
    private boolean isIncreased;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "ACCOUNT_ID", nullable = false)
    private Account account;

    public Transactions() {
        created = LocalDateTime.now();
    }

    public Transactions(Account account, BigDecimal bigDecimal, boolean isIncreased) {
        this.account = account;
        created = LocalDateTime.now();
        this.bigDecimal = bigDecimal;
        this.isIncreased = isIncreased;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public BigDecimal getBigDecimal() {
        return bigDecimal;
    }

    public void setBigDecimal(BigDecimal bigDecimal) {
        this.bigDecimal = bigDecimal;
    }

    public boolean isIncreased() {
        return isIncreased;
    }

    public void setIncreased(boolean increased) {
        isIncreased = increased;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transactions that = (Transactions) o;
        return id == that.id &&
                Objects.equals(created, that.created) &&
                Objects.equals(account, that.account);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, created, account);
    }
}
