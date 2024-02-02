package assignment.pingpong.domain.user;

import assignment.pingpong.domain.BaseEntity;
import assignment.pingpong.domain.UserRoom;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "\"user\"")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer fakerId;
    private String name;
    private String email;
    @Enumerated(EnumType.STRING)
    private Status status;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserRoom> userRooms = new ArrayList<>();


    public boolean isActive() {
        return this.status == Status.ACTIVE;
    }
}
