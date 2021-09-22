package it.uniud.mads.jlibbig.proverif.parseinput;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import it.uniud.mads.jlibbig.core.Interface;

import it.uniud.mads.jlibbig.core.ldb.DirectedBigraph;
import it.uniud.mads.jlibbig.core.ldb.Node;
import it.uniud.mads.jlibbig.proverif.constants.Constants;
import it.uniud.mads.jlibbig.proverif.exceptions.NoChannelFound;
import it.uniud.mads.jlibbig.proverif.exceptions.NoInstanceFound;
import it.uniud.mads.jlibbig.proverif.exceptions.WrongPolarityException;
import it.uniud.mads.jlibbig.proverif.parseinput.records.BigraphInfo;
import it.uniud.mads.jlibbig.proverif.parseinput.records.Channel;
import it.uniud.mads.jlibbig.proverif.parseinput.records.Event;
import it.uniud.mads.jlibbig.proverif.parseinput.records.Function;
import it.uniud.mads.jlibbig.proverif.parseinput.records.Instantiation;
import it.uniud.mads.jlibbig.proverif.parseinput.records.Instantiations;
import it.uniud.mads.jlibbig.proverif.parseinput.records.MainProcess;
import it.uniud.mads.jlibbig.proverif.parseinput.records.Prologue;
import it.uniud.mads.jlibbig.proverif.parseinput.records.Query;
import it.uniud.mads.jlibbig.proverif.parseinput.records.Type;
import it.uniud.mads.jlibbig.proverif.parseinput.records.Variable;

/**
 * This class parse all Node of a given bigraph in order to take their contract
 * and the given contract.
 */
public class ParseInput {

    private BigraphInfo bigraphInfo = null;
    private List<Type> types = new ArrayList<Type>();
    private List<Channel> channels = new ArrayList<Channel>();
    private List<Variable> variables = new ArrayList<Variable>();
    private List<Function> functions = new ArrayList<Function>();
    private List<Event> events = new ArrayList<Event>();
    private List<Query> queries = new ArrayList<Query>();
    private List<String> contracts = new ArrayList<String>();
    private List<Instantiations> instantiations = new ArrayList<Instantiations>();
    private List<Prologue> prologues  = new ArrayList<Prologue>();
    private MainProcess process = null;

    public ParseInput() {
    }

    /**
     * Parse the bigraph and the integrative Json of the bigraph.
     * <p>
     * MODIFY: bigraphInfo, types, channels, variables, functions, queries,
     * contracts, processes. Add parsed element.
     * 
     * @param bigraph         the bigraph to be parsed
     * @param integrativeJson the integrative json of the bigraph to be parsed
     */
    public void parse(DirectedBigraph bigraph, String integrativeJson) {
        parseIntegrativeBigraph(integrativeJson);
        parseNode(bigraph);
    }

