/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package state;

import java.io.Serializable;

/**
 *
 * @author Riccardo
 */
public class Predicate implements Serializable{
    
    public String pred, subj, obj;
    
    public Predicate(String p, String s, String o){
        this.pred = p;
        this.subj = s;
        this.obj = o;
    }
    
    public Predicate(String p, String s){
        this.pred = p;
        this.subj = s;
        this.obj = "_";
    }
    
    @Override
    public boolean equals(Object o){
        if (o==null){
            return false;
        }
        if (!(o instanceof Predicate)){
            return false;
        }
        Predicate p = (Predicate)o;
        return (this.pred.equals(p.pred) && this.subj.equals(p.subj) && this.obj.equals(p.obj));
    }
    
    @Override
    public String toString(){
        return (pred+"("+subj+","+obj+")");
    }   
        
}
