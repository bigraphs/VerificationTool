package it.uniud.mads.jlibbig.proverif.parseinput.records;

/**
 * This class implement a record for the Integrative-Json's Object
 */
public abstract class Record {

    private String id;
    private String contract;

    /**
     * @param id
     * @param contract
     */
    public Record(String id, String contract) {
        this.id = id;
        this.contract = contract;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @return the contract
     */
    public String getContract() {
        return contract;
    }
}