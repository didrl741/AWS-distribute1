package study.jpastudy.config.auth.dto;

import lombok.Getter;
import study.jpastudy.domain.user.MyUser;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {

    private String name;
    private String email;
    private String picture;

    public SessionUser(MyUser myUser) {
        this.name = myUser.getName();
        this.email = myUser.getEmail();
        this.picture = myUser.getPicture();
    }
}
