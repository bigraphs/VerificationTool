package it.uniud.mads.jlibbig.proverif.networkservices;

import org.json.JSONObject;

import it.uniud.mads.jlibbig.proverif.constants.Constants;
import it.uniud.mads.jlibbig.proverif.exceptions.InternalServerError500Exception;
import it.uniud.mads.jlibbig.proverif.exceptions.NotFound404Exception;
import it.uniud.mads.jlibbig.proverif.exceptions.UnsupportedMediaType415Exception;
import it.uniud.mads.jlibbig.proverif.exceptions.Conflict409Exception;
import it.uniud.mads.jlibbig.proverif.exceptions.GeneralException;

/**
 * This class implements specific http-rest request to proverif server
 */
public class ProVerifRequests {

    /**
     * Get all the models in the server
     * 
     * @return the json string represents all models
     */
    public static String getModels() {
        try {
            return HttpRequests.get("models");
        } catch (NotFound404Exception | GeneralException | InternalServerError500Exception e) {
            JSONObject json = new JSONObject(e.getMessage());
            System.err.println(json.toString(1));
            System.exit(1);
            return null;
        }

    }

    /**
     * Get a single model in the server
     * 
     * @param model the wanted model
     * @return the json string represents the wanted model
     */
    public static String getModel(String model) {
        String url = "models/" + model;
        try {
            return HttpRequests.get(url);
        } catch (NotFound404Exception | GeneralException | InternalServerError500Exception e) {
            JSONObject json = new JSONObject(e.getMessage());
            System.err.println(json.toString(1));
            System.exit(1);
            return null;
        }
    }

    /**
     * Post a new model in the server
     * 
     * @param model the new model wanted
     * @return the json string with the server response
     */
    public static String postModel(String model) {
        String url = "models/" + model;
        try {
            return HttpRequests.post(url);
        } catch (NotFound404Exception | Conflict409Exception | UnsupportedMediaType415Exception
                | InternalServerError500Exception | GeneralException e) {
            JSONObject json = new JSONObject(e.getMessage());
            System.err.println(json.toString(1));
            System.exit(1);
            return null;
        }
    }

    /**
     * Delete a model in the server
     * 
     * @param model the model to be deleted
     * @return the json string represent the server response
     */
    public static String deleteModel(String model) {
        String url = "models/" + model;
        try {
            return HttpRequests.delete(url);
        } catch (NotFound404Exception | InternalServerError500Exception | GeneralException e) {
            JSONObject json = new JSONObject(e.getMessage());
            System.err.println(json.toString(1));
            System.exit(1);
            return null;
        }
    }

    /**
     * Get the input file
     * 
     * @param model    the model of the input file wanted
     * @param filename the filename wanted
     * @return the json string represent the input file
     */
    public static String getInputFile(String model, String filename) {
        String url = "models/" + model + "/input/text/" + filename;
        try {
            return HttpRequests.get(url);
        } catch (NotFound404Exception | GeneralException | InternalServerError500Exception e) {
            JSONObject json = new JSONObject(e.getMessage());
            System.err.println(json.toString(1));
            System.exit(1);
            return null;
        }
    }

    /**
     * Post the input file
     * 
     * @param model    the model of the input file
     * @param filename the name of the input file
     * @param filebody the body of the input file
     * @return the json string represent the server response
     */
    public static String postInputFile(String model, String filename, String filebody) {
        String url = "models/" + model + "/input/text/" + filename;
        try {
            return HttpRequests.post(url, Constants.getHttpdata(), filebody);
        } catch (NotFound404Exception | Conflict409Exception | UnsupportedMediaType415Exception
                | InternalServerError500Exception | GeneralException e) {
            JSONObject json = new JSONObject(e.getMessage());
            System.err.println(json.toString(1));
            System.exit(1);
            return null;
        }
    }

    /**
     * Put the input file
     * 
     * @param model    the model of the input file
     * @param filename the name of the input file
     * @param filebody the body of the input file
     * @return the json string represent the server response
     */
    public static String putInputFile(String model, String filename, String filebody) {
        String url = "models/" + model + "/input/text/" + filename;
        try {
            return HttpRequests.put(url, Constants.getHttpdata(), filebody);
        } catch (NotFound404Exception | Conflict409Exception | UnsupportedMediaType415Exception
                | InternalServerError500Exception | GeneralException e) {
            JSONObject json = new JSONObject(e.getMessage());
            System.err.println(json.toString(1));
            System.exit(1);
            return null;
        }
    }

    /**
     * Delete the input file
     * 
     * @param model    the model of the input file
     * @param filename the name of the input file
     * @return the json string represent the server response
     */
    public static String deleteInputFile(String model, String filename) {
        String url = "models/" + model + "/input/text/" + filename;
        try {
            return HttpRequests.delete(url);
        } catch (NotFound404Exception | InternalServerError500Exception | GeneralException e) {
            JSONObject json = new JSONObject(e.getMessage());
            System.err.println(json.toString(1));
            System.exit(1);
            return null;
        }
    }

