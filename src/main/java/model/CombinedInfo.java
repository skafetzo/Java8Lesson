package model;

import java.util.Optional;

public class CombinedInfo {

    private Optional<Person> person;
    Long amount;

    public Optional<Person> getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = Optional.ofNullable(person);
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public CombinedInfo(Optional Person, Long amount){

        this.person = Person;
        this.amount = amount;


    }

}
