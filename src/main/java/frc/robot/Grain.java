package frc.robot;

public class Grain {
    public Procedure procedure; //will be a void method
    public TC tCondition; //will be a method that returns a boolean
    public Procedure tProcedure; //will be a void method
    public boolean dead;
    public boolean active = false;
    public boolean checkmark = true;
     
    public Grain(Procedure procedure, TC tCondition, Procedure tProcedure){
        this.procedure = procedure;
        this.tCondition = tCondition;
        this.tProcedure = tProcedure;
    }

    //Checkmarks should come from the field
    public Grain(Procedure procedure, TC tCondition, Procedure tProcedure, Boolean checkmark){
        this.procedure = procedure;
        this.tCondition = tCondition;
        this.tProcedure = tProcedure;
        this.checkmark = checkmark;
    }

    public void run(){
        if(checkmark){
            if(!tCondition.ask()) {
                //active = true;

                procedure.run();
            }
            else{
                tProcedure.run();
                dead = true;
            }
        }
    }  
}
        

 

