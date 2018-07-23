package myUtils;

/**
 *
 * @author Riccardo
 */
public class Roulette {
    
    private float[] probabilities;
    private float total;
    private SharedRandom rnd;
    
    public Roulette(float[] probabilities){
        this.rnd = SharedRandom.getInstance();
        this.probabilities = probabilities;
        total=0;
        for (float f : probabilities){
            total+=f;
        }
    }
    
    public int extract(){
        float x = rnd.nextFloat()*total;
        float c =probabilities[0];
        for (int i=0; i<probabilities.length; i++, c+=probabilities[i]){
            if (c>=x){
                return i;
            }
                
        }
        return -1;
    }
        
}
