package com.academy.automationpractice.ex;

public class TestScenario {
    String login;
    String password;
    String expectedMessage;
    String TypeScenario;

    public String getTypeScenario() {return TypeScenario;}

    public void setTypeScenario(String typeScenario) {TypeScenario = typeScenario;}

    public String getExpectedMessage() {
        return expectedMessage;
    }

    public void setExpectedMessage(String expectedMessage) {
        this.expectedMessage = expectedMessage;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }


    @Override
    public String toString() {
        return "TestScenario {" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", expectedMessage='" + expectedMessage + '\'' +
                ", TypScenario='" + TypeScenario + '\''+
                '}'+"\n";
    }
}
