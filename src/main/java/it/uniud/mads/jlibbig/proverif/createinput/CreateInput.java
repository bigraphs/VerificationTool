package it.uniud.mads.jlibbig.proverif.createinput;

import java.util.Iterator;
import java.util.List;

import it.uniud.mads.jlibbig.proverif.constants.Constants;
import it.uniud.mads.jlibbig.proverif.parseinput.ParseInput;
import it.uniud.mads.jlibbig.proverif.parseinput.records.BigraphInfo;
import it.uniud.mads.jlibbig.proverif.parseinput.records.Channel;
import it.uniud.mads.jlibbig.proverif.parseinput.records.Event;
import it.uniud.mads.jlibbig.proverif.parseinput.records.Function;
import it.uniud.mads.jlibbig.proverif.parseinput.records.MainProcess;
import it.uniud.mads.jlibbig.proverif.parseinput.records.Prologue;
import it.uniud.mads.jlibbig.proverif.parseinput.records.Query;
import it.uniud.mads.jlibbig.proverif.parseinput.records.Type;
import it.uniud.mads.jlibbig.proverif.parseinput.records.Variable;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

/**
 * This class create an input file for proverif based on the bigraph parsed.
 */
public class CreateInput {

    private List<Type> types;
    private List<Channel> channels;
    private List<Variable> variables;
    private List<Function> functions;
    private List<Event> events;
    private List<Query> queries;
    private List<String> contracts;
    private List<Prologue> prologues;
    MainProcess process;
    private BigraphInfo bigraphInfo;

    private String proverifInput;

    /**
     * MODIFY: types, channels, variables, functions, queries, contracts, processes.
     * Get the data previously parsed.
     * <p>
     * 
     * @param parsedInput the bigraph parsed.
     */
    public CreateInput(ParseInput parsedInput) {
        this.types = parsedInput.getTypes();
        this.channels = parsedInput.getChannels();
        this.variables = parsedInput.getVariables();
        this.functions = parsedInput.getFunctions();
        this.events = parsedInput.getEvents();
        this.queries = parsedInput.getQueries();
        this.contracts = parsedInput.getContracts();
        this.process = parsedInput.getProcess();
        this.bigraphInfo = parsedInput.getBigraphInfo();
        this.prologues = parsedInput.getPrologues();
    }

    /**
     * Create the input file for proverif.
     * <p>
     * MODIFY: proverifInput, parse and set the input file for proverif.
     */
    public void create() {
        String input = "";
        input += addHeading();
        input += addTypes();
        input += addChannels();
        input += addVariables();
        input += addFunctions();
        input += addEvents();
        input += addQueries();
        input += addContracts();
        input += addProcess();
        proverifInput = input;
    }

    /**
     * Add heading to the input file.
     */
    private String addHeading() {
        String heading = "";
        // old format "yyyy/MM/dd HH:mm:ss"
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String timestamp = dtf.format(now);
        heading = Constants.getOpencomment() + "Bigraph " + bigraphInfo.getId().strip() + " - " + timestamp
                + Constants.getClosecomment() + " ";
        return heading;
    }

    /**
     * Add types to the input file.
     */
    private String addTypes() {
        String allTypes = "";
        Iterator<Type> typesIt = types.iterator();
        while (typesIt.hasNext()) {
            Type type = typesIt.next();
            String singleType = type.getContract().strip();
            allTypes += singleType + " ";
        }
        return allTypes;
    }

    /**
     * Add channels to the input file.
     */
    private String addChannels() {
        String allChannels = "";
        Iterator<Channel> channelsIt = channels.iterator();
        while (channelsIt.hasNext()) {
            Channel channel = channelsIt.next();
            String singleChannel = channel.getContract().strip();
            allChannels += singleChannel + " ";
        }
        return allChannels;
    }

    /**
     * Add variables to the input file.
     */
    private String addVariables() {
        String allVariables = "";
        Iterator<Variable> variablesIt = variables.iterator();
        while (variablesIt.hasNext()) {
            Variable variable = variablesIt.next();
            String singleVariable = variable.getContract().strip();
            allVariables += singleVariable + " ";
        }
        return allVariables;
    }

    /**
     * Add functions to the input file.
     */
    private String addFunctions() {
        String allFunctions = "";
        Iterator<Function> functionsIt = functions.iterator();
        while (functionsIt.hasNext()) {
            Function function = functionsIt.next();
            String singleFunction = function.getContract().strip();
            allFunctions += singleFunction + " ";
        }
        return allFunctions;
    }

    /**
     * Add events to the input file.
     */
    private String addEvents() {
        String allEvents = "";
        Iterator<Event> eventsIt = events.iterator();
        while (eventsIt.hasNext()) {
            Event event = eventsIt.next();
            String singleEvent = event.getContract().strip();
            allEvents += singleEvent + " ";
        }
        return allEvents;
    }

    /**
     * Add queries to the input file.
     */
    private String addQueries() {
        String allQueries = "";
        Iterator<Query> queriesIt = queries.iterator();
        while (queriesIt.hasNext()) {
            Query query = queriesIt.next();
            String singleQuery = query.getContract().strip();
            allQueries += singleQuery + " ";
        }
        return allQueries;
    }

    /**
     * Add processes to the input file.
     */
    private String addProcess() {
        String finalProcess = Constants.getProcess() + " " + addPrologue() + process.getContract().strip();
        return finalProcess;
    }

    /**
     * Add contracts to the input file.
     */
    private String addContracts() {
        String allContracts = "";
        Iterator<String> contractsIt = contracts.iterator();
        while (contractsIt.hasNext()) {
            String contract = contractsIt.next();
            allContracts += contract + " ";
        }
        return allContracts;
    }

    /**
     * Add prologue to the input file.
     */
    private String addPrologue() {
        String allPrologues = "";
        Iterator<Prologue> prologuesIt = prologues.iterator();
        while (prologuesIt.hasNext()) {
            Prologue prologue = prologuesIt.next();
            String singlePrologue = prologue.getContract().strip();
            allPrologues += singlePrologue + " ";
        }
        return allPrologues;
    }

    /**
     * @return the proverifInput
     */
    public String getProverifInput() {
        return proverifInput;
    }
}