    /**
     * Post a verify on the server in text-mode
     * 
     * @param model    the model of the file name to verify
     * @param filename the file name to verify
     * @return the json string represent the server response
     */
    public static String postVerifyText(String model, String filename) {
        String url = "models/" + model + "/verify/" + filename;
        try {
            return HttpRequests.post(url, Constants.getHttptype(), "text");
        } catch (NotFound404Exception | Conflict409Exception | UnsupportedMediaType415Exception
                | InternalServerError500Exception | GeneralException e) {
            JSONObject json = new JSONObject(e.getMessage());
            System.err.println(json.toString(1));
            System.exit(1);
            return null;
        }
    }

    /**
     * Post a verify on the server in html-mode
     * 
     * @param model    the model of the file name to verify
     * @param filename the file name to verify
     * @return the json string represent the server response
     */
    public static String postVerifyHtml(String model, String filename) {
        String url = "models/" + model + "/verify/" + filename;
        try {
            return HttpRequests.post(url, Constants.getHttptype(), "html");
        } catch (NotFound404Exception | Conflict409Exception | UnsupportedMediaType415Exception
                | InternalServerError500Exception | GeneralException e) {
            JSONObject json = new JSONObject(e.getMessage());
            System.err.println(json.toString(1));
            System.exit(1);
            return null;
        }
    }

    /**
     * Put a verify on the server in text-mode
     * 
     * @param model    the model of the file name to verify
     * @param filename the file name to verify
     * @return the json string represent the server response
     */
    public static String putVerifyText(String model, String filename) {
        String url = "models/" + model + "/verify/" + filename;
        try {
            return HttpRequests.put(url, Constants.getHttptype(), "text");
        } catch (NotFound404Exception | Conflict409Exception | UnsupportedMediaType415Exception
                | InternalServerError500Exception | GeneralException e) {
            JSONObject json = new JSONObject(e.getMessage());
            System.err.println(json.toString(1));
            System.exit(1);
            return null;
        }
    }

    /**
     * Put a verify on the server in html-mode
     * 
     * @param model    the model of the file name to verify
     * @param filename the file name to verify
     * @return the json string represent the server response
     */
    public static String putVerifyHtml(String model, String filename) {
        String url = "models/" + model + "/verify/" + filename;
        try {
            return HttpRequests.put(url, Constants.getHttptype(), "html");
        } catch (NotFound404Exception | Conflict409Exception | UnsupportedMediaType415Exception
                | InternalServerError500Exception | GeneralException e) {
            JSONObject json = new JSONObject(e.getMessage());
            System.err.println(json.toString(1));
            System.exit(1);
            return null;
        }
    }

    /**
     * Get the proverif output of the text-mode
     * 
     * @param model    the model of the output filename
     * @param filename the output filename
     * @return the json string represents the proverif output of text-mode
     */
    public static String getOutputText(String model, String filename) {
        String url = "models/" + model + "/output/text/" + filename;
        try {
            return HttpRequests.get(url);
        } catch (NotFound404Exception | GeneralException | InternalServerError500Exception e) {
            JSONObject json = new JSONObject(e.getMessage());
            System.err.println(json.toString(1));
            System.exit(1);
            return null;
        }
    }

    /**
     * Delete the proverif output of the text-mode
     * 
     * @param model    the model of the output filename
     * @param filename the output filename
     * @return the jdon string represents the server response
     */
    public static String deleteOutputText(String model, String filename) {
        String url = "models/" + model + "/output/text/" + filename;
        try {
            return HttpRequests.delete(url);
        } catch (NotFound404Exception | InternalServerError500Exception | GeneralException e) {
            JSONObject json = new JSONObject(e.getMessage());
            System.err.println(json.toString(1));
            System.exit(1);
            return null;
        }
    }

    /**
     * Get the proverif output of the html-mode
     * 
     * @param model the model of the output filename
     * @return the json string represents the proverif output of html-mode
     */
    public static String getOutputHtml(String model) {
        String url = "models/" + model + "/output/html/index.html";
        try {
            String httpResult = HttpRequests.get(url).replaceAll("\n", " ").replaceAll(" +", " ")
                    .replaceAll("\"", "\\\\\"").strip();
            // String results = "{\n \"url\": \"" + Constants.getHttpbaseurl() + url + "\",\n \"output\": \"" + httpResult
            //         + "\"\n}";
            String results = "{\n \"url\": \"" + Constants.getHttpgetoutputurl() + url + "\",\n \"output\": \"" + httpResult
                    + "\"\n}";
            JSONObject json = new JSONObject(results);
            return json.toString(1);
        } catch (NotFound404Exception | GeneralException | InternalServerError500Exception e) {
            JSONObject json = new JSONObject(e.getMessage());
            System.err.println(json.toString(1));
            System.exit(1);
            return null;
        }
    }

    /**
     * Delete the proverif output of the html-mode
     * 
     * @param model the model of the output filename
     * @return the jdon string represents the server response
     */
    public static String deleteOutputHtml(String model) {
        String url = "models/" + model + "/output/html/index.html";
        try {
            return HttpRequests.delete(url);
        } catch (NotFound404Exception | InternalServerError500Exception | GeneralException e) {
            JSONObject json = new JSONObject(e.getMessage());
            System.err.println(json.toString(1));
            System.exit(1);
            return null;
        }
    }

}
