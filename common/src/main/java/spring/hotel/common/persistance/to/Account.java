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
@Table(name = "account")
public @Data class Account implements Serializable {
    @Id
    @Column(name = "accountId", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long accountId;
    @Column(name = "username", nullable = false)
    private String username;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "mail", nullable = false)
    private String mail;
//    @OneToOne(optional = false)
//    @JoinColumn(name="profileID", unique = true, nullable = true)
//    private Profile profile;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="account_role",
            joinColumns = @JoinColumn(name="accountId", referencedColumnName="accountId"),
            inverseJoinColumns = @JoinColumn(name="roleId", referencedColumnName="roleId")
    )
    private List<Role> roles;
}
