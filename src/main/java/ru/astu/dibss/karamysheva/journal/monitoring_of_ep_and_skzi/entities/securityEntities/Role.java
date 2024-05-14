package ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.entities.securityEntities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "roles")
@Data
@RequiredArgsConstructor
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name_role")
    private String name;

    @Override
    public String getAuthority() {
        return getName();
    }
}