    /**
     * Parse node attributes of given bigraph.
     * <p>
     * MODIFY: contracts, add the complete behaviour to the list. MODIFY: channels,
     * add the channel's declaration to the list. MODIFY: process, create the main
     * process.
     * 
     * @param bigraph the bigraph to parse
     */
    private void parseNode(DirectedBigraph bigraph) {
        Collection<? extends Node> nodes = bigraph.getNodes();
        Iterator<? extends Node> nodesIterator = nodes.iterator();
        String mainProcess = "( ";
        while (nodesIterator.hasNext()) {
            Node node = nodesIterator.next();
            String singleNodeContract = "let behaviour_";
            String label = "";
            try {
                label = node.<String>getProperty("Label").get();
            } catch (NullPointerException e) {
                System.err.println("Label is needed, add a label to node: " + node.getName());
                System.exit(1);
            }
            String params = "";
            String behaviour = "";
            String events = "";
            try {
                params = node.<String>getProperty("params").get().substring(3).replace("#, ", ", ");
            } catch (NullPointerException e) {
            } catch (java.lang.StringIndexOutOfBoundsException f) {
            }
            try {
                behaviour = node.<String>getProperty("behaviour").get();
            } catch (NullPointerException e) {
            }
            try {
                events = node.<String>getProperty("events").get().substring(3).replace("#, ", ", ");
                String[] eventsArr = events.split(", ");
                for (int i = 0; i < eventsArr.length; i++) {
                    String contract = Constants.getEventdeclaration() + " " + eventsArr[i].strip() + Constants.getDot();
                    Event event = new Event(eventsArr[i].strip(), contract);
                    this.events.add(event);
                }
            } catch (NullPointerException e) {
            } catch (java.lang.StringIndexOutOfBoundsException f) {
            }
            if (!(behaviour.isEmpty())) {
                singleNodeContract += label;
                if (!(params.isEmpty())) {
                    singleNodeContract += "(" + params + ")";
                } else {
                    singleNodeContract += "()";
                }
                singleNodeContract += " = ";
                try {
                    singleNodeContract += parseBehaviour(behaviour, node, bigraph);
                } catch (WrongPolarityException | NoChannelFound e) {
                    System.err.println(e);
                    System.exit(1);
                }
                this.contracts.add(singleNodeContract);
                // Add the behaviour to the main process
                if (!(params.isEmpty())) {
                    mainProcess += "behaviour_" + label + "(";
                    String[] abstractParams = params.split(", ");
                    for (int j = 0; j < abstractParams.length; j++) {
                        String abstractParam = abstractParams[j].split(":")[0].strip();
                        try {
                            mainProcess += findConcreteParam(label, abstractParam) + ",";
                        } catch (NoInstanceFound e) {
                            System.err.println(e);
                            System.exit(1);
                        }
                    }
                    mainProcess = mainProcess.substring(0, mainProcess.length() - 1);
                    mainProcess += ") | ";
                } else {
                    mainProcess += "behaviour_" + label + "() | ";
                }
            }
        }
        mainProcess = mainProcess.substring(0, mainProcess.length() - 2);
        mainProcess += ")";
        MainProcess mainPrc = new MainProcess("mainProcess", mainProcess);
        this.process = mainPrc;
    }

    /**
     * Find the concrete instance of the given abstract param. The concrete instance
     * is specified in the integrative Json.
     * 
     * @param nodeId        the Node's id
     * @param abstractParam the Abstract parameter of Node's behaviour
     * @return the concrete parameter of Node's behaviour equivalent to the abstract
     *         parameter
     * @throws NoInstanceFound if no concrete parameter is found.
     */
    private String findConcreteParam(String nodeId, String abstractParam) throws NoInstanceFound {
        String concreteParam = "";
        Iterator<Instantiations> instanceIt = instantiations.iterator();
        while (instanceIt.hasNext()) {
            Instantiations singelInstance = instanceIt.next();
            if (singelInstance.getNodeID().equals(nodeId)) {
                List<Instantiation> paramList = singelInstance.getNodeInstantiations();
                Iterator<Instantiation> paramListIt = paramList.iterator();
                while (paramListIt.hasNext()) {
                    Instantiation singleParam = paramListIt.next();
                    if (singleParam.getId().equals(abstractParam)) {
                        concreteParam = singleParam.getContract();
                        break;
                    }
                }
            }
        }
        if (concreteParam.isBlank()) {
            throw new NoInstanceFound(
                    "No instance was found for the abstract parameter: " + abstractParam + "of the node: " + nodeId);
        }
        return concreteParam;
    }

