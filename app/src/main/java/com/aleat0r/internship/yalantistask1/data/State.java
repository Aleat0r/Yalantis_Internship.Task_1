package com.aleat0r.internship.yalantistask1.data;

/**
 * Created by Aleksandr Kovalenko on 22.04.2016.
 */
public enum State {

    WAIT(0),
    IN_WORK(1),
    DONE(2);

    private int mStateValue;

    State(int mStateValue) {
        this.mStateValue = mStateValue;
    }

    public int getValue() {
        return mStateValue;
    }

    public static State getByValue(int value) {
        switch (value) {
            case 1:
                return IN_WORK;
            case 2:
                return DONE;
            default:
                return WAIT;
        }
    }

}
