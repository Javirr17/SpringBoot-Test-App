package com.example.test.repository;

import com.example.test.enums.UserStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SQLDelete;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
@SQLDelete(sql = "UPDATE users SET status = 'INACTIVO' WHERE id=?")
public class User {

    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    private Integer id;

    @Column(length = 50)
    private String name;

    @Column(length = 50)
    private String surname1;

    @Column(length = 50)
    private String surname2;

    private String email;

    @Column(name = "phone_number", length = 15)
    private String phoneNumber;

    @Column(length = 11)
    private String nif;

    private String nickname;

    private String password;

    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @Column(name = "entry_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date entryDate;

    @Column(name = "cancel_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date cancelDate;

    @Column(name = "modified_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedDate;

}

