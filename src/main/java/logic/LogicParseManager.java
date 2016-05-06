package logic;


/**
 * Created by francisco on 4/26/16.
 */
public class LogicParseManager {

    private int openedParenthesis;
    private LogicFactory factory;

    public LogicParseManager(){
        openedParenthesis = 0;
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

    public int openedParenthesis(){
        return openedParenthesis;
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