    /**
     * Parse the Node's behaviour checking the right dependency with names.
     * 
     * @param behaviour the Node's raw behaviour
     * @param node      the Object represented the Node
     * @param bigraph   the Object represented the Bigraph
     * @return the parsed behaviour
     * @throws WrongPolarityException if the polarity of the node don't match with
     *                                the specified polarity
     * @throws NoChannelFound         if no channel is found
     */
    private String parseBehaviour(String behaviour, Node node, DirectedBigraph bigraph)
            throws WrongPolarityException, NoChannelFound {
        String finalBehaveiour = "";
        char[] behave = behaviour.toCharArray();
        for (int i = 0; i < behave.length; i++) {
            if (behave[i] != '#') {
                finalBehaveiour += behave[i];
            } else {
                int port = Integer.parseInt("" + behave[i + 1]);
                String polarity = "" + behave[i + 2];
                if (polarity.equals("+") || polarity.equals("-")) {
                    String channelName = node.getOutPort(port).getHandle().toString();
                    boolean isFound = false;
                    // Check Public
                    Interface out = bigraph.getOuterInterface();
                    Set<String> setOut = out.keySet();
                    Iterator<String> setOutIterator = setOut.iterator();
                    while (setOutIterator.hasNext()) {
                        String name = setOutIterator.next();
                        String[] nameArr = name.split(" ");
                        if (channelName.equals(nameArr[2])) {
                            String actualPolarity;
                            if (nameArr[1].equals("r")) {
                                actualPolarity = "-";
                            } else {
                                actualPolarity = "+";
                            }
                            if (polarity.equals(actualPolarity)) {
                                // Replace Shortcode
                                finalBehaveiour += channelName;
                                // Create channel declaration
                                String contract = Constants.getFree() + " " + channelName + " " + Constants.getColon()
                                        + " " + Constants.getChanneldeclaration() + Constants.getDot();
                                Channel channel = new Channel(channelName, contract);
                                boolean alreadyAdded = false;
                                for (int j = 0; j < this.channels.size(); j++) {
                                    if (this.channels.get(j).getId().equals(channel.getId())) {
                                        alreadyAdded = true;
                                    }
                                }
                                if (!alreadyAdded) {
                                    this.channels.add(channel);
                                }
                                // Set is found
                                isFound = true;
                            }
                        }
                    }
                    if (!isFound) {
                        // Check Private
                        Interface in = bigraph.getInnerInterface();
                        Set<String> setIn = in.keySet();
                        Iterator<String> setInIterator = setIn.iterator();
                        while (setInIterator.hasNext()) {
                            String name = setInIterator.next();
                            String[] nameArr = name.split(" ");
                            if (channelName.equals(nameArr[2])) {
                                String actualPolarity;
                                if (nameArr[1].equals("r")) {
                                    actualPolarity = "-";
                                } else {
                                    actualPolarity = "+";
                                }
                                if (polarity.equals(actualPolarity)) {
                                    // Replace Shortcode
                                    finalBehaveiour += channelName;
                                    // Create channel declaration
                                    String contract = Constants.getFree() + " " + channelName + " "
                                            + Constants.getColon() + " " + Constants.getChanneldeclaration() + " "
                                            + Constants.getPrivatedeclaration() + Constants.getDot();
                                    Channel channel = new Channel(channelName, contract);
                                    boolean alreadyAdded = false;
                                    for (int j = 0; j < this.channels.size(); j++) {
                                        if (this.channels.get(j).getId().equals(channel.getId())) {
                                            alreadyAdded = true;
                                        }
                                    }
                                    if (!alreadyAdded) {
                                        this.channels.add(channel);
                                    }
                                    // Set is found
                                    isFound = true;
                                }
                            }
                        }
                    }
                    if (!isFound) {
                        throw new NoChannelFound("No channel was found, ensure that name \"" + channelName
                                + "\" is a name of the bigraph");
                    }
                } else {
                    throw new WrongPolarityException("Polarity in the behaviour is not + or -");
                }
                // Skip shortcode
                i = i + 2;
            }
        }
        return finalBehaveiour;
    }

