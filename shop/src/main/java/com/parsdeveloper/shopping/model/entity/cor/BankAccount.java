package com.parsdeveloper.shopping.model.entity.cor;

import com.parsdeveloper.shopping.model.entity.ApplicationSchema;
import com.parsdeveloper.shopping.model.entity.security.EffectiveModel;

import javax.persistence.*;

@Entity
@Table(name = "BANK_ACCOUNT")

public class BankAccount extends EffectiveModel<Long> {


    public static final String DEFAULT_IBAN = "IR000000000000000000000000";
    public static final String UNKNOWN_OWNER = "UNKNOWN";
    public final static String IBAN_PREFIX = "IR";

    private String accountNumber;
    private String accountOwner;
    private String iban;
    private String ibanPrefix;
    private Bank bank;
    private Person person;
    private BankAccountType bankAccountType;
    private String paymentCode;
    private AccountUsage accountUsage;
    private Boolean confirmFlag;
    private String confirmDescription;

    public static BankAccount.Builder getBuilder() {
        return new BankAccount.Builder();
    }

    @Id
    @Column(unique = true, nullable = false, precision = 19)
    public Long getId() {
        return super.getId();
    }

    @Column(name = "ACCOUNT_NUMBER", length = 25)
    public String getAccountNumber() {
        return this.accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    @Column(name = "ACCOUNT_OWNER", length = 120)
    public String getAccountOwner() {
        return this.accountOwner;
    }

    public void setAccountOwner(String accountOwner) {
        this.accountOwner = accountOwner;
    }

    @Column(name = "IBAN", length = 26)
    public String getIban() {
        return this.iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    @Column(name = "IBAN_PREFIX", length = 10)
    public String getIbanPrefix() {
        return ibanPrefix;
    }

    public void setIbanPrefix(String ibanPrefix) {
        this.ibanPrefix = ibanPrefix;
    }

    //bi-directional many-to-one association to Bank
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BANK_ID", nullable = false)
    public Bank getBank() {
        return this.bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    //bi-directional many-to-one association to Person
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PERSON_ID", nullable = false)
    public Person getPerson() {
        return this.person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BANK_ACCOUNT_TYPE_ID", nullable = false)
    public BankAccountType getBankAccountType() {
        return bankAccountType;
    }

    public void setBankAccountType(BankAccountType bankAccountType) {
        this.bankAccountType = bankAccountType;
    }

    @Column(name = "PAYMENT_CODE", length = 25)
    public String getPaymentCode() {
        return paymentCode;
    }

    public void setPaymentCode(String paymentCode) {
        this.paymentCode = paymentCode;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "ACCOUNT_USAGE")
    public AccountUsage getAccountUsage() {
        return accountUsage;
    }

    public void setAccountUsage(AccountUsage accountUsage) {
        this.accountUsage = accountUsage;
    }

    public Boolean getConfirmFlag() {
        return confirmFlag;
    }

    public void setConfirmFlag(Boolean confirmFlag) {
        this.confirmFlag = confirmFlag;
    }

    @Column(name = "CONFIRM_DESCRIPTION", length = 250, nullable = true)
    public String getConfirmDescription() {
        return confirmDescription;
    }

    public void setConfirmDescription(String confirmDescription) {
        this.confirmDescription = confirmDescription;
    }

    public enum AccountUsage {
        DAMAGE, ISSUE, TWISE, RECOVERY, RETURNED;
    }

    public static class Builder {
        BankAccount bankAccount;

        public Builder() {
            bankAccount = new BankAccount();
        }

        public Builder accountNumber(String accountNumber) {
            bankAccount.accountNumber = accountNumber;
            return this;
        }

        public Builder accountOwner(String accountOwner) {
            bankAccount.accountOwner = accountOwner;
            return this;
        }

        public Builder iban(String iban) {
            bankAccount.iban = iban;
            return this;
        }

        public Builder bank(Bank bank) {
            bankAccount.bank = bank;
            return this;
        }

        public Builder bankAccountType(BankAccountType bankAccountType) {
            bankAccount.bankAccountType = bankAccountType;
            return this;
        }

        public BankAccount build() {
            return bankAccount;
        }
    }
}
