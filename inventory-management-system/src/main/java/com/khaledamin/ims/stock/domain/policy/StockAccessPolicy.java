package com.khaledamin.ims.stock.domain.policy;

import com.khaledamin.ims.identity.core.model.Actor;
import com.khaledamin.ims.identity.core.model.ActorIdentity;
import com.khaledamin.ims.stock.domain.model.Stock;
import com.khaledamin.ims.stock.exception.StockPolicyException;
import org.springframework.stereotype.Component;

@Component
public class StockAccessPolicy {

    public void canView(Actor actor, Stock stock) {
        ActorIdentity actorIdentity = actor.getActorIdentity();

        if (!stock.getOrganization().ownedBy(actorIdentity)) {

            throw StockPolicyException.forbiddenView()
                    .withDebugDetails("problem","current actor is not owner for this organization")
                    .withDebugDetails("stockCode", stock.getCode())
                    .withDebugDetails("organizationCode", stock.getOrganization().getCode());
        }
    }

    public void canUpdate(Actor actor, Stock stock) {
        ActorIdentity actorIdentity = actor.getActorIdentity();

        if (!stock.getOrganization().ownedBy(actorIdentity)) {

            throw StockPolicyException.forbiddenUpdate()
                    .withDebugDetails("problem","current actor is not owner for this organization")
                    .withDebugDetails("stockCode", stock.getCode())
                    .withDebugDetails("organizationCode", stock.getOrganization().getCode());
        }
    }

    public void canDelete(Actor actor, Stock stock) {

        ActorIdentity actorIdentity = actor.getActorIdentity();

        if (!stock.getOrganization().ownedBy(actorIdentity)) {

            throw StockPolicyException.forbiddenDelete()
                    .withDebugDetails("problem", "current actor is not owner for this organization")
                    .withDebugDetails("stockCode", stock.getCode())
                    .withDebugDetails("organizationCode", stock.getOrganization().getCode());
        }
    }

    public void canRestock(Actor actor, Stock stock) {
        ActorIdentity actorIdentity = actor.getActorIdentity();

        if (!stock.getOrganization().ownedBy(actorIdentity)) {

            throw StockPolicyException.forbiddenRestock()
                    .withDebugDetails("problem","current actor is not owner for this organization")
                    .withDebugDetails("stockCode", stock.getCode())
                    .withDebugDetails("organizationCode", stock.getOrganization().getCode());
        }
    }

    public void canListBatches(Actor actor, Stock stock) {
        ActorIdentity actorIdentity = actor.getActorIdentity();

        if (!stock.getOrganization().ownedBy(actorIdentity)) {

            throw StockPolicyException.forbiddenListBatches()
                    .withDebugDetails("problem","current actor is not owner for this organization")
                    .withDebugDetails("stockCode", stock.getCode())
                    .withDebugDetails("organizationCode", stock.getOrganization().getCode());
        }
    }
}