    /**
     * Parse the integrative Json of the bigraph.
     * <p>
     * MODIFY: bigraphInfo, types, variables, functions, queries, instantiations.
     * Add parsed element.
     * 
     * @param integrativeJson the integrative json of the bigraph to be parsed
     */
    private void parseIntegrativeBigraph(String integrativeJson) {
        try {
            final JSONObject obj = new JSONObject(integrativeJson);
            try {
                final JSONObject bigraphInfo = obj.getJSONObject("bigraphInfo");
                parseBigraphInfo(bigraphInfo);
            } catch (JSONException e) {
                parseBigraphInfo();
            }
            final JSONArray types = obj.getJSONArray("types");
            final JSONArray variablesPublic = obj.getJSONObject("variables").getJSONArray("public");
            final JSONArray variablesPrivate = obj.getJSONObject("variables").getJSONArray("private");
            final JSONObject functions = obj.getJSONObject("functions");
            final JSONObject queries = obj.getJSONObject("queries");
            final JSONObject instantiations = obj.getJSONObject("instantiations");
            final JSONArray prologues = obj.getJSONArray("prologue");
            parseIntegrativeTypes(types);
            parseIntegrativeVariables(variablesPublic, variablesPrivate);
            parseIntegrativeFunctions(functions);
            parseIntegrativeQueries(queries);
            parseIntegrativeInstantiations(instantiations);
            parseIntegrativePrologue(prologues);
        } catch (JSONException e) {
            System.err.println(e);
            System.exit(1);
        }
    }

    /**
     * Parse the bigraph information.
     * <p>
     * MODIFY: bigraphInfo. Add the bigraphInfo parsed.
     * 
     * @param bigraphInfo the Json of the bigraph information
     */
    private void parseBigraphInfo(JSONObject bigraphInfo) {
        BigraphInfo info = new BigraphInfo(bigraphInfo.getString(Constants.getJsonidkey()));
        this.bigraphInfo = info;
    }

    /**
     * Parse the bigraph informations.
     * <p>
     * MODIFY: bigraphInfo. Add the bigraphInfo parsed.
     */
    private void parseBigraphInfo() {
        Random random = new Random();
        String id = random.nextInt(1000000) + "";
        BigraphInfo info = new BigraphInfo(id);
        this.bigraphInfo = info;
    }

    /**
     * Parse the types informations.
     * <p>
     * MODIFY: types. Add the types parsed.
     * 
     * @param types the Json of the types.
     */
    private void parseIntegrativeTypes(JSONArray types) {
        final int n = types.length();
        for (int i = 0; i < n; i++) {
            String record = types.getString(i);
            String contract = Constants.getTypedeclaration() + " " + record + Constants.getDot();
            Type type = new Type(record, contract);
            this.types.add(type);
        }
    }

    /**
     * Parse the variables informations.
     * <p>
     * MODIFY: variables. Add the variables parsed.
     * 
     * @param variablesPublic  the Json of the public variables.
     * @param variablesPrivate the Json of the private variables.
     */
    private void parseIntegrativeVariables(JSONArray variablesPublic, JSONArray variablesPrivate) {
        final int numPublic = variablesPublic.length();
        final int numPrivate = variablesPrivate.length();
        for (int i = 0; i < numPublic; i++) {
            String record = variablesPublic.getString(i);
            String contract = Constants.getFree() + " " + record + Constants.getDot();
            Variable variable = new Variable(record, contract);
            this.variables.add(variable);
        }
        for (int i = 0; i < numPrivate; i++) {
            String record = variablesPrivate.getString(i);
            String contract = Constants.getFree() + " " + record + " " + Constants.getPrivatedeclaration()
                    + Constants.getDot();
            Variable variable = new Variable(record, contract);
            this.variables.add(variable);
        }
    }

    /**
     * Parse the functions informations.
     * <p>
     * MODIFY: functions. Add the functions parsed.
     * 
     * @param functions the Json of the functions.
     */
    private void parseIntegrativeFunctions(JSONObject functions) {
        final JSONArray functionsNames = functions.names();
        if (!(functionsNames == null)) {
            final int n = functionsNames.length();
            for (int i = 0; i < n; i++) {
                String contract = functions.getString(functionsNames.getString(i));
                Function function = new Function(functionsNames.getString(i), contract);
                this.functions.add(function);
            }
        }
    }

