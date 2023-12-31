package br.com.mybudget.userdashboard.model.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TB_USER")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_USER")
    private long idUser;

    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;

    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;

    @Column(name = "DATE_OF_BIRTH", nullable = false)
    private Date dateOfBirth;

    @Column(name = "GENRE", nullable = false)
    private String gender;

    @Column(name = "CHILDREN_NUMBER", nullable = false)
    private int childrenNumber;

    @Column(name = "PHONE", nullable = false)
    private String phoneNumber;

    @Column(name = "EMAIL", nullable = false)
    private String email;

    @Column(name = "STATUS", nullable = false)
    private char status;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "STATUS_CIVIL", nullable = false)
    private char civilStatus;

    @Column(name = "CREATE_AT", nullable = false, insertable=false, updatable = false)
    private Date createAt;

    @Column(name = "UPDATE_AT", nullable = false, insertable=false, updatable = false)
    private Date updateAt;

}
