package com.trepudox.rottenitaumatoes.dataprovider.model;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
@EntityListeners(AuditingEntityListener.class)
public class User implements Serializable {

    private static final long serialVersionUID = -6898167379430660643L;

    //TODO: analisar validation e PROFILE em ENUM

    @Id
    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "profile")
    private String profile;

    @Column(name = "score")
    private int score;

    @CreatedDate
    @Column(name = "creation_date_time")
    private LocalDateTime creationDateTime;

}
