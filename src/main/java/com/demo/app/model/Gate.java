package com.demo.app.model;

import lombok.AllArgsConstructor;

/**
 * we could also have just Gate class representing Entry and Exit gate,
 * but for this solution I have kept entrance and Exit separate.
 */
@AllArgsConstructor
public abstract class Gate {
    private int gateId;
}
