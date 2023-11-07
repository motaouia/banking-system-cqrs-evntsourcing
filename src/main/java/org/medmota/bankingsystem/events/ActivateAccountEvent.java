package org.medmota.bankingsystem.events;

public class ActivateAccountEvent  extends BaseEvent<String>{
    public final String status;

    public ActivateAccountEvent(String id, String status) {
        super(id);
        this.status = status;
    }
}
