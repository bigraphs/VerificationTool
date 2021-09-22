package it.uniud.mads.jlibbig.proverif;

import org.json.JSONObject;

import it.uniud.mads.jlibbig.core.ldb.DirectedBigraph;
import it.uniud.mads.jlibbig.proverif.constants.Constants;
import it.uniud.mads.jlibbig.proverif.constants.VerificationType;
import it.uniud.mads.jlibbig.proverif.createinput.CreateInput;
import it.uniud.mads.jlibbig.proverif.networkservices.ProVerifRequests;
import it.uniud.mads.jlibbig.proverif.parseinput.ParseInput;

/**
 * This class implements methods to verify a Proverif file.
 */
public class Verify implements VerificationInterface {

    @Override
    public String postVerify(DirectedBigraph bigraph, String jsonBigraphContract) {
        ParseInput parsedInput = parseInput(bigraph, jsonBigraphContract);
        String inputProverif = createInput(parsedInput);
        String model = parsedInput.getBigraphInfo().getId();
        String output = postVerify(VerificationType.text, model, model, inputProverif);
        putVerify(VerificationType.html, model, model, inputProverif);
        // String url = Constants.getHttpbaseurl() + "models/" + model + "/output/html/index.html";
        String url = Constants.getHttpgetoutputurl() + "models/" + model + "/output/html/index.html";
        String json = "{ \"OutputText\": " +  output.substring(0, output.length() -1) + ",\n \"OutputHtml\": \"" + url + "\"\n }";
        JSONObject jsonOb = new JSONObject(json);
        return jsonOb.toString(1);
    }

    @Override
    public String postVerify(DirectedBigraph bigraph, String jsonBigraphContract, String modelName) {
        ParseInput parsedInput = parseInput(bigraph, jsonBigraphContract);
        String inputProverif = createInput(parsedInput);
        String filename = parsedInput.getBigraphInfo().getId();
        String output = postVerify(VerificationType.text, modelName, filename, inputProverif);
        putVerify(VerificationType.html, modelName, filename, inputProverif);
        // String url = Constants.getHttpbaseurl() + "models/" + modelName + "/output/html/index.html";
        String url = Constants.getHttpgetoutputurl() + "models/" + modelName + "/output/html/index.html";
        String json = "{ \"OutputText\": " +  output.substring(0, output.length() -1) + ",\n \"OutputHtml\": \"" + url + "\"\n }";
        JSONObject jsonOb = new JSONObject(json);
        return jsonOb.toString(1);
    }

    @Override
    public String postVerify(DirectedBigraph bigraph, String jsonBigraphContract, String modelName, String fileName) {
        ParseInput parsedInput = parseInput(bigraph, jsonBigraphContract);
        String inputProverif = createInput(parsedInput);
        String output = postVerify(VerificationType.text, modelName, fileName, inputProverif);
        putVerify(VerificationType.html, modelName, fileName, inputProverif);
        // String url = Constants.getHttpbaseurl() + "models/" + modelName + "/output/html/index.html";
        String url = Constants.getHttpgetoutputurl() + "models/" + modelName + "/output/html/index.html";
        String json = "{ \"OutputText\": " +  output.substring(0, output.length() -1) + ",\n \"OutputHtml\": \"" + url + "\"\n }";
        JSONObject jsonOb = new JSONObject(json);
        return jsonOb.toString(1);
    }

    @Override
    public String postVerifyText(DirectedBigraph bigraph, String jsonBigraphContract) {
        ParseInput parsedInput = parseInput(bigraph, jsonBigraphContract);
        String inputProverif = createInput(parsedInput);
        String model = parsedInput.getBigraphInfo().getId();
        String output = postVerify(VerificationType.text, model, model, inputProverif);
        String json = "{ \"OutputText\": " +  output.substring(0, output.length() -1) + "\n }";
        JSONObject jsonOb = new JSONObject(json);
        return jsonOb.toString(1);
    }

    @Override
    public String postVerifyText(DirectedBigraph bigraph, String jsonBigraphContract, String modelName) {
        ParseInput parsedInput = parseInput(bigraph, jsonBigraphContract);
        String inputProverif = createInput(parsedInput);
        String filename = parsedInput.getBigraphInfo().getId();
        String output = postVerify(VerificationType.text, modelName, filename, inputProverif);
        String json = "{ \"OutputText\": " +  output.substring(0, output.length() -1) + "\n }";
        JSONObject jsonOb = new JSONObject(json);
        return jsonOb.toString(1);
    }

    @Override
    public String postVerifyText(DirectedBigraph bigraph, String jsonBigraphContract, String modelName,
            String fileName) {
        ParseInput parsedInput = parseInput(bigraph, jsonBigraphContract);
        String inputProverif = createInput(parsedInput);
        String output = postVerify(VerificationType.text, modelName, fileName, inputProverif);
        String json = "{ \"OutputText\": " +  output.substring(0, output.length() -1) + "\n }";
        JSONObject jsonOb = new JSONObject(json);
        return jsonOb.toString(1);
    }

