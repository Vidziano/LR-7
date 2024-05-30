package engine;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import compute.Compute;
import compute.Task;

public class ComputeEngine implements Compute {

    public ComputeEngine() {
        super();
    }

    @Override
    public <T> T executeTask(Task<T> t) throws RemoteException {
        return t.execute();
    }

    public static void main(String[] args) {
        try {
            // Створення і запуск RMI реєстру на стандартному порту 1099
            LocateRegistry.createRegistry(1099);

            // Створення об'єкта ComputeEngine і експорт його як віддаленого об'єкта
            Compute engine = new ComputeEngine();
            Compute stub = (Compute) UnicastRemoteObject.exportObject(engine, 0);

            // Реєстрація віддаленого об'єкта у RMI реєстрі
            Registry registry = LocateRegistry.getRegistry();
            registry.rebind("Compute", stub);

            System.out.println("ComputeEngine is ready to work");
        } catch (RemoteException e) {
            System.err.println("ComputeEngine exception:");
            e.printStackTrace();
        }
    }
}
