package net.helipilot50.aerospike.kafka;
public interface Constants {
    public final String NAMESPACE = "test";
    public final String RECORD_SET = "record-with-counter";
    public final String NAME_BIN = "name-bin";
    public final String VISIT_BIN = "visit-bin";
    public final String SINGLE_COUNTER_BIN = "counter-bin";
    public final String SOURCE_TOPIC = "aerospike-sink:1:1";
    public final String SINK_TOPIC = "aerospike-source:1:1";
}