    @Override
    public String postVerifyHtml(DirectedBigraph bigraph, String jsonBigraphContract) {
        ParseInput parsedInput = parseInput(bigraph, jsonBigraphContract);
        String inputProverif = createInput(parsedInput);
        String model = parsedInput.getBigraphInfo().getId();
        postVerify(VerificationType.html, model, model, inputProverif);
        // String url = Constants.getHttpbaseurl() + "models/" + model + "/output/html/index.html";
        String url = Constants.getHttpgetoutputurl() + "models/" + model + "/output/html/index.html";
        String json = "{ \"OutputHtml\": \"" + url + "\"\n }";
        JSONObject jsonOb = new JSONObject(json);
        return jsonOb.toString(1);
    }

    @Override
    public String postVerifyHtml(DirectedBigraph bigraph, String jsonBigraphContract, String modelName) {
        ParseInput parsedInput = parseInput(bigraph, jsonBigraphContract);
        String inputProverif = createInput(parsedInput);
        String filename = parsedInput.getBigraphInfo().getId();
        postVerify(VerificationType.html, modelName, filename, inputProverif);
        // String url = Constants.getHttpbaseurl() + "models/" + modelName + "/output/html/index.html";
        String url = Constants.getHttpgetoutputurl() + "models/" + modelName + "/output/html/index.html";
        String json = "{ \"OutputHtml\": \"" + url + "\"\n }";
        JSONObject jsonOb = new JSONObject(json);
        return jsonOb.toString(1);
    }

    @Override
    public String postVerifyHtml(DirectedBigraph bigraph, String jsonBigraphContract, String modelName,
            String fileName) {
        ParseInput parsedInput = parseInput(bigraph, jsonBigraphContract);
        String inputProverif = createInput(parsedInput);
        postVerify(VerificationType.html, modelName, fileName, inputProverif);
        // String url = Constants.getHttpbaseurl() + "models/" + modelName + "/output/html/index.html";
        String url = Constants.getHttpgetoutputurl() + "models/" + modelName + "/output/html/index.html";
        String json = "{ \"OutputHtml\": \"" + url + "\"\n }";
        JSONObject jsonOb = new JSONObject(json);
        return jsonOb.toString(1);
    }

    @Override
    public String putVerify(DirectedBigraph bigraph, String jsonBigraphContract) {
        ParseInput parsedInput = parseInput(bigraph, jsonBigraphContract);
        String inputProverif = createInput(parsedInput);
        String model = parsedInput.getBigraphInfo().getId();
        putVerify(VerificationType.html, model, model, inputProverif);
        String output = putVerify(VerificationType.text, model, model, inputProverif);
        // String url = Constants.getHttpbaseurl() + "models/" + model + "/output/html/index.html";
        String url = Constants.getHttpgetoutputurl() + "models/" + model + "/output/html/index.html";
        String json = "{ \"OutputText\": " +  output.substring(0, output.length() -1) + ",\n \"OutputHtml\": \"" + url + "\"\n }";
        JSONObject jsonOb = new JSONObject(json);
        return jsonOb.toString(1);
    }

    @Override
    public String putVerify(DirectedBigraph bigraph, String jsonBigraphContract, String modelName) {
        ParseInput parsedInput = parseInput(bigraph, jsonBigraphContract);
        String inputProverif = createInput(parsedInput);
        String filename = parsedInput.getBigraphInfo().getId();
        putVerify(VerificationType.html, modelName, filename, inputProverif);
        String output = putVerify(VerificationType.text, modelName, filename, inputProverif);
        // String url = Constants.getHttpbaseurl() + "models/" + modelName + "/output/html/index.html";
        String url = Constants.getHttpgetoutputurl() + "models/" + modelName + "/output/html/index.html";
        String json = "{ \"OutputText\": " +  output.substring(0, output.length() -1) + ",\n \"OutputHtml\": \"" + url + "\"\n }";
        JSONObject jsonOb = new JSONObject(json);
        return jsonOb.toString(1);
    }

    @Override
    public String putVerify(DirectedBigraph bigraph, String jsonBigraphContract, String modelName, String fileName) {
        ParseInput parsedInput = parseInput(bigraph, jsonBigraphContract);
        String inputProverif = createInput(parsedInput);
        putVerify(VerificationType.html, modelName, fileName, inputProverif);
        String output = putVerify(VerificationType.text, modelName, fileName, inputProverif);
        // String url = Constants.getHttpbaseurl() + "models/" + modelName + "/output/html/index.html";
        String url = Constants.getHttpgetoutputurl() + "models/" + modelName + "/output/html/index.html";
        String json = "{ \"OutputText\": " +  output.substring(0, output.length() -1) + ",\n \"OutputHtml\": \"" + url + "\"\n }";
        JSONObject jsonOb = new JSONObject(json);
        return jsonOb.toString(1);
    }

