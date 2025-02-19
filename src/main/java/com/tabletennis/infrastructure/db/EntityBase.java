package com.tabletennis.infrastructure.db;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.proxy.HibernateProxy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public abstract class EntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @LastModifiedDate
    private LocalDateTime updatedAt;

    @Override
    public boolean equals(Object other) {
        if (other == null) return false;

        if (!(other instanceof HibernateProxy) && this.getClass() != other.getClass()) return false;

        return id == getIdentifier(other);
    }

    private Object getIdentifier(Object obj) {
        if (obj instanceof HibernateProxy) {
            return ((HibernateProxy) obj).getHibernateLazyInitializer().getIdentifier();
        } else {
            return ((EntityBase) obj).getId();
        }
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
