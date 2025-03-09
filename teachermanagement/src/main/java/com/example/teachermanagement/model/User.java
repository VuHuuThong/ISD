package com.example.teachermanagement.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User extends BaseEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fullname", length = 100)
    private String fullName;

    @Column(name = "email", length = 10, nullable = false)
    private String email;

    @Column(name = "address", length = 200)
    private String address;

    @Column(name = "password", length = 200, nullable = false)
    private String password;

    @Column(name = "is_active")
    private boolean active;

    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private  com.example.teachermanagement.model.Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();// danh sách các simpleGrantedAuthority
        authorityList.add(new SimpleGrantedAuthority("ROLE_"+getRole().getName().toUpperCase()));// thêm các role của user và list
        //authorityList.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

        return authorityList;
    }
    @Override
    public String getUsername() {
        return email;
    } // trả vè giá trị trường đăng nhập

    @Override
    public boolean isAccountNonExpired() {//
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