    /**
     * Parse the queries informations.
     * <p>
     * MODIFY: queries. Add the queries parsed.
     * 
     * @param queries the Json of the queries.
     */
    private void parseIntegrativeQueries(JSONObject queries) {
        final JSONArray queriesNames = queries.names();
        if (!(queriesNames == null)) {
            final int n = queriesNames.length();
            for (int i = 0; i < n; i++) {
                JSONObject record = queries.getJSONObject(queriesNames.getString(i));
                try {
                    String attacker = record.getString("attacker");
                    String contract = Constants.getQuerydeclaration() + " " + Constants.getQueryattacker() + "("
                            + attacker + ")" + Constants.getDot();
                    Query query = new Query(queriesNames.getString(i), contract);
                    this.queries.add(query);
                } catch (JSONException a) {
                    try {
                        String qry = record.getString("query");
                        String contract = Constants.getQuerydeclaration() + " " + qry + Constants.getDot();
                        Query query = new Query(queriesNames.getString(i), contract);
                        this.queries.add(query);
                    } catch (Exception b) {
                        try {
                            String equation = record.getString("equation");
                            String contract = Constants.getEquationdeclaration() + " " + equation + Constants.getDot();
                            Query query = new Query(queriesNames.getString(i), contract);
                            this.queries.add(query);
                        } catch (Exception c) {
                            System.err.println(c);
                            System.exit(1);
                        }
                    }

                }
            }
        }
    }

    /**
     * Parse the instantiation informations.
     * <p>
     * MODIFY: instantiations. Add the instatiation parsed.
     * 
     * @param queries the Json of the instatiations.
     */
    private void parseIntegrativeInstantiations(JSONObject instantiations) {
        final JSONArray instantiationsNames = instantiations.names();
        if (!(instantiationsNames == null)) {
            final int n = instantiationsNames.length();
            for (int i = 0; i < n; i++) {
                JSONObject record = instantiations.getJSONObject(instantiationsNames.getString(i));
                String nodeId = instantiationsNames.getString(i);
                final JSONArray recordNames = record.names();
                List<Instantiation> nodeInstance = new ArrayList<Instantiation>();
                for (int j = 0; j < recordNames.length(); j++) {
                    String recordId = recordNames.getString(j);
                    String contractId = record.getString(recordNames.getString(j));
                    Instantiation singleInstantiation = new Instantiation(recordId, contractId);
                    nodeInstance.add(singleInstantiation);
                }
                Instantiations nodeInstantiations = new Instantiations(nodeId, nodeInstance);
                this.instantiations.add(nodeInstantiations);
            }
        }
    }

    /**
     * Parse the prologue informations.
     * <p>
     * MODIFY: prologue. Add the prologue parsed.
     * 
     * @param prologue the Json of the types.
     */
    private void parseIntegrativePrologue(JSONArray prologue) {
        final int n = prologue.length();
        for (int i = 0; i < n; i++) {
            String record = prologue.getString(i);
            Prologue prologueObj = new Prologue(record, record);
            this.prologues.add(prologueObj);
        }
    }

    /**
     * @return the types
     */
    public List<Type> getTypes() {
        return types;
    }

    /**
     * @return the channels
     */
    public List<Channel> getChannels() {
        return channels;
    }

    /**
     * @return the variables
     */
    public List<Variable> getVariables() {
        return variables;
    }

    /**
     * @return the functions
     */
    public List<Function> getFunctions() {
        return functions;
    }

    /**
     * @return the queries
     */
    public List<Query> getQueries() {
        return queries;
    }

    /**
     * @return the contracts
     */
    public List<String> getContracts() {
        return contracts;
    }

    /**
     * @return the process
     */
    public MainProcess getProcess() {
        return process;
    }

    /**
     * @return the bigraphInfo
     */
    public BigraphInfo getBigraphInfo() {
        return bigraphInfo;
    }

    /**
     * @return the events
     */
    public List<Event> getEvents() {
        return events;
    }

    /**
     * @return the prologues
     */
    public List<Prologue> getPrologues() {
        return prologues;
    }
}