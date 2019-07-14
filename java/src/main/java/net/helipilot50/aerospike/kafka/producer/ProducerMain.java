package net.helipilot50.aerospike.kafka.producer;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.aerospike.client.AerospikeClient;
import com.aerospike.client.Bin;
import com.aerospike.client.Key;
import com.aerospike.client.Record;
import com.aerospike.client.Operation;

import net.helipilot50.aerospike.kafka.Constants;

/**
 * Producer of Aerospike operations
 *
 */
public class ProducerMain extends TimerTask {

    private static final List<User> users = new ArrayList<User>() {
        private static final long serialVersionUID = 408064932585810852L;

        {
            add(new User("bob-123", "Bob", 0L));
            add(new User("mary-456", "Mary", 0L));
            add(new User("sue-789", "Sue", 0L));
            add(new User("james-189", "James", 0L));
            add(new User("sarah-289", "Sarah", 0L));
            add(new User("mark-281", "Mark", 0L));
        }
    };

    private static User randomUser() {
        int index = (int)(Math.random() * users.size());
        return users.get(index);
    }

    public static void main(String[] args) {
        System.out.println("Aerospike Kafka Producer");
        ProducerMain producer = new ProducerMain();
        Timer timer = new Timer();
        timer.schedule(producer, 0, 5000);
    }

    public AerospikeClient asClient;

    public ProducerMain() {
        this.asClient = new AerospikeClient("localhost", 3000);
    }

    @Override
    public void run() {
        User user = randomUser();
        System.out.println("Writing to Aerospike: " + user.id);
        incrementVisits(user.id, user.name);
    }

    public User incrementVisits(String userId, String name) {

        // Create a key
        Key recordKey = new Key(Constants.NAMESPACE, Constants.RECORD_SET, userId);

        // Increment operations
        Bin nameBin = new Bin(Constants.NAME_BIN, name);
        Bin visitBin = new Bin(Constants.VISIT_BIN, 1);

        // https://www.aerospike.com/docs/client/java/usage/kvs/multiops.html#operation-specification
        Record record = asClient.operate(null, recordKey, Operation.add(visitBin), Operation.put(nameBin),
                Operation.get(Constants.NAME_BIN), Operation.get(Constants.VISIT_BIN));
        return new User(userId, record.getString(Constants.NAME_BIN), record.getLong(Constants.VISIT_BIN));
    }

    /**
     * close the Aerospike client on process termination
     */
    protected void finalize() {
        this.asClient.close();
        this.asClient = null;
    }

}
