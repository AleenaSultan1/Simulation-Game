package org.team12.states;

public enum EnemyStatus {

    // Enemy is not attacking yet
    PEACEFUL {
        public boolean canTransitionTo(EnemyStatus nextState) {
            // An in-progress transaction can move to PAYMENT_PENDING or be VOIDED
            return nextState == PEACEFUL;
        }
    },

    // Enemy is attacking the player
    HOSTILE {
        public boolean canTransitionTo(EnemyStatus nextState) {
            // An in-progress transaction can move to PAYMENT_PENDING or be VOIDED
            return nextState == DEAD || nextState == CURED;
        }
    },

    // Enemy is defeated
    DEAD {
        public boolean canTransitionTo(EnemyStatus nextState) {
            // COMPLETED is a terminal state; no transitions allowed
            return false;
        }
    },

    //Lily is cured
    CURED {
        public boolean canTransitionTo(EnemyStatus nextState) {
            return false;
        }
    }
    ;

    public abstract boolean canTransitionTo(EnemyStatus nextState);
}
