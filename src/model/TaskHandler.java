package model;

import command.CommandParserExecute;
import core.Input;
import core.Output;
import errors.CharacterClassException;
import message.Message;
import model.entitie.mobs.Monster;
import model.entitie.runa.Runa;
import model.entitie.runa.RunaClass;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * This class models a task handler for the given task.
 * It is used to execute and handle every step of the task.
 *
 * @author urliz
 * @version 1.0
 */
public class TaskHandler {

    private final Input input;
    private final Output output;
    private final Runa runa;
    private final CommandParserExecute parser;
    private Queue<Monster> monster;

    public TaskHandler(Input input, Output output, CommandParserExecute parser) {
        this.input = input;
        this.output = output;
        this.parser = parser;
        this.runa = new Runa(initialize());
        this.monster = new ArrayDeque<>();
    }


    private RunaClass initialize() {
        output.output(Message.WELCOME_MESSAGE);
        RunaClass runaClass = null;
        output.output(Message.ENTER_NUMBER);
        do {
            try {
                runaClass = parser.parseClass(input.read());
            } catch (CharacterClassException e){
                output.output(Message.ENTER_NUMBER);
            }
        }while(runaClass ==  null);
        return runaClass;
    }

    private void shuffleCards() {

    }

}
