package com.khaledamin.ims.core.audit;

import com.khaledamin.ims.identity.core.model.ActorIdentity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@SuperBuilder
@NoArgsConstructor
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AuditableEntity {

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @CreatedBy
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(
                    name = "actorType",
                    column = @Column(
                            name = "created_by_actor_type",
                            nullable = false,
                            updatable = false
                    )
            ),
            @AttributeOverride(
                    name = "actorCode.value",
                    column = @Column(
                            name = "created_by_actor_code",
                            nullable = false,
                            updatable = false
                    )
            )
    })
    private ActorIdentity createdBy;




    @LastModifiedDate
    @Column(name = "last_modified_at", insertable = false)
    private LocalDateTime lastModifiedAt;

    @LastModifiedBy
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(
                    name = "actorType",
                    column = @Column(
                            name = "last_modified_by_actor_type",
                            insertable = false
                    )
            ),
            @AttributeOverride(
                    name = "actorCode.value",
                    column = @Column(
                            name = "last_modified_by_actor_code",
                            insertable = false
                    )
            )
    })
    private ActorIdentity lastModifiedBy;
}