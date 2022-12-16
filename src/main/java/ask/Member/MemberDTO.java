package ask.Member;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "MEMBER")
public class MemberDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String pw;
    private String nickname;

//    @Enumerated(EnumType.STRING)
    @Column(name ="role")
    private String role;

    /*public String setRole(Role role) {
        return String.valueOf(role);
    }
*/

    @Override
    public String toString() {
        return "MemberDTO{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", pw='" + pw + '\'' +
                ", nickname='" + nickname + '\'' +
                ", role=" + role +
                '}';
    }
}
