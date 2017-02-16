/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package fsm;

/**
 *
 * @author eshan
 */
import java.util.*;

public class node {

    public ArrayList<Transition> list = new ArrayList();
    public boolean isAccept = false;

    public node getNext(String str) {
        for (Transition t : list) {
            if ((t.s).equals(str)) {
                return t.n;
            }
        }
        return null;
    }

    public void addTransition(Transition t) {
        this.list.add(t);
    }
}
