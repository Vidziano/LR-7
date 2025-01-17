package client;

import compute.Compute;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.math.BigDecimal;

public class ComputePi {
    public static void main(String[] args) {
        try {
            String host = (args.length < 1) ? null : args[0];
            Registry registry = LocateRegistry.getRegistry(host);
            Compute comp = (Compute) registry.lookup("Compute");
            Pi task = new Pi(Integer.parseInt(args[1]));
            BigDecimal pi = comp.executeTask(task);
            System.out.println(pi);
        } catch (Exception e) {
            System.err.println("ComputePi exception:");
            e.printStackTrace();
        }
    }
}
