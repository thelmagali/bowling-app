package com.thelma;

import com.thelma.controller.Bowling;
import com.thelma.controller.GameInputReader;
import com.thelma.controller.impl.GameInputReaderFile;

import javax.enterprise.inject.spi.CDI;
import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Arrays;
import java.util.List;

@Singleton
public class App {

    @Inject
    private Bowling bowling;

    public static void main(String[] args) {
        CDI<Object> cdi = CDI.getCDIProvider().initialize();
        App main = cdi.select(App.class).get();
        main.run(Arrays.asList(args));
        cdi.close();
    }

    private void run(List<String> args) {
        try {
            if (args.size() != 1) {
                throw new Exception("1 argument is necessary. The name of the input file.");
            }
            GameInputReader reader = new GameInputReaderFile(args.get(0));
            bowling.play(reader);
            bowling.printResult();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

}
