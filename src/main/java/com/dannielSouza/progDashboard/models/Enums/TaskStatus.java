package com.dannielSouza.progDashboard.models.Enums;

public enum TaskStatus {

    WAITING_RESPONSE(1),
    ACCEPTED(2),
    CANCELED(3),
    DONE(4);

    private int code;

    private TaskStatus(int code){
        this.code = code;
    }

    public int getCode(){
        return code;
    }

    public static TaskStatus valueOf(int code){
        for(TaskStatus value : TaskStatus.values()){
            if(value.getCode() == code) {
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid TaskStatus code.");
    }
}