    @Override
    public String putVerifyHtml(DirectedBigraph bigraph, String jsonBigraphContract) {
        ParseInput parsedInput = parseInput(bigraph, jsonBigraphContract);
        String inputProverif = createInput(parsedInput);
        String model = parsedInput.getBigraphInfo().getId();
        putVerify(VerificationType.html, model, model, inputProverif);
        // String url = Constants.getHttpbaseurl() + "models/" + model + "/output/html/index.html";
        String url = Constants.getHttpgetoutputurl() + "models/" + model + "/output/html/index.html";
        String json = "{ \"OutputHtml\": \"" + url + "\"\n }";
        JSONObject jsonOb = new JSONObject(json);
        return jsonOb.toString(1);
    }

    @Override
    public String putVerifyHtml(DirectedBigraph bigraph, String jsonBigraphContract, String modelName) {
        ParseInput parsedInput = parseInput(bigraph, jsonBigraphContract);
        String inputProverif = createInput(parsedInput);
        String filename = parsedInput.getBigraphInfo().getId();
        putVerify(VerificationType.html, modelName, filename, inputProverif);
        // String url = Constants.getHttpbaseurl() + "models/" + modelName + "/output/html/index.html";
        String url = Constants.getHttpgetoutputurl() + "models/" + modelName + "/output/html/index.html";
        String json = "{ \"OutputHtml\": \"" + url + "\"\n }";
        JSONObject jsonOb = new JSONObject(json);
        return jsonOb.toString(1);
    }

    @Override
    public String putVerifyHtml(DirectedBigraph bigraph, String jsonBigraphContract, String modelName,
            String fileName) {
        ParseInput parsedInput = parseInput(bigraph, jsonBigraphContract);
        String inputProverif = createInput(parsedInput);
        putVerify(VerificationType.html, modelName, fileName, inputProverif);
        // String url = Constants.getHttpbaseurl() + "models/" + modelName + "/output/html/index.html";
        String url = Constants.getHttpgetoutputurl() + "models/" + modelName + "/output/html/index.html";
        String json = "{ \"OutputHtml\": \"" + url + "\"\n }";
        JSONObject jsonOb = new JSONObject(json);
        return jsonOb.toString(1);
    }

    @Override
    public String putVerifyText(DirectedBigraph bigraph, String jsonBigraphContract) {
        ParseInput parsedInput = parseInput(bigraph, jsonBigraphContract);
        String inputProverif = createInput(parsedInput);
        String model = parsedInput.getBigraphInfo().getId();
        String output = putVerify(VerificationType.text, model, model, inputProverif);
        String json = "{ \"OutputText\": " +  output.substring(0, output.length() -1) + "\n }";
        JSONObject jsonOb = new JSONObject(json);
        return jsonOb.toString(1);
    }

    @Override
    public String putVerifyText(DirectedBigraph bigraph, String jsonBigraphContract, String modelName) {
        ParseInput parsedInput = parseInput(bigraph, jsonBigraphContract);
        String inputProverif = createInput(parsedInput);
        String filename = parsedInput.getBigraphInfo().getId();
        String output = putVerify(VerificationType.text, modelName, filename, inputProverif);
        String json = "{ \"OutputText\": " +  output.substring(0, output.length() -1) + "\n }";
        JSONObject jsonOb = new JSONObject(json);
        return jsonOb.toString(1);
    }

    @Override
    public String putVerifyText(DirectedBigraph bigraph, String jsonBigraphContract, String modelName,
            String fileName) {
        ParseInput parsedInput = parseInput(bigraph, jsonBigraphContract);
        String inputProverif = createInput(parsedInput);
        String output = putVerify(VerificationType.text, modelName, fileName, inputProverif);
        String json = "{ \"OutputText\": " +  output.substring(0, output.length() -1) + "\n }";
        JSONObject jsonOb = new JSONObject(json);
        return jsonOb.toString(1);
    }

    private ParseInput parseInput(DirectedBigraph bigraph, String jsonBigraphContract) {
        ParseInput parsedInput = new ParseInput();
        parsedInput.parse(bigraph, jsonBigraphContract);
        return parsedInput;
    }

    private String createInput(ParseInput parsedInput) {
        CreateInput createdInput = new CreateInput(parsedInput);
        createdInput.create();
        String inputProverif = createdInput.getProverifInput();
        return inputProverif;
    }

    private String postVerify(VerificationType type, String model, String filename, String inputProverif) {
        ProVerifRequests.postModel(model);
        ProVerifRequests.postInputFile(model, filename, inputProverif);
        if (type.equals(VerificationType.text)) {
            ProVerifRequests.postVerifyText(model, filename);
            return ProVerifRequests.getOutputText(model, filename);
        } else {
            ProVerifRequests.postVerifyHtml(model, filename);
            return ProVerifRequests.getOutputHtml(model);
        }
    }

    private String putVerify(VerificationType type, String model, String filename, String inputProverif) {
        ProVerifRequests.putInputFile(model, filename, inputProverif);
        if (type.equals(VerificationType.text)) {
            ProVerifRequests.putVerifyText(model, filename);
            return ProVerifRequests.getOutputText(model, filename);
        } else {
            ProVerifRequests.putVerifyHtml(model, filename);
            return ProVerifRequests.getOutputHtml(model);
        }
    } 
}