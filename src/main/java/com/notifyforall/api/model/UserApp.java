package com.notifyforall.api.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name = "user_app")
public class UserApp implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String username;

    @Column
    private String email;

    @Column(name = "company_name")
    private String companyName;

    @Column
    private String phone;

    @Column
    private String address;

    @Column
    private String password;

    @Column(name = "automatic_login")
    private Boolean automaticLogin;

    @OneToMany(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_user_app", nullable = true)
    private List<App> apps;
}
