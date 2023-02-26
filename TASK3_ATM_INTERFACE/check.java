import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.sql.SQLOutput;
import java.util.*;

class check{
    static void checkbalance(){
        System.out.println("------------------");
        System.out.println("The available balance in the bank account :");
        ATM.showbalance();
        System.out.println("---------------------------");
        ATM.prompt();
    }
}
