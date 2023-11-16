package org.bs.jpa.domain;

import java.sql.Timestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "persistent_logins")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class PersistentLogins {

    @Id
    private String series;
    private String username;
    private String token;
    private Timestamp lastUsed;
    
}
