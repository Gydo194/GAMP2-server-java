/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Functions;



/**
 *
 * @author gydo194
 * this class parses function call syntax
 */
public class FunctionParser {
    private enum states { FUNC,ARG };
    private states state = states.FUNC;
    private StringBuilder funcBuffer = new StringBuilder();
    private StringBuilder argBuffer = new StringBuilder();
    private FunctionHandler fh;
    
    
    public FunctionParser(FunctionHandler f) { fh = f; }
    
    private void call() {
        if(fh.hasFunction(funcBuffer.toString()))
        fh.call(funcBuffer.toString(), argBuffer.toString());
        //else System.out.printf("FunctionParser::call(): function '%s' cannot be called: not found\n",funcBuffer.toString());
    }
    
    
    private void resetBuffers() {
        funcBuffer.setLength(0);
        argBuffer.setLength(0);
    }
    
    private void append(char in) {
        switch(state) {
            case FUNC:
                funcBuffer.append(in);
                break;
            case ARG:
                argBuffer.append(in);
                
        }
    }
    
    
    
    
    public void process(char in) {
        switch(in) {
            case '(':
                state = states.ARG;
                break;
            case ')':
                state = states.FUNC;
                break;
            case ';':
                //call response handler;
                call();
                resetBuffers();
                break;
            default:
                append(in);
                break;
                
        }
    }
    
    
    
    public void process(String in) {
        char cstr[] = in.toCharArray();
        
        for(char c : cstr) {
            process(c);
        }
    }
    
    
    
}
