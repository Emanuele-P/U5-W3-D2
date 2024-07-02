package ep2024.u5_w3_d2.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "employees")
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = "devices")
public class Employee {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private UUID id;

    private String username;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;

    private String email;
    private String password;

    @Column(name = "avatar_url")
    private String avatarURL;

    @OneToMany(mappedBy = "employee")
    private List<Device> devices = new ArrayList<>();

    public Employee(String username, String firstName, String lastName, String email, String password, String avatarURL) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.avatarURL = avatarURL;
    }
}
