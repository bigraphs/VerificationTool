package it.uniud.mads.jlibbig.proverif.parseinput.records;

import java.util.List;

/**
 * This class implement a list of Integrative-Json's Instantiation
 */
public class Instantiations {

    String nodeID;
    List<Instantiation> nodeInstantiations;

    /**
     * @param nodeID             the node's Id
     * @param nodeInstantiations the node's instantiations
     */
    public Instantiations(String nodeID, List<Instantiation> nodeInstantiations) {
        this.nodeID = nodeID;
        this.nodeInstantiations = nodeInstantiations;
    }

    /**
     * @return the nodeID
     */
    public String getNodeID() {
        return nodeID;
    }

    /**
     * @return the nodeInstantiations
     */
    public List<Instantiation> getNodeInstantiations() {
        return nodeInstantiations;
    }
}
