package client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.math.BigDecimal;

import compute.Compute;

public class ComputePi {
    public static void main(String args[]) {
        try {
            Registry registry = LocateRegistry.getRegistry(args[0]);
            String name = "Compute";
            Compute comp = (Compute) registry.lookup(name);

            if (args[2].equals("pi")) {
                Pi task = new Pi(Integer.parseInt(args[1]));
                BigDecimal pi = comp.executeTask(task);
                System.out.println("Calculated Pi: " + pi);
            } else if (args[2].equals("e")) {
                E task = new E(Integer.parseInt(args[1]));
                BigDecimal e = comp.executeTask(task);
                System.out.println("Calculated e: " + e);
            } else {
                System.out.println("Unknown task. Use 'pi' or 'e' as the third argument.");
            }
        } catch (Exception e) {
            System.err.println("ComputePi exception:");
            e.printStackTrace();
        }
    }
}
