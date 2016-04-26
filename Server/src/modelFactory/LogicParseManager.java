package modelFactory;


/**
 * Created by francisco on 4/26/16.
 */
public class LogicParseManager {

    private int openedParenthesis;
    private LogicBuilder parser;
    private LogicFactory factory;

    public LogicParseManager(LogicBuilder parser){
        openedParenthesis = 0;
        this.parser = parser;
        factory = null;
    }

    public void openParenthesis(){
        openedParenthesis++;
    }

    public void closeParenthesis(){
        openedParenthesis--;
    }

    public void reset(){
        openedParenthesis = 0;
        factory = null;
    }

    public void notifySymbolFound(LogicSymbolInterpreter interpreter){
        if (openedParenthesis == 0){
            factory = interpreter.getFactory();
        }
    }

    public LogicFactory getFactoryForFoundSymbol(){
        return factory;
    }

}
