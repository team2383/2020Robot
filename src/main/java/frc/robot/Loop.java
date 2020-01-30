package frc.robot;

import java.util.ArrayList;
import frc.robot.Grain;

public class Loop {
    
    ArrayList<Grain> chain = new ArrayList<Grain>();

public void addGrain(Grain e){
    chain.add(e);
    seed();
}

public void seed(){
    maintence();
    passThrough();
}
//looks for dead grians
public void maintence(){
		for (int i = 0; i < chain.size(); i++) {
            if(chain.get(i).dead){
                chain.remove(i);
            }
	}
    
}
public void passThrough(){
    for (int i = 0; i < chain.size(); i++) {
        if(!chain.get(i).active){
            chain.get(i).run();
        }
}

}

}