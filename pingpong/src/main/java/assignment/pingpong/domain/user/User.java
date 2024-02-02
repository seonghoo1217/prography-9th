package assignment.pingpong.domain.user;

import assignment.pingpong.domain.BaseEntity;
import assignment.pingpong.domain.UserRoom;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
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

    public User(Integer fakerId, String name, String email, Status status, LocalDateTime createAt, LocalDateTime updateAt) {
        this.fakerId = fakerId;
        this.name = name;
        this.email = email;
        this.status = status;
        this.createdAt = createAt;
        this.updatedAt = updateAt;
    }
}
