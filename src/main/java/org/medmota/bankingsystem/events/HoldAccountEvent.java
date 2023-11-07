package org.medmota.bankingsystem.events;

public class HoldAccountEvent extends BaseEvent<String>{
    public final String status;
    public String errorMsg;

    public HoldAccountEvent(String id, String status, String errorMsg) {
        super(id);
        this.status = status;
        this.errorMsg = errorMsg;
    }
}
