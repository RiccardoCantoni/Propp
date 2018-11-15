/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proppFunction;

import java.io.Serializable;

/**
 *
 * @author Riccardo
 */
public enum NodeType implements Serializable{
    GOAL,
    ACTION,
    PERCEPTION,
    PERCEPTION_INTERNAL,
    INTERNAL,
    OUTCOME,
    EVENT,
    STATE,
    NONE
}
