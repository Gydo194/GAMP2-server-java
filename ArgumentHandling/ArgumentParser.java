/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ArgumentHandling;

/**
 *
 * @author gydo194
 */
public class ArgumentParser {

    private String input;

    public ArgumentParser() {
    }

    public ArgumentParser(String input) {
        this.input = input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getInput() {
        return this.input;
    }

    public boolean hasNext() {
        return input.length() > 0;
    }

    public String next() {
        String ret = "";

        int index = input.indexOf(",");
        if (index < 0) {
            ret = input; //return the remainder
            input = "";
            return ret;
        }

        ret = input.substring(0, index);

        input = input.substring(index + 1, input.length());

        return ret;
    }

}
