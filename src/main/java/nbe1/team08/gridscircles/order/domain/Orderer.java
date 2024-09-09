package nbe1.team08.gridscircles.order.domain;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Orderer {

    private String email;
    private String address;
    private String postcode;

    public boolean isMine(String email) {
        return this.email.equals(email);
    }
}
