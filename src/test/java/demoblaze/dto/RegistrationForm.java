package demoblaze.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RegistrationForm {
    @JsonProperty
    private String password;
    @JsonProperty
    private String username;
    public void setPassword(String password) {
        this.password = password;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }
    @Override
    public String toString() {
        return "RegistrationForm{" +
                "password='" + password + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
