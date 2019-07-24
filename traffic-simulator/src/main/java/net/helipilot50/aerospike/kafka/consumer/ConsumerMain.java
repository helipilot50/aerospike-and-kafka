package net.helipilot50.aerospike.kafka.consumer;

import com.aerospike.client.AerospikeClient;
import com.aerospike.client.Bin;
import com.aerospike.client.Key;
import com.aerospike.client.Record;
import com.aerospike.client.Operation;

import net.helipilot50.aerospike.kafka.Constants;


/**
 * Consumer of Kafka messages
 *
 */
public class ConsumerMain {

    public static void main(String[] args) {
        System.out.println("Aerospike Kafka Consumer");
        ConsumerMain consumer = new ConsumerMain();
    }

    public AerospikeClient asClient;
    

    public ConsumerMain() {
        this.asClient = new AerospikeClient("localhost", 3000);
    }

}
