package spring.hotel.common.persistance.to;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * Created by Yayheniy_Lepkovich on 10/28/2016.
 */

@Entity
@Table(name = "role")
public @Data class Role implements Serializable {
    @Id
    @Column(name = "roleId", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long roleId;
    @Column(name = "nameRole", nullable = false)
    private String nameRole;
    @Column(name = "description")
    private String description;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "roles", cascade = CascadeType.ALL)
    private List<Account> accounts;
}
