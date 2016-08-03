package ua.epam.spring.hometask.domain;

/**
 * Created by sergei-rudenkov on 27.7.16.
 */
public class UserAccount {
    private double prepaidMoney;

    public UserAccount(){
        // default starting sum
        prepaidMoney = 100;
    }

    public double getPrepaidMoney() {
        return prepaidMoney;
    }

    public void setPrepaidMoney(double prepaidMoney) {
        this.prepaidMoney = prepaidMoney;
    }
}
