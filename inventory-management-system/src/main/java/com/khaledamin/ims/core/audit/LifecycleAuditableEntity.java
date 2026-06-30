package com.khaledamin.ims.core.audit;

import com.khaledamin.ims.identity.core.model.ActorIdentity;
import com.khaledamin.ims.identity.core.model.Actor;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@SuperBuilder
@NoArgsConstructor
@MappedSuperclass
public abstract class LifecycleAuditableEntity extends AuditableEntity {


    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(
                    name = "actorType",
                    column = @Column(
                            name = "deleted_by_actor_type",
                            insertable = false
                    )
            ),
            @AttributeOverride(
                    name = "actorCode.value",
                    column = @Column(
                            name = "deleted_by_actor_code",
                            insertable = false
                    )
            )
    })
    private ActorIdentity deletedBy;


    protected void delete(Actor actor) {

        this.deletedAt = LocalDateTime.now();
        this.deletedBy = actor.getActorIdentity();
    }
}
