package spring.hotel.common.persistance.to;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Sw_DOn on 31.10.2016.
 */
@Entity
@Table(name = "profile")
public @Data class Profile implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "surname", nullable = false)
    private String surname;
    @Column(name = "sex")
    private String sex;
    @Column(name = "born", nullable = false)
    private Date born;
    @Column(name = "city")
    private String city;
    @Column(name = "vk")
    private String vk;
    @Column(name = "facebook")
    private String facebook;
    @Column(name = "photo")
    private String photo;
    @Column(name = "twitter")
    private String twitter;
    @Column(name = "about")
    private String about;

//    @OneToOne(optional = false, mappedBy="profile")
//    public Account acount;
}
