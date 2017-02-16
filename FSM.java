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

public class FSM {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int rCount = sc.nextInt();
        sc.nextLine();
        node[] regEx = new node[rCount];
        for (int i = 0; i < regEx.length; ++i) {
            String s = sc.nextLine();
            node start = new node();
            node end = new node();
            end.isAccept = true;
            regEx[i] = start;
            node current = start;
            for (int j = 0; j < s.length(); ++j) {
                String temp = "";
                if (s.charAt(j) == '(') {
                    char val = '-';
                    for (int m = j + 1; m < s.length(); ++m) {
                        if (s.charAt(m) == ')') {
                            if (m + 1 < s.length()) {
                                //System.out.println(m + 1);
                                //System.out.println(s.length());
                                val = s.charAt(m + 1);
                            }
                            j = m;
                            break;
                        } else {
                            temp = temp + s.charAt(m);
                        }
                    }
                    if (val == '*') {
                        //node t = new node();
                        Transition tr = new Transition(current, temp);
                        // System.out.println(j);
                        current.addTransition(tr);
                        //current = t;
                    } else if (val == '+') {
                        node t = new node();
                        Transition tr = new Transition(t, temp);
                        // System.out.println(j);
                        current.addTransition(tr);
                        current = t;
                        Transition r = new Transition(current, temp);
                        current.addTransition(r);
                    } else if (val == '?') {
                        node t = new node();
                        Transition tr = new Transition(t, temp);
                        // System.out.println(j);
                        current.addTransition(tr);
                        tr = new Transition(t, "");
                        current.addTransition(tr);
                        current = t;

                    }
                }
//                if (j == s.length() - 1) {
//                    current.isAccept = true;
//                }

            }
            Transition t = new Transition(end, "");
            current.addTransition(t);
            node l = start;
            //int count = 0;
            while (!l.isAccept) {
                for (Transition tL : l.list) {
                    System.out.println(tL.s);
                    //    System.out.println(count);
                    //  ++count;
                    l = tL.n;
                }
            }
        }
        int sCount = sc.nextInt();
        sc.nextLine();
        for (int i = 0; i < sCount; ++i) {
            String s = sc.nextLine();
            for (int p = 0; p < regEx.length; ++p) {
                node current = regEx[p];
                if (current.isAccept) {
                    System.out.println("True");
                    break;
                } else if (current == null) {
                    System.out.println("False");
                }
                for (int j = 0; j < s.length(); ++j) {
                    String temp = "";
                    if (s.charAt(j) == '(') {
                        for (int m = j + 1; m < s.length(); ++m) {
                            if (s.charAt(m) == ')') {
                                j = m;
                                break;
                            } else {
                                temp = temp + s.charAt(m);
                            }
                        }
                    }
                    System.out.println(current.isAccept);
                    current = current.getNext(temp);
                }
            }

        }

    }
}
