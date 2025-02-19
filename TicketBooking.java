import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class TicketBookingSystem {
    private int availableSeats = 10;
    private final Lock lock = new ReentrantLock();

    public void bookSeat(String customer, int priority) {
        Thread.currentThread().setPriority(priority); //Setting Priority
        lock.lock();
        try {
            if (availableSeats > 0) {
                System.out.println(customer + " booked a seat. Remaining seats: " + (--availableSeats));
            } else {
                System.out.println(customer + " could not book a seat (No seats available)");
            }
        } finally {
            lock.unlock();
        }
    }
}

// Extending Thread class

class Customer extends Thread {
    private final TicketBookingSystem system;
    private final String name;
    private final int priority;

    Customer(TicketBookingSystem system, String name, int priority) {
        this.system = system;
        this.name = name;
        this.priority = priority;
    }

    @Override
    public void run() {
        system.bookSeat(name, priority);
    }
}

public class TicketBooking {
    public static void main(String[] args) {
        TicketBookingSystem system = new TicketBookingSystem();

        Thread vip1 = new Customer(system, "VIP_1", Thread.MAX_PRIORITY);
        Thread vip2 = new Customer(system, "VIP_2", Thread.MAX_PRIORITY);
        Thread regular1 = new Customer(system, "User_1", Thread.NORM_PRIORITY);
        Thread regular2 = new Customer(system, "User_2", Thread.NORM_PRIORITY);
        Thread regular3 = new Customer(system, "User_3", Thread.NORM_PRIORITY);

        vip1.start();
        vip2.start();
        regular1.start();
        regular2.start();
        regular3.start();
    }
}
